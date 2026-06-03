package com.ybc.ybioq.fx.controller;

import com.ybc.ybioq.fx.client.DerivacionClient;
import com.ybc.ybioq.fx.client.dto.DerivacionDto;
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
public class DerivacionesFxController {

    private final DerivacionClient derivacionClient;
    private final ObservableList<DerivacionDto> derivaciones = FXCollections.observableArrayList();
    private DerivacionDto seleccionado;

    @FXML
    private TextField filtroField;
    @FXML
    private TextField nombreField;
    @FXML
    private TextField direccionField;
    @FXML
    private TextField telefonoField;
    @FXML
    private TextField mailField;
    @FXML
    private TextArea observacionesArea;
    @FXML
    private TableView<DerivacionDto> derivacionesTable;
    @FXML
    private TableColumn<DerivacionDto, String> nombreColumn;
    @FXML
    private TableColumn<DerivacionDto, String> direccionColumn;
    @FXML
    private TableColumn<DerivacionDto, String> telefonoColumn;
    @FXML
    private TableColumn<DerivacionDto, String> mailColumn;
    @FXML
    private Label modoLabel;
    @FXML
    private Label mensajeLabel;
    @FXML
    private Button guardarBtn;
    @FXML
    private Button cancelarBtn;

    public DerivacionesFxController(DerivacionClient derivacionClient) {
        this.derivacionClient = derivacionClient;
    }

    @FXML
    private void initialize() {
        nombreColumn.setCellValueFactory(c -> new ReadOnlyStringWrapper(str(c.getValue().nombre())));
        direccionColumn.setCellValueFactory(c -> new ReadOnlyStringWrapper(str(c.getValue().direccion())));
        telefonoColumn.setCellValueFactory(c -> new ReadOnlyStringWrapper(str(c.getValue().telefono())));
        mailColumn.setCellValueFactory(c -> new ReadOnlyStringWrapper(str(c.getValue().mail())));
        derivacionesTable.setItems(derivaciones);
        derivacionesTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);
        derivacionesTable.getSelectionModel().selectedItemProperty()
                .addListener((obs, old, item) -> seleccionar(item));
        filtroField.textProperty().addListener((obs, old, val) -> cargar());
        cargar();
        modoNuevo();
    }

    @FXML
    private void guardar() {
        String nombre = trim(nombreField);
        if (nombre.isBlank()) {
            mensajeLabel.setText("El nombre es obligatorio.");
            return;
        }
        String telefonoStr = trim(telefonoField);
        Long telefono = null;
        if (!telefonoStr.isBlank()) {
            try {
                telefono = Long.valueOf(telefonoStr);
            } catch (NumberFormatException ex) {
                mensajeLabel.setText("El teléfono debe ser numérico.");
                return;
            }
        }
        boolean esNuevo = seleccionado == null;
        DerivacionDto dto = new DerivacionDto(
                esNuevo ? null : seleccionado.id(),
                nombre,
                emptyToNull(trim(direccionField)),
                telefono,
                emptyToNull(trim(mailField)),
                emptyToNull(trim(observacionesArea))
        );
        try {
            derivacionClient.save(dto);
            cargar();
            modoNuevo();
            mensajeLabel.setText(esNuevo ? "Derivación agregada." : "Derivación guardada.");
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
        String filtro = trim(filtroField).toLowerCase(Locale.ROOT);
        try {
            List<DerivacionDto> datos = derivacionClient.findAll().stream()
                    .filter(d -> coincide(d, filtro))
                    .toList();
            derivaciones.setAll(datos);
        } catch (RuntimeException ex) {
            mensajeLabel.setText(ex.getMessage());
        }
    }

    private void seleccionar(DerivacionDto d) {
        seleccionado = d;
        if (d == null) return;
        nombreField.setText(str(d.nombre()));
        direccionField.setText(str(d.direccion()));
        telefonoField.setText(str(d.telefono()));
        mailField.setText(str(d.mail()));
        observacionesArea.setText(str(d.observaciones()));
        mensajeLabel.setText("");
        modoEdicion(d);
    }

    private void modoNuevo() {
        seleccionado = null;
        derivacionesTable.getSelectionModel().clearSelection();
        nombreField.clear();
        direccionField.clear();
        telefonoField.clear();
        mailField.clear();
        observacionesArea.clear();
        mensajeLabel.setText("");
        nombreField.requestFocus();
        modoLabel.setText("Nueva derivación");
        modoLabel.getStyleClass().setAll("modo-label");
        guardarBtn.setText("Agregar");
        guardarBtn.setGraphic(icon(MaterialDesignP.PLUS, 18));
        cancelarBtn.setVisible(false);
        cancelarBtn.setManaged(false);
    }

    private void modoEdicion(DerivacionDto d) {
        modoLabel.setText("Editando: " + str(d.nombre()));
        modoLabel.getStyleClass().setAll("modo-label-editando");
        guardarBtn.setText("Guardar");
        guardarBtn.setGraphic(icon(MaterialDesignC.CONTENT_SAVE, 18));
        cancelarBtn.setVisible(true);
        cancelarBtn.setManaged(true);
    }

    private boolean coincide(DerivacionDto d, String filtro) {
        if (filtro.isBlank()) return true;
        return str(d.nombre()).toLowerCase(Locale.ROOT).contains(filtro)
                || str(d.direccion()).toLowerCase(Locale.ROOT).contains(filtro)
                || str(d.mail()).toLowerCase(Locale.ROOT).contains(filtro);
    }

    private FontIcon icon(org.kordamp.ikonli.Ikon ikon, int size) {
        FontIcon fi = new FontIcon(ikon);
        fi.setIconSize(size);
        return fi;
    }

    private String trim(TextField f) {
        return f.getText() == null ? "" : f.getText().trim();
    }

    private String trim(TextArea a) {
        return a.getText() == null ? "" : a.getText().trim();
    }

    private String str(Object v) {
        return v == null ? "" : v.toString();
    }

    private String emptyToNull(String s) {
        return s.isBlank() ? null : s;
    }
}
