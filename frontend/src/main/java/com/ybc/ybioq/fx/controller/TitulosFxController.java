package com.ybc.ybioq.fx.controller;

import com.ybc.ybioq.fx.client.TituloClient;
import com.ybc.ybioq.fx.client.dto.TituloDto;
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
public class TitulosFxController {

    private final TituloClient tituloClient;
    private final ObservableList<TituloDto> titulos = FXCollections.observableArrayList();
    private TituloDto seleccionado;

    @FXML
    private TableView<TituloDto> tablaTitulos;

    @FXML
    private TableColumn<TituloDto, String> idColumn;

    @FXML
    private TableColumn<TituloDto, String> nombreColumn;

    @FXML
    private TableColumn<TituloDto, String> estadoColumn;

    @FXML
    private TableColumn<TituloDto, String> prioridadColumn;

    @FXML
    private TextField txtTitulo;

    @FXML
    private Label mensajeLabel;

    public TitulosFxController(TituloClient tituloClient) {
        this.tituloClient = tituloClient;
    }

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(data -> new ReadOnlyStringWrapper(toText(data.getValue().getId())));
        nombreColumn.setCellValueFactory(data -> new ReadOnlyStringWrapper(toText(data.getValue().getNombre())));
        estadoColumn.setCellValueFactory(data -> new ReadOnlyStringWrapper(toEstado(data.getValue().getEstado())));
        prioridadColumn.setCellValueFactory(data -> new ReadOnlyStringWrapper(toText(data.getValue().getPrioridad())));
        tablaTitulos.setItems(titulos);
        tablaTitulos.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, item) -> seleccionar(item));
        cargar();
        nuevo();
    }

    @FXML
    private void nuevo() {
        seleccionado = null;
        tablaTitulos.getSelectionModel().clearSelection();
        txtTitulo.clear();
        mensajeLabel.setText("");
        txtTitulo.requestFocus();
    }

    @FXML
    private void guardar() {
        String nombre = text(txtTitulo);
        if (nombre.isBlank()) {
            mensajeLabel.setText("Ingrese el nombre del titulo.");
            return;
        }

        TituloDto dto = seleccionado == null ? new TituloDto() : seleccionado;
        dto.setNombre(nombre);
        if (dto.getEstado() == null) {
            dto.setEstado(1);
        }
        if (dto.getPrioridad() == null) {
            dto.setPrioridad(siguientePrioridad());
        }

        try {
            tituloClient.save(dto);
            cargar();
            nuevo();
            mensajeLabel.setText("Titulo guardado.");
        } catch (RuntimeException ex) {
            mensajeLabel.setText(ex.getMessage());
        }
    }

    @FXML
    private void borrar() {
        if (seleccionado == null || seleccionado.getId() == null) {
            mensajeLabel.setText("Seleccione un titulo para borrar.");
            return;
        }

        try {
            tituloClient.deleteById(seleccionado.getId());
            cargar();
            nuevo();
            mensajeLabel.setText("Titulo borrado.");
        } catch (RuntimeException ex) {
            mensajeLabel.setText(ex.getMessage());
        }
    }

    @FXML
    private void salir() {
        Stage stage = (Stage) txtTitulo.getScene().getWindow();
        stage.close();
    }

    private void cargar() {
        try {
            List<TituloDto> datos = tituloClient.findAll().stream()
                    .sorted(Comparator.comparing(t -> toText(t.getNombre()), String.CASE_INSENSITIVE_ORDER))
                    .toList();
            titulos.setAll(datos);
        } catch (RuntimeException ex) {
            mensajeLabel.setText(ex.getMessage());
        }
    }

    private void seleccionar(TituloDto item) {
        seleccionado = item;
        if (item == null) {
            return;
        }
        txtTitulo.setText(toText(item.getNombre()));
        mensajeLabel.setText("");
    }

    private int siguientePrioridad() {
        return titulos.stream()
                .map(TituloDto::getPrioridad)
                .filter(v -> v != null)
                .max(Integer::compareTo)
                .orElse(0) + 1;
    }

    private String text(TextField field) {
        return field.getText() == null ? "" : field.getText().trim();
    }

    private String toText(Object value) {
        return value == null ? "" : value.toString();
    }

    private String toEstado(Integer estado) {
        return estado != null && estado == 1 ? "Activo" : "Inactivo";
    }
}
