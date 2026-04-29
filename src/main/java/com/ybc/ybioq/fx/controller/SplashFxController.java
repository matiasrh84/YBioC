package com.ybc.ybioq.fx.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class SplashFxController {

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Label estadoLabel;

    public double getProgress() {
        return progressBar.getProgress();
    }

    public void setProgress(double progress, String estado) {
        progressBar.setProgress(progress);
        estadoLabel.setText(estado);
    }

    public void setError(String message) {
        progressBar.setProgress(0);
        estadoLabel.setText(message);
        estadoLabel.getStyleClass().add("error-label");
    }
}
