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
public class Mahasiswa {
    private String nim;
    private String nama;
    
    //menyimpan path foto
    private String foto;
    
    Mahasiswa(String nim, String nama, String foto){
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

}
