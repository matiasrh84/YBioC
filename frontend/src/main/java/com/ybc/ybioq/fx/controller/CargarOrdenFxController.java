package com.ybc.ybioq.fx.controller;

import com.ybc.ybioq.fx.client.MedicoClient;
import com.ybc.ybioq.fx.client.ObraSocialClient;
import com.ybc.ybioq.fx.client.OrdenClient;
import com.ybc.ybioq.fx.client.PacienteClient;
import com.ybc.ybioq.fx.client.PersonaClient;
import com.ybc.ybioq.fx.client.PracticaClient;
import com.ybc.ybioq.fx.client.ReporteClient;
import com.ybc.ybioq.fx.client.dto.*;
import com.ybc.ybioq.fx.session.SessionContext;
import com.ybc.ybioq.fx.util.AutocompleteTextField;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CargarOrdenFxController {

    private final PacienteClient    pacienteClient;
    private final PersonaClient     personaClient;
    private final PracticaClient    practicaClient;
    private final MedicoClient      medicoClient;
    private final OrdenClient       ordenClient;
    private final ObraSocialClient  obraSocialClient;
    private final ReporteClient     reporteClient;
    private final SessionContext    sessionContext;

    // ── Estado ─────────────────────────────────────────────────────
    private PacienteDto             pacienteActual           = null;
    private Integer                 idObraSocialActual       = null;
    private Integer                 selectedMedicoId         = null;
    private Integer                 selectedEspecialidadId   = null;
    private boolean                 esObraSocialNueva        = false;
    private List<MedicoDto>         todosMedicos             = Collections.emptyList();
    private List<PracticaDto>       todasPracticas           = Collections.emptyList();
    private List<ObraSocialDto>     todasLasObrasSociales    = Collections.emptyList();
    private List<ObraSocialPacienteDto> obrasSocialesPaciente = Collections.emptyList();
    private final ObservableList<LineaPractica> practicasOrden = FXCollections.observableArrayList();
    // Modo edición
    private boolean modoEdicion    = false;
    private Integer idOrdenEditando = null;

    // ── FXML: Paciente ────────────────────────────────────────────
    @FXML private VBox      vboxBuscarDni;
    @FXML private TextField txtDni;
    @FXML private Label lblPaciente;
    @FXML private ComboBox<String> cboObraSocial;
    @FXML private TextField txtNumeroAfiliado;

    // ── FXML: Datos de la orden ──────────────────────────────────
    @FXML private TextField txtNumeroOrden;
    @FXML private ComboBox<String> cboTipoOrden;
    @FXML private TextField txtMedico;
    @FXML private ComboBox<String> cboEspecialidad;

    @FXML private Label lblModoOrden;

    // ── FXML: Prácticas ──────────────────────────────────────────
    @FXML private TextField txtBuscarPractica;
    @FXML private TableView<LineaPractica> tablaPracticas;
    @FXML private TableColumn<LineaPractica, String> colCodigo;
    @FXML private TableColumn<LineaPractica, String> colNombre;
    @FXML private TableColumn<LineaPractica, String> colPrecio;
    @FXML private TableColumn<LineaPractica, String> colCodFac;
    @FXML private Label lblTotal;
    @FXML private Label mensajeLabel;

    public CargarOrdenFxController(PacienteClient pacienteClient,
                                   PersonaClient personaClient,
                                   PracticaClient practicaClient,
                                   MedicoClient medicoClient,
                                   OrdenClient ordenClient,
                                   ObraSocialClient obraSocialClient,
                                   ReporteClient reporteClient,
                                   SessionContext sessionContext) {
        this.pacienteClient   = pacienteClient;
        this.personaClient    = personaClient;
        this.practicaClient   = practicaClient;
        this.medicoClient     = medicoClient;
        this.ordenClient      = ordenClient;
        this.obraSocialClient = obraSocialClient;
        this.reporteClient    = reporteClient;
        this.sessionContext   = sessionContext;
    }

    @FXML
    private void initialize() {
        cboTipoOrden.getItems().setAll("Ambulatorio", "Domicilio", "Internacion");
        cboTipoOrden.getSelectionModel().selectFirst();
        configurarTablaPracticas();
        cargarCatalogos();
        limpiarPaciente();
        actualizarTotal();

        OrdenDto ordenMod = sessionContext.getOrdenParaModificar();
        if (ordenMod != null) {
            sessionContext.limpiarOrdenParaModificar();
            precargarOrdenParaEditar(ordenMod);
            return;
        }

        PacienteDto pre = sessionContext.getPacientePreseleccionado();
        if (pre != null) {
            sessionContext.limpiarPacientePreseleccionado();
            pacienteActual = pre;
            String dni = pre.dni() != null ? "  (DNI " + pre.dni() + ")" : "  (sin DNI)";
            lblPaciente.setText(safe(pre.apellido()) + ", " + safe(pre.nombre()) + dni);
            cargarObrasSociales(pre.id());
            vboxBuscarDni.setVisible(false);
            vboxBuscarDni.setManaged(false);
        }
    }

    // ── Modo edición ──────────────────────────────────────────────

    private void precargarOrdenParaEditar(OrdenDto orden) {
        modoEdicion     = true;
        idOrdenEditando = orden.id();

        lblModoOrden.setText("✎  Modificando Orden N° " + safe(orden.numeroOrden())
                + "  —  " + safe(orden.nombrePaciente()));
        lblModoOrden.setVisible(true);
        lblModoOrden.setManaged(true);

        vboxBuscarDni.setVisible(false);
        vboxBuscarDni.setManaged(false);

        // Paciente
        try {
            pacienteActual = pacienteClient.findById(orden.idPaciente());
        } catch (RuntimeException ignored) {}
        lblPaciente.setText(safe(orden.nombrePaciente())
                + (orden.dniPaciente() != null ? "  (DNI " + orden.dniPaciente() + ")" : ""));

        // OS del paciente (carga combo y selecciona la correcta)
        if (pacienteActual != null) {
            cargarObrasSociales(pacienteActual.id());
            Integer idOs = orden.idObraSocial();
            obrasSocialesPaciente.stream()
                    .filter(os -> os.idObraSocial().equals(idOs))
                    .findFirst()
                    .ifPresent(os -> cboObraSocial.setValue(os.nombreObraSocial()));
        }

        // N° orden y tipo
        txtNumeroOrden.setText(safe(orden.numeroOrden()));
        cboTipoOrden.setValue(orden.tipoOrden() != null ? orden.tipoOrden() : "Ambulatorio");

        // Médico y especialidad
        Integer idMed = orden.idMedico();
        todosMedicos.stream().filter(m -> m.id().equals(idMed)).findFirst().ifPresent(m -> {
            seleccionarMedico(m);
            txtMedico.setText(safe(m.apellido()) + ", " + safe(m.nombre()));
        });

        // Prácticas desde backend
        try {
            OrdenDetalleDto detalle = ordenClient.getDetalle(orden.id());
            if (detalle != null) {
                // Especialidad (usando datos del detalle)
                if (detalle.nombreEspecialidad() != null)
                    cboEspecialidad.setValue(detalle.nombreEspecialidad());

                for (LineaOrdenDetalleDto lp : detalle.practicas()) {
                    if (practicasOrden.stream().noneMatch(e -> e.getIdPractica().equals(lp.idPractica()))) {
                        practicasOrden.add(new LineaPractica(
                                lp.idPractica(), lp.codigoPractica(), lp.nombre(),
                                lp.precio() != null ? lp.precio() : BigDecimal.ZERO,
                                lp.codFac() != null ? lp.codFac() : ""));
                    }
                }
                actualizarTotal();
            }
        } catch (RuntimeException ex) {
            mensajeLabel.setText("Error al cargar prácticas: " + ex.getMessage());
        }
    }

    // ── Búsqueda de paciente por DNI ──────────────────────────────

    @FXML
    private void buscarPorDni() {
        String q = txtDni.getText() == null ? "" : txtDni.getText().trim();
        if (q.isBlank()) { mensajeLabel.setText("Ingrese un DNI."); return; }
        try {
            List<PersonaDto> resultado = personaClient.buscar(q);
            if (resultado.isEmpty()) { mensajeLabel.setText("No se encontró persona con DNI " + q); return; }
            cargarPacientePorPersona(resultado.getFirst());
        } catch (RuntimeException ex) {
            mensajeLabel.setText("Error al buscar: " + ex.getMessage());
        }
    }

    private void cargarPacientePorPersona(PersonaDto persona) {
        PacienteDto paciente = pacienteClient.findByPersona(persona.id());
        if (paciente == null) {
            paciente = pacienteClient.crearDesdePersona(persona.id());
            mensajeLabel.setText("Se creó ficha de paciente para " + persona.apellido() + ", " + persona.nombre());
        }
        pacienteActual = paciente;
        lblPaciente.setText(safe(persona.apellido()) + ", " + safe(persona.nombre())
                + "  (DNI " + persona.dni() + ")");
        cargarObrasSociales(paciente.id());
    }

    private void cargarObrasSociales(Integer idPaciente) {
        cboObraSocial.getItems().clear();
        txtNumeroAfiliado.clear();
        idObraSocialActual = null;
        esObraSocialNueva = false;
        try {
            obrasSocialesPaciente = pacienteClient.findObrasSociales(idPaciente);
            if (obrasSocialesPaciente.isEmpty()) {
                // Paciente sin OS → mostramos todas para que el operador asigne una
                esObraSocialNueva = true;
                cboObraSocial.getItems().setAll(
                        todasLasObrasSociales.stream().map(ObraSocialDto::nombre).toList());
                mensajeLabel.setText("El paciente no tiene obra social asignada. Seleccione una para continuar.");
            } else {
                cboObraSocial.getItems().setAll(
                        obrasSocialesPaciente.stream().map(ObraSocialPacienteDto::nombreObraSocial).toList());
            }
            if (!cboObraSocial.getItems().isEmpty()) {
                cboObraSocial.getSelectionModel().selectFirst();
                sincronizarObraSocial();
            }
            cboObraSocial.valueProperty().addListener((obs, o, n) -> sincronizarObraSocial());
        } catch (RuntimeException ex) {
            mensajeLabel.setText("Error al cargar obras sociales: " + ex.getMessage());
        }
    }

    @FXML
    private void sincronizarObraSocial() {
        String selNombre = cboObraSocial.getValue();
        if (selNombre == null) return;
        if (esObraSocialNueva) {
            todasLasObrasSociales.stream()
                    .filter(os -> selNombre.equals(os.nombre()))
                    .findFirst()
                    .ifPresent(os -> {
                        idObraSocialActual = os.id();
                        txtNumeroAfiliado.setPromptText("Nro. de afiliado");
                        recalcularPrecios();
                    });
        } else {
            obrasSocialesPaciente.stream()
                    .filter(os -> os.nombreObraSocial().equals(selNombre))
                    .findFirst()
                    .ifPresent(os -> {
                        idObraSocialActual = os.idObraSocial();
                        txtNumeroAfiliado.setText(os.numeroAfiliado() != null ? os.numeroAfiliado() : "");
                        recalcularPrecios();
                    });
        }
    }

    private void limpiarPaciente() {
        pacienteActual = null;
        idObraSocialActual = null;
        esObraSocialNueva = false;
        lblPaciente.setText("(sin seleccionar)");
        txtDni.clear();
        cboObraSocial.getItems().clear();
        txtNumeroAfiliado.clear();
        obrasSocialesPaciente = Collections.emptyList();
    }

    // ── Prácticas ─────────────────────────────────────────────────

    @FXML
    private void agregarPractica() {
        String q = txtBuscarPractica.getText() == null ? "" : txtBuscarPractica.getText().trim();
        if (q.isBlank()) { mensajeLabel.setText("Ingrese código o nombre."); return; }
        PracticaDto practica = buscarPracticaLocal(q);
        if (practica == null) { mensajeLabel.setText("Práctica no encontrada: " + q); return; }
        agregarPracticaDto(practica);
    }

    private void agregarPracticaDto(PracticaDto practica) {
        if (practicasOrden.stream().anyMatch(l -> l.getIdPractica().equals(practica.id()))) {
            mensajeLabel.setText("La práctica ya está en la lista."); return;
        }
        BigDecimal precio = practica.precio1() != null ? practica.precio1() : BigDecimal.ZERO;
        String codFac = "";
        if (idObraSocialActual != null) {
            try {
                PrecioConsultaDto pc = practicaClient.consultarPrecio(practica.id(), idObraSocialActual);
                precio = pc.precio();
                codFac = pc.codFac() != null ? pc.codFac() : "";
            } catch (RuntimeException ignored) {}
        }
        practicasOrden.add(new LineaPractica(
                practica.id(), practica.codigoPractica(), practica.determinacion(), precio, codFac));
        txtBuscarPractica.clear();
        actualizarTotal();
        mensajeLabel.setText("Práctica agregada: " + practica.determinacion());
    }

    @FXML
    private void quitarPractica() {
        LineaPractica sel = tablaPracticas.getSelectionModel().getSelectedItem();
        if (sel == null) { mensajeLabel.setText("Seleccione una práctica para quitar."); return; }
        practicasOrden.remove(sel);
        actualizarTotal();
    }

    private PracticaDto buscarPracticaLocal(String q) {
        String trimmed = q == null ? "" : q.trim();
        try {
            Integer cod = Integer.parseInt(trimmed);
            // 1. Coincidencia exacta
            var exacto = todasPracticas.stream()
                    .filter(p -> cod.equals(p.codigoPractica())).findFirst();
            if (exacto.isPresent()) return exacto.get();
            // 2. Código termina con el número ingresado (ej: busco "475" → toma "660475")
            var sufijo = todasPracticas.stream()
                    .filter(p -> p.codigoPractica() != null
                            && String.valueOf(p.codigoPractica()).endsWith(trimmed))
                    .findFirst();
            if (sufijo.isPresent()) return sufijo.get();
            // 3. Código contiene el número
            return todasPracticas.stream()
                    .filter(p -> p.codigoPractica() != null
                            && String.valueOf(p.codigoPractica()).contains(trimmed))
                    .findFirst().orElse(null);
        } catch (NumberFormatException ex) {
            String ql = trimmed.toLowerCase();
            return todasPracticas.stream()
                    .filter(p -> p.determinacion() != null && p.determinacion().toLowerCase().contains(ql))
                    .findFirst().orElse(null);
        }
    }

    private void recalcularPrecios() {
        if (idObraSocialActual == null || practicasOrden.isEmpty()) return;
        for (LineaPractica linea : practicasOrden) {
            try {
                PrecioConsultaDto pc = practicaClient.consultarPrecio(linea.getIdPractica(), idObraSocialActual);
                linea.setPrecio(pc.precio().toPlainString());
                linea.setCodFac(pc.codFac() != null ? pc.codFac() : "");
            } catch (RuntimeException ignored) {}
        }
        tablaPracticas.refresh();
        actualizarTotal();
    }

    private void actualizarTotal() {
        BigDecimal total = practicasOrden.stream()
                .map(l -> {
                    try { return new BigDecimal(l.getPrecio().replace(",", ".")); }
                    catch (NumberFormatException ex) { return BigDecimal.ZERO; }
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        if (lblTotal != null)
            lblTotal.setText("$ " + total.setScale(2, RoundingMode.HALF_UP).toPlainString());
    }

    // ── Guardar ───────────────────────────────────────────────────

    @FXML
    private void guardar() {
        if (pacienteActual == null)          { mensajeLabel.setText("Seleccione un paciente."); return; }
        if (idObraSocialActual == null)      { mensajeLabel.setText("Seleccione una obra social."); return; }
        if (selectedMedicoId == null)        { mensajeLabel.setText("Seleccione un médico."); return; }
        if (selectedEspecialidadId == null)  { mensajeLabel.setText("Seleccione una especialidad."); return; }
        if (practicasOrden.isEmpty())        { mensajeLabel.setText("Agregue al menos una práctica."); return; }
        if (sessionContext.getIdUsuario() == null) { mensajeLabel.setText("Sesión no iniciada."); return; }

        List<Map<String, Object>> lineas = new ArrayList<>();
        for (LineaPractica lp : practicasOrden) {
            Map<String, Object> m = new HashMap<>();
            m.put("idPractica",    lp.getIdPractica());
            m.put("precio",        parsePrecio(lp.getPrecio()));
            m.put("codPracticaFac", lp.getCodFac());
            lineas.add(m);
        }

        Map<String, Object> request = new HashMap<>();
        request.put("idPaciente",    pacienteActual.id());
        request.put("idMedico",      selectedMedicoId);
        request.put("idEspecialidad", selectedEspecialidadId);
        request.put("idObraSocial",  idObraSocialActual);
        request.put("idUsuario",     sessionContext.getIdUsuario());
        request.put("numeroOrden",   txtNumeroOrden.getText());
        request.put("tipoOrden",     cboTipoOrden.getValue());
        request.put("periodo",       LocalDate.now().getYear() * 100 + LocalDate.now().getMonthValue());
        request.put("sena",          0);
        request.put("practicas",     lineas);

        // Si la OS es nueva para este paciente, asignarla antes de crear la orden
        if (esObraSocialNueva && idObraSocialActual != null) {
            try {
                pacienteClient.asignarObraSocial(pacienteActual.id(), idObraSocialActual,
                        txtNumeroAfiliado.getText() != null ? txtNumeroAfiliado.getText().trim() : "");
            } catch (RuntimeException ex) {
                mensajeLabel.setText("Error al asignar obra social: " + ex.getMessage()); return;
            }
        }

        try {
            if (modoEdicion) {
                OrdenDto guardada = ordenClient.modificarCompleta(idOrdenEditando, request);
                mensajeLabel.setText("Orden " + guardada.numeroOrden() + " modificada correctamente.");
                limpiarFormulario();
            } else {
                OrdenDto ordenCreada = ordenClient.crear(request);
                mensajeLabel.setText("Orden " + ordenCreada.numeroOrden() + " creada. Abriendo comprobante...");
                abrirPdf(reporteClient.comprobanteOrden(ordenCreada.id()), "orden_" + ordenCreada.id() + ".pdf");
                limpiarFormulario();
            }
        } catch (RuntimeException ex) {
            mensajeLabel.setText("Error al guardar orden: " + ex.getMessage());
        }
    }

    private void abrirPdf(byte[] pdf, String nombreArchivo) {
        try {
            java.io.File tmp = java.io.File.createTempFile("ybioc_", "_" + nombreArchivo);
            tmp.deleteOnExit();
            try (java.io.FileOutputStream fos = new java.io.FileOutputStream(tmp)) { fos.write(pdf); }
            java.awt.Desktop.getDesktop().open(tmp);
        } catch (Exception ex) {
            mensajeLabel.setText(mensajeLabel.getText() + " (no se pudo abrir el PDF: " + ex.getMessage() + ")");
        }
    }

    @FXML
    private void limpiarFormulario() {
        modoEdicion     = false;
        idOrdenEditando = null;
        lblModoOrden.setVisible(false);
        lblModoOrden.setManaged(false);
        vboxBuscarDni.setVisible(true);
        vboxBuscarDni.setManaged(true);
        limpiarPaciente();
        txtNumeroOrden.clear();
        txtMedico.clear();
        selectedMedicoId = null;
        selectedEspecialidadId = null;
        cboEspecialidad.getItems().clear();
        practicasOrden.clear();
        txtBuscarPractica.clear();
        actualizarTotal();
        mensajeLabel.setText("");
    }

    @FXML
    private void cerrar() {
        Stage stage = (Stage) mensajeLabel.getScene().getWindow();
        stage.close();
    }

    // ── Catálogos ─────────────────────────────────────────────────

    private void cargarCatalogos() {
        try {
            todosMedicos = medicoClient.findAll();
            AutocompleteTextField.configurar(
                    txtMedico,
                    todosMedicos,
                    m -> safe(m.apellido()) + ", " + safe(m.nombre()),
                    this::seleccionarMedico);
        } catch (Exception ex) { todosMedicos = Collections.emptyList(); }

        cboEspecialidad.valueProperty().addListener((obs, o, n) -> {
            if (n == null) { selectedEspecialidadId = null; return; }
            // Busca el ID en las especialidades del médico seleccionado
            todosMedicos.stream()
                    .filter(m -> m.id().equals(selectedMedicoId))
                    .flatMap(m -> m.especialidades() != null ? m.especialidades().stream() : java.util.stream.Stream.empty())
                    .filter(e -> n.equals(e.nombre()))
                    .findFirst()
                    .ifPresent(e -> selectedEspecialidadId = e.id());
        });

        try {
            todasLasObrasSociales = obraSocialClient.findAll();
        } catch (Exception ex) { todasLasObrasSociales = Collections.emptyList(); }

        try {
            todasPracticas = practicaClient.findAll();
            AutocompleteTextField.configurar(
                    txtBuscarPractica,
                    todasPracticas,
                    p -> {
                        String cod = p.codigoPractica() != null ? String.valueOf(p.codigoPractica()) : "";
                        String nom = p.determinacion() != null ? p.determinacion() : "";
                        return cod + " - " + nom;
                    },
                    this::agregarPracticaDto);
        } catch (Exception ex) { todasPracticas = Collections.emptyList(); }
    }

    private void seleccionarMedico(MedicoDto m) {
        selectedMedicoId      = m.id();
        selectedEspecialidadId = null;
        cboEspecialidad.getItems().clear();
        cboEspecialidad.getSelectionModel().clearSelection();

        if (m.especialidades() == null || m.especialidades().isEmpty()) {
            mensajeLabel.setText("El médico " + m.apellido() + " no tiene especialidades asignadas.");
        } else {
            cboEspecialidad.getItems().setAll(
                    m.especialidades().stream().map(EspecialidadDto::nombre).toList());
            if (m.especialidades().size() == 1) {
                cboEspecialidad.getSelectionModel().selectFirst();
                selectedEspecialidadId = m.especialidades().getFirst().id();
            }
            mensajeLabel.setText("Médico: " + m.apellido() + ", " + m.nombre()
                    + "  (" + m.especialidades().size() + " especialidad(es))");
        }
    }

    private void configurarTablaPracticas() {
        colCodigo.setCellValueFactory(d -> new ReadOnlyStringWrapper(
                d.getValue().getCodigo() != null ? String.valueOf(d.getValue().getCodigo()) : ""));
        colNombre.setCellValueFactory(d -> new ReadOnlyStringWrapper(d.getValue().getNombre()));
        colPrecio.setCellValueFactory(d -> d.getValue().precioProperty());
        colCodFac.setCellValueFactory(d -> d.getValue().codFacProperty());
        colPrecio.setCellFactory(TextFieldTableCell.forTableColumn());
        colPrecio.setOnEditCommit(e -> { e.getRowValue().setPrecio(e.getNewValue()); actualizarTotal(); });
        colCodFac.setCellFactory(TextFieldTableCell.forTableColumn());
        colCodFac.setOnEditCommit(e -> e.getRowValue().setCodFac(e.getNewValue()));
        tablaPracticas.setItems(practicasOrden);
        tablaPracticas.setEditable(true);
    }

    private BigDecimal parsePrecio(String v) {
        try { return new BigDecimal(v.replace(",", ".")); }
        catch (NumberFormatException ex) { return BigDecimal.ZERO; }
    }

    private String safe(String v) { return v != null ? v : ""; }

    // ── Inner class ───────────────────────────────────────────────

    public static class LineaPractica {
        private final Integer idPractica;
        private final Integer codigo;
        private final String  nombre;
        private final StringProperty precio;
        private final StringProperty codFac;

        LineaPractica(Integer idPractica, Integer codigo, String nombre,
                      BigDecimal precio, String codFac) {
            this.idPractica = idPractica;
            this.codigo     = codigo;
            this.nombre     = nombre;
            this.precio     = new SimpleStringProperty(precio != null ? precio.toPlainString() : "0.00");
            this.codFac     = new SimpleStringProperty(codFac != null ? codFac : "");
        }

        public Integer getIdPractica()          { return idPractica; }
        public Integer getCodigo()              { return codigo; }
        public String  getNombre()              { return nombre; }
        public String  getPrecio()              { return precio.get(); }
        public String  getCodFac()              { return codFac.get(); }
        public StringProperty precioProperty()  { return precio; }
        public StringProperty codFacProperty()  { return codFac; }
        public void setPrecio(String v)         { precio.set(v); }
        public void setCodFac(String v)         { codFac.set(v); }
    }
}
