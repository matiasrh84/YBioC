package com.ybc.ybioq.view;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import static com.ybc.ybioq.config.Escape.funcionescape;

public class DetallePracticasOrden extends javax.swing.JDialog {

    private int x;
    private int y;
    DefaultTableModel model;
    public static String id_orden, afiliado, servicio, fecha, total, num_orden, hora, historia_clinica;

    DefaultTableCellRenderer alinearCentro, alinearDerecha, alinearIzquierda;

    public DetallePracticasOrden(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        funcionescape(this);
        cargartabla(id_orden);
        txtOrden.setText(id_orden);
        txtAfiliado.setText(afiliado);
        txtFecha.setText(fecha);

    }

    void cargartabla(String valor) {
        String[] Titulo = {"Cod", "Practica", "Resultado", "N° de Orden", "O.Social"};
        String[] Registros = new String[5];
        //String sql = "SELECT ordenes_tienen_practicas.id_ordenes, practicas.codigo_practica, practicas.determinacion_practica, precio_practica FROM ordenes_tienen_practicas INNER JOIN practicas ON practicas.id_practicas = ordenes_tienen_practicas.id_practicas  WHERE ordenes_tienen_practicas.id_ordenes=" + valor;
        String sql = "SELECT ordenes_tienen_practicas.id_ordenes, practicas.codigo_practica, practicas.determinacion_practica, numero_orden,razonsocial_obrasocial,factura,Int_codigo_obrasocial,pacientes_tienen_obrasociales.numero_afiliado\n"
                + "FROM ordenes_tienen_practicas \n"
                + "INNER JOIN ordenes ON ordenes_tienen_practicas.id_ordenes = ordenes.id_ordenes \n"
                + "INNER JOIN practicas ON practicas.id_practicas = ordenes_tienen_practicas.id_practicas \n"
                + "INNER JOIN historia_clinica ON historia_clinica.id_ordenes = ordenes.id_ordenes \n"
                + "INNER JOIN obrasocial on obrasocial.id_obrasocial=ordenes.id_obrasocial\n"
                + "INNER JOIN pacientes_tienen_obrasociales ON pacientes_tienen_obrasociales.id_obrasocial=ordenes.id_obrasocial and pacientes_tienen_obrasociales.id_Pacientes=ordenes.id_Pacientes\n"
                + "WHERE ordenes_tienen_practicas.id_ordenes=" + valor;
        model = new DefaultTableModel(null, Titulo) {
            ////Celdas no editables////////
            public boolean isCellEditable(int row, int column) {
                if (column == 3) {
                    return true;
                } else {
                    return false;
                }
            }
        };
//
//        try {
//            Statement st = cn.createStatement();
//            ResultSet rs = st.executeQuery(sql);
//            while (rs.next()) {
//
//                Registros[0] = rs.getString(2);
//                Registros[1] = rs.getString(3);
//                Registros[2] = rs.getString(4);
//                //////////N° de Orden
//                Registros[3] = rs.getString("numero_orden");
//                if (rs.getInt("factura") == 1) {
//                    Registros[4] = rs.getString("razonsocial_obrasocial");
//
//                } else {
//                    Registros[4] = "PARTICULAR";
//                }
//                txtNumeroAfiliado.setText(rs.getString("numero_afiliado"));
//                model.addRow(Registros);
//
//            }
//            tablaPracticas.setModel(model);
//            //  tablapracticas.setAutoCreateRowSorter(true);
//            /////////////////////////////////////////////////////////////////
//            tablaPracticas.getColumnModel().getColumn(2).setMaxWidth(0);
//            tablaPracticas.getColumnModel().getColumn(2).setMinWidth(0);
//            tablaPracticas.getColumnModel().getColumn(2).setPreferredWidth(0);
//            /////ajustar ancho de columna///////
//            tablaPracticas.getColumnModel().getColumn(0).setPreferredWidth(50);
//            tablaPracticas.getColumnModel().getColumn(1).setPreferredWidth(200);
//            tablaPracticas.getColumnModel().getColumn(3).setPreferredWidth(80);
//            tablaPracticas.getColumnModel().getColumn(4).setPreferredWidth(200);
//            /////////////////////////////////////////////////////////////*/
//            alinear();
//            tablaPracticas.getColumnModel().getColumn(0).setCellRenderer(alinearCentro);
//            tablaPracticas.getColumnModel().getColumn(1).setCellRenderer(alinearIzquierda);
//            tablaPracticas.getColumnModel().getColumn(3).setCellRenderer(alinearCentro);
//            tablaPracticas.getColumnModel().getColumn(4).setCellRenderer(alinearCentro);
//            //////////////////////////////////////////////////////////////////
//            /// cn.close();
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, ex);
//        }
    }

    void alinear() {
        alinearCentro = new DefaultTableCellRenderer();
        alinearCentro.setHorizontalAlignment(SwingConstants.CENTER);

        alinearDerecha = new DefaultTableCellRenderer();
        alinearDerecha.setHorizontalAlignment(SwingConstants.RIGHT);

        alinearIzquierda = new DefaultTableCellRenderer();
        alinearIzquierda.setHorizontalAlignment(SwingConstants.LEFT);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        rSButtonIconOne1 = new RSMaterialComponent.RSButtonIconOne();
        jPanel3 = new javax.swing.JPanel();
        btnSalir = new RSMaterialComponent.RSButtonMaterialIconOne();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaPracticas = new RSMaterialComponent.RSTableMetroCustom();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JLabel();
        txtOrden = new javax.swing.JLabel();
        txtNumeroAfiliado = new javax.swing.JLabel();
        txtAfiliado = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(0, 90, 132));
        jPanel2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel2MouseDragged(evt);
            }
        });
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel2MousePressed(evt);
            }
        });

        rSButtonIconOne1.setBackground(new java.awt.Color(255, 255, 255));
        rSButtonIconOne1.setForeground(new java.awt.Color(0, 90, 132));
        rSButtonIconOne1.setBackgroundHover(new java.awt.Color(0, 90, 132));
        rSButtonIconOne1.setForegroundHover(new java.awt.Color(0, 90, 132));
        rSButtonIconOne1.setForegroundText(new java.awt.Color(0, 90, 132));
        rSButtonIconOne1.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.CLOSE);
        rSButtonIconOne1.setRippleColor(new java.awt.Color(0, 90, 132));
        rSButtonIconOne1.setSizeIcon(20.0F);
        rSButtonIconOne1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIconOne1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(rSButtonIconOne1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(rSButtonIconOne1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        btnSalir.setBackground(new java.awt.Color(0, 90, 132));
        btnSalir.setText("Salir");
        btnSalir.setBackgroundHover(new java.awt.Color(204, 204, 204));
        btnSalir.setForegroundIcon(new java.awt.Color(0, 90, 132));
        btnSalir.setForegroundIconHover(new java.awt.Color(0, 90, 132));
        btnSalir.setHorizontalAlignment(SwingConstants.CENTER);
        btnSalir.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.EXIT_TO_APP);
        btnSalir.setRound(20);
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        tablaPracticas.setForeground(new java.awt.Color(255, 255, 255));
        tablaPracticas.setModel(new DefaultTableModel(
                new Object[][]{

                },
                new String[]{

                }
        ));
        tablaPracticas.setBackgoundHead(new java.awt.Color(0, 90, 132));
        tablaPracticas.setBackgoundHover(new java.awt.Color(0, 90, 132));
        tablaPracticas.setColorBorderHead(new java.awt.Color(255, 255, 255));
        tablaPracticas.setColorBorderRows(new java.awt.Color(255, 255, 255));
        tablaPracticas.setGridColor(new java.awt.Color(204, 204, 204));
        tablaPracticas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tablaPracticasKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tablaPracticas);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 90, 132));
        jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel2.setText("N° de afiliado:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 90, 132));
        jLabel3.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel3.setText("Afiliado:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 90, 132));
        jLabel4.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel4.setText("Fecha:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 90, 132));
        jLabel5.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel5.setText("N° de protocolo:");

        txtFecha.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtFecha.setForeground(new java.awt.Color(51, 102, 0));
        txtFecha.setHorizontalAlignment(SwingConstants.CENTER);
        txtFecha.setText(" ");

        txtOrden.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtOrden.setForeground(new java.awt.Color(51, 102, 0));
        txtOrden.setHorizontalAlignment(SwingConstants.CENTER);
        txtOrden.setText(" ");

        txtNumeroAfiliado.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtNumeroAfiliado.setForeground(new java.awt.Color(51, 102, 0));
        txtNumeroAfiliado.setHorizontalAlignment(SwingConstants.CENTER);
        txtNumeroAfiliado.setText(" ");

        txtAfiliado.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtAfiliado.setForeground(new java.awt.Color(51, 102, 0));
        txtAfiliado.setHorizontalAlignment(SwingConstants.CENTER);
        txtAfiliado.setText(" ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jScrollPane1)
                                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                                .addComponent(jLabel4)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(txtFecha, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(jLabel5)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(txtOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(jLabel2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtNumeroAfiliado, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel3)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtAfiliado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtNumeroAfiliado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtAfiliado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(10, 10, 10)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel5)
                                        .addComponent(txtFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtOrden))
                                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 90, 132));
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel1.setText("Detalle de prácticas");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jLabel1)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rSButtonIconOne1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIconOne1ActionPerformed

        dispose();

    }//GEN-LAST:event_rSButtonIconOne1ActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed

        dispose();

    }//GEN-LAST:event_btnSalirActionPerformed

    private void jPanel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MousePressed

        x = evt.getX();
        y = evt.getY();

    }//GEN-LAST:event_jPanel2MousePressed

    private void jPanel2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseDragged

        this.setLocation(this.getLocation().x + evt.getX() - x, this.getLocation().y + evt.getY() - y);

    }//GEN-LAST:event_jPanel2MouseDragged

    private void tablaPracticasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaPracticasKeyPressed
//
//        Connection cn = mysql.Conectar();
//        try {
//            String sSQL3 = "UPDATE ordenes SET numero_orden=? "
//                    + " WHERE id_ordenes=" + id_orden;
//            PreparedStatement pst = cn.prepareStatement(sSQL3);
//            pst.setString(1, tablaPracticas.getValueAt(tablaPracticas.getSelectedRow(), 3).toString());
//            int i = pst.executeUpdate();
//            if (i > 0) {
//                JOptionPane.showMessageDialog(null, "Se modificó con exito...");
//            }
//        } catch (HeadlessException | SQLException e) {
//            JOptionPane.showMessageDialog(null, e);
//        }
//
    }//GEN-LAST:event_tablaPracticasKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private RSMaterialComponent.RSButtonMaterialIconOne btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private RSMaterialComponent.RSButtonIconOne rSButtonIconOne1;
    private RSMaterialComponent.RSTableMetroCustom tablaPracticas;
    private javax.swing.JLabel txtAfiliado;
    private javax.swing.JLabel txtFecha;
    private javax.swing.JLabel txtNumeroAfiliado;
    private javax.swing.JLabel txtOrden;
    // End of variables declaration//GEN-END:variables
}
