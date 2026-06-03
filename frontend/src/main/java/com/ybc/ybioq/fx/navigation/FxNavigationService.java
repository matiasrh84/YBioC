package com.ybc.ybioq.fx.navigation;

import com.ybc.ybioq.fx.client.dto.UsuarioSession;
import com.ybc.ybioq.fx.fxml.SpringFXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

@Component
public class FxNavigationService {

    private static final double MAIN_WIDTH = 1360;
    private static final double MAIN_HEIGHT = 900;

    private static final double DIALOG_MEDIUM_WIDTH = 1360;
    private static final double DIALOG_MEDIUM_HEIGHT = 860;
    private static final double DIALOG_MEDIUM_MIN_WIDTH = 1240;
    private static final double DIALOG_MEDIUM_MIN_HEIGHT = 780;

    private static final double DIALOG_LARGE_WIDTH = 1500;
    private static final double DIALOG_LARGE_HEIGHT = 930;
    private static final double DIALOG_LARGE_MIN_WIDTH = 1380;
    private static final double DIALOG_LARGE_MIN_HEIGHT = 840;

    private static final double DIALOG_SMALL_WIDTH = 560;
    private static final double DIALOG_SMALL_HEIGHT = 420;
    private static final double DIALOG_SMALL_MIN_WIDTH = 520;
    private static final double DIALOG_SMALL_MIN_HEIGHT = 360;

    private static final double DIALOG_SEARCH_WIDTH = 1180;
    private static final double DIALOG_SEARCH_HEIGHT = 760;
    private static final double DIALOG_SEARCH_MIN_WIDTH = 1080;
    private static final double DIALOG_SEARCH_MIN_HEIGHT = 700;

    private final SpringFXMLLoader fxmlLoader;
    private Stage primaryStage;

