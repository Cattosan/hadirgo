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
public class PinDb {
    private static final String URL = "jdbc:sqlite:admin.db";
    
    public void pinMahasiswa(String kodeMatkul, String nim, int pertemuan){
        String sql = "INSERT into pin (kodeMatkul, nim, pertemuanKe)\n"
                    + " VALUES(?, ?, ?);";
        try{
            Class.forName("org.sqlite.JDBC");
            try(Connection conn = DriverManager.getConnection(URL)){
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, kodeMatkul);
                preparedStatement.setString(2, nim);
                preparedStatement.setInt(3, pertemuan);
                preparedStatement.execute();
                
                preparedStatement.close();
                conn.close();
            }
        }catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}
