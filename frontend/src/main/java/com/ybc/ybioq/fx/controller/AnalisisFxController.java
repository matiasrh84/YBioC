package com.ybc.ybioq.fx.controller;

import com.ybc.ybioq.fx.client.*;
import com.ybc.ybioq.fx.client.dto.*;
import com.ybc.ybioq.fx.navigation.FxNavigationService;
import com.ybc.ybioq.fx.util.AutocompleteTextField;
import com.ybc.ybioq.fx.util.ToggleSwitchControl;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AnalisisFxController {

    private static final Map<String, String> TIPOS = new LinkedHashMap<>();

    static {
        TIPOS.put("Análisis estándar", "FILAS");
        TIPOS.put("Dos columnas", "COLUMNAS");
        TIPOS.put("Proteinograma", "PROTEINOGRAMA");
        TIPOS.put("Cultivo / Bacteriológico", "CULTIVO");
        TIPOS.put("Hemograma fijo", "HEMOGRAMA_FIJO");
        TIPOS.put("Hemograma en filas", "HEMOGRAMA_FILAS");
    }

    private final AnalisisClient analisisClient;
    private final DerivacionClient derivacionClient;
    private final MetodoClient metodoClient;
    private final TituloClient tituloClient;
    private final UnidadClient unidadClient;
    private final PracticaClient practicaClient;
    private final SeccionClient seccionClient;
    private final FxNavigationService navigationService;

    private final ObservableList<PrioridadItem> prioridades = FXCollections.observableArrayList();

    // Estado de selección
    private Integer selectedPracticaId = null;
    private Integer selectedMetodoId = null;
    private Integer selectedTituloId = null;

    // Modo edición vs. creación en el panel Parámetros
    private boolean modoEdicion = false;
    private PrioridadItem itemEditando = null;

    // Catálogos cargados una vez al init
    private List<PracticaDto> todasLasPracticas = Collections.emptyList();
    private List<MetodoDto> todosLosMetodos = Collections.emptyList();
    private List<TituloDto> todosLosTitulos = Collections.emptyList();
    private List<SeccionDto> todasLasSecciones = Collections.emptyList();
    private List<DerivacionDto> todasLasDerivaciones = Collections.emptyList();

    // DTO completo de la práctica actualmente cargada
    private PracticaDto practicaSeleccionadaDto = null;

    // ── campos FXML ───────────────────────────────────────────────

    @FXML
    private Button btnGeneral;
    @FXML
    private Button btnAnalisis;
    @FXML
    private Button btnInforme;
    @FXML
    private VBox panelHint;
    @FXML
    private TextField txtPractica;
    @FXML
    private Label lblPractica;
    @FXML
    private VBox panelGeneral;
    @FXML
    private VBox panelAnalisis;
    @FXML
    private VBox panelInforme;
    // Panel General
    @FXML
    private TextArea txtInstrucciones;
    @FXML
    private ComboBox<String> cboSeccion;
    @FXML
    private ComboBox<String> cboTipo;
    @FXML
    private ComboBox<String> cboTiempoProcesamiento;
    @FXML
    private ToggleSwitchControl swDerivacion;
    @FXML
    private ComboBox<String> cboDerivaciones;
    @FXML
    private TableView<PrioridadItem> tablaPioridadPractica;
    @FXML
    private TableColumn<PrioridadItem, String> colNombrePrioridad;
    @FXML
    private TableColumn<PrioridadItem, String> colValorPrioridad;
    // Panel Análisis
    @FXML
    private Label lblParametroActual;
    @FXML
    private Label lblPrioridadGeneral;
    @FXML
    private ToggleSwitchControl chkUnSoloMetodo;
    @FXML
    private TextField txtMetodo;
    @FXML
    private TextField txtTitulo;
    @FXML
    private TextField txtFormatoMesada;
    @FXML
    private TextField txtNombreParametro;
    @FXML
    private TextField txtUnidad;
    @FXML
    private RadioButton radionum;
    @FXML
    private RadioButton radioposneg;
    @FXML
    private RadioButton radioposnegnum;
    @FXML
    private ComboBox<String> cbounidad;
    @FXML
    private ComboBox<String> cboPosneg;
    @FXML
    private ComboBox<String> cboTexto;
    @FXML
    private ComboBox<String> cboUnidadPositivoNegativo;
    @FXML
    private TextField txtPositivoNegativoNumerico;
    @FXML
    private ToggleSwitchControl chkFormula;
    @FXML
    private Button btnCargarFormula;
    @FXML
    private TextField txtformula;
    @FXML
    private ComboBox<String> cboSexo4;
    @FXML
    private TextArea txtValoresReferencia;
    @FXML
    private Button btnGuardar;
    @FXML
    private Label mensajeLabel;
    // Panel Informe
    @FXML
    private Label lblPracticaInforme;
    @FXML
    private Label lblSeccionInforme;
    @FXML
    private TreeView<String> treePrioridades;
    @FXML
    private TableView<PrioridadItem> tablaPrioridadesInforme;
    @FXML
    private TableColumn<PrioridadItem, String> colNombrePrioridadInforme;
    @FXML
    private TableColumn<PrioridadItem, String> colPrioridadInforme;
    @FXML
    private TextArea txtPreviewInforme;

    public AnalisisFxController(AnalisisClient analisisClient,
                                DerivacionClient derivacionClient,
                                MetodoClient metodoClient,
                                TituloClient tituloClient,
                                UnidadClient unidadClient,
                                PracticaClient practicaClient,
                                SeccionClient seccionClient,
                                FxNavigationService navigationService) {
        this.analisisClient = analisisClient;
        this.derivacionClient = derivacionClient;
        this.metodoClient = metodoClient;
        this.tituloClient = tituloClient;
        this.unidadClient = unidadClient;
        this.practicaClient = practicaClient;
        this.seccionClient = seccionClient;
        this.navigationService = navigationService;
    }

    @FXML
    public void initialize() {
        configurarCombos();
        cargarComboUnidades();
        configurarTablaPrioridades();
        configurarEstadosIniciales();
        configurarTipoResultadoInicial();
        cargarCatalogosYConfigurarAutocompletar();
        resetearFormulario();
        mostrarGeneral();
    }

    // ── autocompletado ────────────────────────────────────────────

    private void cargarCatalogosYConfigurarAutocompletar() {
        try {
            todasLasPracticas = practicaClient.findAll();
        } catch (Exception ex) { /* backend no disponible */ }
        try {
            todosLosMetodos = metodoClient.findAll();
        } catch (Exception ex) {
        }
        try {
            todosLosTitulos = tituloClient.findAll();
        } catch (Exception ex) {
        }

        // Práctica: formato "CODIGO - NOMBRE", busca por código o nombre
        AutocompleteTextField.configurar(
                txtPractica,
                todasLasPracticas,
                p -> {
                    String cod = p.codigoPractica() != null ? String.valueOf(p.codigoPractica()) : "";
                    String nom = p.determinacion() != null ? p.determinacion() : "";
                    return cod + " - " + nom;
                },
                this::seleccionarPractica);

        // Método: guarda el ID para usarlo al guardar cada análisis
        AutocompleteTextField.configurar(
                txtMetodo,
                todosLosMetodos,
                m -> m.nombre() != null ? m.nombre() : "",
                m -> {
                    selectedMetodoId = m.id();
                    mensajeLabel.setText("Metodo: " + m.nombre());
                });

        // Título: guarda el ID para usarlo al guardar
        AutocompleteTextField.configurar(
                txtTitulo,
                todosLosTitulos,
                t -> t.nombre() != null ? t.nombre() : "",
                t -> {
                    selectedTituloId = t.id();
                    mensajeLabel.setText("Titulo: " + t.nombre());
                });
    }

    private void seleccionarPractica(PracticaDto p) {
        selectedPracticaId = p.id();
        lblPractica.setText(p.determinacion());
        lblPracticaInforme.setText(p.determinacion());
        txtPractica.clear();
        habilitarNavegacion(true);
        cargarAnalisisDesdeBd();
        try {
            practicaSeleccionadaDto = practicaClient.findById(p.id());
            poblarPanelGeneral(practicaSeleccionadaDto);
        } catch (RuntimeException ex) {
            practicaSeleccionadaDto = p;
        }
        mostrarGeneral();
        mensajeLabel.setText("Practica: " + p.determinacion());
    }

    // Fallback: el usuario escribe en txtPractica y presiona Enter
    @FXML
    private void actualizarPractica() {
        String q = text(txtPractica).toLowerCase();
        if (q.isBlank()) return;
        todasLasPracticas.stream()
                .filter(p -> {
                    String cod = p.codigoPractica() != null ? String.valueOf(p.codigoPractica()) : "";
                    String nom = p.determinacion() != null ? p.determinacion().toLowerCase() : "";
                    return cod.contains(q) || nom.contains(q);
                })
                .min(java.util.Comparator.comparingInt(p -> {
                    String cod = p.codigoPractica() != null ? String.valueOf(p.codigoPractica()) : "";
                    if (cod.endsWith(q)) return 0;
                    if (cod.startsWith(q)) return 1;
                    if (cod.contains(q)) return 2;
                    return 3;
                }))
                .ifPresentOrElse(
                        this::seleccionarPractica,
                        () -> mensajeLabel.setText("Práctica no encontrada: " + text(txtPractica)));
    }

    @FXML
    private void accionEstadoDerivacion() {
        actualizarEstadoDerivacion();
    }

    @FXML
    private void nuevoParametro() {
        limpiarCampos();
        mostrarAnalisis();
    }

    @FXML
    private void editarParametroSeleccionado() {
        PrioridadItem sel = tablaPioridadPractica.getSelectionModel().getSelectedItem();
        if (sel == null) {
            mensajeLabel.setText("Seleccione un parámetro para editar.");
            return;
        }
        modoEdicion = true;
        itemEditando = sel;
        poblarCamposDesdeItem(sel);
        mostrarAnalisis();
    }

    /**
     * Solo actualiza los labels del header del panel Análisis — sin tocar campos con autocomplete.
     */
    private void actualizarHeaderAnalisis(PrioridadItem item) {
        if (lblParametroActual != null) lblParametroActual.setText(item.getNombre());
        if (lblPrioridadGeneral != null) lblPrioridadGeneral.setText("Prioridad: " + item.getPrioridad());
    }

    @FXML
    private void guardarTodo() {
        guardar();
        cerrarVentana();
    }

    // Recarga los catálogos de método y título después de abrir esos formularios
    private void refrescarCatalogoMetodos() {
        try {
            todosLosMetodos = metodoClient.findAll();
        } catch (Exception ex) {
        }
    }

    private void refrescarCatalogoTitulos() {
        try {
            todosLosTitulos = tituloClient.findAll();
        } catch (Exception ex) {
        }
    }

    // ── carga desde BD ────────────────────────────────────────────

    private void cargarAnalisisDesdeBd() {
        if (selectedPracticaId == null) return;
        try {
            List<AnalisisDto> lista = analisisClient.findByPractica(selectedPracticaId);
            prioridades.clear();
            for (AnalisisDto a : lista) {
                prioridades.add(new PrioridadItem(
                        a.id(), a.nombre(),
                        a.prioridad() != null ? String.valueOf(a.prioridad()) : "-",
                        a.idMetodo(), a.nombreMetodo(),
                        a.idTitulo(), a.nombreTitulo(),
                        a.tipoResultado(), a.unidad(), a.valoresReferencia(),
                        a.codigoInterno(), a.unidadExtra()));
            }
            actualizarArboles();
            refrescarDatosInforme();
        } catch (RuntimeException ex) {
            mensajeLabel.setText("Error al cargar analisis: " + ex.getMessage());
        }
    }

    // ── navegación de paneles ─────────────────────────────────────

    @FXML
    private void mostrarGeneral() {
        mostrarPanel(panelGeneral);
        resaltarActivo(btnGeneral);
    }

    @FXML
    private void mostrarAnalisis() {
        mostrarPanel(panelAnalisis);
        resaltarActivo(btnAnalisis);
    }

    @FXML
    private void mostrarInforme() {
        refrescarDatosInforme();
        mostrarPanel(panelInforme);
        resaltarActivo(btnInforme);
    }

    // ── acciones de catálogos relacionados ────────────────────────

    @FXML
    private void accionDerivaciones() {
        navigationService.showDerivaciones();
        cargarComboDerivaciones();
        mensajeLabel.setText("Derivaciones actualizadas.");
    }

    @FXML
    private void accionSecciones() {
        navigationService.showSecciones();
        cargarComboSecciones();
        mensajeLabel.setText("Catalogo de secciones actualizado.");
    }

    @FXML
    private void accionMetodo() {
        navigationService.showMetodos();
        refrescarCatalogoMetodos();
        txtMetodo.requestFocus();
        mensajeLabel.setText("Catalogo de metodos actualizado.");
    }

    @FXML
    private void accionTitulo() {
        navigationService.showTitulos();
        refrescarCatalogoTitulos();
        txtTitulo.requestFocus();
        mensajeLabel.setText("Catalogo de titulos actualizado.");
    }

    @FXML
    private void actualizarInformacion() {
        cargarAnalisisDesdeBd();
        refrescarDatosInforme();
        mensajeLabel.setText("Informacion actualizada.");
    }

    // ── tipo de resultado ─────────────────────────────────────────

    @FXML
    private void seleccionarResultadoNumerico() {
        cbounidad.setDisable(false);
        cboPosneg.setDisable(true);
        txtPositivoNegativoNumerico.setDisable(true);
        cboUnidadPositivoNegativo.setDisable(true);
        txtPositivoNegativoNumerico.clear();
        txtformula.clear();
        btnCargarFormulaSetDisabled(true);
    }

    @FXML
    private void seleccionarResultadoPosNeg() {
        cbounidad.setDisable(true);
        cboPosneg.setDisable(false);
        txtPositivoNegativoNumerico.setDisable(true);
        cboUnidadPositivoNegativo.setDisable(true);
        txtPositivoNegativoNumerico.clear();
        txtformula.clear();
        btnCargarFormulaSetDisabled(true);
    }

    @FXML
    private void seleccionarResultadoPosNegNumerico() {
        cbounidad.setDisable(true);
        cboPosneg.setDisable(true);
        txtPositivoNegativoNumerico.setDisable(false);
        cboUnidadPositivoNegativo.setDisable(false);
        if (!cboUnidadPositivoNegativo.getItems().isEmpty()) {
            cboUnidadPositivoNegativo.getSelectionModel().selectFirst();
        }
        txtPositivoNegativoNumerico.clear();
        txtformula.clear();
        btnCargarFormulaSetDisabled(true);
    }

    @FXML
    private void toggleFormula() {
        // Listener registrado en configurarEstadosIniciales — este método queda como fallback
        boolean sel = chkFormula.isSelected();
        if (!sel) txtformula.clear();
        btnCargarFormulaSetDisabled(!sel);
    }

    @FXML
    private void cargarFormula() {
        TextInputDialog dialog = new TextInputDialog(txtformula.getText());
        dialog.initOwner(ventana());
        dialog.setTitle("Cargar formula");
        dialog.setHeaderText("Defina la formula del resultado");
        dialog.setContentText("Formula:");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(v -> {
            txtformula.setText(v.trim());
            mensajeLabel.setText("Formula cargada.");
        });
    }

    @FXML
    private void abrirUnidades() {
        navigationService.showUnidades();
        cargarComboUnidades();
        mensajeLabel.setText("Unidades actualizadas.");
    }

    @FXML
    private void editarResultados() {
        txtValoresReferencia.requestFocus();
    }

    @FXML
    private void agregarTipoResultado() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.initOwner(ventana());
        dialog.setTitle("Tipo de Resultado");
        dialog.setHeaderText("Agregar nuevo tipo de resultado");
        dialog.setContentText("Nombre:");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(v -> {
            String nuevo = v.trim();
            if (nuevo.isBlank()) return;
            if (!cboTexto.getItems().contains(nuevo)) cboTexto.getItems().add(nuevo);
            cboTexto.getSelectionModel().select(nuevo);
        });
    }

    // ── parámetros / prioridades ──────────────────────────────────

    /**
     * Vuelca los campos del formulario al item que se está editando. No hace nada si no hay edición activa.
     */
    private boolean commitarEdicionActual() {
        if (!modoEdicion || itemEditando == null) return false;
        String nombre = text(txtNombreParametro);
        if (!nombre.isBlank()) itemEditando.setNombre(nombre);
        itemEditando.setIdMetodo(selectedMetodoId);
        itemEditando.setNombreMetodo(text(txtMetodo));
        itemEditando.setIdTitulo(selectedTituloId);
        itemEditando.setNombreTitulo(text(txtTitulo));
        itemEditando.setTipoResultado(tipoResultadoActual());
        itemEditando.setUnidad(unidadResultadoSeleccionada());
        itemEditando.setValoresReferencia(textArea(txtValoresReferencia));
        itemEditando.setCodigoInterno(text(txtFormatoMesada));
        itemEditando.setUnidadExtra(chkFormula.isSelected() ? text(txtformula) : "");
        modoEdicion = false;
        itemEditando = null;
        tablaPioridadPractica.refresh();
        tablaPrioridadesInforme.refresh();
        actualizarArboles();
        return true;
    }

    @FXML
    private void agregarParametro() {
        if (modoEdicion && itemEditando != null) {
            commitarEdicionActual();
            mensajeLabel.setText("Parametro actualizado.");
        } else {
            // Modo creación: crear nuevo item y agregar a la lista
            String nombre = text(txtNombreParametro);
            if (nombre.isBlank()) nombre = "Nuevo parametro";
            PrioridadItem item = new PrioridadItem(null, nombre,
                    String.valueOf(prioridades.size() + 1),
                    selectedMetodoId, text(txtMetodo),
                    selectedTituloId, text(txtTitulo),
                    tipoResultadoActual(), unidadResultadoSeleccionada(),
                    textArea(txtValoresReferencia), text(txtFormatoMesada),
                    chkFormula.isSelected() ? text(txtformula) : "");
            prioridades.add(item);
            actualizarArboles();
            refrescarDatosInforme();
            tablaPioridadPractica.getSelectionModel().select(item);
            mensajeLabel.setText("Parametro agregado.");
        }
    }

    @FXML
    private void borrarParametro() {
        PrioridadItem sel = prioridadSeleccionada();
        if (sel == null) {
            mensajeLabel.setText("Seleccione un parametro para borrar.");
            return;
        }
        if (sel.getId() != null) {
            try {
                analisisClient.delete(sel.getId());
            } catch (RuntimeException ex) {
                mensajeLabel.setText("Error al borrar: " + ex.getMessage());
                return;
            }
        }
        prioridades.remove(sel);
        renumerarPrioridades();
        actualizarArboles();
        refrescarDatosInforme();
        mensajeLabel.setText("Parametro eliminado.");
    }

    @FXML
    private void limpiarCampos() {
        modoEdicion = false;
        itemEditando = null;
        txtNombreParametro.clear();
        txtUnidad.clear();
        txtValoresReferencia.clear();
        txtMetodo.clear();
        txtTitulo.clear();
        txtFormatoMesada.clear();
        txtPositivoNegativoNumerico.clear();
        txtformula.clear();
        selectedMetodoId = null;
        selectedTituloId = null;
        if (lblParametroActual != null) lblParametroActual.setText("(nuevo)");
        if (lblPrioridadGeneral != null) lblPrioridadGeneral.setText("");
        chkFormula.setSelected(false);
        if (!cboPosneg.getItems().isEmpty()) cboPosneg.getSelectionModel().selectFirst();
        if (!cboTexto.getItems().isEmpty()) cboTexto.getSelectionModel().selectFirst();
        if (!cboUnidadPositivoNegativo.getItems().isEmpty())
            cboUnidadPositivoNegativo.getSelectionModel().selectFirst();
        if (radionum != null) radionum.setSelected(true);
        seleccionarResultadoNumerico();
        txtPreviewInforme.clear();
        if (!cboSexo4.getItems().isEmpty()) cboSexo4.getSelectionModel().selectFirst();
        mensajeLabel.setText("Campos limpiados.");
    }

    @FXML
    private void subirPrioridad() {
        moverPrioridad(-1);
    }

    @FXML
    private void bajarPrioridad() {
        moverPrioridad(1);
    }

    // ── informe ───────────────────────────────────────────────────

    @FXML
    private void modificarPrioridadInforme() {
        if (prioridades.isEmpty()) {
            mensajeLabel.setText("No hay prioridades.");
            return;
        }
        prioridades.sort(Comparator.comparingInt(i -> parseInt(i.getPrioridad(), Integer.MAX_VALUE)));
        renumerarPrioridades();
        actualizarArboles();
        refrescarDatosInforme();
        mensajeLabel.setText("Prioridad actualizada.");
    }

    @FXML
    private void previsualizarInforme() {
        StringBuilder sb = new StringBuilder();
        sb.append("Practica: ").append(practicaActual()).append('\n');
        sb.append("Seccion: ").append(value(cboSeccion)).append('\n');
        sb.append("Tipo: ").append(value(cboTipo)).append('\n');
        sb.append("Tiempo: ").append(value(cboTiempoProcesamiento)).append(" dias\n");
        sb.append("Derivacion: ").append(swDerivacion.isSelected() ? value(cboDerivaciones) : "No").append('\n');
        sb.append("Tipo resultado: ").append(tipoResultadoActual()).append('\n');
        String unidad = unidadResultadoSeleccionada();
        if (!unidad.isBlank()) sb.append("Unidad: ").append(unidad).append('\n');
        sb.append('\n').append("Parametros:\n");
        for (PrioridadItem p : prioridades) {
            sb.append("  ").append(p.getPrioridad()).append(". ").append(p.getNombre()).append('\n');
        }
        txtPreviewInforme.setText(sb.toString());
        mensajeLabel.setText("Previsualizacion generada.");
    }

    // ── guardar ───────────────────────────────────────────────────

    @FXML
    private void guardar() {
        if (selectedPracticaId == null) {
            mensajeLabel.setText("Seleccione una practica antes de guardar.");
            return;
        }
        commitarEdicionActual(); // vuelca el formulario al item en curso antes de persistir
        if (prioridades.isEmpty()) {
            mensajeLabel.setText("Agregue al menos un parametro.");
            return;
        }
        try {
            int guardados = 0;
            for (PrioridadItem item : prioridades) {
                AnalisisDto dto = new AnalisisDto(
                        item.getId(),
                        item.getNombre(),
                        item.getCodigoInterno() != null ? item.getCodigoInterno() : "",
                        item.getTipoResultado() != null ? item.getTipoResultado() : tipoResultadoActual(),
                        item.getUnidad() != null ? item.getUnidad() : "",
                        item.getUnidadExtra() != null ? item.getUnidadExtra() : "",
                        item.getValoresReferencia() != null ? item.getValoresReferencia() : "",
                        parseInt(item.getPrioridad(), null),
                        false,
                        true,
                        selectedPracticaId,
                        item.getIdMetodo(),
                        null,
                        item.getIdTitulo(),
                        null
                );
                if (dto.id() == null) {
                    analisisClient.save(dto);
                } else {
                    analisisClient.update(dto.id(), dto);
                }
                guardados++;
            }
            cargarAnalisisDesdeBd();
            guardarPractica();
            mensajeLabel.setText(guardados + " analisis guardados.");
        } catch (RuntimeException ex) {
            mensajeLabel.setText("Error al guardar: " + ex.getMessage());
        }
    }

    @FXML
    private void finalizarProceso() {
        resetearFormulario();
        mostrarGeneral();
        mensajeLabel.setText("Formulario reiniciado.");
    }

    @FXML
    private void cerrarVentana() {
        Stage stage = (Stage) lblPractica.getScene().getWindow();
        if (stage != null) stage.close();
    }

    // ── helpers privados ──────────────────────────────────────────

    private void configurarCombos() {
        cargarComboSecciones();
        cboTipo.getItems().setAll(TIPOS.keySet());
        cboTiempoProcesamiento.getItems().setAll("1", "2", "3", "4", "5", "6", "7");
        cboPosneg.getItems().setAll("Positivo", "Negativo", "No Contiene");
        cboTexto.getItems().setAll("Texto libre", "Resultado cualitativo", "Resultado semicuantitativo");
        cboUnidadPositivoNegativo.getItems().setAll("...", "mg/dl", "UI/L", "%");
        cboSexo4.getItems().setAll("Ambos", "Masculino", "Femenino");
        cboSeccion.getSelectionModel().selectFirst();
        cboTipo.getSelectionModel().selectFirst();
        cboTiempoProcesamiento.getSelectionModel().selectFirst();
        cboPosneg.getSelectionModel().selectFirst();
        cboTexto.getSelectionModel().selectFirst();
        cboUnidadPositivoNegativo.getSelectionModel().selectFirst();
        cboSexo4.getSelectionModel().selectFirst();
    }

    private void cargarComboSecciones() {
        String actual = value(cboSeccion);
        try {
            todasLasSecciones = seccionClient.findAll();
            cboSeccion.getItems().setAll(todasLasSecciones.stream()
                    .map(SeccionDto::nombre).toList());
        } catch (RuntimeException ex) {
            todasLasSecciones = Collections.emptyList();
            cboSeccion.getItems().setAll("BIOQUIMICA GENERAL", "HEMATOLOGIA", "HORMONAS", "BACTERIOLOGIA", "INMUNOLOGIA");
        }
        if (!actual.isBlank() && cboSeccion.getItems().contains(actual)) {
            cboSeccion.getSelectionModel().select(actual);
        } else if (!cboSeccion.getItems().isEmpty()) {
            cboSeccion.getSelectionModel().selectFirst();
        }
    }

    private void cargarComboDerivaciones() {
        String actual = value(cboDerivaciones);
        try {
            todasLasDerivaciones = derivacionClient.findAll();
            cboDerivaciones.getItems().setAll(todasLasDerivaciones.stream()
                    .map(DerivacionDto::nombre)
                    .filter(n -> n != null && !n.isBlank())
                    .sorted(String::compareToIgnoreCase)
                    .toList());
        } catch (RuntimeException ex) {
            todasLasDerivaciones = Collections.emptyList();
            cboDerivaciones.getItems().clear();
        }
        if (!actual.isBlank() && cboDerivaciones.getItems().contains(actual)) {
            cboDerivaciones.getSelectionModel().select(actual);
        } else if (!cboDerivaciones.getItems().isEmpty()) {
            cboDerivaciones.getSelectionModel().selectFirst();
        }
    }

    private void cargarComboUnidades() {
        try {
            cbounidad.getItems().setAll(
                    unidadClient.findAll().stream()
                            .filter(UnidadDto::estado)
                            .map(UnidadDto::nombre)
                            .sorted(String::compareToIgnoreCase)
                            .toList());
        } catch (RuntimeException ex) {
            cbounidad.getItems().setAll("mg/dl", "UI/L", "%");
        }
        if (!cbounidad.getItems().isEmpty()) cbounidad.getSelectionModel().selectFirst();
    }

    private void configurarEstadosIniciales() {
        cargarComboDerivaciones();
        swDerivacion.selectedProperty().addListener((obs, o, n) -> actualizarEstadoDerivacion());
        chkFormula.selectedProperty().addListener((obs, o, n) -> {
            if (!n) txtformula.clear();
            btnCargarFormulaSetDisabled(!n);
        });
        actualizarEstadoDerivacion();
        cboSeccion.valueProperty().addListener((obs, o, n) -> lblSeccionInforme.setText(value(cboSeccion)));
    }

    private void configurarTipoResultadoInicial() {
        if (radionum != null) radionum.setSelected(true);
        if (chkFormula != null) chkFormula.setSelected(false);
        seleccionarResultadoNumerico();
    }

    private void actualizarEstadoDerivacion() {
        cboDerivaciones.setDisable(!swDerivacion.isSelected());
    }

    private void btnCargarFormulaSetDisabled(boolean disabled) {
        if (btnCargarFormula != null) btnCargarFormula.setDisable(disabled);
    }

    private void configurarTablaPrioridades() {
        colNombrePrioridad.setCellValueFactory(d -> new ReadOnlyStringWrapper(d.getValue().getNombre()));
        colValorPrioridad.setCellValueFactory(d -> new ReadOnlyStringWrapper(d.getValue().getPrioridad()));
        tablaPioridadPractica.setItems(prioridades);
        // Columna fija de prioridad; la de nombre se enlaza al espacio restante → sin columna fantasma
        colValorPrioridad.setMinWidth(90);
        colValorPrioridad.setMaxWidth(90);
        colValorPrioridad.setPrefWidth(90);
        colNombrePrioridad.prefWidthProperty().bind(
                tablaPioridadPractica.widthProperty().subtract(92));
        tablaPioridadPractica.getSelectionModel().selectedItemProperty().addListener((obs, o, sel) -> {
            if (sel != null) actualizarHeaderAnalisis(sel);
        });

        colNombrePrioridadInforme.setCellValueFactory(d -> new ReadOnlyStringWrapper(d.getValue().getNombre()));
        colPrioridadInforme.setCellValueFactory(d -> new ReadOnlyStringWrapper(d.getValue().getPrioridad()));
        tablaPrioridadesInforme.setItems(prioridades);
        tablaPrioridadesInforme.setEditable(true);
        tablaPrioridadesInforme.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        colPrioridadInforme.setCellFactory(TextFieldTableCell.forTableColumn());
        colPrioridadInforme.setOnEditCommit(e -> e.getRowValue().setPrioridad(e.getNewValue()));
    }

    private void poblarPanelGeneral(PracticaDto p) {
        txtInstrucciones.setText(p.instrucciones() != null ? p.instrucciones() : "");

        if (p.nombreSeccion() != null && cboSeccion.getItems().contains(p.nombreSeccion())) {
            cboSeccion.getSelectionModel().select(p.nombreSeccion());
        }
        if (p.tipoInforme() != null) {
            String etiqueta = codigoAEtiqueta(p.tipoInforme());
            cboTipo.getSelectionModel().select(etiqueta);
        }
        if (p.tiempoProcesamiento() != null) {
            String t = String.valueOf(p.tiempoProcesamiento());
            if (cboTiempoProcesamiento.getItems().contains(t)) cboTiempoProcesamiento.getSelectionModel().select(t);
        }
        boolean deriva = p.estadoDeriva() != null && p.estadoDeriva() == 1;
        swDerivacion.setSelected(deriva);
        actualizarEstadoDerivacion();
        if (deriva && p.nombreDerivacion() != null) {
            cboDerivaciones.getSelectionModel().select(p.nombreDerivacion());
        }
    }

    private void guardarPractica() {
        if (selectedPracticaId == null || practicaSeleccionadaDto == null) return;
        try {
            PracticaDto dto = new PracticaDto(
                    selectedPracticaId,
                    practicaSeleccionadaDto.codigoPractica(),
                    practicaSeleccionadaDto.determinacion(),
                    textArea(txtInstrucciones),
                    idSeccionActual(),
                    null,
                    swDerivacion.isSelected() ? 1 : 0,
                    idDerivacionActual(),
                    null,
                    practicaSeleccionadaDto.prioridad(),
                    parseTiempoProcesamiento(),
                    parseTipoInforme(),
                    practicaSeleccionadaDto.precio1(),
                    practicaSeleccionadaDto.precio2(),
                    practicaSeleccionadaDto.precio3(),
                    practicaSeleccionadaDto.precio4()
            );
            practicaClient.update(selectedPracticaId, dto);
        } catch (RuntimeException ex) {
            mensajeLabel.setText("Advertencia al guardar datos generales: " + ex.getMessage());
        }
    }

    private Integer idSeccionActual() {
        String nombre = value(cboSeccion);
        return todasLasSecciones.stream()
                .filter(s -> nombre.equals(s.nombre()))
                .map(SeccionDto::id)
                .findFirst().orElse(null);
    }

    private Integer idDerivacionActual() {
        if (!swDerivacion.isSelected()) return null;
        String nombre = value(cboDerivaciones);
        return todasLasDerivaciones.stream()
                .filter(d -> nombre.equals(d.nombre()))
                .map(DerivacionDto::id)
                .findFirst().orElse(null);
    }

    private Integer parseTiempoProcesamiento() {
        try {
            return Integer.parseInt(value(cboTiempoProcesamiento));
        } catch (NumberFormatException ex) {
            return 1;
        }
    }

    private String parseTipoInforme() {
        String etiqueta = cboTipo.getValue();
        return etiqueta != null ? TIPOS.getOrDefault(etiqueta, "FILAS") : "FILAS";
    }

    private String codigoAEtiqueta(String codigo) {
        return TIPOS.entrySet().stream()
                .filter(e -> e.getValue().equals(codigo))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(TIPOS.keySet().iterator().next());
    }

    private void resetearFormulario() {
        selectedPracticaId = null;
        selectedMetodoId = null;
        selectedTituloId = null;
        practicaSeleccionadaDto = null;
        prioridades.clear();
        lblPractica.setText("-");
        lblPracticaInforme.setText("-");
        lblSeccionInforme.setText(value(cboSeccion));
        lblPrioridadGeneral.setText("-");
        mensajeLabel.setText("");
        habilitarNavegacion(false);
        actualizarArboles();
        mostrarPanel(panelHint);
    }

    private void refrescarDatosInforme() {
        lblPracticaInforme.setText(practicaActual());
        lblSeccionInforme.setText(value(cboSeccion));
        PrioridadItem sel = prioridadSeleccionada();
        tablaPrioridadesInforme.refresh();
    }

    private void actualizarArboles() {
        TreeItem<String> root = new TreeItem<>("Prioridades — " + practicaActual());
        root.setExpanded(true);
        prioridades.forEach(i -> root.getChildren().add(
                new TreeItem<>(i.getPrioridad() + " — " + i.getNombre())));
        treePrioridades.setRoot(root);
    }

    private void moverPrioridad(int delta) {
        PrioridadItem sel = prioridadSeleccionada();
        if (sel == null) {
            mensajeLabel.setText("Seleccione un parametro.");
            return;
        }
        int idx = prioridades.indexOf(sel);
        int target = idx + delta;
        if (target < 0 || target >= prioridades.size()) return;
        prioridades.remove(idx);
        prioridades.add(target, sel);
        renumerarPrioridades();
        tablaPioridadPractica.getSelectionModel().select(sel);
        tablaPrioridadesInforme.getSelectionModel().select(sel);
        actualizarArboles();
    }

    private void poblarCamposDesdeItem(PrioridadItem item) {
        lblParametroActual.setText(item.getNombre());
        lblPrioridadGeneral.setText("Prioridad: " + item.getPrioridad());
        txtNombreParametro.setText(item.getNombre());
        txtFormatoMesada.setText(item.getCodigoInterno() != null ? item.getCodigoInterno() : "");

        if (item.getNombreMetodo() != null && !item.getNombreMetodo().isBlank()) {
            txtMetodo.setText(item.getNombreMetodo());
            selectedMetodoId = item.getIdMetodo();
        }
        if (item.getNombreTitulo() != null && !item.getNombreTitulo().isBlank()) {
            txtTitulo.setText(item.getNombreTitulo());
            selectedTituloId = item.getIdTitulo();
        }
        if (item.getValoresReferencia() != null) {
            txtValoresReferencia.setText(item.getValoresReferencia());
        }
        if (item.getUnidad() != null && !item.getUnidad().isBlank()) {
            if (cbounidad.getItems().contains(item.getUnidad())) {
                cbounidad.getSelectionModel().select(item.getUnidad());
            }
        }
        String formula = item.getUnidadExtra();
        boolean tieneFormula = formula != null && !formula.isBlank();
        chkFormula.setSelected(tieneFormula);
        txtformula.setText(tieneFormula ? formula : "");
        btnCargarFormulaSetDisabled(!tieneFormula);

        String tipo = item.getTipoResultado();
        if ("Positivo/Negativo".equals(tipo)) {
            radioposneg.setSelected(true);
            seleccionarResultadoPosNeg();
        } else if ("+/- y Numerico".equals(tipo)) {
            radioposnegnum.setSelected(true);
            seleccionarResultadoPosNegNumerico();
        } else {
            radionum.setSelected(true);
            seleccionarResultadoNumerico();
        }
    }

    private void renumerarPrioridades() {
        for (int i = 0; i < prioridades.size(); i++) prioridades.get(i).setPrioridad(String.valueOf(i + 1));
        tablaPioridadPractica.refresh();
        tablaPrioridadesInforme.refresh();
    }

    private PrioridadItem prioridadSeleccionada() {
        return tablaPioridadPractica.getSelectionModel().getSelectedItem();
    }

    private static final String STYLE_NAV_ACTIVO = "-fx-background-color: #ffffff; -fx-text-fill: #005a84; -fx-font-weight: 700;";
    private static final String STYLE_NAV_INACTIVO = "-fx-background-color: #00394f; -fx-text-fill: #88b8d0; -fx-font-weight: 700;";

    private void habilitarNavegacion(boolean habilitar) {
        btnGeneral.setDisable(!habilitar);
        btnAnalisis.setDisable(!habilitar);
        btnInforme.setDisable(!habilitar);
        if (!habilitar) {
            btnGeneral.setStyle(STYLE_NAV_INACTIVO);
            btnAnalisis.setStyle(STYLE_NAV_INACTIVO);
            btnInforme.setStyle(STYLE_NAV_INACTIVO);
        }
    }

    private void resaltarActivo(Button activo) {
        for (Button b : new Button[]{btnGeneral, btnAnalisis, btnInforme}) {
            b.setStyle(b == activo ? STYLE_NAV_ACTIVO : STYLE_NAV_INACTIVO);
        }
    }

    private javafx.stage.Stage ventana() {
        return (javafx.stage.Stage) lblPractica.getScene().getWindow();
    }

    private void mostrarPanel(Node panelObjetivo) {
        panelHint.setVisible(false);
        panelHint.setManaged(false);
        panelGeneral.setVisible(false);
        panelGeneral.setManaged(false);
        panelAnalisis.setVisible(false);
        panelAnalisis.setManaged(false);
        panelInforme.setVisible(false);
        panelInforme.setManaged(false);
        panelObjetivo.setVisible(true);
        panelObjetivo.setManaged(true);
    }

    private String tipoResultadoActual() {
        if (radionum != null && radionum.isSelected()) return "Numerico";
        if (radioposneg != null && radioposneg.isSelected()) return "Positivo/Negativo";
        if (radioposnegnum != null && radioposnegnum.isSelected()) return "+/- y Numerico";
        return value(cboTexto);
    }

    private String unidadResultadoSeleccionada() {
        if (radionum != null && radionum.isSelected()) return value(cbounidad);
        if (radioposnegnum != null && radioposnegnum.isSelected()) return value(cboUnidadPositivoNegativo);
        return "";
    }

    private String practicaActual() {
        String p = lblPractica.getText();
        return (p == null || p.isBlank() || "-".equals(p)) ? "Sin practica" : p;
    }

    private Integer parseInt(String v, Integer fallback) {
        try {
            return Integer.parseInt(v == null ? "" : v.trim());
        } catch (NumberFormatException ex) {
            return fallback;
        }
    }

    private String text(TextField f) {
        return f.getText() == null ? "" : f.getText().trim();
    }

    private String textArea(TextArea a) {
        return a.getText() == null ? "" : a.getText().trim();
    }

    private String value(ComboBox<String> c) {
        return c.getValue() == null ? "" : c.getValue();
    }

    // ── inner classes ──────────────────────────────────────────────

    private static class PrioridadItem {
        private final Integer id;
        private String nombre;
        private String prioridad;
        private Integer idMetodo;
        private String nombreMetodo;
        private Integer idTitulo;
        private String nombreTitulo;
        private String tipoResultado;
        private String unidad;
        private String valoresReferencia;
        private String codigoInterno;
        private String unidadExtra;

        PrioridadItem(Integer id, String nombre, String prioridad,
                      Integer idMetodo, String nombreMetodo,
                      Integer idTitulo, String nombreTitulo,
                      String tipoResultado, String unidad, String valoresReferencia,
                      String codigoInterno, String unidadExtra) {
            this.id = id;
            this.nombre = nombre;
            this.prioridad = prioridad;
            this.idMetodo = idMetodo;
            this.nombreMetodo = nombreMetodo;
            this.idTitulo = idTitulo;
            this.nombreTitulo = nombreTitulo;
            this.tipoResultado = tipoResultado;
            this.unidad = unidad;
            this.valoresReferencia = valoresReferencia;
            this.codigoInterno = codigoInterno;
            this.unidadExtra = unidadExtra;
        }

        public Integer getId() {
            return id;
        }

        public String getNombre() {
            return nombre;
        }

        public String getPrioridad() {
            return prioridad;
        }

        public Integer getIdMetodo() {
            return idMetodo;
        }

        public String getNombreMetodo() {
            return nombreMetodo;
        }

        public Integer getIdTitulo() {
            return idTitulo;
        }

        public String getNombreTitulo() {
            return nombreTitulo;
        }

        public String getTipoResultado() {
            return tipoResultado;
        }

        public String getUnidad() {
            return unidad;
        }

        public String getValoresReferencia() {
            return valoresReferencia;
        }

        public void setPrioridad(String p) {
            this.prioridad = p;
        }

        public void setNombre(String n) {
            this.nombre = n;
        }

        public void setIdMetodo(Integer id) {
            this.idMetodo = id;
        }

        public void setNombreMetodo(String n) {
            this.nombreMetodo = n;
        }

        public void setIdTitulo(Integer id) {
            this.idTitulo = id;
        }

        public void setNombreTitulo(String n) {
            this.nombreTitulo = n;
        }

        public void setTipoResultado(String t) {
            this.tipoResultado = t;
        }

        public void setUnidad(String u) {
            this.unidad = u;
        }

        public void setValoresReferencia(String v) {
            this.valoresReferencia = v;
        }

        public String getCodigoInterno() {
            return codigoInterno;
        }

        public void setCodigoInterno(String c) {
            this.codigoInterno = c;
        }

        public String getUnidadExtra() {
            return unidadExtra;
        }

        public void setUnidadExtra(String u) {
            this.unidadExtra = u;
        }
    }

}
