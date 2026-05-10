package com.ybc.ybioq.fx.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

@Component
public class AnalisisControllerFx {

    // --- CONTENEDORES ---
    @FXML private ScrollPane panelGeneral;
    @FXML private ScrollPane panelAnalisis;
    // @FXML private ScrollPane panelPrevisualizar;

    // --- BOTONES NAVEGACIÓN ---
    @FXML private Button btnGeneral;
    @FXML private Button btnAnalisis;
    @FXML private Button btnPrevisualizar;

    // --- CAMPOS ---
    @FXML private TextField txtNombreInforme;
    @FXML private TextField txtPractica;
    @FXML private TextField txtMetodo;
    @FXML private TextArea txtInstrucciones;
    @FXML private CheckBox swDerivacion;
    @FXML private TextField txtFormatoMesada;
    @FXML private TableView<?> tablaPioridadPractica;
    @FXML private Label lblPractica;

    // --- ACCIONES ---
    @FXML private Button btnGuardar;
    @FXML private Button btnCancelar;
    @FXML private Button btnCerrar;

    @FXML private TreeView<String> treeParametros;
    @FXML private TextField txtNombreParametro;
    @FXML private TextArea txtValoresReferencia;

    @FXML
    public void initialize() {
        // Lógica de intercambio de paneles
        btnGeneral.setOnAction(e -> mostrarPanel(panelGeneral));
        btnAnalisis.setOnAction(e -> mostrarPanel(panelAnalisis));
        // btnPrevisualizar.setOnAction(e -> mostrarPanel(panelPrevisualizar));

        // Cerrar el diálogo
        btnCerrar.setOnAction(e -> cerrarVentana());
        btnCancelar.setOnAction(e -> cerrarVentana());

        btnGuardar.setOnAction(e -> {
            System.out.println("Guardando datos de: " + txtPractica.getText());
            // Aquí iría tu lógica de negocio
        });
        configurarArbol();
    }

    private void mostrarPanel(ScrollPane panel) {
        panelGeneral.setVisible(false);
        panelAnalisis.setVisible(false);
        // panelPrevisualizar.setVisible(false);
        panel.setVisible(true);
    }

    private void cerrarVentana() {
        Stage stage = (Stage) btnCerrar.getScene().getWindow();
        stage.close();
    }

    private void configurarArbol() {
        // Crear el nodo raíz
        TreeItem<String> rootItem = new TreeItem<>("Análisis: " + txtPractica.getText());
        rootItem.setExpanded(true);

        // Ejemplo de cómo agregar subnodos (esto vendría de tu base de datos)
        TreeItem<String> grupoHematologia = new TreeItem<>("Serie Roja");
        grupoHematologia.getChildren().add(new TreeItem<>("Hemoglobina"));
        grupoHematologia.getChildren().add(new TreeItem<>("Hematocrito"));

        rootItem.getChildren().add(grupoHematologia);

        treeParametros.setRoot(rootItem);

        // Escuchar cuando el usuario selecciona algo en el árbol
        treeParametros.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Actualizar el formulario de la derecha con los datos del nodo
                txtNombreParametro.setText(newValue.getValue());
                System.out.println("Seleccionado: " + newValue.getValue());
            }
        });
    }
}
