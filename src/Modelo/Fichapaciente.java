/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author aleja
 */
public class Fichapaciente {
    int idvisita;
    String fechavisita;
    String visita;
    int fknumpa;

    public Fichapaciente() {
    }

    public Fichapaciente(int idvisita, String fechavisita, String visita, int fknumpa) {
        this.idvisita = idvisita;
        this.fechavisita = fechavisita;
        this.visita = visita;
        this.fknumpa = fknumpa;
    }

    public int getIdvisita() {
        return idvisita;
    }

    public void setIdvisita(int idvisita) {
        this.idvisita = idvisita;
    }

    public String getFechavisita() {
        return fechavisita;
    }

    public void setFechavisita(String fechavisita) {
        this.fechavisita = fechavisita;
    }

    public String getVisita() {
        return visita;
    }

    public void setVisita(String visita) {
        this.visita = visita;
    }

    public int getFknumpa() {
        return fknumpa;
    }

    public void setFknumpa(int fknumpa) {
        this.fknumpa = fknumpa;
    }

    

    
    
    
    
}
