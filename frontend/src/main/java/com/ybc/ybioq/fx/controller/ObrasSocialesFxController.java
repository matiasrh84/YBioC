package com.ybc.ybioq.fx.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ObrasSocialesFxController {

    private final ObservableList<ObraSocialRow> obrasSociales = FXCollections.observableArrayList();

    @FXML
    private TextField txtRazonSocial;

    @FXML
    private TextField txtCodigoOs;

    @FXML
    private DatePicker dpFechaAlta;

    @FXML
    private TextField txtCuit;

    @FXML
    private ComboBox<String> cboNBU;

    @FXML
    private TextField txtCodigoFacturacion;

    @FXML
    private TextField txtDireccion;

    @FXML
    private TextField txtLocalidad;

    @FXML
    private TextField txtTelefono;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPaginaWeb;

    @FXML
    private TextField txtReferente;

    @FXML
    private TextField txtTelefonoReferente;

    @FXML
    private TextField txtUnidadArancel;

    @FXML
    private TextField txtContrato;

    @FXML
    private TextField txtPorcentajeAfiliado;

    @FXML
    private ComboBox<String> cboTipoFacturacion;

    @FXML
    private ComboBox<String> cboTipoIVA;

    @FXML
    private ToggleGroup groupAltaComplejidad;

    @FXML
    private ToggleGroup groupNoNomenclados;

    @FXML
    private ToggleGroup groupFacturaPor;

    @FXML
    private ToggleGroup groupFacturaPorPaciente;

    @FXML
    private ToggleGroup groupImprimeDobleInforme;

    @FXML
    private ToggleGroup groupD998;

    @FXML
    private ToggleGroup groupSubtotal;

    @FXML
    private ToggleGroup groupCategorizacion;

    @FXML
    private ToggleGroup groupTipoFacturacionDirectaColegio;

    @FXML
    private RadioButton rbSiAltaComplejidad;

    @FXML
    private RadioButton rbSiFacturaNoNomenclados;

    @FXML
    private RadioButton rbCupon;

    @FXML
    private RadioButton rbSiFacturaPorPaciente;

    @FXML
    private RadioButton rbNunca;

    @FXML
    private RadioButton rbIncluye;

    @FXML
    private RadioButton rbSiSubtotal;

    @FXML
    private RadioButton rbSiCategorizacion;

    @FXML
    private RadioButton rbDirecta;

    @FXML
    private TableView<ObraSocialRow> tablaObrasSociales;

    @FXML
    private TableColumn<ObraSocialRow, String> colCodigo;

    @FXML
    private TableColumn<ObraSocialRow, String> colRazonSocial;

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnModificar;

    @FXML
    private Label mensajeLabel;

    @FXML
    public void initialize() {
        initCombo(cboNBU, "NBU");
        initCombo(cboTipoFacturacion, "Importes", "NBU", "Convenio", "CONV+DCTO", "S/Detalle");
        initCombo(cboTipoIVA, "Exento", "10.5 %", "21 %", "Responsable Inscripto");

        dpFechaAlta.setValue(LocalDate.now());
        resetOptions();

        colCodigo.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().codigo()));
        colRazonSocial.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().razonSocial()));
        tablaObrasSociales.setItems(obrasSociales);

        tablaObrasSociales.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, selected) -> {
            if (selected == null) {
                return;
            }
            txtCodigoOs.setText(selected.codigo());
            txtRazonSocial.setText(selected.razonSocial());
            btnModificar.setDisable(false);
            btnAgregar.setDisable(true);
            mensajeLabel.setText("Obra social seleccionada. Lista para modificar.");
        });

        limpiarCampos();
    }

    @FXML
    private void guardar() {
        if (isBlank(txtCodigoOs) || isBlank(txtRazonSocial)) {
            mensajeLabel.setText("Complete Codigo OS y Razon Social.");
            return;
        }

        ObraSocialRow row = new ObraSocialRow(txtCodigoOs.getText().trim(), txtRazonSocial.getText().trim());
        int existingIndex = findIndexByCodigo(row.codigo());
        if (existingIndex >= 0) {
            obrasSociales.set(existingIndex, row);
            mensajeLabel.setText("Obra social actualizada en pantalla.");
        } else {
            obrasSociales.add(row);
            mensajeLabel.setText("Obra social agregada en pantalla.");
        }
        tablaObrasSociales.getSelectionModel().select(row);
        btnModificar.setDisable(false);
        btnAgregar.setDisable(true);
    }

    @FXML
    private void modificar() {
        if (tablaObrasSociales.getSelectionModel().getSelectedItem() == null) {
            mensajeLabel.setText("Seleccione una obra social.");
            return;
        }
        guardar();
    }

    @FXML
    private void limpiarCampos() {
        txtRazonSocial.clear();
        txtCodigoOs.clear();
        dpFechaAlta.setValue(LocalDate.now());
        txtCuit.clear();
        txtCodigoFacturacion.clear();
        txtDireccion.clear();
        txtLocalidad.clear();
        txtTelefono.clear();
        txtEmail.clear();
        txtPaginaWeb.clear();
        txtReferente.clear();
        txtTelefonoReferente.clear();
        txtUnidadArancel.clear();
        txtContrato.clear();
        txtPorcentajeAfiliado.clear();

        resetOptions();
        initCombo(cboNBU, "NBU");
        initCombo(cboTipoFacturacion, "Importes", "NBU", "Convenio", "CONV+DCTO", "S/Detalle");
        initCombo(cboTipoIVA, "Exento", "10.5 %", "21 %", "Responsable Inscripto");

        tablaObrasSociales.getSelectionModel().clearSelection();
        btnModificar.setDisable(true);
        btnAgregar.setDisable(false);
        mensajeLabel.setText("Formulario limpio.");
    }

    @FXML
    private void cerrar() {
        if (btnAgregar == null || btnAgregar.getScene() == null) {
            return;
        }
        Stage stage = (Stage) btnAgregar.getScene().getWindow();
        stage.close();
    }

    private void initCombo(ComboBox<String> combo, String... values) {
        combo.getItems().setAll(values);
        if (!combo.getItems().isEmpty()) {
            combo.getSelectionModel().selectFirst();
        }
    }

    private void resetOptions() {
        if (rbSiAltaComplejidad != null) {
            rbSiAltaComplejidad.setSelected(true);
        }
        if (rbSiFacturaNoNomenclados != null) {
            rbSiFacturaNoNomenclados.setSelected(true);
        }
        if (rbCupon != null) {
            rbCupon.setSelected(true);
        }
        if (rbSiFacturaPorPaciente != null) {
            rbSiFacturaPorPaciente.setSelected(true);
        }
        if (rbNunca != null) {
            rbNunca.setSelected(true);
        }
        if (rbIncluye != null) {
            rbIncluye.setSelected(true);
        }
        if (rbSiSubtotal != null) {
            rbSiSubtotal.setSelected(true);
        }
        if (rbSiCategorizacion != null) {
            rbSiCategorizacion.setSelected(true);
        }
        if (rbDirecta != null) {
            rbDirecta.setSelected(true);
        }
    }

    private boolean isBlank(TextField field) {
        return field.getText() == null || field.getText().trim().isEmpty();
    }

    private int findIndexByCodigo(String codigo) {
        for (int i = 0; i < obrasSociales.size(); i++) {
            ObraSocialRow row = obrasSociales.get(i);
            if (row.codigo().equalsIgnoreCase(codigo)) {
                return i;
            }
        }
        return -1;
    }

    private record ObraSocialRow(String codigo, String razonSocial) {
    }
}
