/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Vista.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author aleja
 */
public class ControladorInicio {

    static VentanaPacientes ventanapa = new VentanaPacientes();
    static VentanaArticulos ventanaar = new VentanaArticulos();
    static VentanaInicio ventanain = new VentanaInicio();
    static VentanaVentas ventanave = new VentanaVentas();
    static VentanaAgenda ventanatu = new VentanaAgenda();

    public static void mostrar() {
        ventanain.setVisible(true);
        ventanain.setLocationRelativeTo(null);
    }
    
    public static void fechaSistema(){
    Date fechaactual = new Date();
    SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
    ventanave.getTxtFecha().setText(formato.format(fechaactual));
     }

    public static void botonPacientes() {
//        ventanaoppa.setVisible(true);
//        ventanaoppa.setLocationRelativeTo(null);
//        ControladorPacientes.limpiarCampos();
        ventanapa.setVisible(true);
        ventanapa.setLocationRelativeTo(null);
        ControladorPacientes.limpiarCampos();
        ControladorPacientes.cargarpaciente();
    }

    public static void botonOpcionesregistropa() {
        ventanapa.setVisible(true);
        ventanapa.setLocationRelativeTo(null);
        ControladorPacientes.cargarpaciente();
    }

    public static void botonArticulos() {
        ventanaar.setVisible(true);
        ventanaar.setLocationRelativeTo(null);
        ventanaar.getJcbLab().setSelectedItem(null);
        ControladorComboedit.cargarCombo();
        ControladorArticulos.limpiarCampos();
    }

    public static void botonVentas() {
        ventanave.setVisible(true);
        ventanave.setLocationRelativeTo(null);
        fechaSistema();
    }

    public static void botonTurnos() {
        ventanatu.setVisible(true);
        ventanatu.setLocationRelativeTo(null);
    }

}
