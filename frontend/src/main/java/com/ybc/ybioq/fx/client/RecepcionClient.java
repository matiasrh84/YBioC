package com.ybc.ybioq.fx.client;

import com.ybc.ybioq.fx.client.dto.PageResponse;
import com.ybc.ybioq.fx.client.dto.RecepcionFilaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

@Component
public class RecepcionClient extends BaseClient {

    @Autowired
    public RecepcionClient(RestTemplate restTemplate,
                           @Value("${backend.api.base-url}") String baseUrl) {
        super(restTemplate, baseUrl, "/recepcion");
    }

    public PageResponse<RecepcionFilaDto> findPaginated(int page, int size, String q) {
        try {
            StringBuilder url = new StringBuilder(serviceUrl)
                    .append("?page=").append(page).append("&size=").append(size);
            if (q != null && !q.isBlank())
                url.append("&q=").append(URLEncoder.encode(q, StandardCharsets.UTF_8));
            var response = restTemplate.exchange(url.toString(), HttpMethod.GET, HttpEntity.EMPTY,
                    new ParameterizedTypeReference<PageResponse<RecepcionFilaDto>>() {});
            return response.getBody();
        } catch (RestClientResponseException ex) {
            throw toRuntimeException(ex);
        } catch (Exception ex) {
            throw new RuntimeException("No se pudo obtener la lista.", ex);
        }
    }

    public List<RecepcionFilaDto> buscar(String q) {
        try {
            String url = serviceUrl + "/buscar?q=" + URLEncoder.encode(q, StandardCharsets.UTF_8);
            var response = restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY,
                    new ParameterizedTypeReference<List<RecepcionFilaDto>>() {});
            return response.getBody() == null ? Collections.emptyList() : response.getBody();
        } catch (RestClientResponseException ex) {
            throw toRuntimeException(ex);
        } catch (Exception ex) {
            throw new RuntimeException("No se pudo buscar.", ex);
        }
    }
}
