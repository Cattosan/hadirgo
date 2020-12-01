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
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Akira Rafhael
 */
public class KelasDb {
    private static final String URL = "jdbc:sqlite:admin.db";
    
    private static final String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS kelas (\n"
                                                + " id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n"
                                                + " kodeKelas TEXT NOT NULL,\n"
                                                + " namaKelas TEXT NOT NULL,\n"
                                                + " jam byte NOT NULL, \n"
                                                + " menit byte NOT NULL, \n"
                                                + " id_dosen VARCHAR(3) NOT NULL, \n"
                                                + " FOREIGN KEY(id_dosen)\n"
                                                + " REFERENCES dosen(id_dosen)\n"
                                                + "     ON UPDATE CASCADE\n"
                                                + "     ON DELETE CASCADE\n"
                                                + ");";
    
    private static final String CREATE_TABLE_DOSEN_SQL = "CREATE TABLE IF NOT EXISTS dosen{\n"
                                                       + " id_dosen VARCHAR(3) PRIMARY KEY NOT NULL, \n"
                                                       + " nama_dosen TEXT NOT NULL\n"
                                                       + ");";
    
    private static final String CREATE_TABLE_MAHASISWA_SQL = "CREATE TABLE IF NOT EXISTS mahasiswa(\n"
                                                           + " nim VARCHAR(8) PRIMARY KEY NOT NULL,\n"
                                                           + " nama VARCHAR(100) NOT NULL, \n"
                                                           + " path_foto TEXT NOT NULL"
                                                           + ");";
    
    private static final String INSERT_DOSEN_SQL = "INSERT INTO dosen (id_dosen, nama_dosen)\n"
                                                   + " VALUES('A01', 'Charles'),\n"
                                                   + " ('A02', 'David'),\n"
                                                   + " ('A03', 'Akira');";
    
    private static final String INSERT_MAHASISWA_SQL = "INSERT INTO mahasiswa (nim, nama, path_foto)\n"
                                                       + " VALUES('71180263','Ananda Apriliansyah C. Utama')\n" 
                                                       + ",(\"71180266\",\"Charles Condrad Putra De Sukur\")\n" +
                                                        ",(\"71180268\",\"William Sebastian Hartono\")\n" +
                                                        ",(\"71180274\",\"Angger Herlambang Amandegani\")\n" +
                                                        ",(\"71180275\",\"Jovan Roderick Reinaldo\")\n" +
                                                        ",(\"71180276\",\"David Setiawan Widodo\")\n" +
                                                        ",(\"71180279\",\"Zanetaxina Pudihang\")\n" +
                                                        ",(\"71180291\",\"Kristofan Feriadi\")\n" +
                                                        ",(\"71180296\",\"Hartapriliano Ady Nugroho\")\n" +
                                                        ",(\"71180297\",\"Grevliany Jourien Noya\")\n" +
                                                        ",(\"71180298\",\"Aloysius Gonzaga Ardhian Krisna Aji\")\n" +
                                                        ",(\"71180300\",\"Kevin Triantama Prayitno\")\n" +
                                                        ",(\"71180301\",\"Alexander Rio Wiyanto\")\n" +
                                                        ",(\"71180321\",\"Natasya Tjandra Rahardja\")\n" +
                                                        ",(\"71180324\",\"Victor Anindia\")\n" +
                                                        ",(\"71180342\",\"Alfariel Airami Tulus\")\n" +
                                                        ",(\"71180344\",\"Desendo Imanuel\")\n" +
                                                        ",(\"71180346\",\"I Nyoman Marcel Mahardika\")\n" +
                                                        ",(\"71180348\",\"Mikhael Louis Jastine\")\n" +
                                                        ",(\"71180350\",\"Yabes Qinen Yehdeya\")\n" +
                                                        ",(\"71180352\",\"Yonathan Okta Pradana\");";
    
    private static boolean isDataExist = false;
    
    public static void buatTabelAwalan(Connection conn) throws SQLException{
        Statement stmt = conn.createStatement();
        stmt.execute(CREATE_TABLE_SQL);
        stmt.execute(CREATE_TABLE_DOSEN_SQL);
        stmt.execute(CREATE_TABLE_MAHASISWA_SQL);
        stmt.execute(INSERT_DOSEN_SQL);
        stmt.execute(INSERT_MAHASISWA_SQL);
        stmt.close();
    }
    
    //fungsi untuk mengembalikan semua data kelas berdasarkan akun yang login( admin atau dosen)
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
                    
                    if(isAdmin)
                        kelas.add(new Kelas(kodeKelas, namaKelas, jam, menit));
                }
                return kelas;
            }
        }catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return kelas;
    }
    
//    private static String getNamaDosen(String username){
//        String sql = "SELECT nama_dosen FROM dosen WHERE id_dosen = \n"
//                    + "     (SELECT id_dosen FROM account WHERE username = ?);";
//        String hasil = "";
//        try{
//            Class.forName("org.sqlite.JDBC");
//            try (Connection conn = DriverManager.getConnection(URL)) {
//                PreparedStatement preparedStatement = conn.prepareStatement(sql);
//                preparedStatement.setString(1, username);
//                ResultSet resultSet = preparedStatement.executeQuery();
//                
//                if(resultSet.next()){
//                    hasil = resultSet.getString("nama_dosen");
//                }
//                
//                resultSet.close();
//                preparedStatement.close();
//                conn.close();
//            }
//        }catch(SQLException | ClassNotFoundException e){
//            e.printStackTrace();
//        }
//        return hasil;
//    }
    
    public static void insertKelasBaru(String kodeKelas, String namaKelas, byte jam, byte menit, String kodeDosen){
        String sql = "INSERT INTO kelas (kodeKelas, namaKelas, jam, menit, id_dosen) \n"
                    + "VALUES(?, ?, ?, ?, ?);";
        try{
            //mengakses db
            Class.forName("org.sqlite.JDBC");
            //membuat preparedstatement untuk query validasi
            try (Connection conn = DriverManager.getConnection(URL)) {
                //membuat preparedstatement untuk query validasi
                if(!isDataExist){
                    buatTabelAwalan(conn);
                }
                
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
    public static ArrayList<Mahasiswa> showDetailKelas(String username){
        var mahasiswa = new ArrayList<Mahasiswa>();
        String sql = "SELECT * FROM mahasiswa";
        try{
            Class.forName("org.sqlite.JDBC");
            try(Connection conn = DriverManager.getConnection(URL)){
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery();
                
                while(resultSet.next()){
                    mahasiswa.add(new Mahasiswa(
                        resultSet.getString("nim"),
                        resultSet.getString("nama"),
                        "user-removebg-preview.png")
                    );
                }
                return mahasiswa;
            }
        }catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return mahasiswa;
    }
}
