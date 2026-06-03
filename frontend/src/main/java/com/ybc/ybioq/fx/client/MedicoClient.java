package com.ybc.ybioq.fx.client;

import com.ybc.ybioq.fx.client.dto.MedicoDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class MedicoClient extends BaseClient {

    private static final Logger log = LoggerFactory.getLogger(MedicoClient.class);

    @Autowired
    public MedicoClient(RestTemplate restTemplate,
                        @Value("${backend.api.base-url}") String baseUrl) {
        super(restTemplate, baseUrl, "/medicos");
    }

    public List<MedicoDto> findAll() {
        try {
            ResponseEntity<List<MedicoDto>> response = restTemplate.exchange(
                    serviceUrl,
                    HttpMethod.GET,
                    HttpEntity.EMPTY,
                    new ParameterizedTypeReference<List<MedicoDto>>() {
                    }
            );
            List<MedicoDto> body = response.getBody();
            log.info("MedicoClient.findAll() → {} médicos recibidos", body == null ? 0 : body.size());
            return body == null ? Collections.emptyList() : body;
        } catch (RestClientResponseException ex) {
            log.error("MedicoClient.findAll() HTTP error: {}", ex.getStatusCode(), ex);
            throw toRuntimeException(ex);
        } catch (Exception ex) {
            log.error("MedicoClient.findAll() excepción: {}", ex.getMessage(), ex);
            throw new RuntimeException("No se pudo obtener medicos: " + ex.getMessage(), ex);
        }
    }

    public MedicoDto save(MedicoDto medico) {
        try {
            return restTemplate.postForObject(
                    serviceUrl,
                    medico,
                    MedicoDto.class
            );
        } catch (RestClientResponseException ex) {
            throw toRuntimeException(ex);
        } catch (Exception ex) {
            throw new RuntimeException("No se pudo guardar el medico.", ex);
        }
    }

    public void actualizarEspecialidades(Integer idMedico, List<Integer> idEspecialidades) {
        try {
            restTemplate.put(serviceUrl + "/" + idMedico + "/especialidades", idEspecialidades);
        } catch (RestClientResponseException ex) {
            throw toRuntimeException(ex);
        } catch (Exception ex) {
            throw new RuntimeException("No se pudo actualizar las especialidades.", ex);
        }
    }
}
