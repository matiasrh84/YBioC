package com.ybc.ybioq.fx.controller;

import com.ybc.ybioq.fx.client.MetodoClient;
import com.ybc.ybioq.fx.client.dto.MetodoDto;
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

import java.util.Comparator;
import java.util.List;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MetodosFxController {

    private final MetodoClient metodoClient;
    private final ObservableList<MetodoDto> metodos = FXCollections.observableArrayList();

    @FXML
    private TableView<MetodoDto> tablaMetodos;

    @FXML
    private TableColumn<MetodoDto, String> idColumn;

    @FXML
    private TableColumn<MetodoDto, String> nombreColumn;

    @FXML
    private TextField txtMetodo;

    @FXML
    private Label mensajeLabel;

    public MetodosFxController(MetodoClient metodoClient) {
        this.metodoClient = metodoClient;
    }

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(data -> new ReadOnlyStringWrapper(toText(data.getValue().getId())));
        nombreColumn.setCellValueFactory(data -> new ReadOnlyStringWrapper(toText(data.getValue().getNombre())));
        tablaMetodos.setItems(metodos);
        tablaMetodos.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, selected) -> {
            if (selected == null) {
                return;
            }
            txtMetodo.setText(toText(selected.getNombre()));
            mensajeLabel.setText("");
        });
        cargar();
    }

    @FXML
    private void agregar() {
        String nombre = text(txtMetodo);
        if (nombre.isBlank()) {
            mensajeLabel.setText("Ingrese el nombre del metodo.");
            return;
        }

        MetodoDto dto = new MetodoDto();
        dto.setNombre(nombre);

        try {
            metodoClient.save(dto);
            cargar();
            txtMetodo.clear();
            txtMetodo.requestFocus();
            mensajeLabel.setText("Metodo agregado.");
        } catch (RuntimeException ex) {
            mensajeLabel.setText(ex.getMessage());
        }
    }

    @FXML
    private void salir() {
        Stage stage = (Stage) txtMetodo.getScene().getWindow();
        stage.close();
    }

    private void cargar() {
        try {
            List<MetodoDto> datos = metodoClient.findAll().stream()
                    .sorted(Comparator.comparing(m -> toText(m.getNombre()), String.CASE_INSENSITIVE_ORDER))
                    .toList();
            metodos.setAll(datos);
        } catch (RuntimeException ex) {
            mensajeLabel.setText(ex.getMessage());
        }
    }

    private String text(TextField field) {
        return field.getText() == null ? "" : field.getText().trim();
    }

    private String toText(Object value) {
        return value == null ? "" : value.toString();
    }
}
