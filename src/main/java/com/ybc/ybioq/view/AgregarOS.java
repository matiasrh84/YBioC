package com.ybc.ybioq.view;


import static com.ybc.ybioq.config.Escape.funcionescape;

public class AgregarOS extends javax.swing.JDialog {

    private int x;
    private int y;

    public AgregarOS(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        funcionescape(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoAltaComplejidad = new javax.swing.ButtonGroup();
        grupoNoNomenclados = new javax.swing.ButtonGroup();
        grupoFacturaPor = new javax.swing.ButtonGroup();
        grupoFacturaPorPaciente = new javax.swing.ButtonGroup();
        grupoImprimeDobleInforme = new javax.swing.ButtonGroup();
        grupoD998 = new javax.swing.ButtonGroup();
        grupoSubtotal = new javax.swing.ButtonGroup();
        grupoTieneCategorizacion = new javax.swing.ButtonGroup();
        grupoTipoDeFacturacion = new javax.swing.ButtonGroup();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        txtRazonSocial = new RSMaterialComponent.RSTextFieldMaterial();
        txtCodigoOs = new RSMaterialComponent.RSTextFieldMaterial();
        txtCodigoOs1 = new RSMaterialComponent.RSTextFieldMaterial();
        cboNBU = new RSMaterialComponent.RSComboBox();
        txtCodigoFacturacion = new RSMaterialComponent.RSTextFieldMaterial();
        txtDireccion = new RSMaterialComponent.RSTextFieldMaterial();
        txtLocalidad = new RSMaterialComponent.RSTextFieldMaterial();
        txtCodigoPostal = new RSMaterialComponent.RSTextFieldMaterial();
        txtProvincia = new RSMaterialComponent.RSTextFieldMaterial();
        txtTelefono = new RSMaterialComponent.RSTextFieldMaterial();
        txtEmail = new RSMaterialComponent.RSTextFieldMaterial();
        txtPaginaWeb = new RSMaterialComponent.RSTextFieldMaterial();
        txtReferente = new RSMaterialComponent.RSTextFieldMaterial();
        txtTelefonoReferente = new RSMaterialComponent.RSTextFieldMaterial();
        txtUnidadArancel = new RSMaterialComponent.RSTextFieldMaterial();
        dcFechaAlta = new newscomponents.RSDateChooser();
        txtContrato = new RSMaterialComponent.RSTextFieldMaterial();
        txtPorcentajeAfiliado = new RSMaterialComponent.RSTextFieldMaterial();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaObrasSociales = new RSMaterialComponent.RSTableMetroCustom();
        jPanel4 = new javax.swing.JPanel();
        btnAgregar = new RSMaterialComponent.RSButtonMaterialIconOne();
        btnCancelar = new RSMaterialComponent.RSButtonMaterialIconOne();
        btnSalir = new RSMaterialComponent.RSButtonMaterialIconOne();
        btnModificar = new RSMaterialComponent.RSButtonMaterialIconOne();
        jPanel1 = new javax.swing.JPanel();
        btnCerrar = new RSMaterialComponent.RSButtonIconOne();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        rsSiAltaComplejidad = new RSMaterialComponent.RSRadioButtonMaterial();
        rsNoAltaComplejidad = new RSMaterialComponent.RSRadioButtonMaterial();
        rsNoFacturaNoNomenclados = new RSMaterialComponent.RSRadioButtonMaterial();
        rsSiFacturaNoNomenclados = new RSMaterialComponent.RSRadioButtonMaterial();
        rsPacienteCompleto = new RSMaterialComponent.RSRadioButtonMaterial();
        rsCupon = new RSMaterialComponent.RSRadioButtonMaterial();
        rsNoFacturaPorPaciente = new RSMaterialComponent.RSRadioButtonMaterial();
        rsSiFacturaPorPaciente = new RSMaterialComponent.RSRadioButtonMaterial();
        rsSiempre = new RSMaterialComponent.RSRadioButtonMaterial();
        rsNunca = new RSMaterialComponent.RSRadioButtonMaterial();
        rsNoIncluye = new RSMaterialComponent.RSRadioButtonMaterial();
        rsIncluye = new RSMaterialComponent.RSRadioButtonMaterial();
        rsNoSubtotal = new RSMaterialComponent.RSRadioButtonMaterial();
        rsSiSubtotal = new RSMaterialComponent.RSRadioButtonMaterial();
        rsNoCategorizacion = new RSMaterialComponent.RSRadioButtonMaterial();
        rsSiCategorizacion = new RSMaterialComponent.RSRadioButtonMaterial();
        rsColegio = new RSMaterialComponent.RSRadioButtonMaterial();
        rsDirecta = new RSMaterialComponent.RSRadioButtonMaterial();
        rsSelectivo = new RSMaterialComponent.RSRadioButtonMaterial();
        rsDiscrimina = new RSMaterialComponent.RSRadioButtonMaterial();
        jPanel8 = new javax.swing.JPanel();
        cboTipoIVA = new RSMaterialComponent.RSComboBox();
        jPanel9 = new javax.swing.JPanel();
        cboTipoIVA1 = new RSMaterialComponent.RSComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(255, 255, 255), new java.awt.Color(0, 90, 132)));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        txtRazonSocial.setForeground(new java.awt.Color(0, 90, 132));
        txtRazonSocial.setColorMaterial(new java.awt.Color(0, 90, 132));
        txtRazonSocial.setPhColor(new java.awt.Color(0, 90, 132));
        txtRazonSocial.setPlaceholder("Razón Social");
        txtRazonSocial.setSelectionColor(new java.awt.Color(0, 90, 132));

