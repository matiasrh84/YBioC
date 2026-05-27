package com.ybc.ybioq.fx.controller;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class DetallePracticasFacturacionFxController {

    private final ObservableList<DetalleFacturacionItem> practicas = FXCollections.observableArrayList();

    @FXML
    private Label lblObraSocial;

    @FXML
    private Label lblNumeroAfiliado;

    @FXML
    private Label lblAfiliado;

    @FXML
    private Label lblFecha;

    @FXML
    private Label lblCoseguro;

    @FXML
    private Label lblTotal;

    @FXML
    private TextArea observacionesArea;

    @FXML
    private TableView<DetalleFacturacionItem> tablaPracticas;

    @FXML
    private TableColumn<DetalleFacturacionItem, String> colCodigo;

    @FXML
    private TableColumn<DetalleFacturacionItem, String> colPractica;

    @FXML
    private TableColumn<DetalleFacturacionItem, String> colResultado;

    @FXML
    private TableColumn<DetalleFacturacionItem, String> colNumeroOrden;

    @FXML
    private TableColumn<DetalleFacturacionItem, String> colObraSocial;

    @FXML
    private Label mensajeLabel;

    @FXML
    private void initialize() {
        colCodigo.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().codigo()));
        colPractica.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().practica()));
        colResultado.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().resultado()));
        colNumeroOrden.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().numeroOrden()));
        colObraSocial.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().obraSocial()));
        tablaPracticas.setItems(practicas);
        cargarInicial();
    }

    @FXML
    private void salir() {
        Stage stage = (Stage) tablaPracticas.getScene().getWindow();
        stage.close();
    }

    private void cargarInicial() {
        lblObraSocial.setText("-");
        lblNumeroAfiliado.setText("-");
        lblAfiliado.setText("-");
        lblFecha.setText("-");
        lblCoseguro.setText("$ 0.00");
        lblTotal.setText("$ 0.00");
        observacionesArea.clear();
        practicas.clear();
        mensajeLabel.setText("Formulario listo para conectar datos de facturacion del backend.");
    }

    private record DetalleFacturacionItem(
            String codigo,
            String practica,
            String resultado,
            String numeroOrden,
            String obraSocial
    ) {
    }
}
