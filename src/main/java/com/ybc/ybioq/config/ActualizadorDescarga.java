package com.ybc.ybioq.config;

import com.ybc.ybioq.view.Dialogo;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

@Log4j2
public class ActualizadorDescarga {

    public void descargarEInstalar(String downloadUrl) {
        try {
            // Ruta temporal para descargar el nuevo JAR
            String rutaTemporal = "nuevo_programa.jar";
            Files.copy(new URL(downloadUrl).openStream(), Paths.get(rutaTemporal));

            // Reemplazar el JAR actual
            String rutaActual = System.getProperty("java.class.path");
            Files.move(Paths.get(rutaTemporal), Paths.get(rutaActual), java.nio.file.StandardCopyOption.REPLACE_EXISTING);

            Dialogo dialogo = new Dialogo(null, true);
            dialogo.lblTitulo.setText("Atención");
            dialogo.lblCuerpo.setText("Actualización completada. El programa se reiniciará.");
            dialogo.setVisible(true);

            // Reinicia el programa
            reiniciarPrograma();
        } catch (IOException e) {
            log.error(e.getMessage());
            Dialogo dialogo = new Dialogo(null, true);
            dialogo.lblTitulo.setText("Error");
            dialogo.lblCuerpo.setText("Error al descargar o instalar la actualización");
            dialogo.setVisible(true);
        }
    }

    public void reiniciarPrograma() {
        try {
            // Obtiene el comando de ejecución
            String java = System.getProperty("java.home") + "/bin/java";
            String classpath = System.getProperty("java.class.path");
            String mainClass = System.getProperty("sun.java.command");

            ProcessBuilder builder = new ProcessBuilder(java, "-cp", classpath, mainClass);
            builder.start();

            // Cierra la aplicación actual
            System.exit(0);
        } catch (Exception e) {
            log.error(e.getMessage());
            Dialogo dialogo = new Dialogo(null, true);
            dialogo.lblTitulo.setText("Error");
            dialogo.lblCuerpo.setText("Error al reiniciar el programa");
            dialogo.setVisible(true);
        }
    }
}
