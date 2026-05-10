package com.ybc.ybioq.fx.controller;

import com.ybc.ybioq.fx.navigation.FxNavigationService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PrincipalController {

    // --- SERVICIO DE NAVEGACIÓN DE SPRING ---
    private final FxNavigationService navigationService;

    public PrincipalController(FxNavigationService navigationService) {
        this.navigationService = navigationService;
    }

    // --- PANELES ---
    @FXML private ScrollPane panelCargarPaciente;
    @FXML private ScrollPane panelCargarOrden;
    // @FXML private ScrollPane panelFacturacion;
    @FXML private ScrollPane panelUtilitarios;

    // --- BOTONES DEL MENÚ LATERAL ---
    @FXML private Button btnMenuCargarPaciente;
    @FXML private Button btnMenuTurnos;
    @FXML private Button btnMenuFacturacion;
    @FXML private Button btnMenuUtilitarios;
    @FXML private Button btnSalir;

    // ==========================================
    // --- COMPONENTES: PANEL PACIENTES
    // ==========================================
    @FXML private TextField txtDNI;
    @FXML private TextField txtNombre;
    @FXML private TextField txtApellido;
    @FXML private TextField txtDireccion;
    @FXML private TextField txtLocalidad;
    @FXML private TextField txtTelefono;
    @FXML private TextField txtCelular;
    @FXML private TextField txtMail;
    @FXML private TextField txtObraSocial;
    @FXML private TextField txtNumeroAfiliado;
    @FXML private ComboBox<String> cboSexo;
    @FXML private Label lblEdad;
    @FXML private TableView<?> tablaPacientes;

    @FXML private Button btnBuscar;
    @FXML private Button btnSinDNI;
    @FXML private Button btnModificar;
    @FXML private Button btnBorrar;
    @FXML private Button btnPatologias;
    @FXML private Button btnResultados;
    @FXML private Button btnCargarConsulta;

    // ==========================================
    // --- COMPONENTES: PANEL ÓRDENES/TURNOS
    // ==========================================
    @FXML private TextField txtBuscarOrden;
    @FXML private TextField txtBuscarPractica;
    @FXML private TextField txtMedico;
    @FXML private TextField txtMotivo;
    @FXML private TableView<?> tablaOrdenes;
    @FXML private TableView<?> tablaPracticas;

    @FXML private Label lblTotalParticular;
    @FXML private Label lblTotalOS;
    @FXML private Label lblSeña;
    @FXML private Label lblTotal;

    @FXML private Button btnImprimirListado;
    @FXML private Button btnCertificadoAsistencia;

    // ==========================================
    // --- COMPONENTES: PANEL UTILITARIOS
    // ==========================================
    // ESTE ES EL BOTON QUE ABRE EL FORMULARIO DE ANALISIS
    @FXML private Button btnAnalisis;


    @FXML
    public void initialize() {
        // 1. Llenar combos iniciales
        cboSexo.getItems().addAll("Masculino", "Femenino", "Indistinto");
        if (!cboSexo.getItems().isEmpty()) {
            cboSexo.getSelectionModel().selectFirst();
        }

        // 2. Navegación del Menú Lateral (Para cambiar de pantallas)
        btnMenuCargarPaciente.setOnAction(e -> mostrarPanel(panelCargarPaciente));
        btnMenuTurnos.setOnAction(e -> mostrarPanel(panelCargarOrden));
        // btnMenuFacturacion.setOnAction(e -> mostrarPanel(panelFacturacion));
        btnMenuUtilitarios.setOnAction(e -> mostrarPanel(panelUtilitarios));

        // 3. Botón de salida
        btnSalir.setOnAction(e -> {
            Stage stage = (Stage) btnSalir.getScene().getWindow();
            stage.close();
        });

        // ---------------------------------------------------------
        // 4. ABRIR FORMULARIO DE ANÁLISIS
        // Aquí conectamos tu botón "btnAnalisis" con el servicio de Spring
        // ---------------------------------------------------------
        if (btnAnalisis != null) {
            btnAnalisis.setOnAction(e -> {
                // Al hacer clic, le decimos al NavigationService que abra la ventana modal
                navigationService.showConfiguracionAnalisis();
            });
        }
    }

    /**
     * Oculta todos los paneles y hace visible solo el solicitado.
     */
    private void mostrarPanel(ScrollPane panelDestino) {
        if (panelCargarPaciente != null) panelCargarPaciente.setVisible(false);
        if (panelCargarOrden != null) panelCargarOrden.setVisible(false);
        if (panelUtilitarios != null) panelUtilitarios.setVisible(false);

        if (panelDestino != null) {
            panelDestino.setVisible(true);
        }
    }
}