package com.ybc.ybioq.fx.controller;

import com.ybc.ybioq.fx.client.SeccionClient;
import com.ybc.ybioq.fx.client.dto.SeccionDto;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SeccionesFxController {

    private final SeccionClient seccionClient;
    private final ObservableList<SeccionDto> secciones = FXCollections.observableArrayList();
    private SeccionDto seleccionada = null;

    @FXML private TextField filtroField;
    @FXML private TextField txtNombre;
    @FXML private TextField txtPrioridad;
    @FXML private TableView<SeccionDto> tablaSeccion;
    @FXML private TableColumn<SeccionDto, String> colId;
    @FXML private TableColumn<SeccionDto, String> colNombre;
    @FXML private TableColumn<SeccionDto, String> colPrioridad;
    @FXML private TableColumn<SeccionDto, String> colEstado;
    @FXML private Label mensajeLabel;

    public SeccionesFxController(SeccionClient seccionClient) {
        this.seccionClient = seccionClient;
    }

    @FXML
    private void initialize() {
        colId.setCellValueFactory(d -> new ReadOnlyStringWrapper(String.valueOf(d.getValue().id())));
        colNombre.setCellValueFactory(d -> new ReadOnlyStringWrapper(d.getValue().nombre()));
        colPrioridad.setCellValueFactory(d -> new ReadOnlyStringWrapper(
                d.getValue().prioridad() != null ? String.valueOf(d.getValue().prioridad()) : "0"));
        colEstado.setCellValueFactory(d -> new ReadOnlyStringWrapper(d.getValue().estado() ? "Activa" : "Inactiva"));
        tablaSeccion.setItems(secciones);
        tablaSeccion.getSelectionModel().selectedItemProperty().addListener((obs, o, sel) -> seleccionar(sel));
        filtroField.textProperty().addListener((obs, o, n) -> cargar());

        txtNombre.textProperty().addListener((obs, o, n) -> {
            if (n != null && !n.equals(n.toUpperCase(Locale.ROOT))) {
                txtNombre.setText(n.toUpperCase(Locale.ROOT));
            }
        });

        nuevo();
        cargar();
    }

    @FXML
    private void nuevo() {
        seleccionada = null;
        tablaSeccion.getSelectionModel().clearSelection();
        txtNombre.clear();
        txtPrioridad.clear();
        mensajeLabel.setText("");
        txtNombre.requestFocus();
    }

    @FXML
    private void guardar() {
        String nombre = txtNombre.getText() == null ? "" : txtNombre.getText().trim().toUpperCase(Locale.ROOT);
        if (nombre.isBlank()) {
            mensajeLabel.setText("Ingrese un nombre.");
            txtNombre.requestFocus();
            return;
        }
        Integer prioridad = parsePrioridad(txtPrioridad.getText());

        try {
            SeccionDto dto = new SeccionDto(
                    seleccionada != null ? seleccionada.id() : null,
                    nombre, prioridad,
                    seleccionada == null || seleccionada.estado());

            if (seleccionada == null) {
                seccionClient.save(dto);
                mensajeLabel.setText("Sección creada.");
            } else {
                seccionClient.update(seleccionada.id(), dto);
                mensajeLabel.setText("Sección actualizada.");
            }
            nuevo();
            cargar();
        } catch (RuntimeException ex) {
            mensajeLabel.setText(ex.getMessage());
        }
    }

    @FXML
    private void borrar() {
        if (seleccionada == null) {
            mensajeLabel.setText("Seleccione una sección.");
            return;
        }
        try {
            seccionClient.delete(seleccionada.id());
            mensajeLabel.setText("Sección desactivada.");
            nuevo();
            cargar();
        } catch (RuntimeException ex) {
            mensajeLabel.setText(ex.getMessage());
        }
    }

    @FXML
    private void cargar() {
        String filtro = filtroField.getText() == null ? "" : filtroField.getText().trim().toLowerCase(Locale.ROOT);
        try {
            secciones.setAll(seccionClient.findAll().stream()
                    .filter(s -> filtro.isBlank() || s.nombre().toLowerCase(Locale.ROOT).contains(filtro))
                    .toList());
        } catch (RuntimeException ex) {
            mensajeLabel.setText("Error al cargar: " + ex.getMessage());
        }
    }

    private void seleccionar(SeccionDto dto) {
        seleccionada = dto;
        if (dto == null) return;
        txtNombre.setText(dto.nombre());
        txtPrioridad.setText(dto.prioridad() != null ? String.valueOf(dto.prioridad()) : "0");
        mensajeLabel.setText("");
    }

    private Integer parsePrioridad(String value) {
        try {
            return (value == null || value.isBlank()) ? 0 : Integer.parseInt(value.trim());
        } catch (NumberFormatException ex) {
            return 0;
        }
    }
}
