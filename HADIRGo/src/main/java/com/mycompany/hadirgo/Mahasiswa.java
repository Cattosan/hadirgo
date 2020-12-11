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
    private ImageView objekFoto;
        
    Mahasiswa(int nomor,String nim, String nama, String foto, ImageView objekFoto){
        this.nomor = nomor;
        this.nim = nim;
        this.nama = nama;
        this.foto = foto;
        this.objekFoto = objekFoto;
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

}
