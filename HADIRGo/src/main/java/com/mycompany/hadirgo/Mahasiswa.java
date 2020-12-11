/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hadirgo;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.image.ImageView;

/**
 *
 * @author Akira Rafhael
 */
public class Mahasiswa {
    private int nomor;
    private String nim;
    private String nama;    
    private String foto;
    private ImageView objekFoto;
    private Button btnHadir;
    private Button btnAbsen;
    private RadioButton pin;
        
    Mahasiswa(int nomor,String nim, String nama, String foto, ImageView objekFoto){
        this.nomor = nomor;
        this.nim = nim;
        this.nama = nama;
        this.foto = foto;
        this.objekFoto = objekFoto;
        this.btnHadir = new Button("Hadir");
        this.btnAbsen = new Button("Absen");
        this.pin = new RadioButton();
    }

    public String getNama(){
        return nama;
    }

    public String getNim(){
        return nim;
    }

    public int getNomor() {
        return nomor;
    }

    public String getFoto(){
        return this.foto;
    }
    
    public void setNomor(int nomor) {
        this.nomor = nomor;
    }

    /**
     * @param nim the nim to set
     */
    public void setNim(String nim) {
        this.nim = nim;
    }

    /**
     * @param nama the nama to set
     */
    public void setNama(String nama) {
        this.nama = nama;
    }

    /**
     * @param foto the foto to set
     */
    public void setFoto(String foto) {
        this.foto = foto;
    }

    /**
     * @return the objekFoto
     */
    public ImageView getObjekFoto() {
        return objekFoto;
    }

    /**
     * @param objekFoto the objekFoto to set
     */
    public void setObjekFoto(ImageView objekFoto) {
        this.objekFoto = objekFoto;
    }

    /**
     * @return the pin
     */
    public RadioButton getPin() {
        return pin;
    }

    /**
     * @param pin the pin to set
     */
    public void setPin(RadioButton pin) {
        this.pin = pin;
    }

    /**
     * @return the btnHadir
     */
    public Button getBtnHadir() {
        return btnHadir;
    }

    /**
     * @param btnHadir the btnHadir to set
     */
    public void setBtnHadir(Button btnHadir) {
        this.btnHadir = btnHadir;
    }

    /**
     * @return the btnAbsen
     */
    public Button getBtnAbsen() {
        return btnAbsen;
    }

    /**
     * @param btnAbsen the btnAbsen to set
     */
    public void setBtnAbsen(Button btnAbsen) {
        this.btnAbsen = btnAbsen;
    }

}
