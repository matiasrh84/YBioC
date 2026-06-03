package com.ybc.ybioq.service;

import com.ybc.ybioq.entity.local.Analisis;
import com.ybc.ybioq.entity.local.ConfiguracionReporte;
import com.ybc.ybioq.entity.local.Orden;
import com.ybc.ybioq.entity.local.OrdenTienePractica;
import com.ybc.ybioq.entity.local.Practica;
import com.ybc.ybioq.entity.local.Resultado;
import com.ybc.ybioq.entity.local.TipoInforme;
import com.ybc.ybioq.repository.local.AnalisisRepository;
import com.ybc.ybioq.repository.local.OrdenTienePracticaRepository;
import com.ybc.ybioq.repository.local.PracticaRepository;
import com.ybc.ybioq.repository.local.ResultadoRepository;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReporteService {

    private static final DateTimeFormatter FMT      = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private static final DateTimeFormatter FMT_DATE = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter FMT_HORA = DateTimeFormatter.ofPattern("HH:mm");

    private final OrdenService                   ordenService;
    private final ConfiguracionReporteService    configuracionReporteService;
    private final OrdenTienePracticaRepository   ordenTienePracticaRepository;
    private final PracticaRepository             practicaRepository;
    private final AnalisisRepository             analisisRepository;
    private final ResultadoRepository            resultadoRepository;

    public ReporteService(OrdenService ordenService,
                          ConfiguracionReporteService configuracionReporteService,
                          OrdenTienePracticaRepository ordenTienePracticaRepository,
                          PracticaRepository practicaRepository,
                          AnalisisRepository analisisRepository,
                          ResultadoRepository resultadoRepository) {
        this.ordenService                 = ordenService;
        this.configuracionReporteService  = configuracionReporteService;
        this.ordenTienePracticaRepository = ordenTienePracticaRepository;
        this.practicaRepository           = practicaRepository;
        this.analisisRepository           = analisisRepository;
        this.resultadoRepository          = resultadoRepository;
    }

    // ── Comprobante de orden (prácticas + precios) ────────────────────────────

    @Transactional(readOnly = true)
    public byte[] generarComprobanteOrden(Integer idOrden) throws Exception {
        Orden orden = findOrden(idOrden);

        String pacienteNombre = nombrePaciente(orden);
        String osNombre       = orden.getIdObrasocial() != null ? orden.getIdObrasocial().getNombre() : "";
        String fechaStr       = orden.getFecha() != null ? orden.getFecha().format(FMT) : "";

        List<OrdenTienePractica> detalles = ordenTienePracticaRepository.findByIdIdOrdenes(idOrden);
        List<Map<String, ?>> filas = new ArrayList<>();
        for (OrdenTienePractica d : detalles) {
            Practica p = practicaRepository.findById(d.getId().getIdPracticas()).orElse(null);
            Map<String, String> fila = new HashMap<>();
            fila.put("numero_afiliado", "");
            fila.put("nombre_afiliado", pacienteNombre);
            fila.put("orden",           safe(orden.getNumeroOrden()));
            fila.put("codigo_analisis", p != null && p.getCodigo() != null ? String.valueOf(p.getCodigo()) : "");
            fila.put("analisis",        p != null ? safe(p.getDeterminacion()) : "");
            fila.put("importe",         d.getPrecioPractica() != null ? d.getPrecioPractica().toPlainString() : "0");
            fila.put("codigo_fac",      safe(d.getCodPracticaFac()));
            fila.put("fecha",           fechaStr);
            filas.add(fila);
        }

        ConfiguracionReporte cfg = configuracionReporteService.cargarActiva();

        Map<String, Object> params = new HashMap<>();
        params.put("matricula",             "");
        params.put("periodo",               orden.getPeriodo() != null ? String.valueOf(orden.getPeriodo()) : "");
        params.put("obra_social",           osNombre);
        params.put("num_obra_social",       "");
        params.put("fecha",                 fechaStr);
        params.put("laboratorio",           safe(cfg.getNombre()));
        params.put("domicilio_lab",         safe(cfg.getDireccion()));
        params.put("localidad",             "");
        params.put("pacientes",             pacienteNombre);
        params.put("practicas",             String.valueOf(detalles.size()));
        params.put("total_letras_pesos",    "");
        params.put("total",                 orden.getTotal() != null ? orden.getTotal().toPlainString() : "0");
        params.put("total_letras_centavos", "");
        params.put("foto",                  imgStream(cfg.getLogo()));
        params.put("nombre",                safe(cfg.getNombre()));
        params.put("direccion",             safe(cfg.getDireccion()));
        params.put("telefono",              safe(cfg.getTelefono()));
        params.put("mail",                  safe(cfg.getMail()));

        return fillAndExport("/Reportes/Ordenes.jrxml", params, filas);
    }

    // ── Informe de mesada (para el bioquímico en el banco de trabajo) ─────────

    @Transactional(readOnly = true)
    public byte[] generarInformeMesada(Integer idOrden) throws Exception {
        Orden orden = findOrden(idOrden);

        String pacienteNombre = nombrePaciente(orden);
        String fechaStr       = orden.getFecha() != null ? orden.getFecha().format(FMT_DATE) : "";

        List<Resultado> resultados = resultadoRepository.findByOrdenId(idOrden);
        List<Map<String, ?>> filas = new ArrayList<>();
        for (Resultado r : resultados) {
            Analisis a = analisisRepository.findById(r.getId().getIdAnalisis()).orElse(null);
            Practica p = practicaRepository.findById(r.getId().getIdPracticas()).orElse(null);

            Map<String, String> fila = new HashMap<>();
            fila.put("seccion",          p != null ? safe(p.getDeterminacion()) : "");
            fila.put("titulos",          a != null ? safe(a.getNombre()) : "");
            String valorConUnidad = safe(r.getResultado())
                    + (a != null && a.getUnidad() != null && !a.getUnidad().isBlank()
                       ? "  " + a.getUnidad() : "");
            fila.put("nombre_analisis1", valorConUnidad.isBlank() ? "-" : valorConUnidad);
            fila.put("nombre_analisis2", a != null ? safe(a.getValoresReferencia()) : "");
            filas.add(fila);
        }

        ConfiguracionReporte cfg = configuracionReporteService.cargarActiva();

        Map<String, Object> params = new HashMap<>();
        params.put("id_orden",          safe(orden.getNumeroOrden()));
        params.put("laboratorio",       safe(cfg.getNombre()));
        params.put("paciente",          pacienteNombre);
        params.put("fecha_realizacion", fechaStr);
        params.put("fercha_entrega",    LocalDate.now().format(FMT_DATE));

        return fillAndExport("/Reportes/Informe_mesada_predefinido.jrxml", params, filas);
    }

    // ── Informe final de resultados (para el paciente / médico) ──────────────

    @Transactional(readOnly = true)
    public byte[] generarInformeFinal(Integer idOrden) throws Exception {
        Orden              orden = findOrden(idOrden);
        ConfiguracionReporte cfg = configuracionReporteService.cargarActiva();

        String pacienteNombre = nombrePaciente(orden);
        String protocolo      = safe(orden.getNumeroOrden());
        String fechaStr       = orden.getFecha() != null ? orden.getFecha().format(FMT_DATE) : "";
        String horaStr        = orden.getFecha() != null ? orden.getFecha().format(FMT_HORA)  : "";
        String medicoNombre   = orden.getIdMedicos()      != null ? safe(orden.getIdMedicos().getNombre())      : "";
        String especialidad   = orden.getIdEspecialidades() != null ? safe(orden.getIdEspecialidades().getNombre()) : "";

        // Parámetros comunes a todas las secciones
        Map<String, Object> paramsComunes = new HashMap<>();
        paramsComunes.put("laboratorio",     safe(cfg.getNombre()));
        paramsComunes.put("paciente",        pacienteNombre);
        paramsComunes.put("protocolo",       protocolo);
        paramsComunes.put("fecha",           fechaStr);
        paramsComunes.put("medico",          medicoNombre);
        paramsComunes.put("logo",            imgStream(cfg.getLogo()));
        paramsComunes.put("membrete",        imgStream(cfg.getMembrete()));
        paramsComunes.put("observacion_lab", safe(cfg.getObservacion()));

        // 1. Portada
        JasperPrint compuesto = generarPortadaPrint(cfg, pacienteNombre, protocolo,
                                                    fechaStr, horaStr, medicoNombre, especialidad);

        // 2. Secciones de resultados agrupadas por tipo, en orden definido
        Map<TipoInforme, List<Map<String, Object>>> porTipo =
                agruparResultadosPorTipo(resultadoRepository.findByOrdenId(idOrden));

        for (TipoInforme tipo : new TipoInforme[]{
                TipoInforme.FILAS, TipoInforme.COLUMNAS, TipoInforme.PROTEINOGRAMA,
                TipoInforme.CULTIVO, TipoInforme.HEMOGRAMA_FIJO, TipoInforme.HEMOGRAMA_FILAS}) {

            List<Map<String, Object>> filas = porTipo.get(tipo);
            if (filas == null || filas.isEmpty()) continue;

            JasperReport template = compilar(templateParaTipo(tipo));
            JasperPrint seccion = JasperFillManager.fillReport(
                    template, new HashMap<>(paramsComunes),
                    toDataSource(filas));
            appendPages(compuesto, seccion);
        }

        // 3. Página de firma (si está configurada)
        if (cfg.getFirma() != null && cfg.getFirma().length > 0) {
            JasperReport firmaTemplate = compilar("/Reportes/informe_firma.jrxml");
            Map<String, Object> firmaParams = new HashMap<>();
            firmaParams.put("firma",       imgStream(cfg.getFirma()));
            firmaParams.put("laboratorio", safe(cfg.getNombre()));
            JasperPrint firmaPrint = JasperFillManager.fillReport(
                    firmaTemplate, firmaParams, new JREmptyDataSource());
            appendPages(compuesto, firmaPrint);
        }

        return JasperExportManager.exportReportToPdf(compuesto);
    }

    // ── Muestra del informe (datos ficticios, todas las secciones) ───────────

    public byte[] generarMuestra() throws Exception {
        ConfiguracionReporte cfg = configuracionReporteService.cargarActiva();

        String hoy    = LocalDate.now().format(FMT_DATE);
        String paciente    = "García López, Juan";
        String protocolo   = "00001";
        String medico      = "Dr. Ejemplo Muestra";
        String especialidad = "Clínica Médica";

        Map<String, Object> paramsComunes = new HashMap<>();
        paramsComunes.put("laboratorio",     safe(cfg.getNombre()));
        paramsComunes.put("paciente",        paciente);
        paramsComunes.put("protocolo",       protocolo);
        paramsComunes.put("fecha",           hoy);
        paramsComunes.put("medico",          medico);
        paramsComunes.put("logo",            imgStream(cfg.getLogo()));
        paramsComunes.put("membrete",        imgStream(cfg.getMembrete()));
        paramsComunes.put("observacion_lab", safe(cfg.getObservacion()));

        JasperPrint compuesto = generarPortadaPrint(cfg, paciente, protocolo, hoy, "", medico, especialidad);

        // ── FILAS: análisis estándar ──
        appendSeccion(compuesto, TipoInforme.FILAS, paramsComunes, List.of(
            fila("BIOQUÍMICA GENERAL", "Glucemia",        "95",    "mg/dl", "70 - 110",    "Glucosa oxidasa"),
            fila("BIOQUÍMICA GENERAL", "Colesterol total", "180",  "mg/dl", "< 200",        ""),
            fila("BIOQUÍMICA GENERAL", "Triglicéridos",   "120",   "mg/dl", "< 150",        ""),
            fila("BIOQUÍMICA GENERAL", "Urea",            "28",    "mg/dl", "10 - 50",      ""),
            fila("BIOQUÍMICA GENERAL", "Creatinina",      "0.90",  "mg/dl", "0.50 - 1.10",  ""),
            fila("BIOQUÍMICA GENERAL", "Ácido Úrico",     "5.2",   "mg/dl", "3.5 - 7.2",    "")
        ));

        // ── COLUMNAS: hemograma diferencial ──
        appendSeccion(compuesto, TipoInforme.COLUMNAS, paramsComunes, List.of(
            fila("HEMOGRAMA DIFERENCIAL", "Neutrófilos",  "65", "%", "55 - 70", ""),
            fila("HEMOGRAMA DIFERENCIAL", "Linfocitos",   "28", "%", "20 - 40", ""),
            fila("HEMOGRAMA DIFERENCIAL", "Monocitos",    "5",  "%", "2 - 8",   ""),
            fila("HEMOGRAMA DIFERENCIAL", "Eosinófilos",  "2",  "%", "1 - 4",   ""),
            fila("HEMOGRAMA DIFERENCIAL", "Basófilos",    "0",  "%", "0 - 1",   "")
        ));

        // ── PROTEINOGRAMA ──
        appendSeccion(compuesto, TipoInforme.PROTEINOGRAMA, paramsComunes, List.of(
            fila("PROTEINOGRAMA SÉRICO", "Proteínas Totales", "7.2",  "g/dl", "6.0 - 8.0",   ""),
            fila("PROTEINOGRAMA SÉRICO", "Albúmina",         "60.5",  "%",    "55.0 - 65.0", ""),
            fila("PROTEINOGRAMA SÉRICO", "Alfa-1 Globulina", "4.2",   "%",    "2.0 - 5.0",   ""),
            fila("PROTEINOGRAMA SÉRICO", "Alfa-2 Globulina", "10.1",  "%",    "7.0 - 12.0",  ""),
            fila("PROTEINOGRAMA SÉRICO", "Beta Globulina",   "13.8",  "%",    "8.0 - 15.0",  ""),
            fila("PROTEINOGRAMA SÉRICO", "Gamma Globulina",  "11.4",  "%",    "10.0 - 20.0", "")
        ));

        // ── CULTIVO: bacteriológico con antibiograma ──
        appendSeccion(compuesto, TipoInforme.CULTIVO, paramsComunes, List.of(
            fila("UROCULTIVO", "Resultado del cultivo",      "E. coli > 100.000 UFC/ml", "", "", ""),
            fila("UROCULTIVO", "Ampicilina",                 "Resistente (R)",            "", "", ""),
            fila("UROCULTIVO", "Amoxicilina/Clavulánico",    "Sensible (S)",              "", "", ""),
            fila("UROCULTIVO", "Ciprofloxacina",             "Sensible (S)",              "", "", ""),
            fila("UROCULTIVO", "Trimetoprima/Sulfametoxazol","Intermedio (I)",            "", "", ""),
            fila("UROCULTIVO", "Nitrofurantoína",            "Sensible (S)",              "", "", ""),
            fila("UROCULTIVO", "Ceftriaxona",                "Sensible (S)",              "", "", "")
        ));

        // ── HEMOGRAMA_FIJO ──
        appendSeccion(compuesto, TipoInforme.HEMOGRAMA_FIJO, paramsComunes, List.of(
            fila("HEMATOLOGÍA", "Glóbulos rojos", "4.80",   "mill/mm³", "4.50 - 5.90",      ""),
            fila("HEMATOLOGÍA", "Hemoglobina",    "14.2",   "g/dl",     "13.0 - 17.0",      ""),
            fila("HEMATOLOGÍA", "Hematocrito",    "43",     "%",        "40 - 54",           ""),
            fila("HEMATOLOGÍA", "VCM",            "89",     "fl",       "80 - 99",           ""),
            fila("HEMATOLOGÍA", "HCM",            "29.6",   "pg",       "27 - 33",           ""),
            fila("HEMATOLOGÍA", "Leucocitos",     "6.800",  "/mm³",     "4.500 - 11.000",   ""),
            fila("HEMATOLOGÍA", "Plaquetas",      "210.000","/mm³",     "150.000 - 400.000", "")
        ));

        // ── HEMOGRAMA_FILAS ──
        appendSeccion(compuesto, TipoInforme.HEMOGRAMA_FILAS, paramsComunes, List.of(
            fila("RECUENTO CELULAR", "Eritrocitos",  "4.80",   "mill/mm³", "4.50 - 5.90",   ""),
            fila("RECUENTO CELULAR", "Leucocitos",   "6.800",  "/mm³",     "4.500 - 11.000",""),
            fila("RECUENTO CELULAR", "Plaquetas",    "210.000","/mm³",     "150.000 - 400.000",""),
            fila("RECUENTO CELULAR", "Reticulocitos","1.2",    "%",        "0.5 - 2.5",     "")
        ));

        // ── Firma ──
        if (cfg.getFirma() != null && cfg.getFirma().length > 0) {
            JasperReport ft = compilar("/Reportes/informe_firma.jrxml");
            Map<String, Object> fp = new HashMap<>();
            fp.put("firma",       imgStream(cfg.getFirma()));
            fp.put("laboratorio", safe(cfg.getNombre()));
            appendPages(compuesto, JasperFillManager.fillReport(ft, fp, new JREmptyDataSource()));
        }

        return JasperExportManager.exportReportToPdf(compuesto);
    }

    private void appendSeccion(JasperPrint compuesto, TipoInforme tipo,
                                Map<String, Object> paramsComunes,
                                List<Map<String, Object>> filas) throws Exception {
        JasperReport template = compilar(templateParaTipo(tipo));
        JasperPrint  seccion  = JasperFillManager.fillReport(
                template, new HashMap<>(paramsComunes),
                toDataSource(filas));
        appendPages(compuesto, seccion);
    }

    /** Convierte List<Map<String,Object>> al tipo que acepta JRMapCollectionDataSource. */
    @SuppressWarnings("unchecked")
    private JRMapCollectionDataSource toDataSource(List<Map<String, Object>> filas) {
        return new JRMapCollectionDataSource(
                (Collection<Map<String, ?>>) (Collection<?>) filas);
    }

    private Map<String, Object> fila(String seccion, String analisis, String resultado,
                                      String unidad, String referencia, String metodo) {
        Map<String, Object> m = new HashMap<>();
        m.put("seccion",    seccion);
        m.put("analisis",   analisis);
        m.put("resultado",  resultado);
        m.put("unidad",     unidad);
        m.put("referencia", referencia);
        m.put("metodo",     metodo);
        m.put("observacion", "");
        return m;
    }

    // ── Helpers de ensamblado ─────────────────────────────────────────────────

    private JasperPrint generarPortadaPrint(ConfiguracionReporte cfg,
                                            String paciente, String protocolo,
                                            String fecha, String hora,
                                            String medico, String especialidad) throws Exception {
        // Opción A: portada como imagen PNG cargada por el laboratorio
        if (cfg.getPortada() != null && cfg.getPortada().length > 0) {
            JasperReport t = compilar("/Reportes/informe_portada_imagen.jrxml");
            Map<String, Object> p = new HashMap<>();
            p.put("portada", imgStream(cfg.getPortada()));
            return JasperFillManager.fillReport(t, p, new JREmptyDataSource());
        }
        // Opción B: portada genérica con datos del laboratorio y del paciente
        JasperReport t = compilar("/Reportes/pagina_1.jrxml");
        Map<String, Object> p = new HashMap<>();
        p.put("laboratorio", safe(cfg.getNombre()));
        p.put("paciente",    paciente);
        p.put("protocolo",   protocolo);
        p.put("medico",      medico);
        p.put("especialidad", especialidad);
        p.put("motivo",      "");
        p.put("lugar",       "");
        p.put("fecha",       fecha);
        p.put("hora",        hora);
        p.put("logo",        imgStream(cfg.getLogo()));
        p.put("direccion",   safe(cfg.getDireccion()));
        p.put("telefono",    safe(cfg.getTelefono()));
        p.put("mail",        safe(cfg.getMail()));
        p.put("observacion", safe(cfg.getObservacion()));
        return JasperFillManager.fillReport(t, p, new JREmptyDataSource());
    }

    private Map<TipoInforme, List<Map<String, Object>>> agruparResultadosPorTipo(
            List<Resultado> resultados) {

        Map<TipoInforme, List<Map<String, Object>>> mapa = new LinkedHashMap<>();
        for (Resultado r : resultados) {
            Analisis a = analisisRepository.findById(r.getId().getIdAnalisis()).orElse(null);
            Practica p = practicaRepository.findById(r.getId().getIdPracticas()).orElse(null);
            if (a == null || p == null) continue;

            TipoInforme tipo = p.getTipoInforme() != null ? p.getTipoInforme() : TipoInforme.FILAS;

            Map<String, Object> fila = new HashMap<>();
            fila.put("seccion",    safe(p.getDeterminacion()));
            fila.put("analisis",   safe(a.getNombre()));
            fila.put("resultado",  safe(r.getResultado()));
            fila.put("unidad",     safe(a.getUnidad()));
            fila.put("referencia", safe(a.getValoresReferencia()));
            fila.put("metodo",     a.getMetodo() != null ? safe(a.getMetodo().getNombre()) : "");
            fila.put("observacion", safe(r.getObservacion()));

            mapa.computeIfAbsent(tipo, k -> new ArrayList<>()).add(fila);
        }
        return mapa;
    }

    private String templateParaTipo(TipoInforme tipo) {
        return switch (tipo) {
            case COLUMNAS      -> "/Reportes/informe_seccion_columnas.jrxml";
            case PROTEINOGRAMA -> "/Reportes/informe_seccion_proteinograma.jrxml";
            case CULTIVO       -> "/Reportes/informe_seccion_cultivo.jrxml";
            default            -> "/Reportes/informe_seccion_filas.jrxml";
        };
    }

    private void appendPages(JasperPrint base, JasperPrint add) {
        for (Object page : add.getPages()) {
            base.addPage((JRPrintPage) page);
        }
    }

    // ── Helpers genéricos ─────────────────────────────────────────────────────

    private byte[] fillAndExport(String jrxmlPath,
                                  Map<String, Object> params,
                                  List<Map<String, ?>> filas) throws Exception {
        JasperReport report = compilar(jrxmlPath);
        JasperPrint  print  = JasperFillManager.fillReport(
                report, params,
                filas.isEmpty() ? new JREmptyDataSource() : new JRMapCollectionDataSource(filas));
        return JasperExportManager.exportReportToPdf(print);
    }

    private JasperReport compilar(String classpathPath) throws Exception {
        try (InputStream is = getClass().getResourceAsStream(classpathPath)) {
            if (is == null)
                throw new IllegalStateException("Template no encontrado en classpath: " + classpathPath);
            return JasperCompileManager.compileReport(is);
        }
    }

    private Orden findOrden(Integer idOrden) {
        return ordenService.findById(idOrden)
                .orElseThrow(() -> new IllegalArgumentException("Orden no encontrada: " + idOrden));
    }

    private String nombrePaciente(Orden orden) {
        if (orden.getIdPacientes() == null) return "";
        var persona = orden.getIdPacientes().getPersonasDni();
        if (persona == null) return "";
        return safe(persona.getApellido()) + ", " + safe(persona.getNombre());
    }

    private Object imgStream(byte[] bytes) {
        return (bytes != null && bytes.length > 0) ? new ByteArrayInputStream(bytes) : null;
    }

    private String safe(String v) { return v != null ? v : ""; }
}
