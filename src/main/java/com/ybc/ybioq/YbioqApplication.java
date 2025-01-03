package com.ybc.ybioq;

import com.ybc.ybioq.view.Inicio;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import javax.swing.*;

@SpringBootApplication
public class YbioqApplication {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Inicio inicio = new Inicio(); // Inicializa la ventana sin el contexto todavía
            inicio.setVisible(true);

            // Cargar el contexto de Spring en un hilo separado
            new Thread(() -> {
                ApplicationContext context = new SpringApplicationBuilder(YbioqApplication.class)
                        .headless(false)
                        .run(args);

                // Notifica al formulario que el contexto está listo
                inicio.setContext(context);
            }).start();
        });
    }
}
