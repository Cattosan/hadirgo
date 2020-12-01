package com.mycompany.hadirgo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class HadirGoDb {
    //db dibuat dalam fungsi createAndInsert() secara otomatis
    //insert data juga dilakukan pada fungsi itu
    private static final String URL = "jdbc:sqlite:admin.db";
    
    //membuat tabel baru jika belum ada
    //isAdmin adalah state yang digunakan untuk membedakan akun dosen dan admin
    //0 = dosen
    //1 = admin
    private static final String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS account (\n"
                                                + " id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n"
                                                + " username TEXT NOT NULL,\n"
                                                + " password TEXT NOT NULL,\n"
                                                + " isAdmin BIT NOT NULL,\n"
                                                + " id_dosen VARCHAR(3),"
                                                + " FOREIGN KEY(id_dosen)\n"
                                                + " REFERENCES dosen (id_dosen)\n"
                                                + "     ON UPDATE CASCADE\n"
                                                + "     ON DELETE CASCADE\n"
                                                + ");";
    
    //insert data dosen dan admin untuk login pertama kali
    //username dan password akun dosen = dosen, dosen
    //akun admin = admin, admin 
    private static final String INSERT_ACCOUNT_SQL = "INSERT INTO account (username, password, isAdmin, id_dosen)\n"
                                                     + " VALUES('admin', 'admin', 1, ''),"
                                                     + " ('dosen', 'dosen', 0, 'A01');";
    
    //query untuk validasi akun waktu login di fungsi validate()
    private static final String VALIDATE_QUERY = "SELECT * FROM account WHERE \n"
                                                  + "  username = ? AND password = ?;";
    
    //query untuk mencari akun dalam db untuk keperluan 
    //cek akun apakah admin atau dosen
    private static final String SEARCH_QUERY = "SELECT * FROM account WHERE \n"
                                                  + "  username = ?";
    
    //state untuk mengecek apakah db sudah dibuat dengan akun admin dan dosen
    //true jika telah dibuat dengan fungsi createAndInsert()
    private static boolean isAccountInserted = false;

    //membuat admin.db dan akun dosen dan admin
    private static void createInitDb(){
        try{
            Class.forName("org.sqlite.JDBC");
            try (Connection conn = DriverManager.getConnection(URL); Statement statement = conn.createStatement()) {
                statement.execute(CREATE_TABLE_SQL);
                statement.execute(INSERT_ACCOUNT_SQL);
                isAccountInserted = true;
                statement.close();
                conn.close();
            }
        } catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    //untuk saat ini akun baru yang dibuat hanya akun dengan role dosen
    public static void createNewAccount(String username, String password){
        String sql = "INSERT INTO account (username, password, isAdmin) VALUES(?, ?, 0)";

        try {
            Class.forName("org.sqlite.JDBC");
            try (Connection conn = DriverManager.getConnection(URL)){
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, username);
                stmt.setString(2, password);
                stmt.execute(sql);

                stmt.close();
                conn.close();
            }
        } catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    for(int i = 0; i < mahasiswa.size(); i++){
        mahasiswa.get(i).nama;
    }

    //fungsi untuk validasi login
    public static boolean validate(String username, String password){
        if(!isAccountInserted){
            createInitDb();
        }

        try{
            //mengakses db
            Class.forName("org.sqlite.JDBC");
            //membuat preparedstatement untuk query validasi
            try (Connection conn = DriverManager.getConnection(URL)) {
                //membuat preparedstatement untuk query validasi
                PreparedStatement preparedStatement = conn.prepareStatement(VALIDATE_QUERY);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                
                ResultSet resultSet = preparedStatement.executeQuery();
                if(resultSet.next()){
                    resultSet.close();
                    preparedStatement.close();
                    conn.close();
                    return true;
                }
                
                resultSet.close();
                preparedStatement.close();
                conn.close();
            }
            return false;
        } catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        
        return false;
    }
    
    //fungsi untuk mengecek akun apakah admin atau dosen
    public static boolean isAdmin(String username){
        try {
            Class.forName("org.sqlite.JDBC");
            try(Connection conn = DriverManager.getConnection(URL)) {
                PreparedStatement preparedStatement = conn.prepareStatement(SEARCH_QUERY);
                preparedStatement.setString(1, username);
                ResultSet resultSet = preparedStatement.executeQuery();
                if(resultSet.getInt("isAdmin") == 1){
                    resultSet.close();
                    preparedStatement.close();
                    conn.close();
                    return true;
                }
                
                resultSet.close();
                preparedStatement.close();
                conn.close();
                return false;
            } 
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public static String getIdDosen(String username){
        String sql = "SELECT id_dosen from account\n"
                + " WHERE username = ?";
        String hasil = "";
        try{
            Class.forName("org.sqlite.JDBC");
            try(Connection conn = DriverManager.getConnection(URL)){
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, username);
                ResultSet resultSet = preparedStatement.executeQuery();
                
                if(resultSet.next() && !resultSet.getString("id_dosen").equals("")){
                    hasil = resultSet.getString("id_dosen");
                }
                return hasil;
            }
        }catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return hasil;
    }

    //self explanatory
    public static void editPassword(String username, String newPassword){
        String sql = "UPDATE account SET password = ? WHERE username = ?;";

        try {
            Class.forName("org.sqlite.JDBC");
            try(Connection conn = DriverManager.getConnection(URL)) {
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, newPassword);
                preparedStatement.setString(2, username);
                preparedStatement.executeUpdate();

                preparedStatement.close();
                conn.close();
            } 
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void deleteAccount(String username){
        String sql = "DELETE from account where username = ?";
        try {
            Class.forName("org.sqlite.JDBC");
            try(Connection conn = DriverManager.getConnection(URL)) {
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, username);
                preparedStatement.executeUpdate();

                preparedStatement.close();
                conn.close();
            } 
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //fungsi untuk mendapatkan semua akun yang ada dalam database selain akun admin
    //untuk melakukan fungsi tambah, edit, hapus akun dosen
    public static ArrayList<String> getAllAccount(){
        var akun = new ArrayList<String>();
        String sql = "SELECT * FROM account";

        try {
            Class.forName("org.sqlite.JDBC");
            try (Connection conn = DriverManager.getConnection(URL)){
                Statement stmt = conn.createStatement();
                ResultSet rst = stmt.executeQuery(sql);

                while(rst.next()){

                    if(rst.getByte("isAdmin") == 0)
                        akun.add(rst.getString("username"));
                }
            } 
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return akun;
    }
    
    public static void logout(){
        
    }
}