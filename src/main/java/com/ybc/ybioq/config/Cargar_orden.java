package com.ybc.ybioq.config;

import javax.swing.*;
import java.sql.*;

/**
 * @author lucas.robles
 */
public class Cargar_orden {

    public int id_orden;

    public int cargar(int periodo,
                      String numero_orden,
                      double total_orden,
                      String fecha_orden,
                      int id_usuario,
                      int id_medico,
                      int id_especialidad,
                      int id_paciente,
                      String servicio,
                      String cama,
                      String tipo_orden,
                      int id_obrasocial,
                      String recien_nacido,
                      String hora_carga,
                      String fecha_carga,
                      double seña,
                      String coseguro[],
                      int plan_ss[],
                      int id_practicas[],
                      int id_ordenes[],
                      String precio[],
                      String cod_prac[],
                      int factura[],
                      int id_historia_clinica) {
        /////////////////////////////////////////////////////////////////
        System.out.println("entra a cargar orden");
        ConexionMySQLLocal cc = new ConexionMySQLLocal();
        Connection cn = cc.Conectar();
        String sSQL = "INSERT INTO ordenes(periodo,numero_orden,total,estado_orden,fecha,"
                + "id_Usuarios,id_medicos,id_especialidades,id_Pacientes,servicio, cama, tipo_orden,"
                + " id_obrasocial, nombre_recien_nacido,hora,fecha_de_autorizacion,seña)"
                + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, periodo);
            System.out.println("1");
            pst.setString(2, numero_orden);
            System.out.println("2");
            pst.setDouble(3, total_orden);
            System.out.println("3");
            pst.setInt(4, 0);
            System.out.println("4");
            pst.setString(5, revertir(fecha_orden));
            System.out.println("5");
            pst.setInt(6, id_usuario);
            System.out.println("6");
            ///////////////////// Verifico si el campo txtmedico es vacio 
            if (id_medico == 0) {
                ///1094 id sin datos medicos
                pst.setInt(7, 1094);
                System.out.println("7");
            } else {
                pst.setInt(7, id_medico);
                System.out.println("7");
            }
            pst.setInt(8, id_especialidad);
            System.out.println("8");
            pst.setInt(9, id_paciente);
            System.out.println("9");
            pst.setString(10, servicio);
            System.out.println("10");
            pst.setString(11, cama);
            System.out.println("11");
            pst.setString(12, tipo_orden);
            System.out.println("12");
            pst.setInt(13, id_obrasocial);
            System.out.println("13");
            pst.setString(14, recien_nacido);
            System.out.println("14");
            pst.setString(15, hora_carga);
            System.out.println("15");
            pst.setString(16, revertir(fecha_carga));
            System.out.println("16");
            pst.setDouble(17, seña);
            System.out.println("17");
            int n = pst.executeUpdate();
            if (n > 0) {
                cargarorden();
                System.out.println("trae ultimo id");
                System.out.println("Orden creada");
                //////////////////////////////////////////////////detalle de orden////////////////////
                try {
                    int i = 0;
                    int n2 = id_practicas.length;
                    // JOptionPane.showMessageDialog(null, "longitud del vector id practicas " + n2);

                    if (n2 != 0) {

                        while (i < n2) {

                            //JOptionPane.showMessageDialog(null, "indice: " + i);
                            String SQL = "INSERT INTO ordenes_tienen_practicas( id_practicas, id_ordenes, precio_practica, cod_practica_fac,factura)"
                                    + "VALUES(?,?,?,?,?)";
                            PreparedStatement st = cn.prepareStatement(SQL);
                            st.setInt(1, id_practicas[i]);
                            st.setInt(2, id_orden);
                            st.setString(3, precio[i]);
                            st.setString(4, cod_prac[i]);
                            // JOptionPane.showMessageDialog(null, "graba en carga_orden:" + id_practicas[i]);
                            st.setInt(5, factura[i]);
                            int n3 = st.executeUpdate();

                            if (n3 > 0) {
                                System.out.println("detalle de Orden creado");
                                /////////////////////////////////////resultados//////////////////////
                                String sSQL5 = "SELECT analisis.id_analisis from analisis where analisis.id_practicas=" + id_practicas[i];
                                Statement st5 = cn.createStatement();
                                ResultSet rs5 = st5.executeQuery(sSQL5);
                                /////////////////////////////////////////////////////////////////////////

                                while (rs5.next()) {

                                    String SQL2 = "INSERT INTO resultados( id_analisis, id_practicas, id_ordenes, id_Usuarios, id_medicos, id_especialidades, id_Pacientes, resultado, observacion)"
                                            + "VALUES(?,?,?,?,?,?,?,?,?)";
                                    PreparedStatement st2 = cn.prepareStatement(SQL2);
                                    // JOptionPane.showMessageDialog(null, rs5.getString(1));
                                    st2.setString(1, rs5.getString(1));
                                    st2.setInt(2, id_practicas[i]);
                                    st2.setInt(3, id_orden);
                                    st2.setInt(4, id_usuario);
                                    st2.setInt(5, id_medico);
                                    st2.setInt(6, id_especialidad);
                                    st2.setInt(7, id_paciente);
                                    st2.setString(8, "-");
                                    st2.setString(9, "");
                                    int n4 = st2.executeUpdate();
                                    if (n4 > 0) {
                                        // System.out.println("Resultados creados");
                                        //////////////////////////////////////

                                        //return 1;
                                    } else {
                                        //return 0;
                                    }

                                }
                            }
                            i++;
                        }
                        String SQL3 = "INSERT INTO historia_clinica( idhistoria_clinica, descripcion, id_ordenes,fecha_entrega)"
                                + "VALUES(?,?,?,?)";
                        PreparedStatement st3 = cn.prepareStatement(SQL3);
                        st3.setInt(1, id_historia_clinica);
                        st3.setString(2, tipo_orden);
                        st3.setInt(3, id_orden);
                        st3.setString(4, fecha_carga);
                        st3.executeUpdate();
                        System.out.println("Historia clinica creada");
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                    // return 0;
                }

            } else {
                return 0;
            }
            System.out.println("aqui");
            return 1;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            return 0;
        }
    }

    public double Redondear(double numero) {
        return Math.rint(numero * 100) / 100;
    }

    public String revertir(String entrada) {
        if ((null == entrada) || (entrada.length() <= 1)) {
            return entrada;
        }
        String salida = "";
        int i = 0;
        /////Año/////
        for (i = 6; i <= 9; i++) {
            salida = salida + entrada.charAt(i);
        }
        salida = salida + "-";
        ///Mes///
        for (i = 3; i <= 4; i++) {
            salida = salida + entrada.charAt(i);
        }
        salida = salida + "-";
        ////Dia////
        for (i = 0; i <= 1; i++) {
            salida = salida + entrada.charAt(i);
        }

        return salida;

    }

    void cargarorden() {
        String sSQL = "";
        ConexionMySQLLocal mysql = new ConexionMySQLLocal();
        Connection cn = mysql.Conectar();
        sSQL = "SELECT MAX(id_ordenes) AS id_ordenes FROM ordenes";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            rs.last();
            if (rs.getInt("id_ordenes") != 0) {
                id_orden = rs.getInt("id_ordenes");
            } else {
                id_orden = 1;
            }
            System.out.println("id orden: " + id_orden);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

}
