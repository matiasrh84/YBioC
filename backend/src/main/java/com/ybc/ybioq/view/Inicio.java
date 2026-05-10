package com.ybc.ybioq.view;

import org.springframework.context.ApplicationContext;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public final class Inicio extends javax.swing.JFrame {

    private ApplicationContext context;

    public Inicio() {

        initComponents();
        setBackground(new Color(0, 0, 0, 0));
        setLocationRelativeTo(null);
        setResizable(false);
        iniciarSplash();
        cargarProgreso();
    }

    public void setContext(ApplicationContext context) {
        this.context = context;
        if (context != null) {
            // Cuando el contexto esté listo, cierra el splash y abre el siguiente formulario
            this.dispose();
            new Login(context).setVisible(true);
        }
    }

    private void cargarProgreso() {
        // SwingWorker para simular la carga del progreso
        SwingWorker<Void, Integer> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                int progress = 0;
                while (progress < 100) {
                    Thread.sleep(50); // Simula una tarea
                    progress += 1;
                    publish(progress); // Actualiza el progreso
                }
                return null;
            }

            @Override
            protected void process(List<Integer> chunks) {
                for (int value : chunks) {
                    progreso.setValue(value);
                }
            }

            @Override
            protected void done() {
                if (context == null) {
                    progreso.setString("Esperando contexto...");
                }
            }
        };
        worker.execute();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        progreso = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setModalExclusionType(null);
        setUndecorated(true);
        setResizable(false);

        jLabel1.setBackground(new Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ybioc.png"))); // NOI18N

        progreso.setBackground(new Color(255, 255, 255));
        progreso.setForeground(new Color(51, 204, 0));
        progreso.setString("");
        progreso.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                progresoStateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(progreso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(progreso, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void progresoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_progresoStateChanged
        if (progreso.getValue() == 100) {
            this.dispose();
        }
    }//GEN-LAST:event_progresoStateChanged

    public javax.swing.JProgressBar getjProgressBar1() {
        return progreso;
    }

    public void iniciarSplash() {
        this.getjProgressBar1().setBorderPainted(false);
        this.getjProgressBar1().setForeground(Color.decode("#0bdb46"));

        this.getjProgressBar1().setStringPainted(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JProgressBar progreso;
    // End of variables declaration//GEN-END:variables
}
