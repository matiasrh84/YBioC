package com.ybc.ybioq.view;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static com.ybc.ybioq.config.Escape.funcionescape;

public class Derivaciones_nuevo extends javax.swing.JDialog {

    private int x;
    private int y;
    DefaultTableModel model;
    DefaultTableCellRenderer alinearCentro, alinearDerecha, alinearIzquierda;
    public int bmodificar = 0;

    public Derivaciones_nuevo(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        funcionescape(this);
        cargartabla("");
        eventotabla();
        dobleclick();
    }

    private boolean isNumeric(String cadena) {
        try {
            Double.parseDouble(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    void dobleclick() {
        tablaDerivaciones.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    txtNombre.setText(tablaDerivaciones.getValueAt(tablaDerivaciones.getSelectedRow(), 1).toString());
                    txtDireccion.setText(tablaDerivaciones.getValueAt(tablaDerivaciones.getSelectedRow(), 2).toString());
                    txtTelefono.setText(tablaDerivaciones.getValueAt(tablaDerivaciones.getSelectedRow(), 3).toString());
                    txtMail.setText(tablaDerivaciones.getValueAt(tablaDerivaciones.getSelectedRow(), 4).toString());
                    txtObservaciones.setText(tablaDerivaciones.getValueAt(tablaDerivaciones.getSelectedRow(), 5).toString());
                    bmodificar = 1;
                    btnAgregar.setEnabled(false);
                    btnModificar.setEnabled(true);
                }
            }
        });
    }

    void cargartabla(String valor) {
        String[] Titulo = {"Id", "Nombre", "Dirección", "Teléfono", "Mail", "Obs"};
        String[] Registros = new String[6];
        String sql = "SELECT id_derivaciones, nombre, direccion, telefono, mail, observaciones FROM derivaciones WHERE nombre LIKE '%" + valor + "%'";

        int i = 0;
        model = new DefaultTableModel(null, Titulo) {
            ////Celdas no editables////////
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
//        try {
//
//            ResultSet rs = st.executeQuery(sql);
//            while (rs.next()) {
//                Registros[0] = rs.getString("id_derivaciones");
//                Registros[1] = rs.getString("nombre");
//                Registros[2] = rs.getString("direccion");
//                Registros[3] = rs.getString("telefono");
//                Registros[4] = rs.getString("mail");
//                Registros[5] = rs.getString("observaciones");
//                model.addRow(Registros);
//            }
//            tablaDerivaciones.setModel(model);
//            tablaDerivaciones.setAutoCreateRowSorter(true);
//            tablaDerivaciones.getColumnModel().getColumn(0).setMaxWidth(0);
//            tablaDerivaciones.getColumnModel().getColumn(0).setMinWidth(0);
//            tablaDerivaciones.getColumnModel().getColumn(0).setPreferredWidth(0);
//            tablaDerivaciones.getColumnModel().getColumn(5).setMaxWidth(0);
//            tablaDerivaciones.getColumnModel().getColumn(5).setMinWidth(0);
//            tablaDerivaciones.getColumnModel().getColumn(5).setPreferredWidth(0);
//            alinear();
//            tablaDerivaciones.getColumnModel().getColumn(0).setCellRenderer(alinearCentro);
//            tablaDerivaciones.getColumnModel().getColumn(1).setCellRenderer(alinearCentro);
//            tablaDerivaciones.getColumnModel().getColumn(2).setCellRenderer(alinearCentro);
//            tablaDerivaciones.getColumnModel().getColumn(3).setCellRenderer(alinearCentro);
//            tablaDerivaciones.getColumnModel().getColumn(4).setCellRenderer(alinearCentro);
//            tablaDerivaciones.getColumnModel().getColumn(5).setCellRenderer(alinearCentro);
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, ex);
//            JOptionPane.showMessageDialog(null, "Error en la base de datos.");
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

    void eventotabla() {
        tablaDerivaciones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        ListSelectionModel rowSM = tablaDerivaciones.getSelectionModel();

        rowSM.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    return;
                }
                ListSelectionModel lsm = (ListSelectionModel) e.getSource();
                if (lsm.isSelectionEmpty()) {
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        txtNombre = new RSMaterialComponent.RSTextFieldMaterial();
        txtMail = new RSMaterialComponent.RSTextFieldMaterial();
        txtTelefono = new RSMaterialComponent.RSTextFieldMaterial();
        txtDireccion = new RSMaterialComponent.RSTextFieldMaterial();
        txtObservaciones = new RSMaterialComponent.RSTextFieldMaterial();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaDerivaciones = new RSMaterialComponent.RSTableMetroCustom();
        jPanel4 = new javax.swing.JPanel();
        btnAgregar = new RSMaterialComponent.RSButtonMaterialIconOne();
        btnSalir = new RSMaterialComponent.RSButtonMaterialIconOne();
        btnModificar = new RSMaterialComponent.RSButtonMaterialIconOne();
        jPanel1 = new javax.swing.JPanel();
        rSButtonIconOne1 = new RSMaterialComponent.RSButtonIconOne();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(255, 255, 255), new java.awt.Color(0, 90, 132)));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        txtNombre.setForeground(new java.awt.Color(0, 90, 132));
        txtNombre.setColorMaterial(new java.awt.Color(0, 90, 132));
        txtNombre.setPhColor(new java.awt.Color(0, 90, 132));
        txtNombre.setPlaceholder("Nombre");
        txtNombre.setSelectionColor(new java.awt.Color(0, 90, 132));

        txtMail.setForeground(new java.awt.Color(0, 90, 132));
        txtMail.setColorMaterial(new java.awt.Color(0, 90, 132));
        txtMail.setPhColor(new java.awt.Color(0, 90, 132));
        txtMail.setPlaceholder("Mail");
        txtMail.setSelectionColor(new java.awt.Color(0, 90, 132));

        txtTelefono.setForeground(new java.awt.Color(0, 90, 132));
        txtTelefono.setColorMaterial(new java.awt.Color(0, 90, 132));
        txtTelefono.setPhColor(new java.awt.Color(0, 90, 132));
        txtTelefono.setPlaceholder("Teléfono");
        txtTelefono.setSelectionColor(new java.awt.Color(0, 90, 132));
        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyReleased(evt);
            }
        });

        txtDireccion.setForeground(new java.awt.Color(0, 90, 132));
        txtDireccion.setColorMaterial(new java.awt.Color(0, 90, 132));
        txtDireccion.setPhColor(new java.awt.Color(0, 90, 132));
        txtDireccion.setPlaceholder("Dirección");
        txtDireccion.setSelectionColor(new java.awt.Color(0, 90, 132));

        txtObservaciones.setForeground(new java.awt.Color(0, 90, 132));
        txtObservaciones.setColorMaterial(new java.awt.Color(0, 90, 132));
        txtObservaciones.setPhColor(new java.awt.Color(0, 90, 132));
        txtObservaciones.setPlaceholder("Observación");
        txtObservaciones.setSelectionColor(new java.awt.Color(0, 90, 132));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtObservaciones, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                                        .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                        .addComponent(txtMail, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtMail, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtObservaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        tablaDerivaciones.setForeground(new java.awt.Color(255, 255, 255));
        tablaDerivaciones.setModel(new DefaultTableModel(
                new Object[][]{

                },
                new String[]{

                }
        ));
        tablaDerivaciones.setBackgoundHead(new java.awt.Color(0, 90, 132));
        tablaDerivaciones.setBackgoundHover(new java.awt.Color(0, 90, 132));
        tablaDerivaciones.setColorBorderHead(new java.awt.Color(255, 255, 255));
        tablaDerivaciones.setColorBorderRows(new java.awt.Color(255, 255, 255));
        tablaDerivaciones.setGridColor(new java.awt.Color(204, 204, 204));
        jScrollPane1.setViewportView(tablaDerivaciones);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        btnAgregar.setBackground(new java.awt.Color(0, 90, 132));
        btnAgregar.setText("Agregar");
        btnAgregar.setBackgroundHover(new java.awt.Color(204, 204, 204));
        btnAgregar.setForegroundIcon(new java.awt.Color(0, 90, 132));
        btnAgregar.setForegroundIconHover(new java.awt.Color(0, 90, 132));
        btnAgregar.setHorizontalAlignment(SwingConstants.CENTER);
        btnAgregar.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ADD);
        btnAgregar.setRound(20);
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

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

        btnModificar.setBackground(new java.awt.Color(0, 90, 132));
        btnModificar.setText("Modificar");
        btnModificar.setBackgroundHover(new java.awt.Color(204, 204, 204));
        btnModificar.setForegroundIcon(new java.awt.Color(0, 90, 132));
        btnModificar.setForegroundIconHover(new java.awt.Color(0, 90, 132));
        btnModificar.setHorizontalAlignment(SwingConstants.CENTER);
        btnModificar.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.EDIT);
        btnModificar.setRound(20);
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addContainerGap(16, Short.MAX_VALUE)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(0, 90, 132));
        jPanel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(MouseEvent evt) {
                jPanel1MouseDragged(evt);
            }
        });
        jPanel1.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                jPanel1MousePressed(evt);
            }
        });

        rSButtonIconOne1.setBackground(new java.awt.Color(255, 255, 255));
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(rSButtonIconOne1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(rSButtonIconOne1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 90, 132));
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel1.setText("Derivaciones");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap())
                                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void rSButtonIconOne1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIconOne1ActionPerformed

        dispose();

    }//GEN-LAST:event_rSButtonIconOne1ActionPerformed

    private void jPanel1MousePressed(MouseEvent evt) {//GEN-FIRST:event_jPanel1MousePressed

        x = evt.getX();
        y = evt.getY();

    }//GEN-LAST:event_jPanel1MousePressed

    private void jPanel1MouseDragged(MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseDragged

        this.setLocation(this.getLocation().x + evt.getX() - x, this.getLocation().y + evt.getY() - y);

    }//GEN-LAST:event_jPanel1MouseDragged

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed

        dispose();

    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
