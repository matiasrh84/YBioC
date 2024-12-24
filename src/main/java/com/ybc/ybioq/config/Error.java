package com.ybc.ybioq.config;


import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


public class Error {
    public static void escribirLog(String rutaArchivo, String mensaje) {

        Logger logger = Logger.getLogger("CobiarErrores");
        FileHandler fh;

        try {

            fh = new FileHandler(rutaArchivo, true);
            logger.addHandler(fh);

            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

            logger.info(mensaje);

        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
