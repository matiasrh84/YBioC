package com.ybc.ybioq.fx.controller;

import com.ybc.ybioq.fx.client.DerivacionClient;
import com.ybc.ybioq.fx.client.PracticaClient;
import com.ybc.ybioq.fx.client.SeccionClient;
import com.ybc.ybioq.fx.client.dto.DerivacionDto;
import com.ybc.ybioq.fx.client.dto.PracticaDto;
import com.ybc.ybioq.fx.client.dto.SeccionDto;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PracticasFxController {

    private final PracticaClient practicaClient;
    private final SeccionClient seccionClient;
    private final DerivacionClient derivacionClient;

    // etiqueta visible → código enum almacenado en BD
    private static final Map<String, String> TIPOS = new LinkedHashMap<>();
    static {
        TIPOS.put("Análisis estándar",       "FILAS");
        TIPOS.put("Dos columnas",            "COLUMNAS");
        TIPOS.put("Proteinograma",           "PROTEINOGRAMA");
        TIPOS.put("Cultivo / Bacteriológico","CULTIVO");
        TIPOS.put("Hemograma fijo",          "HEMOGRAMA_FIJO");
        TIPOS.put("Hemograma en filas",      "HEMOGRAMA_FILAS");
    }

    private final ObservableList<PracticaDto> practicas = FXCollections.observableArrayList();
    private List<SeccionDto> todasLasSecciones = Collections.emptyList();
    private List<DerivacionDto> todasLasDerivaciones = Collections.emptyList();
    private PracticaDto seleccionada = null;

    // ── tabla ─────────────────────────────────────────────────────
    @FXML private TextField filtroField;
    @FXML private TableView<PracticaDto> tablaPracticas;
    @FXML private TableColumn<PracticaDto, String> colCodigo;
    @FXML private TableColumn<PracticaDto, String> colNombre;
    @FXML private TableColumn<PracticaDto, String> colSeccion;

    // ── formulario ────────────────────────────────────────────────
    @FXML private TextField txtCodigo;
    @FXML private TextField txtNombre;
    @FXML private TextArea txtInstrucciones;
    @FXML private ComboBox<String> cboSeccion;
    @FXML private ComboBox<String> cboTiempo;
    @FXML private ComboBox<String> cboTipoInforme;
    @FXML private ToggleButton tglDeriva;
    @FXML private ComboBox<String> cboDerivacion;
    @FXML private TextField txtPrecio1;
    @FXML private TextField txtPrecio2;
    @FXML private TextField txtPrecio3;
    @FXML private TextField txtPrecio4;
    @FXML private Label mensajeLabel;

    public PracticasFxController(PracticaClient practicaClient,
                                 SeccionClient seccionClient,
                                 DerivacionClient derivacionClient) {
        this.practicaClient   = practicaClient;
        this.seccionClient    = seccionClient;
        this.derivacionClient = derivacionClient;
    }

    @FXML
    private void initialize() {
        configurarTabla();
        configurarCombos();
        filtroField.textProperty().addListener((obs, o, n) -> cargar());
        tglDeriva.selectedProperty().addListener((obs, o, n) -> cboDerivacion.setDisable(!n));
        txtNombre.textProperty().addListener((obs, o, n) -> {
            if (n != null && !n.equals(n.toUpperCase(Locale.ROOT))) {
                txtNombre.setText(n.toUpperCase(Locale.ROOT));
            }
        });
        nuevo();
        cargar();
    }

    // ── acciones ──────────────────────────────────────────────────

    @FXML
    private void nuevo() {
        seleccionada = null;
        tablaPracticas.getSelectionModel().clearSelection();
        txtCodigo.clear();
        txtNombre.clear();
        txtInstrucciones.clear();
        txtPrecio1.clear();
        txtPrecio2.clear();
        txtPrecio3.clear();
        txtPrecio4.clear();
        if (!cboSeccion.getItems().isEmpty())     cboSeccion.getSelectionModel().selectFirst();
        if (!cboTiempo.getItems().isEmpty())      cboTiempo.getSelectionModel().selectFirst();
        if (!cboTipoInforme.getItems().isEmpty()) cboTipoInforme.getSelectionModel().selectFirst();
        tglDeriva.setSelected(false);
        cboDerivacion.setDisable(true);
        mensajeLabel.setText("");
        txtCodigo.requestFocus();
    }

    @FXML
    private void guardar() {
        String nombre = txtNombre.getText() == null ? "" : txtNombre.getText().trim().toUpperCase(Locale.ROOT);
        Integer codigo = parseCodigo(txtCodigo.getText());

        if (codigo == null) {
            mensajeLabel.setText("El código debe ser un número entero.");
            txtCodigo.requestFocus();
            return;
        }
        if (nombre.isBlank()) {
            mensajeLabel.setText("El nombre es obligatorio.");
            txtNombre.requestFocus();
            return;
        }

        try {
            PracticaDto dto = new PracticaDto(
                    seleccionada != null ? seleccionada.id() : null,
                    codigo,
                    nombre,
                    txtInstrucciones.getText(),
                    idSeccionSeleccionada(),
                    null,
                    tglDeriva.isSelected() ? 1 : 0,
                    idDerivacionSeleccionada(),
                    null,
                    0,
                    parseTiempo(),
                    parseTipoInforme(),
                    parsePrecio(txtPrecio1.getText()),
                    parsePrecio(txtPrecio2.getText()),
                    parsePrecio(txtPrecio3.getText()),
                    parsePrecio(txtPrecio4.getText())
            );

            if (seleccionada == null) {
                practicaClient.save(dto);
                mensajeLabel.setText("Práctica creada.");
            } else {
                practicaClient.update(seleccionada.id(), dto);
                mensajeLabel.setText("Práctica actualizada.");
            }
            nuevo();
            cargar();
        } catch (RuntimeException ex) {
            mensajeLabel.setText(ex.getMessage());
        }
    }

    @FXML
    private void cargar() {
        String filtro = filtroField.getText() == null ? "" : filtroField.getText().trim().toLowerCase(Locale.ROOT);
        try {
            practicas.setAll(practicaClient.findAll().stream()
                    .filter(p -> filtro.isBlank()
                            || (p.determinacion() != null && p.determinacion().toLowerCase(Locale.ROOT).contains(filtro))
                            || (p.codigoPractica() != null && String.valueOf(p.codigoPractica()).contains(filtro)))
                    .toList());
        } catch (RuntimeException ex) {
            mensajeLabel.setText("Error al cargar: " + ex.getMessage());
        }
    }

    // ── privados ──────────────────────────────────────────────────

    private void configurarTabla() {
        colCodigo.setCellValueFactory(d -> new ReadOnlyStringWrapper(
                d.getValue().codigoPractica() != null ? String.valueOf(d.getValue().codigoPractica()) : ""));
        colNombre.setCellValueFactory(d -> new ReadOnlyStringWrapper(
                d.getValue().determinacion() != null ? d.getValue().determinacion() : ""));
        colSeccion.setCellValueFactory(d -> new ReadOnlyStringWrapper(
                d.getValue().nombreSeccion() != null ? d.getValue().nombreSeccion() : ""));
        tablaPracticas.setItems(practicas);
        tablaPracticas.getSelectionModel().selectedItemProperty()
                .addListener((obs, o, sel) -> seleccionar(sel));

        colCodigo.setMinWidth(70);  colCodigo.setMaxWidth(70);  colCodigo.setPrefWidth(70);
        colSeccion.setMinWidth(120); colSeccion.setMaxWidth(150); colSeccion.setPrefWidth(130);
        colNombre.prefWidthProperty().bind(
                tablaPracticas.widthProperty().subtract(colCodigo.getWidth() + colSeccion.getWidth() + 4));
    }

    private void configurarCombos() {
        cboTiempo.getItems().setAll("1", "2", "3", "4", "5", "6", "7");
        cboTipoInforme.getItems().setAll(TIPOS.keySet());
        cboTiempo.getSelectionModel().selectFirst();
        cboTipoInforme.getSelectionModel().selectFirst();

        try {
            todasLasSecciones = seccionClient.findAll();
            cboSeccion.getItems().setAll(todasLasSecciones.stream()
                    .map(SeccionDto::nombre).toList());
        } catch (Exception ex) {
            cboSeccion.getItems().setAll("BIOQUIMICA GENERAL", "HEMATOLOGIA", "HORMONAS");
        }
        if (!cboSeccion.getItems().isEmpty()) cboSeccion.getSelectionModel().selectFirst();

        try {
            todasLasDerivaciones = derivacionClient.findAll();
            cboDerivacion.getItems().setAll(todasLasDerivaciones.stream()
                    .map(DerivacionDto::nombre)
                    .filter(n -> n != null && !n.isBlank())
                    .sorted(String::compareToIgnoreCase)
                    .toList());
        } catch (Exception ex) {
            cboDerivacion.getItems().clear();
        }
        if (!cboDerivacion.getItems().isEmpty()) cboDerivacion.getSelectionModel().selectFirst();
        cboDerivacion.setDisable(true);
    }

    private void seleccionar(PracticaDto dto) {
        seleccionada = dto;
        if (dto == null) return;
        txtCodigo.setText(dto.codigoPractica() != null ? String.valueOf(dto.codigoPractica()) : "");
        txtNombre.setText(dto.determinacion() != null ? dto.determinacion() : "");
        txtInstrucciones.setText(dto.instrucciones() != null ? dto.instrucciones() : "");
        txtPrecio1.setText(dto.precio1() != null ? dto.precio1().toPlainString() : "");
        txtPrecio2.setText(dto.precio2() != null ? dto.precio2().toPlainString() : "");
        txtPrecio3.setText(dto.precio3() != null ? dto.precio3().toPlainString() : "");
        txtPrecio4.setText(dto.precio4() != null ? dto.precio4().toPlainString() : "");

        if (dto.nombreSeccion() != null && cboSeccion.getItems().contains(dto.nombreSeccion())) {
            cboSeccion.getSelectionModel().select(dto.nombreSeccion());
        }
        if (dto.tiempoProcesamiento() != null) {
            String t = String.valueOf(dto.tiempoProcesamiento());
            if (cboTiempo.getItems().contains(t)) cboTiempo.getSelectionModel().select(t);
        }
        if (dto.tipoInforme() != null) {
            String etiqueta = codigoAEtiqueta(dto.tipoInforme());
            cboTipoInforme.getSelectionModel().select(etiqueta);
        }

        boolean deriva = dto.estadoDeriva() != null && dto.estadoDeriva() == 1;
        tglDeriva.setSelected(deriva);
        cboDerivacion.setDisable(!deriva);
        if (deriva && dto.nombreDerivacion() != null) {
            cboDerivacion.getSelectionModel().select(dto.nombreDerivacion());
        }
        mensajeLabel.setText("");
    }

    private Integer idSeccionSeleccionada() {
        String nombre = cboSeccion.getValue();
        if (nombre == null) return null;
        return todasLasSecciones.stream()
                .filter(s -> nombre.equals(s.nombre()))
                .map(SeccionDto::id)
                .findFirst().orElse(null);
    }

    private Integer idDerivacionSeleccionada() {
        if (!tglDeriva.isSelected()) return null;
        String nombre = cboDerivacion.getValue();
        if (nombre == null) return null;
        return todasLasDerivaciones.stream()
                .filter(d -> nombre.equals(d.nombre()))
                .map(DerivacionDto::id)
                .findFirst().orElse(null);
    }

    private Integer parseCodigo(String v) {
        try { return (v == null || v.isBlank()) ? null : Integer.parseInt(v.trim()); }
        catch (NumberFormatException ex) { return null; }
    }

    private Integer parseTiempo() {
        String v = cboTiempo.getValue();
        try { return v != null ? Integer.parseInt(v.trim()) : 1; }
        catch (NumberFormatException ex) { return 1; }
    }

    private String parseTipoInforme() {
        String etiqueta = cboTipoInforme.getValue();
        return etiqueta != null ? TIPOS.getOrDefault(etiqueta, "FILAS") : "FILAS";
    }

    private String codigoAEtiqueta(String codigo) {
        return TIPOS.entrySet().stream()
                .filter(e -> e.getValue().equals(codigo))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(TIPOS.keySet().iterator().next());
    }

    private BigDecimal parsePrecio(String v) {
        try { return (v == null || v.isBlank()) ? BigDecimal.ZERO : new BigDecimal(v.trim().replace(",", ".")); }
        catch (NumberFormatException ex) { return BigDecimal.ZERO; }
    }
}
