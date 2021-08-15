/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Controlador.ControladorArticulos;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author aleja
 */
public class ArticulosDB {

    Conexion co = new Conexion();

    public void agregarArticulos(Articulos a) {
        try {
            Connection c = co.getConexion();
            Statement s = c.createStatement();
            String sql = "insert into articulo values(%1,'%2',%3,%4,'%5')".replace("%1", "" + a.getCodigo()).replace("%2", a.getDescripcion()).replace("%3", "" + a.getPrecio()).replace("%4", "" + a.getStock()).replace("%5", a.getLaboratorio());
            s.executeUpdate(sql);
            ControladorArticulos.limpiarCampos();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminarArticulos(int codigo) {
        try {
            Connection c = co.getConexion();
            Statement s = c.createStatement();
            String sql = "delete from articulo where codigo=" + codigo;
            s.executeUpdate(sql);
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Articulos> getArticulos() {
        ArrayList<Articulos> articulos = new ArrayList<Articulos>();
        try {
            Connection c = co.getConexion();
            Statement s = c.createStatement();
            String sql = "select * from articulo ";
            ResultSet r = s.executeQuery(sql);
            while (r.next()) {
                Articulos a = new Articulos();
                a.setCodigo(r.getInt("codigo"));
                a.setDescripcion(r.getString("descripcion"));
                a.setStock(r.getInt("stock"));
                a.setPrecio(r.getDouble("precio"));
                a.setLaboratorio(r.getString("laboratorio"));
                articulos.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return articulos;
    }

    public void modificarArticulos(Articulos a) {
        try {
            Connection c = co.getConexion();
            Statement s = c.createStatement();
            String sql = ("update articulo set descripcion='%2'," + "precio = %3, " + "stock = %4, " + "laboratorio = '%5' where codigo = %1").replace("%1", "" + a.getCodigo()).replace("%2", a.getDescripcion()).replace("%3", "" + a.getPrecio()).replace("%4", "" + a.getStock()).replace("%5", a.getLaboratorio());
            s.executeUpdate(sql);
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int incrementarCodigo() {
        int num = 1;
        try {
            Connection c = co.getConexion();
            Statement s = c.createStatement();
            String sql = "select max(codigo) from articulo";
            ResultSet r = s.executeQuery(sql);
            while (r.next()) {
                num = r.getInt(1) + 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return num;

    }
    
    public ArrayList<Articulos> getBuscar(String texto) {
        ArrayList<Articulos> articulos = new ArrayList<Articulos>();
        try {
            Connection c = co.getConexion();
            Statement s = c.createStatement();
            String sql = "select * from articulo where descripcion like '%"+texto+"%'";
            ResultSet r = s.executeQuery(sql);
            while (r.next()) {
                Articulos a = new Articulos();
                a.setCodigo(r.getInt("codigo"));
                a.setDescripcion(r.getString("descripcion"));
                a.setStock(r.getInt("stock"));
                a.setPrecio(r.getDouble("precio"));
                a.setLaboratorio(r.getString("laboratorio"));
                articulos.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return articulos;
    }

}
