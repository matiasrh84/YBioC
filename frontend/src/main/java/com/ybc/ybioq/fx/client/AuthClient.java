package com.ybc.ybioq.fx.client;

import com.ybc.ybioq.fx.client.dto.LoginRequest;
import com.ybc.ybioq.fx.client.dto.UsuarioSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthClient extends BaseClient{

    @Autowired
    public AuthClient(RestTemplate restTemplate,
                      @Value("${backend.api.base-url}") String baseUrl) {
        super(restTemplate, baseUrl, "/auth/login");
    }

    public UsuarioSession login(String usuario, String clave) {
        try {
            return restTemplate.postForObject(
                    serviceUrl,
                    new LoginRequest(usuario, clave),
                    UsuarioSession.class
            );
        } catch (RestClientResponseException ex) {
            throw toRuntimeException(ex);
        } catch (Exception ex) {
            throw new RuntimeException("No se pudo conectar con el backend.", ex);
        }
    }
}
