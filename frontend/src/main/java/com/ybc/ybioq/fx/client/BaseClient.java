package com.ybc.ybioq.fx.client;

import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

public abstract class BaseClient {

    protected final RestTemplate restTemplate;
    protected final String serviceUrl;

    public BaseClient(RestTemplate restTemplate, String baseUrl, String endpoint) {
        this.restTemplate = restTemplate;
        this.serviceUrl = normalize(baseUrl) + endpoint;
    }

    protected RuntimeException toRuntimeException(RestClientResponseException ex) {
        String defaultMessage = ex.getStatusCode().value() == 401
                ? "Usuario o clave incorrectos."
                : "Error en backend: " + ex.getStatusCode().value();
        String message = extractMessage(ex.getResponseBodyAsString(), defaultMessage);
        return new RuntimeException(message);
    }

    protected String extractMessage(String body, String defaultMessage) {
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
