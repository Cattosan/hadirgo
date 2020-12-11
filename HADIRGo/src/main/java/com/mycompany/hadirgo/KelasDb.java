/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hadirgo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Akira Rafhael
 */
public class KelasDb {
    private static final String URL = "jdbc:sqlite:admin.db";
    
    private static boolean isDataExist = false;
    
    //fungsi untuk mengembalikan semua data kelas berdasarkan akun yang login(admin atau dosen)
    public static ArrayList<Kelas> showKelas(String username){
        //container untuk menyimpan data kelas yang akan di-return
        var kelas = new ArrayList<Kelas>();
        
        //sql tergantung akun dosen
        String sql;
        
        //untuk detail kelas
        String kodeKelas;
        String namaKelas;
        byte jam;
        byte menit;
//        String namaDosen;
        
        //menyimpan sementara id_dosen untuk tampilan kelas khusus dosen tertentu
        String idDosen = "";
        
        //mengecek apakah akun dosen atau admin
        boolean isAdmin = HadirGoDb.isAdmin(username);
        if(isAdmin){
            sql = "SELECT * FROM kelas";
        } else{
            idDosen = HadirGoDb.getIdDosen(username);
            sql = "SELECT * FROM kelas WHERE id_dosen = ?";
        }
        
        try{
            Class.forName("org.sqlite.JDBC");
            try(Connection conn = DriverManager.getConnection(URL)){
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                if(!isAdmin){
                    preparedStatement.setString(1, idDosen);
                }
                
                ResultSet resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){
                    kodeKelas = resultSet.getString("kodeKelas");
                    namaKelas = resultSet.getString("namaKelas");
                    jam = resultSet.getByte("jam");
                    menit = resultSet.getByte("menit");
                    
                    kelas.add(new Kelas(kodeKelas, namaKelas, jam, menit));
                }
                return kelas;
            }
        }catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return kelas;
    }
    
    public static void insertKelasBaru(String kodeKelas, String namaKelas, byte jam, byte menit, String kodeDosen){
        String sql = "INSERT INTO kelas (kodeKelas, namaKelas, jam, menit, id_dosen) \n"
                    + "VALUES(?, ?, ?, ?, ?);";
        try{
            //mengakses db
            Class.forName("org.sqlite.JDBC");
            //membuat preparedstatement untuk query validasi
            try (Connection conn = DriverManager.getConnection(URL)) {
                //membuat preparedstatement untuk query validasi
                
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, kodeKelas);
                preparedStatement.setString(2, namaKelas);
                preparedStatement.setByte(3, jam);
                preparedStatement.setByte(4, menit);
                preparedStatement.setString(5, kodeDosen);
                
                preparedStatement.executeUpdate();
                preparedStatement.close();
                conn.close();
            }
        } catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        
    }
    
    public static void updateKelas(String kodeKelasLama, String kodeKelasBaru, String namaKelas, byte jam, byte menit, String kodeDosen){
        String sql = "UPDATE kelas SET kodeKelas = ?,\n"
                + " namaKelas = ?, \n"
                + " jam = ?, \n"
                + " menit = ?, \n"
                + " id_dosen = ? "
                + "WHERE kodeKelas = ?;";
        
        try{
            //mengakses db
            Class.forName("org.sqlite.JDBC");
            //membuat preparedstatement untuk query validasi
            try (Connection conn = DriverManager.getConnection(URL)) {
                //membuat preparedstatement untuk query validasi
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, kodeKelasBaru);
                preparedStatement.setString(2, namaKelas);
                preparedStatement.setByte(3, jam);
                preparedStatement.setByte(4, menit);
                preparedStatement.setString(5, kodeDosen);
                preparedStatement.setString(6, kodeKelasLama);
                
                preparedStatement.executeUpdate();
                preparedStatement.close();
                conn.close();
            }
        } catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    
    public static void deleteKelas(String kodeKelas){
        String sql = "DELETE FROM kelas where kodeKelas = ?;";
        try{
            //mengakses db
            Class.forName("org.sqlite.JDBC");
            //membuat preparedstatement untuk query validasi
            try (Connection conn = DriverManager.getConnection(URL)) {
                //membuat preparedstatement untuk query validasi
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, kodeKelas);
                
                preparedStatement.executeUpdate();
                preparedStatement.close();
                conn.close();
            }
        } catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    
    //fungsi untuk tampilan setelah memilih kelas
    //mengembalikan data mahasiswa yang enroll
    //belum complete, menampilkan semua mahasiswa untuk sementara
    public static ArrayList<Mahasiswa> showDetailKelas(String kodeKelas){
        Image foto;
        ImageView fotoView;
        var mahasiswa = new ArrayList<Mahasiswa>();
        int i=1;
        String sql = "SELECT * FROM mahasiswa INNER JOIN enroll on mahasiswa.nim = enroll.nim WHERE kodeKelas = ?;";
        try{
            Class.forName("org.sqlite.JDBC");
            try(Connection conn = DriverManager.getConnection(URL)){
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, kodeKelas);
                ResultSet resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){
                    
                    foto = new Image(new FileInputStream(".\\src\\main\\resources\\com\\mycompany\\hadirgo\\foto.jpg"));
                    fotoView = new ImageView(foto);
                    fotoView.setFitHeight(50);
                    fotoView.setFitWidth(50);
                    mahasiswa.add(new Mahasiswa(
                        i++,
                        resultSet.getString("nim"),
                        resultSet.getString("nama"),
                        resultSet.getString("path_foto"),
                        fotoView)
                    );
                    
//                    i++;
                }
                return mahasiswa;
            } catch (FileNotFoundException ex) {
                Logger.getLogger(KelasDb.class.getName()).log(Level.SEVERE, null, ex);
            }
        }catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return mahasiswa;
    }
}