        txtCodigoOs.setForeground(new java.awt.Color(0, 90, 132));
        txtCodigoOs.setColorMaterial(new java.awt.Color(0, 90, 132));
        txtCodigoOs.setPhColor(new java.awt.Color(0, 90, 132));
        txtCodigoOs.setPlaceholder("Código OS");
        txtCodigoOs.setSelectionColor(new java.awt.Color(0, 90, 132));

        txtCodigoOs1.setForeground(new java.awt.Color(0, 90, 132));
        txtCodigoOs1.setColorMaterial(new java.awt.Color(0, 90, 132));
        txtCodigoOs1.setPhColor(new java.awt.Color(0, 90, 132));
        txtCodigoOs1.setPlaceholder("CUIT");
        txtCodigoOs1.setSelectionColor(new java.awt.Color(0, 90, 132));

        cboNBU.setForeground(new java.awt.Color(0, 90, 132));
        cboNBU.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"NBU"}));
        cboNBU.setColorArrow(new java.awt.Color(0, 90, 132));
        cboNBU.setColorBorde(new java.awt.Color(255, 255, 255));
        cboNBU.setColorBoton(new java.awt.Color(255, 255, 255));
        cboNBU.setColorDisabledIndex(new java.awt.Color(0, 90, 132));
        cboNBU.setColorDisabledIndexText(new java.awt.Color(0, 90, 132));
        cboNBU.setColorFondo(new java.awt.Color(255, 255, 255));
        cboNBU.setColorListaItemsTXT(new java.awt.Color(0, 90, 132));
        cboNBU.setColorSeleccion(new java.awt.Color(0, 90, 132));

        txtCodigoFacturacion.setForeground(new java.awt.Color(0, 90, 132));
        txtCodigoFacturacion.setColorMaterial(new java.awt.Color(0, 90, 132));
        txtCodigoFacturacion.setPhColor(new java.awt.Color(0, 90, 132));
        txtCodigoFacturacion.setPlaceholder("Codigo deFacturación");
        txtCodigoFacturacion.setSelectionColor(new java.awt.Color(0, 90, 132));

        txtDireccion.setForeground(new java.awt.Color(0, 90, 132));
        txtDireccion.setColorMaterial(new java.awt.Color(0, 90, 132));
        txtDireccion.setPhColor(new java.awt.Color(0, 90, 132));
        txtDireccion.setPlaceholder("Dirección");
        txtDireccion.setSelectionColor(new java.awt.Color(0, 90, 132));

        txtLocalidad.setForeground(new java.awt.Color(0, 90, 132));
        txtLocalidad.setColorMaterial(new java.awt.Color(0, 90, 132));
        txtLocalidad.setPhColor(new java.awt.Color(0, 90, 132));
        txtLocalidad.setPlaceholder("Localidad");
        txtLocalidad.setSelectionColor(new java.awt.Color(0, 90, 132));

        txtCodigoPostal.setForeground(new java.awt.Color(0, 90, 132));
        txtCodigoPostal.setColorMaterial(new java.awt.Color(0, 90, 132));
        txtCodigoPostal.setPhColor(new java.awt.Color(0, 90, 132));
        txtCodigoPostal.setPlaceholder("CP");
        txtCodigoPostal.setSelectionColor(new java.awt.Color(0, 90, 132));

        txtProvincia.setForeground(new java.awt.Color(0, 90, 132));
        txtProvincia.setColorMaterial(new java.awt.Color(0, 90, 132));
        txtProvincia.setPhColor(new java.awt.Color(0, 90, 132));
        txtProvincia.setPlaceholder("Provincia");
        txtProvincia.setSelectionColor(new java.awt.Color(0, 90, 132));

        txtTelefono.setForeground(new java.awt.Color(0, 90, 132));
        txtTelefono.setColorMaterial(new java.awt.Color(0, 90, 132));
        txtTelefono.setPhColor(new java.awt.Color(0, 90, 132));
        txtTelefono.setPlaceholder("Teléfono");
        txtTelefono.setSelectionColor(new java.awt.Color(0, 90, 132));

        txtEmail.setForeground(new java.awt.Color(0, 90, 132));
        txtEmail.setColorMaterial(new java.awt.Color(0, 90, 132));
        txtEmail.setPhColor(new java.awt.Color(0, 90, 132));
        txtEmail.setPlaceholder("Email");
        txtEmail.setSelectionColor(new java.awt.Color(0, 90, 132));

        txtPaginaWeb.setForeground(new java.awt.Color(0, 90, 132));
        txtPaginaWeb.setColorMaterial(new java.awt.Color(0, 90, 132));
        txtPaginaWeb.setPhColor(new java.awt.Color(0, 90, 132));
        txtPaginaWeb.setPlaceholder("Página Web");
        txtPaginaWeb.setSelectionColor(new java.awt.Color(0, 90, 132));

        txtReferente.setForeground(new java.awt.Color(0, 90, 132));
        txtReferente.setColorMaterial(new java.awt.Color(0, 90, 132));
        txtReferente.setPhColor(new java.awt.Color(0, 90, 132));
        txtReferente.setPlaceholder("Referente");
        txtReferente.setSelectionColor(new java.awt.Color(0, 90, 132));

        txtTelefonoReferente.setForeground(new java.awt.Color(0, 90, 132));
        txtTelefonoReferente.setColorMaterial(new java.awt.Color(0, 90, 132));
        txtTelefonoReferente.setPhColor(new java.awt.Color(0, 90, 132));
        txtTelefonoReferente.setPlaceholder("Celular Referente");
        txtTelefonoReferente.setSelectionColor(new java.awt.Color(0, 90, 132));

        txtUnidadArancel.setForeground(new java.awt.Color(0, 90, 132));
        txtUnidadArancel.setColorMaterial(new java.awt.Color(0, 90, 132));
        txtUnidadArancel.setPhColor(new java.awt.Color(0, 90, 132));
        txtUnidadArancel.setPlaceholder("Unidad de importe de arancel");
        txtUnidadArancel.setSelectionColor(new java.awt.Color(0, 90, 132));

        dcFechaAlta.setBackground(new java.awt.Color(255, 255, 255));
        dcFechaAlta.setForeground(new java.awt.Color(0, 90, 132));
        dcFechaAlta.setBgColor(new java.awt.Color(0, 90, 132));
        dcFechaAlta.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        dcFechaAlta.setFormatDate("dd-MM-yyyy");

        txtContrato.setForeground(new java.awt.Color(0, 90, 132));
        txtContrato.setColorMaterial(new java.awt.Color(0, 90, 132));
        txtContrato.setPhColor(new java.awt.Color(0, 90, 132));
        txtContrato.setPlaceholder("Contrato");
        txtContrato.setSelectionColor(new java.awt.Color(0, 90, 132));

        txtPorcentajeAfiliado.setForeground(new java.awt.Color(0, 90, 132));
        txtPorcentajeAfiliado.setColorMaterial(new java.awt.Color(0, 90, 132));
        txtPorcentajeAfiliado.setPhColor(new java.awt.Color(0, 90, 132));
        txtPorcentajeAfiliado.setPlaceholder("% afiliado");
        txtPorcentajeAfiliado.setSelectionColor(new java.awt.Color(0, 90, 132));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                .addComponent(txtRazonSocial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtCodigoOs, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(21, 21, 21))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(dcFechaAlta, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtCodigoOs1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(cboNBU, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtCodigoFacturacion, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                                .addContainerGap())
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtLocalidad, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtCodigoPostal, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap())
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(txtProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                                                .addComponent(txtUnidadArancel, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(txtContrato, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                                                .addComponent(txtPaginaWeb, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(txtReferente, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(txtTelefonoReferente, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                                        .addComponent(txtPorcentajeAfiliado, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                                                .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtRazonSocial, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtCodigoOs, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(txtCodigoOs1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(cboNBU, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                                                .addComponent(txtCodigoFacturacion, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(dcFechaAlta, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtLocalidad, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtCodigoPostal, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtPaginaWeb, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtReferente, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtTelefonoReferente, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtUnidadArancel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtContrato, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtPorcentajeAfiliado, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        tablaObrasSociales.setForeground(new java.awt.Color(255, 255, 255));
        tablaObrasSociales.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{

                },
                new String[]{

                }
        ));
        tablaObrasSociales.setBackgoundHead(new java.awt.Color(0, 90, 132));
        tablaObrasSociales.setBackgoundHover(new java.awt.Color(0, 90, 132));
        tablaObrasSociales.setColorBorderHead(new java.awt.Color(255, 255, 255));
        tablaObrasSociales.setColorBorderRows(new java.awt.Color(255, 255, 255));
        tablaObrasSociales.setGridColor(new java.awt.Color(204, 204, 204));
        jScrollPane1.setViewportView(tablaObrasSociales);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
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
        btnAgregar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnAgregar.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ADD);
        btnAgregar.setRound(20);

        btnCancelar.setBackground(new java.awt.Color(0, 90, 132));
        btnCancelar.setText("Cancelar");
        btnCancelar.setBackgroundHover(new java.awt.Color(204, 204, 204));
        btnCancelar.setForegroundIcon(new java.awt.Color(0, 90, 132));
        btnCancelar.setForegroundIconHover(new java.awt.Color(0, 90, 132));
        btnCancelar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnCancelar.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.CLEAR);
        btnCancelar.setRound(20);

        btnSalir.setBackground(new java.awt.Color(0, 90, 132));
        btnSalir.setText("Salir");
        btnSalir.setBackgroundHover(new java.awt.Color(204, 204, 204));
        btnSalir.setForegroundIcon(new java.awt.Color(0, 90, 132));
        btnSalir.setForegroundIconHover(new java.awt.Color(0, 90, 132));
        btnSalir.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
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
        btnModificar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnModificar.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.EDIT);
        btnModificar.setRound(20);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnSalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(0, 90, 132));
        jPanel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel1MouseDragged(evt);
            }
        });
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel1MousePressed(evt);
            }
        });

        btnCerrar.setBackground(new java.awt.Color(255, 255, 255));
        btnCerrar.setBackgroundHover(new java.awt.Color(0, 90, 132));
        btnCerrar.setForegroundHover(new java.awt.Color(0, 90, 132));
        btnCerrar.setForegroundText(new java.awt.Color(0, 90, 132));
        btnCerrar.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.CLOSE);
        btnCerrar.setRippleColor(new java.awt.Color(0, 90, 132));
        btnCerrar.setSizeIcon(20.0F);
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 90, 132));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Alta Obra Social");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)
                                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(0, 90, 132));
        jLabel25.setText("Alta complejidad por separado:");

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(0, 90, 132));
        jLabel26.setText("Factura no nomenclados:");

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(0, 90, 132));
        jLabel32.setText("Factura por:");

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(0, 90, 132));
        jLabel33.setText("Factura por paciente:");

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(0, 90, 132));
        jLabel34.setText("Imprime doble informe:");

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(0, 90, 132));
        jLabel35.setText("d998:");

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(0, 90, 132));
        jLabel36.setText("Subtotal por paciente:");

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(0, 90, 132));
        jLabel37.setText("Tiene categorización:");

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(0, 90, 132));
        jLabel38.setText("Tipo de facturación:");

        grupoAltaComplejidad.add(rsSiAltaComplejidad);
        rsSiAltaComplejidad.setForeground(new java.awt.Color(0, 90, 132));
        rsSiAltaComplejidad.setText("Si");
        rsSiAltaComplejidad.setColorCheck(new java.awt.Color(0, 90, 132));
        rsSiAltaComplejidad.setColorUnCheck(new java.awt.Color(0, 90, 132));
        rsSiAltaComplejidad.setRippleColor(new java.awt.Color(0, 90, 132));

        grupoAltaComplejidad.add(rsNoAltaComplejidad);
        rsNoAltaComplejidad.setForeground(new java.awt.Color(0, 90, 132));
        rsNoAltaComplejidad.setText("No");
        rsNoAltaComplejidad.setColorCheck(new java.awt.Color(0, 90, 132));
        rsNoAltaComplejidad.setColorUnCheck(new java.awt.Color(0, 90, 132));
        rsNoAltaComplejidad.setRippleColor(new java.awt.Color(0, 90, 132));

        grupoNoNomenclados.add(rsNoFacturaNoNomenclados);
        rsNoFacturaNoNomenclados.setForeground(new java.awt.Color(0, 90, 132));
        rsNoFacturaNoNomenclados.setText("No");
        rsNoFacturaNoNomenclados.setColorCheck(new java.awt.Color(0, 90, 132));
        rsNoFacturaNoNomenclados.setColorUnCheck(new java.awt.Color(0, 90, 132));
        rsNoFacturaNoNomenclados.setRippleColor(new java.awt.Color(0, 90, 132));

        grupoNoNomenclados.add(rsSiFacturaNoNomenclados);
        rsSiFacturaNoNomenclados.setForeground(new java.awt.Color(0, 90, 132));
        rsSiFacturaNoNomenclados.setText("Si");
        rsSiFacturaNoNomenclados.setColorCheck(new java.awt.Color(0, 90, 132));
        rsSiFacturaNoNomenclados.setColorUnCheck(new java.awt.Color(0, 90, 132));
        rsSiFacturaNoNomenclados.setRippleColor(new java.awt.Color(0, 90, 132));

        grupoFacturaPor.add(rsPacienteCompleto);
        rsPacienteCompleto.setForeground(new java.awt.Color(0, 90, 132));
        rsPacienteCompleto.setText("Paciente completo");
        rsPacienteCompleto.setColorCheck(new java.awt.Color(0, 90, 132));
        rsPacienteCompleto.setColorUnCheck(new java.awt.Color(0, 90, 132));
        rsPacienteCompleto.setRippleColor(new java.awt.Color(0, 90, 132));

        grupoFacturaPor.add(rsCupon);
        rsCupon.setForeground(new java.awt.Color(0, 90, 132));
        rsCupon.setText("Cupón");
        rsCupon.setColorCheck(new java.awt.Color(0, 90, 132));
        rsCupon.setColorUnCheck(new java.awt.Color(0, 90, 132));
        rsCupon.setRippleColor(new java.awt.Color(0, 90, 132));

        grupoFacturaPorPaciente.add(rsNoFacturaPorPaciente);
        rsNoFacturaPorPaciente.setForeground(new java.awt.Color(0, 90, 132));
        rsNoFacturaPorPaciente.setText("No");
        rsNoFacturaPorPaciente.setColorCheck(new java.awt.Color(0, 90, 132));
        rsNoFacturaPorPaciente.setColorUnCheck(new java.awt.Color(0, 90, 132));
        rsNoFacturaPorPaciente.setRippleColor(new java.awt.Color(0, 90, 132));

        grupoFacturaPorPaciente.add(rsSiFacturaPorPaciente);
        rsSiFacturaPorPaciente.setForeground(new java.awt.Color(0, 90, 132));
        rsSiFacturaPorPaciente.setText("Si");
        rsSiFacturaPorPaciente.setColorCheck(new java.awt.Color(0, 90, 132));
        rsSiFacturaPorPaciente.setColorUnCheck(new java.awt.Color(0, 90, 132));
        rsSiFacturaPorPaciente.setRippleColor(new java.awt.Color(0, 90, 132));

        grupoImprimeDobleInforme.add(rsSiempre);
        rsSiempre.setForeground(new java.awt.Color(0, 90, 132));
        rsSiempre.setText("Siempre");
        rsSiempre.setColorCheck(new java.awt.Color(0, 90, 132));
        rsSiempre.setColorUnCheck(new java.awt.Color(0, 90, 132));
        rsSiempre.setRippleColor(new java.awt.Color(0, 90, 132));

        grupoImprimeDobleInforme.add(rsNunca);
        rsNunca.setForeground(new java.awt.Color(0, 90, 132));
        rsNunca.setText("Nunca");
        rsNunca.setColorCheck(new java.awt.Color(0, 90, 132));
        rsNunca.setColorUnCheck(new java.awt.Color(0, 90, 132));
        rsNunca.setRippleColor(new java.awt.Color(0, 90, 132));

        grupoD998.add(rsNoIncluye);
        rsNoIncluye.setForeground(new java.awt.Color(0, 90, 132));
        rsNoIncluye.setText("No incluye");
        rsNoIncluye.setColorCheck(new java.awt.Color(0, 90, 132));
        rsNoIncluye.setColorUnCheck(new java.awt.Color(0, 90, 132));
        rsNoIncluye.setRippleColor(new java.awt.Color(0, 90, 132));

        grupoD998.add(rsIncluye);
        rsIncluye.setForeground(new java.awt.Color(0, 90, 132));
        rsIncluye.setText("Incluye");
        rsIncluye.setColorCheck(new java.awt.Color(0, 90, 132));
        rsIncluye.setColorUnCheck(new java.awt.Color(0, 90, 132));
        rsIncluye.setRippleColor(new java.awt.Color(0, 90, 132));

        grupoSubtotal.add(rsNoSubtotal);
        rsNoSubtotal.setForeground(new java.awt.Color(0, 90, 132));
        rsNoSubtotal.setText("No");
        rsNoSubtotal.setColorCheck(new java.awt.Color(0, 90, 132));
        rsNoSubtotal.setColorUnCheck(new java.awt.Color(0, 90, 132));
        rsNoSubtotal.setRippleColor(new java.awt.Color(0, 90, 132));

        grupoSubtotal.add(rsSiSubtotal);
        rsSiSubtotal.setForeground(new java.awt.Color(0, 90, 132));
        rsSiSubtotal.setText("Si");
        rsSiSubtotal.setColorCheck(new java.awt.Color(0, 90, 132));
        rsSiSubtotal.setColorUnCheck(new java.awt.Color(0, 90, 132));
        rsSiSubtotal.setRippleColor(new java.awt.Color(0, 90, 132));

        grupoTieneCategorizacion.add(rsNoCategorizacion);
        rsNoCategorizacion.setForeground(new java.awt.Color(0, 90, 132));
        rsNoCategorizacion.setText("No");
        rsNoCategorizacion.setColorCheck(new java.awt.Color(0, 90, 132));
        rsNoCategorizacion.setColorUnCheck(new java.awt.Color(0, 90, 132));
        rsNoCategorizacion.setRippleColor(new java.awt.Color(0, 90, 132));

        grupoTieneCategorizacion.add(rsSiCategorizacion);
        rsSiCategorizacion.setForeground(new java.awt.Color(0, 90, 132));
        rsSiCategorizacion.setText("Si");
        rsSiCategorizacion.setColorCheck(new java.awt.Color(0, 90, 132));
        rsSiCategorizacion.setColorUnCheck(new java.awt.Color(0, 90, 132));
        rsSiCategorizacion.setRippleColor(new java.awt.Color(0, 90, 132));

        grupoTipoDeFacturacion.add(rsColegio);
        rsColegio.setForeground(new java.awt.Color(0, 90, 132));
        rsColegio.setText("Colegio");
        rsColegio.setColorCheck(new java.awt.Color(0, 90, 132));
        rsColegio.setColorUnCheck(new java.awt.Color(0, 90, 132));
        rsColegio.setRippleColor(new java.awt.Color(0, 90, 132));

        grupoTipoDeFacturacion.add(rsDirecta);
        rsDirecta.setForeground(new java.awt.Color(0, 90, 132));
        rsDirecta.setText("Directa");
        rsDirecta.setColorCheck(new java.awt.Color(0, 90, 132));
        rsDirecta.setColorUnCheck(new java.awt.Color(0, 90, 132));
        rsDirecta.setRippleColor(new java.awt.Color(0, 90, 132));

        grupoImprimeDobleInforme.add(rsSelectivo);
        rsSelectivo.setForeground(new java.awt.Color(0, 90, 132));
        rsSelectivo.setText("Selectivo");
        rsSelectivo.setColorCheck(new java.awt.Color(0, 90, 132));
        rsSelectivo.setColorUnCheck(new java.awt.Color(0, 90, 132));
        rsSelectivo.setRippleColor(new java.awt.Color(0, 90, 132));

        grupoD998.add(rsDiscrimina);
        rsDiscrimina.setForeground(new java.awt.Color(0, 90, 132));
        rsDiscrimina.setText("Discrimina");
        rsDiscrimina.setColorCheck(new java.awt.Color(0, 90, 132));
        rsDiscrimina.setColorUnCheck(new java.awt.Color(0, 90, 132));
        rsDiscrimina.setRippleColor(new java.awt.Color(0, 90, 132));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
                jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel25)
                                        .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel32, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel33, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel34, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel35, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel36, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel37, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel38, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(rsDirecta, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(rsSiCategorizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(rsSiSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(rsSiAltaComplejidad, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(rsSiFacturaNoNomenclados, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(rsSiFacturaPorPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(rsNunca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(rsCupon, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel7Layout.createSequentialGroup()
                                                .addComponent(rsIncluye, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(rsPacienteCompleto, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(rsColegio, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                .addComponent(rsNoCategorizacion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                                                                .addComponent(rsNoSubtotal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                                                        .addComponent(rsNoFacturaPorPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                .addComponent(rsNoFacturaNoNomenclados, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                                                                .addComponent(rsNoAltaComplejidad, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                                        .addGroup(jPanel7Layout.createSequentialGroup()
                                                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(rsNoIncluye, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(rsSiempre, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(rsDiscrimina, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                                                                        .addComponent(rsSelectivo, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))))))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
                jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel7Layout.createSequentialGroup()
                                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel25)
                                                        .addComponent(rsSiAltaComplejidad, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(19, 19, 19)
                                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel26)
                                                        .addComponent(rsSiFacturaNoNomenclados, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(19, 19, 19)
                                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel32)
                                                        .addComponent(rsCupon, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(19, 19, 19)
                                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel33)
                                                        .addComponent(rsSiFacturaPorPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(jPanel7Layout.createSequentialGroup()
                                                .addComponent(rsNoAltaComplejidad, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(19, 19, 19)
                                                .addComponent(rsNoFacturaNoNomenclados, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(19, 19, 19)
                                                .addComponent(rsPacienteCompleto, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(19, 19, 19)
                                                .addComponent(rsNoFacturaPorPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(19, 19, 19)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel34)
                                        .addComponent(rsNunca, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(rsSiempre, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(rsSelectivo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(19, 19, 19)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel35)
                                        .addComponent(rsIncluye, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(rsNoIncluye, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(rsDiscrimina, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(19, 19, 19)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel36)
                                        .addComponent(rsSiSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(rsNoSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(19, 19, 19)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel37)
                                        .addComponent(rsSiCategorizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(rsNoCategorizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(19, 19, 19)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel38)
                                        .addComponent(rsDirecta, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(rsColegio, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Tipo IVA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13), new java.awt.Color(0, 90, 132))); // NOI18N
        jPanel8.setForeground(new java.awt.Color(0, 90, 132));

        cboTipoIVA.setForeground(new java.awt.Color(0, 90, 132));
        cboTipoIVA.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Exento", "10.5 %", "21 %", "Responsable Inscripto"}));
        cboTipoIVA.setColorArrow(new java.awt.Color(0, 90, 132));
        cboTipoIVA.setColorBorde(new java.awt.Color(255, 255, 255));
        cboTipoIVA.setColorBoton(new java.awt.Color(255, 255, 255));
        cboTipoIVA.setColorDisabledIndex(new java.awt.Color(0, 90, 132));
        cboTipoIVA.setColorDisabledIndexText(new java.awt.Color(0, 90, 132));
        cboTipoIVA.setColorFondo(new java.awt.Color(255, 255, 255));
        cboTipoIVA.setColorListaItemsTXT(new java.awt.Color(0, 90, 132));
        cboTipoIVA.setColorSeleccion(new java.awt.Color(0, 90, 132));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
                jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(cboTipoIVA, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
                                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
                jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel8Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(cboTipoIVA, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Tipo de facturación", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13), new java.awt.Color(0, 90, 132))); // NOI18N
        jPanel9.setForeground(new java.awt.Color(0, 90, 132));

        cboTipoIVA1.setForeground(new java.awt.Color(0, 90, 132));
        cboTipoIVA1.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Importes", "NBU", "Convenio", "CONV+DCTO", "S/Detalle"}));
        cboTipoIVA1.setColorArrow(new java.awt.Color(0, 90, 132));
        cboTipoIVA1.setColorBorde(new java.awt.Color(255, 255, 255));
        cboTipoIVA1.setColorBoton(new java.awt.Color(255, 255, 255));
        cboTipoIVA1.setColorDisabledIndex(new java.awt.Color(0, 90, 132));
        cboTipoIVA1.setColorDisabledIndexText(new java.awt.Color(0, 90, 132));
        cboTipoIVA1.setColorFondo(new java.awt.Color(255, 255, 255));
        cboTipoIVA1.setColorListaItemsTXT(new java.awt.Color(0, 90, 132));
        cboTipoIVA1.setColorSeleccion(new java.awt.Color(0, 90, 132));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
                jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(cboTipoIVA1, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
                jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel9Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(cboTipoIVA1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addGap(26, 26, 26))
                                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                                                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addGap(18, 18, 18)
                                                                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(5, 5, 5)
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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


    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed

        dispose();

    }//GEN-LAST:event_btnCerrarActionPerformed

    private void jPanel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MousePressed

        x = evt.getX();
        y = evt.getY();

    }//GEN-LAST:event_jPanel1MousePressed

    private void jPanel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseDragged

        this.setLocation(this.getLocation().x + evt.getX() - x, this.getLocation().y + evt.getY() - y);

    }//GEN-LAST:event_jPanel1MouseDragged

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed

        dispose();

    }//GEN-LAST:event_btnSalirActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private RSMaterialComponent.RSButtonMaterialIconOne btnAgregar;
    private RSMaterialComponent.RSButtonMaterialIconOne btnCancelar;
    private RSMaterialComponent.RSButtonIconOne btnCerrar;
    private RSMaterialComponent.RSButtonMaterialIconOne btnModificar;
    private RSMaterialComponent.RSButtonMaterialIconOne btnSalir;
    private RSMaterialComponent.RSComboBox cboNBU;
    private RSMaterialComponent.RSComboBox cboTipoIVA;
    private RSMaterialComponent.RSComboBox cboTipoIVA1;
    private newscomponents.RSDateChooser dcFechaAlta;
    private javax.swing.ButtonGroup grupoAltaComplejidad;
    private javax.swing.ButtonGroup grupoD998;
    private javax.swing.ButtonGroup grupoFacturaPor;
    private javax.swing.ButtonGroup grupoFacturaPorPaciente;
    private javax.swing.ButtonGroup grupoImprimeDobleInforme;
    private javax.swing.ButtonGroup grupoNoNomenclados;
    private javax.swing.ButtonGroup grupoSubtotal;
    private javax.swing.ButtonGroup grupoTieneCategorizacion;
    private javax.swing.ButtonGroup grupoTipoDeFacturacion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private RSMaterialComponent.RSRadioButtonMaterial rsColegio;
    private RSMaterialComponent.RSRadioButtonMaterial rsCupon;
    private RSMaterialComponent.RSRadioButtonMaterial rsDirecta;
    private RSMaterialComponent.RSRadioButtonMaterial rsDiscrimina;
    private RSMaterialComponent.RSRadioButtonMaterial rsIncluye;
    private RSMaterialComponent.RSRadioButtonMaterial rsNoAltaComplejidad;
    private RSMaterialComponent.RSRadioButtonMaterial rsNoCategorizacion;
    private RSMaterialComponent.RSRadioButtonMaterial rsNoFacturaNoNomenclados;
    private RSMaterialComponent.RSRadioButtonMaterial rsNoFacturaPorPaciente;
    private RSMaterialComponent.RSRadioButtonMaterial rsNoIncluye;
    private RSMaterialComponent.RSRadioButtonMaterial rsNoSubtotal;
    private RSMaterialComponent.RSRadioButtonMaterial rsNunca;
    private RSMaterialComponent.RSRadioButtonMaterial rsPacienteCompleto;
    private RSMaterialComponent.RSRadioButtonMaterial rsSelectivo;
    private RSMaterialComponent.RSRadioButtonMaterial rsSiAltaComplejidad;
    private RSMaterialComponent.RSRadioButtonMaterial rsSiCategorizacion;
    private RSMaterialComponent.RSRadioButtonMaterial rsSiFacturaNoNomenclados;
    private RSMaterialComponent.RSRadioButtonMaterial rsSiFacturaPorPaciente;
    private RSMaterialComponent.RSRadioButtonMaterial rsSiSubtotal;
    private RSMaterialComponent.RSRadioButtonMaterial rsSiempre;
    private RSMaterialComponent.RSTableMetroCustom tablaObrasSociales;
    private RSMaterialComponent.RSTextFieldMaterial txtCodigoFacturacion;
    private RSMaterialComponent.RSTextFieldMaterial txtCodigoOs;
    private RSMaterialComponent.RSTextFieldMaterial txtCodigoOs1;
    private RSMaterialComponent.RSTextFieldMaterial txtCodigoPostal;
    private RSMaterialComponent.RSTextFieldMaterial txtContrato;
    private RSMaterialComponent.RSTextFieldMaterial txtDireccion;
    private RSMaterialComponent.RSTextFieldMaterial txtEmail;
    private RSMaterialComponent.RSTextFieldMaterial txtLocalidad;
    private RSMaterialComponent.RSTextFieldMaterial txtPaginaWeb;
    private RSMaterialComponent.RSTextFieldMaterial txtPorcentajeAfiliado;
    private RSMaterialComponent.RSTextFieldMaterial txtProvincia;
    private RSMaterialComponent.RSTextFieldMaterial txtRazonSocial;
    private RSMaterialComponent.RSTextFieldMaterial txtReferente;
    private RSMaterialComponent.RSTextFieldMaterial txtTelefono;
    private RSMaterialComponent.RSTextFieldMaterial txtTelefonoReferente;
    private RSMaterialComponent.RSTextFieldMaterial txtUnidadArancel;
    // End of variables declaration//GEN-END:variables
}
