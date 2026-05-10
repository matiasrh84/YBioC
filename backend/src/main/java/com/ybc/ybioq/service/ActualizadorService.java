package com.ybc.ybioq.service;

import com.ybc.ybioq.model.Actualizacion;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Log4j2
public class ActualizadorService {

    private static final String ACTUALIZACION_URL = "https://matiasrh84.github.io/actualizador/actualizacion.json";
    private static final String VERSION_ACTUAL = "1.1.0";

    public Actualizacion verificarActualizacion() {
        RestTemplate restTemplate = new RestTemplate();
        try {
            return restTemplate.getForObject(ACTUALIZACION_URL, Actualizacion.class);
        } catch (Exception e) {
            log.error(e);
            return null;
        }
    }

    public boolean hayNuevaVersion(Actualizacion actualizacion) {
        if (actualizacion == null) {
            return false;
        }
        return VERSION_ACTUAL.compareTo(actualizacion.getLatestVersion()) < 0;
    }
}
