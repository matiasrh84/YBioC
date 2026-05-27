package com.ybc.ybioq.fx.client;

import com.ybc.ybioq.fx.client.dto.MetodoDto;
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
public class MetodoClient extends BaseClient {

    @Autowired
    public MetodoClient(RestTemplate restTemplate,
                        @Value("${backend.api.base-url}") String baseUrl) {
        super(restTemplate, baseUrl, "/metodos");
    }

    public List<MetodoDto> findAll() {
        try {
            ResponseEntity<List<MetodoDto>> response = restTemplate.exchange(
                    serviceUrl,
                    HttpMethod.GET,
                    HttpEntity.EMPTY,
                    new ParameterizedTypeReference<>() {
                    }
            );
            return response.getBody() == null ? Collections.emptyList() : response.getBody();
        } catch (RestClientResponseException ex) {
            throw toRuntimeException(ex);
        } catch (Exception ex) {
            throw new RuntimeException("No se pudo obtener los metodos.", ex);
        }
    }

    public MetodoDto save(MetodoDto dto) {
        try {
            return restTemplate.postForObject(serviceUrl, dto, MetodoDto.class);
        } catch (RestClientResponseException ex) {
            throw toRuntimeException(ex);
        } catch (Exception ex) {
            throw new RuntimeException("No se pudo guardar el metodo.", ex);
        }
    }
}
