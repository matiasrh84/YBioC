package com.ybc.ybioq.fx.controller;

import com.ybc.ybioq.fx.client.*;
import com.ybc.ybioq.fx.client.dto.*;
import com.ybc.ybioq.fx.navigation.FxNavigationService;
import com.ybc.ybioq.fx.session.SessionContext;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PrincipalFxController {

    private final FxNavigationService navigationService;
    private final OrdenClient ordenClient;
    private final PacienteClient pacienteClient;
    private final RecepcionClient recepcionClient;
    private final ObraSocialClient obraSocialClient;
    private final SessionContext sessionContext;
    private final ObservableList<ObraSocialDto> todasObrasSociales = FXCollections.observableArrayList();


    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private static final int PAGE_SIZE = 30;

    // ── Paneles ───────────────────────────────────────────────────
    @FXML
    private ScrollPane panelRecepcion;
    @FXML
    private ScrollPane panelOrdenes;
    @FXML
    private ScrollPane panelResultados;
    @FXML
    private ScrollPane panelFacturacion;

    // ── Menú lateral ──────────────────────────────────────────────
    @FXML
    private Button btnMenuRecepcion;
    @FXML
    private Button btnMenuOrdenes;
    @FXML
    private Button btnMenuResultados;
    @FXML
    private Button btnMenuFacturacion;
    @FXML
    private Button btnSalir;

    // ── Panel Recepción ───────────────────────────────────────────
    @FXML
    private TextField txtBuscarRecepcion;
    @FXML
    private ComboBox<ObraSocialDto> cboFiltroRecepcionOs;
    @FXML
    private TableView<RecepcionFilaDto> tablaRecepcion;
    @FXML
    private TableColumn<RecepcionFilaDto, String> colRecepcionTipo;
    @FXML
    private TableColumn<RecepcionFilaDto, String> colRecepcionDni;
    @FXML
    private TableColumn<RecepcionFilaDto, String> colRecepcionNombre;
    @FXML
    private Label lblInfoRecepcion;
    @FXML
    private Button btnRecepcionCrearPersona;
    @FXML
    private Button btnRecepcionDarDeAlta;
    @FXML
    private Button btnRecepcionVerOrdenes;
    @FXML
    private Button btnRecepcionNuevaOrden;
    @FXML
    private Button btnRecepcionPrev;
    @FXML
    private Button btnRecepcionSig;
    @FXML
    private Label lblRecepcionPagina;

    private final ObservableList<RecepcionFilaDto> filasRecepcion = FXCollections.observableArrayList();
    private RecepcionFilaDto filaSeleccionada = null;
    private int paginaRecepcion = 0;
    private int totalPaginasRecepcion = 1;

    // ── Filtros órdenes ───────────────────────────────────────────
    @FXML
    private TextField txtFiltroOrdenQ;
    @FXML
    private ComboBox<ObraSocialDto> cboFiltroOrdenOs;
    @FXML
    private DatePicker dpFiltroOrdenDesde;
    @FXML
    private DatePicker dpFiltroOrdenHasta;

    // ── Filtros resultados ────────────────────────────────────────
    @FXML
    private TextField txtFiltroResQ;
    @FXML
    private ComboBox<ObraSocialDto> cboFiltroResOs;
    @FXML
    private ComboBox<String> cboFiltroResEstado;
    @FXML
    private DatePicker dpFiltroResDesde;
    @FXML
    private DatePicker dpFiltroResHasta;

    // ── Panel Órdenes ─────────────────────────────────────────────
    @FXML
    private Label lblOrdenesContexto;
    @FXML
    private Button btnOrdenesVolver;
    @FXML
    private TableView<OrdenDto> tablaOrdenes;
    @FXML
    private TableColumn<OrdenDto, String> colOrdenNumero;
    @FXML
    private TableColumn<OrdenDto, String> colOrdenPaciente;
    @FXML
    private TableColumn<OrdenDto, String> colOrdenOs;
    @FXML
    private TableColumn<OrdenDto, String> colOrdenFecha;
    @FXML
    private TableColumn<OrdenDto, String> colOrdenEstado;
    @FXML
    private Button btnOrdenesPrev;
    @FXML
    private Button btnOrdenesSig;
    @FXML
    private Label lblOrdenesPagina;
    @FXML
    private Label lblInfoOrdenes;
    @FXML
    private Button btnOrdenesNuevaOrden;
    @FXML
    private Button btnOrdenesModificar;
    @FXML
    private Button btnOrdenesInformar;

    private final ObservableList<OrdenDto> ordenesData = FXCollections.observableArrayList();
    private OrdenDto ordenSeleccionada = null;
    private int paginaOrdenes = 0;
    private int totalPaginasOrdenes = 1;
    private Integer idPacienteOrdenes = null;  // null = todas las órdenes

    // ── Panel Resultados ──────────────────────────────────────────
    @FXML
    private TableView<OrdenDto> tablaResultados;
    @FXML
    private TableColumn<OrdenDto, String> colResNumero;
    @FXML
    private TableColumn<OrdenDto, String> colResPaciente;
    @FXML
    private TableColumn<OrdenDto, String> colResOs;
    @FXML
    private TableColumn<OrdenDto, String> colResFecha;
    @FXML
    private TableColumn<OrdenDto, String> colResEstadoRes;
    @FXML
    private Button btnResultadosPrev;
    @FXML
    private Button btnResultadosSig;
    @FXML
    private Label lblResultadosPagina;
    @FXML
    private Label lblInfoResultados;
    @FXML
    private Button btnResultadosInformar;

    private final ObservableList<OrdenDto> resultadosData = FXCollections.observableArrayList();
    private OrdenDto ordenResultadoSeleccionada = null;
    private int paginaResultados = 0;
    private int totalPaginasResultados = 1;

    // ── Utilitarios ───────────────────────────────────────────────
    @FXML
    private Button btnAnalisis;
    @FXML
    private Button btnObrasSociales;
    @FXML
    private Button btnMedicos;
    @FXML
    private Button btnSecciones;
    @FXML
    private Button btnPracticas;
    @FXML
    private Button btnDetallePracticasFacturacion;
    @FXML
    private Button btnEnviarFacturacion;
    @FXML
    private Button btnConfigReporte;

    public PrincipalFxController(FxNavigationService navigationService,
                                 OrdenClient ordenClient,
                                 PacienteClient pacienteClient,
                                 RecepcionClient recepcionClient,
                                 ObraSocialClient obraSocialClient,
                                 MedicoClient medicoClient,
                                 SessionContext sessionContext) {
        this.navigationService = navigationService;
        this.ordenClient = ordenClient;
        this.pacienteClient = pacienteClient;
        this.recepcionClient = recepcionClient;
        this.obraSocialClient = obraSocialClient;
        this.sessionContext = sessionContext;
    }

    @FXML
    public void initialize() {
        cargarObrasSociales();
        configurarTablaRecepcion();
        configurarTablaOrdenes();
        configurarTablaResultados();
        wireButtons();
        mostrarPanel(panelRecepcion);
        cargarPaginaRecepcion();
    }

    private void cargarObrasSociales() {
        try {
            List<ObraSocialDto> activas = obraSocialClient.findAll().stream()
                    .filter(ObraSocialDto::estado)
                    .sorted(java.util.Comparator.comparing(ObraSocialDto::nombre,
                            String::compareToIgnoreCase))
                    .toList();
            todasObrasSociales.setAll(activas);
        } catch (RuntimeException ignored) {
        }

        Callback<ListView<ObraSocialDto>, ListCell<ObraSocialDto>> cf = lv -> new ListCell<>() {
            @Override
            protected void updateItem(ObraSocialDto item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.nombre());
            }
        };

        for (ComboBox<ObraSocialDto> combo : List.of(
                cboFiltroRecepcionOs, cboFiltroOrdenOs, cboFiltroResOs)) {
            combo.setCellFactory(cf);
            combo.setButtonCell(cf.call(null));
            combo.getItems().setAll(todasObrasSociales);
        }

        cboFiltroResEstado.getItems().setAll(
                "Todos", "Sin informar", "Parcialmente informada", "Informada");
    }

    // ══════════════════════════════════════════════════════════════
    // PANEL RECEPCIÓN
    // ══════════════════════════════════════════════════════════════

    private void configurarTablaRecepcion() {
        colRecepcionTipo.setCellValueFactory(d ->
                new ReadOnlyStringWrapper(d.getValue().esPaciente() ? "Paciente" : "Solo persona"));
        colRecepcionDni.setCellValueFactory(d ->
                new ReadOnlyStringWrapper(d.getValue().dni() != null
                        ? d.getValue().dni().toString() : "Sin DNI"));
        colRecepcionNombre.setCellValueFactory(d ->
                new ReadOnlyStringWrapper(safe(d.getValue().apellido()) + ", " + safe(d.getValue().nombre())));

        tablaRecepcion.setItems(filasRecepcion);
        tablaRecepcion.setPlaceholder(new Label("No hay personas registradas."));
        tablaRecepcion.getSelectionModel().selectedItemProperty()
                .addListener((obs, old, fila) -> seleccionarFilaRecepcion(fila));

        txtBuscarRecepcion.setOnAction(e -> ejecutarBusquedaRecepcion());
    }

    @FXML
    private void ejecutarBusquedaRecepcion() {
        paginaRecepcion = 0;
        limpiarSeleccionRecepcion();
        cargarPaginaRecepcion();
    }

    private void cargarPaginaRecepcion() {
        try {
            String q = txtBuscarRecepcion.getText();
            boolean hayBusqueda = q != null && !q.isBlank();
            PageResponse<RecepcionFilaDto> page = recepcionClient.findPaginated(
                    paginaRecepcion, PAGE_SIZE, hayBusqueda ? q : null);
            if (page == null) return;
            filasRecepcion.setAll(page.content());
            totalPaginasRecepcion = Math.max(1, page.totalPages());
            btnRecepcionPrev.setDisable(page.first());
            btnRecepcionSig.setDisable(page.last());

            if (page.content().isEmpty()) {
                lblRecepcionPagina.setText("Sin resultados");
                if (hayBusqueda) {
                    lblInfoRecepcion.setText("No se encontró ningún paciente ni persona con '"
                            + q.trim() + "'. Use 'Crear Persona'.");
                } else {
                    lblInfoRecepcion.setText("No hay pacientes registrados.");
                }
                lblInfoRecepcion.setStyle("-fx-text-fill: #b45309; -fx-font-size: 13px;");
            } else {
                lblRecepcionPagina.setText("Página " + (paginaRecepcion + 1) + " de "
                        + totalPaginasRecepcion + "  (" + page.totalElements() + ")");
                boolean todosSonPacientes = page.content().stream().allMatch(RecepcionFilaDto::esPaciente);
                if (todosSonPacientes) {
                    lblInfoRecepcion.setText(page.totalElements() + " paciente(s).");
                    lblInfoRecepcion.setStyle("-fx-text-fill: #374151; -fx-font-size: 13px;");
                } else {
                    lblInfoRecepcion.setText("Sin pacientes con ese criterio. "
                            + page.totalElements() + " persona(s) encontrada(s) — seleccione y use 'Dar de Alta'.");
                    lblInfoRecepcion.setStyle("-fx-text-fill: #b45309; -fx-font-size: 13px;");
                }
            }
            btnRecepcionCrearPersona.setDisable(false);
            limpiarSeleccionRecepcion();
        } catch (RuntimeException ex) {
            filasRecepcion.clear();
            lblInfoRecepcion.setText("Error al cargar: " + ex.getMessage());
        }
    }

    @FXML
    private void recepcionPaginaAnterior() {
        if (paginaRecepcion > 0) {
            paginaRecepcion--;
            cargarPaginaRecepcion();
        }
    }

    @FXML
    private void recepcionPaginaSiguiente() {
        if (paginaRecepcion < totalPaginasRecepcion - 1) {
            paginaRecepcion++;
            cargarPaginaRecepcion();
        }
    }

    private void seleccionarFilaRecepcion(RecepcionFilaDto fila) {
        filaSeleccionada = fila;
        actualizarBotonesRecepcion(fila);
        if (fila == null) return;

        String nombre = safe(fila.apellido()) + ", " + safe(fila.nombre())
                + (fila.dni() != null ? "  (DNI " + fila.dni() + ")" : "  (sin DNI)");
        String tipo = fila.esPaciente() ? "Paciente" : "Persona (no es paciente aún)";
        lblInfoRecepcion.setText(nombre + " — " + tipo);
        lblInfoRecepcion.setStyle("-fx-text-fill: #004d73; -fx-font-size: 13px; -fx-font-weight: 700;");
    }

    private void actualizarBotonesRecepcion(RecepcionFilaDto fila) {
        btnRecepcionDarDeAlta.setDisable(fila == null || fila.esPaciente());
        btnRecepcionVerOrdenes.setDisable(fila == null || !fila.esPaciente());
        btnRecepcionNuevaOrden.setDisable(fila == null || !fila.esPaciente());
    }

    private void limpiarSeleccionRecepcion() {
        filaSeleccionada = null;
        tablaRecepcion.getSelectionModel().clearSelection();
        btnRecepcionDarDeAlta.setDisable(true);
        btnRecepcionVerOrdenes.setDisable(true);
        btnRecepcionNuevaOrden.setDisable(true);
    }

    @FXML
    private void crearPersona() {
        sessionContext.setPersonaParaAlta(null);
        navigationService.showNuevoPaciente();
        refrescarTrasAlta();
    }

    @FXML
    private void darDeAlta() {
        if (filaSeleccionada == null || filaSeleccionada.esPaciente()) return;
        // Pasamos la persona encontrada para que el formulario la pre-rellene
        sessionContext.setPersonaParaAlta(
                new com.ybc.ybioq.fx.client.dto.PersonaDto(
                        filaSeleccionada.idPersona(),
                        filaSeleccionada.dni(),
                        filaSeleccionada.apellido(),
                        filaSeleccionada.nombre()));
        navigationService.showAltaDesdePaciente();
        refrescarTrasAlta();
    }

    private void refrescarTrasAlta() {
        PacienteDto creado = sessionContext.getPacientePreseleccionado();
        if (creado != null) {
            sessionContext.limpiarPacientePreseleccionado();
            ejecutarBusquedaRecepcion();
            lblInfoRecepcion.setText(safe(creado.apellido()) + ", " + safe(creado.nombre())
                    + " — Alta completada. Ya puede crear una orden.");
            lblInfoRecepcion.setStyle("-fx-text-fill: #1a7a40; -fx-font-size: 13px; -fx-font-weight: 700;");
        }
    }

    @FXML
    private void verOrdenesDelPaciente() {
        if (filaSeleccionada == null || !filaSeleccionada.esPaciente()) return;
        idPacienteOrdenes = filaSeleccionada.idPaciente();
        paginaOrdenes = 0;
        String nombre = safe(filaSeleccionada.apellido()) + ", " + safe(filaSeleccionada.nombre());
        lblOrdenesContexto.setText("Órdenes de: " + nombre);
        btnOrdenesVolver.setVisible(true);
        mostrarPanel(panelOrdenes);
        cargarPaginaOrdenes();
    }

    @FXML
    private void nuevaOrdenDesdeRecepcion() {
        if (filaSeleccionada == null || !filaSeleccionada.esPaciente()) return;
        prepararNuevaOrden(filaSeleccionada.idPaciente());
    }

    // ══════════════════════════════════════════════════════════════
    // PANEL ÓRDENES
    // ══════════════════════════════════════════════════════════════

    private void configurarTablaOrdenes() {
        colOrdenNumero.setCellValueFactory(d ->
                new ReadOnlyStringWrapper(safe(d.getValue().numeroOrden())));
        colOrdenPaciente.setCellValueFactory(d ->
                new ReadOnlyStringWrapper(safe(d.getValue().nombrePaciente())));
        colOrdenOs.setCellValueFactory(d ->
                new ReadOnlyStringWrapper(safe(d.getValue().nombreObraSocial())));
        colOrdenFecha.setCellValueFactory(d ->
                new ReadOnlyStringWrapper(d.getValue().fecha() != null
                        ? d.getValue().fecha().format(FMT) : "-"));
        colOrdenEstado.setCellValueFactory(d ->
                new ReadOnlyStringWrapper(estadoLabel(d.getValue().estadoOrden())));

        tablaOrdenes.setItems(ordenesData);
        tablaOrdenes.setPlaceholder(new Label("No hay órdenes."));
        tablaOrdenes.getSelectionModel().selectedItemProperty()
                .addListener((obs, old, orden) -> seleccionarOrden(orden));
    }

    @FXML
    private void filtrarOrdenes() {
        paginaOrdenes = 0;
        cargarPaginaOrdenes();
    }

    @FXML
    private void limpiarFiltrosOrdenes() {
        txtFiltroOrdenQ.clear();
        cboFiltroOrdenOs.setValue(null);
        dpFiltroOrdenDesde.setValue(null);
        dpFiltroOrdenHasta.setValue(null);
        paginaOrdenes = 0;
        cargarPaginaOrdenes();
    }

    private void cargarPaginaOrdenes() {
        try {
            String q = txtFiltroOrdenQ.getText();
            ObraSocialDto os = cboFiltroOrdenOs.getValue();
            LocalDate desde = dpFiltroOrdenDesde.getValue();
            LocalDate hasta = dpFiltroOrdenHasta.getValue();
            PageResponse<OrdenDto> page = ordenClient.findPaginated(
                    paginaOrdenes, PAGE_SIZE,
                    idPacienteOrdenes,
                    os != null ? os.id() : null,
                    desde, hasta,
                    q != null && !q.isBlank() ? q : null);
            if (page == null) return;
            ordenesData.setAll(page.content());
            totalPaginasOrdenes = Math.max(1, page.totalPages());
            lblOrdenesPagina.setText("Página " + (paginaOrdenes + 1) + " de " + totalPaginasOrdenes
                    + "  (" + page.totalElements() + " órdenes)");
            btnOrdenesPrev.setDisable(page.first());
            btnOrdenesSig.setDisable(page.last());
            if (ordenesData.isEmpty()) {
                lblInfoOrdenes.setText("No se encontraron órdenes con los filtros aplicados.");
            } else {
                lblInfoOrdenes.setText("Seleccione una orden para operar.");
            }
            lblInfoOrdenes.setStyle("-fx-text-fill: #6b7280; -fx-font-size: 13px;");
            ordenSeleccionada = null;
            btnOrdenesModificar.setDisable(true);
            btnOrdenesInformar.setDisable(true);
        } catch (RuntimeException ex) {
            lblInfoOrdenes.setText("Error al cargar órdenes: " + ex.getMessage());
        }
    }

    private void seleccionarOrden(OrdenDto orden) {
        ordenSeleccionada = orden;
        boolean sinInformar = orden != null && "Sin informar".equals(orden.estadoResultados());
        btnOrdenesModificar.setDisable(!sinInformar);
        btnOrdenesInformar.setDisable(orden == null);
        if (orden != null) {
            lblInfoOrdenes.setText("Seleccionada: N° " + safe(orden.numeroOrden())
                    + " — " + safe(orden.nombrePaciente())
                    + " — " + safe(orden.estadoResultados()));
            lblInfoOrdenes.setStyle("-fx-text-fill: #004d73; -fx-font-size: 13px; -fx-font-weight: 700;");
        }
    }

    @FXML
    private void ordenesPaginaAnterior() {
        if (paginaOrdenes > 0) {
            paginaOrdenes--;
            cargarPaginaOrdenes();
        }
    }

    @FXML
    private void ordenesPaginaSiguiente() {
        if (paginaOrdenes < totalPaginasOrdenes - 1) {
            paginaOrdenes++;
            cargarPaginaOrdenes();
        }
    }

    @FXML
    private void volverARecepcion() {
        idPacienteOrdenes = null;
        paginaOrdenes = 0;
        lblOrdenesContexto.setText("Todas las órdenes");
        btnOrdenesVolver.setVisible(false);
        mostrarPanel(panelRecepcion);
    }

    @FXML
    private void nuevaOrdenDesdeOrdenes() {
        Integer idPaciente = idPacienteOrdenes != null ? idPacienteOrdenes
                : (ordenSeleccionada != null ? ordenSeleccionada.idPaciente() : null);
        prepararNuevaOrden(idPaciente);
        cargarPaginaOrdenes();
    }

    @FXML
    private void modificarOrden() {
        if (ordenSeleccionada == null) return;
        sessionContext.setOrdenParaModificar(ordenSeleccionada);
        navigationService.showCargarOrden();
        cargarPaginaOrdenes();
    }

    @FXML
    private void informarOrden() {
        if (ordenSeleccionada == null) return;
        sessionContext.setOrdenParaInformar(ordenSeleccionada);
        navigationService.showCargarResultados();
        cargarPaginaOrdenes();
    }

    // ══════════════════════════════════════════════════════════════
    // PANEL RESULTADOS
    // ══════════════════════════════════════════════════════════════

    private void configurarTablaResultados() {
        colResNumero.setCellValueFactory(d ->
                new ReadOnlyStringWrapper(safe(d.getValue().numeroOrden())));
        colResPaciente.setCellValueFactory(d ->
                new ReadOnlyStringWrapper(safe(d.getValue().nombrePaciente())));
        colResOs.setCellValueFactory(d ->
                new ReadOnlyStringWrapper(safe(d.getValue().nombreObraSocial())));
        colResFecha.setCellValueFactory(d ->
                new ReadOnlyStringWrapper(d.getValue().fecha() != null
                        ? d.getValue().fecha().format(FMT) : "-"));
        colResEstadoRes.setCellValueFactory(d ->
                new ReadOnlyStringWrapper(safe(d.getValue().estadoResultados())));

        tablaResultados.setItems(resultadosData);
        tablaResultados.setPlaceholder(new Label("No hay órdenes."));
        tablaResultados.getSelectionModel().selectedItemProperty()
                .addListener((obs, old, orden) -> seleccionarOrdenResultados(orden));

        dpFiltroResDesde.valueProperty().addListener((obs, o, n) -> filtrarResultados());
        dpFiltroResHasta.valueProperty().addListener((obs, o, n) -> filtrarResultados());
    }

    @FXML
    private void filtrarResultados() {
        paginaResultados = 0;
        cargarPaginaResultados();
    }

    @FXML
    private void limpiarFiltrosResultados() {
        txtFiltroResQ.clear();
        cboFiltroResOs.setValue(null);
        cboFiltroResEstado.setValue(null);
        dpFiltroResDesde.setValue(null);
        dpFiltroResHasta.setValue(null);
        paginaResultados = 0;
        cargarPaginaResultados();
    }

    private void cargarPaginaResultados() {
        try {
            String q = txtFiltroResQ.getText();
            ObraSocialDto os = cboFiltroResOs.getValue();
            LocalDate desde = dpFiltroResDesde.getValue();
            LocalDate hasta = dpFiltroResHasta.getValue();
            PageResponse<OrdenDto> page = ordenClient.findPaginated(
                    paginaResultados, PAGE_SIZE,
                    null,
                    os != null ? os.id() : null,
                    desde, hasta,
                    q != null && !q.isBlank() ? q : null);
            if (page == null) return;

            // Filtro local por estado de resultados (calculado en backend, no requiere consulta extra)
            String estadoFiltro = cboFiltroResEstado.getValue();
            List<OrdenDto> contenido = page.content();
            if (estadoFiltro != null && !estadoFiltro.equals("Todos")) {
                contenido = contenido.stream()
                        .filter(o -> estadoFiltro.equals(o.estadoResultados()))
                        .toList();
            }

            resultadosData.setAll(contenido);
            totalPaginasResultados = Math.max(1, page.totalPages());
            lblResultadosPagina.setText("Página " + (paginaResultados + 1) + " de " + totalPaginasResultados
                    + "  (" + page.totalElements() + " órdenes)");
            btnResultadosPrev.setDisable(page.first());
            btnResultadosSig.setDisable(page.last());
            lblInfoResultados.setText("Seleccione una orden para informar.");
            lblInfoResultados.setStyle("-fx-text-fill: #6b7280; -fx-font-size: 13px;");
            ordenResultadoSeleccionada = null;
            btnResultadosInformar.setDisable(true);
        } catch (RuntimeException ex) {
            lblInfoResultados.setText("Error al cargar: " + ex.getMessage());
        }
    }

    private void seleccionarOrdenResultados(OrdenDto orden) {
        ordenResultadoSeleccionada = orden;
        btnResultadosInformar.setDisable(orden == null);
        if (orden != null) {
            lblInfoResultados.setText("Seleccionada: N° " + safe(orden.numeroOrden())
                    + " — " + safe(orden.nombrePaciente())
                    + " — Estado: " + safe(orden.estadoResultados()));
            lblInfoResultados.setStyle("-fx-text-fill: #004d73; -fx-font-size: 13px; -fx-font-weight: 700;");
        }
    }

    @FXML
    private void resultadosPaginaAnterior() {
        if (paginaResultados > 0) {
            paginaResultados--;
            cargarPaginaResultados();
        }
    }

    @FXML
    private void resultadosPaginaSiguiente() {
        if (paginaResultados < totalPaginasResultados - 1) {
            paginaResultados++;
            cargarPaginaResultados();
        }
    }

    @FXML
    private void informarDesdeResultados() {
        if (ordenResultadoSeleccionada == null) return;
        sessionContext.setOrdenParaInformar(ordenResultadoSeleccionada);
        navigationService.showCargarResultados();
        cargarPaginaResultados();
    }

    // ══════════════════════════════════════════════════════════════
    // Helpers compartidos
    // ══════════════════════════════════════════════════════════════

    private void prepararNuevaOrden(Integer idPaciente) {
        if (idPaciente != null) {
            try {
                PacienteDto paciente = pacienteClient.findById(idPaciente);
                if (paciente != null) sessionContext.setPacientePreseleccionado(paciente);
            } catch (RuntimeException ignored) {
            }
        }
        navigationService.showCargarOrden();
    }

    private String estadoLabel(Integer estado) {
        if (estado == null) return "-";
        return switch (estado) {
            case 0 -> "Pendiente";
            case 1 -> "Validada";
            case 2 -> "Anulada";
            default -> String.valueOf(estado);
        };
    }

    private void wireButtons() {
        wire(btnMenuRecepcion, () -> {
            paginaRecepcion = 0;
            txtBuscarRecepcion.clear();
            mostrarPanel(panelRecepcion);
            cargarPaginaRecepcion();
        });
        wire(btnMenuOrdenes, () -> {
            idPacienteOrdenes = null;
            paginaOrdenes = 0;
            lblOrdenesContexto.setText("Todas las órdenes");
            btnOrdenesVolver.setVisible(false);
            mostrarPanel(panelOrdenes);
            cargarPaginaOrdenes();
        });
        wire(btnMenuResultados, () -> {
            paginaResultados = 0;
            mostrarPanel(panelResultados);
            cargarPaginaResultados();
        });
        wire(btnMenuFacturacion, () -> mostrarPanel(panelFacturacion));
        wire(btnSalir, this::cerrarVentana);
        wire(btnAnalisis, navigationService::showConfiguracionAnalisis);
        wire(btnObrasSociales, navigationService::showObrasSociales);
        wire(btnMedicos, navigationService::showMedicos);
        wire(btnSecciones, navigationService::showSecciones);
        wire(btnPracticas, navigationService::showPracticas);
        wire(btnDetallePracticasFacturacion, navigationService::showDetallePracticasFacturacion);
        wire(btnEnviarFacturacion, navigationService::showEnviarFacturacion);
        wire(btnConfigReporte, navigationService::showConfiguracionReporte);
    }

    private void wire(Button btn, Runnable action) {
        if (btn != null) btn.setOnAction(e -> action.run());
    }

    private void mostrarPanel(ScrollPane destino) {
        for (ScrollPane p : new ScrollPane[]{panelRecepcion, panelOrdenes, panelResultados,
                panelFacturacion}) {
            if (p != null) p.setVisible(false);
        }
        if (destino != null) destino.setVisible(true);
    }

    private void cerrarVentana() {
        if (btnSalir == null || btnSalir.getScene() == null) return;
        ((Stage) btnSalir.getScene().getWindow()).close();
    }

    private String safe(String v) {
        return v != null ? v : "-";
    }
}