//
//        Connection cn = mysql.Conectar();
//        String nombre, direccion, mail, observaciones;
//        long telefono;
//        String sSQL;
//        nombre = txtNombre.getText();
//        direccion = txtDireccion.getText();
//        if (!txtTelefono.getText().equals("")) {
//            telefono = Long.valueOf(txtTelefono.getText());
//        } else {
//            telefono = 0;
//        }
//        mail = txtMail.getText();
//        observaciones = txtObservaciones.getText();
//        int control = 0;
//        if (!"".equals(nombre)) {
//            try {
//                String sql = "SELECT nombre FROM derivaciones";
//                Statement st = cn.createStatement();
//                ResultSet rs = st.executeQuery(sql);
//                int contador = 0;
//                while (rs.next()) {
//                    contador++;
//                }
//                rs.beforeFirst();
//                if (contador != 0) {
//                    while (rs.next()) {
//                        if (nombre.equals(rs.getString("nombre"))) {
//                            JOptionPane.showMessageDialog(null, "El laboratorio ya existe");
//                            txtNombre.requestFocus();
//                            txtNombre.selectAll();
//                            control = 1;
//                            break;
//                        }
//                    }
//                }
//                rs.beforeFirst();
//                if (control == 0) {
//                    sSQL = "INSERT INTO derivaciones (nombre, direccion, telefono, mail, observaciones) VALUES(?,?,?,?,?)";
//                    PreparedStatement pst = cn.prepareStatement(sSQL);
//                    pst.setString(1, nombre);
//                    pst.setString(2, direccion);
//                    pst.setLong(3, telefono);
//                    pst.setString(4, mail);
//                    pst.setString(5, observaciones);
//                    pst.executeUpdate();
//                    txtNombre.setText("");
//                    txtDireccion.setText("");
//                    txtTelefono.setText("");
//                    txtMail.setText("");
//                    txtObservaciones.setText("");
//                    txtNombre.requestFocus();
//                    cargartabla("");
//                }
//            } catch (SQLException ex) {
//                JOptionPane.showMessageDialog(null, "Error en la base de datos...");
//            }
//        } else {
//            JOptionPane.showMessageDialog(null, "Debe completar los campos obligatorios");
//        }

    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
