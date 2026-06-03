package com.ybc.ybioq.api;

import com.ybc.ybioq.api.dto.ObraSocialDto;
import com.ybc.ybioq.entity.local.ObraSocial;
import com.ybc.ybioq.service.ObraSocialService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/api/obras-sociales")
public class ObraSocialApiController {

    private final ObraSocialService obraSocialService;

    public ObraSocialApiController(ObraSocialService obraSocialService) {
        this.obraSocialService = obraSocialService;
    }

    @GetMapping
    public List<ObraSocialDto> findAll() {
        return obraSocialService.findAll().stream()
                .sorted(Comparator.comparing(ObraSocial::getRazonSocial, Comparator.nullsLast(String::compareToIgnoreCase)))
                .map(this::toDto)
                .toList();
    }

    @PostMapping
    public ObraSocialDto save(@RequestBody ObraSocialDto req) {
        ObraSocial os = req.id() == null
                ? new ObraSocial()
                : obraSocialService.findById(req.id())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Obra social no encontrada."));

        os.setCodigo(blank(req.codigo()));
        os.setRazonSocial(blank(req.razonSocial()));
        os.setNombre(blank(req.nombre()));
        os.setCuit(blank(req.cuit()));
        os.setCodigoFacturacion(blank(req.codigoFacturacion()));
        os.setTelefono(blank(req.telefono()));
        os.setMail1(blank(req.mail1()));
        os.setMail2(blank(req.mail2()));
        os.setMail3(blank(req.mail3()));
        os.setWeb(blank(req.web()));
        os.setDireccion(blank(req.direccion()));
        os.setFechaDeAlta(parseDate(req.fechaDeAlta()));
        os.setNombreReferente(blank(req.nombreReferente()));
        os.setCelularReferente(blank(req.celularReferente()));
        os.setPeriodoNbu(blank(req.periodoNbu()));
        os.setImporteUnidadDeArancel(req.importeUnidadDeArancel());
        os.setPorcentajeAfiliado(blank(req.porcentajeAfiliado()));
        os.setPorcentajeDescuento(blank(req.porcentajeDescuento()));
        os.setFacturaAltaComplejidad(req.facturaAltaComplejidad());
        os.setFacturaNoNomenclados(req.facturaNoNomenclados());
        os.setFacturaPor(blank(req.facturaPor()));
        os.setFacturaPorPaciente(req.facturaPorPaciente());
        os.setImprimeDobleInforme(blank(req.imprimeDobleInforme()));
        os.setD998(blank(req.d998()));
        os.setSubtotalPorPaciente(req.subtotalPorPaciente());
        os.setTieneCategorizacion(req.tieneCategorizacion());
        os.setTipoDeFacturacion(blank(req.tipoDeFacturacion()));
        os.setTipoDeFacturacionDirectaOColegio(blank(req.tipoDeFacturacionDirectaOColegio()));
        os.setTipoIva(blank(req.tipoIva()));
        os.setEstado(req.estado());

        try {
            return toDto(obraSocialService.guardar(os));
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, ex.getMessage());
        }
    }

    private String blank(String s) {
        return (s == null || s.isBlank()) ? null : s.trim();
    }

    private LocalDate parseDate(String s) {
        try {
            return (s == null || s.isBlank()) ? null : LocalDate.parse(s);
        } catch (Exception e) {
            return null;
        }
    }

    private ObraSocialDto toDto(ObraSocial os) {
        return new ObraSocialDto(
                os.getId(),
                os.getCodigo(),
                os.getRazonSocial(),
                os.getNombre(),
                os.getCuit(),
                os.getCodigoFacturacion(),
                os.getTelefono(),
                os.getMail1(), os.getMail2(), os.getMail3(),
                os.getWeb(),
                os.getDireccion(),
                os.getFechaDeAlta() != null ? os.getFechaDeAlta().toString() : null,
                os.getNombreReferente(),
                os.getCelularReferente(),
                os.getPeriodoNbu(),
                os.getImporteUnidadDeArancel(),
                os.getPorcentajeAfiliado(),
                os.getPorcentajeDescuento(),
                os.isFacturaAltaComplejidad(),
                os.isFacturaNoNomenclados(),
                os.getFacturaPor(),
                os.isFacturaPorPaciente(),
                os.getImprimeDobleInforme(),
                os.getD998(),
                os.isSubtotalPorPaciente(),
                os.isTieneCategorizacion(),
                os.getTipoDeFacturacion(),
                os.getTipoDeFacturacionDirectaOColegio(),
                os.getTipoIva(),
                os.isEstado()
        );
    }
}
