package com.ybc.ybioq.config;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ExportExcel {

    private int Protocolo;
    private int codigo_obrasocial;
    private String razonsocial_obrasocial;
    private String NombreAfiliado;
    private int dni;
    private String NumeroAfiliado;
    private String NumeroOrden;
    private String FechaOrden;
    private String CodigoPractica;
    private String NombrePractica;
    private String CodigoFacturacion;
    private double PrecioPractica;
    private double Arancel;

    public ExportExcel(int Protocolo, int codigo_obrasocial, String razonsocial_obrasocial, String NombreAfiliado, int dni, String NumeroAfiliado, String NumeroOrden, String FechaOrden, String CodigoPractica, String NombrePractica, String CodigoFacturacion, double PrecioPractica, double Arancel) {
        this.Protocolo = Protocolo;
        this.codigo_obrasocial = codigo_obrasocial;
        this.razonsocial_obrasocial = razonsocial_obrasocial;
        this.NombreAfiliado = NombreAfiliado;
        this.dni = dni;
        this.NumeroAfiliado = NumeroAfiliado;
        this.NumeroOrden = NumeroOrden;
        this.FechaOrden = FechaOrden;
        this.CodigoPractica = CodigoPractica;
        this.NombrePractica = NombrePractica;
        this.CodigoFacturacion = CodigoFacturacion;
        this.PrecioPractica = PrecioPractica;
        this.Arancel = Arancel;
    }

    public int getProtocolo() {
        return Protocolo;
    }

    public void setProtocolo(int Protocolo) {
        this.Protocolo = Protocolo;
    }

    public int getCodigo_obrasocial() {
        return codigo_obrasocial;
    }

    public void setCodigo_obrasocial(int codigo_obrasocial) {
        this.codigo_obrasocial = codigo_obrasocial;
    }

    public String getRazonsocial_obrasocial() {
        return razonsocial_obrasocial;
    }

    public void setRazonsocial_obrasocial(String razonsocial_obrasocial) {
        this.razonsocial_obrasocial = razonsocial_obrasocial;
    }

    public String getNombreAfiliado() {
        return NombreAfiliado;
    }

    public void setNombreAfiliado(String NombreAfiliado) {
        this.NombreAfiliado = NombreAfiliado;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getNumeroAfiliado() {
        return NumeroAfiliado;
    }

    public void setNumeroAfiliado(String NumeroAfiliado) {
        this.NumeroAfiliado = NumeroAfiliado;
    }

    public String getNumeroOrden() {
        return NumeroOrden;
    }

    public void setNumeroOrden(String NumeroOrden) {
        this.NumeroOrden = NumeroOrden;
    }

    public String getFechaOrden() {
        return FechaOrden;
    }

    public void setFechaOrden(String FechaOrden) {
        this.FechaOrden = FechaOrden;
    }

    public String getCodigoPractica() {
        return CodigoPractica;
    }

    public void setCodigoPractica(String CodigoPractica) {
        this.CodigoPractica = CodigoPractica;
    }

    public String getNombrePractica() {
        return NombrePractica;
    }

    public void setNombrePractica(String NombrePractica) {
        this.NombrePractica = NombrePractica;
    }

    public String getCodigoFacturacion() {
        return CodigoFacturacion;
    }

    public void setCodigoFacturacion(String CodigoFacturacion) {
        this.CodigoFacturacion = CodigoFacturacion;
    }

    public double getPrecioPractica() {
        return PrecioPractica;
    }

    public void setPrecioPractica(double PrecioPractica) {
        this.PrecioPractica = PrecioPractica;
    }

    public double getArancel() {
        return Arancel;
    }

    public void setArancel(double Arancel) {
        this.Arancel = Arancel;
    }

    public static ArrayList<ExportExcel> cargarOrdenesObrasocialPeriodo(Connection connection, int codigo_Obrasocial, String FechaInico, String FechaFin) {

        ArrayList<ExportExcel> lista = new ArrayList<>();

        try {
            Statement instruccion = connection.createStatement();
            ResultSet rs = instruccion.executeQuery(" SELECT\n"
                    + "  `historia_clinica`.`idhistoria_clinica`,\n"
                    + "  `obrasocial`.`codigo_obrasocial`,\n"
                    + "  `obrasocial`.`razonsocial_obrasocial`,\n"
                    + "  concat(`personas`.`nombre`,' ',`personas`.`apellido`) as Nombre_Apellido,  \n"
                    + "  `personas`.`dni`,\n"
                    + "   pacientes_tienen_obrasociales.numero_afiliado,\n"
                    + "  `ordenes`.`numero_orden`,\n"
                    + "DATE_FORMAT(`ordenes`.`fecha`, '%d-%m-%Y') AS 'fecha',\n"
                    + "  `practicas`.`codigo_practica`,\n"
                    + "  `practicas`.`determinacion_practica`,\n"
                    + "  `ordenes_tienen_practicas`.`cod_practica_fac`,\n"
                    + "  `ordenes_tienen_practicas`.`precio_practica`,\n"
                    + "  `obrasocial`.`importeunidaddearancel_obrasocial`\n"
                    + "\n"
                    + "FROM\n"
                    + "  ordenes\n"
                    + "    JOIN `pacientes` ON `pacientes`.`id_Pacientes` = `ordenes`.`id_Pacientes`\n"
                    + "    JOIN  pacientes_tienen_obrasociales  ON `pacientes_tienen_obrasociales`.`id_Pacientes` = `pacientes`.`id_Pacientes`\n"
                    + "    JOIN `historia_clinica` ON `historia_clinica`.`id_ordenes` = `ordenes`.`id_ordenes`\n"
                    + "    JOIN `personas` ON `personas`.`dni` = `pacientes`.`personas_dni`\n"
                    + "    JOIN `obrasocial` ON `obrasocial`.`id_obrasocial` = `ordenes`.`id_obrasocial`\n"
                    + "    JOIN `ordenes_tienen_practicas` ON `ordenes_tienen_practicas`.`id_ordenes` = `ordenes`.`id_ordenes`\n"
                    + "    JOIN `practicas` ON `practicas`.`id_practicas` = `ordenes_tienen_practicas`.`id_practicas`\n"
                    + " WHERE ordenes.estado_orden=1  and Date(fecha) BETWEEN '2020-09-01' and '2020-09-01'");

            while (rs.next()) {
                System.out.println("Toma la consulta: " + rs.getInt("idhistoria_clinica"));
                System.out.println("Consulta" + rs.getInt("idhistoria_clinica") +
                        rs.getInt("codigo_obrasocial") +
                        rs.getString("razonsocial_obrasocial") +
                        rs.getString("Nombre_Apellido") +
                        rs.getInt("dni") +
                        rs.getString("numero_afiliado") +
                        rs.getString("numero_orden") +
                        rs.getString("fecha") +
                        rs.getString("codigo_practica") +
                        rs.getString("determinacion_practica") +
                        rs.getString("cod_practica_fac") +
                        rs.getDouble("precio_practica") +
                        rs.getDouble("importeunidaddearancel_obrasocial"));


                lista.add(new ExportExcel(
                        rs.getInt("idhistoria_clinica"),
                        rs.getInt("codigo_obrasocial"),
                        rs.getString("razonsocial_obrasocial"),
                        rs.getString("Nombre_Apellido"),
                        rs.getInt("dni"),
                        rs.getString("numero_afiliado"),
                        rs.getString("numero_orden"),
                        rs.getString("fecha"),
                        rs.getString("codigo_practica"),
                        rs.getString("determinacion_practica"),
                        rs.getString("cod_practica_fac"),
                        rs.getDouble("precio_practica"),
                        rs.getDouble("importeunidaddearancel_obrasocial")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
