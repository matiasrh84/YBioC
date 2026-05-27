package com.ybc.ybioq.fx.controller;

import com.ybc.ybioq.fx.client.AnalisisClient;
import com.ybc.ybioq.fx.client.DerivacionClient;
import com.ybc.ybioq.fx.client.UnidadClient;
import com.ybc.ybioq.fx.client.dto.AnalisisDto;
import com.ybc.ybioq.fx.client.dto.DerivacionDto;
import com.ybc.ybioq.fx.client.dto.ParametroDto;
import com.ybc.ybioq.fx.client.dto.UnidadDto;
import com.ybc.ybioq.fx.navigation.FxNavigationService;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AnalisisControllerNewFx {

    private final AnalisisClient analisisClient;
    private final DerivacionClient derivacionClient;
    private final UnidadClient unidadClient;
    private final FxNavigationService navigationService;
    private final ObservableList<PrioridadItem> prioridades = FXCollections.observableArrayList();
    private final ObservableList<TituloItem> titulos = FXCollections.observableArrayList();

    @FXML
    private TextField txtPractica;

    @FXML
    private Label lblPractica;

    @FXML
    private VBox panelGeneral;

    @FXML
    private HBox panelAnalisis;

    @FXML
    private VBox panelInforme;

    @FXML
    private TextArea txtInstrucciones;

    @FXML
    private ComboBox<String> cboSeccion;

    @FXML
    private ComboBox<String> cboTipo;

    @FXML
    private ComboBox<String> cboTiempoProcesamiento;

    @FXML
    private CheckBox swDerivacion;

    @FXML
    private ComboBox<String> cboDerivaciones;

    @FXML
    private Label lblPrioridadGeneral;

    @FXML
    private TreeView<String> arbolPracticas;

    @FXML
    private TableView<PrioridadItem> tablaPioridadPractica;

    @FXML
    private TableColumn<PrioridadItem, String> colNombrePrioridad;

    @FXML
    private TableColumn<PrioridadItem, String> colValorPrioridad;

    @FXML
    private CheckBox chkUnSoloMetodo;

    @FXML
    private TextField txtMetodo;

    @FXML
    private TextField txtTitulo;

    @FXML
    private TextField txtFormatoMesada;

    @FXML
    private TextField txtNombreInforme;

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
    private CheckBox chkFormula;

    @FXML
    private Button btnCargarFormula;

    @FXML
    private TextField txtformula;

    @FXML
    private ComboBox<String> cboSexo4;

    @FXML
    private TextArea txtValoresReferencia;

    @FXML
    private TreeView<String> jTree1;

    @FXML
    private Label lblPracticaInforme;

    @FXML
    private Label lblSeccionInforme;

    @FXML
    private Label lblPrioridadInforme;

    @FXML
    private TableView<TituloItem> tablaTitulos;

    @FXML
    private TableColumn<TituloItem, String> colIdAnalisisTitulo;

    @FXML
    private TableColumn<TituloItem, String> colIdTitulo;

    @FXML
    private TableColumn<TituloItem, String> colTitulo;

    @FXML
    private TableColumn<TituloItem, String> colAnalisisTitulo;

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

    @FXML
    private Label mensajeLabel;

    public AnalisisControllerNewFx(AnalisisClient analisisClient,
                                   DerivacionClient derivacionClient,
                                   UnidadClient unidadClient,
                                   FxNavigationService navigationService) {
        this.analisisClient = analisisClient;
        this.derivacionClient = derivacionClient;
        this.unidadClient = unidadClient;
        this.navigationService = navigationService;
    }

    @FXML
    public void initialize() {
        configurarCombos();
        cargarComboUnidades();
        configurarTablaPrioridades();
        configurarTablaTitulos();
        configurarEstadosIniciales();
        configurarTipoResultadoInicial();
        cargarDatosIniciales();
        mostrarGeneral();
    }

    @FXML
    private void actualizarPractica() {
        String practica = text(txtPractica);
        if (practica.isBlank()) {
            return;
        }
        lblPractica.setText(practica);
        lblPracticaInforme.setText(practica);
        txtPractica.clear();
        actualizarArboles();
        mensajeLabel.setText("Practica seleccionada: " + practica);
    }

    @FXML
    private void mostrarGeneral() {
        mostrarPanel(panelGeneral);
    }

    @FXML
    private void mostrarAnalisis() {
        mostrarPanel(panelAnalisis);
    }

    @FXML
    private void mostrarInforme() {
        refrescarDatosInforme();
        mostrarPanel(panelInforme);
    }

    @FXML
    private void siguienteDesdeGeneral() {
        mostrarAnalisis();
    }

    @FXML
    private void siguienteDesdeAnalisis() {
        refrescarDatosInforme();
        mostrarInforme();
    }

    @FXML
    private void volverAnalisisDesdeInforme() {
        mostrarAnalisis();
    }

    @FXML
    private void actualizarInformacion() {
        actualizarArboles();
        refrescarDatosInforme();
        mensajeLabel.setText("Informacion de practica actualizada.");
    }

    @FXML
    private void accionDerivaciones() {
        navigationService.showDerivaciones();
        cargarComboDerivaciones();
        mensajeLabel.setText("Derivaciones actualizadas.");
    }

    @FXML
    private void accionMetodo() {
        navigationService.showMetodos();
        txtMetodo.requestFocus();
        mensajeLabel.setText("Catalogo de metodos actualizado.");
    }

    @FXML
    private void accionTitulo() {
        navigationService.showTitulos();
        txtTitulo.requestFocus();
        mensajeLabel.setText("Catalogo de titulos actualizado.");
    }

    @FXML
    private void editarResultados() {
        txtValoresReferencia.requestFocus();
        mensajeLabel.setText("Edicion de resultados lista.");
    }

    @FXML
    private void seleccionarResultadoNumerico() {
        cbounidad.setDisable(false);
        cboPosneg.setDisable(true);
        txtPositivoNegativoNumerico.setDisable(true);
        cboUnidadPositivoNegativo.setDisable(true);
        cboTexto.setDisable(false);
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
        cboTexto.setDisable(false);
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
        cboTexto.setDisable(false);
        if (!cboUnidadPositivoNegativo.getItems().isEmpty()) {
            cboUnidadPositivoNegativo.getSelectionModel().selectFirst();
        }
        txtPositivoNegativoNumerico.clear();
        txtformula.clear();
        btnCargarFormulaSetDisabled(true);
    }

    @FXML
    private void toggleFormula() {
        if (!chkFormula.isSelected()) {
            txtformula.clear();
        }
        btnCargarFormulaSetDisabled(!chkFormula.isSelected());
    }

    @FXML
    private void cargarFormula() {
        TextInputDialog dialog = new TextInputDialog(txtformula.getText());
        dialog.setTitle("Cargar formula");
        dialog.setHeaderText("Defina la formula del resultado");
        dialog.setContentText("Formula:");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(value -> {
            txtformula.setText(value.trim());
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
    private void agregarTipoResultado() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Tipo de Resultado");
        dialog.setHeaderText("Agregar nuevo tipo de resultado");
        dialog.setContentText("Nombre:");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(value -> {
            String nuevo = value.trim();
            if (nuevo.isBlank()) {
                return;
            }
            if (!cboTexto.getItems().contains(nuevo)) {
                cboTexto.getItems().add(nuevo);
            }
            cboTexto.getSelectionModel().select(nuevo);
            mensajeLabel.setText("Tipo de resultado agregado.");
        });
    }

    @FXML
    private void agregarParametro() {
        String nombre = text(txtNombreParametro);
        if (nombre.isBlank()) {
            nombre = "Nuevo parametro";
        }

        PrioridadItem item = new PrioridadItem(nombre, String.valueOf(prioridades.size() + 1));
        prioridades.add(item);
        actualizarArboles();
        refrescarDatosInforme();
        tablaPioridadPractica.getSelectionModel().select(item);
        mensajeLabel.setText("Parametro agregado.");
    }

    @FXML
    private void borrarParametro() {
        PrioridadItem seleccionado = prioridadSeleccionada();
        if (seleccionado == null) {
            mensajeLabel.setText("Seleccione un parametro para borrar.");
            return;
        }

        prioridades.remove(seleccionado);
        renumerarPrioridades();
        actualizarArboles();
        refrescarDatosInforme();
        mensajeLabel.setText("Parametro eliminado.");
    }

    @FXML
    private void limpiarCampos() {
        txtNombreParametro.clear();
        txtUnidad.clear();
        txtValoresReferencia.clear();
        txtMetodo.clear();
        txtTitulo.clear();
        txtFormatoMesada.clear();
        txtNombreInforme.clear();
        txtPositivoNegativoNumerico.clear();
        txtformula.clear();
        chkFormula.setSelected(false);
        if (!cboPosneg.getItems().isEmpty()) {
            cboPosneg.getSelectionModel().selectFirst();
        }
        if (!cboTexto.getItems().isEmpty()) {
            cboTexto.getSelectionModel().selectFirst();
        }
        if (!cboUnidadPositivoNegativo.getItems().isEmpty()) {
            cboUnidadPositivoNegativo.getSelectionModel().selectFirst();
        }
        if (radionum != null) {
            radionum.setSelected(true);
        }
        seleccionarResultadoNumerico();
        txtPreviewInforme.clear();
        if (!cboSexo4.getItems().isEmpty()) {
            cboSexo4.getSelectionModel().selectFirst();
        }
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

    @FXML
    private void agregarTitulo() {
        PrioridadItem prioridad = prioridadSeleccionada();
        if (prioridad == null) {
            mensajeLabel.setText("Seleccione un analisis o parametro para agregar titulo.");
            return;
        }

        int idTitulo = titulos.size() + 1;
        String nombreTitulo = text(txtTitulo);
        if (nombreTitulo.isBlank()) {
            nombreTitulo = "Titulo " + idTitulo;
        }

        TituloItem item = new TituloItem(
                String.valueOf(idTitulo),
                String.valueOf(idTitulo),
                nombreTitulo,
                prioridad.getNombre());
        titulos.add(item);
        tablaTitulos.getSelectionModel().select(item);
        mensajeLabel.setText("Titulo agregado.");
    }

    @FXML
    private void modificarPrioridadInforme() {
        if (prioridades.isEmpty()) {
            mensajeLabel.setText("No hay prioridades para actualizar.");
            return;
        }

        prioridades.sort(Comparator.comparingInt(item -> parseInt(item.getPrioridad(), Integer.MAX_VALUE)));
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
        sb.append("Tiempo: ").append(value(cboTiempoProcesamiento)).append(" dias").append('\n');
        sb.append("Derivacion: ").append(swDerivacion.isSelected() ? value(cboDerivaciones) : "No").append('\n');
        sb.append("Tipo resultado: ").append(tipoResultadoActual()).append('\n');
        if (!unidadResultadoSeleccionada().isBlank()) {
            sb.append("Unidad resultado: ").append(unidadResultadoSeleccionada()).append('\n');
        }
        if (!text(txtPositivoNegativoNumerico).isBlank()) {
            sb.append("Valor +/- numerico: ").append(text(txtPositivoNegativoNumerico)).append('\n');
        }
        if (chkFormula.isSelected()) {
            sb.append("Formula: ").append(text(txtformula)).append('\n');
        }
        sb.append('\n');

        if (titulos.isEmpty()) {
            sb.append("Titulos: sin titulos cargados").append('\n');
        } else {
            sb.append("Titulos:\n");
            for (TituloItem titulo : titulos) {
                sb.append(" - [").append(titulo.getIdTitulo()).append("] ")
                        .append(titulo.getTitulo()).append(" -> ")
                        .append(titulo.getAnalisis()).append('\n');
            }
        }

        sb.append('\n').append("Orden de analisis:\n");
        for (PrioridadItem prioridad : prioridades) {
            sb.append(" ").append(prioridad.getPrioridad()).append(". ")
                    .append(prioridad.getNombre()).append('\n');
        }

        txtPreviewInforme.setText(sb.toString());
        mensajeLabel.setText("Previsualizacion generada.");
    }

    @FXML
    private void guardar() {
        try {
            AnalisisDto dto = construirDto();
            analisisClient.save(dto);
            mensajeLabel.setText("Analisis guardado.");
        } catch (RuntimeException ex) {
            mensajeLabel.setText("No se pudo guardar: " + ex.getMessage());
        }
    }

    @FXML
    private void finalizarProceso() {
        txtPractica.clear();
        txtInstrucciones.clear();
        txtMetodo.clear();
        txtTitulo.clear();
        txtFormatoMesada.clear();
        txtNombreInforme.clear();
        txtNombreParametro.clear();
        txtUnidad.clear();
        txtValoresReferencia.clear();
        txtPositivoNegativoNumerico.clear();
        txtformula.clear();
        chkFormula.setSelected(false);
        cboTexto.getSelectionModel().selectFirst();
        cboPosneg.getSelectionModel().selectFirst();
        cboUnidadPositivoNegativo.getSelectionModel().selectFirst();
        if (!cbounidad.getItems().isEmpty()) {
            cbounidad.getSelectionModel().selectFirst();
        }
        seleccionarResultadoNumerico();
        txtPreviewInforme.clear();
        swDerivacion.setSelected(false);
        actualizarEstadoDerivacion();
        titulos.clear();
        cargarDatosIniciales();
        mostrarGeneral();
        mensajeLabel.setText("Formulario reiniciado.");
    }

    @FXML
    private void cerrarVentana() {
        Stage stage = (Stage) lblPractica.getScene().getWindow();
        if (stage != null) {
            stage.close();
        }
    }

    private void configurarCombos() {
        cboSeccion.getItems().setAll(
                "Bioquimica general",
                "Hematologia",
                "Hormonas",
                "Bacteriologia",
                "Inmunologia");

        cboTipo.getItems().setAll(
                "Filas",
                "Columnas",
                "Proteinas",
                "Cultivo 1",
                "Cultivo 2",
                "Cultivo 3",
                "Hemograma fijo",
                "Hemograma filas");

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

    private void cargarComboDerivaciones() {
        String seleccionActual = value(cboDerivaciones);
        try {
            cboDerivaciones.getItems().setAll(
                    derivacionClient.findAll().stream()
                            .map(DerivacionDto::getNombre)
                            .filter(nombre -> nombre != null && !nombre.isBlank())
                            .sorted(String::compareToIgnoreCase)
                            .toList());
        } catch (RuntimeException ex) {
            cboDerivaciones.getItems().clear();
        }

        if (!seleccionActual.isBlank() && cboDerivaciones.getItems().contains(seleccionActual)) {
            cboDerivaciones.getSelectionModel().select(seleccionActual);
            return;
        }
        if (!cboDerivaciones.getItems().isEmpty()) {
            cboDerivaciones.getSelectionModel().selectFirst();
        }
    }

    private void cargarComboUnidades() {
        try {
            cbounidad.getItems().setAll(
                    unidadClient.findAll().stream()
                            .filter(UnidadDto::isEstado)
                            .map(UnidadDto::getNombre)
                            .sorted(String::compareToIgnoreCase)
                            .toList());
        } catch (RuntimeException ex) {
            cbounidad.getItems().setAll("mg/dl", "UI/L", "%");
        }
        if (!cbounidad.getItems().isEmpty()) {
            cbounidad.getSelectionModel().selectFirst();
        }
    }

    private void configurarEstadosIniciales() {
        cargarComboDerivaciones();
        swDerivacion.selectedProperty().addListener((obs, oldValue, newValue) -> actualizarEstadoDerivacion());
        actualizarEstadoDerivacion();

        cboSeccion.valueProperty().addListener((obs, oldValue, newValue) -> lblSeccionInforme.setText(value(cboSeccion)));
    }

    private void configurarTipoResultadoInicial() {
        if (radionum != null) {
            radionum.setSelected(true);
        }
        if (chkFormula != null) {
            chkFormula.setSelected(false);
        }
        seleccionarResultadoNumerico();
    }

    private void actualizarEstadoDerivacion() {
        boolean habilitado = swDerivacion.isSelected();
        cboDerivaciones.setDisable(!habilitado);
    }

    private void btnCargarFormulaSetDisabled(boolean disabled) {
        if (btnCargarFormula == null) {
            return;
        }
        btnCargarFormula.setDisable(disabled);
    }

    private void configurarTablaPrioridades() {
        colNombrePrioridad.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getNombre()));
        colValorPrioridad.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getPrioridad()));
        tablaPioridadPractica.setItems(prioridades);
        tablaPioridadPractica.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, selected) -> {
            if (selected == null) {
                return;
            }
            lblPrioridadGeneral.setText(selected.getPrioridad());
            txtNombreParametro.setText(selected.getNombre());
            lblPrioridadInforme.setText(selected.getPrioridad());
        });

        colNombrePrioridadInforme.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getNombre()));
        colPrioridadInforme.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getPrioridad()));
        tablaPrioridadesInforme.setItems(prioridades);
        tablaPrioridadesInforme.setEditable(true);
        colPrioridadInforme.setCellFactory(TextFieldTableCell.forTableColumn());
        colPrioridadInforme.setOnEditCommit(event -> {
            PrioridadItem item = event.getRowValue();
            item.setPrioridad(event.getNewValue());
            lblPrioridadInforme.setText(event.getNewValue());
        });
        tablaPrioridadesInforme.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, selected) -> {
            if (selected != null) {
                lblPrioridadInforme.setText(selected.getPrioridad());
            }
        });
    }

    private void configurarTablaTitulos() {
        colIdAnalisisTitulo.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getIdAnalisis()));
        colIdTitulo.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getIdTitulo()));
        colTitulo.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getTitulo()));
        colAnalisisTitulo.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getAnalisis()));
        tablaTitulos.setItems(titulos);
    }

    private void cargarDatosIniciales() {
        prioridades.setAll(
                new PrioridadItem("Hemoglobina", "1"),
                new PrioridadItem("Hematocrito", "2"),
                new PrioridadItem("Glucosa", "3"));
        lblPractica.setText("Nueva practica");
        lblPracticaInforme.setText("Nueva practica");
        lblSeccionInforme.setText(value(cboSeccion));
        lblPrioridadGeneral.setText("1");
        lblPrioridadInforme.setText("1");
        tablaPioridadPractica.getSelectionModel().selectFirst();
        mensajeLabel.setText("");
        actualizarArboles();
    }

    private void refrescarDatosInforme() {
        lblPracticaInforme.setText(practicaActual());
        lblSeccionInforme.setText(value(cboSeccion));
        PrioridadItem selected = prioridadSeleccionada();
        lblPrioridadInforme.setText(selected == null ? "-" : selected.getPrioridad());
        tablaPrioridadesInforme.refresh();
        tablaTitulos.refresh();
    }

    private void actualizarArboles() {
        String practica = practicaActual();
        TreeItem<String> rootGeneral = new TreeItem<>("Practica: " + practica);
        rootGeneral.setExpanded(true);
        for (PrioridadItem item : prioridades) {
            rootGeneral.getChildren().add(new TreeItem<>(item.getPrioridad() + " - " + item.getNombre()));
        }
        arbolPracticas.setRoot(rootGeneral);

        TreeItem<String> rootAnalisis = new TreeItem<>("Analisis: " + practica);
        rootAnalisis.setExpanded(true);
        for (PrioridadItem item : prioridades) {
            rootAnalisis.getChildren().add(new TreeItem<>(item.getNombre()));
        }
        jTree1.setRoot(rootAnalisis);

        TreeItem<String> rootInforme = new TreeItem<>("Prioridades");
        rootInforme.setExpanded(true);
        for (PrioridadItem item : prioridades) {
            rootInforme.getChildren().add(new TreeItem<>(item.getPrioridad() + " - " + item.getNombre()));
        }
        treePrioridades.setRoot(rootInforme);
    }

    private void moverPrioridad(int delta) {
        PrioridadItem selected = prioridadSeleccionada();
        if (selected == null) {
            mensajeLabel.setText("Seleccione un parametro.");
            return;
        }

        int selectedIndex = prioridades.indexOf(selected);
        int targetIndex = selectedIndex + delta;
        if (targetIndex < 0 || targetIndex >= prioridades.size()) {
            return;
        }

        prioridades.remove(selectedIndex);
        prioridades.add(targetIndex, selected);
        renumerarPrioridades();
        tablaPioridadPractica.getSelectionModel().select(selected);
        tablaPrioridadesInforme.getSelectionModel().select(selected);
        actualizarArboles();
    }

    private void renumerarPrioridades() {
        for (int i = 0; i < prioridades.size(); i++) {
            prioridades.get(i).setPrioridad(String.valueOf(i + 1));
        }
        tablaPioridadPractica.refresh();
        tablaPrioridadesInforme.refresh();
    }

    private PrioridadItem prioridadSeleccionada() {
        PrioridadItem selectedInforme = tablaPrioridadesInforme.getSelectionModel().getSelectedItem();
        if (selectedInforme != null) {
            return selectedInforme;
        }
        return tablaPioridadPractica.getSelectionModel().getSelectedItem();
    }

    private void mostrarPanel(Node panelObjetivo) {
        panelGeneral.setVisible(false);
        panelGeneral.setManaged(false);
        panelAnalisis.setVisible(false);
        panelAnalisis.setManaged(false);
        panelInforme.setVisible(false);
        panelInforme.setManaged(false);

        panelObjetivo.setVisible(true);
        panelObjetivo.setManaged(true);
    }

    private AnalisisDto construirDto() {
        AnalisisDto dto = new AnalisisDto();
        dto.setNombre(practicaActual());
        dto.setNombreInforme(text(txtNombreInforme));
        dto.setMetodo(text(txtMetodo));
        dto.setInstrucciones(textArea(txtInstrucciones));
        dto.setDerivacion(swDerivacion.isSelected());
        dto.setFormatoMesada(text(txtFormatoMesada));

        String detalleResultado = detalleResultadoSeleccionado();
        String unidadResultado = unidadResultadoSeleccionada();

        for (PrioridadItem item : prioridades) {
            ParametroDto parametro = new ParametroDto();
            parametro.setNombre(item.getNombre());
            parametro.setValorReferencia(detalleResultado);
            parametro.setUnidad(unidadResultado.isBlank() ? text(txtUnidad) : unidadResultado);
            parametro.setSexo(value(cboSexo4));
            dto.getParametros().add(parametro);
        }
        return dto;
    }

    private String detalleResultadoSeleccionado() {
        StringBuilder sb = new StringBuilder();
        sb.append("Tipo: ").append(tipoResultadoActual());
        String valoresRef = textArea(txtValoresReferencia);
        if (!valoresRef.isBlank()) {
            sb.append(" | Ref: ").append(valoresRef);
        }
        if (radioposneg != null && radioposneg.isSelected()) {
            sb.append(" | Estado: ").append(value(cboPosneg));
        }
        if (radioposnegnum != null && radioposnegnum.isSelected()) {
            sb.append(" | Valor: ").append(text(txtPositivoNegativoNumerico));
        }
        if (chkFormula != null && chkFormula.isSelected() && !text(txtformula).isBlank()) {
            sb.append(" | Formula: ").append(text(txtformula));
        }
        return sb.toString();
    }

    private String unidadResultadoSeleccionada() {
        if (radionum != null && radionum.isSelected()) {
            return value(cbounidad);
        }
        if (radioposnegnum != null && radioposnegnum.isSelected()) {
            return value(cboUnidadPositivoNegativo);
        }
        return "";
    }

    private String tipoResultadoActual() {
        if (radionum != null && radionum.isSelected()) {
            return "Numerico";
        }
        if (radioposneg != null && radioposneg.isSelected()) {
            return "Positivo/Negativo";
        }
        if (radioposnegnum != null && radioposnegnum.isSelected()) {
            return "+/- y Numerico";
        }
        return value(cboTexto);
    }

    private String practicaActual() {
        String practica = lblPractica.getText();
        if (practica == null || practica.isBlank() || "-".equals(practica)) {
            return "Sin nombre";
        }
        return practica;
    }

    private int parseInt(String value, int fallback) {
        try {
            return Integer.parseInt(value == null ? "" : value.trim());
        } catch (NumberFormatException ex) {
            return fallback;
        }
    }

    private String text(TextField field) {
        return field.getText() == null ? "" : field.getText().trim();
    }

    private String textArea(TextArea area) {
        return area.getText() == null ? "" : area.getText().trim();
    }

    private String value(ComboBox<String> comboBox) {
        return comboBox.getValue() == null ? "" : comboBox.getValue();
    }

    private static class PrioridadItem {
        private String nombre;
        private String prioridad;

        private PrioridadItem(String nombre, String prioridad) {
            this.nombre = nombre;
            this.prioridad = prioridad;
        }

        public String getNombre() {
            return nombre;
        }

        public String getPrioridad() {
            return prioridad;
        }

        public void setPrioridad(String prioridad) {
            this.prioridad = prioridad;
        }
    }

    private static class TituloItem {
        private final String idAnalisis;
        private final String idTitulo;
        private final String titulo;
        private final String analisis;

        private TituloItem(String idAnalisis, String idTitulo, String titulo, String analisis) {
            this.idAnalisis = Objects.requireNonNullElse(idAnalisis, "");
            this.idTitulo = Objects.requireNonNullElse(idTitulo, "");
            this.titulo = Objects.requireNonNullElse(titulo, "");
            this.analisis = Objects.requireNonNullElse(analisis, "");
        }

        public String getIdAnalisis() {
            return idAnalisis;
        }

        public String getIdTitulo() {
            return idTitulo;
        }

        public String getTitulo() {
            return titulo;
        }

        public String getAnalisis() {
            return analisis;
        }
    }
}
