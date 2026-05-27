package com.ybc.ybioq.fx.controller;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class DetalleTxtFxController {

    private final ObservableList<DetalleTxtItem> practicas = FXCollections.observableArrayList();

    @FXML
    private Label lblObraSocial;

    @FXML
    private Label lblAfiliado;

    @FXML
    private Label lblFecha;

    @FXML
    private Label lblTotal;

    @FXML
    private TableView<DetalleTxtItem> tablaPracticas;

    @FXML
    private TableColumn<DetalleTxtItem, String> colCodigo;

    @FXML
    private TableColumn<DetalleTxtItem, String> colPractica;

    @FXML
    private TableColumn<DetalleTxtItem, String> colCantidad;

    @FXML
    private TableColumn<DetalleTxtItem, String> colImporte;

    @FXML
    private Label mensajeLabel;

    @FXML
    private void initialize() {
        colCodigo.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().codigo()));
        colPractica.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().practica()));
        colCantidad.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().cantidad()));
        colImporte.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().importe()));
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
        lblAfiliado.setText("-");
        lblFecha.setText("-");
        lblTotal.setText("$ 0.00");
        practicas.clear();
        mensajeLabel.setText("Formulario listo para conectar el detalle TXT del backend.");
    }

    private record DetalleTxtItem(
            String codigo,
            String practica,
            String cantidad,
            String importe
    ) {
    }
}
