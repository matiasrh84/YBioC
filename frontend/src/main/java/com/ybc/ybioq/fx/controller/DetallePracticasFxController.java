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
public class DetallePracticasFxController {

    private final ObservableList<DetallePracticaItem> practicas = FXCollections.observableArrayList();

    @FXML
    private Label lblNumeroAfiliado;

    @FXML
    private Label lblAfiliado;

    @FXML
    private Label lblFecha;

    @FXML
    private Label lblProtocolo;

    @FXML
    private TableView<DetallePracticaItem> tablaPracticas;

    @FXML
    private TableColumn<DetallePracticaItem, String> colCodigo;

    @FXML
    private TableColumn<DetallePracticaItem, String> colPractica;

    @FXML
    private TableColumn<DetallePracticaItem, String> colResultado;

    @FXML
    private TableColumn<DetallePracticaItem, String> colNumeroOrden;

    @FXML
    private TableColumn<DetallePracticaItem, String> colObraSocial;

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
        cargarDemo();
    }

    @FXML
    private void salir() {
        Stage stage = (Stage) tablaPracticas.getScene().getWindow();
        stage.close();
    }

    private void cargarDemo() {
        lblNumeroAfiliado.setText("-");
        lblAfiliado.setText("-");
        lblFecha.setText("-");
        lblProtocolo.setText("-");
        practicas.clear();
        mensajeLabel.setText("Formulario listo para conectar datos del backend.");
    }

    private record DetallePracticaItem(
            String codigo,
            String practica,
            String resultado,
            String numeroOrden,
            String obraSocial
    ) {
    }
}
