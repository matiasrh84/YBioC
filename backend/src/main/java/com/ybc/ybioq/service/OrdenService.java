package com.ybc.ybioq.service;

import com.ybc.ybioq.api.dto.CrearOrdenRequest;
import com.ybc.ybioq.api.dto.LineaOrdenDetalleDto;
import com.ybc.ybioq.api.dto.OrdenDetalleDto;
import com.ybc.ybioq.entity.local.*;
import com.ybc.ybioq.repository.local.*;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrdenService extends AbstractCrudService<Orden, Integer> {

    private final OrdenRepository ordenRepository;
    private final OrdenTienePracticaRepository ordenTienePracticaRepository;
    private final ResultadoRepository resultadoRepository;
    private final AnalisisRepository analisisRepository;
    private final PacienteRepository pacienteRepository;
    private final MedicoRepository medicoRepository;
    private final EspecialidadRepository especialidadRepository;
    private final ObraSocialRepository obraSocialRepository;
    private final UsuarioRepository usuarioRepository;
    private final PracticaRepository practicaRepository;

    public OrdenService(OrdenRepository ordenRepository,
                        OrdenTienePracticaRepository ordenTienePracticaRepository,
                        ResultadoRepository resultadoRepository,
                        AnalisisRepository analisisRepository,
                        PacienteRepository pacienteRepository,
                        MedicoRepository medicoRepository,
                        EspecialidadRepository especialidadRepository,
                        ObraSocialRepository obraSocialRepository,
                        UsuarioRepository usuarioRepository,
                        PracticaRepository practicaRepository) {
        super(ordenRepository);
        this.ordenRepository = ordenRepository;
        this.ordenTienePracticaRepository = ordenTienePracticaRepository;
        this.resultadoRepository = resultadoRepository;
        this.analisisRepository = analisisRepository;
        this.pacienteRepository = pacienteRepository;
        this.medicoRepository = medicoRepository;
        this.especialidadRepository = especialidadRepository;
        this.obraSocialRepository = obraSocialRepository;
        this.usuarioRepository = usuarioRepository;
        this.practicaRepository = practicaRepository;
    }

    public List<Orden> findByPaciente(Integer idPaciente) {
        return ordenRepository.findTop20ByIdPacientesIdOrderByFechaDesc(idPaciente);
    }

    public List<Orden> findUltimas() {
        return ordenRepository.findTop30ByOrderByFechaDesc();
    }

    public Page<Orden> findPaginated(int page, int size) {
        return findPaginated(page, size, null, null, null, null, null);
    }

    public Page<Orden> findPaginated(int page, int size,
                                     Integer idPaciente, Integer idObraSocial,
                                     LocalDate desde, LocalDate hasta, String q) {
        Specification<Orden> spec = buildSpec(idPaciente, idObraSocial, desde, hasta, q);
        return ordenRepository.findAll(spec,
                PageRequest.of(page, size, Sort.by("fecha").descending()));
    }

    private Specification<Orden> buildSpec(Integer idPaciente, Integer idObraSocial,
                                            LocalDate desde, LocalDate hasta, String q) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (idPaciente != null)
                predicates.add(cb.equal(root.get("idPacientes").get("id"), idPaciente));
            if (idObraSocial != null)
                predicates.add(cb.equal(root.get("idObrasocial").get("id"), idObraSocial));
            if (desde != null)
                predicates.add(cb.greaterThanOrEqualTo(root.get("fecha"), desde.atStartOfDay()));
            if (hasta != null)
                predicates.add(cb.lessThan(root.get("fecha"), hasta.plusDays(1).atStartOfDay()));
            if (q != null && !q.trim().isBlank()) {
                String lower = q.trim().toLowerCase();
                var persona = root.join("idPacientes", JoinType.LEFT).join("personasDni", JoinType.LEFT);
                List<Predicate> textPreds = new ArrayList<>();
                textPreds.add(cb.like(cb.lower(persona.get("apellido")), "%" + lower + "%"));
                textPreds.add(cb.like(cb.lower(persona.get("nombre")),   "%" + lower + "%"));
                textPreds.add(cb.like(root.get("numeroOrden"),            "%" + q.trim() + "%"));
                try {
                    Integer dni = Integer.parseInt(q.trim());
                    textPreds.add(cb.equal(persona.get("dni"), dni));
                } catch (NumberFormatException ignored) {}
                predicates.add(cb.or(textPreds.toArray(new Predicate[0])));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    public String calcularEstadoResultados(Integer idOrden) {
        long total       = resultadoRepository.countByOrden(idOrden);
        if (total == 0) return "Sin informar";
        long informados  = resultadoRepository.countInformadosByOrden(idOrden);
        if (informados == 0)      return "Sin informar";
        if (informados >= total)  return "Informada";
        return "Parcialmente informada";
    }

    public Optional<Orden> findByNumeroOrden(String numeroOrden) {
        return ordenRepository.findFirstByNumeroOrdenOrderByFechaDesc(numeroOrden);
    }

    @Transactional
    public Orden crearOrden(CrearOrdenRequest req) {
        Paciente paciente = pacienteRepository.findById(req.idPaciente())
                .orElseThrow(() -> new IllegalArgumentException("Paciente no encontrado: " + req.idPaciente()));
        Medico medico = medicoRepository.findById(req.idMedico())
                .orElseThrow(() -> new IllegalArgumentException("Médico no encontrado: " + req.idMedico()));
        Especialidad especialidad = especialidadRepository.findById(req.idEspecialidad())
                .orElseThrow(() -> new IllegalArgumentException("Especialidad no encontrada: " + req.idEspecialidad()));
        ObraSocial obraSocial = obraSocialRepository.findById(req.idObraSocial())
                .orElseThrow(() -> new IllegalArgumentException("Obra social no encontrada: " + req.idObraSocial()));
        Usuario usuario = usuarioRepository.findById(req.idUsuario())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado: " + req.idUsuario()));

        // Calcular total
        BigDecimal total = req.practicas().stream()
                .map(CrearOrdenRequest.LineaOrdenRequest::precio)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Crear cabecera
        Orden orden = new Orden();
        orden.setIdPacientes(paciente);
        orden.setIdMedicos(medico);
        orden.setIdEspecialidades(especialidad);
        orden.setIdObrasocial(obraSocial);
        orden.setIdUsuarios(usuario);
        orden.setNumeroOrden(req.numeroOrden() != null ? req.numeroOrden() : "");
        orden.setTipoOrden(req.tipoOrden() != null ? req.tipoOrden() : "Ambulatorio");
        orden.setPeriodo(req.periodo() != null ? req.periodo() : LocalDateTime.now().getYear() * 100 + LocalDateTime.now().getMonthValue());
        orden.setTotal(total);
        orden.setEstadoOrden(0);
        orden.setEstadoEnviado(0);
        orden.setFecha(LocalDateTime.now());
        orden.setHora(LocalDateTime.now().toLocalTime());
        orden.setIdExpediente(0); // sin historia clínica por ahora
        Orden ordenGuardada = ordenRepository.save(orden);

        // Crear detalle y stubs de resultado
        for (CrearOrdenRequest.LineaOrdenRequest linea : req.practicas()) {
            // Detalle de orden
            OrdenTienePracticaId otp_id = new OrdenTienePracticaId();
            otp_id.setIdPracticas(linea.idPractica());
            otp_id.setIdOrdenes(ordenGuardada.getId());

            OrdenTienePractica detalle = new OrdenTienePractica();
            detalle.setId(otp_id);
            detalle.setPrecioPractica(linea.precio() != null ? linea.precio() : BigDecimal.ZERO);
            detalle.setCodPracticaFac(linea.codPracticaFac() != null ? linea.codPracticaFac() : "");
            detalle.setFactura(0);
            detalle.setEstado(1);
            ordenTienePracticaRepository.save(detalle);

            // Stubs de resultado por cada analisis de la practica
            List<Analisis> analisis = analisisRepository.findByPracticaIdConRelaciones(linea.idPractica());
            for (Analisis a : analisis) {
                ResultadoId rid = new ResultadoId();
                rid.setIdAnalisis(a.getId());
                rid.setIdPracticas(linea.idPractica());
                rid.setIdOrdenes(ordenGuardada.getId());
                rid.setIdUsuarios(usuario.getId());

                Resultado resultado = new Resultado();
                resultado.setId(rid);
                resultado.setResultado("-");
                resultado.setObservacion("");
                resultado.setEstadoImprime(0);
                resultado.setImprimirNombre(0);
                resultadoRepository.save(resultado);
            }
        }

        return ordenGuardada;
    }

    public OrdenDetalleDto getDetalle(Integer idOrden) {
        Orden orden = ordenRepository.findById(idOrden)
                .orElseThrow(() -> new IllegalArgumentException("Orden no encontrada: " + idOrden));
        List<OrdenTienePractica> lineas = ordenTienePracticaRepository.findByIdIdOrdenes(idOrden);
        List<LineaOrdenDetalleDto> practicas = lineas.stream().map(l -> {
            var prac = practicaRepository.findById(l.getId().getIdPracticas()).orElse(null);
            return new LineaOrdenDetalleDto(
                    l.getId().getIdPracticas(),
                    prac != null ? prac.getCodigo()        : null,
                    prac != null ? prac.getDeterminacion() : null,
                    l.getPrecioPractica(),
                    l.getCodPracticaFac());
        }).toList();

        var paciente = orden.getIdPacientes();
        var persona  = paciente != null ? paciente.getPersonasDni() : null;
        var medico   = orden.getIdMedicos();
        var esp      = orden.getIdEspecialidades();
        var os       = orden.getIdObrasocial();
        return new OrdenDetalleDto(
                orden.getId(), orden.getNumeroOrden(), orden.getPeriodo(), orden.getFecha(),
                paciente != null ? paciente.getId()   : null,
                persona  != null ? persona.getApellido() + ", " + persona.getNombre() : null,
                persona  != null ? persona.getDni()   : null,
                medico   != null ? medico.getId()     : null,
                medico   != null ? medico.getApellido() + ", " + medico.getNombre() : null,
                esp      != null ? esp.getId()        : null,
                esp      != null ? esp.getNombre()    : null,
                os       != null ? os.getId()         : null,
                os       != null ? os.getNombre()     : null,
                orden.getTipoOrden(), orden.getTotal(),
                orden.getEstadoOrden(), calcularEstadoResultados(idOrden),
                practicas);
    }

    @Transactional
    public Orden modificarOrdenCompleta(Integer idOrden, CrearOrdenRequest req) {
        Orden orden = ordenRepository.findById(idOrden)
                .orElseThrow(() -> new IllegalArgumentException("Orden no encontrada: " + idOrden));
        if (resultadoRepository.countInformadosByOrden(idOrden) > 0)
            throw new IllegalStateException("La orden tiene resultados informados y no puede modificarse.");

        Medico medico = medicoRepository.findById(req.idMedico())
                .orElseThrow(() -> new IllegalArgumentException("Médico no encontrado: " + req.idMedico()));
        Especialidad especialidad = especialidadRepository.findById(req.idEspecialidad())
                .orElseThrow(() -> new IllegalArgumentException("Especialidad no encontrada: " + req.idEspecialidad()));
        ObraSocial obraSocial = obraSocialRepository.findById(req.idObraSocial())
                .orElseThrow(() -> new IllegalArgumentException("Obra social no encontrada: " + req.idObraSocial()));
        Usuario usuario = usuarioRepository.findById(req.idUsuario())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado: " + req.idUsuario()));

        orden.setIdMedicos(medico);
        orden.setIdEspecialidades(especialidad);
        orden.setIdObrasocial(obraSocial);
        if (req.numeroOrden() != null) orden.setNumeroOrden(req.numeroOrden());
        if (req.tipoOrden()   != null) orden.setTipoOrden(req.tipoOrden());
        BigDecimal total = req.practicas().stream()
                .map(CrearOrdenRequest.LineaOrdenRequest::precio)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        orden.setTotal(total);
        ordenRepository.save(orden);

        // Reemplazar detalle y stubs
        ordenTienePracticaRepository.deleteByOrden(idOrden);
        resultadoRepository.deleteByOrden(idOrden);

        for (CrearOrdenRequest.LineaOrdenRequest linea : req.practicas()) {
            OrdenTienePracticaId otpId = new OrdenTienePracticaId();
            otpId.setIdPracticas(linea.idPractica());
            otpId.setIdOrdenes(idOrden);
            OrdenTienePractica detalle = new OrdenTienePractica();
            detalle.setId(otpId);
            detalle.setPrecioPractica(linea.precio() != null ? linea.precio() : BigDecimal.ZERO);
            detalle.setCodPracticaFac(linea.codPracticaFac() != null ? linea.codPracticaFac() : "");
            detalle.setFactura(0);
            detalle.setEstado(1);
            ordenTienePracticaRepository.save(detalle);

            List<Analisis> analisis = analisisRepository.findByPracticaIdConRelaciones(linea.idPractica());
            for (Analisis a : analisis) {
                ResultadoId rid = new ResultadoId();
                rid.setIdAnalisis(a.getId());
                rid.setIdPracticas(linea.idPractica());
                rid.setIdOrdenes(idOrden);
                rid.setIdUsuarios(usuario.getId());
                Resultado resultado = new Resultado();
                resultado.setId(rid);
                resultado.setResultado("-");
                resultado.setObservacion("");
                resultado.setEstadoImprime(0);
                resultado.setImprimirNombre(0);
                resultadoRepository.save(resultado);
            }
        }
        return orden;
    }
}
