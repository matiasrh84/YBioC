package com.ybc.ybioq.service;

import com.ybc.ybioq.entity.local.ConfiguracionReporteLayout;
import com.ybc.ybioq.repository.local.ConfiguracionReporteLayoutRepository;
import org.springframework.stereotype.Service;

@Service
public class ConfiguracionReporteLayoutService extends AbstractCrudService<ConfiguracionReporteLayout, Integer> {

    public ConfiguracionReporteLayoutService(ConfiguracionReporteLayoutRepository repository) {
        super(repository);
    }
}
