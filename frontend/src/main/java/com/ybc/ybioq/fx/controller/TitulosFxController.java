package com.ybc.ybioq.fx.controller;

import com.ybc.ybioq.fx.client.TituloClient;
import com.ybc.ybioq.fx.client.dto.TituloDto;
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
public class TitulosFxController {

    private final TituloClient tituloClient;
    private final ObservableList<TituloDto> titulos = FXCollections.observableArrayList();
    private TituloDto seleccionado;

    @FXML
    private TableView<TituloDto> tablaTitulos;
    @FXML
    private TableColumn<TituloDto, String> nombreColumn;
    @FXML
    private TableColumn<TituloDto, String> estadoColumn;
    @FXML
    private TableColumn<TituloDto, String> prioridadColumn;
    @FXML
    private TextField filtroField;
    @FXML
    private TextField txtTitulo;
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

    public TitulosFxController(TituloClient tituloClient) {
        this.tituloClient = tituloClient;
    }

    @FXML
    public void initialize() {
        nombreColumn.setCellValueFactory(c -> new ReadOnlyStringWrapper(str(c.getValue().nombre())));
        estadoColumn.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().estado() ? "Activo" : "Inactivo"));
        prioridadColumn.setCellValueFactory(c -> new ReadOnlyStringWrapper(str(c.getValue().prioridad())));
        tablaTitulos.setItems(titulos);
        tablaTitulos.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);
        tablaTitulos.getSelectionModel().selectedItemProperty()
                .addListener((obs, old, item) -> seleccionar(item));
        cargar();
        modoNuevo();
    }

    @FXML
    private void guardar() {
        String nombre = txtTitulo.getText() == null ? "" : txtTitulo.getText().trim();
        if (nombre.isBlank()) {
            mensajeLabel.setText("Ingrese el nombre del título.");
            return;
        }
        boolean esNuevo = seleccionado == null;
        TituloDto dto = new TituloDto(
                esNuevo ? null : seleccionado.id(),
                nombre,
                seleccionado != null ? seleccionado.estado() : true,
                seleccionado != null ? seleccionado.prioridad() : null
        );
        try {
            tituloClient.save(dto);
            cargar();
            modoNuevo();
            mensajeLabel.setText(esNuevo ? "Título agregado." : "Título guardado.");
        } catch (RuntimeException ex) {
            mensajeLabel.setText(ex.getMessage());
        }
    }

    @FXML
    private void cambiarEstado() {
        if (seleccionado == null) return;
        TituloDto dto = new TituloDto(seleccionado.id(), seleccionado.nombre(), !seleccionado.estado(), seleccionado.prioridad());
        try {
            tituloClient.save(dto);
            cargar();
            modoNuevo();
            mensajeLabel.setText(dto.estado() ? "Título reactivado." : "Título dado de baja.");
        } catch (RuntimeException ex) {
            mensajeLabel.setText(ex.getMessage());
        }
    }

    @FXML
    private void cancelar() {
        modoNuevo();
    }

    @FXML
    private void cargar() {
        String filtro = filtroField.getText() == null ? "" : filtroField.getText().trim().toLowerCase(Locale.ROOT);
        try {
            List<TituloDto> datos = tituloClient.findAll().stream()
                    .filter(t -> filtro.isBlank() || t.nombre() != null && t.nombre().toLowerCase(Locale.ROOT).contains(filtro))
                    .toList();
            titulos.setAll(datos);
        } catch (RuntimeException ex) {
            mensajeLabel.setText(ex.getMessage());
        }
    }

    private void seleccionar(TituloDto item) {
        seleccionado = item;
        if (item == null) return;
        txtTitulo.setText(str(item.nombre()));
        mensajeLabel.setText("");
        modoEdicion(item);
    }

    private void modoNuevo() {
        seleccionado = null;
        tablaTitulos.getSelectionModel().clearSelection();
        txtTitulo.clear();
        mensajeLabel.setText("");
        txtTitulo.requestFocus();
        modoLabel.setText("Nuevo título");
        modoLabel.getStyleClass().setAll("modo-label");
        guardarBtn.setText("Agregar");
        guardarBtn.setGraphic(icon(MaterialDesignP.PLUS, 18));
        cancelarBtn.setVisible(false);
        cancelarBtn.setManaged(false);
        bajaBtn.setVisible(false);
        bajaBtn.setManaged(false);
    }

    private void modoEdicion(TituloDto item) {
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
