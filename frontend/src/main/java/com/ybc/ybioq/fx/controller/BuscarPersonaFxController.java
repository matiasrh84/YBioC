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

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class BuscarPersonaFxController {

    private static final int MIN_CHARS = 2;

    private final PersonaClient personaClient;
    private final ObservableList<PersonaDto> personas = FXCollections.observableArrayList();

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
        idColumn.setCellValueFactory(d -> new ReadOnlyStringWrapper(str(d.getValue().id())));
        apellidoColumn.setCellValueFactory(d -> new ReadOnlyStringWrapper(safe(d.getValue().apellido())));
        nombreColumn.setCellValueFactory(d -> new ReadOnlyStringWrapper(safe(d.getValue().nombre())));
        dniColumn.setCellValueFactory(d -> new ReadOnlyStringWrapper(str(d.getValue().dni())));
        personasTable.setItems(personas);
        personasTable.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) aceptar();
        });
        mensajeLabel.setText("Ingrese apellido, nombre o DNI y presione Buscar.");
    }

    @FXML
    private void buscar() {
        String q = filtroField.getText() == null ? "" : filtroField.getText().trim();
        if (q.length() < MIN_CHARS) {
            mensajeLabel.setText("Ingrese al menos " + MIN_CHARS + " caracteres.");
            personas.clear();
            return;
        }
        try {
            mensajeLabel.setText("Buscando...");
            var resultado = personaClient.buscar(q);
            personas.setAll(resultado);
            if (resultado.isEmpty()) {
                mensajeLabel.setText("Sin resultados para \"" + q + "\".");
            } else {
                mensajeLabel.setText(resultado.size() == 50
                        ? "Se muestran los primeros 50 resultados. Refine la búsqueda."
                        : resultado.size() + " resultado(s).");
            }
        } catch (RuntimeException ex) {
            personas.clear();
            mensajeLabel.setText("Error: " + ex.getMessage());
        }
    }

    @FXML
    private void aceptar() {
        PersonaDto sel = personasTable.getSelectionModel().getSelectedItem();
        if (sel == null) {
            mensajeLabel.setText("Seleccione una persona.");
            return;
        }
        cerrar();
    }

    @FXML
    private void cancelar() {
        cerrar();
    }

    private void cerrar() {
        Stage stage = (Stage) personasTable.getScene().getWindow();
        stage.close();
    }

    private String str(Object v) {
        return v == null ? "" : v.toString();
    }

    private String safe(String v) {
        return v == null ? "" : v;
    }
}
