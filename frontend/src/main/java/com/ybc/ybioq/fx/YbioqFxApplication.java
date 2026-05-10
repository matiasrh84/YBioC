package com.ybc.ybioq.fx;

import com.ybc.ybioq.YbioqFrontendApplication;
import com.ybc.ybioq.fx.controller.SplashFxController;
import com.ybc.ybioq.fx.navigation.FxNavigationService;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

public class YbioqFxApplication extends Application {

    private ConfigurableApplicationContext applicationContext;
    private Timeline splashProgress;

    @Override
    public void start(Stage primaryStage) {
        SplashFxController splashController = showSplash(primaryStage);
        Task<ConfigurableApplicationContext> springBootTask = createSpringBootTask();

        springBootTask.setOnSucceeded(event -> {
            splashProgress.stop();
            splashController.setProgress(1.0, "Inicializacion completa");
            applicationContext = springBootTask.getValue();
            FxNavigationService navigationService = applicationContext.getBean(FxNavigationService.class);
            navigationService.setPrimaryStage(primaryStage);
            navigationService.showLogin();
        });

        springBootTask.setOnFailed(event -> {
            splashProgress.stop();
            Throwable exception = springBootTask.getException();
            splashController.setError(exception == null ? "No se pudo iniciar la aplicacion." : exception.getMessage());
        });

        Thread thread = new Thread(springBootTask, "ybioq-frontend-spring");
        thread.setDaemon(true);
        thread.start();
    }

    @Override
    public void stop() {
        if (splashProgress != null) {
            splashProgress.stop();
        }
        if (applicationContext != null) {
            applicationContext.close();
        }
        Platform.exit();
    }

    private Task<ConfigurableApplicationContext> createSpringBootTask() {
        String[] args = getParameters().getRaw().toArray(String[]::new);
        return new Task<>() {
            @Override
            protected ConfigurableApplicationContext call() {
                return new SpringApplicationBuilder(YbioqFrontendApplication.class)
                        .headless(false)
                        .run(args);
            }
        };
    }

    private SplashFxController showSplash(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fx/splash-view.fxml"));
            Parent root = loader.load();
            SplashFxController controller = loader.getController();
            Scene scene = new Scene(root, 520, 330);
            scene.getStylesheets().add(getClass().getResource("/fx/styles.css").toExternalForm());
            primaryStage.setTitle("YBioC");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.centerOnScreen();
            primaryStage.show();
            startSplashProgress(controller);
            return controller;
        } catch (IOException e) {
            throw new IllegalStateException("No se pudo cargar la pantalla de presentacion.", e);
        }
    }

    private void startSplashProgress(SplashFxController controller) {
        controller.setProgress(0.0, "Iniciando aplicacion");
        splashProgress = new Timeline(new KeyFrame(Duration.millis(55), event -> {
            double nextProgress = Math.min(controller.getProgress() + 0.01, 0.92);
            String message = nextProgress < 0.35
                    ? "Cargando modulos"
                    : nextProgress < 0.70 ? "Conectando servicios" : "Preparando interfaz";
            controller.setProgress(nextProgress, message);
        }));
        splashProgress.setCycleCount(Timeline.INDEFINITE);
        splashProgress.play();
    }
}
