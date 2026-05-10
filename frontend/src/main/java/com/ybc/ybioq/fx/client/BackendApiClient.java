package com.ybc.ybioq.fx.client;

import com.ybc.ybioq.fx.client.dto.EspecialidadDto;
import com.ybc.ybioq.fx.client.dto.LoginRequest;
import com.ybc.ybioq.fx.client.dto.MedicoDto;
import com.ybc.ybioq.fx.client.dto.UsuarioSession;
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
public class BackendApiClient {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String apiBaseUrl;

    public BackendApiClient(@Value("${backend.api.base-url:http://localhost:8080/api}") String apiBaseUrl) {
        this.apiBaseUrl = normalize(apiBaseUrl);
    }

    public UsuarioSession login(String usuario, String clave) {
        try {
            return restTemplate.postForObject(
                    apiBaseUrl + "/auth/login",
                    new LoginRequest(usuario, clave),
                    UsuarioSession.class
            );
        } catch (RestClientResponseException ex) {
            throw toRuntimeException(ex);
        } catch (Exception ex) {
            throw new RuntimeException("No se pudo conectar con el backend.", ex);
        }
    }

    public List<EspecialidadDto> findEspecialidades() {
        try {
            ResponseEntity<List<EspecialidadDto>> response = restTemplate.exchange(
                    apiBaseUrl + "/especialidades",
                    HttpMethod.GET,
                    HttpEntity.EMPTY,
                    new ParameterizedTypeReference<>() {
                    }
            );
            return response.getBody() == null ? Collections.emptyList() : response.getBody();
        } catch (RestClientResponseException ex) {
            throw toRuntimeException(ex);
        } catch (Exception ex) {
            throw new RuntimeException("No se pudo obtener especialidades.", ex);
        }
    }

    public EspecialidadDto saveEspecialidad(EspecialidadDto especialidad) {
        try {
            return restTemplate.postForObject(
                    apiBaseUrl + "/especialidades",
                    especialidad,
                    EspecialidadDto.class
            );
        } catch (RestClientResponseException ex) {
            throw toRuntimeException(ex);
        } catch (Exception ex) {
            throw new RuntimeException("No se pudo guardar la especialidad.", ex);
        }
    }

    public List<MedicoDto> findMedicos() {
        try {
            ResponseEntity<List<MedicoDto>> response = restTemplate.exchange(
                    apiBaseUrl + "/medicos",
                    HttpMethod.GET,
                    HttpEntity.EMPTY,
                    new ParameterizedTypeReference<>() {
                    }
            );
            return response.getBody() == null ? Collections.emptyList() : response.getBody();
        } catch (RestClientResponseException ex) {
            throw toRuntimeException(ex);
        } catch (Exception ex) {
            throw new RuntimeException("No se pudo obtener medicos.", ex);
        }
    }

    public MedicoDto saveMedico(MedicoDto medico) {
        try {
            return restTemplate.postForObject(
                    apiBaseUrl + "/medicos",
                    medico,
                    MedicoDto.class
            );
        } catch (RestClientResponseException ex) {
            throw toRuntimeException(ex);
        } catch (Exception ex) {
            throw new RuntimeException("No se pudo guardar el medico.", ex);
        }
    }

    private RuntimeException toRuntimeException(RestClientResponseException ex) {
        String defaultMessage = ex.getStatusCode().value() == 401
                ? "Usuario o clave incorrectos."
                : "Error en backend: " + ex.getStatusCode().value();
        String message = extractMessage(ex.getResponseBodyAsString(), defaultMessage);
        return new RuntimeException(message);
    }

    private String extractMessage(String body, String defaultMessage) {
        if (body == null || body.isBlank()) {
            return defaultMessage;
        }
        int marker = body.indexOf("\"message\":\"");
        if (marker < 0) {
            return defaultMessage;
        }
        int start = marker + "\"message\":\"".length();
        int end = body.indexOf('"', start);
        if (end <= start) {
            return defaultMessage;
        }
        return body.substring(start, end).replace("\\\"", "\"");
    }

    private String normalize(String baseUrl) {
        if (baseUrl == null || baseUrl.isBlank()) {
            return "http://localhost:8080/api";
        }
        String value = baseUrl.trim();
        return value.endsWith("/") ? value.substring(0, value.length() - 1) : value;
    }
}
