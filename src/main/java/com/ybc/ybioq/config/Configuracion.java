package com.ybc.ybioq.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuracion {

    public static String direccionip = "";
    public static String puerto = "";
    public static String db = "";

    public static void tomaDatos() {
        Properties prop = new Properties();
        InputStream is = null;

        try {
            is = new FileInputStream("c:\\COBIAR\\configuracion.cfg");

            prop.load(is);
            direccionip = prop.getProperty("direccionip");
            puerto = prop.getProperty("puerto");
            db = prop.getProperty("db");
            System.out.println("ip:" + direccionip);
            System.out.println("puerto:" + puerto);
            System.out.println("db:" + db);
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}