package com.ybc.ybioq.fx.controller;

import com.ybc.ybioq.entity.local.Usuario;
import com.ybc.ybioq.fx.navigation.FxNavigationService;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MainFxController {

    private final FxNavigationService navigationService;

    @FXML
    private Parent root;

    @FXML
    private Label usuarioLabel;

    @FXML
    private Label permisosLabel;

    public MainFxController(FxNavigationService navigationService) {
        this.navigationService = navigationService;
    }

    @FXML
    private void initialize() {
        root.getProperties().put(MainFxController.class.getName(), this);
    }

    public void setUsuario(Usuario usuario) {
        String nombre = "%s %s".formatted(nullToEmpty(usuario.getNombre()), nullToEmpty(usuario.getApellido())).trim();
        usuarioLabel.setText(nombre.isBlank() ? usuario.getUsuario() : nombre);
        permisosLabel.setText("Datos: %s  |  Informes: %s  |  Facturacion: %s"
                .formatted(siNo(usuario.getDatos()), siNo(usuario.getInformes()), siNo(usuario.getFacturacion())));
    }

    @FXML
    private void cerrarSesion() {
        navigationService.showLogin();
    }

    @FXML
    private void salir() {
        Platform.exit();
    }

    private String nullToEmpty(String value) {
        return value == null ? "" : value;
    }

    private String siNo(Integer value) {
        return value != null && value == 1 ? "Si" : "No";
    }
}
