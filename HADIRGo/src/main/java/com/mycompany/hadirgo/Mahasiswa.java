/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hadirgo;

import javafx.scene.image.ImageView;

/**
 *
 * @author Akira Rafhael
 */
public class Mahasiswa {
    private int nomor;
    private String nim;
    private String nama;
    
    //menyimpan path foto
    private String foto;
        
    Mahasiswa(int nomor,String nim, String nama, String foto){
        this.nomor = nomor;
        this.nim = nim;
        this.nama = nama;
        this.foto = foto;
    }

    public String getNama(){
        return nama;
    }

    public String getNim(){
        return nim;
    }

    public String getFoto(){
        return foto;
    }

    /**
     * @return the nomor
     */
    public int getNomor() {
        return nomor;
    }

    /**
     * @param nomor the nomor to set
     */
    public void setNomor(int nomor) {
        this.nomor = nomor;
    }

}
