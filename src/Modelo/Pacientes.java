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
public class Pacientes {
    private int numpa;
    private String nompa;
    private String fecpa;
    private String dirpa;
    private String telpa;

    @Override
    public String toString() {
        return "Pacientes{" + "numpa=" + numpa + ", nompa=" + nompa + ", fecpa=" + fecpa + ", dirpa=" + dirpa + ", telpa=" + telpa + '}';
    }

    public int getNumpa() {
        return numpa;
    }

    public void setNumpa(int numpa) {
        this.numpa = numpa;
    }

    public String getNompa() {
        return nompa;
    }

    public void setNompa(String nompa) {
        this.nompa = nompa;
    }

    public String getFecpa() {
        return fecpa;
    }

    public void setFecpa(String fecpa) {
        this.fecpa = fecpa;
    }

    public String getDirpa() {
        return dirpa;
    }

    public void setDirpa(String dirpa) {
        this.dirpa = dirpa;
    }

    public String getTelpa() {
        return telpa;
    }

    public void setTelpa(String telpa) {
        this.telpa = telpa;
    }

    public Pacientes() {
    }

    public Pacientes(int numpa, String nompa, String fecpa, String dirpa, String telpa) {
        this.numpa = numpa;
        this.nompa = nompa;
        this.fecpa = fecpa;
        this.dirpa = dirpa;
        this.telpa = telpa;
    }
    
    
    

    
    
}
