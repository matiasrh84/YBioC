package com.ybc.ybioq.fx.client;

import com.ybc.ybioq.fx.client.dto.ActualizarResultadoRequest;
import com.ybc.ybioq.fx.client.dto.ResultadoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Component
public class ResultadoClient extends BaseClient {

    @Autowired
    public ResultadoClient(RestTemplate restTemplate,
                           @Value("${backend.api.base-url}") String baseUrl) {
        super(restTemplate, baseUrl, "/resultados");
    }

    public List<ResultadoDto> findByOrden(Integer idOrden) {
        try {
            var response = restTemplate.exchange(
                    serviceUrl + "/orden/" + idOrden,
                    HttpMethod.GET,
                    HttpEntity.EMPTY,
                    new ParameterizedTypeReference<List<ResultadoDto>>() {});
            return response.getBody() == null ? Collections.emptyList() : response.getBody();
        } catch (RestClientResponseException ex) {
            throw toRuntimeException(ex);
        } catch (Exception ex) {
            throw new RuntimeException("No se pudo obtener resultados de la orden.", ex);
        }
    }

    public ResultadoDto actualizar(Integer idAnalisis, Integer idPracticas, Integer idOrdenes, Integer idUsuarios,
                                   ActualizarResultadoRequest request) {
        try {
            return restTemplate.exchange(
                    serviceUrl + "?idAnalisis=" + idAnalisis + "&idPracticas=" + idPracticas
                            + "&idOrdenes=" + idOrdenes + "&idUsuarios=" + idUsuarios,
                    HttpMethod.PUT,
                    new HttpEntity<>(request),
                    ResultadoDto.class).getBody();
        } catch (RestClientResponseException ex) {
            throw toRuntimeException(ex);
        } catch (Exception ex) {
            throw new RuntimeException("No se pudo actualizar el resultado.", ex);
        }
    }
}
