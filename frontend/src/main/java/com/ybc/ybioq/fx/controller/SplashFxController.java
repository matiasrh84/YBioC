package com.ybc.ybioq.fx.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;

public class SplashFxController {

    @FXML
    private ProgressBar progressBar;
    @FXML
    private Label estadoLabel;
    @FXML
    private HBox panelError;
    @FXML
    private Button btnReintentar;

    private Runnable reintentarCallback;

    public void setProgress(double progress, String estado) {
        progressBar.setProgress(progress);
        estadoLabel.setText(estado);
        estadoLabel.getStyleClass().remove("error-label");
    }

    public double getProgress() {
        return progressBar.getProgress();
    }

    public void setError(String mensaje) {
        progressBar.setProgress(0);
        estadoLabel.setText(mensaje);
        if (!estadoLabel.getStyleClass().contains("error-label")) {
            estadoLabel.getStyleClass().add("error-label");
        }
        if (panelError != null) {
            panelError.setVisible(true);
            panelError.setManaged(true);
        }
    }

    public void setReintentarCallback(Runnable callback) {
        this.reintentarCallback = callback;
    }

    @FXML
    private void reintentar() {
        estadoLabel.getStyleClass().remove("error-label");
        if (panelError != null) {
            panelError.setVisible(false);
            panelError.setManaged(false);
        }
        progressBar.setProgress(0);
        estadoLabel.setText("Reintentando...");
        if (reintentarCallback != null) reintentarCallback.run();
    }
}
