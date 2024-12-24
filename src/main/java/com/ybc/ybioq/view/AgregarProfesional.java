package com.ybc.ybioq.view;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static com.ybc.ybioq.config.Escape.funcionescape;

public class AgregarProfesional extends javax.swing.JDialog {

    private int x;
    private int y;
    DefaultTableModel model;
    int id_bioquimico;
    public int bmodificar = 0;
    DefaultTableCellRenderer alinearCentro, alinearDerecha, alinearIzquierda;

    public AgregarProfesional(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        funcionescape(this);
        cargartabla("");
        dobleclick();
        btnAceptar.setText("Aplicar");
    }

    private void limpiarDatos() {

        txtApellido.setText(null);
        txtCelular.setText(null);
        txtClaveMail.setText(null);
        txtDni.setText(null);
        txtDireccion.setText(null);
        txtMatricula.setText(null);
        txtNombre.setText(null);
        txtTelefono.setText(null);
        txtMail.setText(null);
        btnAceptar.setText("Aplicar");

    }

    void dobleclick() {
        tablaProfesionales.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    id_bioquimico = Integer.valueOf(tablaProfesionales.getValueAt(tablaProfesionales.getSelectedRow(), 0).toString());
                    txtNombre.setText(tablaProfesionales.getValueAt(tablaProfesionales.getSelectedRow(), 2).toString());
                    txtApellido.setText(tablaProfesionales.getValueAt(tablaProfesionales.getSelectedRow(), 1).toString());
                    txtMatricula.setText(tablaProfesionales.getValueAt(tablaProfesionales.getSelectedRow(), 3).toString());
                    txtDireccion.setText(tablaProfesionales.getValueAt(tablaProfesionales.getSelectedRow(), 4).toString());
                    txtTelefono.setText(tablaProfesionales.getValueAt(tablaProfesionales.getSelectedRow(), 5).toString());
                    txtCuil.setText(tablaProfesionales.getValueAt(tablaProfesionales.getSelectedRow(), 6).toString());
                    txtCelular.setText(tablaProfesionales.getValueAt(tablaProfesionales.getSelectedRow(), 7).toString());
                    txtDni.setText(tablaProfesionales.getValueAt(tablaProfesionales.getSelectedRow(), 8).toString());
                    txtUsuario.setText(tablaProfesionales.getValueAt(tablaProfesionales.getSelectedRow(), 9).toString());
                    txtClaveUsuario.setText(tablaProfesionales.getValueAt(tablaProfesionales.getSelectedRow(), 10).toString());
                    txtMail.setText(tablaProfesionales.getValueAt(tablaProfesionales.getSelectedRow(), 11).toString());
                    txtClaveMail.setText(tablaProfesionales.getValueAt(tablaProfesionales.getSelectedRow(), 12).toString());
                    bmodificar = 1;
                }
            }
        });
    }

    void cargartabla(String valor) {
//        String[] titulo = {"id_laboratorios", "Apellido", "Nombre", "Matricula", "", "", "", "", "", "", "", "", ""};
//        String[] registros = new String[13];
//        String sql = "SELECT id_laboratorios, nombre, apellido, matricula, direccion, telefono, cuit, celular, dni, usuario, clave, mail_direccion, mail_clave FROM laboratorios WHERE concat(apellido,'', nombre,'',matricula) LIKE '%" + valor + "%'";
//
//        model = new DefaultTableModel(null, titulo) {
//            @Override
//            public boolean isCellEditable(int row, int column) {
//                return false;
//            }
//        };
//        ResultSet rs = st.executeQuery(sql);
//        while (rs.next()) {
//            registros[0] = rs.getString("id_laboratorios");
//            registros[1] = rs.getString("apellido");
//            registros[2] = rs.getString("nombre");
//            registros[3] = rs.getString("matricula");
//            if (rs.getString("direccion") != null) {
//                registros[4] = rs.getString("direccion");
//            } else {
//                registros[4] = "";
//            }
//            if (rs.getString("telefono") != null) {
//                registros[5] = rs.getString("telefono");
//            } else {
//                registros[5] = "";
//            }
//            if (rs.getString("cuit") != null) {
//                registros[6] = rs.getString("cuit");
//            } else {
//                registros[6] = "";
//            }
//            if (rs.getString("celular") != null) {
//                registros[7] = rs.getString("celular");
//            } else {
//                registros[7] = "";
//            }
//            if (rs.getString("dni") != null) {
//                registros[8] = rs.getString("dni");
//            } else {
//                registros[8] = "";
//            }
//            registros[9] = rs.getString("usuario");
//            registros[10] = rs.getString("clave");
//            registros[11] = rs.getString("mail_direccion");
//            registros[12] = rs.getString("mail_clave");
//            model.addRow(registros);
//        }
//        tablaProfesionales.setModel(model);
//        tablaProfesionales.setAutoCreateRowSorter(true);
//        tablaProfesionales.getColumnModel().getColumn(0).setMaxWidth(0);
//        tablaProfesionales.getColumnModel().getColumn(0).setMinWidth(0);
//        tablaProfesionales.getColumnModel().getColumn(0).setPreferredWidth(0);
//        tablaProfesionales.getColumnModel().getColumn(4).setMaxWidth(0);
//        tablaProfesionales.getColumnModel().getColumn(4).setMinWidth(0);
//        tablaProfesionales.getColumnModel().getColumn(4).setPreferredWidth(0);
//        tablaProfesionales.getColumnModel().getColumn(5).setMaxWidth(0);
//        tablaProfesionales.getColumnModel().getColumn(5).setMinWidth(0);
//        tablaProfesionales.getColumnModel().getColumn(5).setPreferredWidth(0);
//        tablaProfesionales.getColumnModel().getColumn(6).setMaxWidth(0);
//        tablaProfesionales.getColumnModel().getColumn(6).setMinWidth(0);
//        tablaProfesionales.getColumnModel().getColumn(6).setPreferredWidth(0);
//        tablaProfesionales.getColumnModel().getColumn(7).setMaxWidth(0);
//        tablaProfesionales.getColumnModel().getColumn(7).setMinWidth(0);
//        tablaProfesionales.getColumnModel().getColumn(7).setPreferredWidth(0);
//        tablaProfesionales.getColumnModel().getColumn(8).setMaxWidth(0);
//        tablaProfesionales.getColumnModel().getColumn(8).setMinWidth(0);
//        tablaProfesionales.getColumnModel().getColumn(8).setPreferredWidth(0);
//        tablaProfesionales.getColumnModel().getColumn(9).setMaxWidth(0);
//        tablaProfesionales.getColumnModel().getColumn(9).setMinWidth(0);
//        tablaProfesionales.getColumnModel().getColumn(9).setPreferredWidth(0);
//        tablaProfesionales.getColumnModel().getColumn(10).setMaxWidth(0);
//        tablaProfesionales.getColumnModel().getColumn(10).setMinWidth(0);
//        tablaProfesionales.getColumnModel().getColumn(10).setPreferredWidth(0);
//        tablaProfesionales.getColumnModel().getColumn(11).setMaxWidth(0);
//        tablaProfesionales.getColumnModel().getColumn(11).setMinWidth(0);
//        tablaProfesionales.getColumnModel().getColumn(11).setPreferredWidth(0);
//        tablaProfesionales.getColumnModel().getColumn(12).setMaxWidth(0);
//        tablaProfesionales.getColumnModel().getColumn(12).setMinWidth(0);
//        tablaProfesionales.getColumnModel().getColumn(12).setPreferredWidth(0);
//        alinear();
//        tablaProfesionales.getColumnModel().getColumn(1).setCellRenderer(alinearCentro);
//        tablaProfesionales.getColumnModel().getColumn(2).setCellRenderer(alinearCentro);
//        tablaProfesionales.getColumnModel().getColumn(3).setCellRenderer(alinearCentro);
//
    }

    void alinear() {
        alinearCentro = new DefaultTableCellRenderer();
        alinearCentro.setHorizontalAlignment(SwingConstants.CENTER);
        alinearDerecha = new DefaultTableCellRenderer();
        alinearDerecha.setHorizontalAlignment(SwingConstants.RIGHT);
        alinearIzquierda = new DefaultTableCellRenderer();
        alinearIzquierda.setHorizontalAlignment(SwingConstants.LEFT);
    }


    private void aplicar() {
//        String sSQL;
//        int control = 0;
//
//        String sql = "SELECT matricula FROM laboratorios";
//        ResultSet rs = st.executeQuery(sql);
//        int contador = 0;
//        while (rs.next()) {
//            contador++;
//        }
//        rs.beforeFirst();
//        if (contador != 0) {
//            while (rs.next()) {
//
//                if (txtMatricula.getText().equals(rs.getString("matricula"))) {
//
//                    JOptionPane.showMessageDialog(null, "La matrícula ya existe");
//                    txtMatricula.requestFocus();
//                    txtMatricula.selectAll();
//
//                    control = 1;
//                    break;
//                }
//            }
//        }
//        rs.beforeFirst();
//        if (control == 0) {
//            sSQL = "INSERT INTO laboratorios  (nombre, apellido, matricula, direccion, telefono, cuit, celular, dni,usuario, clave, mail_direccion, mail_clave) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
//            PreparedStatement pst = cn.prepareStatement(sSQL);
//            pst.setString(1, txtNombre.getText());
//            pst.setString(2, txtApellido.getText());
//            pst.setString(3, txtMatricula.getText());
//            pst.setString(4, txtDireccion.getText());
//            pst.setString(5, txtTelefono.getText());
//            pst.setString(6, txtCuil.getText());
//            pst.setString(7, txtCelular.getText());
//            pst.setString(8, txtDni.getText());
//            pst.setString(9, txtUsuario.getText());
//            pst.setString(10, txtClaveUsuario.getText());
//            pst.setString(11, txtMail.getText());
//            pst.setString(12, txtClaveMail.getText());
//            int n = pst.executeUpdate();
//            if (n > 0) {
//                JOptionPane.showMessageDialog(null, "Bioquímico cargado con éxito...");
//                cargartabla("");
//            }
//        }
    }

    private void modificar() {
//        String sSQL3 = "UPDATE laboratorios SET nombre=?, apellido=?, matricula=?, direccion=?, telefono=?, cuit=?, celular=?, dni=?,usuario=?, clave=?, mail_direccion=?, mail_clave=? WHERE id_laboratorios=" + tablaProfesionales.getValueAt(tablaProfesionales.getSelectedRow(), 0).toString();
//        PreparedStatement pst = cn.prepareStatement(sSQL3);
//        pst.setString(1, txtNombre.getText());
//        pst.setString(2, txtApellido.getText());
//        pst.setString(3, txtMatricula.getText());
//        pst.setString(4, txtDireccion.getText());
//        pst.setString(5, txtTelefono.getText());
//        pst.setString(6, txtCuil.getText());
//        pst.setString(7, txtCelular.getText());
//        pst.setString(8, txtDni.getText());
//        pst.setString(9, txtUsuario.getText());
//        pst.setString(10, txtClaveUsuario.getText());
//        pst.setString(11, txtMail.getText());
//        pst.setString(12, txtClaveMail.getText());
//        int n = pst.executeUpdate();
//        if (n > 0) {
//            JOptionPane.showMessageDialog(null, "Bioquímico modificado con éxito...");
//        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPrincipal = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        txtApellido = new RSMaterialComponent.RSTextFieldMaterial();
        txtNombre = new RSMaterialComponent.RSTextFieldMaterial();
        txtDni = new RSMaterialComponent.RSTextFieldMaterial();
        txtTelefono = new RSMaterialComponent.RSTextFieldMaterial();
        txtCelular = new RSMaterialComponent.RSTextFieldMaterial();
        txtCuil = new RSMaterialComponent.RSTextFieldMaterial();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaProfesionales = new RSMaterialComponent.RSTableMetroCustom();
        jPanel4 = new javax.swing.JPanel();
        btnAceptar = new RSMaterialComponent.RSButtonMaterialIconOne();
        btnCancelar = new RSMaterialComponent.RSButtonMaterialIconOne();
        jPanel6 = new javax.swing.JPanel();
        txtMail = new RSMaterialComponent.RSTextFieldMaterial();
        txtMatricula = new RSMaterialComponent.RSTextFieldMaterial();
        txtClaveMail = new RSMaterialComponent.RSPasswordMaterial();
        txtUsuario = new RSMaterialComponent.RSTextFieldMaterial();
        txtDireccion = new RSMaterialComponent.RSTextFieldMaterial();
        txtClaveUsuario = new RSMaterialComponent.RSPasswordMaterial();
        jPanel1 = new javax.swing.JPanel();
        rSButtonIconOne1 = new RSMaterialComponent.RSButtonIconOne();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        panelPrincipal.setBackground(new java.awt.Color(255, 255, 255));
        panelPrincipal.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(255, 255, 255), new java.awt.Color(0, 90, 132)));
        panelPrincipal.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(MouseEvent evt) {
                panelPrincipalMouseDragged(evt);
            }
        });
        panelPrincipal.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                panelPrincipalMousePressed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        txtApellido.setForeground(new java.awt.Color(0, 90, 132));
        txtApellido.setColorMaterial(new java.awt.Color(0, 90, 132));
        txtApellido.setPhColor(new java.awt.Color(0, 90, 132));
        txtApellido.setPlaceholder("Apellido");
        txtApellido.setSelectionColor(new java.awt.Color(0, 90, 132));

        txtNombre.setForeground(new java.awt.Color(0, 90, 132));
        txtNombre.setColorMaterial(new java.awt.Color(0, 90, 132));
        txtNombre.setPhColor(new java.awt.Color(0, 90, 132));
        txtNombre.setPlaceholder("Nombre");
        txtNombre.setSelectionColor(new java.awt.Color(0, 90, 132));

        txtDni.setForeground(new java.awt.Color(0, 90, 132));
        txtDni.setColorMaterial(new java.awt.Color(0, 90, 132));
        txtDni.setPhColor(new java.awt.Color(0, 90, 132));
        txtDni.setPlaceholder("DNI");
        txtDni.setSelectionColor(new java.awt.Color(0, 90, 132));

        txtTelefono.setForeground(new java.awt.Color(0, 90, 132));
        txtTelefono.setColorMaterial(new java.awt.Color(0, 90, 132));
        txtTelefono.setPhColor(new java.awt.Color(0, 90, 132));
        txtTelefono.setPlaceholder("Teléfono");
        txtTelefono.setSelectionColor(new java.awt.Color(0, 90, 132));

        txtCelular.setForeground(new java.awt.Color(0, 90, 132));
        txtCelular.setColorMaterial(new java.awt.Color(0, 90, 132));
        txtCelular.setPhColor(new java.awt.Color(0, 90, 132));
        txtCelular.setPlaceholder("Celular");
        txtCelular.setSelectionColor(new java.awt.Color(0, 90, 132));

        txtCuil.setForeground(new java.awt.Color(0, 90, 132));
        txtCuil.setColorMaterial(new java.awt.Color(0, 90, 132));
        txtCuil.setPhColor(new java.awt.Color(0, 90, 132));
        txtCuil.setPlaceholder("CUIL");
        txtCuil.setSelectionColor(new java.awt.Color(0, 90, 132));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                                                        .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                                        .addComponent(txtApellido, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                        .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                        .addComponent(txtCuil, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtCuil, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        tablaProfesionales.setForeground(new java.awt.Color(255, 255, 255));
        tablaProfesionales.setModel(new DefaultTableModel(
                new Object[][]{

                },
                new String[]{

                }
        ));
        tablaProfesionales.setBackgoundHead(new java.awt.Color(0, 90, 132));
        tablaProfesionales.setBackgoundHover(new java.awt.Color(0, 90, 132));
        tablaProfesionales.setColorBorderHead(new java.awt.Color(255, 255, 255));
        tablaProfesionales.setColorBorderRows(new java.awt.Color(255, 255, 255));
        tablaProfesionales.setGridColor(new java.awt.Color(204, 204, 204));
        tablaProfesionales.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                tablaProfesionalesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaProfesionales);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1)
                                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        btnAceptar.setBackground(new java.awt.Color(0, 90, 132));
        btnAceptar.setText("Aceptar");
        btnAceptar.setBackgroundHover(new java.awt.Color(204, 204, 204));
        btnAceptar.setForegroundIcon(new java.awt.Color(0, 90, 132));
        btnAceptar.setForegroundIconHover(new java.awt.Color(0, 90, 132));
        btnAceptar.setHorizontalAlignment(SwingConstants.CENTER);
        btnAceptar.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.CHECK);
        btnAceptar.setRound(20);
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        btnCancelar.setBackground(new java.awt.Color(0, 90, 132));
        btnCancelar.setText("Cancelar");
        btnCancelar.setBackgroundHover(new java.awt.Color(204, 204, 204));
        btnCancelar.setForegroundIcon(new java.awt.Color(0, 90, 132));
        btnCancelar.setForegroundIconHover(new java.awt.Color(0, 90, 132));
        btnCancelar.setHorizontalAlignment(SwingConstants.CENTER);
        btnCancelar.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.CLEAR);
        btnCancelar.setRound(20);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        txtMail.setForeground(new java.awt.Color(0, 90, 132));
        txtMail.setColorMaterial(new java.awt.Color(0, 90, 132));
        txtMail.setPhColor(new java.awt.Color(0, 90, 132));
        txtMail.setPlaceholder("Mail");
        txtMail.setSelectionColor(new java.awt.Color(0, 90, 132));

        txtMatricula.setForeground(new java.awt.Color(0, 90, 132));
        txtMatricula.setColorMaterial(new java.awt.Color(0, 90, 132));
        txtMatricula.setPhColor(new java.awt.Color(0, 90, 132));
        txtMatricula.setPlaceholder("Matrícula");
        txtMatricula.setSelectionColor(new java.awt.Color(0, 90, 132));

        txtClaveMail.setForeground(new java.awt.Color(0, 90, 132));
        txtClaveMail.setColorMaterial(new java.awt.Color(0, 90, 132));
        txtClaveMail.setPhColor(new java.awt.Color(0, 90, 132));
        txtClaveMail.setPlaceholder("Clave mail");
        txtClaveMail.setSelectionColor(new java.awt.Color(0, 90, 132));

        txtUsuario.setForeground(new java.awt.Color(0, 90, 132));
        txtUsuario.setColorMaterial(new java.awt.Color(0, 90, 132));
        txtUsuario.setPhColor(new java.awt.Color(0, 90, 132));
        txtUsuario.setPlaceholder("Usuario CBT");
        txtUsuario.setSelectionColor(new java.awt.Color(0, 90, 132));

        txtDireccion.setForeground(new java.awt.Color(0, 90, 132));
        txtDireccion.setColorMaterial(new java.awt.Color(0, 90, 132));
        txtDireccion.setPhColor(new java.awt.Color(0, 90, 132));
        txtDireccion.setPlaceholder("Dirección");
        txtDireccion.setSelectionColor(new java.awt.Color(0, 90, 132));

        txtClaveUsuario.setForeground(new java.awt.Color(0, 90, 132));
        txtClaveUsuario.setColorMaterial(new java.awt.Color(0, 90, 132));
        txtClaveUsuario.setPhColor(new java.awt.Color(0, 90, 132));
        txtClaveUsuario.setPlaceholder("Clave");
        txtClaveUsuario.setSelectionColor(new java.awt.Color(0, 90, 132));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtDireccion, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                                        .addGroup(jPanel6Layout.createSequentialGroup()
                                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(txtMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(txtMail, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(txtClaveMail, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(txtClaveUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtMail, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtClaveMail, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtClaveUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(0, 90, 132));

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(238, Short.MAX_VALUE)
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

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 90, 132));
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel1.setText("Alta Profesional");

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
                panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelPrincipalLayout.createSequentialGroup()
                                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panelPrincipalLayout.createSequentialGroup()
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPrincipalLayout.createSequentialGroup()
                                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPrincipalLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelPrincipalLayout.setVerticalGroup(
                panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelPrincipalLayout.createSequentialGroup()
                                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(panelPrincipalLayout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jLabel1)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panelPrincipal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limpiarDatos();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void panelPrincipalMousePressed(MouseEvent evt) {//GEN-FIRST:event_panelPrincipalMousePressed

        x = evt.getX();
        y = evt.getY();

    }//GEN-LAST:event_panelPrincipalMousePressed

    private void panelPrincipalMouseDragged(MouseEvent evt) {//GEN-FIRST:event_panelPrincipalMouseDragged

        this.setLocation(this.getLocation().x + evt.getX() - x, this.getLocation().y + evt.getY() - y);

    }//GEN-LAST:event_panelPrincipalMouseDragged

    private void rSButtonIconOne1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIconOne1ActionPerformed

        dispose();
    }//GEN-LAST:event_rSButtonIconOne1ActionPerformed

    private void tablaProfesionalesMouseClicked(MouseEvent evt) {//GEN-FIRST:event_tablaProfesionalesMouseClicked
        if (evt.getClickCount() == 2) {
            if (tablaProfesionales.getSelectedRow() > -1) {
                btnAceptar.setText("Modificar");
            }
        }
    }//GEN-LAST:event_tablaProfesionalesMouseClicked

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        if (!"".equals(txtNombre.getText()) && !"".equals(txtApellido.getText()) && !"".equals(txtMatricula.getText())) {
            if (bmodificar == 0) {
                aplicar();
            } else {
                modificar();
            }
            txtNombre.setText("");
            txtApellido.setText("");
            txtMatricula.setText("");
            txtCelular.setText("");
            txtCuil.setText("");
            txtDireccion.setText("");
            txtDni.setText("");
            txtTelefono.setText("");
            txtClaveUsuario.setText("");
            txtUsuario.setText("");
            txtClaveMail.setText("");
            txtMail.setText("");
            cargartabla("");
        }
    }//GEN-LAST:event_btnAceptarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private RSMaterialComponent.RSButtonMaterialIconOne btnAceptar;
    private RSMaterialComponent.RSButtonMaterialIconOne btnCancelar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelPrincipal;
    private RSMaterialComponent.RSButtonIconOne rSButtonIconOne1;
    private RSMaterialComponent.RSTableMetroCustom tablaProfesionales;
    private RSMaterialComponent.RSTextFieldMaterial txtApellido;
    private RSMaterialComponent.RSTextFieldMaterial txtCelular;
    private RSMaterialComponent.RSPasswordMaterial txtClaveMail;
    private RSMaterialComponent.RSPasswordMaterial txtClaveUsuario;
    private RSMaterialComponent.RSTextFieldMaterial txtCuil;
    private RSMaterialComponent.RSTextFieldMaterial txtDireccion;
    private RSMaterialComponent.RSTextFieldMaterial txtDni;
    private RSMaterialComponent.RSTextFieldMaterial txtMail;
    private RSMaterialComponent.RSTextFieldMaterial txtMatricula;
    private RSMaterialComponent.RSTextFieldMaterial txtNombre;
    private RSMaterialComponent.RSTextFieldMaterial txtTelefono;
    private RSMaterialComponent.RSTextFieldMaterial txtUsuario;
    // End of variables declaration//GEN-END:variables
}
