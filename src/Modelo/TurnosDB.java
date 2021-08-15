/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JComboBox;

/**
 *
 * @author aleja
 */
public class TurnosDB {
    Conexion co = new Conexion();
    
    public void llenarCombo(JComboBox comboNombre) {
        try {
            Connection c = co.getConexion();
            Statement s = c.createStatement();
            String sql = "select * from paciente order by nompa ASC";
            ResultSet r = s.executeQuery(sql);
//            comboLaboratorio.addItem("Seleccione una opción");
            while(r.next()){
                comboNombre.addItem(r.getString("nompa"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
