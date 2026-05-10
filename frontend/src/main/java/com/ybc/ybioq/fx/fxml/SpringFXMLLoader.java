package com.ybc.ybioq.fx.fxml;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;

@Component
public class SpringFXMLLoader {

    private final ApplicationContext applicationContext;

    public SpringFXMLLoader(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public Parent load(String resourcePath) {
        URL resource = getClass().getResource(resourcePath);
        if (resource == null) {
            throw new IllegalStateException("No se encontro el recurso FXML: " + resourcePath);
        }

        FXMLLoader loader = new FXMLLoader(resource);
        loader.setControllerFactory(applicationContext::getBean);
        try {
            return loader.load();
        } catch (IOException e) {
            throw new IllegalStateException("No se pudo cargar la vista: " + resourcePath, e);
        }
    }
}
