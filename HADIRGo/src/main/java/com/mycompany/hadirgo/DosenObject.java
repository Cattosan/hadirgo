/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hadirgo;

/**
 *
 * @author Akira Rafhael
 */
public class DosenObject {
    private String idDosen;
    private String namaDosen;
    
    DosenObject(String idDosen, String namaDosen){
        this.idDosen = idDosen;
        this.namaDosen = namaDosen;
    }

    /**
     * @return the idDosen
     */
    public String getIdDosen() {
        return idDosen;
    }

    /**
     * @return the namaDosen
     */
    public String getNamaDosen() {
        return namaDosen;
    }
    
    public String toString(){
        return getIdDosen() + " | " + getNamaDosen();
    }
    
}
