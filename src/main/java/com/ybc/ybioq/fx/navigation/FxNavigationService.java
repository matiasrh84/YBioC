package com.ybc.ybioq.fx.navigation;

import com.ybc.ybioq.entity.local.Usuario;
import com.ybc.ybioq.fx.controller.MainFxController;
import com.ybc.ybioq.fx.fxml.SpringFXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

    public void showMain(Usuario usuario) {
        Parent root = fxmlLoader.load("/fx/main-view.fxml");
        MainFxController controller = (MainFxController) root.getProperties().get(MainFxController.class.getName());
        controller.setUsuario(usuario);

        Scene scene = createScene(root, 1180, 760);
        primaryStage.setTitle("YBioC");
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    private Scene createScene(Parent root, double width, double height) {
        Scene scene = new Scene(root, width, height);
        scene.getStylesheets().add(getClass().getResource("/fx/styles.css").toExternalForm());
        return scene;
    }
}
