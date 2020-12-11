/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hadirgo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
            //mengakses db
            Class.forName("org.sqlite.JDBC");
            //membuat preparedstatement untuk query validasi
            try (Connection conn = DriverManager.getConnection(URL)) {
                //membuat preparedstatement untuk query validasi
                
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
}
