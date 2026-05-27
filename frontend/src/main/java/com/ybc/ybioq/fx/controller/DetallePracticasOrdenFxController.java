package com.ybc.ybioq.fx.controller;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class DetallePracticasOrdenFxController {

    private final ObservableList<DetallePracticaOrdenItem> practicas = FXCollections.observableArrayList();

    @FXML
    private Label lblNumeroAfiliado;

    @FXML
    private Label lblAfiliado;

    @FXML
    private Label lblFecha;

    @FXML
    private Label lblProtocolo;

    @FXML
    private Label lblServicio;

    @FXML
    private Label lblHora;

    @FXML
    private Label lblTotal;

    @FXML
    private TableView<DetallePracticaOrdenItem> tablaPracticas;

    @FXML
    private TableColumn<DetallePracticaOrdenItem, String> colCodigo;

    @FXML
    private TableColumn<DetallePracticaOrdenItem, String> colPractica;

    @FXML
    private TableColumn<DetallePracticaOrdenItem, String> colResultado;

    @FXML
    private TableColumn<DetallePracticaOrdenItem, String> colNumeroOrden;

    @FXML
    private TableColumn<DetallePracticaOrdenItem, String> colObraSocial;

    @FXML
    private Label mensajeLabel;

    @FXML
    private void initialize() {
        colCodigo.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getCodigo()));
        colPractica.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getPractica()));
        colResultado.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getResultado()));
        colNumeroOrden.setCellValueFactory(data -> data.getValue().numeroOrdenProperty());
        colObraSocial.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getObraSocial()));
        colNumeroOrden.setCellFactory(TextFieldTableCell.forTableColumn());
        colNumeroOrden.setOnEditCommit(event -> {
            event.getRowValue().setNumeroOrden(event.getNewValue());
            mensajeLabel.setText("Numero de orden actualizado en pantalla. Pendiente de persistir en backend.");
        });
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
        lblServicio.setText("-");
        lblHora.setText("-");
        lblTotal.setText("-");
        practicas.clear();
        mensajeLabel.setText("Formulario listo para conectar datos de la orden del backend.");
    }

    private static final class DetallePracticaOrdenItem {
        private final String codigo;
        private final String practica;
        private final String resultado;
        private final StringProperty numeroOrden;
        private final String obraSocial;

        private DetallePracticaOrdenItem(
                String codigo,
                String practica,
                String resultado,
                String numeroOrden,
                String obraSocial
        ) {
            this.codigo = codigo;
            this.practica = practica;
            this.resultado = resultado;
            this.numeroOrden = new SimpleStringProperty(numeroOrden);
            this.obraSocial = obraSocial;
        }

        private String getCodigo() {
            return codigo;
        }

        private String getPractica() {
            return practica;
        }

        private String getResultado() {
            return resultado;
        }

        private StringProperty numeroOrdenProperty() {
            return numeroOrden;
        }

        private void setNumeroOrden(String numeroOrden) {
            this.numeroOrden.set(numeroOrden);
        }

        private String getObraSocial() {
            return obraSocial;
        }
    }
}
