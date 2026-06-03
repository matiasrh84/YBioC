package com.ybc.ybioq.fx.client;

import com.ybc.ybioq.fx.client.dto.PracticaDto;
import com.ybc.ybioq.fx.client.dto.PrecioConsultaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Component
public class PracticaClient extends BaseClient {

    @Autowired
    public PracticaClient(RestTemplate restTemplate, @Value("${backend.api.base-url}") String baseUrl) {
        super(restTemplate, baseUrl, "/practicas");
    }

    public List<PracticaDto> findAll() {
        try {
            var response = restTemplate.exchange(serviceUrl, HttpMethod.GET, HttpEntity.EMPTY,
                    new ParameterizedTypeReference<List<PracticaDto>>() {});
            return response.getBody() == null ? Collections.emptyList() : response.getBody();
        } catch (RestClientResponseException ex) {
            throw toRuntimeException(ex);
        } catch (Exception ex) {
            throw new RuntimeException("No se pudo obtener las prácticas.", ex);
        }
    }

    public PracticaDto findById(Integer id) {
        try {
            return restTemplate.getForObject(serviceUrl + "/" + id, PracticaDto.class);
        } catch (RestClientResponseException ex) {
            throw toRuntimeException(ex);
        } catch (Exception ex) {
            throw new RuntimeException("No se pudo obtener la práctica.", ex);
        }
    }

    public PracticaDto save(PracticaDto dto) {
        try {
            return restTemplate.postForObject(serviceUrl, dto, PracticaDto.class);
        } catch (RestClientResponseException ex) {
            throw toRuntimeException(ex);
        } catch (Exception ex) {
            throw new RuntimeException("No se pudo guardar la práctica.", ex);
        }
    }

    public PracticaDto update(Integer id, PracticaDto dto) {
        try {
            restTemplate.put(serviceUrl + "/" + id, dto);
            return findById(id);
        } catch (RestClientResponseException ex) {
            throw toRuntimeException(ex);
        } catch (Exception ex) {
            throw new RuntimeException("No se pudo modificar la práctica.", ex);
        }
    }

    public PrecioConsultaDto consultarPrecio(Integer idPractica, Integer idObraSocial) {
        try {
            return restTemplate.getForObject(
                    serviceUrl + "/" + idPractica + "/precio?idObraSocial=" + idObraSocial,
                    PrecioConsultaDto.class);
        } catch (RestClientResponseException ex) {
            throw toRuntimeException(ex);
        } catch (Exception ex) {
            throw new RuntimeException("No se pudo consultar el precio.", ex);
        }
    }
}
