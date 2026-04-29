package com.ybc.ybioq.fx.controller;

import com.ybc.ybioq.entity.local.Usuario;
import com.ybc.ybioq.fx.fxml.SpringFXMLLoader;
import com.ybc.ybioq.fx.navigation.FxNavigationService;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MainFxController {

    private final FxNavigationService navigationService;
    private final SpringFXMLLoader fxmlLoader;

    @FXML
    private Parent root;

    @FXML
    private StackPane contentStack;

    @FXML
    private VBox homeContent;

    @FXML
    private Label usuarioLabel;

    @FXML
    private Label permisosLabel;

    public MainFxController(FxNavigationService navigationService, SpringFXMLLoader fxmlLoader) {
        this.navigationService = navigationService;
        this.fxmlLoader = fxmlLoader;
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
    private void showHome() {
        contentStack.getChildren().setAll(homeContent);
    }

    @FXML
    private void showEspecialidades() {
        setContent(fxmlLoader.load("/fx/especialidades-view.fxml"));
    }

    @FXML
    private void showMedicos() {
        setContent(fxmlLoader.load("/fx/medicos-view.fxml"));
    }

    @FXML
    private void salir() {
        Platform.exit();
    }

    private void setContent(Node content) {
        contentStack.getChildren().setAll(content);
    }

    private String nullToEmpty(String value) {
        return value == null ? "" : value;
    }

    private String siNo(Integer value) {
        return value != null && value == 1 ? "Si" : "No";
    }
}
