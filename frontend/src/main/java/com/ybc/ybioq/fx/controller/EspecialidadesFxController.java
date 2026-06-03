package com.ybc.ybioq.fx.controller;

import com.ybc.ybioq.fx.client.EspecialidadClient;
import com.ybc.ybioq.fx.client.dto.EspecialidadDto;
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
public class EspecialidadesFxController {

    private final EspecialidadClient especialidadClient;
    private final ObservableList<EspecialidadDto> especialidades = FXCollections.observableArrayList();

    @FXML
    private TextField filtroField;

    @FXML
    private TextField nombreField;

    @FXML
    private CheckBox estadoCheck;

    @FXML
    private TableView<EspecialidadDto> especialidadesTable;

    @FXML
    private TableColumn<EspecialidadDto, String> idColumn;

    @FXML
    private TableColumn<EspecialidadDto, String> nombreColumn;

    @FXML
    private TableColumn<EspecialidadDto, String> estadoColumn;

    @FXML
    private Label mensajeLabel;

    public EspecialidadesFxController(EspecialidadClient especialidadClient) {
        this.especialidadClient = especialidadClient;
    }

    @FXML
    private void initialize() {
        idColumn.setCellValueFactory(data -> new ReadOnlyStringWrapper(String.valueOf(data.getValue().id())));
        nombreColumn.setCellValueFactory(data -> new ReadOnlyStringWrapper(nullToEmpty(data.getValue().nombre())));
        estadoColumn.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().estado() ? "Activo" : "Inactivo"));

        especialidadesTable.setItems(especialidades);
        especialidadesTable.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, selected) -> seleccionar(selected));
        filtroField.textProperty().addListener((obs, oldValue, newValue) -> cargar());
        nuevo();
        cargar();
    }

    @FXML
    private void nuevo() {
        especialidadesTable.getSelectionModel().clearSelection();
        nombreField.clear();
        estadoCheck.setSelected(true);
        mensajeLabel.setText("");
        nombreField.requestFocus();
    }

    @FXML
    private void guardar() {
        String nombre = nombreField.getText() == null ? "" : nombreField.getText().trim();
        if (nombre.isBlank()) {
            mensajeLabel.setText("Ingrese el nombre de la especialidad.");
            return;
        }

        EspecialidadDto seleccionado = especialidadesTable.getSelectionModel().getSelectedItem();

        // CORREGIDO: Como es inmutable, calculamos el ID y creamos una nueva instancia pura
        Integer idActual = (seleccionado != null) ? seleccionado.id() : null;
        EspecialidadDto especialidad = new EspecialidadDto(idActual, nombre, estadoCheck.isSelected());

        try {
            especialidadClient.save(especialidad);
            cargar();
            nuevo();
            mensajeLabel.setText("Especialidad guardada.");
        } catch (RuntimeException ex) {
            mensajeLabel.setText(ex.getMessage());
        }
    }

    @FXML
    private void cambiarEstado() {
        EspecialidadDto seleccionado = especialidadesTable.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mensajeLabel.setText("Seleccione una especialidad.");
            return;
        }

        // CORREGIDO: Se invierte el estado creando una nueva instancia basada en la seleccionada
        boolean nuevoEstado = !seleccionado.estado();
        EspecialidadDto especialidadModificada = new EspecialidadDto(seleccionado.id(), seleccionado.nombre(), nuevoEstado);

        try {
            especialidadClient.save(especialidadModificada);
            cargar();
            mensajeLabel.setText(nuevoEstado ? "Especialidad reactivada." : "Especialidad dada de baja.");
        } catch (RuntimeException ex) {
            mensajeLabel.setText(ex.getMessage());
        }
    }

    @FXML
    private void cargar() {
        String filtro = filtroField.getText() == null ? "" : filtroField.getText().trim().toLowerCase(Locale.ROOT);
        try {
            // CORREGIDO: Cambiado item.getNombre() por item.nombre() e igualmente en el Comparator
            List<EspecialidadDto> datos = especialidadClient.findAll().stream()
                    .filter(item -> filtro.isBlank() || nullToEmpty(item.nombre()).toLowerCase(Locale.ROOT).contains(filtro))
                    .sorted(Comparator.comparing(EspecialidadDto::nombre, Comparator.nullsLast(String::compareToIgnoreCase)))
                    .toList();
            especialidades.setAll(datos);
        } catch (RuntimeException ex) {
            mensajeLabel.setText(ex.getMessage());
        }
    }

    private void seleccionar(EspecialidadDto especialidad) {
        if (especialidad == null) {
            return;
        }
        // CORREGIDO: Cambiado a métodos del record
        nombreField.setText(nullToEmpty(especialidad.nombre()));
        estadoCheck.setSelected(especialidad.estado());
        mensajeLabel.setText("");
    }

    private String nullToEmpty(String value) {
        return value == null ? "" : value;
    }
}
