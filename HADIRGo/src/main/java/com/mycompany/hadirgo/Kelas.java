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
    private String kodeKelas;
    private String namaKelas;
    private byte jam;
    private byte menit;
    private String idDosen;
    
    Kelas(String kodeKelas, String namaKelas, byte jam, byte menit,String idDosen){
        this.kodeKelas = kodeKelas;
        this.namaKelas = namaKelas;
        this.jam = jam;
        this.menit = menit;
        this.idDosen = idDosen;
    }

    /**
     * @return the kodeKelas
     */
    public String getKodeKelas() {
        return kodeKelas;
    }

    /**
     * @return the namaKelas
     */
    public String getNamaKelas() {
        return namaKelas;
    }

    /**
     * @return the jam
     */
    public byte getJam() {
        return jam;
    }

    /**
     * @return the menit
     */
    public byte getMenit() {
        return menit;
    }
    
    public String toString(){
        return "[" + getKodeKelas() + "]  | " + getNamaKelas();
    }

    /**
     * @return the idDosen
     */
    public String getIdDosen() {
        return idDosen;
    }

    /**
     * @param idDosen the idDosen to set
     */
    public void setIdDosen(String idDosen) {
        this.idDosen = idDosen;
    }
}
