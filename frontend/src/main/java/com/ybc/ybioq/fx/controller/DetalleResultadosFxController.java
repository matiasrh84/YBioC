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
public class DetalleResultadosFxController {

    private final ObservableList<DetalleResultadoItem> resultados = FXCollections.observableArrayList();

    @FXML
    private Label lblAfiliado;

    @FXML
    private Label lblProtocolo;

    @FXML
    private TableView<DetalleResultadoItem> tablaResultados;

    @FXML
    private TableColumn<DetalleResultadoItem, String> colPractica;

    @FXML
    private TableColumn<DetalleResultadoItem, String> colAnalisis;

    @FXML
    private TableColumn<DetalleResultadoItem, String> colResultado;

    @FXML
    private TableColumn<DetalleResultadoItem, String> colObservacion;

    @FXML
    private TableColumn<DetalleResultadoItem, String> colImprime;

    @FXML
    private Label mensajeLabel;

    @FXML
    private void initialize() {
        colPractica.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().practica()));
        colAnalisis.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().analisis()));
        colResultado.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().resultado()));
        colObservacion.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().observacion()));
        colImprime.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().imprime()));
        tablaResultados.setItems(resultados);
        cargarDemo();
    }

    @FXML
    private void salir() {
        Stage stage = (Stage) tablaResultados.getScene().getWindow();
        stage.close();
    }

    private void cargarDemo() {
        lblAfiliado.setText("-");
        lblProtocolo.setText("-");
        resultados.clear();
        mensajeLabel.setText("Formulario listo para conectar resultados del backend.");
    }

    private record DetalleResultadoItem(
            String practica,
            String analisis,
            String resultado,
            String observacion,
            String imprime
    ) {
    }
}
