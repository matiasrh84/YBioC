package com.ybc.ybioq.fx.controller;

import com.ybc.ybioq.fx.client.MetodoClient;
import com.ybc.ybioq.fx.client.dto.MetodoDto;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.materialdesign2.MaterialDesignC;
import org.kordamp.ikonli.materialdesign2.MaterialDesignP;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MetodosFxController {

    private final MetodoClient metodoClient;
    private final ObservableList<MetodoDto> metodos = FXCollections.observableArrayList();
    private MetodoDto seleccionado;

    @FXML
    private TableView<MetodoDto> tablaMetodos;
    @FXML
    private TableColumn<MetodoDto, String> nombreColumn;
    @FXML
    private TableColumn<MetodoDto, String> estadoColumn;
    @FXML
    private TextField filtroField;
    @FXML
    private TextField txtMetodo;
    @FXML
    private Label modoLabel;
    @FXML
    private Label mensajeLabel;
    @FXML
    private Button guardarBtn;
    @FXML
    private Button cancelarBtn;
    @FXML
    private Button bajaBtn;

    public MetodosFxController(MetodoClient metodoClient) {
        this.metodoClient = metodoClient;
    }

    @FXML
    public void initialize() {
        nombreColumn.setCellValueFactory(c -> new ReadOnlyStringWrapper(str(c.getValue().nombre())));
        estadoColumn.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().estado() ? "Activo" : "Inactivo"));
        tablaMetodos.setItems(metodos);
        tablaMetodos.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);
        tablaMetodos.getSelectionModel().selectedItemProperty()
                .addListener((obs, old, item) -> seleccionar(item));
        filtroField.textProperty().addListener((obs, old, val) -> cargar());
        cargar();
        modoNuevo();
    }

    @FXML
    private void guardar() {
        String nombre = txtMetodo.getText() == null ? "" : txtMetodo.getText().trim();
        if (nombre.isBlank()) {
            mensajeLabel.setText("Ingrese el nombre del método.");
            return;
        }
        boolean esNuevo = seleccionado == null;
        MetodoDto dto = new MetodoDto(
                esNuevo ? null : seleccionado.id(),
                nombre,
                seleccionado != null ? seleccionado.estado() : true
        );
        try {
            metodoClient.save(dto);
            cargar();
            modoNuevo();
            mensajeLabel.setText(esNuevo ? "Método agregado." : "Método guardado.");
        } catch (RuntimeException ex) {
            mensajeLabel.setText(ex.getMessage());
        }
    }

    @FXML
    private void cambiarEstado() {
        if (seleccionado == null) return;
        MetodoDto dto = new MetodoDto(seleccionado.id(), seleccionado.nombre(), !seleccionado.estado());
        try {
            metodoClient.save(dto);
            cargar();
            modoNuevo();
            mensajeLabel.setText(dto.estado() ? "Método reactivado." : "Método dado de baja.");
        } catch (RuntimeException ex) {
            mensajeLabel.setText(ex.getMessage());
        }
    }

    @FXML
    private void cancelar() {
        modoNuevo();
    }

    private void cargar() {
        String filtro = filtroField.getText() == null ? "" : filtroField.getText().trim().toLowerCase(Locale.ROOT);
        try {
            List<MetodoDto> datos = metodoClient.findAll().stream()
                    .filter(m -> filtro.isBlank() || m.nombre() != null && m.nombre().toLowerCase(Locale.ROOT).contains(filtro))
                    .toList();
            metodos.setAll(datos);
        } catch (RuntimeException ex) {
            mensajeLabel.setText(ex.getMessage());
        }
    }

    private void seleccionar(MetodoDto item) {
        seleccionado = item;
        if (item == null) return;
        txtMetodo.setText(str(item.nombre()));
        mensajeLabel.setText("");
        modoEdicion(item);
    }

    private void modoNuevo() {
        seleccionado = null;
        tablaMetodos.getSelectionModel().clearSelection();
        txtMetodo.clear();
        mensajeLabel.setText("");
        txtMetodo.requestFocus();
        modoLabel.setText("Nuevo método");
        modoLabel.getStyleClass().setAll("modo-label");
        guardarBtn.setText("Agregar");
        guardarBtn.setGraphic(icon(MaterialDesignP.PLUS, 18));
        cancelarBtn.setVisible(false);
        cancelarBtn.setManaged(false);
        bajaBtn.setVisible(false);
        bajaBtn.setManaged(false);
    }

    private void modoEdicion(MetodoDto item) {
        modoLabel.setText("Editando: " + str(item.nombre()));
        modoLabel.getStyleClass().setAll("modo-label-editando");
        guardarBtn.setText("Guardar");
        guardarBtn.setGraphic(icon(MaterialDesignC.CONTENT_SAVE, 18));
        cancelarBtn.setVisible(true);
        cancelarBtn.setManaged(true);
        bajaBtn.setVisible(true);
        bajaBtn.setManaged(true);
        bajaBtn.setText(item.estado() ? "Dar de baja" : "Reactivar");
    }

    private FontIcon icon(org.kordamp.ikonli.Ikon ikon, int size) {
        FontIcon fi = new FontIcon(ikon);
        fi.setIconSize(size);
        return fi;
    }

    private String str(Object v) {
        return v == null ? "" : v.toString();
    }
}
