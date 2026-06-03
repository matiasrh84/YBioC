package com.ybc.ybioq.fx.controller;

import com.ybc.ybioq.fx.client.OrdenClient;
import com.ybc.ybioq.fx.client.ResultadoClient;
import com.ybc.ybioq.fx.client.dto.OrdenDto;
import com.ybc.ybioq.fx.client.dto.ResultadoDto;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class DetallePracticasOrdenFxController {

    private final OrdenClient ordenClient;
    private final ResultadoClient resultadoClient;

    private OrdenDto ordenCargada = null;
    private final ObservableList<ResultadoDto> resultados = FXCollections.observableArrayList();

    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    // ── Búsqueda ──────────────────────────────────────────────────
    @FXML
    private TextField txtBuscarOrdenDetalle;
    @FXML
    private Label lblMensajeDetalle;

    // ── Cabecera ──────────────────────────────────────────────────
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

    // ── Tabla ─────────────────────────────────────────────────────
    @FXML
    private TableView<ResultadoDto> tablaPracticas;
    @FXML
    private TableColumn<ResultadoDto, String> colCodigo;
    @FXML
    private TableColumn<ResultadoDto, String> colPractica;
    @FXML
    private TableColumn<ResultadoDto, String> colResultado;
    @FXML
    private TableColumn<ResultadoDto, String> colNumeroOrden;
    @FXML
    private TableColumn<ResultadoDto, String> colObraSocial;

    @FXML
    private Label mensajeLabel;

    public DetallePracticasOrdenFxController(OrdenClient ordenClient,
                                             ResultadoClient resultadoClient) {
        this.ordenClient = ordenClient;
        this.resultadoClient = resultadoClient;
    }

    @FXML
    private void initialize() {
        configurarTabla();
        limpiarCabecera();
        if (txtBuscarOrdenDetalle != null) {
            txtBuscarOrdenDetalle.setOnAction(e -> buscarOrden());
        }
    }

    @FXML
    private void buscarOrden() {
        String q = txtBuscarOrdenDetalle.getText() == null ? "" : txtBuscarOrdenDetalle.getText().trim();
        if (q.isBlank()) return;
        lblMensajeDetalle.setText("");

        try {
            OrdenDto orden = ordenClient.findByNumero(q);
            if (orden == null) {
                lblMensajeDetalle.setText("No existe orden con número: " + q);
                resultados.clear();
                limpiarCabecera();
                return;
            }
            ordenCargada = orden;
            poblarCabecera(orden);

            List<ResultadoDto> lista = resultadoClient.findByOrden(orden.id());
            resultados.setAll(lista);
            tablaPracticas.refresh();

            if (lista.isEmpty()) {
                mensajeLabel.setText("La orden no tiene resultados registrados.");
            } else {
                mensajeLabel.setText(lista.size() + " análisis encontrados.");
            }
        } catch (RuntimeException ex) {
            lblMensajeDetalle.setText("Error: " + ex.getMessage());
        }
    }

    @FXML
    private void salir() {
        Stage stage = (Stage) tablaPracticas.getScene().getWindow();
        stage.close();
    }

    // ── Helpers ───────────────────────────────────────────────────

    private void configurarTabla() {
        colCodigo.setCellValueFactory(d -> new ReadOnlyStringWrapper(
                d.getValue().codigoPractica() != null ? String.valueOf(d.getValue().codigoPractica()) : "-"));
        colPractica.setCellValueFactory(d -> new ReadOnlyStringWrapper(
                safe(d.getValue().nombrePractica())));
        colResultado.setCellValueFactory(d -> {
            String r = d.getValue().resultado();
            return new ReadOnlyStringWrapper(r != null && !r.equals("-") && !r.isBlank() ? r : "(sin resultado)");
        });
        colNumeroOrden.setCellValueFactory(d -> new ReadOnlyStringWrapper(
                ordenCargada != null ? safe(ordenCargada.numeroOrden()) : "-"));
        colObraSocial.setCellValueFactory(d -> new ReadOnlyStringWrapper(
                ordenCargada != null ? safe(ordenCargada.nombreObraSocial()) : "-"));

        tablaPracticas.setItems(resultados);
        tablaPracticas.setEditable(false);

        tablaPracticas.setRowFactory(tv -> new javafx.scene.control.TableRow<>() {
            @Override
            protected void updateItem(ResultadoDto item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setStyle("");
                } else {
                    String r = item.resultado();
                    boolean cargado = r != null && !r.equals("-") && !r.isBlank();
                    setStyle(cargado ? "-fx-background-color: #d4edda;" : "");
                }
            }
        });
    }

    private void poblarCabecera(OrdenDto orden) {
        lblProtocolo.setText(safe(orden.numeroOrden()));
        lblAfiliado.setText(safe(orden.nombrePaciente()));
        lblNumeroAfiliado.setText(orden.dniPaciente() != null ? String.valueOf(orden.dniPaciente()) : "-");
        lblFecha.setText(orden.fecha() != null ? orden.fecha().toLocalDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "-");
        lblHora.setText(orden.fecha() != null ? orden.fecha().format(DateTimeFormatter.ofPattern("HH:mm")) : "-");
        lblServicio.setText(safe(orden.tipoOrden()));
        lblTotal.setText(orden.total() != null ? "$ " + orden.total().toPlainString() : "-");
    }

    private void limpiarCabecera() {
        for (Label l : new Label[]{lblNumeroAfiliado, lblAfiliado, lblFecha,
                lblProtocolo, lblServicio, lblHora, lblTotal}) {
            if (l != null) l.setText("-");
        }
        if (mensajeLabel != null) mensajeLabel.setText("");
        resultados.clear();
    }

    private String safe(String v) {
        return v != null ? v : "-";
    }
}
