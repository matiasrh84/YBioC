package com.ybc.ybioq.service;

import com.ybc.ybioq.entity.local.ConfiguracionReporte;
import com.ybc.ybioq.repository.local.ConfiguracionReporteRepository;
import org.springframework.stereotype.Service;

@Service
public class ConfiguracionReporteService extends AbstractCrudService<ConfiguracionReporte, Integer> {

    public ConfiguracionReporteService(ConfiguracionReporteRepository repository) {
        super(repository);
    }
}
