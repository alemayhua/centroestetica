/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Fichapaciente;
import Vista.*;
import Modelo.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author aleja
 */
public class ControladorFichapaciente {
    static VentanaFichapacientes ventanaficha = ControladorPacientes.ventanaficha;
    static VentanaPacientes ventanapa = ControladorPacientes.ventanapa;
    
    public static void fechaSistema(){
    Date fechaactual = new Date();
    SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
    ventanaficha.getTxtFechavisita().setText(formato.format(fechaactual));
     }
    
    public static void botonAgregar() {
        FichapacienteDB fdb = new FichapacienteDB();
        String id = ventanaficha.getTxtNumvisita().getText();
        String fecha = ventanaficha.getTxtFechavisita().getText();
        String visita = ventanaficha.getTxtAreavisita().getText();
        String fknumpa = ventanaficha.getTxtFknumpa().getText();
        Fichapaciente fp = new Fichapaciente();
        fp.setIdvisita(Integer.parseInt(id));
        fp.setFechavisita(fecha);
        fp.setVisita(visita);
        fp.setFknumpa(Integer.parseInt(fknumpa));
        fdb.agregarVisita(fp);
        cargarFicha();
    }
    
    public static void botonModificar(){
        String idvisita = ventanaficha.getTxtNumvisita().getText();
        String fecha = ventanaficha.getTxtFechavisita().getText();
        String visita = ventanaficha.getTxtAreavisita().getText();
        String fknumpa = ventanaficha.getTxtFknumpa().getText();
        Fichapaciente fp = new Fichapaciente();
        fp.setIdvisita(Integer.parseInt(idvisita));
        fp.setFechavisita(fecha);
        fp.setVisita(visita);
        fp.setFknumpa(Integer.parseInt(fknumpa));
        FichapacienteDB fdb = new FichapacienteDB();
        fdb.modificarFicha(fp);
        cargarFicha();
    }
    
    public static void botonEliminar(){
        String fknumpa = ventanaficha.getTxtFknumpa().getText();
        String id = ventanaficha.getTxtNumvisita().getText();
        FichapacienteDB fdb = new FichapacienteDB();
        if (JOptionPane.showConfirmDialog(null, "Se eliminará la visita, ¿desea continuar?",
                "Eliminar visita", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            fdb.eliminarVisita(Integer.parseInt(id), Integer.parseInt(fknumpa));
            cargarFicha();
            limpiarCampos();
        }
    }
    
    public static void cargarFicha() {
        FichapacienteDB fpb = new FichapacienteDB();
        fpb.acomodarId(Integer.parseInt(ventanapa.getTxtNumpa().getText()));
        DefaultTableModel datos = (DefaultTableModel) ventanaficha.getJtbVisitas().getModel();
        datos.setNumRows(0);
        for (Fichapaciente fp : new FichapacienteDB().getFichapacientes(ventanaficha.getTxtFknumpa().getText())) {
            Object[] fila = {
                fp.getIdvisita(),
                fp.getFechavisita(),
                fp.getVisita(),
            };
            datos.addRow(fila);
        }
        ventanaficha.getJtbVisitas().scrollRectToVisible(ventanaficha.getJtbVisitas().getCellRect(ventanaficha.getJtbVisitas().getRowCount()-1, 0, true));
    }
    
    public static void cargarTextoficha(){
        FichapacienteDB fdb = new FichapacienteDB();
        //String id = Integer.toString(fdb.incrementarIdvisita(Integer.parseInt(ventanapa.getTxtNumpa().getText())));
        String numero = ventanapa.getTxtNumpa().getText();
        String nombre = ventanapa.getTxtNompa().getText();
        ventanaficha.getTxtNumvisita().setText(""+fdb.incrementarIdvisita(Integer.parseInt(ventanapa.getTxtNumpa().getText())));
        ventanaficha.getTxtFknumpa().setText(numero);
        ventanaficha.getTxtNompa().setText(nombre);
        ventanaficha.getTxtAreavisita().setText("");
        ventanaficha.setTitle("Ficha de "+nombre);
        cargarFicha();
    }
    
    public static void limpiarCampos(){
        FichapacienteDB fdb = new FichapacienteDB();
        ventanaficha.getTxtNumvisita().setText("" + fdb.incrementarIdvisita(Integer.parseInt(ventanapa.getTxtNumpa().getText())));
        ventanaficha.getTxtAreavisita().setText("");
        fechaSistema();
        cargarFicha();
    }
}
