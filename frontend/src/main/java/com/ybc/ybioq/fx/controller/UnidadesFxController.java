package com.ybc.ybioq.fx.controller;

import com.ybc.ybioq.fx.client.UnidadClient;
import com.ybc.ybioq.fx.client.dto.UnidadDto;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UnidadesFxController {

    private final UnidadClient unidadClient;
    private final ObservableList<UnidadDto> unidades = FXCollections.observableArrayList();
    private UnidadDto unidadSeleccionada;

    @FXML
    private TextField filtroField;
    @FXML
    private TextField nombreField;
    @FXML
    private CheckBox estadoCheck;
    @FXML
    private TableView<UnidadDto> unidadesTable;
    @FXML
    private TableColumn<UnidadDto, String> idColumn;
    @FXML
    private TableColumn<UnidadDto, String> nombreColumn;
    @FXML
    private TableColumn<UnidadDto, String> estadoColumn;
    @FXML
    private Label mensajeLabel;

    public UnidadesFxController(UnidadClient unidadClient) {
        this.unidadClient = unidadClient;
    }

    @FXML
    public void initialize() {
        configurarTabla();
        cargar();
    }

    private void configurarTabla() {
        idColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(String.valueOf(cellData.getValue().getId())));
        nombreColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getNombre()));
        estadoColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().isEstado() ? "Activa" : "Baja"));

        unidadesTable.setItems(unidades);
        unidadesTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            unidadSeleccionada = newSelection;
            seleccionar(newSelection);
        });
    }

    @FXML
    private void nuevo() {
        unidadSeleccionada = null;
        nombreField.clear();
        estadoCheck.setSelected(true);
        unidadesTable.getSelectionModel().clearSelection();
        nombreField.requestFocus();
        mensajeLabel.setText("");
    }

    @FXML
    private void guardar() {
        String nombre = nombreField.getText();
        if (nombre == null || nombre.isBlank()) {
            mensajeLabel.setText("El nombre es obligatorio.");
            return;
        }

        UnidadDto dto = unidadSeleccionada != null ? unidadSeleccionada : new UnidadDto();
        dto.setNombre(nombre.trim());
        dto.setEstado(estadoCheck.isSelected());

        try {
            unidadClient.save(dto);
            cargar();
            nuevo();
            mensajeLabel.setText("Unidad guardada correctamente.");
        } catch (RuntimeException ex) {
            mensajeLabel.setText(ex.getMessage());
        }
    }

    @FXML
    private void cambiarEstado() {
        if (unidadSeleccionada == null) {
            mensajeLabel.setText("Seleccione una unidad.");
            return;
        }

        unidadSeleccionada.setEstado(!unidadSeleccionada.isEstado());
        try {
            unidadClient.save(unidadSeleccionada);
            cargar();
            mensajeLabel.setText(unidadSeleccionada.isEstado() ? "Unidad reactivada." : "Unidad dada de baja.");
        } catch (RuntimeException ex) {
            mensajeLabel.setText(ex.getMessage());
        }
    }

    @FXML
    private void cargar() {
        String filtro = filtroField.getText() == null ? "" : filtroField.getText().trim().toLowerCase(Locale.ROOT);
        try {
            List<UnidadDto> datos = unidadClient.findAll().stream()
                    .filter(item -> filtro.isBlank() || nullToEmpty(item.getNombre()).toLowerCase(Locale.ROOT).contains(filtro))
                    .sorted(Comparator.comparing(UnidadDto::getNombre, Comparator.nullsLast(String::compareToIgnoreCase)))
                    .toList();
            unidades.setAll(datos);
        } catch (RuntimeException ex) {
            mensajeLabel.setText(ex.getMessage());
        }
    }

    private void seleccionar(UnidadDto unidad) {
        if (unidad == null) return;
        nombreField.setText(nullToEmpty(unidad.getNombre()));
        estadoCheck.setSelected(unidad.isEstado());
        mensajeLabel.setText("");
    }

    private String nullToEmpty(String value) {
        return value == null ? "" : value;
    }
}
