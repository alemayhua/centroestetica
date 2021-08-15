/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author aleja
 */
public class Conexion {
    String cadena = "jdbc:mysql://localhost/any";

       public Connection getConexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection(cadena, "root", "");
            return c;
        } catch (Exception e) {
            e.printStackTrace(); 
        }
        return null; 
    }
    
}
