package com.ybc.ybioq.fx.controller;

import com.ybc.ybioq.fx.client.DerivacionClient;
import com.ybc.ybioq.fx.client.dto.DerivacionDto;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class DerivacionesFxController {

    private final DerivacionClient derivacionClient;
    private final ObservableList<DerivacionDto> derivaciones = FXCollections.observableArrayList();

    @FXML
    private TextField filtroField;

    @FXML
    private TextField nombreField;

    @FXML
    private TextField direccionField;

    @FXML
    private TextField telefonoField;

    @FXML
    private TextField mailField;

    @FXML
    private TextArea observacionesArea;

    @FXML
    private TableView<DerivacionDto> derivacionesTable;

    @FXML
    private TableColumn<DerivacionDto, String> idColumn;

    @FXML
    private TableColumn<DerivacionDto, String> nombreColumn;

    @FXML
    private TableColumn<DerivacionDto, String> direccionColumn;

    @FXML
    private TableColumn<DerivacionDto, String> telefonoColumn;

    @FXML
    private TableColumn<DerivacionDto, String> mailColumn;

    @FXML
    private Label mensajeLabel;

    public DerivacionesFxController(DerivacionClient derivacionClient) {
        this.derivacionClient = derivacionClient;
    }

    @FXML
    private void initialize() {
        idColumn.setCellValueFactory(data -> new ReadOnlyStringWrapper(toText(data.getValue().getId())));
        nombreColumn.setCellValueFactory(data -> new ReadOnlyStringWrapper(nullToEmpty(data.getValue().getNombre())));
        direccionColumn.setCellValueFactory(data -> new ReadOnlyStringWrapper(nullToEmpty(data.getValue().getDireccion())));
        telefonoColumn.setCellValueFactory(data -> new ReadOnlyStringWrapper(toText(data.getValue().getTelefono())));
        mailColumn.setCellValueFactory(data -> new ReadOnlyStringWrapper(nullToEmpty(data.getValue().getMail())));
        derivacionesTable.setItems(derivaciones);
        derivacionesTable.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, selected) -> seleccionar(selected));
        filtroField.textProperty().addListener((obs, oldValue, newValue) -> cargar());
        nuevo();
        cargar();
    }

    @FXML
    private void nuevo() {
        derivacionesTable.getSelectionModel().clearSelection();
        nombreField.clear();
        direccionField.clear();
        telefonoField.clear();
        mailField.clear();
        observacionesArea.clear();
        mensajeLabel.setText("");
        nombreField.requestFocus();
    }

    @FXML
    private void guardar() {
        String nombre = text(nombreField);
        if (nombre.isBlank()) {
            mensajeLabel.setText("Ingrese el nombre de la derivacion.");
            return;
        }

        Long telefono = parseLong(text(telefonoField));
        if (!text(telefonoField).isBlank() && telefono == null) {
            return;
        }

        DerivacionDto derivacion = derivacionesTable.getSelectionModel().getSelectedItem();
        if (derivacion == null) {
            derivacion = new DerivacionDto();
        }

        derivacion.setNombre(nombre);
        derivacion.setDireccion(text(direccionField));
        derivacion.setTelefono(telefono);
        derivacion.setMail(text(mailField));
        derivacion.setObservaciones(textArea(observacionesArea));

        try {
            derivacionClient.save(derivacion);
            cargar();
            nuevo();
            mensajeLabel.setText("Derivacion guardada.");
        } catch (RuntimeException ex) {
            mensajeLabel.setText(ex.getMessage());
        }
    }

    @FXML
    private void salir() {
        Stage stage = (Stage) nombreField.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void cargar() {
        String filtro = text(filtroField).toLowerCase(Locale.ROOT);
        try {
            List<DerivacionDto> datos = derivacionClient.findAll().stream()
                    .filter(item -> coincideFiltro(item, filtro))
                    .sorted(Comparator.comparing(DerivacionDto::getNombre, Comparator.nullsLast(String::compareToIgnoreCase)))
                    .toList();
            derivaciones.setAll(datos);
        } catch (RuntimeException ex) {
            mensajeLabel.setText(ex.getMessage());
        }
    }

    private void seleccionar(DerivacionDto derivacion) {
        if (derivacion == null) {
            return;
        }
        nombreField.setText(nullToEmpty(derivacion.getNombre()));
        direccionField.setText(nullToEmpty(derivacion.getDireccion()));
        telefonoField.setText(toText(derivacion.getTelefono()));
        mailField.setText(nullToEmpty(derivacion.getMail()));
        observacionesArea.setText(nullToEmpty(derivacion.getObservaciones()));
        mensajeLabel.setText("");
    }

    private boolean coincideFiltro(DerivacionDto item, String filtro) {
        if (filtro.isBlank()) {
            return true;
        }
        return nullToEmpty(item.getNombre()).toLowerCase(Locale.ROOT).contains(filtro)
                || nullToEmpty(item.getDireccion()).toLowerCase(Locale.ROOT).contains(filtro)
                || nullToEmpty(item.getMail()).toLowerCase(Locale.ROOT).contains(filtro);
    }

    private Long parseLong(String value) {
        if (value.isBlank()) {
            return null;
        }
        try {
            return Long.valueOf(value);
        } catch (NumberFormatException ex) {
            mensajeLabel.setText("El telefono debe ser numerico.");
            return null;
        }
    }

    private String text(TextField field) {
        return field.getText() == null ? "" : field.getText().trim();
    }

    private String textArea(TextArea area) {
        return area.getText() == null ? "" : area.getText().trim();
    }

    private String toText(Object value) {
        return value == null ? "" : value.toString();
    }

    private String nullToEmpty(String value) {
        return value == null ? "" : value;
    }
}
