package com.ybc.ybioq.config;

import com.ybc.ybioq.view.Inicio_nuevo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwingConfig {

    @Bean
    public Inicio_nuevo inicioNuevo() {
        return new Inicio_nuevo();
    }
}
