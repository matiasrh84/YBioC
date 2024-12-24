/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ybc.ybioq.config;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @author CBT-COMISIONES
 */
public class HistoriaClinica {

    public int ActualizaEstadoHistoriaClinica(int id_historia_clinica, String descripcion, int estado, String fecha) {
        int respuesta = 0;
        ConexionMySQLLocal cc = new ConexionMySQLLocal();
        Connection cn = cc.Conectar();
        System.out.println("-----------------------------------------------------------------");
        try {
            String sSQL3 = "update historia_clinica set descripcion=?, estado=?, fecha_entrega=? where idhistoria_clinica=" + id_historia_clinica;
            PreparedStatement pst = cn.prepareStatement(sSQL3);
            pst.setString(1, descripcion);
            pst.setInt(2, estado);
            pst.setString(3, fecha);
            int n4 = pst.executeUpdate();
            if (n4 > 0) {
                respuesta = 1;
            }
            cn.close();
        } catch (Exception e) {
            respuesta = 0;
            JOptionPane.showMessageDialog(null, e);
        }

        return respuesta;
    }
}
