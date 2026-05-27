package com.ybc.ybioq.fx.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ElegirBioquimicoFxController {

    @FXML
    private TextField txtBioquimico;

    @FXML
    private Label lblApellido;

    @FXML
    private Label lblNombre;

    @FXML
    private Label lblMatricula;

    @FXML
    private Label lblCuil;

    @FXML
    private Label mensajeLabel;

    private boolean aceptado;

    @FXML
    private void initialize() {
        limpiarSeleccion();
        txtBioquimico.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null || newValue.isBlank()) {
                limpiarSeleccion();
            } else {
                mensajeLabel.setText("Busqueda lista para conectar con el backend.");
            }
        });
    }

    @FXML
    private void aceptar() {
        aceptado = true;
        cerrar();
    }

    @FXML
    private void cancelar() {
        aceptado = false;
        cerrar();
    }

    public boolean isAceptado() {
        return aceptado;
    }

    private void limpiarSeleccion() {
        lblApellido.setText("-");
        lblNombre.setText("-");
        lblMatricula.setText("-");
        lblCuil.setText("-");
        mensajeLabel.setText("Ingrese el bioquimico a buscar.");
    }

    private void cerrar() {
        Stage stage = (Stage) txtBioquimico.getScene().getWindow();
        stage.close();
    }
}
