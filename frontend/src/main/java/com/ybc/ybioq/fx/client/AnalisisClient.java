package com.ybc.ybioq.fx.client;

import com.ybc.ybioq.fx.client.dto.AnalisisDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AnalisisClient extends BaseClient {
    @Autowired
    public AnalisisClient(RestTemplate restTemplate, @Value("${backend.api.base-url}") String baseUrl) {
        super(restTemplate, baseUrl, "/analisis");
    }

    public AnalisisDto findById(Integer id) {
        return restTemplate.getForObject(serviceUrl + "/" + id, AnalisisDto.class);
    }

    public AnalisisDto save(AnalisisDto dto) {
        return restTemplate.postForObject(serviceUrl, dto, AnalisisDto.class);
    }
}
