package com.ybc.ybioq.fx.controller;

import com.ybc.ybioq.fx.client.UnidadClient;
import com.ybc.ybioq.fx.client.dto.UnidadDto;
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
public class UnidadesFxController {

    private final UnidadClient unidadClient;
    private final ObservableList<UnidadDto> unidades = FXCollections.observableArrayList();
    private UnidadDto seleccionado;

    @FXML
    private TextField filtroField;
    @FXML
    private TextField nombreField;
    @FXML
    private CheckBox estadoCheck;
    @FXML
    private TableView<UnidadDto> unidadesTable;
    @FXML
    private TableColumn<UnidadDto, String> nombreColumn;
    @FXML
    private TableColumn<UnidadDto, String> estadoColumn;
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

    public UnidadesFxController(UnidadClient unidadClient) {
        this.unidadClient = unidadClient;
    }

    @FXML
    public void initialize() {
        nombreColumn.setCellValueFactory(c -> new ReadOnlyStringWrapper(str(c.getValue().nombre())));
        estadoColumn.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().estado() ? "Activa" : "Baja"));
        unidadesTable.setItems(unidades);
        unidadesTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);
        unidadesTable.getSelectionModel().selectedItemProperty()
                .addListener((obs, old, item) -> seleccionar(item));
        cargar();
        modoNuevo();
    }

    @FXML
    private void guardar() {
        String nombre = nombreField.getText() == null ? "" : nombreField.getText().trim();
        if (nombre.isBlank()) {
            mensajeLabel.setText("El nombre es obligatorio.");
            return;
        }
        boolean esNuevo = seleccionado == null;
        UnidadDto dto = new UnidadDto(esNuevo ? null : seleccionado.id(), nombre, estadoCheck.isSelected());
        try {
            unidadClient.save(dto);
            cargar();
            modoNuevo();
            mensajeLabel.setText(esNuevo ? "Unidad agregada." : "Unidad guardada.");
        } catch (RuntimeException ex) {
            mensajeLabel.setText(ex.getMessage());
        }
    }

    @FXML
    private void cambiarEstado() {
        if (seleccionado == null) return;
        UnidadDto dto = new UnidadDto(seleccionado.id(), seleccionado.nombre(), !seleccionado.estado());
        try {
            unidadClient.save(dto);
            cargar();
            modoNuevo();
            mensajeLabel.setText(dto.estado() ? "Unidad reactivada." : "Unidad dada de baja.");
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
            List<UnidadDto> datos = unidadClient.findAll().stream()
                    .filter(u -> filtro.isBlank() || u.nombre() != null && u.nombre().toLowerCase(Locale.ROOT).contains(filtro))
                    .toList();
            unidades.setAll(datos);
        } catch (RuntimeException ex) {
            mensajeLabel.setText(ex.getMessage());
        }
    }

    private void seleccionar(UnidadDto item) {
        seleccionado = item;
        if (item == null) return;
        nombreField.setText(str(item.nombre()));
        estadoCheck.setSelected(item.estado());
        mensajeLabel.setText("");
        modoEdicion(item);
    }

    private void modoNuevo() {
        seleccionado = null;
        unidadesTable.getSelectionModel().clearSelection();
        nombreField.clear();
        estadoCheck.setSelected(true);
        mensajeLabel.setText("");
        nombreField.requestFocus();
        modoLabel.setText("Nueva unidad");
        modoLabel.getStyleClass().setAll("modo-label");
        guardarBtn.setText("Agregar");
        guardarBtn.setGraphic(icon(MaterialDesignP.PLUS, 18));
        cancelarBtn.setVisible(false);
        cancelarBtn.setManaged(false);
        bajaBtn.setVisible(false);
        bajaBtn.setManaged(false);
    }

    private void modoEdicion(UnidadDto item) {
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