    public FxNavigationService(SpringFXMLLoader fxmlLoader) {
        this.fxmlLoader = fxmlLoader;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void showLogin() {
        Parent root = fxmlLoader.load("/fx/login-view.fxml");
        Scene scene = createScene(root, 420, 460);
        primaryStage.setTitle("YBioC - Ingreso");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    public void showMain(UsuarioSession usuario) {
        // 1. Ocultamos/Cerramos la ventana de Login actual
        if (primaryStage != null) {
            primaryStage.close();
        }

        // 2. Cargamos el FXML de la pantalla principal
        Parent root = fxmlLoader.load("/fx/principal-form.fxml");

        // 3. Creamos un NUEVO Stage (Ventana nueva y limpia)
        Stage mainStage = new Stage();
        Scene scene = createScene(root, MAIN_WIDTH, MAIN_HEIGHT);

        mainStage.setTitle("YBioC");
        mainStage.setScene(scene);

        // 4. Le ponemos límites EXCLUSIVOS a esta ventana para que nunca se aplaste
        mainStage.setMinWidth(1240);
        mainStage.setMinHeight(820);

        // 5. La abrimos maximizada por comodidad del usuario
        mainStage.setMaximized(true);

        mainStage.centerOnScreen();
        mainStage.show();

        // 6. Actualizamos la referencia global por si necesitas abrir otras cosas después
        this.primaryStage = mainStage;
    }

    public void showConfiguracionAnalisis() {
        try {
            // Cargamos el FXML usando tu fxmlLoader que ya integra Spring
            Parent root = fxmlLoader.load("/fx/analisis-view.fxml");

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Configuración de Análisis - YBioC");

            // Esto hace que sea una ventana modal (bloquea la de atrás)
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.initOwner(primaryStage); // La ventana principal es la "dueña"

            Scene scene = new Scene(root, DIALOG_LARGE_WIDTH, DIALOG_LARGE_HEIGHT);
            // Opcional: Cargar los mismos estilos
            scene.getStylesheets().add(getClass().getResource("/fx/styles.css").toExternalForm());

            dialogStage.setScene(scene);
            dialogStage.setMinWidth(DIALOG_LARGE_MIN_WIDTH);
            dialogStage.setMinHeight(DIALOG_LARGE_MIN_HEIGHT);
            dialogStage.setResizable(false);
            dialogStage.showAndWait(); // El código se detiene aquí hasta que cierres el diálogo

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showObrasSociales() {
        try {
            Parent root = fxmlLoader.load("/fx/obras-sociales-view.fxml");

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Obras Sociales - YBioC");
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.initOwner(primaryStage);

            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/fx/styles.css").toExternalForm());

            dialogStage.setScene(scene);
            dialogStage.setMinWidth(1100);
            dialogStage.setMinHeight(580);
            dialogStage.setResizable(false);
            dialogStage.sizeToScene();
            dialogStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showMedicos() {
        try {
            Parent root = fxmlLoader.load("/fx/medicos-view.fxml");

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Medicos - YBioC");
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.initOwner(primaryStage);

            Scene scene = new Scene(root, DIALOG_MEDIUM_WIDTH, DIALOG_MEDIUM_HEIGHT);
            scene.getStylesheets().add(getClass().getResource("/fx/styles.css").toExternalForm());

            dialogStage.setScene(scene);
            dialogStage.setMinWidth(DIALOG_MEDIUM_MIN_WIDTH);
            dialogStage.setMinHeight(DIALOG_MEDIUM_MIN_HEIGHT);
            dialogStage.setResizable(false);
            dialogStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showEspecialidades() {
        try {
            // Cargamos el FXML de especialidades
            Parent root = fxmlLoader.load("/fx/especialidades-view.fxml");

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Mantenimiento de Especialidades - YBioC");
            dialogStage.initModality(Modality.APPLICATION_MODAL); // Hace que sea una ventana emergente
            dialogStage.initOwner(primaryStage);

            Scene scene = new Scene(root, DIALOG_MEDIUM_WIDTH, DIALOG_MEDIUM_HEIGHT);
            scene.getStylesheets().add(getClass().getResource("/fx/styles.css").toExternalForm());

            dialogStage.setScene(scene);
            dialogStage.setMinWidth(DIALOG_MEDIUM_MIN_WIDTH);
            dialogStage.setMinHeight(DIALOG_MEDIUM_MIN_HEIGHT);
            dialogStage.setResizable(false);

            // El programa se detiene aquí hasta que se cierre esta ventana
            dialogStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showUnidades() {
        try {
            Parent root = fxmlLoader.load("/fx/unidades-view.fxml");

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Unidades de Medida - YBioC");
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.initOwner(primaryStage);

            Scene scene = new Scene(root, DIALOG_MEDIUM_WIDTH, DIALOG_MEDIUM_HEIGHT);
            scene.getStylesheets().add(getClass().getResource("/fx/styles.css").toExternalForm());

            dialogStage.setScene(scene);
            dialogStage.setMinWidth(DIALOG_MEDIUM_MIN_WIDTH);
            dialogStage.setMinHeight(DIALOG_MEDIUM_MIN_HEIGHT);
            dialogStage.setResizable(false);
            dialogStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showMetodos() {
        try {
            Parent root = fxmlLoader.load("/fx/metodos-view.fxml");

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Metodos - YBioC");
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.initOwner(primaryStage);

            Scene scene = new Scene(root, DIALOG_MEDIUM_WIDTH, DIALOG_MEDIUM_HEIGHT);
            scene.getStylesheets().add(getClass().getResource("/fx/styles.css").toExternalForm());

            dialogStage.setScene(scene);
            dialogStage.setMinWidth(DIALOG_MEDIUM_MIN_WIDTH);
            dialogStage.setMinHeight(DIALOG_MEDIUM_MIN_HEIGHT);
            dialogStage.setResizable(false);
            dialogStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showTitulos() {
        try {
            Parent root = fxmlLoader.load("/fx/titulos-view.fxml");

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Titulos - YBioC");
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.initOwner(primaryStage);

            Scene scene = new Scene(root, DIALOG_MEDIUM_WIDTH, DIALOG_MEDIUM_HEIGHT);
            scene.getStylesheets().add(getClass().getResource("/fx/styles.css").toExternalForm());

            dialogStage.setScene(scene);
            dialogStage.setMinWidth(DIALOG_MEDIUM_MIN_WIDTH);
            dialogStage.setMinHeight(DIALOG_MEDIUM_MIN_HEIGHT);
            dialogStage.setResizable(false);
            dialogStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showSecciones() {
        try {
            Parent root = fxmlLoader.load("/fx/secciones-view.fxml");
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Secciones - YBioC");
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/fx/styles.css").toExternalForm());
            dialogStage.setScene(scene);
            dialogStage.setMinWidth(700);
            dialogStage.setMinHeight(420);
            dialogStage.setResizable(false);
            dialogStage.sizeToScene();
            dialogStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showConfiguracionReporte() {
        try {
            Parent root = fxmlLoader.load("/fx/configuracion-reporte-view.fxml");

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Configuración del Informe - YBioC");
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.initOwner(primaryStage);

            Scene scene = new Scene(root, DIALOG_MEDIUM_WIDTH, DIALOG_MEDIUM_HEIGHT);
            scene.getStylesheets().add(getClass().getResource("/fx/styles.css").toExternalForm());

            dialogStage.setScene(scene);
            dialogStage.setMinWidth(DIALOG_MEDIUM_MIN_WIDTH);
            dialogStage.setMinHeight(DIALOG_MEDIUM_MIN_HEIGHT);
            dialogStage.setResizable(false);
            dialogStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showDerivaciones() {
        try {
            Parent root = fxmlLoader.load("/fx/derivaciones-view.fxml");

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Derivaciones - YBioC");
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.initOwner(primaryStage);

            Scene scene = new Scene(root, DIALOG_MEDIUM_WIDTH, DIALOG_MEDIUM_HEIGHT);
            scene.getStylesheets().add(getClass().getResource("/fx/styles.css").toExternalForm());

            dialogStage.setScene(scene);
            dialogStage.setMinWidth(DIALOG_MEDIUM_MIN_WIDTH);
            dialogStage.setMinHeight(DIALOG_MEDIUM_MIN_HEIGHT);
            dialogStage.setResizable(false);
            dialogStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showAnticipo() {
        try {
            Parent root = fxmlLoader.load("/fx/anticipo-view.fxml");

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Anticipo - YBioC");
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.initOwner(primaryStage);

            Scene scene = new Scene(root, DIALOG_SMALL_WIDTH, DIALOG_SMALL_HEIGHT);
            scene.getStylesheets().add(getClass().getResource("/fx/styles.css").toExternalForm());

            dialogStage.setScene(scene);
            dialogStage.setMinWidth(DIALOG_SMALL_MIN_WIDTH);
            dialogStage.setMinHeight(DIALOG_SMALL_MIN_HEIGHT);
            dialogStage.setResizable(false);
            dialogStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showBuscarPersona() {
        try {
            Parent root = fxmlLoader.load("/fx/buscar-persona-view.fxml");

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Buscar Persona - YBioC");
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.initOwner(primaryStage);

            Scene scene = new Scene(root, DIALOG_SEARCH_WIDTH, DIALOG_SEARCH_HEIGHT);
            scene.getStylesheets().add(getClass().getResource("/fx/styles.css").toExternalForm());

            dialogStage.setScene(scene);
            dialogStage.setMinWidth(DIALOG_SEARCH_MIN_WIDTH);
            dialogStage.setMinHeight(DIALOG_SEARCH_MIN_HEIGHT);
            dialogStage.setResizable(false);
            dialogStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showAltaDesdePaciente() {
        showNuevoPaciente();
    }

    public void showModificarOrden() {
        try {
            Parent root = fxmlLoader.load("/fx/modificar-orden-view.fxml");
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Modificar Orden - YBioC");
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(root, 640, 340);
            scene.getStylesheets().add(getClass().getResource("/fx/styles.css").toExternalForm());
            dialogStage.setScene(scene);
            dialogStage.setMinWidth(580);
            dialogStage.setMinHeight(300);
            dialogStage.setResizable(false);
            dialogStage.showAndWait();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showNuevoPaciente() {
        try {
            Parent root = fxmlLoader.load("/fx/nuevo-paciente-view.fxml");
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Nuevo Paciente - YBioC");
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(root, 640, 360);
            scene.getStylesheets().add(getClass().getResource("/fx/styles.css").toExternalForm());
            dialogStage.setScene(scene);
            dialogStage.setMinWidth(580);
            dialogStage.setMinHeight(320);
            dialogStage.setResizable(false);
            dialogStage.showAndWait();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showCargarOrden() {
        try {
            Parent root = fxmlLoader.load("/fx/cargar-orden-view.fxml");
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Cargar Orden - YBioC");
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(root, DIALOG_LARGE_WIDTH, DIALOG_LARGE_HEIGHT);
            scene.getStylesheets().add(getClass().getResource("/fx/styles.css").toExternalForm());
            dialogStage.setScene(scene);
            dialogStage.setMinWidth(DIALOG_LARGE_MIN_WIDTH);
            dialogStage.setMinHeight(DIALOG_LARGE_MIN_HEIGHT);
            dialogStage.showAndWait();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showPracticas() {
        try {
            Parent root = fxmlLoader.load("/fx/practicas-view.fxml");
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Prácticas - YBioC");
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(root, DIALOG_LARGE_WIDTH, DIALOG_LARGE_HEIGHT);
            scene.getStylesheets().add(getClass().getResource("/fx/styles.css").toExternalForm());
            dialogStage.setScene(scene);
            dialogStage.setMinWidth(DIALOG_LARGE_MIN_WIDTH);
            dialogStage.setMinHeight(DIALOG_LARGE_MIN_HEIGHT);
            dialogStage.showAndWait();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showCargarResultados() {
        try {
            Parent root = fxmlLoader.load("/fx/cargar-resultados-view.fxml");
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Cargar Resultados - YBioC");
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(root, DIALOG_LARGE_WIDTH, DIALOG_LARGE_HEIGHT);
            scene.getStylesheets().add(getClass().getResource("/fx/styles.css").toExternalForm());
            dialogStage.setScene(scene);
            dialogStage.setMinWidth(DIALOG_LARGE_MIN_WIDTH);
            dialogStage.setMinHeight(DIALOG_LARGE_MIN_HEIGHT);
            dialogStage.showAndWait();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showDetallePracticas() {
        try {
            Parent root = fxmlLoader.load("/fx/detalle-practicas-view.fxml");

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Detalle de Practicas - YBioC");
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.initOwner(primaryStage);

            Scene scene = new Scene(root, DIALOG_MEDIUM_WIDTH, DIALOG_MEDIUM_HEIGHT);
            scene.getStylesheets().add(getClass().getResource("/fx/styles.css").toExternalForm());

            dialogStage.setScene(scene);
            dialogStage.setMinWidth(DIALOG_MEDIUM_MIN_WIDTH);
            dialogStage.setMinHeight(DIALOG_MEDIUM_MIN_HEIGHT);
            dialogStage.setResizable(false);
            dialogStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showDetallePracticasOrden() {
        try {
            Parent root = fxmlLoader.load("/fx/detalle-practicas-orden-view.fxml");

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Detalle de Practicas - Orden - YBioC");
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.initOwner(primaryStage);

            Scene scene = new Scene(root, DIALOG_MEDIUM_WIDTH, DIALOG_MEDIUM_HEIGHT);
            scene.getStylesheets().add(getClass().getResource("/fx/styles.css").toExternalForm());

            dialogStage.setScene(scene);
            dialogStage.setMinWidth(DIALOG_MEDIUM_MIN_WIDTH);
            dialogStage.setMinHeight(DIALOG_MEDIUM_MIN_HEIGHT);
            dialogStage.setResizable(false);
            dialogStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showDetallePracticasFacturacion() {
        try {
            Parent root = fxmlLoader.load("/fx/detalle-practicas-facturacion-view.fxml");

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Detalle de Practicas - Facturacion - YBioC");
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.initOwner(primaryStage);

            Scene scene = new Scene(root, DIALOG_MEDIUM_WIDTH, DIALOG_MEDIUM_HEIGHT);
            scene.getStylesheets().add(getClass().getResource("/fx/styles.css").toExternalForm());

            dialogStage.setScene(scene);
            dialogStage.setMinWidth(DIALOG_MEDIUM_MIN_WIDTH);
            dialogStage.setMinHeight(DIALOG_MEDIUM_MIN_HEIGHT);
            dialogStage.setResizable(false);
            dialogStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showEnviarFacturacion() {
        try {
            Parent root = fxmlLoader.load("/fx/enviar-facturacion-view.fxml");

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Enviar Facturacion - YBioC");
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.initOwner(primaryStage);

            Scene scene = new Scene(root, DIALOG_LARGE_WIDTH, DIALOG_LARGE_HEIGHT);
            scene.getStylesheets().add(getClass().getResource("/fx/styles.css").toExternalForm());

            dialogStage.setScene(scene);
            dialogStage.setMinWidth(DIALOG_LARGE_MIN_WIDTH);
            dialogStage.setMinHeight(DIALOG_LARGE_MIN_HEIGHT);
            dialogStage.setResizable(false);
            dialogStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showDetalleResultados() {
        try {
            Parent root = fxmlLoader.load("/fx/detalle-resultados-view.fxml");

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Detalle de Resultados - YBioC");
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.initOwner(primaryStage);

            Scene scene = new Scene(root, DIALOG_MEDIUM_WIDTH, DIALOG_MEDIUM_HEIGHT);
            scene.getStylesheets().add(getClass().getResource("/fx/styles.css").toExternalForm());

            dialogStage.setScene(scene);
            dialogStage.setMinWidth(DIALOG_MEDIUM_MIN_WIDTH);
            dialogStage.setMinHeight(DIALOG_MEDIUM_MIN_HEIGHT);
            dialogStage.setResizable(false);
            dialogStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showDetalleTxt() {
        try {
            Parent root = fxmlLoader.load("/fx/detalle-txt-view.fxml");

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Detalle TXT - YBioC");
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.initOwner(primaryStage);

            Scene scene = new Scene(root, DIALOG_MEDIUM_WIDTH, DIALOG_MEDIUM_HEIGHT);
            scene.getStylesheets().add(getClass().getResource("/fx/styles.css").toExternalForm());

            dialogStage.setScene(scene);
            dialogStage.setMinWidth(DIALOG_MEDIUM_MIN_WIDTH);
            dialogStage.setMinHeight(DIALOG_MEDIUM_MIN_HEIGHT);
            dialogStage.setResizable(false);
            dialogStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showElegirBioquimico() {
        try {
            Parent root = fxmlLoader.load("/fx/elegir-bioquimico-view.fxml");

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Elegir Bioquimico - YBioC");
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.initOwner(primaryStage);

            Scene scene = new Scene(root, DIALOG_SMALL_WIDTH, DIALOG_SMALL_HEIGHT);
            scene.getStylesheets().add(getClass().getResource("/fx/styles.css").toExternalForm());

            dialogStage.setScene(scene);
            dialogStage.setMinWidth(DIALOG_SMALL_MIN_WIDTH);
            dialogStage.setMinHeight(DIALOG_SMALL_MIN_HEIGHT);
            dialogStage.setResizable(false);
            dialogStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Scene createScene(Parent root, double width, double height) {
        Scene scene = new Scene(root, width, height);
        scene.getStylesheets().add(getClass().getResource("/fx/styles.css").toExternalForm());
        return scene;
    }
}
