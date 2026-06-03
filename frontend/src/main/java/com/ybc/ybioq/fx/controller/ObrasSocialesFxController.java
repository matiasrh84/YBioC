package com.ybc.ybioq.fx.controller;

import com.ybc.ybioq.fx.client.ObraSocialClient;
import com.ybc.ybioq.fx.client.dto.ObraSocialDto;
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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ObrasSocialesFxController {

    private final ObraSocialClient obraSocialClient;
    private final ObservableList<ObraSocialDto> lista = FXCollections.observableArrayList();
    private ObraSocialDto seleccionado;

    // --- Lista ---
    @FXML
    private TextField filtroField;
    @FXML
    private TableView<ObraSocialDto> tablaObrasSociales;
    @FXML
    private TableColumn<ObraSocialDto, String> colCodigo;
    @FXML
    private TableColumn<ObraSocialDto, String> colRazonSocial;
    @FXML
    private TableColumn<ObraSocialDto, String> colEstado;
    @FXML
    private Label modoLabel;
    @FXML
    private Label mensajeLabel;
    @FXML
    private Button guardarBtn;
    @FXML
    private Button cancelarBtn;

    // --- Datos principales ---
    @FXML
    private TextField txtRazonSocial;
    @FXML
    private TextField txtCodigoOs;
    @FXML
    private TextField txtNombre;
    @FXML
    private DatePicker dpFechaAlta;
    @FXML
    private TextField txtCuit;
    @FXML
    private TextField txtPeriodoNbu;
    @FXML
    private TextField txtCodigoFacturacion;
    @FXML
    private TextField txtDireccion;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtEmail;        // mail1
    @FXML
    private TextField txtMail1;        // mail2
    @FXML
    private TextField txtMail2;        // mail3
    @FXML
    private TextField txtPaginaWeb;
    @FXML
    private TextField txtReferente;
    @FXML
    private TextField txtTelefonoReferente;
    @FXML
    private TextField txtUnidadArancel;
    @FXML
    private TextField txtPorcentajeAfiliado;
    @FXML
    private TextField txtPorcentajeDescuento;

    // --- CheckBoxes para campos booleanos ---
    @FXML
    private CheckBox cbFacturaAltaComplejidad;
    @FXML
    private CheckBox cbFacturaNoNomenclados;
    @FXML
    private CheckBox cbFacturaPorPaciente;
    @FXML
    private CheckBox cbSubtotalPorPaciente;
    @FXML
    private CheckBox cbTieneCategorizacion;

    // --- ToggleGroups para opciones múltiples ---
    @FXML
    private ToggleGroup groupFacturaPor;
    @FXML
    private ToggleGroup groupImprimeDobleInforme;
    @FXML
    private ToggleGroup groupD998;
    @FXML
    private ToggleGroup groupTipoFacturacionDirectaColegio;

    // RadioButtons para defaults en resetOptions
    @FXML
    private RadioButton rbCupon;
    @FXML
    private RadioButton rbNunca;
    @FXML
    private RadioButton rbIncluye;
    @FXML
    private RadioButton rbDirecta;

    @FXML
    private ComboBox<String> cboTipoFacturacion;
    @FXML
    private ComboBox<String> cboTipoIVA;

    public ObrasSocialesFxController(ObraSocialClient obraSocialClient) {
        this.obraSocialClient = obraSocialClient;
    }

    @FXML
    public void initialize() {
        colCodigo.setCellValueFactory(c -> new ReadOnlyStringWrapper(str(c.getValue().codigo())));
        colRazonSocial.setCellValueFactory(c -> new ReadOnlyStringWrapper(str(c.getValue().razonSocial())));
        colEstado.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().estado() ? "Activa" : "Baja"));
        tablaObrasSociales.setItems(lista);
        tablaObrasSociales.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);
        tablaObrasSociales.getSelectionModel().selectedItemProperty()
                .addListener((obs, old, item) -> seleccionar(item));
        filtroField.textProperty().addListener((obs, old, val) -> cargar());

        initCombo(cboTipoFacturacion, "Importes", "NBU", "Convenio", "CONV+DCTO", "S/Detalle");
        initCombo(cboTipoIVA, "Exento", "10.5 %", "21 %", "Responsable Inscripto");

        cargar();
        modoNuevo();
    }

    @FXML
    private void guardar() {
        String codigo = trim(txtCodigoOs);
        String razonSocial = trim(txtRazonSocial);
        if (codigo.isBlank() || razonSocial.isBlank()) {
            mensajeLabel.setText("Código OS y Razón Social son obligatorios.");
            return;
        }
        boolean esNuevo = seleccionado == null;
        ObraSocialDto dto = new ObraSocialDto(
                esNuevo ? null : seleccionado.id(),
                codigo,
                razonSocial,
                emptyToNull(trim(txtNombre)),
                emptyToNull(trim(txtCuit)),
                emptyToNull(trim(txtCodigoFacturacion)),
                emptyToNull(trim(txtTelefono)),
                emptyToNull(trim(txtEmail)),
                emptyToNull(trim(txtMail1)),
                emptyToNull(trim(txtMail2)),
                emptyToNull(trim(txtPaginaWeb)),
                emptyToNull(trim(txtDireccion)),
                dpFechaAlta.getValue() != null ? dpFechaAlta.getValue().toString() : null,
                emptyToNull(trim(txtReferente)),
                emptyToNull(trim(txtTelefonoReferente)),
                emptyToNull(trim(txtPeriodoNbu)),
                parseBigDecimal(trim(txtUnidadArancel)),
                emptyToNull(trim(txtPorcentajeAfiliado)),
                emptyToNull(trim(txtPorcentajeDescuento)),
                cbFacturaAltaComplejidad.isSelected(),
                cbFacturaNoNomenclados.isSelected(),
                getRadio(groupFacturaPor),
                cbFacturaPorPaciente.isSelected(),
                getRadio(groupImprimeDobleInforme),
                getRadio(groupD998),
                cbSubtotalPorPaciente.isSelected(),
                cbTieneCategorizacion.isSelected(),
                cboTipoFacturacion.getValue(),
                getRadio(groupTipoFacturacionDirectaColegio),
                cboTipoIVA.getValue(),
                true
        );
        try {
            obraSocialClient.save(dto);
            cargar();
            modoNuevo();
            mensajeLabel.setText(esNuevo ? "Obra social agregada." : "Obra social guardada.");
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
            List<ObraSocialDto> datos = obraSocialClient.findAll().stream()
                    .filter(os -> filtro.isBlank()
                            || str(os.razonSocial()).toLowerCase(Locale.ROOT).contains(filtro)
                            || str(os.codigo()).toLowerCase(Locale.ROOT).contains(filtro))
                    .toList();
            lista.setAll(datos);
        } catch (RuntimeException ex) {
            mensajeLabel.setText(ex.getMessage());
        }
    }

    private void seleccionar(ObraSocialDto os) {
        seleccionado = os;
        if (os == null) return;

        txtCodigoOs.setText(str(os.codigo()));
        txtRazonSocial.setText(str(os.razonSocial()));
        txtNombre.setText(str(os.nombre()));
        txtCuit.setText(str(os.cuit()));
        txtCodigoFacturacion.setText(str(os.codigoFacturacion()));
        txtTelefono.setText(str(os.telefono()));
        txtEmail.setText(str(os.mail1()));
        txtMail1.setText(str(os.mail2()));
        txtMail2.setText(str(os.mail3()));
        txtPaginaWeb.setText(str(os.web()));
        txtDireccion.setText(str(os.direccion()));
        dpFechaAlta.setValue(parseDate(os.fechaDeAlta()));
        txtReferente.setText(str(os.nombreReferente()));
        txtTelefonoReferente.setText(str(os.celularReferente()));
        txtPeriodoNbu.setText(str(os.periodoNbu()));
        txtUnidadArancel.setText(os.importeUnidadDeArancel() != null ? os.importeUnidadDeArancel().toPlainString() : "");
        txtPorcentajeAfiliado.setText(str(os.porcentajeAfiliado()));
        txtPorcentajeDescuento.setText(str(os.porcentajeDescuento()));

        cbFacturaAltaComplejidad.setSelected(os.facturaAltaComplejidad());
        cbFacturaNoNomenclados.setSelected(os.facturaNoNomenclados());
        cbFacturaPorPaciente.setSelected(os.facturaPorPaciente());
        cbSubtotalPorPaciente.setSelected(os.subtotalPorPaciente());
        cbTieneCategorizacion.setSelected(os.tieneCategorizacion());
        selectByText(groupFacturaPor, os.facturaPor());
        selectByText(groupImprimeDobleInforme, os.imprimeDobleInforme());
        selectByText(groupD998, os.d998());
        selectCombo(cboTipoFacturacion, os.tipoDeFacturacion());
        selectByText(groupTipoFacturacionDirectaColegio, os.tipoDeFacturacionDirectaOColegio());
        selectCombo(cboTipoIVA, os.tipoIva());

        mensajeLabel.setText("");
        modoEdicion(os);
    }

    private void modoNuevo() {
        seleccionado = null;
        tablaObrasSociales.getSelectionModel().clearSelection();
        limpiarCampos();
        modoLabel.setText("Nueva obra social");
        modoLabel.getStyleClass().setAll("modo-label");
        guardarBtn.setText("Agregar");
        guardarBtn.setGraphic(icon(MaterialDesignP.PLUS, 18));
        cancelarBtn.setVisible(false);
        cancelarBtn.setManaged(false);
    }

    private void modoEdicion(ObraSocialDto os) {
        modoLabel.setText("Editando: " + str(os.razonSocial()));
        modoLabel.getStyleClass().setAll("modo-label-editando");
        guardarBtn.setText("Guardar");
        guardarBtn.setGraphic(icon(MaterialDesignC.CONTENT_SAVE, 18));
        cancelarBtn.setVisible(true);
        cancelarBtn.setManaged(true);
    }

    private void limpiarCampos() {
        txtCodigoOs.clear();
        txtRazonSocial.clear();
        txtNombre.clear();
        txtCuit.clear();
        txtCodigoFacturacion.clear();
        txtDireccion.clear();
        txtTelefono.clear();
        txtEmail.clear();
        txtMail1.clear();
        txtMail2.clear();
        txtPaginaWeb.clear();
        txtReferente.clear();
        txtTelefonoReferente.clear();
        txtUnidadArancel.clear();
        txtPorcentajeAfiliado.clear();
        txtPorcentajeDescuento.clear();
        txtPeriodoNbu.clear();
        dpFechaAlta.setValue(LocalDate.now());
        cbFacturaAltaComplejidad.setSelected(true);
        cbFacturaNoNomenclados.setSelected(true);
        cbFacturaPorPaciente.setSelected(true);
        cbSubtotalPorPaciente.setSelected(true);
        cbTieneCategorizacion.setSelected(true);
        if (rbCupon != null) rbCupon.setSelected(true);
        if (rbNunca != null) rbNunca.setSelected(true);
        if (rbIncluye != null) rbIncluye.setSelected(true);
        if (rbDirecta != null) rbDirecta.setSelected(true);
        initCombo(cboTipoFacturacion, "Importes", "NBU", "Convenio", "CONV+DCTO", "S/Detalle");
        initCombo(cboTipoIVA, "Exento", "10.5 %", "21 %", "Responsable Inscripto");
    }

    private String getRadio(ToggleGroup group) {
        Toggle t = group.getSelectedToggle();
        return (t instanceof RadioButton rb) ? rb.getText() : null;
    }

    private void selectByText(ToggleGroup group, String value) {
        if (value == null) return;
        group.getToggles().stream()
                .filter(t -> t instanceof RadioButton rb && rb.getText().equalsIgnoreCase(value.trim()))
                .findFirst()
                .ifPresent(t -> t.setSelected(true));
    }

    private void selectCombo(ComboBox<String> combo, String value) {
        if (value == null) return;
        combo.getItems().stream()
                .filter(item -> item.equalsIgnoreCase(value.trim()))
                .findFirst()
                .ifPresent(combo::setValue);
    }

    private void initCombo(ComboBox<String> combo, String... values) {
        combo.getItems().setAll(values);
        if (!combo.getItems().isEmpty()) combo.getSelectionModel().selectFirst();
    }

    private LocalDate parseDate(String s) {
        try {
            return (s == null || s.isBlank()) ? null : LocalDate.parse(s);
        } catch (Exception e) {
            return null;
        }
    }

    private BigDecimal parseBigDecimal(String s) {
        try {
            return (s == null || s.isBlank()) ? null : new BigDecimal(s.replace(",", "."));
        } catch (Exception e) {
            return null;
        }
    }

    private FontIcon icon(org.kordamp.ikonli.Ikon ikon, int size) {
        FontIcon fi = new FontIcon(ikon);
        fi.setIconSize(size);
        return fi;
    }

    private String trim(TextField f) {
        return f.getText() == null ? "" : f.getText().trim();
    }

    private String str(Object v) {
        return v == null ? "" : v.toString();
    }

    private String emptyToNull(String s) {
        return s.isBlank() ? null : s;
    }
}
