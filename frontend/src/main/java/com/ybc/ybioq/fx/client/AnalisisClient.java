package com.ybc.ybioq.fx.client;

import com.ybc.ybioq.fx.client.dto.AnalisisDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class AnalisisClient extends BaseClient {

    @Autowired
    public AnalisisClient(RestTemplate restTemplate, @Value("${backend.api.base-url}") String baseUrl) {
        super(restTemplate, baseUrl, "/analisis");
    }

    public List<AnalisisDto> findByPractica(Integer idPractica) {
        AnalisisDto[] result = restTemplate.getForObject(
                serviceUrl + "?idPractica=" + idPractica, AnalisisDto[].class);
        return result != null ? Arrays.asList(result) : Collections.emptyList();
    }

    public AnalisisDto findById(Integer id) {
        return restTemplate.getForObject(serviceUrl + "/" + id, AnalisisDto.class);
    }

    public AnalisisDto save(AnalisisDto dto) {
        return restTemplate.postForObject(serviceUrl, dto, AnalisisDto.class);
    }

    public AnalisisDto update(Integer id, AnalisisDto dto) {
        restTemplate.put(serviceUrl + "/" + id, dto);
        return findById(id);
    }

    public void delete(Integer id) {
        restTemplate.delete(serviceUrl + "/" + id);
    }
}
