package com.ybc.ybioq.fx;

import com.ybc.ybioq.YbioqFrontendApplication;
import com.ybc.ybioq.fx.client.HealthClient;
import com.ybc.ybioq.fx.client.dto.HealthResponse;
import com.ybc.ybioq.fx.controller.SplashFxController;
import com.ybc.ybioq.fx.navigation.FxNavigationService;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

public class YbioqFxApplication extends Application {

    private static final String APP_VERSION = "1.0.0";
    private static final int MAX_REINTENTOS = 3;
    private static final long ESPERA_MS = 2_000;

    private ConfigurableApplicationContext applicationContext;

    @Override
    public void start(Stage primaryStage) {
        Stage splashStage = new Stage();
        SplashFxController splash = mostrarSplash(splashStage);

        Task<ConfigurableApplicationContext> springTask = crearTareaSpring();
        springTask.setOnSucceeded(e -> {
            applicationContext = springTask.getValue();
            ejecutarVerificacion(splash, splashStage, primaryStage);
        });
        springTask.setOnFailed(e -> {
            Throwable ex = springTask.getException();
            Platform.runLater(() -> splash.setError(
                    "Error al iniciar el sistema: " + (ex != null ? ex.getMessage() : "desconocido")));
        });

        Thread t = new Thread(springTask, "ybioq-spring-init");
        t.setDaemon(true);
        t.start();
    }

    @Override
    public void stop() {
        if (applicationContext != null) applicationContext.close();
        Platform.exit();
    }

    // ── Verificación secuencial ───────────────────────────────────

    private void ejecutarVerificacion(SplashFxController splash, Stage splashStage, Stage primaryStage) {
        Task<Void> verificacion = new Task<>() {
            @Override
            protected Void call() throws Exception {

                paso(splash, 0.10, "Módulos del sistema cargados.");

                // ── Conexión con el backend ───────────────────────
                paso(splash, 0.25, "Verificando conexión con el servidor...");
                HealthClient healthClient = applicationContext.getBean(HealthClient.class);
                HealthResponse health = null;

                for (int intento = 1; intento <= MAX_REINTENTOS; intento++) {
                    try {
                        health = healthClient.verificar();
                        break;
                    } catch (Exception ex) {
                        if (intento < MAX_REINTENTOS) {
                            paso(splash, 0.25 + intento * 0.05,
                                    "Sin respuesta del servidor. Reintentando (" + (intento + 1) + "/" + MAX_REINTENTOS + ")...");
                            Thread.sleep(ESPERA_MS);
                        } else {
                            throw new RuntimeException(
                                    "No se pudo conectar al servidor después de " + MAX_REINTENTOS + " intentos.\n"
                                            + "Verifique que el backend esté activo e intente nuevamente.");
                        }
                    }
                }

                paso(splash, 0.50, "Servidor disponible.");

                // ── Versión ───────────────────────────────────────
                paso(splash, 0.65, "Verificando versión...");
                if (health != null && health.version() != null && !health.version().equals(APP_VERSION)) {
                    paso(splash, 0.70,
                            "Advertencia: versión del cliente (" + APP_VERSION
                                    + ") difiere del servidor (" + health.version() + ").");
                    Thread.sleep(1_500);
                } else {
                    paso(splash, 0.70, "Versión verificada (" + APP_VERSION + ").");
                }

                // ── Licencia ──────────────────────────────────────
                paso(splash, 0.80, "Verificando licencia...");
                if (health != null && !health.licenciaValida()) {
                    throw new RuntimeException(
                            "Licencia no válida o vencida.\nContacte al administrador del sistema.");
                }
                paso(splash, 0.92, "Licencia válida.");
                Thread.sleep(400);

                paso(splash, 1.0, "Sistema listo. Iniciando sesión...");
                Thread.sleep(500);
                return null;
            }
        };

        verificacion.setOnSucceeded(e -> {
            FxNavigationService nav = applicationContext.getBean(FxNavigationService.class);
            splashStage.close();
            nav.setPrimaryStage(primaryStage);
            nav.showLogin();
        });

        verificacion.setOnFailed(e -> {
            Throwable ex = verificacion.getException();
            String msg = ex != null ? ex.getMessage() : "Error desconocido.";
            Platform.runLater(() -> {
                splash.setError(msg);
                splash.setReintentarCallback(
                        () -> ejecutarVerificacion(splash, splashStage, primaryStage));
            });
        });

        Thread t = new Thread(verificacion, "ybioq-verificacion");
        t.setDaemon(true);
        t.start();
    }

    // ── Helpers ───────────────────────────────────────────────────

    private void paso(SplashFxController splash, double progreso, String mensaje) {
        Platform.runLater(() -> splash.setProgress(progreso, mensaje));
    }

    private Task<ConfigurableApplicationContext> crearTareaSpring() {
        String[] args = getParameters().getRaw().toArray(String[]::new);
        return new Task<>() {
            @Override
            protected ConfigurableApplicationContext call() {
                Platform.runLater(() -> {
                });
                return new SpringApplicationBuilder(YbioqFrontendApplication.class)
                        .headless(false)
                        .run(args);
            }
        };
    }

    private SplashFxController mostrarSplash(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fx/splash-view.fxml"));
            Parent root = loader.load();
            SplashFxController controller = loader.getController();

            Scene scene = new Scene(root, 760, 420);
            scene.getStylesheets().add(getClass().getResource("/fx/styles.css").toExternalForm());

            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();

            return controller;
        } catch (IOException e) {
            throw new IllegalStateException("No se pudo cargar la pantalla de inicio.", e);
        }
    }
}
