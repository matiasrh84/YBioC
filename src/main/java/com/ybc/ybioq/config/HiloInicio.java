package com.ybc.ybioq.config;

import com.ybc.ybioq.view.Login;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationContext;

import javax.swing.*;

@Log4j2
public class HiloInicio extends Thread {

    JProgressBar progreso;
    public static String[] analisis = new String[50000];
    private final ApplicationContext context;


    public HiloInicio(JProgressBar progreso1, ApplicationContext context) {
        super();
        this.progreso = progreso1;
        this.context = context;
    }

    public void run() {
        int i = 0;
        while (i < 10) {
            progreso.setValue(i);
            i++;
            pausa(5);
        }

        while (i < 20) {
            progreso.setValue(i);
            i++;
            pausa(5);
        }


        while (i < 30) {
            progreso.setValue(i);
            i++;
            pausa(5);
        }


        while (i < 40) {
            progreso.setValue(i);
            i++;
            pausa(5);
        }

        while (i < 50) {
            progreso.setValue(i);
            i++;
            pausa(5);
        }

        while (i < 60) {
            progreso.setValue(i);
            i++;
            pausa(5);
        }


        while (i <= 100) {
            progreso.setValue(i);
            i++;
            pausa(5);
        }
        new Login(context).setVisible(true);
    }


    public void pausa(int mlSeg) {
        try {
            // pausa para el splash
            Thread.sleep(mlSeg);
        } catch (Exception e) {
            log.error(e.toString());
        }
    }
}
