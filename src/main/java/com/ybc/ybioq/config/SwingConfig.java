package com.ybc.ybioq.config;

import com.ybc.ybioq.view.Inicio;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwingConfig {

    @Bean
    public Inicio inicioNuevo() {
        return new Inicio();
    }
}
