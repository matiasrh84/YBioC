package com.ybc.ybioq.config;

import com.ybc.ybioq.view.Login_nuevo;
import lombok.extern.log4j.Log4j2;

import javax.swing.*;

@Log4j2
public class HiloInicio extends Thread {

    JProgressBar progreso;
    public static String[] analisis = new String[50000];


    public HiloInicio(JProgressBar progreso1) {
        super();
        this.progreso = progreso1;
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
        new Login_nuevo().setVisible(true);
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
