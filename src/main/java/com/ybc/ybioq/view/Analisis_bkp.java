package com.ybc.ybioq.view;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;

import static com.ybc.ybioq.config.Escape.funcionescape;

public class Analisis_bkp extends javax.swing.JDialog {

    private int x;
    private int y;

    public Analisis_bkp(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        funcionescape(this);
        panelGeneral.setVisible(true);
        panelAnalisis.setVisible(false);
        panelPrevisualizar.setVisible(false);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        panelPrincipal = new javax.swing.JPanel();
        panelCerrar = new javax.swing.JPanel();
        rSButtonIconOne1 = new RSMaterialComponent.RSButtonIconOne();
        panelPracticas = new javax.swing.JPanel();
        txtPractica = new RSMaterialComponent.RSTextFieldMaterial();
        panelTitulo = new javax.swing.JPanel();
        lblPractica = new javax.swing.JLabel();
        panelBotones = new javax.swing.JPanel();
        btnGeneral = new RSMaterialComponent.RSButtonMaterialIconOne();
        btnUtilitarios1 = new RSMaterialComponent.RSButtonMaterialIconOne();
        rSLabelIcon1 = new RSMaterialComponent.RSLabelIcon();
        btnInforme1 = new RSMaterialComponent.RSButtonMaterialIconOne();
        btnInforme2 = new RSMaterialComponent.RSButtonMaterialIconOne();
        panelContenedor = new javax.swing.JPanel();
        panelGeneral = new javax.swing.JPanel();
        txtInstrucciones = new RSMaterialComponent.RSTextFieldMaterial();
        jPanel14 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        cboTiempoProcesamiento = new RSMaterialComponent.RSComboBox();
        jLabel3 = new javax.swing.JLabel();
        cboTipo = new RSMaterialComponent.RSComboBox();
        jLabel2 = new javax.swing.JLabel();
        cboSeccion = new RSMaterialComponent.RSComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        swDerivacion = new RSMaterialComponent.RSSwitch();
        cboDerivaciones = new RSMaterialComponent.RSComboBox();
        btnDerivaciones = new RSMaterialComponent.RSButtonMaterialIconOne();
        jLabel16 = new javax.swing.JLabel();
        lblPrioridadInforme = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        arbolPracticas = new javax.swing.JTree();
        jPanel16 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tablaPioridadPractica = new RSMaterialComponent.RSTableMetroCustom();
        btnActualizarInformacion = new RSMaterialComponent.RSButtonMaterialIconOne();
        panelAnalisis = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        jPanel10 = new javax.swing.JPanel();
        chkUnSoloMetodo = new RSMaterialComponent.RSCheckBoxMaterial();
        btnTitulo = new RSMaterialComponent.RSButtonIconOne();
        txtMetodo = new RSMaterialComponent.RSTextFieldMaterial();
        txtTitulo = new RSMaterialComponent.RSTextFieldMaterial();
        txtFormatoMesada = new RSMaterialComponent.RSTextFieldMaterial();
        txtNombreInforme = new RSMaterialComponent.RSTextFieldMaterial();
        btnMetodo = new RSMaterialComponent.RSButtonIconOne();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cboSexo4 = new RSMaterialComponent.RSComboBox();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        btnEditarResultados = new RSMaterialComponent.RSButtonMaterialIconOne();
        jPanel2 = new javax.swing.JPanel();
        btnAgregar = new RSMaterialComponent.RSButtonMaterialIconOne();
        btnBorrar = new RSMaterialComponent.RSButtonMaterialIconOne();
        btnLimpiar = new RSMaterialComponent.RSButtonMaterialIconOne();
        jPanel1 = new javax.swing.JPanel();
        btnSubirPrioridad = new RSMaterialComponent.RSButtonMaterialIconOne();
        btnBajarPrioridad = new RSMaterialComponent.RSButtonMaterialIconOne();
        jLabel9 = new javax.swing.JLabel();
        panelPrevisualizar = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        panelPrincipal.setBackground(new java.awt.Color(0, 90, 132));
        panelPrincipal.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(255, 255, 255), new java.awt.Color(0, 90, 132)));
        panelPrincipal.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                panelPrincipalMouseDragged(evt);
            }
        });
        panelPrincipal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelPrincipalMousePressed(evt);
            }
        });

        panelCerrar.setBackground(new java.awt.Color(0, 90, 132));

        rSButtonIconOne1.setBackground(new java.awt.Color(255, 255, 255));
        rSButtonIconOne1.setBackgroundHover(new java.awt.Color(255, 255, 255));
        rSButtonIconOne1.setForegroundHover(new java.awt.Color(0, 90, 132));
        rSButtonIconOne1.setForegroundText(new java.awt.Color(0, 90, 132));
        rSButtonIconOne1.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.CLOSE);
        rSButtonIconOne1.setSizeIcon(25.0F);
        rSButtonIconOne1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIconOne1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelCerrarLayout = new javax.swing.GroupLayout(panelCerrar);
        panelCerrar.setLayout(panelCerrarLayout);
        panelCerrarLayout.setHorizontalGroup(
                panelCerrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCerrarLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(rSButtonIconOne1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        panelCerrarLayout.setVerticalGroup(
                panelCerrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelCerrarLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(rSButtonIconOne1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelPracticas.setBackground(new java.awt.Color(0, 90, 132));

        txtPractica.setForeground(new java.awt.Color(0, 90, 132));
        txtPractica.setColorMaterial(new java.awt.Color(0, 90, 132));
        txtPractica.setPhColor(new java.awt.Color(0, 90, 132));
        txtPractica.setPlaceholder("Práctica");
        txtPractica.setSelectionColor(new java.awt.Color(0, 90, 132));
        txtPractica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPracticaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelPracticasLayout = new javax.swing.GroupLayout(panelPracticas);
        panelPracticas.setLayout(panelPracticasLayout);
        panelPracticasLayout.setHorizontalGroup(
                panelPracticasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelPracticasLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(txtPractica, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        panelPracticasLayout.setVerticalGroup(
                panelPracticasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPracticasLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(txtPractica, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelTitulo.setBackground(new java.awt.Color(0, 90, 132));

        lblPractica.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblPractica.setForeground(new java.awt.Color(255, 255, 255));
        lblPractica.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout panelTituloLayout = new javax.swing.GroupLayout(panelTitulo);
        panelTitulo.setLayout(panelTituloLayout);
        panelTituloLayout.setHorizontalGroup(
                panelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTituloLayout.createSequentialGroup()
                                .addComponent(lblPractica, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        panelTituloLayout.setVerticalGroup(
                panelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelTituloLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblPractica, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                                .addContainerGap())
        );

        panelBotones.setBackground(new java.awt.Color(0, 90, 132));
        panelBotones.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 90, 132)));

        btnGeneral.setBackground(new java.awt.Color(255, 255, 255));
        btnGeneral.setText("General");
        btnGeneral.setBackgroundHover(new java.awt.Color(204, 204, 204));
        btnGeneral.setForegroundIcon(new java.awt.Color(0, 90, 132));
        btnGeneral.setForegroundIconHover(new java.awt.Color(0, 90, 132));
        btnGeneral.setForegroundText(new java.awt.Color(0, 90, 132));
        btnGeneral.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnGeneral.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.APPS);
        btnGeneral.setRound(20);
        btnGeneral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGeneralActionPerformed(evt);
            }
        });

        btnUtilitarios1.setBackground(new java.awt.Color(255, 255, 255));
        btnUtilitarios1.setText("Salir");
        btnUtilitarios1.setBackgroundHover(new java.awt.Color(204, 204, 204));
        btnUtilitarios1.setForegroundIcon(new java.awt.Color(0, 90, 132));
        btnUtilitarios1.setForegroundIconHover(new java.awt.Color(0, 90, 132));
        btnUtilitarios1.setForegroundText(new java.awt.Color(0, 90, 132));
        btnUtilitarios1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnUtilitarios1.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.EXIT_TO_APP);
        btnUtilitarios1.setRound(20);
        btnUtilitarios1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUtilitarios1ActionPerformed(evt);
            }
        });

        rSLabelIcon1.setForeground(new java.awt.Color(255, 255, 255));
        rSLabelIcon1.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ADD_LOCATION);
        rSLabelIcon1.setSizeIcon(120.0F);

        btnInforme1.setBackground(new java.awt.Color(255, 255, 255));
        btnInforme1.setText("Análisis");
        btnInforme1.setBackgroundHover(new java.awt.Color(204, 204, 204));
        btnInforme1.setForegroundIcon(new java.awt.Color(0, 90, 132));
        btnInforme1.setForegroundIconHover(new java.awt.Color(0, 90, 132));
        btnInforme1.setForegroundText(new java.awt.Color(0, 90, 132));
        btnInforme1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnInforme1.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ASSESSMENT);
        btnInforme1.setRound(20);
        btnInforme1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInforme1ActionPerformed(evt);
            }
        });

        btnInforme2.setBackground(new java.awt.Color(255, 255, 255));
        btnInforme2.setText("Previsualizar");
        btnInforme2.setBackgroundHover(new java.awt.Color(204, 204, 204));
        btnInforme2.setForegroundIcon(new java.awt.Color(0, 90, 132));
        btnInforme2.setForegroundIconHover(new java.awt.Color(0, 90, 132));
        btnInforme2.setForegroundText(new java.awt.Color(0, 90, 132));
        btnInforme2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnInforme2.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.REMOVE_RED_EYE);
        btnInforme2.setRound(20);
        btnInforme2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInforme2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBotonesLayout = new javax.swing.GroupLayout(panelBotones);
        panelBotones.setLayout(panelBotonesLayout);
        panelBotonesLayout.setHorizontalGroup(
                panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelBotonesLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnGeneral, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                        .addComponent(btnUtilitarios1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                        .addComponent(rSLabelIcon1, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                                        .addComponent(btnInforme1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                        .addComponent(btnInforme2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addContainerGap())
        );
        panelBotonesLayout.setVerticalGroup(
                panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelBotonesLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(rSLabelIcon1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnInforme1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnInforme2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnUtilitarios1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        panelContenedor.setLayout(new javax.swing.OverlayLayout(panelContenedor));

        panelGeneral.setBackground(new java.awt.Color(255, 255, 255));

        txtInstrucciones.setForeground(new java.awt.Color(0, 90, 132));
        txtInstrucciones.setColorMaterial(new java.awt.Color(0, 90, 132));
        txtInstrucciones.setPhColor(new java.awt.Color(0, 90, 132));
        txtInstrucciones.setPlaceholder("Instrucciones");
        txtInstrucciones.setSelectionColor(new java.awt.Color(0, 90, 132));
        txtInstrucciones.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtInstruccionesFocusLost(evt);
            }
        });
        txtInstrucciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtInstruccionesActionPerformed(evt);
            }
        });
        txtInstrucciones.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtInstruccionesKeyTyped(evt);
            }
        });

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 90, 132)));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 90, 132));
        jLabel4.setText("días");

        cboTiempoProcesamiento.setForeground(new java.awt.Color(0, 90, 132));
        cboTiempoProcesamiento.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"1", "2", "3", "4", "5", "6", "7"}));
        cboTiempoProcesamiento.setColorArrow(new java.awt.Color(0, 90, 132));
        cboTiempoProcesamiento.setColorBorde(new java.awt.Color(255, 255, 255));
        cboTiempoProcesamiento.setColorBoton(new java.awt.Color(255, 255, 255));
        cboTiempoProcesamiento.setColorDisabledIndex(new java.awt.Color(0, 90, 132));
        cboTiempoProcesamiento.setColorDisabledIndexText(new java.awt.Color(0, 90, 132));
        cboTiempoProcesamiento.setColorFondo(new java.awt.Color(255, 255, 255));
        cboTiempoProcesamiento.setColorListaItemsTXT(new java.awt.Color(0, 90, 132));
        cboTiempoProcesamiento.setColorSeleccion(new java.awt.Color(0, 90, 132));
        cboTiempoProcesamiento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cboTiempoProcesamientoKeyPressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 90, 132));
        jLabel3.setText("Tiempo de procesamiento:");

        cboTipo.setForeground(new java.awt.Color(0, 90, 132));
        cboTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Filas", "Columnas", "Proteinas", "Cultivo 1", "Cultivo 2", "Cultivo 3", "Hemograma Fijo", "Hemograma Filas"}));
        cboTipo.setColorArrow(new java.awt.Color(0, 90, 132));
        cboTipo.setColorBorde(new java.awt.Color(255, 255, 255));
        cboTipo.setColorBoton(new java.awt.Color(255, 255, 255));
        cboTipo.setColorDisabledIndex(new java.awt.Color(0, 90, 132));
        cboTipo.setColorDisabledIndexText(new java.awt.Color(0, 90, 132));
        cboTipo.setColorFondo(new java.awt.Color(255, 255, 255));
        cboTipo.setColorListaItemsTXT(new java.awt.Color(0, 90, 132));
        cboTipo.setColorSeleccion(new java.awt.Color(0, 90, 132));
        cboTipo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cboTipoKeyPressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 90, 132));
        jLabel2.setText("Tipo:");

        cboSeccion.setForeground(new java.awt.Color(0, 90, 132));
        cboSeccion.setModel(new javax.swing.DefaultComboBoxModel(new String[]{""}));
        cboSeccion.setColorArrow(new java.awt.Color(0, 90, 132));
        cboSeccion.setColorBorde(new java.awt.Color(255, 255, 255));
        cboSeccion.setColorBoton(new java.awt.Color(255, 255, 255));
        cboSeccion.setColorDisabledIndex(new java.awt.Color(0, 90, 132));
        cboSeccion.setColorDisabledIndexText(new java.awt.Color(0, 90, 132));
        cboSeccion.setColorFondo(new java.awt.Color(255, 255, 255));
        cboSeccion.setColorListaItemsTXT(new java.awt.Color(0, 90, 132));
        cboSeccion.setColorSeleccion(new java.awt.Color(0, 90, 132));
        cboSeccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cboSeccionKeyPressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 90, 132));
        jLabel1.setText("Sección:");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
                jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel14Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addGap(8, 8, 8)
                                .addComponent(cboSeccion, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboTiempoProcesamiento, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4)
                                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
                jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel14Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(cboSeccion, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2)
                                        .addComponent(cboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel3)
                                        .addComponent(cboTiempoProcesamiento, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel4))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 90, 132));
        jLabel5.setText("Se deriva:");

        swDerivacion.setToolTipText("");
        swDerivacion.setActivado(false);
        swDerivacion.setBgOn(new java.awt.Color(0, 90, 132));

        cboDerivaciones.setForeground(new java.awt.Color(0, 90, 132));
        cboDerivaciones.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Laboratorio 1", "Laboratorio 2", "Laboratorio 3"}));
        cboDerivaciones.setColorArrow(new java.awt.Color(0, 90, 132));
        cboDerivaciones.setColorBorde(new java.awt.Color(255, 255, 255));
        cboDerivaciones.setColorBoton(new java.awt.Color(255, 255, 255));
        cboDerivaciones.setColorDisabledIndex(new java.awt.Color(0, 90, 132));
        cboDerivaciones.setColorDisabledIndexText(new java.awt.Color(0, 90, 132));
        cboDerivaciones.setColorFondo(new java.awt.Color(255, 255, 255));
        cboDerivaciones.setColorListaItemsTXT(new java.awt.Color(0, 90, 132));
        cboDerivaciones.setColorSeleccion(new java.awt.Color(0, 90, 132));
        cboDerivaciones.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cboDerivacionesKeyPressed(evt);
            }
        });

        btnDerivaciones.setBackground(new java.awt.Color(0, 90, 132));
        btnDerivaciones.setText("Derivaciones");
        btnDerivaciones.setBackgroundHover(new java.awt.Color(50, 90, 132));
        btnDerivaciones.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnDerivaciones.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.REDO);
        btnDerivaciones.setRound(20);
        btnDerivaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDerivacionesActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 90, 132));
        jLabel16.setText("Prioridad:");

        lblPrioridadInforme.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblPrioridadInforme.setForeground(new java.awt.Color(153, 0, 0));
        lblPrioridadInforme.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        DefaultTreeCellRenderer render = (DefaultTreeCellRenderer) arbolPracticas.getCellRenderer();
        render.setLeafIcon(new ImageIcon(getClass().getResource("/Imagenes/analisis.png")));
        render.setClosedIcon(new ImageIcon(getClass().getResource("/Imagenes/practicas.png")));
        render.setOpenIcon(new ImageIcon(getClass().getResource("/Imagenes/practica.png")));
        arbolPracticas.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        arbolPracticas.setForeground(new java.awt.Color(0, 90, 132));
        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Prácticas");
        javax.swing.tree.DefaultMutableTreeNode treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Hemograma");
        javax.swing.tree.DefaultMutableTreeNode treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Leucocitos");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Eritrocitos");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        arbolPracticas.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jScrollPane3.setViewportView(arbolPracticas);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
                jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel8Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
                                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
                jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel8Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                                .addContainerGap())
        );

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));

        tablaPioridadPractica.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        tablaPioridadPractica.setForeground(new java.awt.Color(255, 255, 255));
        tablaPioridadPractica.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{

                },
                new String[]{

                }
        ));
        tablaPioridadPractica.setBackgoundHead(new java.awt.Color(0, 90, 132));
        tablaPioridadPractica.setBackgoundHover(new java.awt.Color(0, 90, 132));
        tablaPioridadPractica.setBorderHead(null);
        tablaPioridadPractica.setBorderRows(null);
        tablaPioridadPractica.setColorBorderHead(new java.awt.Color(255, 255, 255));
        tablaPioridadPractica.setColorBorderRows(new java.awt.Color(255, 255, 255));
        tablaPioridadPractica.setColorPrimaryText(new java.awt.Color(0, 90, 132));
        tablaPioridadPractica.setColorSecondary(new java.awt.Color(255, 255, 255));
        tablaPioridadPractica.setColorSecundaryText(new java.awt.Color(0, 90, 132));
        tablaPioridadPractica.setGridColor(new java.awt.Color(255, 255, 255));
        tablaPioridadPractica.setSelectionBackground(new java.awt.Color(0, 90, 132));
        tablaPioridadPractica.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaPioridadPracticaMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tablaPioridadPractica);

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
                jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel16Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
                                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
                jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel16Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
                jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
                jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );

        btnActualizarInformacion.setBackground(new java.awt.Color(0, 90, 132));
        btnActualizarInformacion.setText("Actualizar información");
        btnActualizarInformacion.setBackgroundHover(new java.awt.Color(50, 90, 132));
        btnActualizarInformacion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnActualizarInformacion.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.AIRPLAY);
        btnActualizarInformacion.setRound(20);
        btnActualizarInformacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarInformacionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelGeneralLayout = new javax.swing.GroupLayout(panelGeneral);
        panelGeneral.setLayout(panelGeneralLayout);
        panelGeneralLayout.setHorizontalGroup(
                panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelGeneralLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtInstrucciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(panelGeneralLayout.createSequentialGroup()
                                                .addComponent(jLabel5)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(swDerivacion, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(cboDerivaciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnDerivaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(panelGeneralLayout.createSequentialGroup()
                                                .addComponent(jLabel16)
                                                .addGap(18, 18, 18)
                                                .addComponent(lblPrioridadInforme, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelGeneralLayout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(btnActualizarInformacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
        );
        panelGeneralLayout.setVerticalGroup(
                panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelGeneralLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(txtInstrucciones, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblPrioridadInforme, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(swDerivacion, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cboDerivaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnDerivaciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                                .addComponent(btnActualizarInformacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        panelContenedor.add(panelGeneral);

        panelAnalisis.setBackground(new java.awt.Color(255, 255, 255));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 90, 132)), "Análisis", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 90, 132))); // NOI18N

        DefaultTreeCellRenderer render2 = (DefaultTreeCellRenderer) jTree1.getCellRenderer();
        render2.setLeafIcon(new ImageIcon(getClass().getResource("/Imagenes/analisis.png")));
        render2.setClosedIcon(new ImageIcon(getClass().getResource("/Imagenes/practicas.png")));
        render2.setOpenIcon(new ImageIcon(getClass().getResource("/Imagenes/practica.png")));
        jTree1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTree1.setForeground(new java.awt.Color(0, 90, 132));
        jScrollPane1.setViewportView(jTree1);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
                jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel9Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1)
                                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
                jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jScrollPane1)
                                .addContainerGap())
        );

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 90, 132)), "Datos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 90, 132))); // NOI18N

        chkUnSoloMetodo.setBorder(null);
        chkUnSoloMetodo.setForeground(new java.awt.Color(0, 90, 132));
        chkUnSoloMetodo.setText("Un solo método para la práctica");
        chkUnSoloMetodo.setColorCheck(new java.awt.Color(0, 90, 132));
        chkUnSoloMetodo.setColorUnCheck(new java.awt.Color(0, 90, 132));
        chkUnSoloMetodo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkUnSoloMetodo.setRippleColor(new java.awt.Color(0, 90, 132));

        btnTitulo.setBackground(new java.awt.Color(0, 90, 132));
        btnTitulo.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.EDIT);
        btnTitulo.setRound(20);
        btnTitulo.setSizeIcon(20.0F);

        txtMetodo.setForeground(new java.awt.Color(0, 90, 132));
        txtMetodo.setColorMaterial(new java.awt.Color(0, 90, 132));
        txtMetodo.setMaximumSize(null);
        txtMetodo.setMinimumSize(null);
        txtMetodo.setPhColor(new java.awt.Color(0, 90, 132));
        txtMetodo.setPlaceholder("Método");
        txtMetodo.setPreferredSize(null);
        txtMetodo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMetodoKeyReleased(evt);
            }
        });

        txtTitulo.setForeground(new java.awt.Color(0, 90, 132));
        txtTitulo.setColorMaterial(new java.awt.Color(0, 90, 132));
        txtTitulo.setMaximumSize(null);
        txtTitulo.setMinimumSize(null);
        txtTitulo.setPhColor(new java.awt.Color(0, 90, 132));
        txtTitulo.setPlaceholder("Título");
        txtTitulo.setPreferredSize(null);
        txtTitulo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTituloKeyReleased(evt);
            }
        });

        txtFormatoMesada.setForeground(new java.awt.Color(0, 90, 132));
        txtFormatoMesada.setColorMaterial(new java.awt.Color(0, 90, 132));
        txtFormatoMesada.setMaximumSize(null);
        txtFormatoMesada.setMinimumSize(null);
        txtFormatoMesada.setPhColor(new java.awt.Color(0, 90, 132));
        txtFormatoMesada.setPlaceholder("Nombre del análisis");
        txtFormatoMesada.setPreferredSize(null);
        txtFormatoMesada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFormatoMesadaActionPerformed(evt);
            }
        });
        txtFormatoMesada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFormatoMesadaKeyReleased(evt);
            }
        });

        txtNombreInforme.setForeground(new java.awt.Color(0, 90, 132));
        txtNombreInforme.setColorMaterial(new java.awt.Color(0, 90, 132));
        txtNombreInforme.setMaximumSize(null);
        txtNombreInforme.setMinimumSize(null);
        txtNombreInforme.setPhColor(new java.awt.Color(0, 90, 132));
        txtNombreInforme.setPlaceholder("Nombre en informe");
        txtNombreInforme.setPreferredSize(null);
        txtNombreInforme.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreInformeKeyReleased(evt);
            }
        });

        btnMetodo.setBackground(new java.awt.Color(0, 90, 132));
        btnMetodo.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.EDIT);
        btnMetodo.setRound(20);
        btnMetodo.setSizeIcon(20.0F);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 90, 132)), "Tipo de resultado y valores de referencia", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 90, 132))); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 90, 132));
        jLabel6.setText(" ");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 90, 132));
        jLabel7.setText(" ");

        cboSexo4.setForeground(new java.awt.Color(0, 90, 132));
        cboSexo4.setModel(new javax.swing.DefaultComboBoxModel(new String[]{""}));
        cboSexo4.setToolTipText("");
        cboSexo4.setColorArrow(new java.awt.Color(0, 90, 132));
        cboSexo4.setColorBorde(new java.awt.Color(255, 255, 255));
        cboSexo4.setColorBoton(new java.awt.Color(255, 255, 255));
        cboSexo4.setColorDisabledIndex(new java.awt.Color(0, 90, 132));
        cboSexo4.setColorDisabledIndexText(new java.awt.Color(0, 90, 132));
        cboSexo4.setColorFondo(new java.awt.Color(255, 255, 255));
        cboSexo4.setColorListaItemsTXT(new java.awt.Color(0, 90, 132));
        cboSexo4.setColorSeleccion(new java.awt.Color(0, 90, 132));
        cboSexo4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cboSexo4KeyPressed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 90, 132));
        jLabel8.setText(" ");

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextArea1.setForeground(new java.awt.Color(0, 90, 132));
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setBorder(null);
        jTextArea1.setCaretColor(new java.awt.Color(255, 255, 255));
        jTextArea1.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jTextArea1.setSelectionColor(new java.awt.Color(0, 90, 132));
        jScrollPane2.setViewportView(jTextArea1);

        btnEditarResultados.setBackground(new java.awt.Color(0, 90, 132));
        btnEditarResultados.setText("Editar");
        btnEditarResultados.setBackgroundHover(new java.awt.Color(204, 204, 204));
        btnEditarResultados.setForegroundIconHover(new java.awt.Color(0, 90, 132));
        btnEditarResultados.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnEditarResultados.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.EDIT);
        btnEditarResultados.setPreferredSize(new java.awt.Dimension(130, 40));
        btnEditarResultados.setRound(20);
        btnEditarResultados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarResultadosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cboSexo4, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)
                                        .addComponent(jScrollPane2)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(btnEditarResultados, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(cboSexo4, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEditarResultados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
                jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel10Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(chkUnSoloMetodo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtFormatoMesada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel10Layout.createSequentialGroup()
                                                .addComponent(txtTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(txtNombreInforme, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel10Layout.createSequentialGroup()
                                                .addComponent(txtMetodo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnMetodo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
                jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel10Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel10Layout.createSequentialGroup()
                                                .addComponent(txtFormatoMesada, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(txtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btnTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addComponent(txtNombreInforme, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtMetodo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(btnMetodo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(chkUnSoloMetodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        btnAgregar.setBackground(new java.awt.Color(0, 90, 132));
        btnAgregar.setText("Agregar");
        btnAgregar.setBackgroundHover(new java.awt.Color(204, 204, 204));
        btnAgregar.setForegroundIconHover(new java.awt.Color(0, 90, 132));
        btnAgregar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnAgregar.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ADD);
        btnAgregar.setPreferredSize(new java.awt.Dimension(130, 40));
        btnAgregar.setRound(20);
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnBorrar.setBackground(new java.awt.Color(0, 90, 132));
        btnBorrar.setText("Borrar");
        btnBorrar.setBackgroundHover(new java.awt.Color(204, 204, 204));
        btnBorrar.setForegroundIconHover(new java.awt.Color(0, 90, 132));
        btnBorrar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnBorrar.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.DELETE);
        btnBorrar.setPreferredSize(new java.awt.Dimension(130, 40));
        btnBorrar.setRound(20);
        btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarActionPerformed(evt);
            }
        });

        btnLimpiar.setBackground(new java.awt.Color(0, 90, 132));
        btnLimpiar.setText("Limpiar");
        btnLimpiar.setBackgroundHover(new java.awt.Color(204, 204, 204));
        btnLimpiar.setForegroundIconHover(new java.awt.Color(0, 90, 132));
        btnLimpiar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnLimpiar.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.CLEAR_ALL);
        btnLimpiar.setPreferredSize(new java.awt.Dimension(130, 40));
        btnLimpiar.setRound(20);
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 90, 132)), "Prioridad", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 90, 132))); // NOI18N

        btnSubirPrioridad.setBackground(new java.awt.Color(0, 90, 132));
        btnSubirPrioridad.setText("Subir prioridad");
        btnSubirPrioridad.setBackgroundHover(new java.awt.Color(204, 204, 204));
        btnSubirPrioridad.setForegroundIconHover(new java.awt.Color(0, 90, 132));
        btnSubirPrioridad.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnSubirPrioridad.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ARROW_UPWARD);
        btnSubirPrioridad.setPreferredSize(new java.awt.Dimension(130, 40));
        btnSubirPrioridad.setRound(20);
        btnSubirPrioridad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubirPrioridadActionPerformed(evt);
            }
        });

        btnBajarPrioridad.setBackground(new java.awt.Color(0, 90, 132));
        btnBajarPrioridad.setText("Bajar prioridad");
        btnBajarPrioridad.setBackgroundHover(new java.awt.Color(204, 204, 204));
        btnBajarPrioridad.setForegroundIconHover(new java.awt.Color(0, 90, 132));
        btnBajarPrioridad.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnBajarPrioridad.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ARROW_DOWNWARD);
        btnBajarPrioridad.setPreferredSize(new java.awt.Dimension(130, 40));
        btnBajarPrioridad.setRound(20);
        btnBajarPrioridad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBajarPrioridadActionPerformed(evt);
            }
        });

        jLabel9.setForeground(new java.awt.Color(0, 90, 132));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Seleccione primero el análisis y luego modifique la prioridad");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(btnSubirPrioridad, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnBajarPrioridad, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnSubirPrioridad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnBajarPrioridad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
        );

        javax.swing.GroupLayout panelAnalisisLayout = new javax.swing.GroupLayout(panelAnalisis);
        panelAnalisis.setLayout(panelAnalisisLayout);
        panelAnalisisLayout.setHorizontalGroup(
                panelAnalisisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelAnalisisLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addGroup(panelAnalisisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        panelAnalisisLayout.setVerticalGroup(
                panelAnalisisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelAnalisisLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelAnalisisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(panelAnalisisLayout.createSequentialGroup()
                                                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
        );

        panelContenedor.add(panelAnalisis);

        panelPrevisualizar.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelPrevisualizarLayout = new javax.swing.GroupLayout(panelPrevisualizar);
        panelPrevisualizar.setLayout(panelPrevisualizarLayout);
        panelPrevisualizarLayout.setHorizontalGroup(
                panelPrevisualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 893, Short.MAX_VALUE)
        );
        panelPrevisualizarLayout.setVerticalGroup(
                panelPrevisualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 632, Short.MAX_VALUE)
        );

        panelContenedor.add(panelPrevisualizar);

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
                panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(panelPrincipalLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panelPrincipalLayout.createSequentialGroup()
                                                .addComponent(panelPracticas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(panelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(panelCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(panelPrincipalLayout.createSequentialGroup()
                                                .addComponent(panelBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(panelContenedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        panelPrincipalLayout.setVerticalGroup(
                panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelPrincipalLayout.createSequentialGroup()
                                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panelPrincipalLayout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(panelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(panelPracticas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(panelCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(panelBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(panelContenedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>                        

    private void rSButtonIconOne1ActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
    }

    private void txtPracticaActionPerformed(java.awt.event.ActionEvent evt) {
        lblPractica.setText(txtPractica.getText());
        txtPractica.setText(null);
    }

    private void btnUtilitarios1ActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
    }

    private void btnGeneralActionPerformed(java.awt.event.ActionEvent evt) {
        panelGeneral.setVisible(true);
        panelAnalisis.setVisible(false);
        panelPrevisualizar.setVisible(false);
    }

    private void panelPrincipalMousePressed(java.awt.event.MouseEvent evt) {
        x = evt.getX();
        y = evt.getY();
    }

    private void panelPrincipalMouseDragged(java.awt.event.MouseEvent evt) {
        this.setLocation(this.getLocation().x + evt.getX() - x, this.getLocation().y + evt.getY() - y);
    }

    private void txtInstruccionesFocusLost(java.awt.event.FocusEvent evt) {
    }

    private void txtInstruccionesActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void txtInstruccionesKeyTyped(java.awt.event.KeyEvent evt) {
        char c = evt.getKeyChar();
        if (c < '0' || c > '9') {
            evt.consume();
        }
    }

    private void cboSeccionKeyPressed(java.awt.event.KeyEvent evt) {
    }

    private void cboTipoKeyPressed(java.awt.event.KeyEvent evt) {
    }

    private void cboTiempoProcesamientoKeyPressed(java.awt.event.KeyEvent evt) {
    }

    private void btnDerivacionesActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void cboDerivacionesKeyPressed(java.awt.event.KeyEvent evt) {
    }

    private void txtFormatoMesadaKeyReleased(java.awt.event.KeyEvent evt) {
    }

    private void txtTituloKeyReleased(java.awt.event.KeyEvent evt) {
    }

    private void txtMetodoKeyReleased(java.awt.event.KeyEvent evt) {
    }

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void btnEditarResultadosActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void txtFormatoMesadaActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void btnInforme1ActionPerformed(java.awt.event.ActionEvent evt) {
        panelGeneral.setVisible(false);
        panelAnalisis.setVisible(true);
        panelPrevisualizar.setVisible(false);
    }

    private void btnInforme2ActionPerformed(java.awt.event.ActionEvent evt) {
        panelGeneral.setVisible(false);
        panelAnalisis.setVisible(false);
        panelPrevisualizar.setVisible(true);
    }

    private void tablaPioridadPracticaMouseClicked(java.awt.event.MouseEvent evt) {
    }

    private void txtNombreInformeKeyReleased(java.awt.event.KeyEvent evt) {
    }

    private void cboSexo4KeyPressed(java.awt.event.KeyEvent evt) {
    }

    private void btnSubirPrioridadActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void btnBajarPrioridadActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void btnActualizarInformacionActionPerformed(java.awt.event.ActionEvent evt) {
    }


    // Variables declaration - do not modify
    private javax.swing.JTree arbolPracticas;
    private RSMaterialComponent.RSButtonMaterialIconOne btnActualizarInformacion;
    private RSMaterialComponent.RSButtonMaterialIconOne btnAgregar;
    private RSMaterialComponent.RSButtonMaterialIconOne btnBajarPrioridad;
    private RSMaterialComponent.RSButtonMaterialIconOne btnBorrar;
    private RSMaterialComponent.RSButtonMaterialIconOne btnDerivaciones;
    private RSMaterialComponent.RSButtonMaterialIconOne btnEditarResultados;
    private RSMaterialComponent.RSButtonMaterialIconOne btnGeneral;
    private RSMaterialComponent.RSButtonMaterialIconOne btnInforme1;
    private RSMaterialComponent.RSButtonMaterialIconOne btnInforme2;
    private RSMaterialComponent.RSButtonMaterialIconOne btnLimpiar;
    private RSMaterialComponent.RSButtonIconOne btnMetodo;
    private RSMaterialComponent.RSButtonMaterialIconOne btnSubirPrioridad;
    private RSMaterialComponent.RSButtonIconOne btnTitulo;
    private RSMaterialComponent.RSButtonMaterialIconOne btnUtilitarios1;
    private RSMaterialComponent.RSComboBox cboDerivaciones;
    private RSMaterialComponent.RSComboBox cboSeccion;
    private RSMaterialComponent.RSComboBox cboSexo4;
    private RSMaterialComponent.RSComboBox cboTiempoProcesamiento;
    private RSMaterialComponent.RSComboBox cboTipo;
    private RSMaterialComponent.RSCheckBoxMaterial chkUnSoloMetodo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTree jTree1;
    private javax.swing.JLabel lblPractica;
    private javax.swing.JLabel lblPrioridadInforme;
    private javax.swing.JPanel panelAnalisis;
    private javax.swing.JPanel panelBotones;
    private javax.swing.JPanel panelCerrar;
    private javax.swing.JPanel panelContenedor;
    private javax.swing.JPanel panelGeneral;
    private javax.swing.JPanel panelPracticas;
    private javax.swing.JPanel panelPrevisualizar;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JPanel panelTitulo;
    private RSMaterialComponent.RSButtonIconOne rSButtonIconOne1;
    private RSMaterialComponent.RSLabelIcon rSLabelIcon1;
    private RSMaterialComponent.RSSwitch swDerivacion;
    private RSMaterialComponent.RSTableMetroCustom tablaPioridadPractica;
    private RSMaterialComponent.RSTextFieldMaterial txtFormatoMesada;
    private RSMaterialComponent.RSTextFieldMaterial txtInstrucciones;
    private RSMaterialComponent.RSTextFieldMaterial txtMetodo;
    private RSMaterialComponent.RSTextFieldMaterial txtNombreInforme;
    private RSMaterialComponent.RSTextFieldMaterial txtPractica;
    private RSMaterialComponent.RSTextFieldMaterial txtTitulo;
    // End of variables declaration                   
}
