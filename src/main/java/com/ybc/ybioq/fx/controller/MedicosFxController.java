package com.ybc.ybioq.fx.controller;

import com.ybc.ybioq.controller.MedicoController;
import com.ybc.ybioq.entity.local.Medico;
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
public class MedicosFxController {

    private final MedicoController medicoController;
    private final ObservableList<Medico> medicos = FXCollections.observableArrayList();

    @FXML
    private TextField filtroField;

    @FXML
    private TextField apellidoField;

    @FXML
    private TextField nombreField;

    @FXML
    private TextField matriculaField;

    @FXML
    private TextField mailField;

    @FXML
    private TextField telefonoField;

    @FXML
    private TextField observacionesField;

    @FXML
    private CheckBox estadoCheck;

    @FXML
    private TableView<Medico> medicosTable;

    @FXML
    private TableColumn<Medico, String> matriculaColumn;

    @FXML
    private TableColumn<Medico, String> apellidoColumn;

    @FXML
    private TableColumn<Medico, String> nombreColumn;

    @FXML
    private TableColumn<Medico, String> mailColumn;

    @FXML
    private TableColumn<Medico, String> telefonoColumn;

    @FXML
    private TableColumn<Medico, String> estadoColumn;

    @FXML
    private Label mensajeLabel;

    public MedicosFxController(MedicoController medicoController) {
        this.medicoController = medicoController;
    }

    @FXML
    private void initialize() {
        matriculaColumn.setCellValueFactory(data -> new ReadOnlyStringWrapper(toText(data.getValue().getMatricula())));
        apellidoColumn.setCellValueFactory(data -> new ReadOnlyStringWrapper(nullToEmpty(data.getValue().getApellido())));
        nombreColumn.setCellValueFactory(data -> new ReadOnlyStringWrapper(nullToEmpty(data.getValue().getNombre())));
        mailColumn.setCellValueFactory(data -> new ReadOnlyStringWrapper(nullToEmpty(data.getValue().getMail())));
        telefonoColumn.setCellValueFactory(data -> new ReadOnlyStringWrapper(toText(data.getValue().getTelefono())));
        estadoColumn.setCellValueFactory(data -> new ReadOnlyStringWrapper(isActivo(data.getValue()) ? "Activo" : "Inactivo"));
        medicosTable.setItems(medicos);
        medicosTable.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, selected) -> seleccionar(selected));
        filtroField.textProperty().addListener((obs, oldValue, newValue) -> cargar());
        nuevo();
        cargar();
    }

    @FXML
    private void nuevo() {
        medicosTable.getSelectionModel().clearSelection();
        apellidoField.clear();
        nombreField.clear();
        matriculaField.clear();
        mailField.clear();
        telefonoField.clear();
        observacionesField.clear();
        estadoCheck.setSelected(true);
        mensajeLabel.setText("");
        apellidoField.requestFocus();
    }

    @FXML
    private void guardar() {
        String apellido = text(apellidoField);
        String nombre = text(nombreField);
        Integer matricula = parseInteger(text(matriculaField), "matricula");
        Long telefono = text(telefonoField).isBlank() ? null : parseLong(text(telefonoField), "telefono");
        if (matricula == null || telefonoField.getText() != null && !telefonoField.getText().isBlank() && telefono == null) {
            return;
        }
        if (apellido.isBlank() || nombre.isBlank()) {
            mensajeLabel.setText("Ingrese apellido y nombre.");
            return;
        }

        Medico medico = medicosTable.getSelectionModel().getSelectedItem();
        if (medico == null) {
            medico = new Medico();
        }

        medico.setApellido(apellido);
        medico.setNombre(nombre);
        medico.setMatricula(matricula);
        medico.setMail(text(mailField));
        medico.setTelefono(telefono);
        medico.setObservaciones(text(observacionesField));
        medico.setEstado(estadoCheck.isSelected() ? 1 : 0);
        medicoController.save(medico);
        cargar();
        nuevo();
        mensajeLabel.setText("Medico guardado.");
    }

    @FXML
    private void cambiarEstado() {
        Medico medico = medicosTable.getSelectionModel().getSelectedItem();
        if (medico == null) {
            mensajeLabel.setText("Seleccione un medico.");
            return;
        }

        medico.setEstado(isActivo(medico) ? 0 : 1);
        medicoController.save(medico);
        cargar();
        mensajeLabel.setText(isActivo(medico) ? "Medico reactivado." : "Medico dado de baja.");
    }

    @FXML
    private void cargar() {
        String filtro = text(filtroField).toLowerCase(Locale.ROOT);
        List<Medico> datos = medicoController.findAll().stream()
                .filter(item -> coincideFiltro(item, filtro))
                .sorted(Comparator.comparing(Medico::getApellido, Comparator.nullsLast(String::compareToIgnoreCase))
                        .thenComparing(Medico::getNombre, Comparator.nullsLast(String::compareToIgnoreCase)))
                .toList();
        medicos.setAll(datos);
    }

    private void seleccionar(Medico medico) {
        if (medico == null) {
            return;
        }
        apellidoField.setText(nullToEmpty(medico.getApellido()));
        nombreField.setText(nullToEmpty(medico.getNombre()));
        matriculaField.setText(toText(medico.getMatricula()));
        mailField.setText(nullToEmpty(medico.getMail()));
        telefonoField.setText(toText(medico.getTelefono()));
        observacionesField.setText(nullToEmpty(medico.getObservaciones()));
        estadoCheck.setSelected(isActivo(medico));
        mensajeLabel.setText("");
    }

    private boolean coincideFiltro(Medico medico, String filtro) {
        if (filtro.isBlank()) {
            return true;
        }
        return nullToEmpty(medico.getApellido()).toLowerCase(Locale.ROOT).contains(filtro)
                || nullToEmpty(medico.getNombre()).toLowerCase(Locale.ROOT).contains(filtro)
                || toText(medico.getMatricula()).contains(filtro);
    }

    private boolean isActivo(Medico medico) {
        return medico.getEstado() != null && medico.getEstado() == 1;
    }

    private Integer parseInteger(String value, String fieldName) {
        try {
            return Integer.valueOf(value);
        } catch (NumberFormatException e) {
            mensajeLabel.setText("El campo " + fieldName + " debe ser numerico.");
            return null;
        }
    }

    private Long parseLong(String value, String fieldName) {
        try {
            return Long.valueOf(value);
        } catch (NumberFormatException e) {
            mensajeLabel.setText("El campo " + fieldName + " debe ser numerico.");
            return null;
        }
    }

    private String text(TextField field) {
        return field.getText() == null ? "" : field.getText().trim();
    }

    private String toText(Object value) {
        return value == null ? "" : value.toString();
    }

    private String nullToEmpty(String value) {
        return value == null ? "" : value;
    }
}
