package com.ybc.ybioq.fx.controller;

import com.ybc.ybioq.fx.client.AuthClient;
import com.ybc.ybioq.fx.client.dto.UsuarioSession;
import com.ybc.ybioq.fx.navigation.FxNavigationService;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class LoginFxController {

    private final AuthClient authClient;
    private final FxNavigationService navigationService;

    @FXML
    private TextField usuarioField;

    @FXML
    private PasswordField claveField;

    @FXML
    private Label mensajeLabel;

    @FXML
    private Button ingresarButton;

    @FXML
    private ProgressIndicator progressIndicator;

    public LoginFxController(AuthClient authClient, FxNavigationService navigationService) {
        this.authClient = authClient;
        this.navigationService = navigationService;
    }

    @FXML
    private void initialize() {
        progressIndicator.setVisible(false);
        mensajeLabel.setText("");
    }

    @FXML
    private void ingresar() {
        String usuario = usuarioField.getText() == null ? "" : usuarioField.getText().trim();
        String clave = claveField.getText() == null ? "" : claveField.getText();
        if (usuario.isBlank() || clave.isBlank()) {
            mensajeLabel.setText("Ingrese usuario y clave.");
            return;
        }

        setLoading(true);
        Task<UsuarioSession> task = new Task<>() {
            @Override
            protected UsuarioSession call() {
                return authClient.login(usuario, clave);
            }
        };
        task.setOnSucceeded(event -> {
            setLoading(false);
            navigationService.showMain(task.getValue());
        });
        task.setOnFailed(event -> {
            setLoading(false);
            Throwable exception = task.getException();
            mensajeLabel.setText(exception == null ? "No se pudo iniciar sesion." : exception.getMessage());
        });
        Thread thread = new Thread(task, "ybioq-login");
        thread.setDaemon(true);
        thread.start();
    }

    private void setLoading(boolean loading) {
        ingresarButton.setDisable(loading);
        progressIndicator.setVisible(loading);
    }
}
