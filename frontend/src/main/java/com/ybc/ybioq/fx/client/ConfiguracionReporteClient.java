package com.ybc.ybioq.fx.client;

import com.ybc.ybioq.fx.client.dto.ConfiguracionReporteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Component
public class ConfiguracionReporteClient extends BaseClient {

    @Autowired
    public ConfiguracionReporteClient(RestTemplate restTemplate,
                                      @Value("${backend.api.base-url}") String baseUrl) {
        super(restTemplate, baseUrl, "/configuracion-reporte");
    }

    public List<ConfiguracionReporteDto> findAll() {
        try {
            ResponseEntity<List<ConfiguracionReporteDto>> response = restTemplate.exchange(
                    serviceUrl, HttpMethod.GET, HttpEntity.EMPTY,
                    new ParameterizedTypeReference<>() {});
            return response.getBody() == null ? Collections.emptyList() : response.getBody();
        } catch (RestClientResponseException ex) {
            throw toRuntimeException(ex);
        } catch (Exception ex) {
            throw new RuntimeException("No se pudo obtener la configuración del informe.", ex);
        }
    }

    public ConfiguracionReporteDto save(ConfiguracionReporteDto dto) {
        try {
            return restTemplate.postForObject(serviceUrl, dto, ConfiguracionReporteDto.class);
        } catch (RestClientResponseException ex) {
            throw toRuntimeException(ex);
        } catch (Exception ex) {
            throw new RuntimeException("No se pudo guardar la configuración.", ex);
        }
    }

    public ConfiguracionReporteDto update(Integer id, ConfiguracionReporteDto dto) {
        try {
            ResponseEntity<ConfiguracionReporteDto> response = restTemplate.exchange(
                    serviceUrl + "/" + id, HttpMethod.PUT,
                    new HttpEntity<>(dto),
                    ConfiguracionReporteDto.class);
            return response.getBody();
        } catch (RestClientResponseException ex) {
            throw toRuntimeException(ex);
        } catch (Exception ex) {
            throw new RuntimeException("No se pudo actualizar la configuración.", ex);
        }
    }
}
