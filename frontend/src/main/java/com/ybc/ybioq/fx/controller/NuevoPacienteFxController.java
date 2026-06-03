package com.ybc.ybioq.fx.controller;

import com.ybc.ybioq.fx.client.ObraSocialClient;
import com.ybc.ybioq.fx.client.PacienteClient;
import com.ybc.ybioq.fx.client.PersonaClient;
import com.ybc.ybioq.fx.client.dto.ObraSocialDto;
import com.ybc.ybioq.fx.client.dto.PacienteDto;
import com.ybc.ybioq.fx.client.dto.PersonaDto;
import com.ybc.ybioq.fx.session.SessionContext;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class NuevoPacienteFxController {

    private final PersonaClient   personaClient;
    private final PacienteClient  pacienteClient;
    private final ObraSocialClient obraSocialClient;
    private final SessionContext  sessionContext;

    // ── Sección datos de persona ──────────────────────────────────
    @FXML private VBox     seccionDatosPersona;
    @FXML private Label    lblModoInfo;
    @FXML private TextField txtApellido;
    @FXML private TextField txtNombre;
    @FXML private TextField txtDni;
    @FXML private CheckBox  chkSinDni;
    @FXML private DatePicker dpNacimiento;
    @FXML private TextField txtDomicilio;
    @FXML private TextField txtTelefono;
    @FXML private TextField txtMail;

    // ── Sección OS ────────────────────────────────────────────────
    @FXML private ComboBox<ObraSocialDto> cboObraSocial;
    @FXML private TextField               txtNroAfiliado;

    @FXML private Label lblMensaje;

    private PersonaDto personaExistente = null;

    public NuevoPacienteFxController(PersonaClient personaClient,
                                     PacienteClient pacienteClient,
                                     ObraSocialClient obraSocialClient,
                                     SessionContext sessionContext) {
        this.personaClient    = personaClient;
        this.pacienteClient   = pacienteClient;
        this.obraSocialClient = obraSocialClient;
        this.sessionContext   = sessionContext;
    }

    @FXML
    private void initialize() {
        Callback<ListView<ObraSocialDto>, ListCell<ObraSocialDto>> cf = lv -> new ListCell<>() {
            @Override protected void updateItem(ObraSocialDto item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.nombre());
            }
        };
        cboObraSocial.setCellFactory(cf);
        cboObraSocial.setButtonCell(cf.call(null));

        try {
            List<ObraSocialDto> activas = obraSocialClient.findAll().stream()
                    .filter(ObraSocialDto::estado)
                    .sorted(Comparator.comparing(ObraSocialDto::nombre, String::compareToIgnoreCase))
                    .toList();
            cboObraSocial.getItems().setAll(activas);
        } catch (Exception e) {
            lblMensaje.setText("Error al cargar obras sociales.");
        }

        // Modo desde persona existente
        PersonaDto persona = sessionContext.getPersonaParaAlta();
        if (persona != null) {
            sessionContext.limpiarPersonaParaAlta();
            personaExistente = persona;
            habilitarModoDesdePersona(persona);
        }
    }

    private void habilitarModoDesdePersona(PersonaDto persona) {
        lblModoInfo.setText("Persona encontrada: " + safe(persona.apellido())
                + ", " + safe(persona.nombre())
                + (persona.dni() != null ? "  —  DNI " + persona.dni() : "  —  Sin DNI"));
        lblModoInfo.setStyle("-fx-text-fill: #004d73; -fx-font-weight: 700;");

        txtApellido.setText(safe(persona.apellido()));
        txtNombre.setText(safe(persona.nombre()));
        if (persona.dni() != null) {
            txtDni.setText(persona.dni().toString());
        } else {
            chkSinDni.setSelected(true);
            txtDni.setDisable(true);
        }

        // En modo "desde persona" los datos demográficos son de solo lectura
        txtApellido.setDisable(true);
        txtNombre.setDisable(true);
        txtDni.setDisable(true);
        chkSinDni.setDisable(true);
        dpNacimiento.setDisable(false);
        txtDomicilio.setDisable(false);
        txtTelefono.setDisable(false);
        txtMail.setDisable(false);
    }

    @FXML
    private void toggleSinDni() {
        txtDni.setDisable(chkSinDni.isSelected());
        if (chkSinDni.isSelected()) txtDni.clear();
    }

    @FXML
    private void guardar() {
        String apellido = trim(txtApellido);
        String nombre   = trim(txtNombre);
        ObraSocialDto os = cboObraSocial.getValue();

        if (apellido.isBlank() || nombre.isBlank()) {
            lblMensaje.setText("Apellido y nombre son obligatorios.");
            return;
        }
        if (os == null) {
            lblMensaje.setText("Seleccione una obra social.");
            return;
        }

        try {
            PersonaDto persona;
            if (personaExistente != null) {
                // Modo desde persona existente — solo crear el paciente
                persona = personaExistente;
            } else {
                // Modo nueva persona
                boolean sinDni = chkSinDni.isSelected();
                String dniText = trim(txtDni);
                if (!sinDni && dniText.isBlank()) {
                    lblMensaje.setText("Ingrese el DNI o marque 'Sin DNI'.");
                    return;
                }
                Integer dni = null;
                if (!sinDni) {
                    try { dni = Integer.parseInt(dniText); }
                    catch (NumberFormatException e) { lblMensaje.setText("El DNI debe ser un número."); return; }
                }
                persona = personaClient.crear(apellido, nombre, dni);
            }

            PacienteDto paciente = pacienteClient.crearDesdePersona(persona.id());

            // Actualizar campos demográficos del paciente si se completaron
            LocalDate nacimiento = dpNacimiento.getValue();
            String domicilio = trim(txtDomicilio);
            String telefono  = trim(txtTelefono);
            String mail      = trim(txtMail);
            if (nacimiento != null || !domicilio.isBlank() || !telefono.isBlank() || !mail.isBlank()) {
                pacienteClient.actualizarDatos(paciente.id(), nacimiento, telefono, mail);
            }

            pacienteClient.asignarObraSocial(paciente.id(), os.id(), trim(txtNroAfiliado));
            sessionContext.setPacientePreseleccionado(paciente);
            cerrar();
        } catch (RuntimeException ex) {
            lblMensaje.setText(ex.getMessage());
        }
    }

    @FXML
    private void cancelar() { cerrar(); }

    private void cerrar() {
        ((Stage) txtApellido.getScene().getWindow()).close();
    }

    private String trim(TextField f) { return f.getText() == null ? "" : f.getText().trim(); }
    private String safe(String v)    { return v != null ? v : ""; }
}
