package com.ybc.ybioq.fx.controller;

import com.ybc.ybioq.fx.dialog.FxDialogService;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class EnviarFacturacionFxController {

    private final FxDialogService dialogService;
    private final ObservableList<OrdenFacturacionItem> ordenes = FXCollections.observableArrayList();
    private final ObservableList<DetalleOrdenFacturacionItem> detalles = FXCollections.observableArrayList();

    @FXML
    private Label lblBioquimico;

    @FXML
    private Label lblMatricula;

    @FXML
    private Label lblPeriodo;

    @FXML
    private CheckBox chkObraSocial;

    @FXML
    private DatePicker dcDesde;

    @FXML
    private DatePicker dcHasta;

    @FXML
    private TextField txtSeleccionarOrdenes;

    @FXML
    private Label lblTotalOrdenes;

    @FXML
    private Label lblTotalPesos;

    @FXML
    private Label mensajeLabel;

    @FXML
    private ProgressBar progreso;

    @FXML
    private TableView<OrdenFacturacionItem> tablaOrdenes;

    @FXML
    private TableColumn<OrdenFacturacionItem, String> colOrden;

    @FXML
    private TableColumn<OrdenFacturacionItem, String> colPeriodo;

    @FXML
    private TableColumn<OrdenFacturacionItem, String> colObraSocial;

    @FXML
    private TableColumn<OrdenFacturacionItem, String> colAfiliado;

    @FXML
    private TableColumn<OrdenFacturacionItem, String> colNumeroOrden;

    @FXML
    private TableColumn<OrdenFacturacionItem, String> colFecha;

    @FXML
    private TableColumn<OrdenFacturacionItem, String> colTotal;

    @FXML
    private TableColumn<OrdenFacturacionItem, Boolean> colEnviar;

    @FXML
    private TableColumn<OrdenFacturacionItem, String> colEstado;

    @FXML
    private TableView<DetalleOrdenFacturacionItem> tablaDetalle;

    @FXML
    private TableColumn<DetalleOrdenFacturacionItem, String> colDetalleOrden;

    @FXML
    private TableColumn<DetalleOrdenFacturacionItem, String> colDetalleObraSocial;

    @FXML
    private TableColumn<DetalleOrdenFacturacionItem, String> colDetalleAfiliado;

    @FXML
    private TableColumn<DetalleOrdenFacturacionItem, String> colDetalleFecha;

    @FXML
    private TableColumn<DetalleOrdenFacturacionItem, String> colDetalleTotal;

    public EnviarFacturacionFxController(FxDialogService dialogService) {
        this.dialogService = dialogService;
    }

    @FXML
    private void initialize() {
        configurarTablaOrdenes();
        configurarTablaDetalle();
        tablaOrdenes.setItems(ordenes);
        tablaDetalle.setItems(detalles);
        cargarInicial();
    }

    @FXML
    private void enviarCbt() {
        if (ordenes.isEmpty()) {
            dialogService.advertir("No hay ordenes para enviar.");
            return;
        }
        progreso.setProgress(1);
        mensajeLabel.setText("Envio a CBT listo para conectar con el backend.");
    }

    @FXML
    private void exportarExcel() {
        if (ordenes.isEmpty()) {
            dialogService.advertir("No hay ordenes para exportar.");
            return;
        }
        mensajeLabel.setText("Exportacion a Excel lista para conectar con el backend.");
    }

    @FXML
    private void salir() {
        Stage stage = (Stage) tablaOrdenes.getScene().getWindow();
        stage.close();
    }

    private void configurarTablaOrdenes() {
        colOrden.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().orden()));
        colPeriodo.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().periodo()));
        colObraSocial.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().obraSocial()));
        colAfiliado.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().afiliado()));
        colNumeroOrden.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().numeroOrden()));
        colFecha.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().fecha()));
        colTotal.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().total()));
        colEnviar.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().enviar()));
        colEstado.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().estado()));
    }

    private void configurarTablaDetalle() {
        colDetalleOrden.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().orden()));
        colDetalleObraSocial.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().obraSocial()));
        colDetalleAfiliado.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().afiliado()));
        colDetalleFecha.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().fecha()));
        colDetalleTotal.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().total()));
    }

    private void cargarInicial() {
        lblBioquimico.setText("-");
        lblMatricula.setText("-");
        lblPeriodo.setText("-");
        chkObraSocial.setSelected(false);
        txtSeleccionarOrdenes.clear();
        lblTotalOrdenes.setText("0");
        lblTotalPesos.setText("$ 0.00");
        progreso.setProgress(0);
        ordenes.clear();
        detalles.clear();
        mensajeLabel.setText("Formulario listo para conectar facturacion con el backend.");
    }

    private record OrdenFacturacionItem(
            String orden,
            String periodo,
            String obraSocial,
            String afiliado,
            String numeroOrden,
            String fecha,
            String total,
            boolean enviar,
            String estado
    ) {
    }

    private record DetalleOrdenFacturacionItem(
            String orden,
            String obraSocial,
            String afiliado,
            String fecha,
            String total
    ) {
    }
}
