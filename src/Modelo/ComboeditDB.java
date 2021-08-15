package Modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JComboBox;
import Controlador.*;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author aleja
 */
public class ComboeditDB {

    Conexion co = new Conexion();

    public void agregarLaboratorio(Comboedit ce) {
        try {
            Connection c = co.getConexion();
            Statement s = c.createStatement();
            String sql = "insert into laboratorio values(%1 ,'%2')".replace("%1", ""+ce.getId()).replace("%2", ce.getLab());
            s.executeUpdate(sql);
            s.close();
            ControladorComboedit.limpiarCampos();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminarLaboratorio(String idlab) {
        try {
            Connection c = co.getConexion();
            Statement s = c.createStatement();
            String sql = "delete from laboratorio where idlab =" + idlab;
            s.executeUpdate(sql);
            s.close();
            ControladorComboedit.limpiarCampos();
//            JOptionPane.showMessageDialog(null, "PACIENTE ELIMINADO!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Comboedit> getComboedit() {
        ArrayList<Comboedit> comboedit = new ArrayList<Comboedit>();
        try {
            Connection c = co.getConexion();
            Statement s = c.createStatement();
            String sql = "select * from laboratorio lab order by idlab ASC";
            ResultSet r = s.executeQuery(sql);
            while (r.next()) {
                Comboedit ce = new Comboedit();
                ce.setId(r.getInt("idlab"));
                ce.setLab(r.getString("lab"));
                comboedit.add(ce);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return comboedit;
    }
    
    public void llenarCombo(JComboBox comboLaboratorio) {
        try {
            Connection c = co.getConexion();
            Statement s = c.createStatement();
            String sql = "select * from laboratorio order by lab ASC ";
            ResultSet r = s.executeQuery(sql);
            //comboLaboratorio.addItem("");
            while(r.next()){
                comboLaboratorio.addItem(r.getString("lab"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public int incrementarIdlab() {
        int id = 1;
        try {
            Connection c = co.getConexion();
            Statement s = c.createStatement();
            String sql = "select max(idlab) from laboratorio";
            ResultSet r = s.executeQuery(sql);
            while (r.next()) {
                id = r.getInt(1) + 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }
    
    public void acomodarId(){
        try {
         Connection c = co.getConexion();
         Statement s = c.createStatement();
         Statement j = c.createStatement();
         String sql = "set @num :=0";
         j.executeQuery(sql);
         j.close();
         String sql2 = "UPDATE laboratorio SET idlab = @num := (@num+1)";
         s.executeUpdate(sql2);
         s.close();
         }
          catch (Exception e) {
             e.printStackTrace();
         }
    }

}
