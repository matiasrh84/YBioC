package com.ybc.ybioq;

import com.ybc.ybioq.view.Inicio_nuevo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import javax.swing.*;

@SpringBootApplication
public class YbioqApplication {

    @Autowired
    public static ApplicationContext context;

    public static void main(String[] args) {

        context = new SpringApplicationBuilder(Inicio_nuevo.class).headless(false).run(args);

        SwingUtilities.invokeLater(() -> {
            Inicio_nuevo inicio = context.getBean(Inicio_nuevo.class);
            inicio.setVisible(true);
        });
    }
}
