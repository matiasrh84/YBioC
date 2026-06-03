package com.ybc.ybioq.api;

import com.ybc.ybioq.service.ReporteService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/reportes")
public class ReporteApiController {

    private final ReporteService reporteService;

    public ReporteApiController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }

    /** Comprobante de orden — lista de prácticas y precios. */
    @GetMapping("/orden/{idOrden}")
    public ResponseEntity<byte[]> comprobanteOrden(@PathVariable Integer idOrden) {
        try {
            byte[] pdf = reporteService.generarComprobanteOrden(idOrden);
            return pdfResponse(pdf, "orden_" + idOrden + ".pdf");
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al generar el comprobante: " + ex.getMessage());
        }
    }

    /** Informe de mesada — referencia para el bioquímico en el banco de trabajo. */
    @GetMapping("/mesada/{idOrden}")
    public ResponseEntity<byte[]> informeMesada(@PathVariable Integer idOrden) {
        try {
            byte[] pdf = reporteService.generarInformeMesada(idOrden);
            return pdfResponse(pdf, "mesada_" + idOrden + ".pdf");
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al generar el informe de mesada: " + ex.getMessage());
        }
    }

    /** Informe final de resultados — documento para el paciente y el médico. */
    @GetMapping("/informe/{idOrden}")
    public ResponseEntity<byte[]> informeFinal(@PathVariable Integer idOrden) {
        try {
            byte[] pdf = reporteService.generarInformeFinal(idOrden);
            return pdfResponse(pdf, "informe_" + idOrden + ".pdf");
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al generar el informe: " + ex.getMessage());
        }
    }

    /** Muestra del informe con datos ficticios — todas las secciones. */
    @GetMapping("/muestra")
    public ResponseEntity<byte[]> muestra() {
        try {
            byte[] pdf = reporteService.generarMuestra();
            return pdfResponse(pdf, "muestra_informe.pdf");
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al generar la muestra: " + ex.getMessage());
        }
    }

    private ResponseEntity<byte[]> pdfResponse(byte[] pdf, String filename) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("inline", filename);
        headers.setContentLength(pdf.length);
        return ResponseEntity.ok().headers(headers).body(pdf);
    }
}
