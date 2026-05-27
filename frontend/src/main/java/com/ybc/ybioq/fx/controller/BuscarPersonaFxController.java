package com.ybc.ybioq.fx.controller;

import com.ybc.ybioq.fx.client.PersonaClient;
import com.ybc.ybioq.fx.client.dto.PersonaDto;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class BuscarPersonaFxController {

    private final PersonaClient personaClient;
    private final ObservableList<PersonaDto> personas = FXCollections.observableArrayList();
    private List<PersonaDto> cache = new ArrayList<>();

    @FXML
    private TextField filtroField;

    @FXML
    private TableView<PersonaDto> personasTable;

    @FXML
    private TableColumn<PersonaDto, String> idColumn;

    @FXML
    private TableColumn<PersonaDto, String> apellidoColumn;

    @FXML
    private TableColumn<PersonaDto, String> nombreColumn;

    @FXML
    private TableColumn<PersonaDto, String> dniColumn;

    @FXML
    private Label mensajeLabel;

    public BuscarPersonaFxController(PersonaClient personaClient) {
        this.personaClient = personaClient;
    }

    @FXML
    private void initialize() {
        idColumn.setCellValueFactory(data -> new ReadOnlyStringWrapper(toText(data.getValue().getId())));
        apellidoColumn.setCellValueFactory(data -> new ReadOnlyStringWrapper(nullToEmpty(data.getValue().getApellido())));
        nombreColumn.setCellValueFactory(data -> new ReadOnlyStringWrapper(nullToEmpty(data.getValue().getNombre())));
        dniColumn.setCellValueFactory(data -> new ReadOnlyStringWrapper(toText(data.getValue().getDni())));
        personasTable.setItems(personas);
        personasTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                aceptar();
            }
        });
        filtroField.textProperty().addListener((obs, oldValue, newValue) -> filtrar());
        cargar();
    }

    @FXML
    private void cargar() {
        try {
            cache = personaClient.findAll().stream()
                    .sorted(Comparator.comparing(PersonaDto::getApellido, Comparator.nullsLast(String::compareToIgnoreCase))
                            .thenComparing(PersonaDto::getNombre, Comparator.nullsLast(String::compareToIgnoreCase)))
                    .toList();
            filtrar();
            mensajeLabel.setText("");
        } catch (RuntimeException ex) {
            cache = new ArrayList<>();
            personas.clear();
            mensajeLabel.setText(ex.getMessage());
        }
    }

    @FXML
    private void aceptar() {
        PersonaDto seleccionada = personasTable.getSelectionModel().getSelectedItem();
        if (seleccionada == null) {
            mensajeLabel.setText("Seleccione una persona.");
            return;
        }
        cerrar();
    }

    @FXML
    private void cancelar() {
        cerrar();
    }

    private void filtrar() {
        String filtro = text(filtroField).toLowerCase(Locale.ROOT);
        personas.setAll(cache.stream()
                .filter(item -> coincideFiltro(item, filtro))
                .toList());
    }

    private boolean coincideFiltro(PersonaDto persona, String filtro) {
        if (filtro.isBlank()) {
            return true;
        }
        return nullToEmpty(persona.getApellido()).toLowerCase(Locale.ROOT).contains(filtro)
                || nullToEmpty(persona.getNombre()).toLowerCase(Locale.ROOT).contains(filtro)
                || toText(persona.getDni()).contains(filtro);
    }

    private void cerrar() {
        Stage stage = (Stage) personasTable.getScene().getWindow();
        stage.close();
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
