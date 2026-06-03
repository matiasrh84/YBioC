package com.ybc.ybioq.fx.client;

import com.ybc.ybioq.fx.client.dto.ModificarOrdenRequest;
import com.ybc.ybioq.fx.client.dto.OrdenDetalleDto;
import com.ybc.ybioq.fx.client.dto.OrdenDto;
import com.ybc.ybioq.fx.client.dto.PageResponse;
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
import java.util.Map;

@Component
public class OrdenClient extends BaseClient {

    @Autowired
    public OrdenClient(RestTemplate restTemplate,
                       @Value("${backend.api.base-url}") String baseUrl) {
        super(restTemplate, baseUrl, "/ordenes");
    }

    public OrdenDetalleDto getDetalle(Integer id) {
        try {
            return restTemplate.getForObject(serviceUrl + "/" + id + "/detalle", OrdenDetalleDto.class);
        } catch (RestClientResponseException ex) {
            if (ex.getStatusCode().value() == 404) return null;
            throw toRuntimeException(ex);
        } catch (Exception ex) {
            throw new RuntimeException("No se pudo obtener el detalle de la orden.", ex);
        }
    }

    public OrdenDto modificarCompleta(Integer id, java.util.Map<String, Object> request) {
        try {
            var entity = new org.springframework.http.HttpEntity<>(request);
            var response = restTemplate.exchange(
                    serviceUrl + "/" + id,
                    HttpMethod.PUT, entity, OrdenDto.class);
            return response.getBody();
        } catch (RestClientResponseException ex) {
            throw toRuntimeException(ex);
        } catch (Exception ex) {
            throw new RuntimeException("No se pudo modificar la orden.", ex);
        }
    }

    public OrdenDto findByNumero(String numero) {
        try {
            return restTemplate.getForObject(
                    serviceUrl + "/por-numero?numero=" + numero, OrdenDto.class);
        } catch (RestClientResponseException ex) {
            if (ex.getStatusCode().value() == 404) return null;
            throw toRuntimeException(ex);
        } catch (Exception ex) {
            throw new RuntimeException("No se pudo buscar la orden.", ex);
        }
    }

    public OrdenDto crear(Map<String, Object> request) {
        try {
            return restTemplate.postForObject(serviceUrl, request, OrdenDto.class);
        } catch (RestClientResponseException ex) {
            throw toRuntimeException(ex);
        } catch (Exception ex) {
            throw new RuntimeException("No se pudo crear la orden.", ex);
        }
    }

    public OrdenDto modificar(Integer id, ModificarOrdenRequest req) {
        try {
            var entity = new org.springframework.http.HttpEntity<>(req);
            var response = restTemplate.exchange(
                    serviceUrl + "/" + id,
                    org.springframework.http.HttpMethod.PATCH,
                    entity, OrdenDto.class);
            return response.getBody();
        } catch (RestClientResponseException ex) {
            throw toRuntimeException(ex);
        } catch (Exception ex) {
            throw new RuntimeException("No se pudo modificar la orden.", ex);
        }
    }

    public PageResponse<OrdenDto> findPaginated(int page, int size,
                                                  Integer idPaciente, Integer idObraSocial,
                                                  java.time.LocalDate desde, java.time.LocalDate hasta,
                                                  String q) {
        try {
            StringBuilder url = new StringBuilder(serviceUrl)
                    .append("?page=").append(page).append("&size=").append(size);
            if (idPaciente   != null) url.append("&idPaciente=").append(idPaciente);
            if (idObraSocial != null) url.append("&idObraSocial=").append(idObraSocial);
            if (desde        != null) url.append("&desde=").append(desde);
            if (hasta        != null) url.append("&hasta=").append(hasta);
            if (q != null && !q.isBlank())
                url.append("&q=").append(java.net.URLEncoder.encode(q, java.nio.charset.StandardCharsets.UTF_8));
            var response = restTemplate.exchange(url.toString(), HttpMethod.GET, HttpEntity.EMPTY,
                    new ParameterizedTypeReference<PageResponse<OrdenDto>>() {});
            return response.getBody();
        } catch (RestClientResponseException ex) {
            throw toRuntimeException(ex);
        } catch (Exception ex) {
            throw new RuntimeException("No se pudo obtener las órdenes.", ex);
        }
    }

    public List<OrdenDto> ultimas() {
        try {
            var response = restTemplate.exchange(
                    serviceUrl + "/ultimas",
                    HttpMethod.GET, HttpEntity.EMPTY,
                    new ParameterizedTypeReference<List<OrdenDto>>() {});
            return response.getBody() == null ? Collections.emptyList() : response.getBody();
        } catch (RestClientResponseException ex) {
            throw toRuntimeException(ex);
        } catch (Exception ex) {
            throw new RuntimeException("No se pudo obtener las últimas órdenes.", ex);
        }
    }

    public List<OrdenDto> recientes(Integer idPaciente) {
        try {
            var response = restTemplate.exchange(
                    serviceUrl + "/recientes?idPaciente=" + idPaciente,
                    HttpMethod.GET, HttpEntity.EMPTY,
                    new ParameterizedTypeReference<List<OrdenDto>>() {});
            return response.getBody() == null ? Collections.emptyList() : response.getBody();
        } catch (RestClientResponseException ex) {
            throw toRuntimeException(ex);
        } catch (Exception ex) {
            throw new RuntimeException("No se pudo obtener órdenes recientes.", ex);
        }
    }
}
