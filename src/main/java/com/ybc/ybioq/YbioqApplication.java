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
            Inicio inicio = new Inicio();
            inicio.setVisible(true);

            new Thread(() -> {
                ApplicationContext context = new SpringApplicationBuilder(YbioqApplication.class)
                        .headless(false)
                        .run(args);

                inicio.setContext(context);
            }).start();
        });
    }
}
