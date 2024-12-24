package com.ybc.ybioq.config;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.ybc.ybioq.config.Configuracion.*;

public class ConexionMySQLLocal {

    //public String db = "gasparre";
    /// public String db = "bioquimicos";
    //public String db = "bioquimicos_alicata";
    public String url = "jdbc:mariadb://" + direccionip + ":" + puerto + "/" + db;
    public String user = "root";
    public String pass = "Cole978-+";
    //public String pass = "Cole978++";

    public Connection Conectar() {
        Connection link = null;
        try {
            //Cargamos el Driver MySQL
            //Class.forName("org.gjt.mm.mysql.Driver");
            Class.forName("org.mariadb.jdbc.Driver");
            System.out.println("url:" + url);
            link = DriverManager.getConnection(url, this.user, this.pass);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexionMySQL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo conectar al servidor... Verifique las conexiones de red");
        }
        return link;
    }
}
