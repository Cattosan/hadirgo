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

public class Enroll {
    private static final String URL = "jdbc:sqlite:admin.db";
    private static final String CREATE_TABLE_ENROLL = "CREATE TABLE IF NOT EXISTS enroll(\n"
                                                    + " kode_kelas TEXT NOT NULL,\n"
                                                    + " nim VARCHAR(8) NOT NULL,\n"
                                                    + " PRIMARY KEY (kode_kelas, nim),"
                                                    + " FOREIGN KEY (kode_kelas)\n"
                                                    + "     REFERENCES kelas(kode_kelas),\n"
                                                    + " FOREIGN KEY (nim)\n"
                                                    + "     REFERENCES mahasiswa(nim)\n"
                                                    + ");";
    
    private String insert_enroll_sql = "INSERT INTO enroll VALUES(?,?);";
    Enroll(){}
    
    public void enroll_mahasiswa(Kelas kelas, Mahasiswa mahasiswa){
        try{
            Class.forName("org.sqlite.JDBC");
            try(Connection conn = DriverManager.getConnection(URL)){
                PreparedStatement preparedStatement = conn.prepareStatement(insert_enroll_sql);
                preparedStatement.setString(1, kelas.getKodeKelas());
                preparedStatement.setString(2, mahasiswa.getNim());
                preparedStatement.execute();
            }
        }catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    
}
