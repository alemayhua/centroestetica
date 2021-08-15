package Controlador;

import javax.swing.table.DefaultTableModel;
import Modelo.*;
import Vista.*;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

public class ControladorPacientes {

    static VentanaPacientes ventanapa = ControladorInicio.ventanapa;
    static Pacientes pa = new Pacientes();
    static VentanaFichapacientes ventanaficha = new VentanaFichapacientes();

//   Como llama un jdatechooser la profe
//    private static LocalDate obtenerfechaJDateChooser(JDateChooser dateChooser) {
//        Date date;
//        if(dateChooser.getCalendar() != null){
//            date = dateChooser.getCalendar().getTime();
//            return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//        }
//        else
//        return null;
//    }
    //        LocalDate fecpa = obtenerfechaJDateChooser(ventanapa.getJdcFecpa());
    public static void botonAgregarpa() {
        String numpa = ventanapa.getTxtNumpa().getText();
        String nompa = ventanapa.getTxtNompa().getText();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        String fecpa = formatoFecha.format(ventanapa.getJdcFecpa().getDate());
        String dirpa = ventanapa.getTxtDirpa().getText();
        String telpa = ventanapa.getTxtTelpa().getText();
        Pacientes p = new Pacientes();
        p.setNumpa(Integer.parseInt(numpa));
        p.setNompa(nompa);
        p.setFecpa(fecpa);
        p.setDirpa(dirpa);
        p.setTelpa(telpa);
        PacientesDB db = new PacientesDB();
        db.agregarPacientes(p);
        cargarpaciente();
    }

    public static void botonModificarpa() {
        String numpa = ventanapa.getTxtNumpa().getText();
        String nompa = ventanapa.getTxtNompa().getText();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        String fecpa = formatoFecha.format(ventanapa.getJdcFecpa().getDate());
        String dirpa = ventanapa.getTxtDirpa().getText();
        String telpa = ventanapa.getTxtTelpa().getText();
        Pacientes p = new Pacientes();
        p.setNumpa(Integer.parseInt(numpa));
        p.setNompa(nompa);
        p.setFecpa(fecpa);
        p.setDirpa(dirpa);
        p.setTelpa(telpa);
        PacientesDB db = new PacientesDB();
        db.modificarPacientes(p);
        cargarpaciente();
        limpiarCampos();
    }

    public static void botonEliminarpa() {
        String numpa = ventanapa.getTxtNumpa().getText();
        String nombre = ventanapa.getTxtNompa().getText();
        PacientesDB padb = new PacientesDB();
        if (JOptionPane.showConfirmDialog(null, "Se eliminará el paciente: "+nombre+", ¿desea continuar?",
                "Eliminar Paciente", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            padb.eliminarPacientes(Integer.parseInt(numpa));
            cargarpaciente();
            limpiarCampos();
        }
    }

    public static void botonFicha() {
        ControladorFichapaciente.cargarTextoficha();
        ControladorFichapaciente.cargarFicha();
        ventanaficha.setVisible(true);
        ventanaficha.setLocationRelativeTo(null);
        ControladorFichapaciente.fechaSistema();
    }

//    public static void buscarPaciente() {
//        String panum = ventanapa.getTxtBuscar().getText();
//        PacientesDB padb = new PacientesDB();
//        Pacientes p = padb.buscarNum(panum);
//            if (!panum.isEmpty()) {
//                ventanapa.getTxtNompa().setText(p.getNompa());
//                ventanapa.getJdcFecpa().setDateFormatString(p.getFecpa());
//                ventanapa.getTxtDirpa().setText(p.getDirpa());
//                ventanapa.getTxtTelpa().setText(p.getTelpa());
//                }
//            cargarpaciente();
//            }
//        
    public static void cargarpaciente() {
        DefaultTableModel datos = (DefaultTableModel) ventanapa.getJtPa().getModel();
        datos.setNumRows(0);
        for (Pacientes p : new PacientesDB().getPacientes()) {
            Object[] fila = {
                p.getNumpa(),
                p.getNompa(),
                p.getFecpa(),
                //                        .substring(8, 10) + "-" + p.getFecpa().substring(5, 7) + "-" + p.getFecpa().substring(0, 4),
                //            p.getFecpa().substring(5,7 ) + "/"+p.getFecpa().substring(0, 4),
                p.getDirpa(),
                p.getTelpa()
            };
            datos.addRow(fila);
        }
    }

    public static void limpiarCampos() {
        PacientesDB pdb = new PacientesDB();
        ventanapa.getTxtNumpa().setText("" + pdb.incrementarNumpa());
        ventanapa.getTxtNompa().setText("");
        ventanapa.getJdcFecpa().setCalendar(null);
        ventanapa.getTxtDirpa().setText("");
        ventanapa.getTxtTelpa().setText("");
        ventanapa.getJdcFecpa().cleanup();
        ventanapa.getTxtBuscar().setText("");
        cargarpaciente();
        bloquearBotonficha();
    }

    
    public static void cargarNombre(String texto) {
        DefaultTableModel datos = (DefaultTableModel) ventanapa.getJtPa().getModel();
        datos.setNumRows(0);
        for (Pacientes p : new PacientesDB().getNombre(ventanapa.getTxtBuscar().getText())) {
            Object[] fila = {
                p.getNumpa(),
                p.getNompa(),
                p.getFecpa(),
                //                        .substring(8, 10) + "-" + p.getFecpa().substring(5, 7) + "-" + p.getFecpa().substring(0, 4),
                //            p.getFecpa().substring(5,7 ) + "/"+p.getFecpa().substring(0, 4),
                p.getDirpa(),
                p.getTelpa()
            };
            datos.addRow(fila);
        }
    }
    
    public static void bloquearBotonficha(){
        ventanapa.getBtnFicha().setEnabled(false);
    }
    
    public static void desbloquearBotonficha(){
        ventanapa.getBtnFicha().setEnabled(ventanapa.getTxtNompa().getText().length()!=0);
    }
}
