/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import static Controlador.ControladorInicio.ventanaar;
import Modelo.*;
import Vista.VentanaComboedit;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author aleja
 */
public class ControladorComboedit {

    static VentanaComboedit ventanace = ControladorArticulos.ventanacbe;
    static Comboedit ce = new Comboedit();

    public static void botonAgregarlaboratorio() {
        ComboeditDB cedb = new ComboeditDB();
        String id = ventanace.getTxtId().getText();
        String lab = ventanace.getTxtLab().getText();
        Comboedit comboe = new Comboedit();
        comboe.setId(Integer.parseInt(id));
        comboe.setLab(lab);
        cedb.agregarLaboratorio(comboe);
        cargarCombo();
        cargar();
    }

    public static void cargar() {
        ComboeditDB cdb = new ComboeditDB();
        ventanace.getTxtId().setText(""+cdb.incrementarIdlab());
        cdb.acomodarId();
        DefaultTableModel datos = (DefaultTableModel) ventanace.getJtlab().getModel();
        datos.setNumRows(0);
        for (Comboedit comboe : new ComboeditDB().getComboedit()) {
            Object[] fila = {
                comboe.getId(),
                comboe.getLab()};
            datos.addRow(fila);
        }
    }

    public static void botonEliminarlaboratorio() {
        ComboeditDB cedb = new ComboeditDB();
        String id = ventanace.getTxtId().getText();
        cedb.eliminarLaboratorio(id);
        cargarCombo();
        cargar();
    }

    public static void seleccionFila() {
        int seleccion = ventanace.getJtlab().getSelectedRow();
        ventanace.getTxtId().setText(String.valueOf(ventanace.getJtlab().getValueAt(seleccion, 0)));
        ventanace.getTxtLab().setText(String.valueOf(ventanace.getJtlab().getValueAt(seleccion, 1)));
    }
    
    public static void cargarCombo(){
        ventanaar.getJcbLab().removeAllItems();
        ComboeditDB cedb = new ComboeditDB();
        cedb.llenarCombo(ventanaar.getJcbLab());
    }
    
    public static void limpiarCampos(){
        ComboeditDB cdb = new ComboeditDB();
        ventanace.getTxtId().setText(""+cdb.incrementarIdlab());
        ventanace.getTxtLab().setText("");
    }
}
