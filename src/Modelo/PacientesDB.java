    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Controlador.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author aleja
 */
public class PacientesDB {

    Conexion co = new Conexion();

    public void agregarPacientes(Pacientes p) {
        try {
            Connection c = co.getConexion();
            Statement s = c.createStatement();
            String sql = "insert into paciente values(%1,'%2','%3','%4','%5')".replace("%1", "" + p.getNumpa()).replace("%2", p.getNompa()).replace("%3", p.getFecpa()).replace("%4", p.getDirpa()).replace("%5", p.getTelpa());
            s.executeUpdate(sql);
            s.close();
            JOptionPane.showMessageDialog(null, "Paciente agregado con Exito!");
            ControladorPacientes.limpiarCampos();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR: Número de paciente repetido!");
            e.printStackTrace();
        }
    }

    public void eliminarPacientes(int numpa) {
        try {
            Connection c = co.getConexion();
            Statement s = c.createStatement();
            String sql = "delete from paciente where numpa=" + numpa;
            s.executeUpdate(sql);
            s.close();
//            JOptionPane.showMessageDialog(null, "PACIENTE ELIMINADO!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Pacientes> getPacientes() {
        ArrayList<Pacientes> pacientes = new ArrayList<Pacientes>();
        try {
            Connection c = co.getConexion();
            Statement s = c.createStatement();
            String sql = "select numpa, nompa, date_format(fecpa, '%d-%m-%Y'), dirpa, telpa from paciente";
            ResultSet r = s.executeQuery(sql);
            while (r.next()) {
                Pacientes p = new Pacientes();
                p.setNumpa(r.getInt("numpa"));
                p.setNompa(r.getString("nompa"));
                p.setFecpa(r.getString("date_format(fecpa, '%d-%m-%Y')"));
                p.setDirpa(r.getString("dirpa"));
                p.setTelpa(r.getString("telpa"));
                pacientes.add(p);
            }
            r.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pacientes;
    }

    public void modificarPacientes(Pacientes p) {
        try {
            Connection c = co.getConexion();
            Statement s = c.createStatement();
            String sql = ("update paciente set nompa='%2'," + "fecpa = '%3', " + "dirpa = '%4', " + "telpa = '%5' where numpa = %1").replace("%1", "" + p.getNumpa()).replace("%2", p.getNompa()).replace("%3", p.getFecpa()).replace("%4", p.getDirpa()).replace("%5", p.getTelpa());
            s.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Modificado Correctamente!");
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int incrementarNumpa() {
        int id = 1;
        try {
            Connection c = co.getConexion();
            Statement s = c.createStatement();
            String sql = "select max(numpa) from paciente";
            ResultSet r = s.executeQuery(sql);
            while (r.next()) {
                id = r.getInt(1) + 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    public ArrayList<Pacientes> getNombre(String texto) {
        ArrayList<Pacientes> pacientes = new ArrayList<Pacientes>();
        try {
            Connection c = co.getConexion();
            Statement s = c.createStatement();
            String sql = "select numpa, nompa, date_format(fecpa, '%d-%m-%Y'), dirpa, telpa from paciente where nompa like '%"+texto+"%'";
            ResultSet r = s.executeQuery(sql);
            while (r.next()) {
                Pacientes p = new Pacientes();
                p.setNumpa(r.getInt("numpa"));
                p.setNompa(r.getString("nompa"));
                p.setFecpa(r.getString("date_format(fecpa, '%d-%m-%Y')"));
                p.setDirpa(r.getString("dirpa"));
                p.setTelpa(r.getString("telpa"));
                pacientes.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pacientes;
    }
}
