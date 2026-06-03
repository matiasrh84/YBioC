package com.ybc.ybioq.fx.controller;

import com.ybc.ybioq.fx.client.AnticipoClient;
import com.ybc.ybioq.fx.client.dto.AnticipoDto;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AnticipoFxController {

    private final AnticipoClient anticipoClient;

    @FXML
    private TextField montoField;

    @FXML
    private TextField ordenField;

    @FXML
    private TextArea observacionArea;

    @FXML
    private Label mensajeLabel;

    public AnticipoFxController(AnticipoClient anticipoClient) {
        this.anticipoClient = anticipoClient;
    }

    @FXML
    private void initialize() {
        mensajeLabel.setText("");
    }

    @FXML
    private void cobrar() {
        BigDecimal monto = parseMonto(text(montoField));
        if (monto == null || monto.compareTo(BigDecimal.ZERO) <= 0) {
            mensajeLabel.setText("Ingrese un anticipo valido.");
            return;
        }

        Integer idOrden = parseInteger(text(ordenField));
        if (!text(ordenField).isBlank() && idOrden == null) {
            mensajeLabel.setText("El numero de orden debe ser numerico.");
            return;
        }

        AnticipoDto dto = new AnticipoDto(
                null,
                monto,
                idOrden,
                1,
                LocalDate.now().toString(),
                textArea(observacionArea)
        );

        try {
            anticipoClient.save(dto);
            mensajeLabel.setText("Anticipo registrado.");
            montoField.clear();
            observacionArea.clear();
            cerrar();
        } catch (RuntimeException ex) {
            mensajeLabel.setText(ex.getMessage());
        }
    }

    @FXML
    private void salir() {
        cerrar();
    }

    private void cerrar() {
        Stage stage = (Stage) montoField.getScene().getWindow();
        stage.close();
    }

    private BigDecimal parseMonto(String value) {
        if (value.isBlank()) {
            return null;
        }
        try {
            return new BigDecimal(value.replace(",", "."));
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    private Integer parseInteger(String value) {
        try {
            return Integer.valueOf(value);
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    private String text(TextField field) {
        return field.getText() == null ? "" : field.getText().trim();
    }

    private String textArea(TextArea area) {
        return area.getText() == null ? "" : area.getText().trim();
    }
}
