package com.ybc.ybioq.fx.controller;

import com.ybc.ybioq.controller.EspecialidadController;
import com.ybc.ybioq.entity.local.Especialidad;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class EspecialidadesFxController {

    private final EspecialidadController especialidadController;
    private final ObservableList<Especialidad> especialidades = FXCollections.observableArrayList();

    @FXML
    private TextField filtroField;

    @FXML
    private TextField nombreField;

    @FXML
    private CheckBox estadoCheck;

    @FXML
    private TableView<Especialidad> especialidadesTable;

    @FXML
    private TableColumn<Especialidad, String> idColumn;

    @FXML
    private TableColumn<Especialidad, String> nombreColumn;

    @FXML
    private TableColumn<Especialidad, String> estadoColumn;

    @FXML
    private Label mensajeLabel;

    public EspecialidadesFxController(EspecialidadController especialidadController) {
        this.especialidadController = especialidadController;
    }

    @FXML
    private void initialize() {
        idColumn.setCellValueFactory(data -> new ReadOnlyStringWrapper(String.valueOf(data.getValue().getId())));
        nombreColumn.setCellValueFactory(data -> new ReadOnlyStringWrapper(nullToEmpty(data.getValue().getNombre())));
        estadoColumn.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().isEstado() ? "Activo" : "Inactivo"));
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

        Especialidad especialidad = especialidadesTable.getSelectionModel().getSelectedItem();
        if (especialidad == null) {
            especialidad = new Especialidad();
        }

        especialidad.setNombre(nombre);
        especialidad.setEstado(estadoCheck.isSelected());
        especialidadController.save(especialidad);
        cargar();
        nuevo();
        mensajeLabel.setText("Especialidad guardada.");
    }

    @FXML
    private void cambiarEstado() {
        Especialidad especialidad = especialidadesTable.getSelectionModel().getSelectedItem();
        if (especialidad == null) {
            mensajeLabel.setText("Seleccione una especialidad.");
            return;
        }

        especialidad.setEstado(!especialidad.isEstado());
        especialidadController.save(especialidad);
        cargar();
        mensajeLabel.setText(especialidad.isEstado() ? "Especialidad reactivada." : "Especialidad dada de baja.");
    }

    @FXML
    private void cargar() {
        String filtro = filtroField.getText() == null ? "" : filtroField.getText().trim().toLowerCase(Locale.ROOT);
        List<Especialidad> datos = especialidadController.findAll().stream()
                .filter(item -> filtro.isBlank() || nullToEmpty(item.getNombre()).toLowerCase(Locale.ROOT).contains(filtro))
                .sorted(Comparator.comparing(Especialidad::getNombre, Comparator.nullsLast(String::compareToIgnoreCase)))
                .toList();
        especialidades.setAll(datos);
    }

    private void seleccionar(Especialidad especialidad) {
        if (especialidad == null) {
            return;
        }
        nombreField.setText(nullToEmpty(especialidad.getNombre()));
        estadoCheck.setSelected(especialidad.isEstado());
        mensajeLabel.setText("");
    }

    private String nullToEmpty(String value) {
        return value == null ? "" : value;
    }
}
