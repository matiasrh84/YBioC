package com.ybc.ybioq.fx.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

@Component
public class ReporteClient extends BaseClient {

    @Autowired
    public ReporteClient(RestTemplate restTemplate,
                         @Value("${backend.api.base-url}") String baseUrl) {
        super(restTemplate, baseUrl, "/reportes");
    }

    /** Comprobante de orden (prácticas + precios). */
    public byte[] comprobanteOrden(Integer idOrden) {
        try {
            return restTemplate.getForObject(serviceUrl + "/orden/" + idOrden, byte[].class);
        } catch (RestClientResponseException ex) {
            throw toRuntimeException(ex);
        } catch (Exception ex) {
            throw new RuntimeException("No se pudo generar el comprobante.", ex);
        }
    }

    /** Informe de mesada (referencia para el bioquímico). */
    public byte[] informeMesada(Integer idOrden) {
        try {
            return restTemplate.getForObject(serviceUrl + "/mesada/" + idOrden, byte[].class);
        } catch (RestClientResponseException ex) {
            throw toRuntimeException(ex);
        } catch (Exception ex) {
            throw new RuntimeException("No se pudo generar el informe de mesada.", ex);
        }
    }

    /** Muestra del informe con datos ficticios — todas las secciones. */
    public byte[] muestra() {
        try {
            return restTemplate.getForObject(serviceUrl + "/muestra", byte[].class);
        } catch (RestClientResponseException ex) {
            throw toRuntimeException(ex);
        } catch (Exception ex) {
            throw new RuntimeException("No se pudo generar la muestra del informe.", ex);
        }
    }

    /** Informe final de resultados (para el paciente/médico). */
    public byte[] informeFinal(Integer idOrden) {
        try {
            return restTemplate.getForObject(serviceUrl + "/informe/" + idOrden, byte[].class);
        } catch (RestClientResponseException ex) {
            throw toRuntimeException(ex);
        } catch (Exception ex) {
            throw new RuntimeException("No se pudo generar el informe.", ex);
        }
    }
}
