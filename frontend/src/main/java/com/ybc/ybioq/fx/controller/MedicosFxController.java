package com.ybc.ybioq.fx.controller;

import com.ybc.ybioq.fx.client.EspecialidadClient;
import com.ybc.ybioq.fx.client.MedicoClient;
import com.ybc.ybioq.fx.client.dto.EspecialidadDto;
import com.ybc.ybioq.fx.client.dto.MedicoDto;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MedicosFxController {

    private final MedicoClient medicoClient;
    private final EspecialidadClient especialidadClient;
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
    private TextArea observacionesField;
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
    private ComboBox<EspecialidadDto> especialidadCombo;
    @FXML
    private ListView<EspecialidadDto> especialidadesListView;

    @FXML
    private Label mensajeLabel;

    public MedicosFxController(MedicoClient medicoClient, EspecialidadClient especialidadClient) {
        this.medicoClient = medicoClient;
        this.especialidadClient = especialidadClient;
    }

    @FXML
    private void initialize() {
        matriculaColumn.setCellValueFactory(data -> new ReadOnlyStringWrapper(toText(data.getValue().matricula())));
        apellidoColumn.setCellValueFactory(data -> new ReadOnlyStringWrapper(nullToEmpty(data.getValue().apellido())));
        nombreColumn.setCellValueFactory(data -> new ReadOnlyStringWrapper(nullToEmpty(data.getValue().nombre())));
        mailColumn.setCellValueFactory(data -> new ReadOnlyStringWrapper(nullToEmpty(data.getValue().mail())));
        telefonoColumn.setCellValueFactory(data -> new ReadOnlyStringWrapper(toText(data.getValue().telefono())));
        estadoColumn.setCellValueFactory(data -> new ReadOnlyStringWrapper(isActivo(data.getValue()) ? "Activo" : "Inactivo"));

        medicosTable.setItems(medicos);
        medicosTable.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, selected) -> seleccionar(selected));
        filtroField.textProperty().addListener((obs, oldValue, newValue) -> cargar());

        Callback<ListView<EspecialidadDto>, ListCell<EspecialidadDto>> cellFactory = lv -> new ListCell<>() {
            @Override
            protected void updateItem(EspecialidadDto item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.nombre());
            }
        };
        especialidadCombo.setCellFactory(cellFactory);
        especialidadCombo.setButtonCell(cellFactory.call(null));
        especialidadesListView.setCellFactory(cellFactory);

        cargarComboEspecialidades();
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
        especialidadesListView.getItems().clear();
        especialidadCombo.setValue(null);
        mensajeLabel.setText("");
        apellidoField.requestFocus();
    }

    @FXML
    private void guardar() {
        String apellido = text(apellidoField);
        String nombre = text(nombreField);
        Integer matricula = parseInteger(text(matriculaField));
        Long telefono = text(telefonoField).isBlank() ? null : parseLong(text(telefonoField));
        if (matricula == null || (!text(telefonoField).isBlank() && telefono == null)) {
            return;
        }
        if (apellido.isBlank() || nombre.isBlank()) {
            mensajeLabel.setText("Ingrese apellido y nombre.");
            return;
        }

        MedicoDto seleccionado = medicosTable.getSelectionModel().getSelectedItem();
        Integer idActual = (seleccionado != null) ? seleccionado.id() : null;

        MedicoDto medico = new MedicoDto(
                idActual,
                apellido,
                nombre,
                matricula,
                text(mailField),
                telefono,
                observacionesField.getText(),
                estadoCheck.isSelected() ? 1 : 0,
                List.of()
        );

        try {
            MedicoDto guardado = medicoClient.save(medico);
            if (guardado != null && guardado.id() != null) {
                List<Integer> ids = especialidadesListView.getItems().stream()
                        .map(EspecialidadDto::id)
                        .toList();
                medicoClient.actualizarEspecialidades(guardado.id(), ids);
            }
            cargar();
            nuevo();
            mensajeLabel.setText("Medico guardado.");
        } catch (RuntimeException ex) {
            mensajeLabel.setText(ex.getMessage());
        }
    }

    @FXML
    private void cambiarEstado() {
        MedicoDto seleccionado = medicosTable.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mensajeLabel.setText("Seleccione un medico.");
            return;
        }

        int nuevoEstado = isActivo(seleccionado) ? 0 : 1;
        MedicoDto medicoModificado = new MedicoDto(
                seleccionado.id(),
                seleccionado.apellido(),
                seleccionado.nombre(),
                seleccionado.matricula(),
                seleccionado.mail(),
                seleccionado.telefono(),
                seleccionado.observaciones(),
                nuevoEstado,
                List.of()
        );

        try {
            medicoClient.save(medicoModificado);
            cargar();
            mensajeLabel.setText(nuevoEstado == 1 ? "Medico reactivado." : "Medico dado de baja.");
        } catch (RuntimeException ex) {
            mensajeLabel.setText(ex.getMessage());
        }
    }

    @FXML
    private void agregarEspecialidad() {
        EspecialidadDto sel = especialidadCombo.getValue();
        if (sel == null) {
            mensajeLabel.setText("Seleccione una especialidad del combo.");
            return;
        }
        boolean yaAsignada = especialidadesListView.getItems().stream()
                .anyMatch(e -> e.id().equals(sel.id()));
        if (!yaAsignada) {
            especialidadesListView.getItems().add(sel);
        }
        especialidadCombo.setValue(null);
        mensajeLabel.setText("");
    }

    @FXML
    private void quitarEspecialidad() {
        EspecialidadDto sel = especialidadesListView.getSelectionModel().getSelectedItem();
        if (sel != null) {
            especialidadesListView.getItems().remove(sel);
        }
    }

    private void cargarComboEspecialidades() {
        try {
            List<EspecialidadDto> activas = especialidadClient.findAll().stream()
                    .filter(EspecialidadDto::estado)
                    .sorted(Comparator.comparing(EspecialidadDto::nombre, String::compareToIgnoreCase))
                    .toList();
            especialidadCombo.getItems().setAll(activas);
        } catch (Exception e) {
            mensajeLabel.setText("Error al cargar especialidades.");
        }
    }

    @FXML
    private void cargar() {
        String filtro = text(filtroField).toLowerCase(Locale.ROOT);
        try {
            List<MedicoDto> datos = medicoClient.findAll().stream()
                    .filter(item -> coincideFiltro(item, filtro))
                    .sorted(Comparator.comparing(MedicoDto::apellido, Comparator.nullsLast(String::compareToIgnoreCase))
                            .thenComparing(MedicoDto::nombre, Comparator.nullsLast(String::compareToIgnoreCase)))
                    .toList();
            medicos.setAll(datos);
        } catch (RuntimeException ex) {
            mensajeLabel.setText(ex.getMessage());
        }
    }

    private void seleccionar(MedicoDto medico) {
        if (medico == null) return;
        apellidoField.setText(nullToEmpty(medico.apellido()));
        nombreField.setText(nullToEmpty(medico.nombre()));
        matriculaField.setText(toText(medico.matricula()));
        mailField.setText(nullToEmpty(medico.mail()));
        telefonoField.setText(toText(medico.telefono()));
        observacionesField.setText(nullToEmpty(medico.observaciones()));
        estadoCheck.setSelected(isActivo(medico));
        mensajeLabel.setText("");

        List<EspecialidadDto> especialidades = medico.especialidades() == null
                ? List.of()
                : medico.especialidades().stream()
                .map(e -> new EspecialidadDto(e.id(), e.nombre(), e.estado()))
                .sorted(Comparator.comparing(EspecialidadDto::nombre, Comparator.nullsLast(String::compareToIgnoreCase)))
                .toList();
        especialidadesListView.getItems().setAll(especialidades);
        especialidadCombo.setValue(null);
    }

    private boolean coincideFiltro(MedicoDto medico, String filtro) {
        if (filtro.isBlank()) return true;
        return nullToEmpty(medico.apellido()).toLowerCase(Locale.ROOT).contains(filtro)
                || nullToEmpty(medico.nombre()).toLowerCase(Locale.ROOT).contains(filtro)
                || toText(medico.matricula()).contains(filtro);
    }

    private boolean isActivo(MedicoDto medico) {
        return medico.estado() != null && medico.estado() == 1;
    }

    private Integer parseInteger(String value) {
        try {
            return Integer.valueOf(value);
        } catch (NumberFormatException e) {
            mensajeLabel.setText("El campo matricula debe ser numerico.");
            return null;
        }
    }

    private Long parseLong(String value) {
        try {
            return Long.valueOf(value);
        } catch (NumberFormatException e) {
            mensajeLabel.setText("El campo telefono debe ser numerico.");
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
