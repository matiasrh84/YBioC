package com.ybc.ybioq.fx.controller;

import com.ybc.ybioq.fx.client.MedicoClient;
import com.ybc.ybioq.fx.client.OrdenClient;
import com.ybc.ybioq.fx.client.dto.MedicoDto;
import com.ybc.ybioq.fx.client.dto.ModificarOrdenRequest;
import com.ybc.ybioq.fx.client.dto.OrdenDto;
import com.ybc.ybioq.fx.session.SessionContext;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ModificarOrdenFxController {

    private final OrdenClient   ordenClient;
    private final MedicoClient  medicoClient;
    private final SessionContext sessionContext;

    @FXML private Label    lblTitulo;
    @FXML private Label    lblInfo;
    @FXML private TextField txtNumeroOrden;
    @FXML private ComboBox<String>    cboTipoOrden;
    @FXML private ComboBox<MedicoDto> cboMedico;
    @FXML private Label    lblMensaje;

    private OrdenDto ordenActual;

    public ModificarOrdenFxController(OrdenClient ordenClient,
                                      MedicoClient medicoClient,
                                      SessionContext sessionContext) {
        this.ordenClient   = ordenClient;
        this.medicoClient  = medicoClient;
        this.sessionContext = sessionContext;
    }

    @FXML
    private void initialize() {
        cboTipoOrden.getItems().setAll("Ambulatorio", "Domicilio", "Internacion");

        Callback<ListView<MedicoDto>, ListCell<MedicoDto>> cf = lv -> new ListCell<>() {
            @Override protected void updateItem(MedicoDto item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null
                        : item.apellido() + ", " + item.nombre()
                          + (item.matricula() != null ? "  (Mat. " + item.matricula() + ")" : ""));
            }
        };
        cboMedico.setCellFactory(cf);
        cboMedico.setButtonCell(cf.call(null));

        try {
            List<MedicoDto> medicos = medicoClient.findAll().stream()
                    .filter(m -> m.estado() != null && m.estado() == 1)
                    .sorted(Comparator.comparing(MedicoDto::apellido,
                            Comparator.nullsLast(String::compareToIgnoreCase)))
                    .toList();
            cboMedico.getItems().setAll(medicos);
        } catch (Exception e) {
            lblMensaje.setText("No se pudieron cargar los médicos.");
        }

        // Leer la orden del contexto
        ordenActual = sessionContext.getOrdenParaModificar();
        if (ordenActual != null) {
            sessionContext.limpiarOrdenParaModificar();
            precargar(ordenActual);
        }
    }

    private void precargar(OrdenDto orden) {
        lblTitulo.setText("Modificar Orden");
        lblInfo.setText("Paciente: " + safe(orden.nombrePaciente())
                + "  |  OS: " + safe(orden.nombreObraSocial()));
        txtNumeroOrden.setText(safe(orden.numeroOrden()));
        if (orden.tipoOrden() != null) {
            cboTipoOrden.setValue(orden.tipoOrden());
        }
        // Pre-seleccionar médico actual
        if (orden.idMedico() != null) {
            cboMedico.getItems().stream()
                    .filter(m -> m.id().equals(orden.idMedico()))
                    .findFirst()
                    .ifPresent(cboMedico::setValue);
        }
    }

    @FXML
    private void guardar() {
        if (ordenActual == null) { cerrar(); return; }

        String nroOrden  = txtNumeroOrden.getText() == null ? "" : txtNumeroOrden.getText().trim();
        String tipoOrden = cboTipoOrden.getValue();
        MedicoDto medico = cboMedico.getValue();

        if (nroOrden.isBlank()) {
            lblMensaje.setText("El número de orden no puede estar vacío.");
            return;
        }

        try {
            ModificarOrdenRequest req = new ModificarOrdenRequest(
                    nroOrden,
                    tipoOrden,
                    medico != null ? medico.id() : null);
            ordenClient.modificar(ordenActual.id(), req);
            cerrar();
        } catch (RuntimeException ex) {
            lblMensaje.setText("Error: " + ex.getMessage());
        }
    }

    @FXML
    private void cancelar() { cerrar(); }

    private void cerrar() {
        ((Stage) txtNumeroOrden.getScene().getWindow()).close();
    }

    private String safe(String v) { return v != null ? v : "-"; }
}
