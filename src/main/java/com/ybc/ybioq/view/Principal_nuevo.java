package com.ybc.ybioq.view;

import com.mxrck.autocompleter.TextAutoCompleter;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class Principal_nuevo extends javax.swing.JFrame {

    private int x;
    private int y;

    public String[] descripcion = new String[100000];
    public static String direccion, lugar, fecha, fecha2, periodo;
    int id_historia_clinica = 0, id_localidad = 2358, id_orden, total = 0;
    DefaultTableModel model, model3, modelPaciente, modelPracticas;
    DefaultTableCellRenderer alinearCentro, alinearDerecha, alinearIzquierda;
    HiloBusquedaPacientes hiloPacientes;
    String hora = "", hora2 = "", fechaonline = "";

    public static String medico = "";
    public static int idobraimprime = 0;

    TextAutoCompleter textAutoAcompleter;
    TextAutoCompleter textAutoAcompletermatricula2;
    TextAutoCompleter textAutoAcompleter2;
    TextAutoCompleter textAutoAcompletertipo;

    public static double totalParticular;

    String ip = "";


    public Principal_nuevo() {

        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        panelCargarPaciente.setVisible(true);
        panelCargarOrden.setVisible(false);
        panelFacturacion.setVisible(false);
        panelUtilidades.setVisible(false);
        limpiarDatosPaciente();
        textAutoAcompleter = new TextAutoCompleter(txtBuscarPractica);
        textAutoAcompletermatricula2 = new TextAutoCompleter(txtMedico);
        textAutoAcompleter2 = new TextAutoCompleter(txtObraSocial);
        textAutoAcompletertipo = new TextAutoCompleter(txtMotivo);
        textAutoAcompletertipo.addItem("Clínico.");
        textAutoAcompletertipo.addItem("Embarazada.");
        textAutoAcompletertipo.addItem("Prelaborales.");
        textAutoAcompletertipo.addItem("Recíen Nacidos.");
        textAutoAcompletertipo.addItem("Transplantes.");
        textAutoAcompletertipo.addItem("Otros.");
        textAutoAcompletertipo.addItem("Indistinto.");
        textAutoAcompletertipo.addItem("VIH.");
        textAutoAcompletertipo.addItem("S/D.");
        textAutoAcompletertipo.setMode(0);
        textAutoAcompletertipo.setCaseSensitive(false);
        btnPatologias.setEnabled(false);
        alinear();
        empleados();
        cargarperiodo();
        //cargarobrasocial();
        //cargarorden();
        //cargartablapacientes();
        unclick();
        dobleclickordenes();
        dobleclick();
        //cargarlocalidad();
        btnCargarConsulta.setEnabled(false);
        //cargarmatricula();
        cboSexo.setSelectedIndex(0);
        unclickhistoriaclinica();
        //cargarhistoriaclinica();
        //cargarfechafacturacion();
        cargarhora();
        cargarip();

        txtApellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
                Character c = evt.getKeyChar();
                if (Character.isLetter(c)) {
                    evt.setKeyChar(Character.toUpperCase(c));
                }
            }
        });

        txtDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
                Character c = evt.getKeyChar();
                if (Character.isLetter(c)) {
                    evt.setKeyChar(Character.toUpperCase(c));
                }
            }
        });
        lblInstrucciones.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
                Character c = evt.getKeyChar();
                if (Character.isLetter(c)) {
                    evt.setKeyChar(Character.toUpperCase(c));
                }
            }
        });

        txtMedico.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
                Character c = evt.getKeyChar();
                if (Character.isLetter(c)) {
                    evt.setKeyChar(Character.toUpperCase(c));
                }
            }
        });
        txtMail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
                Character c = evt.getKeyChar();
                if (Character.isLetter(c)) {
                    evt.setKeyChar(Character.toLowerCase(c));
                }
            }
        });
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
                Character c = evt.getKeyChar();
                if (Character.isLetter(c)) {
                    evt.setKeyChar(Character.toUpperCase(c));
                }
            }
        });
        lblPaciente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
                Character c = evt.getKeyChar();
                if (Character.isLetter(c)) {
                    evt.setKeyChar(Character.toUpperCase(c));
                }
            }
        });
        txtObraSocial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
                Character c = evt.getKeyChar();
                if (Character.isLetter(c)) {
                    evt.setKeyChar(Character.toUpperCase(c));
                }
            }
        });

        lblNumeroProtocolo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
                Character c = evt.getKeyChar();
                if (Character.isLetter(c)) {
                    evt.setKeyChar(Character.toUpperCase(c));
                }
            }
        });
        txtRecienNacido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
                Character c = evt.getKeyChar();
                if (Character.isLetter(c)) {
                    evt.setKeyChar(Character.toUpperCase(c));
                }
            }
        });
        txtMotivo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
                Character c = evt.getKeyChar();
                if (Character.isLetter(c)) {
                    evt.setKeyChar(Character.toUpperCase(c));
                }
            }
        });
        txtDNI.requestFocus();

    }

    void alinear() {
        alinearCentro = new DefaultTableCellRenderer();
        alinearCentro.setHorizontalAlignment(SwingConstants.CENTER);
        alinearDerecha = new DefaultTableCellRenderer();
        alinearDerecha.setHorizontalAlignment(SwingConstants.RIGHT);
        alinearIzquierda = new DefaultTableCellRenderer();
        alinearIzquierda.setHorizontalAlignment(SwingConstants.LEFT);
    }

    void cargarip() {
        try {
            String thisIp = InetAddress.getLocalHost().getHostAddress();
            String thisname = InetAddress.getLocalHost().getHostName();
            ip = thisIp + "-" + thisname;
        } catch (UnknownHostException e) {
        }
    }

    void cargarhora() {
        SimpleDateFormat formatoTiempo2 = new SimpleDateFormat("HHmmss");
        SimpleDateFormat formatoTiempo = new SimpleDateFormat("HH:mm:ss");
        Date currentDate1 = new Date();
        GregorianCalendar calendar1 = new GregorianCalendar();
        calendar1.setTime(currentDate1);
        hora = formatoTiempo.format(currentDate1);
        hora2 = formatoTiempo2.format(currentDate1);
    }

    void cargarfechafacturacion() {

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date currentDate = new Date();
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(currentDate);
        fecha = formato.format(currentDate);
        //fechainicial.setText(fecha);
        //fechafinal.setText(fecha);
        //txtfechaPaciente.setText(fecha);
        //txtfechaPaciente2.setText(fecha);
    }

    void cargarhistoriaclinica() {

//        String sSQL = "SELECT MAX(idhistoria_clinica) AS idhistoria_clinica FROM historia_clinica";
//
//            rs.last();
//            if (rs.getInt("idhistoria_clinica") != 0) {
//                id_historia_clinica = rs.getInt("idhistoria_clinica") + 1;
//            } else {
//                id_historia_clinica = 1;
//            }
//
//        lblNumeroProtocolo.setText(completarceros(String.valueOf(id_historia_clinica), 8));
    }

    void unclickhistoriaclinica() {
        tablaPacientes.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    int i = tablaPacientes.getSelectedRow();
                    //txtDescripcion.setText(" Observaciones: " + descripcion[i]);
                }
            }
        });
    }

    String completarceros(String v, int d) {
        if (v.length() < d) {
            String ceros = "";
            for (int i = v.length(); i < d; i++) {
                ceros += "0";
            }
            v = ceros + v;
        }
        return v;
    }

    void cargarmatricula() {
//        textAutoAcompletermatricula2.removeAllItems();
//        int i = 0;
//        String sSQL = "SELECT medicos.id_medicos,medicos.nombre, medicos.apellido, medicos_tienen_especialidades.matricula FROM medicos INNER JOIN medicos_tienen_especialidades ON medicos_tienen_especialidades.id_medicos = medicos.id_medicos where estado=1";
//
//            while (rs.next()) {
//                idmedico[contadormedico] = rs.getInt(1);
//                matriculamedico[contadormedico] = (rs.getInt(4));
//                nombremedico[contadormedico] = (rs.getString(3) + " " + rs.getString(2));
//                textAutoAcompletermatricula2.addItem(matriculamedico[i] + "-" + nombremedico[i]);
//                contadormedico++;
//                i++;
//            }
//        textAutoAcompletermatricula2.setMode(0);
//        textAutoAcompletermatricula2.setCaseSensitive(false);

    }

    void cargarlocalidad() {

//        TextAutoCompleter textAutoAcompleter3 = new TextAutoCompleter(txtLocalidad);
//        int i = 0;
//        while (i < contadorlocalidad) {
//            textAutoAcompleter3.addItem(nombrelocalidad[i]);
//            i++;
//        }
//        textAutoAcompleter3.setMode(0);
//        textAutoAcompleter3.setCaseSensitive(false);
    }

    void dobleclickordenes() {
//        tablaOrdenes.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                if (e.getClickCount() == 2) {
//                    System.out.println(tablaOrdenes.getValueAt(tablaOrdenes.getSelectedRow(), 12).toString());
//                    DetallePracticasOrden_nuevo.id_orden = tablaOrdenes.getValueAt(tablaOrdenes.getSelectedRow(), 0).toString();
//                    DetallePracticasOrden_nuevo.historia_clinica = tablaOrdenes.getValueAt(tablaOrdenes.getSelectedRow(), 12).toString();
//                    DetallePracticasOrden_nuevo.afiliado = tablaOrdenes.getValueAt(tablaOrdenes.getSelectedRow(), 3).toString();
//                    DetallePracticasOrden_nuevo.servicio = tablaOrdenes.getValueAt(tablaOrdenes.getSelectedRow(), 2).toString();
//                    DetallePracticasOrden_nuevo.fecha = tablaOrdenes.getValueAt(tablaOrdenes.getSelectedRow(), 5).toString();
//                    DetallePracticasOrden_nuevo.total = tablaOrdenes.getValueAt(tablaOrdenes.getSelectedRow(), 6).toString();
//                    new DetallePracticasOrden_nuevo(null, true).setVisible(true);
//                }
//            }
//        });
    }

    void dobleclick() {
//        tablaPacientes.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                if (e.getClickCount() == 2) {
//                    DetallePracticas_nuevo.id_ordenes = tablaPacientes.getValueAt(tablaPacientes.getSelectedRow(), 12).toString();
//                    DetallePracticas_nuevo.historia_clinica = tablaPacientes.getValueAt(tablaPacientes.getSelectedRow(), 0).toString();
//                    DetallePracticas_nuevo.afiliado = tablaPacientes.getValueAt(tablaPacientes.getSelectedRow(), 1).toString() + " " + tablaPacientes.getValueAt(tablaPacientes.getSelectedRow(), 2).toString();
//                    DetallePracticas_nuevo.servicio = tablaPacientes.getValueAt(tablaPacientes.getSelectedRow(), 9).toString();
//                    DetallePracticas_nuevo.fecha = tablaPacientes.getValueAt(tablaPacientes.getSelectedRow(), 5).toString();
//                    DetallePracticas_nuevo.num_orden = tablaPacientes.getValueAt(tablaPacientes.getSelectedRow(), 0).toString();
//                    DetallePracticas_nuevo.hora = tablaPacientes.getValueAt(tablaPacientes.getSelectedRow(), 11).toString();
//                    new DetallePracticas_nuevo(null, true).setVisible(true);
//                }
//            }
//        });
    }


    void unclick() {
        tablaPracticas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    cargatotalespracticas();
                    CargaTotalesPrecios();
                }
            }
        });
    }

    void cargatotalespracticas() {

        double total2 = 0.00, total3 = 0.0, sumatoria2, totalPracticas = 0.00, sumatoria, sumatoria3;

        int totalRowPracticas = tablaPracticas.getRowCount(), aux = 0;
        totalRowPracticas -= 1;
        for (int i = 0; i <= (totalRowPracticas); i++) {
            String x1 = "0", x2 = "0", x3;
            if (tablaPracticas.getValueAt(i, 8).toString().equals("true")) {
                x1 = tablaPracticas.getValueAt(i, 4).toString();///suma por Obra Social                        
            }
            if (tablaPracticas.getValueAt(i, 8).toString().equals("false")) {
                x2 = tablaPracticas.getValueAt(i, 5).toString();/// suma por particular                
            }
            if (aux < Integer.parseInt(tablaPracticas.getValueAt(i, 10).toString())) {
                aux = Integer.parseInt(tablaPracticas.getValueAt(i, 10).toString());
            }
            x3 = tablaPracticas.getValueAt(i, 7).toString();///suma por costo

            sumatoria = Double.parseDouble(x1);
            sumatoria2 = Double.parseDouble(x2);
            sumatoria3 = Double.parseDouble(x3);

            totalPracticas = totalPracticas + sumatoria;
            total2 = total2 + sumatoria2;
            total3 = total3 + sumatoria3;
        }
        lblTotalParticular.setText((String.valueOf(Redondear(total2))));
        lblTotalOS.setText((String.valueOf(Redondear(totalPracticas))));
        lblTotal.setText((String.valueOf(Redondear(totalPracticas))));

    }

    void CargaTotalesPrecios() {
        double totalOS, totalSeña, sumatoria;
        totalParticular = 0.0;

        if (lblTotalOS.getText().isEmpty()) {
            totalOS = 0;
        } else {
            totalOS = Double.parseDouble(lblTotalOS.getText());
        }

        if (lblTotalParticular.getText().isEmpty()) {
            totalParticular = 0;
        } else {
            totalParticular = Double.parseDouble(lblTotalParticular.getText());
        }

        if (lblSeña.getText().isEmpty()) {
            totalSeña = 0;
        } else {
            totalSeña = Double.parseDouble(lblSeña.getText());
        }
        sumatoria = totalOS + totalParticular - totalSeña;
        System.out.println("sumatoria = totalOS + totalParticular - totalSeña;" + sumatoria + " " + totalOS + " " + totalParticular + " " + totalSeña);
        lblTotal.setText((String.valueOf(Redondear(sumatoria))));
    }

    void cargartablapacientes() {
//        borrartablapacientes();
//        iniciarSplash();//
//        hiloPacientes = new HiloBusquedaPacientes(progresoOrdenes);
//        hiloPacientes.start();
//        hiloPacientes = null;
    }

    public void iniciarSplash() {
        progresoOrdenes.setBorderPainted(false);
        progresoOrdenes.setForeground(new Color(100, 100, 100, 100));
        //[77,239,38]
        progresoOrdenes.setStringPainted(true);
    }

    void cargarorden() {
//        String sSQL = "SELECT MAX(id_ordenes) AS id_ordenes FROM ordenes";
//
//        rs.last();
//        if (rs.getInt("id_ordenes") != 0) {
//            id_orden = rs.getInt("id_ordenes") + 1;
//        } else {
//            id_orden = 1;
//        }
    }

    void borrartablapacientes() {
        DefaultTableModel temp = (DefaultTableModel) tablaPacientes.getModel();
        int a = temp.getRowCount() - 1;  //Índices van de 0 a n-1
        for (int i = a; i >= 0; i--) {
            temp.removeRow(i);
        }
    }

    void cargarobrasocial() {
//        int i = 0;
//        textAutoAcompleter2.removeAllItems();
//        while (i < contadorobrasocial) {
//            textAutoAcompleter2.addItem(obrasocial[i]);
//            i++;
//        }
//        textAutoAcompleter2.setMode(0);
//        textAutoAcompleter2.setCaseSensitive(false);
    }

    void cargarperiodo() {
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat formato2 = new SimpleDateFormat("d' de 'MMMM' de 'yyyy");
        SimpleDateFormat formato3 = new SimpleDateFormat("yyyyMMdd");

        Date currentDate = new Date();
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(currentDate);
        fecha = formato.format(currentDate);
        fecha2 = formato2.format(currentDate);
        fechaonline = formato3.format(currentDate);
        lblFecha.setText(fecha);
        lblHora.setText(hora);
        String año2 = "", mes2 = "", dia2 = "";
        calendar.setTime(currentDate);

        int i;
        for (i = 3; i <= 4; i++) {
            mes2 = mes2 + fecha.charAt(i);
        }
        for (i = 6; i <= 9; i++) {
            año2 = año2 + fecha.charAt(i);
        }
        for (i = 8; i <= 9; i++) {
            dia2 = dia2 + fecha.charAt(i);
        }
        periodo = año2 + mes2;
    }

    void empleados() {

//        if (Login_nuevo.datos == false) {
//
//        }
//
//        if (Login_nuevo.cbt == false) {
//
//        }
//
//        if (Login_nuevo.informes == false) {
//
//        }
//
//        if (Login_nuevo.facturacion == false) {
//
//        }
    }

    void limpiarDatosPaciente() {
        txtDNI.setText("");
        txtApellido.setText("");
        txtNombre.setText("");
        cboSexo.setSelectedIndex(0);
        txtLocalidad.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        txtCelular.setText("");
        txtMail.setText("");
        id_localidad = 2358;
        lblEdad.setText("");
        txtObraSocial.setText("");
        txtNumeroAfiliado.setText("");
        txtDNI.requestFocus();
    }

    public class HiloOrdenes extends Thread {

//        JProgressBar progreso;
//
//        public HiloOrdenes(JProgressBar progreso1) {
//            super();
//            this.progreso = progreso1;
//        }
//
//        @Override
//        public void run() {
//            model3 = (DefaultTableModel) tablaOrdenes.getModel();
//            double total = 0.0;
//            try {
//                if (chkObrasocial.isSelected()) {
//                    System.out.println("Es seleccionado");
//                    Rs1 = St1.executeQuery("SELECT * FROM vista_ordenes WHERE Date(fecha) BETWEEN '" + dcFechaDesde.getDate() + "'" + " AND '" + dcFechaHasta.getDate() + "' AND id_obrasocial=" + idobraimprime);
//
//                } else {
//                    Rs1 = St1.executeQuery("SELECT * FROM vista_ordenes WHERE Date(fecha) BETWEEN '" + dcFechaDesde.getDate() + "'" + " AND '" + dcFechaHasta.getDate() + "'");
//                }
//                String estado;
//                int fila = 0;
//                while (Rs1.next()) {
//                    if (Rs1.getInt(13) == 0) {
//                        estado = "OK";
//                    } else {
//                        estado = "Anulada";
//                    }
//                    Object nuevo[] = {
//                            Rs1.getString(1),
//                            Rs1.getString(2),
//                            Rs1.getString(3) + " " + Rs1.getString(12),
//                            Rs1.getString(4) + " " + Rs1.getString(11),
//                            Rs1.getString(5),
//                            Rs1.getString(6),
//                            Rs1.getString(7),
//                            true,
//                            Rs1.getString(8),
//                            Rs1.getString(9),
//                            Rs1.getString(10),
//                            estado,
//                            Rs1.getInt(14)};
//                    model3.addRow(nuevo);
//                    tablaOrdenes.setValueAt(true, fila, 7);
//                    fila++;
//                }
//                tablaOrdenes.setModel(model3);
//                tablaOrdenes.getColumnModel().getColumn(1).setMaxWidth(0);
//                tablaOrdenes.getColumnModel().getColumn(1).setMinWidth(0);
//                tablaOrdenes.getColumnModel().getColumn(1).setPreferredWidth(0);
//                tablaOrdenes.getColumnModel().getColumn(8).setMaxWidth(0);
//                tablaOrdenes.getColumnModel().getColumn(8).setMinWidth(0);
//                tablaOrdenes.getColumnModel().getColumn(8).setPreferredWidth(0);
//                tablaOrdenes.getColumnModel().getColumn(9).setMaxWidth(0);
//                tablaOrdenes.getColumnModel().getColumn(9).setMinWidth(0);
//                tablaOrdenes.getColumnModel().getColumn(9).setPreferredWidth(0);
//                tablaOrdenes.getColumnModel().getColumn(10).setMaxWidth(0);
//                tablaOrdenes.getColumnModel().getColumn(10).setMinWidth(0);
//                tablaOrdenes.getColumnModel().getColumn(10).setPreferredWidth(0);
//                tablaOrdenes.getColumnModel().getColumn(12).setMaxWidth(0);
//                tablaOrdenes.getColumnModel().getColumn(12).setMinWidth(0);
//                tablaOrdenes.getColumnModel().getColumn(12).setPreferredWidth(0);
//                tablaOrdenes.getColumnModel().getColumn(0).setPreferredWidth(30);
//                tablaOrdenes.getColumnModel().getColumn(2).setPreferredWidth(200);
//                tablaOrdenes.getColumnModel().getColumn(3).setPreferredWidth(200);
//                tablaOrdenes.getColumnModel().getColumn(4).setPreferredWidth(50);
//                tablaOrdenes.getColumnModel().getColumn(5).setPreferredWidth(60);
//                tablaOrdenes.getColumnModel().getColumn(6).setPreferredWidth(40);
//                tablaOrdenes.getColumnModel().getColumn(7).setPreferredWidth(40);
//                tablaOrdenes.getColumnModel().getColumn(11).setPreferredWidth(60);
//                progreso.setValue(100);
//                txtBuscarOrden.requestFocus();
//                txtBuscarOrden.setEnabled(true);
//                cargatotalesordenesfacturacion();
//            } catch (SQLException ex) {
//                JOptionPane.showMessageDialog(null, ex);
//
//            }
//            String[] titulos2 = {"N° Orden", "cod_practica", "1", "2", "3", "4"};
//            String[] datos2 = new String[6];
//            model = new DefaultTableModel(null, titulos2) {
//                public boolean isCellEditable(int row, int column) {
//                    return false;
//                }
//            };
//            int i = 0;
//            while (i < tablaOrdenes.getRowCount()) {
//
//                String sql = "SELECT ordenes.id_ordenes, practicas.codigo_practica, practicas.determinacion_practica,precio_practica,cod_practica_fac FROM ordenes INNER JOIN ordenes_tienen_practicas ON ordenes_tienen_practicas.id_ordenes=ordenes.id_ordenes INNER JOIN practicas ON practicas.id_practicas=ordenes_tienen_practicas.id_practicas WHERE ordenes.estado_orden=0 and ordenes_tienen_practicas.factura!=0 and fecha BETWEEN '" + dcFechaDesde.getDate() + "'" + " AND '" + dcFechaHasta.getDate() + "' AND id_obrasocial=" + tablaOrdenes.getValueAt(i, 10).toString();
//
//                while (Rs.next()) {
//                    if (tablaOrdenes.getValueAt(i, 7).equals(true) && Rs.getString(1).equals(tablaOrdenes.getValueAt(i, 0).toString())) {
//                        datos2[0] = Rs.getString(1);///id orden
//                        datos2[1] = Rs.getString(2);////cod practica
//                        datos2[2] = Rs.getString(3);////determinacion
//                        datos2[3] = Rs.getString(4);////precio practica
//                        total = total + Rs.getDouble(4);
//                        datos2[4] = Rs.getString(5);////cod fac prac
//                        datos2[5] = "idorden" + Rs.getString(1);
//                        model.addRow(datos2);
//                    }
//                }
//                i++;
//            }
//            lblMontoTotalOrdenes.setText((String.valueOf(Redondear(total))));
//            tablaDetalle.setModel(model);
//            tablaDetalle.getColumnModel().getColumn(2).setMaxWidth(0);
//            tablaDetalle.getColumnModel().getColumn(2).setMinWidth(0);
//            tablaDetalle.getColumnModel().getColumn(2).setPreferredWidth(0);
//            tablaDetalle.getColumnModel().getColumn(3).setMaxWidth(0);
//            tablaDetalle.getColumnModel().getColumn(3).setMinWidth(0);
//            tablaDetalle.getColumnModel().getColumn(3).setPreferredWidth(0);
//            tablaDetalle.getColumnModel().getColumn(4).setMaxWidth(0);
//            tablaDetalle.getColumnModel().getColumn(4).setMinWidth(0);
//            tablaDetalle.getColumnModel().getColumn(4).setPreferredWidth(0);
//            tablaDetalle.getColumnModel().getColumn(5).setMaxWidth(0);
//            tablaDetalle.getColumnModel().getColumn(5).setMinWidth(0);
//            tablaDetalle.getColumnModel().getColumn(5).setPreferredWidth(0);
//
//
//            cargatotalesordenesfacturacion();
//        }
//
//        public void pausa(int mlSeg) {
//            try {
//                Thread.sleep(mlSeg);
//            } catch (InterruptedException e) {
//            }
//        }
    }

    public class HiloBusquedaPacientes extends Thread {

//        JProgressBar progreso;
//
//        public HiloBusquedaPacientes(JProgressBar progreso1) {
//            super();
//            this.progreso = progreso1;
//        }
//
//        public void run() {
//            String[] Titulo = {"N°", "Apellido", "Nombre", "DNI", "Edad", "Fecha Orden", "Seña", "Diag", "Tipo", "Servicio", "id_OS", "hora", "id", "Mail", "Celular"};
//            Object[] Registros = new Object[15];
//            double total = 0;
//            int i = 0;
//
//            String sql = "SELECT\n"
//                    + "  `historia_clinica`.`idhistoria_clinica`,\n"
//                    + "  `personas`.`apellido`,\n"
//                    + "  `personas`.`nombre`,\n"
//                    + "    `pacientes`.`personas_dni`,\n"
//                    + "  DATE_FORMAT(`pacientes`.`fecha_nacimiento`, '%d-%m-%Y') AS 'fecha_nacimiento',\n"
//                    + "    ((YEAR(CURDATE()) - YEAR(`pacientes`.`fecha_nacimiento`)) + IF((DATE_FORMAT(CURDATE(), '%m-%d') > DATE_FORMAT(`pacientes`.`fecha_nacimiento`, '%m-%d')), 0, -(1))) AS 'edad',\n"
//                    + "  DATE_FORMAT(`ordenes`.`fecha`, '%d-%m-%Y') AS 'fecha',\n"
//                    + "    `seña`.`seña`,\n"
//                    + "  `ordenes`.`observaciones`,\n"
//                    + "  `ordenes`.`tipo_orden`,\n"
//                    + "  `ordenes`.`servicio`,\n"
//                    + "  `historia_clinica`.`descripcion`,\n"
//                    + "  `ordenes`.`id_obrasocial`,\n"
//                    + "  CONCAT(SUBSTR(`ordenes`.`hora`,1, 2), ':', SUBSTR(`ordenes`.`hora`,3, 2), ':', SUBSTR(`ordenes`.`hora`,4, 2)) AS 'hora',\n"
//                    + "  `ordenes`.`id_ordenes`,\n"
//                    + "    `pacientes`.`mail`  as mail,\n"
//                    + "        `pacientes`.`celular` as celular\n"
//                    + "FROM\n"
//                    + "  ((((`ordenes`\n"
//                    + "    JOIN `pacientes` ON ((`pacientes`.`id_Pacientes` = `ordenes`.`id_Pacientes`)))\n"
//                    + "    JOIN `personas` ON ((`personas`.`dni` = `pacientes`.`personas_dni`)))\n"
//                    + "    JOIN `historia_clinica` ON ((`historia_clinica`.`id_ordenes` = `ordenes`.`id_ordenes`)))\n"
//                    + "    LEFT JOIN `seña` ON ((`historia_clinica`.`idhistoria_clinica` = `seña`.`idhistoria_clinica`)))\n"
//                    + "WHERE\n"
//                    + "  (`ordenes`.`estado_orden` = 0) and (pacientes.estado=1) and Date(fecha) BETWEEN DATE_SUB(NOW(),INTERVAL 60 DAY)  AND curdate()\n"
//                    + "GROUP BY\n"
//                    + "  `historia_clinica`.`idhistoria_clinica`\n"
//                    + "ORDER BY `ordenes`.`id_ordenes`";
//
//            modelPaciente = new DefaultTableModel(null, Titulo) {
//                ////Celdas no editables////////
//                public boolean isCellEditable(int row, int column) {
//                    return false;
//                }
//            };
//
//            while (rs.next()) {
//                total = 0;
//                Registros[0] = rs.getString("idhistoria_clinica");
//                Registros[1] = rs.getString("apellido");
//                Registros[2] = rs.getString("nombre");
//                Registros[3] = rs.getString("personas_dni");
//                Registros[4] = rs.getString("edad");
//                Registros[5] = rs.getString("fecha");
//                Registros[6] = "$  " + rs.getDouble("seña");
//                Registros[7] = rs.getString("observaciones");
//                Registros[8] = rs.getString("tipo_orden");
//                Registros[9] = rs.getString("servicio");
//                descripcion[i] = rs.getString("descripcion");
//                Registros[10] = rs.getString("id_obrasocial");
//                Registros[11] = rs.getString("hora");
//                Registros[12] = rs.getString("id_ordenes");
//                Registros[13] = rs.getString("mail");
//                Registros[14] = rs.getString("celular");
//
//                modelPaciente.addRow(Registros);
//                i++;
//            }
//            tablaPacientes.setModel(modelPaciente);
//            tablaPacientes.setAutoCreateRowSorter(true);
//            tablaPacientes.getColumnModel().getColumn(0).setMaxWidth(90);
//            tablaPacientes.getColumnModel().getColumn(0).setMinWidth(90);
//            tablaPacientes.getColumnModel().getColumn(0).setPreferredWidth(90);
//            tablaPacientes.getColumnModel().getColumn(1).setMinWidth(150);
//            tablaPacientes.getColumnModel().getColumn(1).setPreferredWidth(150);
//            tablaPacientes.getColumnModel().getColumn(2).setMaxWidth(150);
//            tablaPacientes.getColumnModel().getColumn(2).setMinWidth(150);
//            tablaPacientes.getColumnModel().getColumn(2).setPreferredWidth(150);
//            tablaPacientes.getColumnModel().getColumn(3).setMaxWidth(95);
//            tablaPacientes.getColumnModel().getColumn(3).setMinWidth(95);
//            tablaPacientes.getColumnModel().getColumn(3).setPreferredWidth(95);
//            tablaPacientes.getColumnModel().getColumn(4).setMaxWidth(60);
//            tablaPacientes.getColumnModel().getColumn(4).setMinWidth(60);
//            tablaPacientes.getColumnModel().getColumn(4).setPreferredWidth(60);
//            tablaPacientes.getColumnModel().getColumn(5).setMaxWidth(95);
//            tablaPacientes.getColumnModel().getColumn(5).setMinWidth(95);
//            tablaPacientes.getColumnModel().getColumn(5).setPreferredWidth(95);
//            tablaPacientes.getColumnModel().getColumn(6).setMaxWidth(60);
//            tablaPacientes.getColumnModel().getColumn(6).setMinWidth(60);
//            tablaPacientes.getColumnModel().getColumn(6).setPreferredWidth(60);
//            tablaPacientes.getColumnModel().getColumn(7).setMaxWidth(0);
//            tablaPacientes.getColumnModel().getColumn(7).setMinWidth(0);
//            tablaPacientes.getColumnModel().getColumn(7).setPreferredWidth(85);
//            tablaPacientes.getColumnModel().getColumn(8).setMaxWidth(0);
//            tablaPacientes.getColumnModel().getColumn(8).setMinWidth(0);
//            tablaPacientes.getColumnModel().getColumn(8).setPreferredWidth(0);
//            tablaPacientes.getColumnModel().getColumn(9).setMaxWidth(0);
//            tablaPacientes.getColumnModel().getColumn(9).setMinWidth(0);
//            tablaPacientes.getColumnModel().getColumn(9).setPreferredWidth(0);
//            tablaPacientes.getColumnModel().getColumn(10).setMaxWidth(0);
//            tablaPacientes.getColumnModel().getColumn(10).setMinWidth(0);
//            tablaPacientes.getColumnModel().getColumn(10).setPreferredWidth(0);
//            tablaPacientes.getColumnModel().getColumn(11).setMaxWidth(0);
//            tablaPacientes.getColumnModel().getColumn(11).setMinWidth(0);
//            tablaPacientes.getColumnModel().getColumn(11).setPreferredWidth(0);
//            tablaPacientes.getColumnModel().getColumn(12).setMaxWidth(0);
//            tablaPacientes.getColumnModel().getColumn(12).setMinWidth(0);
//            tablaPacientes.getColumnModel().getColumn(12).setPreferredWidth(0);
//            tablaPacientes.getColumnModel().getColumn(13).setMaxWidth(85);
//            tablaPacientes.getColumnModel().getColumn(13).setMinWidth(85);
//            tablaPacientes.getColumnModel().getColumn(13).setPreferredWidth(85);
//            tablaPacientes.getColumnModel().getColumn(14).setMaxWidth(85);
//            tablaPacientes.getColumnModel().getColumn(14).setMinWidth(85);
//            tablaPacientes.getColumnModel().getColumn(14).setPreferredWidth(85);
//            tablaPacientes.getColumnModel().getColumn(0).setCellRenderer(alinearCentro);
//            tablaPacientes.getColumnModel().getColumn(1).setCellRenderer(alinearIzquierda);
//            tablaPacientes.getColumnModel().getColumn(2).setCellRenderer(alinearIzquierda);
//            tablaPacientes.getColumnModel().getColumn(3).setCellRenderer(alinearCentro);
//            tablaPacientes.getColumnModel().getColumn(4).setCellRenderer(alinearCentro);
//            tablaPacientes.getColumnModel().getColumn(5).setCellRenderer(alinearCentro);
//            tablaPacientes.getColumnModel().getColumn(6).setCellRenderer(alinearDerecha);
//
//            Rectangle r = tablaPacientes.getCellRect(tablaPacientes.getRowCount() - 1, 0, true);
//            tablaPacientes.scrollRectToVisible(r);
//            tablaPacientes.getSelectionModel().setSelectionInterval(tablaPacientes.getRowCount() - 1, tablaPacientes.getRowCount() - 1);
//        }
//
//        public void pausa(int mlSeg) {
//            try {
//                // pausa para el splash
//                Thread.sleep(mlSeg);
//            } catch (InterruptedException e) {
//            }
//        }

    }

    public class HiloBusquedaPacientesPorFecha extends Thread {

//        JProgressBar progreso;
//
//        public HiloBusquedaPacientesPorFecha(JProgressBar progreso1) {
//            super();
//            this.progreso = progreso1;
//        }
//
//        public void run() {
//            String[] Titulo = {"N°", "Apellido", "Nombre", "DNI", "Edad", "Fecha Orden", "Seña", "Diag", "Tipo", "Servicio", "id_OS", "hora", "id", "Mail", "Celular"};
//            Object[] Registros = new Object[15];
//            int i = 0;
//            String sql = "SELECT\n"
//                    + "  `historia_clinica`.`idhistoria_clinica`,\n"
//                    + "  `personas`.`apellido`,\n"
//                    + "  `personas`.`nombre`,\n"
//                    + "    `pacientes`.`personas_dni`,\n"
//                    + "  DATE_FORMAT(`pacientes`.`fecha_nacimiento`, '%d-%m-%Y') AS 'fecha_nacimiento',\n"
//                    + "    ((YEAR(CURDATE()) - YEAR(`pacientes`.`fecha_nacimiento`)) + IF((DATE_FORMAT(CURDATE(), '%m-%d') > DATE_FORMAT(`pacientes`.`fecha_nacimiento`, '%m-%d')), 0, -(1))) AS 'edad',\n"
//                    + "  DATE_FORMAT(`ordenes`.`fecha`, '%d-%m-%Y') AS 'fecha',\n"
//                    + "    `seña`.`seña`,\n"
//                    + "  `ordenes`.`observaciones`,\n"
//                    + "  `ordenes`.`tipo_orden`,\n"
//                    + "  `ordenes`.`servicio`,\n"
//                    + "  `historia_clinica`.`descripcion`,\n"
//                    + "  `ordenes`.`id_obrasocial`,\n"
//                    + "  CONCAT(SUBSTR(`ordenes`.`hora`,1, 2), ':', SUBSTR(`ordenes`.`hora`,3, 2), ':', SUBSTR(`ordenes`.`hora`,4, 2)) AS 'hora',\n"
//                    + "  `ordenes`.`id_ordenes`,\n"
//                    + "    `pacientes`.`mail`  ,\n"
//                    + "        `pacientes`.`celular`\n"
//                    + "FROM\n"
//                    + "  ((((`ordenes`\n"
//                    + "    JOIN `pacientes` ON ((`pacientes`.`id_Pacientes` = `ordenes`.`id_Pacientes`)))\n"
//                    + "    JOIN `personas` ON ((`personas`.`dni` = `pacientes`.`personas_dni`)))\n"
//                    + "    JOIN `historia_clinica` ON ((`historia_clinica`.`id_ordenes` = `ordenes`.`id_ordenes`)))\n"
//                    + "    LEFT JOIN `seña` ON ((`historia_clinica`.`idhistoria_clinica` = `seña`.`idhistoria_clinica`)))\n"
//                    + "WHERE\n"
//                    + "  (`ordenes`.`estado_orden` = 0) and Date(fecha) BETWEEN '" + dcFechaPacienteDesde.getDate() + "'  AND '" + dcFechaPacienteHasta.getDate() + "'\n"
//                    + "GROUP BY\n"
//                    + "  `historia_clinica`.`idhistoria_clinica`\n"
//                    + "ORDER BY `ordenes`.`id_ordenes`";
//
//            modelPaciente = new DefaultTableModel(null, Titulo) {
//
//                @Override
//                public boolean isCellEditable(int row, int column) {
//                    return false;
//                }
//            };
//
//            while (rs.next()) {
//                total = 0;
//                Registros[0] = rs.getString("idhistoria_clinica");
//                Registros[1] = rs.getString("apellido");
//                Registros[2] = rs.getString("nombre");
//                Registros[3] = rs.getString("personas_dni");
//                Registros[4] = rs.getString("edad");
//                Registros[5] = rs.getString("fecha");
//                Registros[6] = "$  " + rs.getDouble("seña");
//                Registros[7] = rs.getString("observaciones");
//                Registros[8] = rs.getString("tipo_orden");
//                Registros[9] = rs.getString("servicio");
//                descripcion[i] = rs.getString("descripcion");
//                Registros[10] = rs.getString("id_obrasocial");
//                Registros[11] = rs.getString("hora");
//                Registros[12] = rs.getString("id_ordenes");
//                Registros[13] = rs.getString("mail");
//                Registros[14] = rs.getString("celular");
//
//                modelPaciente.addRow(Registros);
//                i++;
//            }
//            tablaPacientes.setModel(modelPaciente);
//            tablaPacientes.setAutoCreateRowSorter(true);
//            tablaPacientes.getColumnModel().getColumn(0).setMaxWidth(90);
//            tablaPacientes.getColumnModel().getColumn(0).setMinWidth(90);
//            tablaPacientes.getColumnModel().getColumn(0).setPreferredWidth(90);
//
//            tablaPacientes.getColumnModel().getColumn(1).setMinWidth(150);
//            tablaPacientes.getColumnModel().getColumn(1).setPreferredWidth(150);
//            tablaPacientes.getColumnModel().getColumn(2).setMaxWidth(150);
//            tablaPacientes.getColumnModel().getColumn(2).setMinWidth(150);
//            tablaPacientes.getColumnModel().getColumn(2).setPreferredWidth(150);
//            /////////////////////////////////////////////////////////////
//            tablaPacientes.getColumnModel().getColumn(3).setMaxWidth(95);
//            tablaPacientes.getColumnModel().getColumn(3).setMinWidth(95);
//            tablaPacientes.getColumnModel().getColumn(3).setPreferredWidth(95);
//            /////////////////////////////////////////////////////////////
//            tablaPacientes.getColumnModel().getColumn(4).setMaxWidth(60);
//            tablaPacientes.getColumnModel().getColumn(4).setMinWidth(60);
//            tablaPacientes.getColumnModel().getColumn(4).setPreferredWidth(60);
//            /////////////////////////////////////////////////////////////
//            tablaPacientes.getColumnModel().getColumn(5).setMaxWidth(95);
//            tablaPacientes.getColumnModel().getColumn(5).setMinWidth(95);
//            tablaPacientes.getColumnModel().getColumn(5).setPreferredWidth(95);
//            /////////////////////////////////////////////////////////////
//            tablaPacientes.getColumnModel().getColumn(6).setMaxWidth(60);
//            tablaPacientes.getColumnModel().getColumn(6).setMinWidth(60);
//            tablaPacientes.getColumnModel().getColumn(6).setPreferredWidth(60);
//            //////////////////////////////////////////////////////////////
//            tablaPacientes.getColumnModel().getColumn(7).setMaxWidth(0);
//            tablaPacientes.getColumnModel().getColumn(7).setMinWidth(0);
//            tablaPacientes.getColumnModel().getColumn(7).setPreferredWidth(85);
//            /////////////////////////////////////////////////////////////
//            tablaPacientes.getColumnModel().getColumn(8).setMaxWidth(0);
//            tablaPacientes.getColumnModel().getColumn(8).setMinWidth(0);
//            tablaPacientes.getColumnModel().getColumn(8).setPreferredWidth(0);
//            /////////////////////////////////////////////////////////////
//            tablaPacientes.getColumnModel().getColumn(9).setMaxWidth(0);
//            tablaPacientes.getColumnModel().getColumn(9).setMinWidth(0);
//            tablaPacientes.getColumnModel().getColumn(9).setPreferredWidth(0);
//            /////////////////////////////////////////////////////////////
//            tablaPacientes.getColumnModel().getColumn(10).setMaxWidth(0);
//            tablaPacientes.getColumnModel().getColumn(10).setMinWidth(0);
//            tablaPacientes.getColumnModel().getColumn(10).setPreferredWidth(0);
//            /////////////////////////////////////////////////////////////////////
//            tablaPacientes.getColumnModel().getColumn(11).setMaxWidth(0);
//            tablaPacientes.getColumnModel().getColumn(11).setMinWidth(0);
//            tablaPacientes.getColumnModel().getColumn(11).setPreferredWidth(0);
//            /////////////////////////////////////////////////////////////////////
//            tablaPacientes.getColumnModel().getColumn(12).setMaxWidth(0);
//            tablaPacientes.getColumnModel().getColumn(12).setMinWidth(0);
//            tablaPacientes.getColumnModel().getColumn(12).setPreferredWidth(0);
//            ////////////////////////////////////////////////////////////////////
//            tablaPacientes.getColumnModel().getColumn(13).setMaxWidth(85);
//            tablaPacientes.getColumnModel().getColumn(13).setMinWidth(85);
//            tablaPacientes.getColumnModel().getColumn(13).setPreferredWidth(85);
//            ////////////////////////////////////////////////////////////////////
//            tablaPacientes.getColumnModel().getColumn(14).setMaxWidth(85);
//            tablaPacientes.getColumnModel().getColumn(14).setMinWidth(85);
//            tablaPacientes.getColumnModel().getColumn(14).setPreferredWidth(85);
//            ///////////////////////////////////////////////////////
//            tablaPacientes.getColumnModel().getColumn(0).setCellRenderer(alinearCentro);
//            tablaPacientes.getColumnModel().getColumn(1).setCellRenderer(alinearIzquierda);
//            tablaPacientes.getColumnModel().getColumn(2).setCellRenderer(alinearIzquierda);
//            tablaPacientes.getColumnModel().getColumn(3).setCellRenderer(alinearCentro);
//            tablaPacientes.getColumnModel().getColumn(4).setCellRenderer(alinearCentro);
//            tablaPacientes.getColumnModel().getColumn(5).setCellRenderer(alinearCentro);
//            tablaPacientes.getColumnModel().getColumn(6).setCellRenderer(alinearDerecha);
//
//            Rectangle r = tablaPacientes.getCellRect(tablaPacientes.getRowCount() - 1, 0, true);
//            tablaPacientes.scrollRectToVisible(r);
//            tablaPacientes.getSelectionModel().setSelectionInterval(tablaPacientes.getRowCount() - 1, tablaPacientes.getRowCount() - 1);
//
//        }
//
//        public void pausa(int mlSeg) {
//            try {
//                // pausa para el splash
//                Thread.sleep(mlSeg);
//            } catch (Exception e) {
//            }
//        }

    }

    public void cargatotalesordenesfacturacion() {
        int totalRow = tablaOrdenes.getRowCount(), contador = 0;
        totalRow -= 1;
        for (int i = 0; i <= (totalRow); i++) {
            if (tablaOrdenes.getValueAt(i, 7).toString().equals("true")) {
                contador++;
            }
        }
        lblTotalOrdenes.setText((String.valueOf(contador)));
    }

    public double Redondear(double numero) {
        return Math.rint(numero * 100) / 100;
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        popuMenu1 = new rojeru_san.complementos.PopuMenu();
        Resultados = new javax.swing.JMenuItem();
        Modifica = new javax.swing.JMenuItem();
        Cargar = new javax.swing.JMenuItem();
        panelBotones = new javax.swing.JPanel();
        rSLabelIcon1 = new RSMaterialComponent.RSLabelIcon();
        btnCargarResultados = new RSMaterialComponent.RSButtonMaterialIconOne();
        btnUtilitarios = new RSMaterialComponent.RSButtonMaterialIconOne();
        btnFacturacion = new RSMaterialComponent.RSButtonMaterialIconOne();
        btnCargarPaciente = new RSMaterialComponent.RSButtonMaterialIconOne();
        rSButtonMaterialIconOne5 = new RSMaterialComponent.RSButtonMaterialIconOne();
        rSButtonIconOne1 = new RSMaterialComponent.RSButtonIconOne();
        rSButtonIconOne2 = new RSMaterialComponent.RSButtonIconOne();
        btnCargarResultados1 = new RSMaterialComponent.RSButtonMaterialIconOne();
        panelContenedor = new javax.swing.JPanel();
        panelCargarPaciente = new javax.swing.JPanel();
        txtDNI = new RSMaterialComponent.RSTextFieldMaterial();
        txtApellido = new RSMaterialComponent.RSTextFieldMaterial();
        txtNombre = new RSMaterialComponent.RSTextFieldMaterial();
        txtLocalidad = new RSMaterialComponent.RSTextFieldMaterial();
        txtDireccion = new RSMaterialComponent.RSTextFieldMaterial();
        txtCelular = new RSMaterialComponent.RSTextFieldMaterial();
        txtTelefono = new RSMaterialComponent.RSTextFieldMaterial();
        txtMail = new RSMaterialComponent.RSTextFieldMaterial();
        lblEdad = new javax.swing.JLabel();
        txtObraSocial = new RSMaterialComponent.RSTextFieldMaterial();
        txtNumeroAfiliado = new RSMaterialComponent.RSTextFieldMaterial();
        jPanel7 = new javax.swing.JPanel();
        btnCargarConsulta = new RSMaterialComponent.RSButtonMaterialIconOne();
        btnBuscar = new RSMaterialComponent.RSButtonMaterialIconOne();
        btnPatologias = new RSMaterialComponent.RSButtonMaterialIconOne();
        btnSinDNI = new RSMaterialComponent.RSButtonMaterialIconOne();
        btnModificar = new RSMaterialComponent.RSButtonMaterialIconOne();
        btnBorrar = new RSMaterialComponent.RSButtonMaterialIconOne();
        jPanel8 = new javax.swing.JPanel();
        txtBuscar = new RSMaterialComponent.RSTextFieldMaterial();
        jScrollPane5 = new javax.swing.JScrollPane();
        tablaPacientes = new RSMaterialComponent.RSTableMetroCustom();
        dcFechaPacienteDesde = new newscomponents.RSDateChooser();
        dcFechaPacienteHasta = new newscomponents.RSDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cboSexo = new RSMaterialComponent.RSComboBox();
        dcFechaNacimiento = new newscomponents.RSDateChooser();
        panelCargarOrden = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblNumeroProtocolo = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblHora = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblPaciente = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        txtMedico = new RSMaterialComponent.RSTextFieldMaterial();
        jLabel9 = new javax.swing.JLabel();
        cboServicio = new RSMaterialComponent.RSComboBox();
        txtCama = new RSMaterialComponent.RSTextFieldMaterial();
        txtNumeroOrden = new RSMaterialComponent.RSTextFieldMaterial();
        jLabel12 = new javax.swing.JLabel();
        txtMotivo = new RSMaterialComponent.RSTextFieldMaterial();
        chkCoseguro = new RSMaterialComponent.RSCheckBoxMaterial();
        txtPrecioCoseguro = new RSMaterialComponent.RSTextFieldMaterial();
        txtRecienNacido = new RSMaterialComponent.RSTextFieldMaterial();
        dcFechaOrden = new newscomponents.RSDateChooser();
        jPanel11 = new javax.swing.JPanel();
        txtBuscarPractica = new RSMaterialComponent.RSTextFieldIconTwo();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaPracticas = new RSMaterialComponent.RSTableMetroCustom();
        jLabel10 = new javax.swing.JLabel();
        lblInstrucciones = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel61 = new javax.swing.JLabel();
        lblTotalOS = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        lblTotalParticular = new javax.swing.JTextField();
        jPanel14 = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        lblSeña = new javax.swing.JTextField();
        jLabel55 = new javax.swing.JLabel();
        lblTotal = new javax.swing.JTextField();
        btnSiguienteBono = new RSMaterialComponent.RSButtonMaterialIconOne();
        jPanel12 = new javax.swing.JPanel();
        btnGrabarImprimir = new RSMaterialComponent.RSButtonMaterialIconOne();
        btnGrabar = new RSMaterialComponent.RSButtonMaterialIconOne();
        btnReimprimir = new RSMaterialComponent.RSButtonMaterialIconOne();
        btnPresupuesto = new RSMaterialComponent.RSButtonMaterialIconOne();
        btnResultados = new RSMaterialComponent.RSButtonMaterialIconOne();
        btnCancelar = new RSMaterialComponent.RSButtonMaterialIconOne();
        btnSeñas = new RSMaterialComponent.RSButtonMaterialIconOne();
        btnCertificadoAsistencia = new RSMaterialComponent.RSButtonMaterialIconOne();
        btnImprimirListado = new RSMaterialComponent.RSButtonMaterialIconOne();
        panelFacturacion = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        chkObrasocial = new RSMaterialComponent.RSCheckBoxMaterial();
        btnFiltrar = new RSMaterialComponent.RSButtonMaterialIconOne();
        dcFechaDesde = new newscomponents.RSDateChooser();
        dcFechaHasta = new newscomponents.RSDateChooser();
        jPanel16 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaDetalle = new RSMaterialComponent.RSTableMetroCustom();
        jPanel19 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaOrdenes = new RSMaterialComponent.RSTableMetroCustom();
        txtBuscarOrden = new RSMaterialComponent.RSTextFieldIconTwo();
        jPanel17 = new javax.swing.JPanel();
        progresoOrdenes = new JProgressBar();
        jLabel16 = new javax.swing.JLabel();
        lblTotalOrdenes = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        lblMontoTotalOrdenes = new javax.swing.JLabel();
        btnImprimirResumen = new RSMaterialComponent.RSButtonMaterialIconOne();
        btnLimpiar = new RSMaterialComponent.RSButtonMaterialIconOne();
        jLabel15 = new javax.swing.JLabel();
        panelUtilidades = new javax.swing.JPanel();
        btnAnalisis = new RSMaterialComponent.RSButtonMaterialIconOne();
        btnMedicos = new RSMaterialComponent.RSButtonMaterialIconOne();
        btnDescargarDDJJ = new RSMaterialComponent.RSButtonMaterialIconOne();
        btnHistoriasClinicas = new RSMaterialComponent.RSButtonMaterialIconOne();
        btnNomenclador = new RSMaterialComponent.RSButtonMaterialIconOne();
        btnExportarDatos = new RSMaterialComponent.RSButtonMaterialIconOne();
        btnBioquimicos = new RSMaterialComponent.RSButtonMaterialIconOne();
        btnSecciones = new RSMaterialComponent.RSButtonMaterialIconOne();
        btnInformes = new RSMaterialComponent.RSButtonMaterialIconOne();
        btnUsuarios = new RSMaterialComponent.RSButtonMaterialIconOne();
        btnDescargas = new RSMaterialComponent.RSButtonMaterialIconOne();
        btnObrasSociales = new RSMaterialComponent.RSButtonMaterialIconOne();
        btnActualizarBD = new RSMaterialComponent.RSButtonMaterialIconOne();
        btnHistorico = new RSMaterialComponent.RSButtonMaterialIconOne();
        btnPracticas = new RSMaterialComponent.RSButtonMaterialIconOne();
        btnAyuda = new RSMaterialComponent.RSButtonMaterialIconOne();
        btnPacientes = new RSMaterialComponent.RSButtonMaterialIconOne();
        btnActualizarPrecio = new RSMaterialComponent.RSButtonMaterialIconOne();

        popuMenu1.setForeground(new Color(0, 90, 132));
        popuMenu1.setColorBackgroud(new Color(255, 255, 255));
        popuMenu1.setColorBorde(new Color(0, 90, 132));
        popuMenu1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        Resultados.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Resultados.setForeground(new Color(0, 90, 132));
        Resultados.setText("Cargar resultados");
        Resultados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResultadosActionPerformed(evt);
            }
        });
        popuMenu1.add(Resultados);

        Modifica.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Modifica.setForeground(new Color(0, 90, 132));
        Modifica.setText("Modificar orden");
        Modifica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModificaActionPerformed(evt);
            }
        });
        popuMenu1.add(Modifica);

        Cargar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Cargar.setForeground(new Color(0, 90, 132));
        Cargar.setText("Cargar paciente");
        Cargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CargarActionPerformed(evt);
            }
        });
        popuMenu1.add(Cargar);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        panelBotones.setBackground(new Color(0, 90, 132));
        panelBotones.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(0, 90, 132)));
        panelBotones.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        panelBotones.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(MouseEvent evt) {
                panelBotonesMouseDragged(evt);
            }
        });
        panelBotones.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                panelBotonesMousePressed(evt);
            }
        });

        rSLabelIcon1.setForeground(new Color(255, 255, 255));
        rSLabelIcon1.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ADD_LOCATION);
        rSLabelIcon1.setSizeIcon(100.0F);

        btnCargarResultados.setBackground(new Color(255, 255, 255));
        btnCargarResultados.setText("Turnos");
        btnCargarResultados.setBackgroundHover(new Color(204, 204, 204));
        btnCargarResultados.setForegroundIcon(new Color(0, 90, 132));
        btnCargarResultados.setForegroundIconHover(new Color(0, 90, 132));
        btnCargarResultados.setForegroundText(new Color(0, 90, 132));
        btnCargarResultados.setHorizontalAlignment(SwingConstants.CENTER);
        btnCargarResultados.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ACCESS_TIME);
        btnCargarResultados.setRound(20);
        btnCargarResultados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarResultadosActionPerformed(evt);
            }
        });

        btnUtilitarios.setBackground(new Color(255, 255, 255));
        btnUtilitarios.setText("Utilitarios");
        btnUtilitarios.setBackgroundHover(new Color(204, 204, 204));
        btnUtilitarios.setForegroundIcon(new Color(0, 90, 132));
        btnUtilitarios.setForegroundIconHover(new Color(0, 90, 132));
        btnUtilitarios.setForegroundText(new Color(0, 90, 132));
        btnUtilitarios.setHorizontalAlignment(SwingConstants.CENTER);
        btnUtilitarios.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.TUNE);
        btnUtilitarios.setRound(20);
        btnUtilitarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUtilitariosActionPerformed(evt);
            }
        });

        btnFacturacion.setBackground(new Color(255, 255, 255));
        btnFacturacion.setText("Facturación");
        btnFacturacion.setBackgroundHover(new Color(204, 204, 204));
        btnFacturacion.setForegroundIcon(new Color(0, 90, 132));
        btnFacturacion.setForegroundIconHover(new Color(0, 90, 132));
        btnFacturacion.setForegroundText(new Color(0, 90, 132));
        btnFacturacion.setHorizontalAlignment(SwingConstants.CENTER);
        btnFacturacion.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.MONETIZATION_ON);
        btnFacturacion.setRound(20);
        btnFacturacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFacturacionActionPerformed(evt);
            }
        });

        btnCargarPaciente.setBackground(new Color(255, 255, 255));
        btnCargarPaciente.setText("Cargar paciente");
        btnCargarPaciente.setBackgroundHover(new Color(204, 204, 204));
        btnCargarPaciente.setForegroundIcon(new Color(0, 90, 132));
        btnCargarPaciente.setForegroundIconHover(new Color(0, 90, 132));
        btnCargarPaciente.setForegroundText(new Color(0, 90, 132));
        btnCargarPaciente.setHorizontalAlignment(SwingConstants.CENTER);
        btnCargarPaciente.setRound(20);
        btnCargarPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarPacienteActionPerformed(evt);
            }
        });

        rSButtonMaterialIconOne5.setBackground(new Color(255, 255, 255));
        rSButtonMaterialIconOne5.setText("Salir");
        rSButtonMaterialIconOne5.setBackgroundHover(new Color(204, 204, 204));
        rSButtonMaterialIconOne5.setForegroundIcon(new Color(0, 90, 132));
        rSButtonMaterialIconOne5.setForegroundIconHover(new Color(0, 90, 132));
        rSButtonMaterialIconOne5.setForegroundText(new Color(0, 90, 132));
        rSButtonMaterialIconOne5.setHorizontalAlignment(SwingConstants.CENTER);
        rSButtonMaterialIconOne5.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.EXIT_TO_APP);
        rSButtonMaterialIconOne5.setRound(20);
        rSButtonMaterialIconOne5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMaterialIconOne5ActionPerformed(evt);
            }
        });

        rSButtonIconOne1.setBackground(new Color(0, 90, 132));
        rSButtonIconOne1.setBackgroundHover(new Color(0, 90, 132));
        rSButtonIconOne1.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.CLEAR);
        rSButtonIconOne1.setRound(20);
        rSButtonIconOne1.setSizeIcon(30.0F);
        rSButtonIconOne1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIconOne1ActionPerformed(evt);
            }
        });

        rSButtonIconOne2.setBackground(new Color(0, 90, 132));
        rSButtonIconOne2.setBackgroundHover(new Color(0, 90, 132));
        rSButtonIconOne2.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.REMOVE);
        rSButtonIconOne2.setRound(20);
        rSButtonIconOne2.setSizeIcon(30.0F);
        rSButtonIconOne2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIconOne2ActionPerformed(evt);
            }
        });

        btnCargarResultados1.setBackground(new Color(255, 255, 255));
        btnCargarResultados1.setText("Informes");
        btnCargarResultados1.setBackgroundHover(new Color(204, 204, 204));
        btnCargarResultados1.setForegroundIcon(new Color(0, 90, 132));
        btnCargarResultados1.setForegroundIconHover(new Color(0, 90, 132));
        btnCargarResultados1.setForegroundText(new Color(0, 90, 132));
        btnCargarResultados1.setHorizontalAlignment(SwingConstants.CENTER);
        btnCargarResultados1.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.PAGEVIEW);
        btnCargarResultados1.setRound(20);
        btnCargarResultados1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarResultados1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBotonesLayout = new javax.swing.GroupLayout(panelBotones);
        panelBotones.setLayout(panelBotonesLayout);
        panelBotonesLayout.setHorizontalGroup(
                panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelBotonesLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panelBotonesLayout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(rSButtonIconOne2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(rSButtonIconOne1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(rSLabelIcon1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnUtilitarios, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                                        .addComponent(btnFacturacion, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                                        .addComponent(btnCargarResultados, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                                        .addComponent(btnCargarPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                                        .addComponent(rSButtonMaterialIconOne5, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                                        .addComponent(btnCargarResultados1, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE))
                                .addContainerGap())
        );
        panelBotonesLayout.setVerticalGroup(
                panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelBotonesLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(rSButtonIconOne2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(rSButtonIconOne1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(rSLabelIcon1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnCargarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnCargarResultados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnCargarResultados1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnFacturacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnUtilitarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                                .addComponent(rSButtonMaterialIconOne5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        getContentPane().add(panelBotones);

        panelContenedor.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(0, 90, 132)));
        panelContenedor.setLayout(new javax.swing.OverlayLayout(panelContenedor));

        panelCargarPaciente.setBackground(new Color(255, 255, 255));
        panelCargarPaciente.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtDNI.setForeground(new Color(0, 90, 132));
        txtDNI.setColorMaterial(new Color(0, 90, 132));
        txtDNI.setPhColor(new Color(0, 90, 132));
        txtDNI.setPlaceholder("DNI");
        txtDNI.setSelectionColor(new Color(0, 90, 132));
        txtDNI.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDNIFocusLost(evt);
            }
        });
        txtDNI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDNIActionPerformed(evt);
            }
        });
        txtDNI.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
                txtDNIKeyTyped(evt);
            }
        });

        txtApellido.setForeground(new Color(0, 90, 132));
        txtApellido.setColorMaterial(new Color(0, 90, 132));
        txtApellido.setPhColor(new Color(0, 90, 132));
        txtApellido.setPlaceholder("Apellido");
        txtApellido.setSelectionColor(new Color(0, 90, 132));
        txtApellido.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtApellidoFocusGained(evt);
            }
        });
        txtApellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                txtApellidoKeyPressed(evt);
            }
        });

        txtNombre.setForeground(new Color(0, 90, 132));
        txtNombre.setColorMaterial(new Color(0, 90, 132));
        txtNombre.setPhColor(new Color(0, 90, 132));
        txtNombre.setPlaceholder("Nombre");
        txtNombre.setSelectionColor(new Color(0, 90, 132));
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                txtNombreKeyPressed(evt);
            }
        });

        txtLocalidad.setForeground(new Color(0, 90, 132));
        txtLocalidad.setColorMaterial(new Color(0, 90, 132));
        txtLocalidad.setMaximumSize(null);
        txtLocalidad.setMinimumSize(null);
        txtLocalidad.setPhColor(new Color(0, 90, 132));
        txtLocalidad.setPlaceholder("Localidad");
        txtLocalidad.setPreferredSize(null);
        txtLocalidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                txtLocalidadKeyPressed(evt);
            }
        });

        txtDireccion.setForeground(new Color(0, 90, 132));
        txtDireccion.setColorMaterial(new Color(0, 90, 132));
        txtDireccion.setPhColor(new Color(0, 90, 132));
        txtDireccion.setPlaceholder("Dirección");
        txtDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                txtDireccionKeyPressed(evt);
            }
        });

        txtCelular.setForeground(new Color(0, 90, 132));
        txtCelular.setColorMaterial(new Color(0, 90, 132));
        txtCelular.setPhColor(new Color(0, 90, 132));
        txtCelular.setPlaceholder("Celular");
        txtCelular.setSelectionColor(new Color(0, 90, 132));
        txtCelular.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                txtCelularKeyPressed(evt);
            }
        });

        txtTelefono.setForeground(new Color(0, 90, 132));
        txtTelefono.setColorMaterial(new Color(0, 90, 132));
        txtTelefono.setPhColor(new Color(0, 90, 132));
        txtTelefono.setPlaceholder("Teléfono");
        txtTelefono.setSelectionColor(new Color(0, 90, 132));
        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                txtTelefonoKeyPressed(evt);
            }
        });

        txtMail.setForeground(new Color(0, 90, 132));
        txtMail.setColorMaterial(new Color(0, 90, 132));
        txtMail.setPhColor(new Color(0, 90, 132));
        txtMail.setPlaceholder("Mail");
        txtMail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                txtMailKeyPressed(evt);
            }
        });

        lblEdad.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblEdad.setForeground(new Color(0, 90, 132));

        txtObraSocial.setForeground(new Color(0, 90, 132));
        txtObraSocial.setColorMaterial(new Color(0, 90, 132));
        txtObraSocial.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtObraSocial.setNextFocusableComponent(txtNumeroAfiliado);
        txtObraSocial.setPhColor(new Color(0, 90, 132));
        txtObraSocial.setPlaceholder("Obra Social");
        txtObraSocial.setSelectionColor(new Color(0, 90, 132));
        txtObraSocial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtObraSocialActionPerformed(evt);
            }
        });
        txtObraSocial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                txtObraSocialKeyPressed(evt);
            }
        });

        txtNumeroAfiliado.setForeground(new Color(0, 90, 132));
        txtNumeroAfiliado.setColorMaterial(new Color(0, 90, 132));
        txtNumeroAfiliado.setPhColor(new Color(0, 90, 132));
        txtNumeroAfiliado.setPlaceholder("Afiliado");
        txtNumeroAfiliado.setSelectionColor(new Color(0, 90, 132));
        txtNumeroAfiliado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                txtNumeroAfiliadoKeyPressed(evt);
            }
        });

        jPanel7.setBackground(new Color(255, 255, 255));

        btnCargarConsulta.setBackground(new Color(0, 90, 132));
        btnCargarConsulta.setText("Cargar consulta");
        btnCargarConsulta.setBackgroundHover(new Color(204, 204, 204));
        btnCargarConsulta.setForegroundIconHover(new Color(0, 90, 132));
        btnCargarConsulta.setHorizontalAlignment(SwingConstants.CENTER);
        btnCargarConsulta.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ATTACHMENT);
        btnCargarConsulta.setPreferredSize(new java.awt.Dimension(130, 40));
        btnCargarConsulta.setRound(20);
        btnCargarConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarConsultaActionPerformed(evt);
            }
        });

        btnBuscar.setBackground(new Color(0, 90, 132));
        btnBuscar.setText("Buscar");
        btnBuscar.setBackgroundHover(new Color(204, 204, 204));
        btnBuscar.setForegroundIconHover(new Color(0, 90, 132));
        btnBuscar.setHorizontalAlignment(SwingConstants.CENTER);
        btnBuscar.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SEARCH);
        btnBuscar.setPreferredSize(new java.awt.Dimension(130, 40));
        btnBuscar.setRound(20);
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnPatologias.setBackground(new Color(0, 90, 132));
        btnPatologias.setBackgroundHover(new Color(204, 204, 204));
        btnPatologias.setForegroundIconHover(new Color(0, 90, 132));
        btnPatologias.setHorizontalAlignment(SwingConstants.CENTER);
        btnPatologias.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.BUG_REPORT);
        btnPatologias.setLabel("Patologías");
        btnPatologias.setPreferredSize(new java.awt.Dimension(130, 40));
        btnPatologias.setRound(20);
        btnPatologias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPatologiasActionPerformed(evt);
            }
        });

        btnSinDNI.setBackground(new Color(0, 90, 132));
        btnSinDNI.setBackgroundHover(new Color(204, 204, 204));
        btnSinDNI.setForegroundIconHover(new Color(0, 90, 132));
        btnSinDNI.setHorizontalAlignment(SwingConstants.CENTER);
        btnSinDNI.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.TAB_UNSELECTED);
        btnSinDNI.setLabel("Sin DNI");
        btnSinDNI.setPreferredSize(new java.awt.Dimension(130, 40));
        btnSinDNI.setRound(20);
        btnSinDNI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSinDNIActionPerformed(evt);
            }
        });

        btnModificar.setBackground(new Color(0, 90, 132));
        btnModificar.setText("Modificar");
        btnModificar.setBackgroundHover(new Color(204, 204, 204));
        btnModificar.setForegroundIconHover(new Color(0, 90, 132));
        btnModificar.setHorizontalAlignment(SwingConstants.CENTER);
        btnModificar.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.EDIT);
        btnModificar.setPreferredSize(new java.awt.Dimension(130, 40));
        btnModificar.setRound(20);
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnBorrar.setBackground(new Color(0, 90, 132));
        btnBorrar.setBackgroundHover(new Color(204, 204, 204));
        btnBorrar.setForegroundIconHover(new Color(0, 90, 132));
        btnBorrar.setHorizontalAlignment(SwingConstants.CENTER);
        btnBorrar.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.DELETE);
        btnBorrar.setLabel("Borrar");
        btnBorrar.setPreferredSize(new java.awt.Dimension(130, 40));
        btnBorrar.setRound(20);
        btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
                jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btnCargarConsulta, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnPatologias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnSinDNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
                jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnCargarConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnPatologias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnSinDNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
        );

        jPanel8.setBackground(new Color(255, 255, 255));

        txtBuscar.setForeground(new Color(0, 90, 132));
        txtBuscar.setColorMaterial(new Color(0, 90, 132));
        txtBuscar.setMaximumSize(null);
        txtBuscar.setMinimumSize(null);
        txtBuscar.setPhColor(new Color(0, 90, 132));
        txtBuscar.setPlaceholder("Buscar");
        txtBuscar.setPreferredSize(null);
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });

        tablaPacientes.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(255, 255, 255)));
        tablaPacientes.setForeground(new Color(255, 255, 255));
        tablaPacientes.setModel(new DefaultTableModel(
                new Object[][]{

                },
                new String[]{

                }
        ));
        tablaPacientes.setBackgoundHead(new Color(0, 90, 132));
        tablaPacientes.setBackgoundHover(new Color(0, 90, 132));
        tablaPacientes.setBorderHead(null);
        tablaPacientes.setBorderRows(null);
        tablaPacientes.setColorBorderHead(new Color(255, 255, 255));
        tablaPacientes.setColorBorderRows(new Color(255, 255, 255));
        tablaPacientes.setColorPrimaryText(new Color(0, 90, 132));
        tablaPacientes.setColorSecondary(new Color(255, 255, 255));
        tablaPacientes.setColorSecundaryText(new Color(0, 90, 132));
        tablaPacientes.setComponentPopupMenu(popuMenu1);
        tablaPacientes.setGridColor(new Color(255, 255, 255));
        tablaPacientes.setSelectionBackground(new Color(0, 90, 132));
        tablaPacientes.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                tablaPacientesMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tablaPacientes);

        dcFechaPacienteDesde.setBackground(new Color(255, 255, 255));
        dcFechaPacienteDesde.setForeground(new Color(0, 90, 132));
        dcFechaPacienteDesde.setBgColor(new Color(0, 90, 132));
        dcFechaPacienteDesde.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        dcFechaPacienteDesde.setFormatDate("dd-MM-yyyy");

        dcFechaPacienteHasta.setBackground(new Color(255, 255, 255));
        dcFechaPacienteHasta.setForeground(new Color(0, 90, 132));
        dcFechaPacienteHasta.setBgColor(new Color(0, 90, 132));
        dcFechaPacienteHasta.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        dcFechaPacienteHasta.setFormatDate("dd-MM-yyyy");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new Color(0, 90, 132));
        jLabel2.setText("Desde:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new Color(0, 90, 132));
        jLabel4.setText("Hasta:");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
                jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel8Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel8Layout.createSequentialGroup()
                                                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel2)
                                                .addGap(18, 18, 18)
                                                .addComponent(dcFechaPacienteDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel4)
                                                .addGap(18, 18, 18)
                                                .addComponent(dcFechaPacienteHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 906, Short.MAX_VALUE))
                                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
                jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txtBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                                        .addComponent(dcFechaPacienteDesde, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                                        .addComponent(dcFechaPacienteHasta, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
                                .addContainerGap())
        );

        cboSexo.setForeground(new Color(0, 90, 132));
        cboSexo.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"FEMENINO", "MASCULINO"}));
        cboSexo.setColorArrow(new Color(0, 90, 132));
        cboSexo.setColorBorde(new Color(255, 255, 255));
        cboSexo.setColorBoton(new Color(255, 255, 255));
        cboSexo.setColorDisabledIndex(new Color(0, 90, 132));
        cboSexo.setColorDisabledIndexText(new Color(0, 90, 132));
        cboSexo.setColorFondo(new Color(255, 255, 255));
        cboSexo.setColorListaItemsTXT(new Color(0, 90, 132));
        cboSexo.setColorSeleccion(new Color(0, 90, 132));
        cboSexo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                cboSexoKeyPressed(evt);
            }
        });

        dcFechaNacimiento.setBackground(new Color(255, 255, 255));
        dcFechaNacimiento.setForeground(new Color(0, 90, 132));
        dcFechaNacimiento.setBgColor(new Color(0, 90, 132));
        dcFechaNacimiento.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        dcFechaNacimiento.setFormatDate("dd-MM-yyyy");

        javax.swing.GroupLayout panelCargarPacienteLayout = new javax.swing.GroupLayout(panelCargarPaciente);
        panelCargarPaciente.setLayout(panelCargarPacienteLayout);
        panelCargarPacienteLayout.setHorizontalGroup(
                panelCargarPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelCargarPacienteLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelCargarPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panelCargarPacienteLayout.createSequentialGroup()
                                                .addComponent(txtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(cboSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                        .addGroup(panelCargarPacienteLayout.createSequentialGroup()
                                                .addComponent(txtLocalidad, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtDireccion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(panelCargarPacienteLayout.createSequentialGroup()
                                                .addComponent(txtMail, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(dcFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(lblEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtObraSocial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtNumeroAfiliado, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
        );
        panelCargarPacienteLayout.setVerticalGroup(
                panelCargarPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelCargarPacienteLayout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(panelCargarPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(panelCargarPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(txtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(cboSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(panelCargarPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtLocalidad, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(panelCargarPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(panelCargarPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(txtObraSocial, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtMail, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtNumeroAfiliado, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(dcFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );

        panelContenedor.add(panelCargarPaciente);

        panelCargarOrden.setBackground(new Color(255, 255, 255));
        panelCargarOrden.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel9.setBackground(new Color(255, 255, 255));

        jLabel1.setBackground(new Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new Color(0, 90, 132));
        jLabel1.setText("Nº de protocolo:");

        lblNumeroProtocolo.setBackground(new Color(255, 255, 255));
        lblNumeroProtocolo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblNumeroProtocolo.setForeground(new Color(102, 0, 51));

        jLabel3.setBackground(new Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new Color(0, 90, 132));
        jLabel3.setText("Fecha:");

        lblFecha.setBackground(new Color(255, 255, 255));
        lblFecha.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblFecha.setForeground(new Color(102, 0, 51));

        jLabel5.setBackground(new Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new Color(0, 90, 132));
        jLabel5.setText("Hora:");

        lblHora.setBackground(new Color(255, 255, 255));
        lblHora.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblHora.setForeground(new Color(102, 0, 51));

        jLabel7.setBackground(new Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new Color(0, 90, 132));
        jLabel7.setText("Paciente:");

        lblPaciente.setBackground(new Color(255, 255, 255));
        lblPaciente.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblPaciente.setForeground(new Color(102, 0, 51));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
                jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel9Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblNumeroProtocolo, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblHora, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
                jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel9Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(jLabel3))
                                        .addComponent(lblNumeroProtocolo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblFecha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel9Layout.createSequentialGroup()
                                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                .addComponent(jLabel5)
                                                                .addComponent(jLabel7))
                                                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(lblHora, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
        );

        jPanel10.setBackground(new Color(255, 255, 255));

        txtMedico.setForeground(new Color(0, 90, 132));
        txtMedico.setColorMaterial(new Color(0, 90, 132));
        txtMedico.setPhColor(new Color(0, 90, 132));
        txtMedico.setPlaceholder("Médico");
        txtMedico.setSelectionColor(new Color(0, 90, 132));
        txtMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMedicoActionPerformed(evt);
            }
        });
        txtMedico.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                txtMedicoKeyPressed(evt);
            }
        });

        jLabel9.setBackground(new Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setForeground(new Color(0, 90, 132));
        jLabel9.setText("Servicio:");

        cboServicio.setForeground(new Color(0, 90, 132));
        cboServicio.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Ambulatorio", "Domicilio", "Internación"}));
        cboServicio.setColorArrow(new Color(0, 90, 132));
        cboServicio.setColorBorde(new Color(255, 255, 255));
        cboServicio.setColorBoton(new Color(255, 255, 255));
        cboServicio.setColorDisabledIndex(new Color(0, 90, 132));
        cboServicio.setColorDisabledIndexText(new Color(0, 90, 132));
        cboServicio.setColorFondo(new Color(255, 255, 255));
        cboServicio.setColorListaItemsTXT(new Color(0, 90, 132));
        cboServicio.setColorSeleccion(new Color(0, 90, 132));
        cboServicio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                cboServicioKeyPressed(evt);
            }
        });

        txtCama.setForeground(new Color(0, 90, 132));
        txtCama.setColorMaterial(new Color(0, 90, 132));
        txtCama.setPhColor(new Color(0, 90, 132));
        txtCama.setPlaceholder("Cama");
        txtCama.setSelectionColor(new Color(0, 90, 132));
        txtCama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                txtCamaKeyPressed(evt);
            }
        });

        txtNumeroOrden.setForeground(new Color(0, 90, 132));
        txtNumeroOrden.setColorMaterial(new Color(0, 90, 132));
        txtNumeroOrden.setPhColor(new Color(0, 90, 132));
        txtNumeroOrden.setPlaceholder("Número de orden");
        txtNumeroOrden.setSelectionColor(new Color(0, 90, 132));
        txtNumeroOrden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumeroOrdenActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setForeground(new Color(0, 90, 132));
        jLabel12.setText("Fecha:");

        txtMotivo.setForeground(new Color(0, 90, 132));
        txtMotivo.setColorMaterial(new Color(0, 90, 132));
        txtMotivo.setPhColor(new Color(0, 90, 132));
        txtMotivo.setPlaceholder("Motivo");
        txtMotivo.setSelectionColor(new Color(0, 90, 132));
        txtMotivo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                txtMotivoKeyPressed(evt);
            }
        });

        chkCoseguro.setForeground(new Color(0, 90, 132));
        chkCoseguro.setText("Coseguro");
        chkCoseguro.setColorCheck(new Color(0, 90, 132));
        chkCoseguro.setColorUnCheck(new Color(0, 90, 132));
        chkCoseguro.setRippleColor(new Color(0, 90, 132));
        chkCoseguro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkCoseguroActionPerformed(evt);
            }
        });

        txtPrecioCoseguro.setForeground(new Color(0, 90, 132));
        txtPrecioCoseguro.setColorMaterial(new Color(0, 90, 132));
        txtPrecioCoseguro.setPhColor(new Color(0, 90, 132));
        txtPrecioCoseguro.setPlaceholder("Precio");
        txtPrecioCoseguro.setSelectionColor(new Color(0, 90, 132));

        txtRecienNacido.setForeground(new Color(0, 90, 132));
        txtRecienNacido.setColorMaterial(new Color(0, 90, 132));
        txtRecienNacido.setPhColor(new Color(0, 90, 132));
        txtRecienNacido.setPlaceholder("");
        txtRecienNacido.setSelectionColor(new Color(0, 90, 132));

        dcFechaOrden.setBackground(new Color(255, 255, 255));
        dcFechaOrden.setForeground(new Color(0, 90, 132));
        dcFechaOrden.setBgColor(new Color(0, 90, 132));
        dcFechaOrden.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        dcFechaOrden.setFormatDate("dd-MM-yyyy");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
                jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel10Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel10Layout.createSequentialGroup()
                                                .addComponent(txtMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel9)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cboServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtCama, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtNumeroOrden, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE))
                                        .addGroup(jPanel10Layout.createSequentialGroup()
                                                .addComponent(jLabel12)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(dcFechaOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(chkCoseguro, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtPrecioCoseguro, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtRecienNacido, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
                jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel10Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel9)
                                        .addComponent(cboServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtCama, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtNumeroOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(txtMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                                .addComponent(chkCoseguro, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                                .addComponent(txtPrecioCoseguro, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                                .addComponent(txtRecienNacido, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                        .addComponent(dcFechaOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel11.setBackground(new Color(255, 255, 255));

        txtBuscarPractica.setForeground(new Color(0, 90, 132));
        txtBuscarPractica.setBorderColor(new Color(0, 90, 132));
        txtBuscarPractica.setColorIcon(new Color(0, 90, 132));
        txtBuscarPractica.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SEARCH);
        txtBuscarPractica.setPhColor(new Color(0, 90, 132));
        txtBuscarPractica.setPlaceholder("");
        txtBuscarPractica.setSelectionColor(new Color(0, 0, 0));
        txtBuscarPractica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarPracticaActionPerformed(evt);
            }
        });
        txtBuscarPractica.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                txtBuscarPracticaKeyReleased(evt);
            }
        });

        tablaPracticas.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(255, 255, 255)));
        tablaPracticas.setForeground(new Color(255, 255, 255));
        tablaPracticas.setModel(new DefaultTableModel(
                new Object[][]{

                },
                new String[]{

                }
        ));
        tablaPracticas.setBackgoundHead(new Color(0, 90, 132));
        tablaPracticas.setBackgoundHover(new Color(0, 90, 132));
        tablaPracticas.setBorderHead(null);
        tablaPracticas.setBorderRows(null);
        tablaPracticas.setColorBorderHead(new Color(255, 255, 255));
        tablaPracticas.setColorBorderRows(new Color(255, 255, 255));
        tablaPracticas.setColorPrimaryText(new Color(0, 90, 132));
        tablaPracticas.setColorSecondary(new Color(255, 255, 255));
        tablaPracticas.setColorSecundaryText(new Color(0, 90, 132));
        tablaPracticas.setGridColor(new Color(255, 255, 255));
        tablaPracticas.setSelectionBackground(new Color(0, 90, 132));
        jScrollPane2.setViewportView(tablaPracticas);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setForeground(new Color(0, 90, 132));
        jLabel10.setText("Instrucciones:");

        lblInstrucciones.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jPanel13.setBackground(new Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Totales a cobrar por:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13), new Color(153, 153, 153))); // NOI18N

        jLabel61.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel61.setForeground(new Color(0, 90, 132));
        jLabel61.setText("Obra Social : $");

        lblTotalOS.setEditable(false);
        lblTotalOS.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblTotalOS.setForeground(new Color(0, 102, 204));
        lblTotalOS.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        lblTotalOS.setBorder(null);
        lblTotalOS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lblTotalOSActionPerformed(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel29.setForeground(new Color(0, 90, 132));
        jLabel29.setText("Particular: $");

        lblTotalParticular.setEditable(false);
        lblTotalParticular.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblTotalParticular.setForeground(new Color(0, 102, 204));
        lblTotalParticular.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        lblTotalParticular.setBorder(null);
        lblTotalParticular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lblTotalParticularActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
                jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel13Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel61)
                                        .addComponent(jLabel29))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblTotalParticular, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblTotalOS, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
                jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel61)
                                        .addComponent(lblTotalOS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel29)
                                        .addComponent(lblTotalParticular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel14.setBackground(new Color(255, 255, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ".", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13), new Color(153, 153, 153))); // NOI18N

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel39.setForeground(new Color(102, 0, 51));
        jLabel39.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel39.setText("Seña: $");

        lblSeña.setEditable(false);
        lblSeña.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblSeña.setForeground(new Color(102, 0, 51));
        lblSeña.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        lblSeña.setText("0");
        lblSeña.setBorder(null);
        lblSeña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lblSeñaActionPerformed(evt);
            }
        });

        jLabel55.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel55.setForeground(new Color(0, 90, 132));
        jLabel55.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel55.setText("Total: $");

        lblTotal.setEditable(false);
        lblTotal.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblTotal.setForeground(new Color(0, 102, 204));
        lblTotal.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        lblTotal.setBorder(null);
        lblTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lblTotalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
                jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel14Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel39, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel55, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(lblTotal)
                                        .addComponent(lblSeña, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE))
                                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
                jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel14Layout.createSequentialGroup()
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel39)
                                        .addComponent(lblSeña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel55)
                                        .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnSiguienteBono.setBackground(new Color(0, 90, 132));
        btnSiguienteBono.setText("Siguiente bono");
        btnSiguienteBono.setBackgroundHover(new Color(204, 204, 204));
        btnSiguienteBono.setForegroundIconHover(new Color(0, 90, 132));
        btnSiguienteBono.setHorizontalAlignment(SwingConstants.CENTER);
        btnSiguienteBono.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ADD);
        btnSiguienteBono.setPreferredSize(new java.awt.Dimension(130, 40));
        btnSiguienteBono.setRound(15);
        btnSiguienteBono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteBonoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
                jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel11Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane2)
                                        .addGroup(jPanel11Layout.createSequentialGroup()
                                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(jPanel11Layout.createSequentialGroup()
                                                                .addComponent(txtBuscarPractica, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(btnSiguienteBono, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(jPanel11Layout.createSequentialGroup()
                                                                .addComponent(jLabel10)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(lblInstrucciones, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(35, 35, 35)
                                                                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(0, 2, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
                jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel11Layout.createSequentialGroup()
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(txtBuscarPractica, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel11Layout.createSequentialGroup()
                                                .addComponent(btnSiguienteBono, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                                .addGap(4, 4, 4)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel11Layout.createSequentialGroup()
                                                .addGap(16, 16, 16)
                                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(lblInstrucciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addGroup(jPanel11Layout.createSequentialGroup()
                                                                .addComponent(jLabel10)
                                                                .addGap(0, 0, Short.MAX_VALUE)))
                                                .addContainerGap())))
        );

        jPanel12.setBackground(new Color(255, 255, 255));

        btnGrabarImprimir.setBackground(new Color(0, 90, 132));
        btnGrabarImprimir.setText("Grabar e imprimir");
        btnGrabarImprimir.setBackgroundHover(new Color(204, 204, 204));
        btnGrabarImprimir.setForegroundIconHover(new Color(0, 90, 132));
        btnGrabarImprimir.setHorizontalAlignment(SwingConstants.CENTER);
        btnGrabarImprimir.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.PAGES);
        btnGrabarImprimir.setPreferredSize(new java.awt.Dimension(130, 40));
        btnGrabarImprimir.setRound(20);
        btnGrabarImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGrabarImprimirActionPerformed(evt);
            }
        });

        btnGrabar.setBackground(new Color(0, 90, 132));
        btnGrabar.setText("Grabar");
        btnGrabar.setBackgroundHover(new Color(204, 204, 204));
        btnGrabar.setForegroundIconHover(new Color(0, 90, 132));
        btnGrabar.setHorizontalAlignment(SwingConstants.CENTER);
        btnGrabar.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SAVE);
        btnGrabar.setPreferredSize(new java.awt.Dimension(130, 40));
        btnGrabar.setRound(20);
        btnGrabar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGrabarActionPerformed(evt);
            }
        });

        btnReimprimir.setBackground(new Color(0, 90, 132));
        btnReimprimir.setText("Reimprimir");
        btnReimprimir.setBackgroundHover(new Color(204, 204, 204));
        btnReimprimir.setForegroundIconHover(new Color(0, 90, 132));
        btnReimprimir.setHorizontalAlignment(SwingConstants.CENTER);
        btnReimprimir.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.PRINT);
        btnReimprimir.setPreferredSize(new java.awt.Dimension(130, 40));
        btnReimprimir.setRound(20);
        btnReimprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReimprimirActionPerformed(evt);
            }
        });

        btnPresupuesto.setBackground(new Color(0, 90, 132));
        btnPresupuesto.setText("Presupuesto");
        btnPresupuesto.setBackgroundHover(new Color(204, 204, 204));
        btnPresupuesto.setForegroundIconHover(new Color(0, 90, 132));
        btnPresupuesto.setHorizontalAlignment(SwingConstants.CENTER);
        btnPresupuesto.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.REORDER);
        btnPresupuesto.setPreferredSize(new java.awt.Dimension(130, 40));
        btnPresupuesto.setRound(20);
        btnPresupuesto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPresupuestoActionPerformed(evt);
            }
        });

        btnResultados.setBackground(new Color(0, 90, 132));
        btnResultados.setText("Resultados");
        btnResultados.setBackgroundHover(new Color(204, 204, 204));
        btnResultados.setForegroundIconHover(new Color(0, 90, 132));
        btnResultados.setHorizontalAlignment(SwingConstants.CENTER);
        btnResultados.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ONDEMAND_VIDEO);
        btnResultados.setPreferredSize(new java.awt.Dimension(130, 40));
        btnResultados.setRound(20);
        btnResultados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResultadosActionPerformed(evt);
            }
        });

        btnCancelar.setBackground(new Color(0, 90, 132));
        btnCancelar.setText("Cancelar");
        btnCancelar.setBackgroundHover(new Color(204, 204, 204));
        btnCancelar.setForegroundIconHover(new Color(0, 90, 132));
        btnCancelar.setHorizontalAlignment(SwingConstants.CENTER);
        btnCancelar.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.CANCEL);
        btnCancelar.setPreferredSize(new java.awt.Dimension(130, 40));
        btnCancelar.setRound(20);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnSeñas.setBackground(new Color(0, 90, 132));
        btnSeñas.setBackgroundHover(new Color(204, 204, 204));
        btnSeñas.setForegroundIconHover(new Color(0, 90, 132));
        btnSeñas.setHorizontalAlignment(SwingConstants.CENTER);
        btnSeñas.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.MONETIZATION_ON);
        btnSeñas.setLabel("Señas");
        btnSeñas.setPreferredSize(new java.awt.Dimension(130, 40));
        btnSeñas.setRound(20);
        btnSeñas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeñasActionPerformed(evt);
            }
        });

        btnCertificadoAsistencia.setBackground(new Color(0, 90, 132));
        btnCertificadoAsistencia.setText("Certificado de asistencia");
        btnCertificadoAsistencia.setBackgroundHover(new Color(204, 204, 204));
        btnCertificadoAsistencia.setForegroundIconHover(new Color(0, 90, 132));
        btnCertificadoAsistencia.setHorizontalAlignment(SwingConstants.CENTER);
        btnCertificadoAsistencia.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.BLUR_LINEAR);
        btnCertificadoAsistencia.setPreferredSize(new java.awt.Dimension(130, 40));
        btnCertificadoAsistencia.setRound(20);
        btnCertificadoAsistencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCertificadoAsistenciaActionPerformed(evt);
            }
        });

        btnImprimirListado.setBackground(new Color(0, 90, 132));
        btnImprimirListado.setText("Imprimir listado");
        btnImprimirListado.setBackgroundHover(new Color(204, 204, 204));
        btnImprimirListado.setForegroundIconHover(new Color(0, 90, 132));
        btnImprimirListado.setHorizontalAlignment(SwingConstants.CENTER);
        btnImprimirListado.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.LIST);
        btnImprimirListado.setPreferredSize(new java.awt.Dimension(130, 40));
        btnImprimirListado.setRound(20);
        btnImprimirListado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirListadoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
                jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel12Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                                                .addComponent(btnGrabarImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnGrabar, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnReimprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnPresupuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnResultados, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                                                .addComponent(btnImprimirListado, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnCertificadoAsistencia, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(149, 149, 149)
                                                .addComponent(btnSeñas, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
                jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel12Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnGrabarImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnGrabar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnPresupuesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnResultados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnReimprimir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnImprimirListado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnCertificadoAsistencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnSeñas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout panelCargarOrdenLayout = new javax.swing.GroupLayout(panelCargarOrden);
        panelCargarOrden.setLayout(panelCargarOrdenLayout);
        panelCargarOrdenLayout.setHorizontalGroup(
                panelCargarOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelCargarOrdenLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelCargarOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        panelCargarOrdenLayout.setVerticalGroup(
                panelCargarOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelCargarOrdenLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );

        panelContenedor.add(panelCargarOrden);

        panelFacturacion.setBackground(new Color(255, 255, 255));
        panelFacturacion.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel15.setBackground(new Color(255, 255, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setForeground(new Color(0, 90, 132));
        jLabel13.setText("Desde:");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setForeground(new Color(0, 90, 132));
        jLabel14.setText("Hasta:");

        chkObrasocial.setForeground(new Color(0, 90, 132));
        chkObrasocial.setText("Obra Social");
        chkObrasocial.setColorCheck(new Color(0, 90, 132));
        chkObrasocial.setColorUnCheck(new Color(0, 90, 132));
        chkObrasocial.setRippleColor(new Color(0, 90, 132));

        btnFiltrar.setBackground(new Color(0, 90, 132));
        btnFiltrar.setText("Filtrar");
        btnFiltrar.setBackgroundHover(new Color(204, 204, 204));
        btnFiltrar.setForegroundIconHover(new Color(0, 90, 132));
        btnFiltrar.setHorizontalAlignment(SwingConstants.CENTER);
        btnFiltrar.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.FILTER_LIST);
        btnFiltrar.setPreferredSize(new java.awt.Dimension(130, 40));
        btnFiltrar.setRound(10);
        btnFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarActionPerformed(evt);
            }
        });

        dcFechaDesde.setBackground(new Color(255, 255, 255));
        dcFechaDesde.setForeground(new Color(0, 90, 132));
        dcFechaDesde.setBgColor(new Color(0, 90, 132));
        dcFechaDesde.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        dcFechaDesde.setFormatDate("dd-MM-yyyy");

        dcFechaHasta.setBackground(new Color(255, 255, 255));
        dcFechaHasta.setForeground(new Color(0, 90, 132));
        dcFechaHasta.setBgColor(new Color(0, 90, 132));
        dcFechaHasta.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        dcFechaHasta.setFormatDate("dd-MM-yyyy");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
                jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel15Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dcFechaDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dcFechaHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(chkObrasocial, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(54, 54, 54)
                                .addComponent(btnFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
                jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                                .addContainerGap(21, Short.MAX_VALUE)
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(dcFechaHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(dcFechaDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(btnFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(chkObrasocial, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
        );

        jPanel16.setBackground(new Color(255, 255, 255));

        jPanel18.setBackground(new Color(255, 255, 255));

        tablaDetalle.setForeground(new Color(255, 255, 255));
        tablaDetalle.setModel(new DefaultTableModel(
                new Object[][]{

                },
                new String[]{

                }
        ));
        tablaDetalle.setBackgoundHead(new Color(0, 90, 132));
        tablaDetalle.setBackgoundHover(new Color(0, 90, 132));
        tablaDetalle.setColorBorderHead(new Color(255, 255, 255));
        tablaDetalle.setColorBorderRows(new Color(255, 255, 255));
        tablaDetalle.setGridColor(new Color(204, 204, 204));
        jScrollPane4.setViewportView(tablaDetalle);

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
                jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel18Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
                jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel18Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 389, Short.MAX_VALUE)
                                .addContainerGap())
        );

        jPanel19.setBackground(new Color(255, 255, 255));

        tablaOrdenes.setForeground(new Color(255, 255, 255));
        tablaOrdenes.setModel(new DefaultTableModel(
                new Object[][]{

                },
                new String[]{

                }
        ));
        tablaOrdenes.setBackgoundHead(new Color(0, 90, 132));
        tablaOrdenes.setBackgoundHover(new Color(0, 90, 132));
        tablaOrdenes.setColorBorderHead(new Color(255, 255, 255));
        tablaOrdenes.setColorBorderRows(new Color(255, 255, 255));
        tablaOrdenes.setGridColor(new Color(204, 204, 204));
        jScrollPane3.setViewportView(tablaOrdenes);

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
                jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel19Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane3)
                                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
                jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addContainerGap())
        );

        txtBuscarOrden.setForeground(new Color(0, 90, 132));
        txtBuscarOrden.setBorderColor(new Color(0, 90, 132));
        txtBuscarOrden.setColorIcon(new Color(0, 90, 132));
        txtBuscarOrden.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SEARCH);
        txtBuscarOrden.setPhColor(new Color(0, 90, 132));
        txtBuscarOrden.setPlaceholder("");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
                jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel16Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                        .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(txtBuscarOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
                jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(txtBuscarOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );

        jPanel17.setBackground(new Color(255, 255, 255));

        progresoOrdenes.setBackground(new Color(255, 255, 255));
        progresoOrdenes.setForeground(new Color(0, 90, 132));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setForeground(new Color(0, 90, 132));
        jLabel16.setText("Total órdenes a enviar:");

        lblTotalOrdenes.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setForeground(new Color(0, 90, 132));
        jLabel18.setText("Total $:");

        lblMontoTotalOrdenes.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        btnImprimirResumen.setBackground(new Color(0, 90, 132));
        btnImprimirResumen.setText("Imprimir resumen");
        btnImprimirResumen.setBackgroundHover(new Color(204, 204, 204));
        btnImprimirResumen.setForegroundIconHover(new Color(0, 90, 132));
        btnImprimirResumen.setHorizontalAlignment(SwingConstants.CENTER);
        btnImprimirResumen.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.PRINT);
        btnImprimirResumen.setPreferredSize(new java.awt.Dimension(130, 40));
        btnImprimirResumen.setRound(20);
        btnImprimirResumen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirResumenActionPerformed(evt);
            }
        });

        btnLimpiar.setBackground(new Color(0, 90, 132));
        btnLimpiar.setText("Limpiar");
        btnLimpiar.setBackgroundHover(new Color(204, 204, 204));
        btnLimpiar.setForegroundIconHover(new Color(0, 90, 132));
        btnLimpiar.setHorizontalAlignment(SwingConstants.CENTER);
        btnLimpiar.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.CLEAR_ALL);
        btnLimpiar.setPreferredSize(new java.awt.Dimension(130, 40));
        btnLimpiar.setRound(20);
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
                jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel17Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel17Layout.createSequentialGroup()
                                                .addComponent(btnImprimirResumen, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 100, Short.MAX_VALUE))
                                        .addComponent(progresoOrdenes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblTotalOrdenes, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblMontoTotalOrdenes, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(69, 69, 69))
        );
        jPanel17Layout.setVerticalGroup(
                jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel17Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(lblMontoTotalOrdenes, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(progresoOrdenes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(lblTotalOrdenes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnImprimirResumen, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                        .addComponent(btnLimpiar, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)))
        );

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setForeground(new Color(0, 90, 132));
        jLabel15.setText("Ingrese los parametros para el filtrado");

        javax.swing.GroupLayout panelFacturacionLayout = new javax.swing.GroupLayout(panelFacturacion);
        panelFacturacion.setLayout(panelFacturacionLayout);
        panelFacturacionLayout.setHorizontalGroup(
                panelFacturacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelFacturacionLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelFacturacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(panelFacturacionLayout.createSequentialGroup()
                                                .addGroup(panelFacturacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel15))
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        panelFacturacionLayout.setVerticalGroup(
                panelFacturacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelFacturacionLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel15)
                                .addGap(2, 2, 2)
                                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        panelContenedor.add(panelFacturacion);

        panelUtilidades.setBackground(new Color(255, 255, 255));
        panelUtilidades.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnAnalisis.setBackground(new Color(0, 90, 132));
        btnAnalisis.setText("Análisis");
        btnAnalisis.setBackgroundHover(new Color(204, 204, 204));
        btnAnalisis.setFont(new java.awt.Font("Roboto Bold", 1, 18)); // NOI18N
        btnAnalisis.setForegroundIconHover(new Color(0, 90, 132));
        btnAnalisis.setHorizontalAlignment(SwingConstants.CENTER);
        btnAnalisis.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ASSESSMENT);
        btnAnalisis.setPreferredSize(new java.awt.Dimension(130, 40));
        btnAnalisis.setRound(30);
        btnAnalisis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnalisisActionPerformed(evt);
            }
        });

        btnMedicos.setBackground(new Color(0, 90, 132));
        btnMedicos.setText("Médicos");
        btnMedicos.setBackgroundHover(new Color(204, 204, 204));
        btnMedicos.setFont(new java.awt.Font("Roboto Bold", 1, 18)); // NOI18N
        btnMedicos.setForegroundIconHover(new Color(0, 90, 132));
        btnMedicos.setHorizontalAlignment(SwingConstants.CENTER);
        btnMedicos.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.HEALING);
        btnMedicos.setPreferredSize(new java.awt.Dimension(130, 40));
        btnMedicos.setRound(30);
        btnMedicos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMedicosActionPerformed(evt);
            }
        });

        btnDescargarDDJJ.setBackground(new Color(0, 90, 132));
        btnDescargarDDJJ.setText("Descargar DDJJ");
        btnDescargarDDJJ.setBackgroundHover(new Color(204, 204, 204));
        btnDescargarDDJJ.setFont(new java.awt.Font("Roboto Bold", 1, 18)); // NOI18N
        btnDescargarDDJJ.setForegroundIconHover(new Color(0, 90, 132));
        btnDescargarDDJJ.setHorizontalAlignment(SwingConstants.CENTER);
        btnDescargarDDJJ.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.WRAP_TEXT);
        btnDescargarDDJJ.setPreferredSize(new java.awt.Dimension(130, 40));
        btnDescargarDDJJ.setRound(30);
        btnDescargarDDJJ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDescargarDDJJActionPerformed(evt);
            }
        });

        btnHistoriasClinicas.setBackground(new Color(0, 90, 132));
        btnHistoriasClinicas.setText("Historias clínicas");
        btnHistoriasClinicas.setBackgroundHover(new Color(204, 204, 204));
        btnHistoriasClinicas.setFont(new java.awt.Font("Roboto Bold", 1, 18)); // NOI18N
        btnHistoriasClinicas.setForegroundIconHover(new Color(0, 90, 132));
        btnHistoriasClinicas.setHorizontalAlignment(SwingConstants.CENTER);
        btnHistoriasClinicas.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.FOLDER_OPEN);
        btnHistoriasClinicas.setPreferredSize(new java.awt.Dimension(130, 40));
        btnHistoriasClinicas.setRound(30);
        btnHistoriasClinicas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistoriasClinicasActionPerformed(evt);
            }
        });

        btnNomenclador.setBackground(new Color(0, 90, 132));
        btnNomenclador.setText("Nomenclador");
        btnNomenclador.setBackgroundHover(new Color(204, 204, 204));
        btnNomenclador.setFont(new java.awt.Font("Roboto Bold", 1, 18)); // NOI18N
        btnNomenclador.setForegroundIconHover(new Color(0, 90, 132));
        btnNomenclador.setHorizontalAlignment(SwingConstants.CENTER);
        btnNomenclador.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.BOOK);
        btnNomenclador.setPreferredSize(new java.awt.Dimension(130, 40));
        btnNomenclador.setRound(30);
        btnNomenclador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNomencladorActionPerformed(evt);
            }
        });

        btnExportarDatos.setBackground(new Color(0, 90, 132));
        btnExportarDatos.setText("Exportar datos");
        btnExportarDatos.setBackgroundHover(new Color(204, 204, 204));
        btnExportarDatos.setFont(new java.awt.Font("Roboto Bold", 1, 18)); // NOI18N
        btnExportarDatos.setForegroundIconHover(new Color(0, 90, 132));
        btnExportarDatos.setHorizontalAlignment(SwingConstants.CENTER);
        btnExportarDatos.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.VERTICAL_ALIGN_TOP);
        btnExportarDatos.setPreferredSize(new java.awt.Dimension(130, 40));
        btnExportarDatos.setRound(30);
        btnExportarDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarDatosActionPerformed(evt);
            }
        });

        btnBioquimicos.setBackground(new Color(0, 90, 132));
        btnBioquimicos.setText("Bioquimicos");
        btnBioquimicos.setBackgroundHover(new Color(204, 204, 204));
        btnBioquimicos.setFont(new java.awt.Font("Roboto Bold", 1, 18)); // NOI18N
        btnBioquimicos.setForegroundIconHover(new Color(0, 90, 132));
        btnBioquimicos.setHorizontalAlignment(SwingConstants.CENTER);
        btnBioquimicos.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.PERSON_PIN_CIRCLE);
        btnBioquimicos.setPreferredSize(new java.awt.Dimension(130, 40));
        btnBioquimicos.setRound(30);
        btnBioquimicos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBioquimicosActionPerformed(evt);
            }
        });

        btnSecciones.setBackground(new Color(0, 90, 132));
        btnSecciones.setText("Secciones");
        btnSecciones.setBackgroundHover(new Color(204, 204, 204));
        btnSecciones.setFont(new java.awt.Font("Roboto Bold", 1, 18)); // NOI18N
        btnSecciones.setForegroundIconHover(new Color(0, 90, 132));
        btnSecciones.setHorizontalAlignment(SwingConstants.CENTER);
        btnSecciones.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SELECT_ALL);
        btnSecciones.setPreferredSize(new java.awt.Dimension(130, 40));
        btnSecciones.setRound(30);
        btnSecciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeccionesActionPerformed(evt);
            }
        });

        btnInformes.setBackground(new Color(0, 90, 132));
        btnInformes.setText("Informes");
        btnInformes.setBackgroundHover(new Color(204, 204, 204));
        btnInformes.setFont(new java.awt.Font("Roboto Bold", 1, 18)); // NOI18N
        btnInformes.setForegroundIconHover(new Color(0, 90, 132));
        btnInformes.setHorizontalAlignment(SwingConstants.CENTER);
        btnInformes.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.REPORT);
        btnInformes.setPreferredSize(new java.awt.Dimension(130, 40));
        btnInformes.setRound(30);
        btnInformes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInformesActionPerformed(evt);
            }
        });

        btnUsuarios.setBackground(new Color(0, 90, 132));
        btnUsuarios.setText("Usuarios");
        btnUsuarios.setBackgroundHover(new Color(204, 204, 204));
        btnUsuarios.setFont(new java.awt.Font("Roboto Bold", 1, 18)); // NOI18N
        btnUsuarios.setForegroundIconHover(new Color(0, 90, 132));
        btnUsuarios.setHorizontalAlignment(SwingConstants.CENTER);
        btnUsuarios.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.PERSON);
        btnUsuarios.setPreferredSize(new java.awt.Dimension(130, 40));
        btnUsuarios.setRound(30);
        btnUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuariosActionPerformed(evt);
            }
        });

        btnDescargas.setBackground(new Color(0, 90, 132));
        btnDescargas.setText("Descargas");
        btnDescargas.setBackgroundHover(new Color(204, 204, 204));
        btnDescargas.setFont(new java.awt.Font("Roboto Bold", 1, 18)); // NOI18N
        btnDescargas.setForegroundIconHover(new Color(0, 90, 132));
        btnDescargas.setHorizontalAlignment(SwingConstants.CENTER);
        btnDescargas.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.VERTICAL_ALIGN_BOTTOM);
        btnDescargas.setPreferredSize(new java.awt.Dimension(130, 40));
        btnDescargas.setRound(30);
        btnDescargas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDescargasActionPerformed(evt);
            }
        });

        btnObrasSociales.setBackground(new Color(0, 90, 132));
        btnObrasSociales.setText("Obras Sociales");
        btnObrasSociales.setBackgroundHover(new Color(204, 204, 204));
        btnObrasSociales.setFont(new java.awt.Font("Roboto Bold", 1, 18)); // NOI18N
        btnObrasSociales.setForegroundIconHover(new Color(0, 90, 132));
        btnObrasSociales.setHorizontalAlignment(SwingConstants.CENTER);
        btnObrasSociales.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.BUSINESS);
        btnObrasSociales.setPreferredSize(new java.awt.Dimension(130, 40));
        btnObrasSociales.setRound(30);
        btnObrasSociales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnObrasSocialesActionPerformed(evt);
            }
        });

        btnActualizarBD.setBackground(new Color(0, 90, 132));
        btnActualizarBD.setText("Actualizar BD");
        btnActualizarBD.setBackgroundHover(new Color(204, 204, 204));
        btnActualizarBD.setFont(new java.awt.Font("Roboto Bold", 1, 18)); // NOI18N
        btnActualizarBD.setForegroundIconHover(new Color(0, 90, 132));
        btnActualizarBD.setHorizontalAlignment(SwingConstants.CENTER);
        btnActualizarBD.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.DATA_USAGE);
        btnActualizarBD.setPreferredSize(new java.awt.Dimension(130, 40));
        btnActualizarBD.setRound(30);
        btnActualizarBD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarBDActionPerformed(evt);
            }
        });

        btnHistorico.setBackground(new Color(0, 90, 132));
        btnHistorico.setText("Histórico");
        btnHistorico.setBackgroundHover(new Color(204, 204, 204));
        btnHistorico.setFont(new java.awt.Font("Roboto Bold", 1, 18)); // NOI18N
        btnHistorico.setForegroundIconHover(new Color(0, 90, 132));
        btnHistorico.setHorizontalAlignment(SwingConstants.CENTER);
        btnHistorico.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.HISTORY);
        btnHistorico.setPreferredSize(new java.awt.Dimension(130, 40));
        btnHistorico.setRound(30);
        btnHistorico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistoricoActionPerformed(evt);
            }
        });

        btnPracticas.setBackground(new Color(0, 90, 132));
        btnPracticas.setText("Prácticas");
        btnPracticas.setBackgroundHover(new Color(204, 204, 204));
        btnPracticas.setFont(new java.awt.Font("Roboto Bold", 1, 18)); // NOI18N
        btnPracticas.setForegroundIconHover(new Color(0, 90, 132));
        btnPracticas.setHorizontalAlignment(SwingConstants.CENTER);
        btnPracticas.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.STYLE);
        btnPracticas.setPreferredSize(new java.awt.Dimension(130, 40));
        btnPracticas.setRound(30);
        btnPracticas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPracticasActionPerformed(evt);
            }
        });

        btnAyuda.setBackground(new Color(0, 90, 132));
        btnAyuda.setText("Ayuda");
        btnAyuda.setBackgroundHover(new Color(204, 204, 204));
        btnAyuda.setFont(new java.awt.Font("Roboto Bold", 1, 18)); // NOI18N
        btnAyuda.setForegroundIconHover(new Color(0, 90, 132));
        btnAyuda.setHorizontalAlignment(SwingConstants.CENTER);
        btnAyuda.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.HELP_OUTLINE);
        btnAyuda.setPreferredSize(new java.awt.Dimension(130, 40));
        btnAyuda.setRound(30);
        btnAyuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAyudaActionPerformed(evt);
            }
        });

        btnPacientes.setBackground(new Color(0, 90, 132));
        btnPacientes.setText("Pacientes");
        btnPacientes.setBackgroundHover(new Color(204, 204, 204));
        btnPacientes.setFont(new java.awt.Font("Roboto Bold", 1, 18)); // NOI18N
        btnPacientes.setForegroundIconHover(new Color(0, 90, 132));
        btnPacientes.setHorizontalAlignment(SwingConstants.CENTER);
        btnPacientes.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.PEOPLE);
        btnPacientes.setPreferredSize(new java.awt.Dimension(130, 40));
        btnPacientes.setRound(30);
        btnPacientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPacientesActionPerformed(evt);
            }
        });

        btnActualizarPrecio.setBackground(new Color(0, 90, 132));
        btnActualizarPrecio.setText("Actualizar precio");
        btnActualizarPrecio.setBackgroundHover(new Color(204, 204, 204));
        btnActualizarPrecio.setFont(new java.awt.Font("Roboto Bold", 1, 18)); // NOI18N
        btnActualizarPrecio.setForegroundIconHover(new Color(0, 90, 132));
        btnActualizarPrecio.setHorizontalAlignment(SwingConstants.CENTER);
        btnActualizarPrecio.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.MONETIZATION_ON);
        btnActualizarPrecio.setPreferredSize(new java.awt.Dimension(130, 40));
        btnActualizarPrecio.setRound(30);
        btnActualizarPrecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarPrecioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelUtilidadesLayout = new javax.swing.GroupLayout(panelUtilidades);
        panelUtilidades.setLayout(panelUtilidadesLayout);
        panelUtilidadesLayout.setHorizontalGroup(
                panelUtilidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelUtilidadesLayout.createSequentialGroup()
                                .addGap(67, 67, 67)
                                .addGroup(panelUtilidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panelUtilidadesLayout.createSequentialGroup()
                                                .addGroup(panelUtilidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(btnAnalisis, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btnHistoriasClinicas, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btnDescargarDDJJ, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btnMedicos, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(panelUtilidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(btnBioquimicos, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btnExportarDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btnNomenclador, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btnSecciones, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(panelUtilidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(btnObrasSociales, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btnUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btnInformes, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btnDescargas, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(panelUtilidadesLayout.createSequentialGroup()
                                                .addGroup(panelUtilidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(btnPracticas, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btnAyuda, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(panelUtilidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(btnActualizarBD, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btnHistorico, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(panelUtilidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(btnPacientes, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btnActualizarPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap(101, Short.MAX_VALUE))
        );
        panelUtilidadesLayout.setVerticalGroup(
                panelUtilidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelUtilidadesLayout.createSequentialGroup()
                                .addContainerGap(68, Short.MAX_VALUE)
                                .addGroup(panelUtilidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panelUtilidadesLayout.createSequentialGroup()
                                                .addComponent(btnObrasSociales, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnInformes, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnDescargas, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(panelUtilidadesLayout.createSequentialGroup()
                                                .addComponent(btnBioquimicos, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnExportarDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnNomenclador, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnSecciones, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(panelUtilidadesLayout.createSequentialGroup()
                                                .addComponent(btnAnalisis, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnHistoriasClinicas, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnDescargarDDJJ, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnMedicos, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(panelUtilidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panelUtilidadesLayout.createSequentialGroup()
                                                .addGap(97, 97, 97)
                                                .addComponent(btnPacientes, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(panelUtilidadesLayout.createSequentialGroup()
                                                .addGroup(panelUtilidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(btnActualizarBD, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btnActualizarPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addComponent(btnHistorico, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(panelUtilidadesLayout.createSequentialGroup()
                                                .addComponent(btnPracticas, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnAyuda, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(68, Short.MAX_VALUE))
        );

        panelContenedor.add(panelUtilidades);

        getContentPane().add(panelContenedor);

        pack();
    }// </editor-fold>

    private void btnCargarPacienteActionPerformed(java.awt.event.ActionEvent evt) {

        panelCargarPaciente.setVisible(true);
        panelCargarOrden.setVisible(false);
        panelFacturacion.setVisible(false);
        panelUtilidades.setVisible(false);

    }

    private void btnCargarResultadosActionPerformed(java.awt.event.ActionEvent evt) {


    }

    private void btnFacturacionActionPerformed(java.awt.event.ActionEvent evt) {

        panelCargarPaciente.setVisible(false);
        panelCargarOrden.setVisible(false);
        panelFacturacion.setVisible(true);
        panelUtilidades.setVisible(false);

    }

    private void btnUtilitariosActionPerformed(java.awt.event.ActionEvent evt) {

        panelCargarPaciente.setVisible(false);
        panelCargarOrden.setVisible(false);
        panelFacturacion.setVisible(false);
        panelUtilidades.setVisible(true);

    }

    private void rSButtonMaterialIconOne5ActionPerformed(java.awt.event.ActionEvent evt) {

        System.exit(0);

    }

    private void btnCargarConsultaActionPerformed(java.awt.event.ActionEvent evt) {

        panelCargarPaciente.setVisible(false);
        panelCargarOrden.setVisible(true);
        panelFacturacion.setVisible(false);
        panelUtilidades.setVisible(false);
        txtMedico.requestFocus();

    }

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {
        new BuscarPersona_nuevo(null, true).setVisible(true);
        txtDNI.requestFocus();
    }

    private void btnPatologiasActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void btnSinDNIActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void lblTotalOSActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void lblTotalParticularActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void lblSeñaActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void lblTotalActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void btnGrabarImprimirActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void btnGrabarActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void btnReimprimirActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void btnPresupuestoActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void btnResultadosActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void btnSeñasActionPerformed(java.awt.event.ActionEvent evt) {
        new Seña_nuevo(null, true).setVisible(true);
    }

    private void btnCertificadoAsistenciaActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void btnImprimirListadoActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void btnSiguienteBonoActionPerformed(java.awt.event.ActionEvent evt) {
        new Proxima_Orden_nuevo(this, true).setVisible(true);
    }

    private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void btnImprimirResumenActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void btnMedicosActionPerformed(java.awt.event.ActionEvent evt) {
        new AltaMedico_nuevo(this, true).setVisible(true);
    }

    private void btnAnalisisActionPerformed(java.awt.event.ActionEvent evt) {
        new Analisis_nuevo(this, true).setVisible(true);
    }

    private void btnDescargarDDJJActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void btnHistoriasClinicasActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void btnNomencladorActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void btnExportarDatosActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void btnBioquimicosActionPerformed(java.awt.event.ActionEvent evt) {
        new Agregar_Profesional_nuevo(this, true).setVisible(true);
    }

    private void btnSeccionesActionPerformed(java.awt.event.ActionEvent evt) {
        new Secciones_nuevo(this, true).setVisible(true);
    }

    private void btnInformesActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void btnUsuariosActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void btnDescargasActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void btnObrasSocialesActionPerformed(java.awt.event.ActionEvent evt) {
        new AgregarOS_nuevo(this, true).setVisible(true);
    }

    private void btnActualizarBDActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void btnHistoricoActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void btnPracticasActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void btnAyudaActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void btnPacientesActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void btnActualizarPrecioActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void panelBotonesMousePressed(MouseEvent evt) {
        x = evt.getX();
        y = evt.getY();
    }

    private void panelBotonesMouseDragged(MouseEvent evt) {
        this.setLocation(this.getLocation().x + evt.getX() - x, this.getLocation().y + evt.getY() - y);
    }

    private void rSButtonIconOne1ActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }

    private void rSButtonIconOne2ActionPerformed(java.awt.event.ActionEvent evt) {
        this.setExtendedState(ICONIFIED);
    }

    private void txtDNIFocusLost(java.awt.event.FocusEvent evt) {
    }

    private void txtDNIKeyTyped(KeyEvent evt) {
        char c = evt.getKeyChar();
        if (c < '0' || c > '9') {
            evt.consume();
        }
    }

    private void txtObraSocialActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void txtDNIActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void txtApellidoFocusGained(java.awt.event.FocusEvent evt) {
        if (!txtApellido.getText().equals("")) {
            txtObraSocial.requestFocus();
        }
    }

    private void txtObraSocialKeyPressed(KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!txtObraSocial.getText().equals("")) {
                if (txtNumeroAfiliado.getText().equals("")) {
                    txtNumeroAfiliado.setText("0");
                }
                txtNumeroAfiliado.requestFocus();
            }
        }
    }

    private void txtNumeroAfiliadoKeyPressed(KeyEvent evt) {
    }

    private void txtMedicoActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void txtNumeroOrdenActionPerformed(java.awt.event.ActionEvent evt) {
        dcFechaOrden.requestFocus();
    }

    private void chkCoseguroActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void txtBuscarPracticaKeyReleased(KeyEvent evt) {
    }

    private void txtBuscarPracticaActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void tablaPacientesMouseClicked(MouseEvent evt) {
    }

    private void CargarActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void ResultadosActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void ModificaActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void txtBuscarKeyReleased(KeyEvent evt) {
    }

    private void txtApellidoKeyPressed(KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtNombre.requestFocus();
        }
    }

    private void txtNombreKeyPressed(KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cboSexo.requestFocus();
        }
    }

    private void cboSexoKeyPressed(KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtLocalidad.requestFocus();
        }
    }

    private void txtLocalidadKeyPressed(KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtDireccion.requestFocus();
        }
    }

    private void txtDireccionKeyPressed(KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtTelefono.requestFocus();
        }
    }

    private void txtTelefonoKeyPressed(KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtCelular.requestFocus();
        }
    }

    private void txtCelularKeyPressed(KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtMail.requestFocus();
        }
    }

    private void txtMailKeyPressed(KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            dcFechaNacimiento.requestFocus();
        }
    }

    private void cboServicioKeyPressed(KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (cboServicio.getSelectedItem().toString().equals("Internación")) {
                txtCama.setEnabled(true);
                txtCama.requestFocus();
            } else {
                txtNumeroOrden.requestFocus();
            }
        }
    }

    private void txtCamaKeyPressed(KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txtCama.getText().equals("")) {
                txtCama.requestFocus();
            } else {
                txtNumeroOrden.requestFocus();
            }
        }
    }

    private void txtMedicoKeyPressed(KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        }
    }

    private void txtMotivoKeyPressed(KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txtMotivo.getText().equals("Recién Nacidos") || txtMotivo.getText().equals("VIH")) {
                txtRecienNacido.setEnabled(true);
                if (txtMotivo.getText().equals("Recién Nacidos")) {
                    txtRecienNacido.setPlaceholder("Nombre RN:");
                } else {
                    txtRecienNacido.setPlaceholder("Nombre Codif:");
                }
                txtRecienNacido.requestFocus();
            } else {
                txtRecienNacido.setEnabled(false);
                chkCoseguro.requestFocus();
            }
        }
    }

    private void btnCargarResultados1ActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private javax.swing.JMenuItem Cargar;
    private javax.swing.JMenuItem Modifica;
    private javax.swing.JMenuItem Resultados;
    private RSMaterialComponent.RSButtonMaterialIconOne btnActualizarBD;
    private RSMaterialComponent.RSButtonMaterialIconOne btnActualizarPrecio;
    private RSMaterialComponent.RSButtonMaterialIconOne btnAnalisis;
    private RSMaterialComponent.RSButtonMaterialIconOne btnAyuda;
    private RSMaterialComponent.RSButtonMaterialIconOne btnBioquimicos;
    private RSMaterialComponent.RSButtonMaterialIconOne btnBorrar;
    private RSMaterialComponent.RSButtonMaterialIconOne btnBuscar;
    private RSMaterialComponent.RSButtonMaterialIconOne btnCancelar;
    private RSMaterialComponent.RSButtonMaterialIconOne btnCargarConsulta;
    private RSMaterialComponent.RSButtonMaterialIconOne btnCargarPaciente;
    private RSMaterialComponent.RSButtonMaterialIconOne btnCargarResultados;
    private RSMaterialComponent.RSButtonMaterialIconOne btnCargarResultados1;
    private RSMaterialComponent.RSButtonMaterialIconOne btnCertificadoAsistencia;
    private RSMaterialComponent.RSButtonMaterialIconOne btnDescargarDDJJ;
    private RSMaterialComponent.RSButtonMaterialIconOne btnDescargas;
    private RSMaterialComponent.RSButtonMaterialIconOne btnExportarDatos;
    private RSMaterialComponent.RSButtonMaterialIconOne btnFacturacion;
    private RSMaterialComponent.RSButtonMaterialIconOne btnFiltrar;
    private RSMaterialComponent.RSButtonMaterialIconOne btnGrabar;
    private RSMaterialComponent.RSButtonMaterialIconOne btnGrabarImprimir;
    private RSMaterialComponent.RSButtonMaterialIconOne btnHistoriasClinicas;
    private RSMaterialComponent.RSButtonMaterialIconOne btnHistorico;
    private RSMaterialComponent.RSButtonMaterialIconOne btnImprimirListado;
    private RSMaterialComponent.RSButtonMaterialIconOne btnImprimirResumen;
    private RSMaterialComponent.RSButtonMaterialIconOne btnInformes;
    private RSMaterialComponent.RSButtonMaterialIconOne btnLimpiar;
    private RSMaterialComponent.RSButtonMaterialIconOne btnMedicos;
    private RSMaterialComponent.RSButtonMaterialIconOne btnModificar;
    private RSMaterialComponent.RSButtonMaterialIconOne btnNomenclador;
    private RSMaterialComponent.RSButtonMaterialIconOne btnObrasSociales;
    private RSMaterialComponent.RSButtonMaterialIconOne btnPacientes;
    private RSMaterialComponent.RSButtonMaterialIconOne btnPatologias;
    private RSMaterialComponent.RSButtonMaterialIconOne btnPracticas;
    private RSMaterialComponent.RSButtonMaterialIconOne btnPresupuesto;
    private RSMaterialComponent.RSButtonMaterialIconOne btnReimprimir;
    private RSMaterialComponent.RSButtonMaterialIconOne btnResultados;
    private RSMaterialComponent.RSButtonMaterialIconOne btnSecciones;
    private RSMaterialComponent.RSButtonMaterialIconOne btnSeñas;
    private RSMaterialComponent.RSButtonMaterialIconOne btnSiguienteBono;
    private RSMaterialComponent.RSButtonMaterialIconOne btnSinDNI;
    private RSMaterialComponent.RSButtonMaterialIconOne btnUsuarios;
    private RSMaterialComponent.RSButtonMaterialIconOne btnUtilitarios;
    private RSMaterialComponent.RSComboBox cboServicio;
    private RSMaterialComponent.RSComboBox cboSexo;
    private RSMaterialComponent.RSCheckBoxMaterial chkCoseguro;
    private RSMaterialComponent.RSCheckBoxMaterial chkObrasocial;
    private newscomponents.RSDateChooser dcFechaDesde;
    private newscomponents.RSDateChooser dcFechaHasta;
    private newscomponents.RSDateChooser dcFechaNacimiento;
    private newscomponents.RSDateChooser dcFechaOrden;
    private newscomponents.RSDateChooser dcFechaPacienteDesde;
    private newscomponents.RSDateChooser dcFechaPacienteHasta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lblEdad;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblHora;
    private javax.swing.JLabel lblInstrucciones;
    private javax.swing.JLabel lblMontoTotalOrdenes;
    private javax.swing.JLabel lblNumeroProtocolo;
    private javax.swing.JLabel lblPaciente;
    private javax.swing.JTextField lblSeña;
    private javax.swing.JTextField lblTotal;
    private javax.swing.JTextField lblTotalOS;
    private javax.swing.JLabel lblTotalOrdenes;
    private javax.swing.JTextField lblTotalParticular;
    private javax.swing.JPanel panelBotones;
    private javax.swing.JPanel panelCargarOrden;
    private javax.swing.JPanel panelCargarPaciente;
    private javax.swing.JPanel panelContenedor;
    private javax.swing.JPanel panelFacturacion;
    private javax.swing.JPanel panelUtilidades;
    private rojeru_san.complementos.PopuMenu popuMenu1;
    private JProgressBar progresoOrdenes;
    private RSMaterialComponent.RSButtonIconOne rSButtonIconOne1;
    private RSMaterialComponent.RSButtonIconOne rSButtonIconOne2;
    private RSMaterialComponent.RSButtonMaterialIconOne rSButtonMaterialIconOne5;
    private RSMaterialComponent.RSLabelIcon rSLabelIcon1;
    private RSMaterialComponent.RSTableMetroCustom tablaDetalle;
    private RSMaterialComponent.RSTableMetroCustom tablaOrdenes;
    private RSMaterialComponent.RSTableMetroCustom tablaPacientes;
    private RSMaterialComponent.RSTableMetroCustom tablaPracticas;
    private RSMaterialComponent.RSTextFieldMaterial txtApellido;
    private RSMaterialComponent.RSTextFieldMaterial txtBuscar;
    private RSMaterialComponent.RSTextFieldIconTwo txtBuscarOrden;
    private RSMaterialComponent.RSTextFieldIconTwo txtBuscarPractica;
    private RSMaterialComponent.RSTextFieldMaterial txtCama;
    private RSMaterialComponent.RSTextFieldMaterial txtCelular;
    private RSMaterialComponent.RSTextFieldMaterial txtDNI;
    private RSMaterialComponent.RSTextFieldMaterial txtDireccion;
    private RSMaterialComponent.RSTextFieldMaterial txtLocalidad;
    private RSMaterialComponent.RSTextFieldMaterial txtMail;
    private RSMaterialComponent.RSTextFieldMaterial txtMedico;
    private RSMaterialComponent.RSTextFieldMaterial txtMotivo;
    private RSMaterialComponent.RSTextFieldMaterial txtNombre;
    private RSMaterialComponent.RSTextFieldMaterial txtNumeroAfiliado;
    private RSMaterialComponent.RSTextFieldMaterial txtNumeroOrden;
    private RSMaterialComponent.RSTextFieldMaterial txtObraSocial;
    private RSMaterialComponent.RSTextFieldMaterial txtPrecioCoseguro;
    private RSMaterialComponent.RSTextFieldMaterial txtRecienNacido;
    private RSMaterialComponent.RSTextFieldMaterial txtTelefono;
}
