package com.ybc.ybioq.fx.controller;

import com.ybc.ybioq.fx.client.OrdenClient;
import com.ybc.ybioq.fx.client.ReporteClient;
import com.ybc.ybioq.fx.client.ResultadoClient;
import com.ybc.ybioq.fx.session.SessionContext;
import com.ybc.ybioq.fx.client.dto.ActualizarResultadoRequest;
import com.ybc.ybioq.fx.client.dto.OrdenDto;
import com.ybc.ybioq.fx.client.dto.ResultadoDto;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CargarResultadosFxController {

    private final OrdenClient    ordenClient;
    private final ResultadoClient resultadoClient;
    private final ReporteClient  reporteClient;

    @FXML
    private TextField txtBuscarOrden;
    @FXML
    private Label lblOrdenInfo;
    @FXML
    private Label lblPacienteInfo;
    @FXML
    private Label mensajeLabel;
    @FXML
    private TableView<LineaResultado> tablaResultados;
    @FXML
    private TableColumn<LineaResultado, String> colAnalisis;
    @FXML
    private TableColumn<LineaResultado, String> colPractica;
    @FXML
    private TableColumn<LineaResultado, String> colTipo;
    @FXML
    private TableColumn<LineaResultado, String> colUnidad;
    @FXML
    private TableColumn<LineaResultado, String> colValoresRef;
    @FXML
    private TableColumn<LineaResultado, String> colResultado;
    @FXML
    private TableColumn<LineaResultado, String> colObservacion;
    @FXML
    private TableColumn<LineaResultado, Boolean> colEstadoImprime;
    @FXML
    private TableColumn<LineaResultado, Boolean> colImprimirNombre;

    private final SessionContext sessionContext;

    private final ObservableList<LineaResultado> resultados = FXCollections.observableArrayList();
    private OrdenDto ordenActual = null;

    public CargarResultadosFxController(OrdenClient ordenClient,
                                        ResultadoClient resultadoClient,
                                        ReporteClient reporteClient,
                                        SessionContext sessionContext) {
        this.ordenClient     = ordenClient;
        this.resultadoClient = resultadoClient;
        this.reporteClient   = reporteClient;
        this.sessionContext  = sessionContext;
    }

    @FXML
    private void initialize() {
        configurarTabla();
        txtBuscarOrden.setOnAction(e -> buscarOrden());

        OrdenDto pre = sessionContext.getOrdenParaInformar();
        if (pre != null) {
            sessionContext.limpiarOrdenParaInformar();
            ordenActual = pre;
            txtBuscarOrden.setText(pre.numeroOrden());
            cargarResultadosDeOrden(pre);
        }
    }

    @FXML
    private void buscarOrden() {
        String q = txtBuscarOrden.getText() == null ? "" : txtBuscarOrden.getText().trim();
        if (q.isBlank()) {
            mensajeLabel.setText("Ingrese el número de orden.");
            return;
        }
        try {
            OrdenDto orden = ordenClient.findByNumero(q);
            if (orden == null) {
                mensajeLabel.setText("No existe orden con número: " + q);
                return;
            }
            ordenActual = orden;
            cargarResultadosDeOrden(orden);
        } catch (RuntimeException ex) {
            mensajeLabel.setText("Error al buscar: " + ex.getMessage());
        }
    }

    private void cargarResultadosDeOrden(OrdenDto orden) {
        try {
            List<ResultadoDto> resultadosDto = resultadoClient.findByOrden(orden.id());
            if (resultadosDto.isEmpty()) {
                mensajeLabel.setText("No hay resultados para esta orden.");
                resultados.clear();
                lblOrdenInfo.setText("-");
                lblPacienteInfo.setText("-");
                return;
            }

            resultados.clear();
            resultadosDto.forEach(r -> resultados.add(new LineaResultado(r)));

            lblOrdenInfo.setText("Orden N° " + orden.numeroOrden()
                    + (orden.fecha() != null ? "  —  " + orden.fecha().toLocalDate() : ""));
            lblPacienteInfo.setText(orden.nombrePaciente() != null ? orden.nombrePaciente() : "-");

            mensajeLabel.setText("Cargados " + resultados.size() + " análisis.");
        } catch (RuntimeException ex) {
            mensajeLabel.setText("Error al cargar: " + ex.getMessage());
        }
    }

    @FXML
    private void guardar() {
        if (resultados.isEmpty()) {
            mensajeLabel.setText("No hay resultados para guardar.");
            return;
        }

        int guardados = 0;
        for (LineaResultado linea : resultados) {
            if (!linea.getResultadoModificado()) continue;
            try {
                resultadoClient.actualizar(
                        linea.getIdAnalisis(),
                        linea.getIdPracticas(),
                        linea.getIdOrdenes(),
                        linea.getIdUsuarios(),
                        new ActualizarResultadoRequest(
                                linea.getResultado(),
                                linea.getObservacion(),
                                linea.getEstadoImprime() ? 1 : 0,
                                linea.getImprimirNombre() ? 1 : 0
                        ));
                linea.setResultadoModificado(false);
                guardados++;
            } catch (RuntimeException ex) {
                mensajeLabel.setText("Error al guardar " + linea.getNombreAnalisis() + ": " + ex.getMessage());
                return;
            }
        }
        mensajeLabel.setText("Guardados " + guardados + " resultados.");
    }

    @FXML
    private void limpiar() {
        txtBuscarOrden.clear();
        resultados.clear();
        lblOrdenInfo.setText("-");
        lblPacienteInfo.setText("-");
        mensajeLabel.setText("");
    }

    @FXML
    private void verInforme() {
        if (ordenActual == null) {
            mensajeLabel.setText("Busque una orden primero.");
            return;
        }
        try {
            mensajeLabel.setText("Generando informe...");
            byte[] pdf = reporteClient.informeFinal(ordenActual.id());
            java.io.File tmp = java.io.File.createTempFile("ybioc_informe_", ".pdf");
            tmp.deleteOnExit();
            try (java.io.FileOutputStream fos = new java.io.FileOutputStream(tmp)) { fos.write(pdf); }
            java.awt.Desktop.getDesktop().open(tmp);
            mensajeLabel.setText("Informe abierto.");
        } catch (Exception ex) {
            mensajeLabel.setText("Error al generar el informe: " + ex.getMessage());
        }
    }

    @FXML
    private void cerrar() {
        Stage stage = (Stage) mensajeLabel.getScene().getWindow();
        stage.close();
    }

    private void configurarTabla() {
        colAnalisis.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getNombreAnalisis()));
        colPractica.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getNombrePractica()));
        colTipo.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getTipoResultado()));
        colUnidad.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getUnidad()));
        colValoresRef.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getValoresReferencia()));
        colResultado.setCellValueFactory(d -> d.getValue().resultadoProperty());
        colObservacion.setCellValueFactory(d -> d.getValue().observacionProperty());
        colEstadoImprime.setCellValueFactory(d -> d.getValue().estadoImprimeProperty());
        colImprimirNombre.setCellValueFactory(d -> d.getValue().imprimirNombreProperty());

        // Resultado editable
        colResultado.setCellFactory(col -> new ResultadoCellFactory());

        // Observación editable
        colObservacion.setCellFactory(TextFieldTableCell.forTableColumn());
        colObservacion.setOnEditCommit(e -> {
            e.getRowValue().setObservacion(e.getNewValue());
            e.getRowValue().setResultadoModificado(true);
        });

        // Checkboxes editables
        colEstadoImprime.setCellFactory(col -> {
            CheckBox cb = new CheckBox();
            TableCell<LineaResultado, Boolean> cell = new TableCell<LineaResultado, Boolean>() {
                @Override
                protected void updateItem(Boolean item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || getTableRow() == null || getTableRow().getItem() == null) {
                        setGraphic(null);
                    } else {
                        cb.setSelected(item != null && item);
                        cb.setOnAction(e -> {
                            getTableRow().getItem().setEstadoImprime(!cb.isSelected());
                            getTableRow().getItem().setResultadoModificado(true);
                        });
                        setGraphic(cb);
                        setAlignment(Pos.CENTER);
                    }
                }
            };
            return cell;
        });

        colImprimirNombre.setCellFactory(col -> {
            CheckBox cb = new CheckBox();
            TableCell<LineaResultado, Boolean> cell = new TableCell<LineaResultado, Boolean>() {
                @Override
                protected void updateItem(Boolean item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || getTableRow() == null || getTableRow().getItem() == null) {
                        setGraphic(null);
                    } else {
                        cb.setSelected(item != null && item);
                        cb.setOnAction(e -> {
                            getTableRow().getItem().setImprimirNombre(!cb.isSelected());
                            getTableRow().getItem().setResultadoModificado(true);
                        });
                        setGraphic(cb);
                        setAlignment(Pos.CENTER);
                    }
                }
            };
            return cell;
        });

        tablaResultados.setItems(resultados);
        tablaResultados.setEditable(true);

        // Colorear filas: verde si cargado, blanco si no
        tablaResultados.setRowFactory(tv -> new TableRow<LineaResultado>() {
            @Override
            protected void updateItem(LineaResultado item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setStyle("");
                } else {
                    boolean cargado = item.getResultado() != null && !item.getResultado().equals("-") && !item.getResultado().isBlank();
                    setStyle(cargado ? "-fx-background-color: #90EE90;" : "-fx-background-color: white;");
                }
            }
        });
    }

    // ── Inner class: línea de resultado ────────────────────────────────

    public static class LineaResultado {
        private final Integer idAnalisis;
        private final Integer idPracticas;
        private final Integer idOrdenes;
        private final Integer idUsuarios;
        private final StringProperty nombreAnalisis = new SimpleStringProperty();
        private final StringProperty nombrePractica = new SimpleStringProperty();
        private final StringProperty tipoResultado = new SimpleStringProperty();
        private final StringProperty unidad = new SimpleStringProperty();
        private final StringProperty valoresReferencia = new SimpleStringProperty();
        private final StringProperty resultado = new SimpleStringProperty();
        private final StringProperty observacion = new SimpleStringProperty();
        private final javafx.beans.property.BooleanProperty estadoImprime = new javafx.beans.property.SimpleBooleanProperty();
        private final javafx.beans.property.BooleanProperty imprimirNombre = new javafx.beans.property.SimpleBooleanProperty();
        private boolean resultadoModificado = false;
        private boolean estadoModificado = false;
        private boolean imprimirModificado = false;

        LineaResultado(ResultadoDto dto) {
            this.idAnalisis = dto.idAnalisis();
            this.idPracticas = dto.idPracticas();
            this.idOrdenes = dto.idOrdenes();
            this.idUsuarios = dto.idUsuarios();
            this.nombreAnalisis.set(dto.nombreAnalisis());
            this.nombrePractica.set(dto.nombrePractica());
            this.tipoResultado.set(dto.tipoResultado());
            this.unidad.set(dto.unidad());
            this.valoresReferencia.set(dto.valoresReferencia());
            this.resultado.set(dto.resultado() != null ? dto.resultado() : "-");
            this.observacion.set(dto.observacion() != null ? dto.observacion() : "");
            this.estadoImprime.set(dto.estadoImprime() != null && dto.estadoImprime() == 1);
            this.imprimirNombre.set(dto.imprimirNombre() != null && dto.imprimirNombre() == 1);
        }

        public Integer getIdAnalisis() {
            return idAnalisis;
        }

        public Integer getIdPracticas() {
            return idPracticas;
        }

        public Integer getIdOrdenes() {
            return idOrdenes;
        }

        public Integer getIdUsuarios() {
            return idUsuarios;
        }

        public String getNombreAnalisis() {
            return nombreAnalisis.get();
        }

        public String getNombrePractica() {
            return nombrePractica.get();
        }

        public String getTipoResultado() {
            return tipoResultado.get();
        }

        public String getUnidad() {
            return unidad.get();
        }

        public String getValoresReferencia() {
            return valoresReferencia.get();
        }

        public String getResultado() {
            return resultado.get();
        }

        public String getObservacion() {
            return observacion.get();
        }

        public Boolean getEstadoImprime() {
            return estadoImprime.get();
        }

        public Boolean getImprimirNombre() {
            return imprimirNombre.get();
        }

        public StringProperty resultadoProperty() {
            return resultado;
        }

        public StringProperty observacionProperty() {
            return observacion;
        }

        public javafx.beans.property.BooleanProperty estadoImprimeProperty() {
            return estadoImprime;
        }

        public javafx.beans.property.BooleanProperty imprimirNombreProperty() {
            return imprimirNombre;
        }

        public void setResultado(String v) {
            resultado.set(v);
        }

        public void setObservacion(String v) {
            observacion.set(v);
        }

        public void setEstadoImprime(Boolean v) {
            estadoImprime.set(v != null && v);
        }

        public void setImprimirNombre(Boolean v) {
            imprimirNombre.set(v != null && v);
        }

        public boolean getResultadoModificado() {
            return resultadoModificado;
        }

        public void setResultadoModificado(boolean v) {
            resultadoModificado = v;
        }

        public boolean getEstadoModificado() {
            return estadoModificado;
        }

        public void setEstadoModificado(boolean v) {
            estadoModificado = v;
        }

        public boolean getImprimirModificado() {
            return imprimirModificado;
        }

        public void setImprimirModificado(boolean v) {
            imprimirModificado = v;
        }
    }

    // ── Celda personalizada para resultado con validación ───────────────

    private class ResultadoCellFactory extends TableCell<LineaResultado, String> {
        private final TextField textField = new TextField();

        ResultadoCellFactory() {
            textField.setOnKeyReleased(e -> {
                if (e.getCode().toString().equals("ENTER")) {
                    // Guardar en el item de LA FILA ACTUAL (no la seleccionada)
                    if (getTableRow() != null && getTableRow().getItem() != null) {
                        LineaResultado item = getTableRow().getItem();
                        item.setResultado(textField.getText());
                        item.setResultadoModificado(true);
                        getTableView().refresh(); // Repintar tabla para coloreo
                        getTableView().getSelectionModel().selectNext();
                    }
                }
            });
            textField.focusedProperty().addListener((obs, oldVal, newVal) -> {
                if (!newVal && getTableRow() != null && getTableRow().getItem() != null) {
                    LineaResultado item = getTableRow().getItem();
                    item.setResultado(textField.getText());
                    item.setResultadoModificado(true);
                }
            });
        }

        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setGraphic(null);
            } else {
                textField.setText(item);
                setGraphic(textField);
            }
        }

        @Override
        public void startEdit() {
            super.startEdit();
            if (getItem() != null) {
                textField.setText(getItem());
                textField.selectAll();
                setGraphic(textField);
                textField.requestFocus();
            }
        }
    }
}
