package com.ybc.ybioq.fx.controller;

import com.ybc.ybioq.fx.client.ConfiguracionReporteClient;
import com.ybc.ybioq.fx.client.ReporteClient;
import com.ybc.ybioq.fx.client.dto.ConfiguracionReporteDto;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.awt.Desktop;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.util.List;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ConfiguracionReporteFxController {

    private final ConfiguracionReporteClient client;
    private final ReporteClient              reporteClient;

    private Integer configId      = null;
    private byte[]  logoBytes     = null;
    private byte[]  firmaBytes    = null;
    private byte[]  portadaBytes  = null;
    private byte[]  membreteBytes = null;

    @FXML private TextField        nombreField;
    @FXML private TextField        direccionField;
    @FXML private TextField        telefonoField;
    @FXML private TextField        mailField;
    @FXML private TextArea         observacionArea;
    @FXML private TextField        observacion2Field;
    @FXML private ComboBox<String> formatoCombo;
    @FXML private ComboBox<String> orientacionCombo;
    @FXML private ComboBox<String> disenoCombo;

    // logo
    @FXML private ImageView logoView;
    @FXML private Label     sinLogoLabel;
    @FXML private Button    quitarLogoBtn;

    // firma
    @FXML private ImageView firmaView;
    @FXML private Label     sinFirmaLabel;
    @FXML private Button    quitarFirmaBtn;

    // portada
    @FXML private ImageView portadaView;
    @FXML private Label     sinPortadaLabel;
    @FXML private Button    quitarPortadaBtn;

    // membrete
    @FXML private ImageView membreteView;
    @FXML private Label     sinMembreteLabel;
    @FXML private Button    quitarMembreteBtn;

    @FXML private Label mensajeLabel;

    public ConfiguracionReporteFxController(ConfiguracionReporteClient client,
                                            ReporteClient reporteClient) {
        this.client        = client;
        this.reporteClient = reporteClient;
    }

    @FXML
    private void initialize() {
        formatoCombo.setItems(FXCollections.observableArrayList("A4", "A5", "A6"));
        orientacionCombo.setItems(FXCollections.observableArrayList("Vertical", "Horizontal"));
        disenoCombo.setItems(FXCollections.observableArrayList("Estándar", "Compacto"));
        cargarConfiguracion();
    }

    // ── Logo ──────────────────────────────────────────────────────────────────

    @FXML
    private void seleccionarLogo() {
        File file = abrirSelector("Seleccionar logo");
        if (file == null) return;
        try {
            logoBytes = Files.readAllBytes(file.toPath());
            mostrarImagen(logoView, sinLogoLabel, quitarLogoBtn, logoBytes);
            mensajeLabel.setText("");
        } catch (Exception ex) {
            mensajeLabel.setText("No se pudo cargar el logo.");
        }
    }

    @FXML
    private void quitarLogo() {
        logoBytes = null;
        limpiarImagen(logoView, sinLogoLabel, "Sin logo", quitarLogoBtn);
    }

    // ── Firma ─────────────────────────────────────────────────────────────────

    @FXML
    private void seleccionarFirma() {
        File file = abrirSelector("Seleccionar firma del bioquímico");
        if (file == null) return;
        try {
            firmaBytes = Files.readAllBytes(file.toPath());
            mostrarImagen(firmaView, sinFirmaLabel, quitarFirmaBtn, firmaBytes);
            mensajeLabel.setText("");
        } catch (Exception ex) {
            mensajeLabel.setText("No se pudo cargar la firma.");
        }
    }

    @FXML
    private void quitarFirma() {
        firmaBytes = null;
        limpiarImagen(firmaView, sinFirmaLabel, "Sin firma", quitarFirmaBtn);
    }

    // ── Portada ───────────────────────────────────────────────────────────────

    @FXML
    private void seleccionarPortada() {
        File file = abrirSelector("Seleccionar portada del informe");
        if (file == null) return;
        try {
            portadaBytes = Files.readAllBytes(file.toPath());
            mostrarImagen(portadaView, sinPortadaLabel, quitarPortadaBtn, portadaBytes);
            mensajeLabel.setText("");
        } catch (Exception ex) {
            mensajeLabel.setText("No se pudo cargar la portada.");
        }
    }

    @FXML
    private void quitarPortada() {
        portadaBytes = null;
        limpiarImagen(portadaView, sinPortadaLabel, "Sin portada personalizada", quitarPortadaBtn);
    }

    // ── Membrete ──────────────────────────────────────────────────────────────

    @FXML
    private void seleccionarMembrete() {
        File file = abrirSelector("Seleccionar membrete");
        if (file == null) return;
        try {
            byte[] bytes = Files.readAllBytes(file.toPath());
            if (!validarProporcionMembrete(bytes)) return;
            membreteBytes = bytes;
            mostrarImagen(membreteView, sinMembreteLabel, quitarMembreteBtn, membreteBytes);
            mensajeLabel.setText("");
        } catch (Exception ex) {
            mensajeLabel.setText("No se pudo cargar el membrete.");
        }
    }

    @FXML
    private void quitarMembrete() {
        membreteBytes = null;
        limpiarImagen(membreteView, sinMembreteLabel, "Sin membrete personalizado", quitarMembreteBtn);
    }

    // Proporción mínima: el ancho debe ser al menos 3× la altura.
    // Ejemplo correcto para A4: 595×100 px (ratio 5.95). Incorrecto: 300×200 (ratio 1.5).
    private boolean validarProporcionMembrete(byte[] bytes) {
        Image img = new Image(new ByteArrayInputStream(bytes));
        double w = img.getWidth();
        double h = img.getHeight();
        if (h <= 0) {
            mensajeLabel.setText("No se pudo leer las dimensiones de la imagen.");
            return false;
        }
        double ratio = w / h;
        if (ratio < 3.0) {
            String formato = formatoCombo.getValue() != null ? formatoCombo.getValue() : "A4";
            String anchoEsperado = switch (formato) {
                case "A5" -> "420";
                case "A6" -> "298";
                default   -> "595";
            };
            mensajeLabel.setText(
                "El membrete debe ser apaisado (ancho ≥ 3× alto). " +
                "Para " + formato + " se recomienda ~" + anchoEsperado + " px de ancho. " +
                "La imagen cargada mide " + (int)w + "×" + (int)h + " px."
            );
            return false;
        }
        return true;
    }

    // ── Muestra ───────────────────────────────────────────────────────────────

    @FXML
    private void verMuestra() {
        mensajeLabel.setText("Generando muestra del informe...");
        try {
            byte[] pdf = reporteClient.muestra();
            File temp = File.createTempFile("muestra_informe_", ".pdf");
            temp.deleteOnExit();
            try (FileOutputStream fos = new FileOutputStream(temp)) {
                fos.write(pdf);
            }
            Desktop.getDesktop().open(temp);
            mensajeLabel.setText("Muestra generada. Revisá el visor de PDF.");
        } catch (RuntimeException ex) {
            mensajeLabel.setText("Error al generar la muestra: " + ex.getMessage());
        } catch (Exception ex) {
            mensajeLabel.setText("No se pudo abrir el PDF: " + ex.getMessage());
        }
    }

    // ── Guardar ───────────────────────────────────────────────────────────────

    @FXML
    private void guardar() {
        String nombre = trim(nombreField);
        if (nombre.isBlank()) {
            mensajeLabel.setText("El nombre del laboratorio es obligatorio.");
            return;
        }

        ConfiguracionReporteDto dto = new ConfiguracionReporteDto(
                configId,
                nombre,
                emptyToNull(trim(direccionField)),
                emptyToNull(trim(telefonoField)),
                emptyToNull(trim(mailField)),
                emptyToNull(trim(observacionArea)),
                emptyToNull(trim(observacion2Field)),
                logoBytes,
                firmaBytes,
                portadaBytes,
                membreteBytes,
                formatoCombo.getValue(),
                orientacionCombo.getValue(),
                disenoCombo.getValue()
        );

        try {
            ConfiguracionReporteDto guardado = (configId == null)
                    ? client.save(dto)
                    : client.update(configId, dto);
            configId      = guardado.id();
            logoBytes     = guardado.logo();
            firmaBytes    = guardado.firma();
            portadaBytes  = guardado.portada();
            membreteBytes = guardado.membrete();
            mensajeLabel.setText("Configuración guardada.");
        } catch (RuntimeException ex) {
            mensajeLabel.setText(ex.getMessage());
        }
    }

    // ── Carga inicial ─────────────────────────────────────────────────────────

    private void cargarConfiguracion() {
        try {
            List<ConfiguracionReporteDto> lista = client.findAll();
            if (!lista.isEmpty()) poblarFormulario(lista.get(0));
        } catch (RuntimeException ex) {
            mensajeLabel.setText("No se pudo cargar la configuración: " + ex.getMessage());
        }
    }

    private void poblarFormulario(ConfiguracionReporteDto dto) {
        configId      = dto.id();
        logoBytes     = dto.logo();
        firmaBytes    = dto.firma();
        portadaBytes  = dto.portada();
        membreteBytes = dto.membrete();

        nombreField.setText(str(dto.nombre()));
        direccionField.setText(str(dto.direccion()));
        telefonoField.setText(str(dto.telefono()));
        mailField.setText(str(dto.mail()));
        observacionArea.setText(str(dto.observacion()));
        observacion2Field.setText(str(dto.observacion2()));
        formatoCombo.setValue(dto.formato());
        orientacionCombo.setValue(dto.orientacion());
        disenoCombo.setValue(dto.diseno());

        if (logoBytes     != null && logoBytes.length     > 0)
            mostrarImagen(logoView,     sinLogoLabel,     quitarLogoBtn,     logoBytes);
        if (firmaBytes    != null && firmaBytes.length    > 0)
            mostrarImagen(firmaView,    sinFirmaLabel,    quitarFirmaBtn,    firmaBytes);
        if (portadaBytes  != null && portadaBytes.length  > 0)
            mostrarImagen(portadaView,  sinPortadaLabel,  quitarPortadaBtn,  portadaBytes);
        if (membreteBytes != null && membreteBytes.length > 0)
            mostrarImagen(membreteView, sinMembreteLabel, quitarMembreteBtn, membreteBytes);
    }

    // ── Helpers ───────────────────────────────────────────────────────────────

    private File abrirSelector(String titulo) {
        FileChooser fc = new FileChooser();
        fc.setTitle(titulo);
        fc.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg", "*.gif", "*.bmp"));
        return fc.showOpenDialog(nombreField.getScene().getWindow());
    }

    private void mostrarImagen(ImageView view, Label placeholder, Button quitarBtn, byte[] bytes) {
        view.setImage(new Image(new ByteArrayInputStream(bytes)));
        placeholder.setVisible(false);
        placeholder.setManaged(false);
        quitarBtn.setVisible(true);
        quitarBtn.setManaged(true);
    }

    private void limpiarImagen(ImageView view, Label placeholder, String texto, Button quitarBtn) {
        view.setImage(null);
        placeholder.setText(texto);
        placeholder.setVisible(true);
        placeholder.setManaged(true);
        quitarBtn.setVisible(false);
        quitarBtn.setManaged(false);
    }

    private String trim(TextField f) { return f.getText() == null ? "" : f.getText().trim(); }
    private String trim(TextArea a)  { return a.getText() == null ? "" : a.getText().trim(); }
    private String str(String v)     { return v == null ? "" : v; }
    private String emptyToNull(String s) { return s == null || s.isBlank() ? null : s; }
}