//
//        ConexionMySQLLocal mysql = new ConexionMySQLLocal();
//        Connection cn = mysql.Conectar();
//        String nombre, direccion, mail, observaciones;
//        int telefono;
//        nombre = txtNombre.getText();
//        direccion = txtDireccion.getText();
//        if (!txtTelefono.getText().equals("")) {
//            telefono = Integer.valueOf(txtTelefono.getText());
//        } else {
//            telefono = 0;
//        }
//        mail = txtMail.getText();
//        observaciones = txtObservaciones.getText();
//        try {
//            String sSQL3 = "UPDATE derivaciones SET nombre=?, direccion=?, telefono=?, mail=?, observaciones=? WHERE id_derivaciones=" + tablaDerivaciones.getValueAt(tablaDerivaciones.getSelectedRow(), 0).toString();
//            PreparedStatement pst = cn.prepareStatement(sSQL3);
//            pst.setString(1, nombre);
//            pst.setString(2, direccion);
//            pst.setInt(3, telefono);
//            pst.setString(4, mail);
//            pst.setString(5, observaciones);
//            pst.executeUpdate();
//            txtNombre.setText("");
//            txtDireccion.setText("");
//            txtTelefono.setText("");
//            txtMail.setText("");
//            txtObservaciones.setText("");
//            txtNombre.requestFocus();
//            cargartabla("");
//            btnModificar.setEnabled(false);
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, ex);
//            JOptionPane.showMessageDialog(null, "Error en la base de datos...");
//        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void txtTelefonoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyReleased

        if (!isNumeric(txtTelefono.getText())) {
            txtTelefono.setText("");
        }

    }//GEN-LAST:event_txtTelefonoKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private RSMaterialComponent.RSButtonMaterialIconOne btnAgregar;
    private RSMaterialComponent.RSButtonMaterialIconOne btnModificar;
    private RSMaterialComponent.RSButtonMaterialIconOne btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private RSMaterialComponent.RSButtonIconOne rSButtonIconOne1;
    private RSMaterialComponent.RSTableMetroCustom tablaDerivaciones;
    private RSMaterialComponent.RSTextFieldMaterial txtDireccion;
    private RSMaterialComponent.RSTextFieldMaterial txtMail;
    private RSMaterialComponent.RSTextFieldMaterial txtNombre;
    private RSMaterialComponent.RSTextFieldMaterial txtObservaciones;
    private RSMaterialComponent.RSTextFieldMaterial txtTelefono;
    // End of variables declaration//GEN-END:variables
}
