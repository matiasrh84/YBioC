package com.ybc.ybioq.fx.client;

import com.ybc.ybioq.fx.client.dto.HealthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class HealthClient extends BaseClient {

    @Autowired
    public HealthClient(RestTemplate restTemplate,
                        @Value("${backend.api.base-url}") String baseUrl) {
        super(restTemplate, baseUrl, "/health");
    }

    public HealthResponse verificar() {
        try {
            HealthResponse response = restTemplate.getForObject(serviceUrl, HealthResponse.class);
            if (response == null) throw new RuntimeException("El servidor no respondió correctamente.");
            return response;
        } catch (Exception ex) {
            throw new RuntimeException("No se pudo conectar al servidor: " + ex.getMessage(), ex);
        }
    }
}
