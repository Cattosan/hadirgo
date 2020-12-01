package com.mycompany.hadirgo;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Akira Rafhael
 */
public class Kelas {
    public String kodeKelas;
    public String namaKelas;
    public byte jam;
    public byte menit;
    
    Kelas(String kodeKelas, String namaKelas, byte jam, byte menit){
        this.kodeKelas = kodeKelas;
        this.namaKelas = namaKelas;
        this.jam = jam;
        this.menit = menit;
    }
}
