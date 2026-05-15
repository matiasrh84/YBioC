package com.ybc.ybioq.fx.controller;

import com.ybc.ybioq.fx.navigation.FxNavigationService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PrincipalController {

    private final FxNavigationService navigationService;

    @FXML
    private ScrollPane panelCargarPaciente;

    @FXML
    private ScrollPane panelCargarOrden;

    @FXML
    private ScrollPane panelInformes;

    @FXML
    private ScrollPane panelFacturacion;

    @FXML
    private ScrollPane panelUtilitarios;

    @FXML
    private Button btnMenuCargarPaciente;

    @FXML
    private Button btnMenuTurnos;

    @FXML
    private Button btnMenuInformes;

    @FXML
    private Button btnMenuFacturacion;

    @FXML
    private Button btnMenuUtilitarios;

    @FXML
    private Button btnSalir;

    @FXML
    private Button btnAnalisis;

    @FXML
    private Button btnObrasSociales;

    @FXML Button btnMedicos;

    @FXML
    private ComboBox<String> cboSexo;

    @FXML
    private ComboBox<String> cboServicio;

    @FXML
    private CheckBox chkCoseguro;

    @FXML
    private CheckBox chkObrasocial;

    @FXML
    private Label lblTotalParticular;

    @FXML
    private Label lblTotalOS;

    @FXML
    private Label lblAnticipo;

    @FXML
    private Label lblTotal;

    public PrincipalController(FxNavigationService navigationService) {
        this.navigationService = navigationService;
    }

    @FXML
    public void initialize() {
        initCombo(cboSexo, "Masculino", "Femenino", "Indistinto");
        initCombo(cboServicio, "Ambulatorio", "Domicilio", "Internacion");

        if (chkCoseguro != null) {
            chkCoseguro.setSelected(false);
        }
        if (chkObrasocial != null) {
            chkObrasocial.setSelected(false);
        }

        if (lblTotalParticular != null) {
            lblTotalParticular.setText("$ 0.00");
        }
        if (lblTotalOS != null) {
            lblTotalOS.setText("$ 0.00");
        }
        if (lblAnticipo != null) {
            lblAnticipo.setText("$ 0.00");
        }
        if (lblTotal != null) {
            lblTotal.setText("$ 0.00");
        }

        if (btnMenuCargarPaciente != null) {
            btnMenuCargarPaciente.setOnAction(event -> mostrarPanel(panelCargarPaciente));
        }
        if (btnMenuTurnos != null) {
            btnMenuTurnos.setOnAction(event -> mostrarPanel(panelCargarOrden));
        }
        if (btnMenuInformes != null) {
            btnMenuInformes.setOnAction(event -> mostrarPanel(panelInformes));
        }
        if (btnMenuFacturacion != null) {
            btnMenuFacturacion.setOnAction(event -> mostrarPanel(panelFacturacion));
        }
        if (btnMenuUtilitarios != null) {
            btnMenuUtilitarios.setOnAction(event -> mostrarPanel(panelUtilitarios));
        }
        if (btnSalir != null) {
            btnSalir.setOnAction(event -> cerrarVentana());
        }
        if (btnAnalisis != null) {
            btnAnalisis.setOnAction(event -> navigationService.showConfiguracionAnalisis());
        }
        if (btnObrasSociales != null) {
            btnObrasSociales.setOnAction(event -> navigationService.showObrasSociales());
        }

        if(btnMedicos != null) {
            btnMedicos.setOnAction(event -> navigationService.showMedicos());
        }

        mostrarPanel(panelCargarPaciente);
    }

    private void initCombo(ComboBox<String> combo, String... items) {
        if (combo == null) {
            return;
        }
        combo.getItems().setAll(items);
        if (!combo.getItems().isEmpty()) {
            combo.getSelectionModel().selectFirst();
        }
    }

    private void mostrarPanel(ScrollPane panelDestino) {
        if (panelCargarPaciente != null) {
            panelCargarPaciente.setVisible(false);
        }
        if (panelCargarOrden != null) {
            panelCargarOrden.setVisible(false);
        }
        if (panelInformes != null) {
            panelInformes.setVisible(false);
        }
        if (panelFacturacion != null) {
            panelFacturacion.setVisible(false);
        }
        if (panelUtilitarios != null) {
            panelUtilitarios.setVisible(false);
        }
        if (panelDestino != null) {
            panelDestino.setVisible(true);
        }
    }

    private void cerrarVentana() {
        if (btnSalir == null || btnSalir.getScene() == null) {
            return;
        }
        Stage stage = (Stage) btnSalir.getScene().getWindow();
        stage.close();
    }
}
