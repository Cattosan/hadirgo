package com.mycompany.hadirgo;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class Admin implements Initializable{
    private ObservableList<Kelas> daftarKelas = FXCollections.observableArrayList();
    private ArrayList<Kelas> daftar2 = KelasDb.showKelas(Home.getuser());
    public static String namaKelas;
    public static String kodeKelas;

    @FXML
    private Label labeluser;

    @FXML
    private JFXButton logoutButton;

    @FXML
    private JFXButton ubahpass;

    @FXML
    private JFXButton tambahkls;

    @FXML
    private JFXButton editkls;

    @FXML
    private JFXButton tambahdsn;

    @FXML
    private JFXButton hapusdsn;

    
    @FXML
    private ListView<Kelas> ListDaftarKelas;
    @FXML
    private AnchorPane user;
    
    public int sizekelas(){
        return daftar2.size();
    }
    
    public void listKelas(){
        for(int i = 0; i < sizekelas(); i++){
            daftarKelas.addAll(daftar2.get(i));
        }
    }
    
    @FXML
    private void backToWelcome(ActionEvent event) throws IOException {
        App.setRoot("Home");
    }
    private void moveToDetailKelas() throws IOException{
        App.setRoot("DetailKelas");
    }
    
    @FXML
    private void ubahPassword() throws IOException{
        App.setRoot("UbahPassword");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        labeluser.setText(Home.getuser());
        listKelas();
        ListDaftarKelas.setItems(daftarKelas);
    }
    
    @FXML
    public void handle(MouseEvent event) throws IOException{
        namaKelas = ListDaftarKelas.getSelectionModel().getSelectedItem().getNamaKelas();
        kodeKelas = ListDaftarKelas.getSelectionModel().getSelectedItem().getKodeKelas();
        App.setRoot("Presensi");
    }
    
    @FXML
    private void dosen(ActionEvent event) throws IOException{
        App.setRoot("tambahDosen");
    }
//    private void deletedosen(ActionEvent event) throws IOException{
//        App.setRoot("deleteDosen");
//    }
    
    @FXML
    private void tambahdaneditkelas(ActionEvent event) throws IOException{
        App.setRoot("Matkul");
    }
    @FXML
    private void enroll(ActionEvent event) throws IOException{
        App.setRoot("enroll");
    }
    
    static String namaMatkul(){
        return namaKelas;
    }
}
