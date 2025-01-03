package com.ybc.ybioq;

import com.ybc.ybioq.view.Inicio;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import javax.swing.*;

@SpringBootApplication
public class YbioqApplication {

    public static void main(String[] args) {
        // Inicializa el contexto de Spring
        ApplicationContext context = new SpringApplicationBuilder(YbioqApplication.class)
                .headless(false)
                .run(args);

        // Lanza el JFrame de inicio
        SwingUtilities.invokeLater(() -> {
            Inicio inicio = new Inicio(context); // Pasamos el contexto
            inicio.setVisible(true);
        });
    }
}
