package com.ybc.ybioq.fx.client;

import com.ybc.ybioq.fx.client.dto.AnticipoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

@Component
public class AnticipoClient extends BaseClient {

    @Autowired
    public AnticipoClient(RestTemplate restTemplate,
                          @Value("${backend.api.base-url}") String baseUrl) {
        super(restTemplate, baseUrl, "/anticipos");
    }

    public AnticipoDto save(AnticipoDto dto) {
        try {
            return restTemplate.postForObject(serviceUrl, dto, AnticipoDto.class);
        } catch (RestClientResponseException ex) {
            throw toRuntimeException(ex);
        } catch (Exception ex) {
            throw new RuntimeException("No se pudo registrar el anticipo.", ex);
        }
    }
}
