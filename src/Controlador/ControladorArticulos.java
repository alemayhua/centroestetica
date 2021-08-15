    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Articulos;
import Modelo.ArticulosDB;
import Vista.VentanaArticulos;
import Vista.VentanaComboedit;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author aleja
 */
public class ControladorArticulos {
    static VentanaArticulos ventanaar = ControladorInicio.ventanaar;
    static VentanaComboedit ventanacbe = new VentanaComboedit();
    
     public static void botonComboedit(){
     ventanacbe.setVisible(true);
     ventanacbe.setLocationRelativeTo(null);
     ventanacbe.getTxtId().setVisible(false);
     ControladorComboedit.cargar();
     ControladorComboedit.limpiarCampos();
     }
    
    public static void botonAgregarar(){
        String codigo = ventanaar.getTxtCodigo().getText();
        String descripcion = ventanaar.getTxtDescripcion().getText();
        String laboratorio = (String) ventanaar.getJcbLab().getSelectedItem();
        String precio = ventanaar.getTxtPrecio().getText();
        String stock = ventanaar.getTxtStock().getText();
        Articulos ar = new Articulos();
        ar.setCodigo(Integer.parseInt(codigo));
        ar.setDescripcion(descripcion);
        ar.setLaboratorio(laboratorio);
        ar.setPrecio(Double.parseDouble(precio));
        ar.setStock(Integer.parseInt(stock));
        ArticulosDB ardb = new ArticulosDB();
        ardb.agregarArticulos(ar);
        cargarArticulo();
    }
    
    public static void botonModificar(){
       String codigo = ventanaar.getTxtCodigo().getText();
        String descripcion = ventanaar.getTxtDescripcion().getText();
        String laboratorio = (String) ventanaar.getJcbLab().getSelectedItem();
        String precio = ventanaar.getTxtPrecio().getText();
        String stock = ventanaar.getTxtStock().getText();
        Articulos ar = new Articulos();
        ar.setCodigo(Integer.parseInt(codigo));
        ar.setDescripcion(descripcion);
        ar.setLaboratorio(laboratorio);
        ar.setPrecio(Double.parseDouble(precio));
        ar.setStock(Integer.parseInt(stock));
        ArticulosDB ardb = new ArticulosDB();
        ardb.modificarArticulos(ar);
        cargarArticulo();
    }
    
    public static void botonEliminarpa() {
        String codigo = ventanaar.getTxtCodigo().getText();
        String descripcion = ventanaar.getTxtDescripcion().getText();
        ArticulosDB padb = new ArticulosDB();
        if (JOptionPane.showConfirmDialog(null, "Se eliminará el artículo: "+descripcion+", ¿desea continuar?",
                "Eliminar Artículo", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            padb.eliminarArticulos(Integer.parseInt(codigo));
            limpiarCampos();
        }
    }
    
    public static void cargarArticulo(){        
      DefaultTableModel datos = (DefaultTableModel) ventanaar.getJtArticulos().getModel();
      datos.setNumRows(0);
      for (Articulos a : new ArticulosDB().getArticulos()){
          Object[] fila = {
              a.getCodigo(), 
              a.getDescripcion(),
              a.getStock(),
              a.getPrecio(),
              a.getLaboratorio()
          };
          datos.addRow(fila);
      }    
    }
    
    public static void cargarBuscar(){        
      DefaultTableModel datos = (DefaultTableModel) ventanaar.getJtArticulos().getModel();
      datos.setNumRows(0);
      for (Articulos a : new ArticulosDB().getBuscar(ventanaar.getTxtBuscarar().getText())){
          Object[] fila = {
              a.getCodigo(), 
              a.getDescripcion(),
              a.getStock(),
              a.getPrecio(),
              a.getLaboratorio()
          };
          datos.addRow(fila);
      }    
    }
    
     public static void limpiarCampos() {
        ArticulosDB adb = new ArticulosDB();
        ventanaar.getTxtCodigo().setText(""+adb.incrementarCodigo());
        ventanaar.getJcbLab().setSelectedItem(null);
        ventanaar.getTxtDescripcion().setText("");
        ventanaar.getTxtPrecio().setText("");
        ventanaar.getTxtStock().setText("");
        ventanaar.getTxtBuscarar().setText("");
        cargarArticulo();
    }
}
