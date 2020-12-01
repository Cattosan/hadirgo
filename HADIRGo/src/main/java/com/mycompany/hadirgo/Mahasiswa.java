/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hadirgo;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author KUCI
 */
public class Mahasiswa {
    private String foto = new String();
    private String nama = new String();
    private String nim = new String();
    private boolean presensi = false;
    private String pin = new String();

    public Mahasiswa(String foto,String nama,String nim,boolean presensi,String pin) {
        this.foto = foto;
        this.nama = nama;
        this.nim = nim;
        this.presensi = presensi;
        this.pin = pin;
    }  

    /**
     * @return the foto
     */
    public String getFoto() {
        return foto;
    }

    /**
     * @param foto the foto to set
     */
    public void setFoto(String foto) {
        this.foto = foto;
    }

    /**
     * @return the nama
     */
    public String getNama() {
        return nama;
    }

    /**
     * @param nama the nama to set
     */
    public void setNama(String nama) {
        this.nama = nama;
    }

    /**
     * @return the nim
     */
    public String getNim() {
        return nim;
    }

    /**
     * @param nim the nim to set
     */
    public void setNim(String nim) {
        this.nim = nim;
    }

    /**
     * @return the presensi
     */
    public boolean isPresensi() {
        return presensi;
    }

    /**
     * @param presensi the presensi to set
     */
    public void setPresensi(boolean presensi) {
        this.presensi = presensi;
    }

    /**
     * @return the pin
     */
    public String getPin() {
        return pin;
    }

    /**
     * @param pin the pin to set
     */
    public void setPin(String pin) {
        this.pin = pin;
    }
    
}
