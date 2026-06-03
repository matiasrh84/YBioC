package com.ybc.ybioq.fx.client;

import com.ybc.ybioq.fx.client.dto.PersonaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PersonaClient extends BaseClient {

    @Autowired
    public PersonaClient(RestTemplate restTemplate,
                         @Value("${backend.api.base-url}") String baseUrl) {
        super(restTemplate, baseUrl, "/personas");
    }

    public PersonaDto crear(String apellido, String nombre, Integer dni) {
        try {
            Map<String, Object> body = new HashMap<>();
            body.put("apellido", apellido);
            body.put("nombre", nombre);
            if (dni != null) body.put("dni", dni);
            return restTemplate.postForObject(serviceUrl, body, PersonaDto.class);
        } catch (RestClientResponseException ex) {
            throw toRuntimeException(ex);
        } catch (Exception ex) {
            throw new RuntimeException("No se pudo crear la persona.", ex);
        }
    }

    public List<PersonaDto> buscar(String q) {
        try {
            var response = restTemplate.exchange(
                    serviceUrl + "/buscar?q=" + java.net.URLEncoder.encode(q, java.nio.charset.StandardCharsets.UTF_8),
                    HttpMethod.GET,
                    HttpEntity.EMPTY,
                    new ParameterizedTypeReference<List<PersonaDto>>() {
                    }
            );
            return response.getBody() == null ? Collections.emptyList() : response.getBody();
        } catch (RestClientResponseException ex) {
            throw toRuntimeException(ex);
        } catch (Exception ex) {
            throw new RuntimeException("No se pudo buscar personas.", ex);
        }
    }
}
