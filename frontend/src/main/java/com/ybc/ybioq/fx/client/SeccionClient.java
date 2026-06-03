package com.ybc.ybioq.fx.client;

import com.ybc.ybioq.fx.client.dto.SeccionDto;
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
public class SeccionClient extends BaseClient {

    @Autowired
    public SeccionClient(RestTemplate restTemplate, @Value("${backend.api.base-url}") String baseUrl) {
        super(restTemplate, baseUrl, "/secciones");
    }

    public List<SeccionDto> findAll() {
        try {
            var response = restTemplate.exchange(serviceUrl, HttpMethod.GET, HttpEntity.EMPTY,
                    new ParameterizedTypeReference<List<SeccionDto>>() {});
            return response.getBody() == null ? Collections.emptyList() : response.getBody();
        } catch (RestClientResponseException ex) {
            throw toRuntimeException(ex);
        } catch (Exception ex) {
            throw new RuntimeException("No se pudo obtener las secciones.", ex);
        }
    }

    public SeccionDto save(SeccionDto dto) {
        try {
            return restTemplate.postForObject(serviceUrl, dto, SeccionDto.class);
        } catch (RestClientResponseException ex) {
            throw toRuntimeException(ex);
        } catch (Exception ex) {
            throw new RuntimeException("No se pudo guardar la sección.", ex);
        }
    }

    public SeccionDto update(Integer id, SeccionDto dto) {
        try {
            restTemplate.put(serviceUrl + "/" + id, dto);
            return dto;
        } catch (RestClientResponseException ex) {
            throw toRuntimeException(ex);
        } catch (Exception ex) {
            throw new RuntimeException("No se pudo modificar la sección.", ex);
        }
    }

    public void delete(Integer id) {
        try {
            restTemplate.delete(serviceUrl + "/" + id);
        } catch (RestClientResponseException ex) {
            throw toRuntimeException(ex);
        } catch (Exception ex) {
            throw new RuntimeException("No se pudo eliminar la sección.", ex);
        }
    }
}
