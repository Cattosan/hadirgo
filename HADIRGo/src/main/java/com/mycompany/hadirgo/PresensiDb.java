/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hadirgo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Akira Rafhael
 */
public class PresensiDb {
    private static final String URL = "jdbc:sqlite:admin.db";
    
    public void presensi_mahasiswa(String kodeKelas, String nim, byte pertemuanKe){
        String sql = "INSERT INTO presensi (kodeKelas, nim, pertemuanKe) \n"
                    + " VALUES(?, ?, ?);";
        try{
            Class.forName("org.sqlite.JDBC");
            try (Connection conn = DriverManager.getConnection(URL)) {
                
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, kodeKelas);
                preparedStatement.setString(2, nim);
                preparedStatement.setByte(3, pertemuanKe);
                
                preparedStatement.execute();
                preparedStatement.close();
                conn.close();
            }
        } catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    
    public void absen_mahasiswa(String kodeKelas, String nim, byte pertemuanKe){
        String sql = "DELETE FROM presensi WHERE kodeKelas = ? AND nim = ? AND pertemuanKe = ?;";
        try{
            Class.forName("org.sqlite.JDBC");
            try (Connection conn = DriverManager.getConnection(URL)) {
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, kodeKelas);
                preparedStatement.setString(2, nim);
                preparedStatement.setByte(3, pertemuanKe);
                
                preparedStatement.executeUpdate();
                preparedStatement.close();
                conn.close();
            }
        } catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    
    public boolean isMahasiswaHadir(String kodeKelas, int pertemuan){
        String sql = "SELECT * from presensi where kodeKelas = ? AND pertemuanKe = ?";
        
        try{
            Class.forName("org.sqlite.JDBC");
            try (Connection conn = DriverManager.getConnection(URL)) {
                
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, kodeKelas);
                preparedStatement.setInt(2, pertemuan);
                
                ResultSet resultSet = preparedStatement.executeQuery();
                if(resultSet.next()){
                    return true;
                }
                
                resultSet.close();
                preparedStatement.close();
                conn.close();
                
                return false;
            }
        } catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return false;
    }
    
//    public int cekMingguKe(){
//        String sql = "SELECT DISTINCT pertemuan FROM presensi";
//        
//    }
                            
}
