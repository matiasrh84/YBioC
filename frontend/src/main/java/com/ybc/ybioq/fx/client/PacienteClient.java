package com.ybc.ybioq.fx.client;

import com.ybc.ybioq.fx.client.dto.ObraSocialPacienteDto;
import com.ybc.ybioq.fx.client.dto.PacienteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PacienteClient extends BaseClient {

    @Autowired
    public PacienteClient(RestTemplate restTemplate,
                          @Value("${backend.api.base-url}") String baseUrl) {
        super(restTemplate, baseUrl, "/pacientes");
    }

    public void actualizarDatos(Integer idPaciente, LocalDate nacimiento, String telefono, String mail) {
        try {
            Map<String, Object> body = new HashMap<>();
            if (nacimiento != null) body.put("fechaNacimiento", nacimiento.toString());
            if (telefono != null && !telefono.isBlank()) body.put("telefono", telefono);
            if (mail     != null && !mail.isBlank())     body.put("mail", mail);
            restTemplate.put(serviceUrl + "/" + idPaciente + "/datos", body);
        } catch (RestClientResponseException ex) {
            throw toRuntimeException(ex);
        } catch (Exception ex) {
            throw new RuntimeException("No se pudo actualizar el paciente.", ex);
        }
    }

    public PacienteDto findById(Integer id) {
        try {
            return restTemplate.getForObject(serviceUrl + "/" + id, PacienteDto.class);
        } catch (RestClientResponseException ex) {
            if (ex.getStatusCode().value() == 404) return null;
            throw toRuntimeException(ex);
        } catch (Exception ex) {
            throw new RuntimeException("No se pudo obtener el paciente.", ex);
        }
    }

    public PacienteDto findByPersona(Integer idPersona) {
        try {
            return restTemplate.getForObject(
                    serviceUrl + "/por-persona?idPersona=" + idPersona, PacienteDto.class);
        } catch (RestClientResponseException ex) {
            if (ex.getStatusCode().value() == 404) return null;
            throw toRuntimeException(ex);
        } catch (Exception ex) {
            throw new RuntimeException("No se pudo buscar el paciente.", ex);
        }
    }

    public PacienteDto crearDesdePersona(Integer idPersona) {
        try {
            return restTemplate.postForObject(
                    serviceUrl + "/desde-persona?idPersona=" + idPersona, null, PacienteDto.class);
        } catch (RestClientResponseException ex) {
            throw toRuntimeException(ex);
        } catch (Exception ex) {
            throw new RuntimeException("No se pudo crear el paciente.", ex);
        }
    }

    public ObraSocialPacienteDto asignarObraSocial(Integer idPaciente, Integer idObraSocial, String numeroAfiliado) {
        try {
            java.util.Map<String, Object> body = new java.util.HashMap<>();
            body.put("idObraSocial", idObraSocial);
            body.put("numeroAfiliado", numeroAfiliado != null ? numeroAfiliado : "");
            return restTemplate.postForObject(
                    serviceUrl + "/" + idPaciente + "/obras-sociales", body, ObraSocialPacienteDto.class);
        } catch (RestClientResponseException ex) {
            throw toRuntimeException(ex);
        } catch (Exception ex) {
            throw new RuntimeException("No se pudo asignar la obra social.", ex);
        }
    }

    public List<ObraSocialPacienteDto> findObrasSociales(Integer idPaciente) {
        try {
            var response = restTemplate.exchange(
                    serviceUrl + "/" + idPaciente + "/obras-sociales",
                    HttpMethod.GET, HttpEntity.EMPTY,
                    new ParameterizedTypeReference<List<ObraSocialPacienteDto>>() {});
            return response.getBody() == null ? Collections.emptyList() : response.getBody();
        } catch (RestClientResponseException ex) {
            throw toRuntimeException(ex);
        } catch (Exception ex) {
            throw new RuntimeException("No se pudo obtener obras sociales del paciente.", ex);
        }
    }
}
