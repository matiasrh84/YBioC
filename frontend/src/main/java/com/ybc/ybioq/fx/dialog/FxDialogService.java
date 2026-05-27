package com.ybc.ybioq.fx.dialog;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Modality;
import javafx.stage.Window;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.net.URL;
import java.util.Optional;

@Component
public class FxDialogService {

    public void informar(String mensaje) {
        mostrar(Alert.AlertType.INFORMATION, "YBioC", mensaje);
    }

    public void informar(String titulo, String mensaje) {
        mostrar(Alert.AlertType.INFORMATION, titulo, mensaje);
    }

    public void advertir(String mensaje) {
        mostrar(Alert.AlertType.WARNING, "YBioC", mensaje);
    }

    public void advertir(String titulo, String mensaje) {
        mostrar(Alert.AlertType.WARNING, titulo, mensaje);
    }

    public void error(String mensaje) {
        mostrar(Alert.AlertType.ERROR, "YBioC", mensaje);
    }

    public void error(String titulo, String mensaje) {
        mostrar(Alert.AlertType.ERROR, titulo, mensaje);
    }

    public boolean confirmar(String mensaje) {
        return confirmar("YBioC", mensaje);
    }

    public boolean confirmar(String titulo, String mensaje) {
        ButtonType si = new ButtonType("Si", ButtonBar.ButtonData.YES);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.NO);

        Alert alert = crearAlert(Alert.AlertType.CONFIRMATION, titulo, mensaje);
        alert.getButtonTypes().setAll(si, no);

        Optional<ButtonType> respuesta = alert.showAndWait();
        return respuesta.filter(si::equals).isPresent();
    }

    public Optional<Integer> requestCantidad() {
        return requestCantidad(null);
    }

    public Optional<Integer> requestCantidad(Integer valorInicial) {
        TextInputDialog dialog = new TextInputDialog(valorInicial == null ? "" : String.valueOf(valorInicial));
        dialog.setTitle("Cantidad - YBioC");
        dialog.setHeaderText("Ingrese la cantidad.");
        dialog.setContentText("Cantidad:");
        dialog.initModality(Modality.APPLICATION_MODAL);
        buscarVentanaActiva().ifPresent(dialog::initOwner);

        agregarEstilos(dialog);
        dialog.getDialogPane().getStyleClass().add("content");
        dialog.getEditor().setTextFormatter(new TextFormatter<>(change ->
                change.getControlNewText().matches("\\d*") ? change : null));

        while (true) {
            Optional<String> result = dialog.showAndWait();
            if (result.isEmpty()) {
                return Optional.empty();
            }

            String texto = result.get().trim();
            if (texto.isBlank()) {
                dialog.setHeaderText("Ingrese la cantidad (entero mayor a 0).");
                continue;
            }

            try {
                int cantidad = Integer.parseInt(texto);
                if (cantidad > 0) {
                    return Optional.of(cantidad);
                }
            } catch (NumberFormatException ignored) {
                // Validado por el TextFormatter, se mantiene por robustez.
            }

            dialog.setHeaderText("Cantidad invalida. Debe ser un entero mayor a 0.");
            dialog.getEditor().selectAll();
        }
    }

    public Optional<BigDecimal> requestArancel() {
        return requestArancel(null);
    }

    public Optional<BigDecimal> requestArancel(BigDecimal valorInicial) {
        TextInputDialog dialog = new TextInputDialog(valorInicial == null ? "" : valorInicial.toPlainString());
        dialog.setTitle("Arancel - YBioC");
        dialog.setHeaderText("Ingrese el arancel.");
        dialog.setContentText("Arancel:");
        dialog.initModality(Modality.APPLICATION_MODAL);
        buscarVentanaActiva().ifPresent(dialog::initOwner);

        agregarEstilos(dialog);
        dialog.getDialogPane().getStyleClass().add("content");
        dialog.getEditor().setTextFormatter(new TextFormatter<>(change ->
                change.getControlNewText().matches("\\d*(?:[\\.,]\\d{0,4})?") ? change : null));

        while (true) {
            Optional<String> result = dialog.showAndWait();
            if (result.isEmpty()) {
                return Optional.empty();
            }

            String texto = result.get().trim();
            if (texto.isBlank()) {
                dialog.setHeaderText("Ingrese el arancel (numero mayor o igual a 0).");
                continue;
            }

            try {
                BigDecimal arancel = new BigDecimal(texto.replace(",", "."));
                if (arancel.compareTo(BigDecimal.ZERO) >= 0) {
                    return Optional.of(arancel);
                }
            } catch (NumberFormatException ignored) {
                // Validado por el TextFormatter, se mantiene por robustez.
            }

            dialog.setHeaderText("Arancel invalido. Debe ser un numero mayor o igual a 0.");
            dialog.getEditor().selectAll();
        }
    }

    private void mostrar(Alert.AlertType tipo, String titulo, String mensaje) {
        crearAlert(tipo, titulo, mensaje).showAndWait();
    }

    private Alert crearAlert(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);

        buscarVentanaActiva().ifPresent(alert::initOwner);
        agregarEstilos(alert);

        return alert;
    }

    private Optional<Window> buscarVentanaActiva() {
        return Window.getWindows().stream()
                .filter(Window::isShowing)
                .filter(Window::isFocused)
                .findFirst();
    }

    private void agregarEstilos(Dialog<?> dialog) {
        URL styles = getClass().getResource("/fx/styles.css");
        if (styles != null) {
            dialog.getDialogPane().getStylesheets().add(styles.toExternalForm());
        }
    }
}
