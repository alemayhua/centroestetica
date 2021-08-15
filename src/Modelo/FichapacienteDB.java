/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Controlador.ControladorFichapaciente;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author aleja
 */
public class FichapacienteDB {

    Conexion co = new Conexion();
    
     public void agregarVisita(Fichapaciente fp) {
        try {
            Connection c = co.getConexion();
            Statement s = c.createStatement();
            String sql = "insert into fichapaciente values(%1,'%2','%3',%4)".replace("%1", "" + fp.getIdvisita()).replace("%2", fp.getFechavisita()).replace("%3", fp.getVisita()).replace("%4", ""+fp.getFknumpa());
            s.executeUpdate(sql);
            s.close();
            ControladorFichapaciente.limpiarCampos();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR!");
        }
    }
     
     public void modificarFicha(Fichapaciente fp) {
        try {
            Connection c = co.getConexion();
            Statement s = c.createStatement();
            String sql = ("update fichapaciente set idvisita = %1, " + "fechavisita = '%2', " + "visita = '%3' where fknumpa=%4 and idvisita=%1").replace("%1", "" + fp.getIdvisita()).replace("%2", fp.getFechavisita()).replace("%3", fp.getVisita()).replace("%4",""+fp.getFknumpa());
            s.executeUpdate(sql);
            s.close();
            JOptionPane.showMessageDialog(null, "Modificado Correctamente!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     
     public void eliminarVisita(int id, int fknumpa) {
        try {
            Connection c = co.getConexion();
            Statement s = c.createStatement();
            String sql = "delete from fichapaciente where idvisita="+id+" and fknumpa="+fknumpa ;
            s.executeUpdate(sql);
            s.close();
//            JOptionPane.showMessageDialog(null, "PACIENTE ELIMINADO!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     
     public void acomodarId(int numpa){
         try {
         Connection c = co.getConexion();
         Statement s = c.createStatement();
         Statement j = c.createStatement();
         String sql = "set @num :=0";
         j.executeQuery(sql);
         j.close();
         String sql2 = "UPDATE fichapaciente SET idvisita = @num := (@num+1) where fknumpa="+numpa;
         s.executeUpdate(sql2);
         s.close();
         }
          catch (Exception e) {
             e.printStackTrace();
         }
     }
     
    public ArrayList<Fichapaciente> getFichapacientes(String fknumpa) {
        ArrayList<Fichapaciente> fichapaciente = new ArrayList<Fichapaciente>();
        try {
            Connection c = co.getConexion();
            Statement s = c.createStatement();
            String sql = "select * from fichapaciente where fknumpa="+fknumpa+" order by idvisita asc";
            ResultSet r = s.executeQuery(sql);
            while (r.next()) {
                Fichapaciente fp = new Fichapaciente();
                fp.setIdvisita(r.getInt("idvisita"));
                fp.setFechavisita(r.getString("fechavisita"));
                fp.setVisita(r.getString("visita"));
                fichapaciente.add(fp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fichapaciente;
    }
    
    public int incrementarIdvisita(int num) {
        int id = 1;
        try {
            Connection c = co.getConexion();
            Statement s = c.createStatement();
            String sql = "select max(idvisita) from fichapaciente where fknumpa="+num;
            ResultSet r = s.executeQuery(sql);
            while (r.next()) {
                id = r.getInt(1) + 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }
}
