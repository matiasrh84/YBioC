package com.ybc.ybioq.service;

import com.ybc.ybioq.entity.local.ConfiguracionReporte;
import com.ybc.ybioq.repository.local.ConfiguracionReporteRepository;
import org.springframework.stereotype.Service;

@Service
public class ConfiguracionReporteService extends AbstractCrudService<ConfiguracionReporte, Integer> {

    private final ConfiguracionReporteRepository repo;

    public ConfiguracionReporteService(ConfiguracionReporteRepository repository) {
        super(repository);
        this.repo = repository;
    }

    /** Devuelve la primera configuración guardada, o una instancia vacía si no existe ninguna. */
    public ConfiguracionReporte cargarActiva() {
        return repo.findAll().stream().findFirst().orElse(new ConfiguracionReporte());
    }
}
