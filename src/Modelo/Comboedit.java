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
public class Comboedit {
    int id;
    String lab;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLab() {
        return lab;
    }

    public void setLab(String lab) {
        this.lab = lab;
    }
    
    public Comboedit() {
    }

    public Comboedit(int id, String lab) {
        this.id = id;
        this.lab = lab;
    }

}
