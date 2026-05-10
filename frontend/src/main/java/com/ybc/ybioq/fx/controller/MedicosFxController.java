package com.ybc.ybioq.fx.controller;

import com.ybc.ybioq.fx.client.BackendApiClient;
import com.ybc.ybioq.fx.client.dto.MedicoDto;
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

    private final BackendApiClient backendApiClient;
    private final ObservableList<MedicoDto> medicos = FXCollections.observableArrayList();

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
    private TableView<MedicoDto> medicosTable;

    @FXML
    private TableColumn<MedicoDto, String> matriculaColumn;

    @FXML
    private TableColumn<MedicoDto, String> apellidoColumn;

    @FXML
    private TableColumn<MedicoDto, String> nombreColumn;

    @FXML
    private TableColumn<MedicoDto, String> mailColumn;

    @FXML
    private TableColumn<MedicoDto, String> telefonoColumn;

    @FXML
    private TableColumn<MedicoDto, String> estadoColumn;

    @FXML
    private Label mensajeLabel;

    public MedicosFxController(BackendApiClient backendApiClient) {
        this.backendApiClient = backendApiClient;
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

        MedicoDto medico = medicosTable.getSelectionModel().getSelectedItem();
        if (medico == null) {
            medico = new MedicoDto();
        }

        medico.setApellido(apellido);
        medico.setNombre(nombre);
        medico.setMatricula(matricula);
        medico.setMail(text(mailField));
        medico.setTelefono(telefono);
        medico.setObservaciones(text(observacionesField));
        medico.setEstado(estadoCheck.isSelected() ? 1 : 0);

        try {
            backendApiClient.saveMedico(medico);
            cargar();
            nuevo();
            mensajeLabel.setText("Medico guardado.");
        } catch (RuntimeException ex) {
            mensajeLabel.setText(ex.getMessage());
        }
    }

    @FXML
    private void cambiarEstado() {
        MedicoDto medico = medicosTable.getSelectionModel().getSelectedItem();
        if (medico == null) {
            mensajeLabel.setText("Seleccione un medico.");
            return;
        }

        medico.setEstado(isActivo(medico) ? 0 : 1);
        try {
            backendApiClient.saveMedico(medico);
            cargar();
            mensajeLabel.setText(isActivo(medico) ? "Medico reactivado." : "Medico dado de baja.");
        } catch (RuntimeException ex) {
            mensajeLabel.setText(ex.getMessage());
        }
    }

    @FXML
    private void cargar() {
        String filtro = text(filtroField).toLowerCase(Locale.ROOT);
        try {
            List<MedicoDto> datos = backendApiClient.findMedicos().stream()
                    .filter(item -> coincideFiltro(item, filtro))
                    .sorted(Comparator.comparing(MedicoDto::getApellido, Comparator.nullsLast(String::compareToIgnoreCase))
                            .thenComparing(MedicoDto::getNombre, Comparator.nullsLast(String::compareToIgnoreCase)))
                    .toList();
            medicos.setAll(datos);
        } catch (RuntimeException ex) {
            mensajeLabel.setText(ex.getMessage());
        }
    }

    private void seleccionar(MedicoDto medico) {
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

    private boolean coincideFiltro(MedicoDto medico, String filtro) {
        if (filtro.isBlank()) {
            return true;
        }
        return nullToEmpty(medico.getApellido()).toLowerCase(Locale.ROOT).contains(filtro)
                || nullToEmpty(medico.getNombre()).toLowerCase(Locale.ROOT).contains(filtro)
                || toText(medico.getMatricula()).contains(filtro);
    }

    private boolean isActivo(MedicoDto medico) {
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
