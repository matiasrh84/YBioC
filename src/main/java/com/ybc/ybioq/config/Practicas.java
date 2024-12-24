package com.ybc.ybioq.config;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Practicas {

    private int idPracticas;
    private int codigo_practica;
    private String determinacion_practica;
    private String instrucciones;
    private int id_derivaciones;
    private int tiempo_procesamiento;
    private int estado_deriva;
    private int id_seccion;
    private int prioridad;
    private int tipo_informe;

    public Practicas(int idPracticas, int codigo_practica, String determinacion_practica, String instrucciones, int id_derivaciones, double precio1, double precio2, double precio3, double precio4, int tiempo_procesamiento, int estado_deriva, int id_seccion, int prioridad, int tipo_informe) {
        this.idPracticas = idPracticas;
        this.codigo_practica = codigo_practica;
        this.determinacion_practica = determinacion_practica;
        this.instrucciones = instrucciones;
        this.id_derivaciones = id_derivaciones;
        this.tiempo_procesamiento = tiempo_procesamiento;
        this.estado_deriva = estado_deriva;
        this.id_seccion = id_seccion;
        this.prioridad = prioridad;
        this.tipo_informe = tipo_informe;
    }

    public Practicas() {
    }

    public void setIdPracticas(int idPracticas) {
        this.idPracticas = idPracticas;
    }

    public void setCodigo_practica(int codigo_practica) {
        this.codigo_practica = codigo_practica;
    }

    public void setDeterminacion_practica(String determinacion_practica) {
        this.determinacion_practica = determinacion_practica;
    }

    public void setInstrucciones(String instrucciones) {
        this.instrucciones = instrucciones;
    }

    public void setId_derivaciones(int id_derivaciones) {
        this.id_derivaciones = id_derivaciones;
    }

    public void setTiempo_procesamiento(int tiempo_procesamiento) {
        this.tiempo_procesamiento = tiempo_procesamiento;
    }

    public void setEstado_deriva(int estado_deriva) {
        this.estado_deriva = estado_deriva;
    }

    public void setId_seccion(int id_seccion) {
        this.id_seccion = id_seccion;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public void setTipo_informe(int tipo_informe) {
        this.tipo_informe = tipo_informe;
    }

    public int getIdPracticas() {
        return idPracticas;
    }

    public int getCodigo_practica() {
        return codigo_practica;
    }

    public String getDeterminacion_practica() {
        return determinacion_practica;
    }

    public String getInstrucciones() {
        return instrucciones;
    }

    public int getId_derivaciones() {
        return id_derivaciones;
    }

    public int getTiempo_procesamiento() {
        return tiempo_procesamiento;
    }

    public int getEstado_deriva() {
        return estado_deriva;
    }

    public int getId_seccion() {
        return id_seccion;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public int getTipo_informe() {
        return tipo_informe;
    }

    public int AgregarPracticas(int codigo,
                                String determinacion_practica,
                                String instrucciones,
                                int id_derivaciones,
                                int tiempo_procesamiento,
                                int estado_deriva,
                                int id_seccion,
                                int prioridad,
                                int tipo_informe) {
        int n = 0;
        ConexionMySQLLocal mysql = new ConexionMySQLLocal();
        Connection cn = mysql.Conectar();
        String sSQL = "INSERT INTO practicas("
                + "codigo_practica, "
                + "determinacion_practica, "
                + "instrucciones, "
                + "id_derivaciones, "
                + "tiempo_procesamiento, "
                + "estado_deriva,"
                + "id_seccion, "
                + "prioridad,"
                + "tipo_informe)"
                + "VALUES(?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, codigo);
            pst.setString(2, determinacion_practica);
            pst.setString(3, instrucciones);
            pst.setInt(4, id_derivaciones);
            pst.setInt(5, tiempo_procesamiento);
            pst.setInt(6, estado_deriva);
            pst.setInt(7, id_seccion);
            pst.setInt(8, prioridad);
            pst.setInt(9, tipo_informe);
            n = pst.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                cn.close();
            } catch (SQLException ex) {
                Logger.getLogger(Practicas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return n;
    }

    public int AgregarPracticas(int codigo, String determinacion_practica) {
        int n = 0;
        ConexionMySQLLocal mysql = new ConexionMySQLLocal();
        Connection cn = mysql.Conectar();
        String insertSQL = "INSERT INTO practicas(codigo_practica, determinacion_practica) VALUES(?,?)";
        String selectSQL = "SELECT MAX(id_practicas) FROM practicas";
        try {
            PreparedStatement pst = cn.prepareStatement(insertSQL);
            pst.setInt(1, codigo);
            pst.setString(2, determinacion_practica);
            n = pst.executeUpdate();
            if (n > 0) {
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(selectSQL);

                if (rs.next()) {
                    n = rs.getInt(1);
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                cn.close();
            } catch (SQLException ex) {
                Logger.getLogger(Practicas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return n;
    }

    public int AgregarPracticasNBU(int id, double unidadBioquimica) {
        int n = 0;
        ConexionMySQLLocal mysql = new ConexionMySQLLocal();
        Connection cn = mysql.Conectar();
        String sSQL = "INSERT INTO practicas_nbu(id_practicas, id_nbu, unidadbioquimica_practica) VALUES(?,?,?)";
        String selectSQL = "SELECT MAX(id_practicasnbu) FROM practicas_nbu";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, id);
            pst.setInt(2, 5);
            pst.setDouble(3, unidadBioquimica);
            n = pst.executeUpdate();
            if (n > 0) {
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(selectSQL);
                if (rs.next()) {
                    n = rs.getInt(1);
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                cn.close();
            } catch (SQLException ex) {
                Logger.getLogger(Practicas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return n;
    }

    public int AgregarObrasocialTienePracticasNBU(int id, int codigo, double unidadBioquimica, double precio) {
        int n = 0;
        ConexionMySQLLocal mysql = new ConexionMySQLLocal();
        Connection cn = mysql.Conectar();
        String sSQL = "INSERT INTO obrasocial_tiene_practicas_nbu(id_obrasocial, id_practicasnbu, codigo_fac_practicas_obrasocial, unidadbioquimica, importeunidaddearancel_obrasocial, preciofijo, "
                + "preciototal, id_nbu, id_usuarios, estado) VALUES(?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, 96);
            pst.setInt(2, id);
            pst.setInt(3, codigo);
            pst.setDouble(4, unidadBioquimica);
            pst.setInt(5, 0);
            pst.setDouble(6, precio);
            pst.setDouble(7, precio);
            pst.setInt(8, 5);
            //pst.setInt(9, Login_nuevo.id_usuario);
            pst.setInt(10, 1);
            n = pst.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                cn.close();
            } catch (SQLException ex) {
                Logger.getLogger(Practicas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return n;
    }
}
