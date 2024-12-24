package com.ybc.ybioq.view;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static com.ybc.ybioq.config.Escape.funcionescape;

public class AltaMedico extends javax.swing.JDialog {

    int x;
    int y;
    DefaultTableModel model;
    DefaultTableCellRenderer alinearCentro, alinearDerecha, alinearIzquierda;
    public int idespecialidad[] = new int[100];
    public int bmodificar = 0;

    public AltaMedico(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        funcionescape(this);
        cargartabla("");
        eventotabla();
        cargarcombo();
        dobleclick();
    }

    void cargarcombo() {
//        cboEspecialidad.removeAllItems();
//        cboEspecialidad.addItem("...");
//        int i = 0;
//        String sql = "SELECT id_especialidades, nombre_esp FROM especialidades order by nombre_esp ";
//
//        while (rs.next()) {
//            cboEspecialidad.addItem(rs.getString("nombre_esp"));
//            idespecialidad[i] = rs.getInt("id_especialidades");
//            i++;
//        }
    }

    void dobleclick() {
        tablaMedicos.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    txtNombre.setText(tablaMedicos.getValueAt(tablaMedicos.getSelectedRow(), 2).toString());
                    txtApellido.setText(tablaMedicos.getValueAt(tablaMedicos.getSelectedRow(), 1).toString());
                    txtMatricula.setText(tablaMedicos.getValueAt(tablaMedicos.getSelectedRow(), 3).toString());
                    cboEspecialidad.setSelectedItem(tablaMedicos.getValueAt(tablaMedicos.getSelectedRow(), 4).toString());
                    if (tablaMedicos.getValueAt(tablaMedicos.getSelectedRow(), 5) != null) {
                        txtObservaciones.setText(tablaMedicos.getValueAt(tablaMedicos.getSelectedRow(), 5).toString());
                    }
                    if (tablaMedicos.getValueAt(tablaMedicos.getSelectedRow(), 6).equals("OK")) {
                        bmodificar = 1;
                    } else {
                        JOptionPane.showMessageDialog(null, "El Medico se encuentra dado de Baja");
                        bmodificar = 0;
                    }

                }
            }
        });
    }

    void cargartabla(String valor) {
//        String[] Titulo = {"id_medicos", "Apellido", "Nombre", "Matricula", "Especialidad", "Obs", "Estado"};
//        String[] Registros = new String[7];
//        String sql = "SELECT id_medicos, nombre, apellido, medicos.matricula, medicos.observaciones, nombre_esp, medicos.estado FROM medicos INNER JOIN medicos_tienen_especialidades USING (id_medicos) INNER JOIN especialidades USING (id_especialidades) WHERE concat(apellido,'', nombre,'',medicos.matricula) LIKE '%" + valor + "%'";
//
//        int i = 0;
//        model = new DefaultTableModel(null, Titulo) {
//            ////Celdas no editables////////
//            public boolean isCellEditable(int row, int column) {
//                return false;
//            }
//        };
//        while (rs.next()) {
//            Registros[0] = rs.getString("id_medicos");
//            Registros[1] = rs.getString("apellido");
//            Registros[2] = rs.getString("nombre");
//            Registros[3] = rs.getString("medicos.matricula");
//            Registros[4] = rs.getString("nombre_esp");
//            if (rs.getString("medicos.observaciones") != null) {
//                Registros[5] = rs.getString("medicos.observaciones");
//            }
//            if (rs.getString("medicos.estado").equals("1")) {
//                Registros[6] = "OK";
//            } else {
//                Registros[6] = "BAJA";
//            }
//
//            model.addRow(Registros);
//        }
//        tablaMedicos.setModel(model);
//        tablaMedicos.setAutoCreateRowSorter(true);
//        tablaMedicos.getColumnModel().getColumn(0).setMaxWidth(0);
//        tablaMedicos.getColumnModel().getColumn(0).setMinWidth(0);
//        tablaMedicos.getColumnModel().getColumn(0).setPreferredWidth(0);
//        alinear();
//        tablaMedicos.getColumnModel().getColumn(0).setCellRenderer(alinearCentro);
//        tablaMedicos.getColumnModel().getColumn(1).setCellRenderer(alinearIzquierda);
//        tablaMedicos.getColumnModel().getColumn(2).setCellRenderer(alinearIzquierda);
//        tablaMedicos.getColumnModel().getColumn(3).setCellRenderer(alinearCentro);
//        tablaMedicos.getColumnModel().getColumn(4).setCellRenderer(alinearCentro);
//        tablaMedicos.getColumnModel().getColumn(5).setCellRenderer(alinearCentro);
//        tablaMedicos.getColumnModel().getColumn(6).setCellRenderer(alinearCentro);

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
        tablaMedicos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        ListSelectionModel rowSM = tablaMedicos.getSelectionModel();

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
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        txtApellido = new RSMaterialComponent.RSTextFieldMaterial();
        txtNombre = new RSMaterialComponent.RSTextFieldMaterial();
        txtmail = new RSMaterialComponent.RSTextFieldMaterial();
        txtTelefono = new RSMaterialComponent.RSTextFieldMaterial();
        txtMatricula = new RSMaterialComponent.RSTextFieldMaterial();
        txtObservaciones = new RSMaterialComponent.RSTextFieldMaterial();
        cboEspecialidad = new RSMaterialComponent.RSComboBox();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaMedicos = new RSMaterialComponent.RSTableMetroCustom();
        jPanel4 = new javax.swing.JPanel();
        btnAgregar = new RSMaterialComponent.RSButtonMaterialIconOne();
        btnBorrar = new RSMaterialComponent.RSButtonMaterialIconOne();
        btnSalir = new RSMaterialComponent.RSButtonMaterialIconOne();
        jPanel1 = new javax.swing.JPanel();
        rSButtonIconOne1 = new RSMaterialComponent.RSButtonIconOne();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnAgregarEspecialidad = new RSMaterialComponent.RSButtonMaterialIconOne();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(255, 255, 255), new java.awt.Color(0, 90, 132)));

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

        txtmail.setForeground(new java.awt.Color(0, 90, 132));
        txtmail.setColorMaterial(new java.awt.Color(0, 90, 132));
        txtmail.setPhColor(new java.awt.Color(0, 90, 132));
        txtmail.setPlaceholder("Mail");
        txtmail.setSelectionColor(new java.awt.Color(0, 90, 132));

        txtTelefono.setForeground(new java.awt.Color(0, 90, 132));
        txtTelefono.setColorMaterial(new java.awt.Color(0, 90, 132));
        txtTelefono.setPhColor(new java.awt.Color(0, 90, 132));
        txtTelefono.setPlaceholder("Teléfono");
        txtTelefono.setSelectionColor(new java.awt.Color(0, 90, 132));

        txtMatricula.setForeground(new java.awt.Color(0, 90, 132));
        txtMatricula.setColorMaterial(new java.awt.Color(0, 90, 132));
        txtMatricula.setPhColor(new java.awt.Color(0, 90, 132));
        txtMatricula.setPlaceholder("Matrícula");
        txtMatricula.setSelectionColor(new java.awt.Color(0, 90, 132));

        txtObservaciones.setForeground(new java.awt.Color(0, 90, 132));
        txtObservaciones.setColorMaterial(new java.awt.Color(0, 90, 132));
        txtObservaciones.setPhColor(new java.awt.Color(0, 90, 132));
        txtObservaciones.setPlaceholder("Observación");
        txtObservaciones.setSelectionColor(new java.awt.Color(0, 90, 132));

        cboEspecialidad.setForeground(new java.awt.Color(0, 90, 132));
        cboEspecialidad.setColorArrow(new java.awt.Color(0, 90, 132));
        cboEspecialidad.setColorBorde(new java.awt.Color(255, 255, 255));
        cboEspecialidad.setColorBoton(new java.awt.Color(255, 255, 255));
        cboEspecialidad.setColorDisabledIndex(new java.awt.Color(0, 90, 132));
        cboEspecialidad.setColorDisabledIndexText(new java.awt.Color(0, 90, 132));
        cboEspecialidad.setColorFondo(new java.awt.Color(255, 255, 255));
        cboEspecialidad.setColorListaItemsTXT(new java.awt.Color(0, 90, 132));
        cboEspecialidad.setColorSeleccion(new java.awt.Color(0, 90, 132));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtObservaciones, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                                        .addComponent(txtMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                        .addComponent(txtmail, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                        .addComponent(txtApellido, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                        .addComponent(cboEspecialidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addContainerGap(14, Short.MAX_VALUE)
                                .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtmail, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cboEspecialidad, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(txtObservaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        tablaMedicos.setForeground(new java.awt.Color(255, 255, 255));
        tablaMedicos.setModel(new DefaultTableModel(
                new Object[][]{

                },
                new String[]{

                }
        ));
        tablaMedicos.setBackgoundHead(new java.awt.Color(0, 90, 132));
        tablaMedicos.setBackgoundHover(new java.awt.Color(0, 90, 132));
        tablaMedicos.setColorBorderHead(new java.awt.Color(255, 255, 255));
        tablaMedicos.setColorBorderRows(new java.awt.Color(255, 255, 255));
        tablaMedicos.setGridColor(new java.awt.Color(204, 204, 204));
        jScrollPane1.setViewportView(tablaMedicos);

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
                                .addComponent(jScrollPane1)
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

        btnBorrar.setBackground(new java.awt.Color(0, 90, 132));
        btnBorrar.setText("Borrar");
        btnBorrar.setBackgroundHover(new java.awt.Color(204, 204, 204));
        btnBorrar.setForegroundIcon(new java.awt.Color(0, 90, 132));
        btnBorrar.setForegroundIconHover(new java.awt.Color(0, 90, 132));
        btnBorrar.setHorizontalAlignment(SwingConstants.CENTER);
        btnBorrar.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.DELETE);
        btnBorrar.setRound(20);
        btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        jLabel1.setText("Alta médico");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addContainerGap(17, Short.MAX_VALUE))
        );

        btnAgregarEspecialidad.setBackground(new java.awt.Color(0, 90, 132));
        btnAgregarEspecialidad.setText("Agregar especialidad");
        btnAgregarEspecialidad.setBackgroundHover(new java.awt.Color(204, 204, 204));
        btnAgregarEspecialidad.setForegroundIcon(new java.awt.Color(0, 90, 132));
        btnAgregarEspecialidad.setForegroundIconHover(new java.awt.Color(0, 90, 132));
        btnAgregarEspecialidad.setHorizontalAlignment(SwingConstants.CENTER);
        btnAgregarEspecialidad.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SIM_CARD);
        btnAgregarEspecialidad.setRound(20);
        btnAgregarEspecialidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarEspecialidadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btnAgregarEspecialidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                                                .addGap(18, 18, 18)
                                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnAgregarEspecialidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
    }// </editor-fold>


    private void rSButtonIconOne1ActionPerformed(java.awt.event.ActionEvent evt) {

        dispose();
    }

    private void jPanel1MousePressed(MouseEvent evt) {

        x = evt.getX();
        y = evt.getY();

    }

    private void jPanel1MouseDragged(MouseEvent evt) {

        this.setLocation(this.getLocation().x + evt.getX() - x, this.getLocation().y + evt.getY() - y);

    }

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {
//
//        String nombre, apellido, matricula, id_especialidad, observaciones, mail;
//        String sSQL = "";
//        nombre = txtNombre.getText();
//        apellido = txtApellido.getText();
//        matricula = txtMatricula.getText();
//        mail = txtmail.getText();
//        observaciones = txtObservaciones.getText();
//
//        int control = 0;
//
//        if (!"".equals(nombre) && !"".equals(apellido) && !"".equals(matricula) && !"...".equals(cboEspecialidad.getSelectedItem().toString())) {
//
//            if (bmodificar == 0) {
//                id_especialidad = String.valueOf(idespecialidad[cboEspecialidad.getSelectedIndex() - 1]);
//                String sql = "SELECT matricula FROM medicos where estado=1";
//                int contador = 0;
//                if (rs.next()) {
//                    contador++;
//                }
//                rs.beforeFirst();
//                if (contador != 0) {
//                    while (rs.next()) {
//                        if (matricula.equals(rs.getString("matricula"))) {
//                            JOptionPane.showMessageDialog(null, "La matricula ya existe");
//                            txtMatricula.requestFocus();
//                            txtMatricula.selectAll();
//                            cboEspecialidad.setSelectedIndex(0);
//                            control = 1;
//                            break;
//
//                        }
//                    }
//                }
//                rs.beforeFirst();
//                if (control == 0) {
//                    sSQL = "INSERT INTO medicos(nombre, apellido, matricula, observaciones, mail) VALUES (?,?,?,?,?)";
//                    PreparedStatement pst = cn.prepareStatement(sSQL);
//                    pst.setString(1, nombre);
//                    pst.setString(2, apellido);
//                    pst.setString(3, matricula);
//                    pst.setString(4, observaciones);
//                    pst.setString(5, mail);
//                    int n = pst.executeUpdate();
//                    if (n > 0) {
//                        cargartabla("");
//                        sSQL = "SELECT id_medicos FROM medicos WHERE matricula=" + matricula;
//                        Statement stt = cn.createStatement();
//                        ResultSet rss = stt.executeQuery(sSQL);
//                        int id_medico = 0;
//                        while (rss.next()) {
//                            id_medico = rss.getInt("id_medicos");
//                        }
//                        sSQL = "INSERT INTO medicos_tienen_especialidades  (id_medicos, id_especialidades) VALUES (?,?)";
//                        PreparedStatement pst1 = cn.prepareStatement(sSQL);
//                        pst1.setInt(1, id_medico);
//                        pst1.setString(2, id_especialidad);
//                        int m = pst1.executeUpdate();
//                        if (m > 0) {
//                            txtNombre.setText("");
//                            txtApellido.setText("");
//                            txtMatricula.setText("");
//                            txtObservaciones.setText("");
//                            txtmail.setText("");
//                            cboEspecialidad.setSelectedIndex(0);
//                            txtNombre.requestFocus();
//                            cargartabla("");
//                        }
//                    }
//                }
//            } else {
//
//                id_especialidad = String.valueOf(idespecialidad[cboEspecialidad.getSelectedIndex() - 1]);
//
//                String sSQL3 = "UPDATE medicos SET nombre=?, apellido=?, matricula=?, mail=?, observaciones=? WHERE id_medicos=" + tablaMedicos.getValueAt(tablaMedicos.getSelectedRow(), 0).toString();
//
//                pst.setString(1, nombre);
//                pst.setString(2, apellido);
//                pst.setString(3, matricula);
//                pst.setString(4, mail);
//                pst.setString(5, observaciones);
//                int n = pst.executeUpdate();
//                if (n > 0) {
//                    String sSQL4 = "UPDATE medicos_tienen_especialidades SET id_especialidades=? WHERE id_medicos=" + tablaMedicos.getValueAt(tablaMedicos.getSelectedRow(), 0).toString();
//                    PreparedStatement pst2 = cn.prepareStatement(sSQL4);
//                    pst2.setString(1, id_especialidad);
//                    int n2 = pst2.executeUpdate();
//                    if (n2 > 0) {
//                        txtNombre.setText("");
//                        txtApellido.setText("");
//                        txtMatricula.setText("");
//                        txtObservaciones.setText("");
//                        cboEspecialidad.setSelectedIndex(0);
//                        txtNombre.requestFocus();
//                        txtMatricula.setText("");
//                        txtmail.setText("");
//                        cargartabla("");
//                    }
//                }
//            }
//        } else {
//            JOptionPane.showMessageDialog(null, "Debe completar los campos obligatorios");
//        }

    }

    private void btnAgregarEspecialidadActionPerformed(java.awt.event.ActionEvent evt) {
        new AltaEspecialidad(null, true).setVisible(true);
        cargarcombo();

    }

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {

        dispose();

    }

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {

//        if (tablaMedicos.getSelectedRow() == -1) {
//            JOptionPane.showMessageDialog(null, "No seleccionó ninguna fila...");
//        } else {
//            if (!tablaMedicos.getValueAt(tablaMedicos.getSelectedRow(), 6).equals("OK")) {
//                JOptionPane.showMessageDialog(null, "El Medico ya se encuentra dado de Baja");
//            } else {
//                int opt = JOptionPane.showConfirmDialog(this, "Desea borrar al Medico?", "Confirmación", JOptionPane.YES_NO_OPTION);
//                if (opt == 0) {
//                    int id_medicos = Integer.valueOf(tablaMedicos.getValueAt(tablaMedicos.getSelectedRow(), 0).toString());
//                    String actualizar = "UPDATE medicos SET estado=? WHERE id_medicos= " + id_medicos;
//                    PreparedStatement pst = cn.prepareStatement(actualizar);
//                    pst.setInt(1, 0);
//                    int p = pst.executeUpdate();
//
//                    cargartabla("");
//                }
//            }
//        }
    }

    private RSMaterialComponent.RSButtonMaterialIconOne btnAgregar;
    private RSMaterialComponent.RSButtonMaterialIconOne btnAgregarEspecialidad;
    private RSMaterialComponent.RSButtonMaterialIconOne btnBorrar;
    private RSMaterialComponent.RSButtonMaterialIconOne btnSalir;
    private RSMaterialComponent.RSComboBox cboEspecialidad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private RSMaterialComponent.RSButtonIconOne rSButtonIconOne1;
    private RSMaterialComponent.RSTableMetroCustom tablaMedicos;
    private RSMaterialComponent.RSTextFieldMaterial txtApellido;
    private RSMaterialComponent.RSTextFieldMaterial txtMatricula;
    private RSMaterialComponent.RSTextFieldMaterial txtNombre;
    private RSMaterialComponent.RSTextFieldMaterial txtObservaciones;
    private RSMaterialComponent.RSTextFieldMaterial txtTelefono;
    private RSMaterialComponent.RSTextFieldMaterial txtmail;

}