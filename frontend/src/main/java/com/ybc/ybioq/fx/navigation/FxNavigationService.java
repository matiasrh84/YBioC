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

    private final SpringFXMLLoader fxmlLoader;
    private Stage primaryStage;

    public FxNavigationService(SpringFXMLLoader fxmlLoader) {
        this.fxmlLoader = fxmlLoader;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setMinWidth(900);
        this.primaryStage.setMinHeight(620);
    }

    public void showLogin() {
        Parent root = fxmlLoader.load("/fx/login-view.fxml");
        Scene scene = createScene(root, 900, 620);
        primaryStage.setTitle("YBioC - Ingreso");
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
        Scene scene = createScene(root, 1280, 850);

        mainStage.setTitle("YBioC");
        mainStage.setScene(scene);

        // 4. Le ponemos límites EXCLUSIVOS a esta ventana para que nunca se aplaste
        mainStage.setMinWidth(1100);
        mainStage.setMinHeight(750);

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
            Parent root = fxmlLoader.load("/fx/analisis.fxml");

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Configuración de Análisis - YBioC");

            // Esto hace que sea una ventana modal (bloquea la de atrás)
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.initOwner(primaryStage); // La ventana principal es la "dueña"

            Scene scene = new Scene(root);
            // Opcional: Cargar los mismos estilos
            scene.getStylesheets().add(getClass().getResource("/fx/styles.css").toExternalForm());

            dialogStage.setScene(scene);
            dialogStage.setResizable(false); // Normalmente los diálogos de config no se redimensionan
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

            Scene scene = new Scene(root, 1260, 760);
            scene.getStylesheets().add(getClass().getResource("/fx/styles.css").toExternalForm());

            dialogStage.setScene(scene);
            dialogStage.setMinWidth(1180);
            dialogStage.setMinHeight(700);
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

            Scene scene = new Scene(root, 1260, 760);
            scene.getStylesheets().add(getClass().getResource("/fx/styles.css").toExternalForm());

            dialogStage.setScene(scene);
            dialogStage.setMinWidth(1180);
            dialogStage.setMinHeight(700);
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

            Scene scene = new Scene(root, 1260, 760);
            scene.getStylesheets().add(getClass().getResource("/fx/styles.css").toExternalForm());

            dialogStage.setScene(scene);
            dialogStage.setMinWidth(1180);
            dialogStage.setMinHeight(700);

            // El programa se detiene aquí hasta que se cierre esta ventana
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
