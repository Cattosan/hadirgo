package com.mycompany.hadirgo;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
//import javafx.event.Event;
//import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

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
    private Button test;
    
    @FXML
    private ListView<Kelas> ListDaftarKelas;
    
    public int sizekelas(){
        return daftar2.size();
    }
    
    private void listKelas(){
        for(int i = 0; i < sizekelas(); i++){
            daftarKelas.addAll(daftar2.get(i));
        }
    }
    
    @FXML
    private void backToWelcome(ActionEvent event) throws IOException {
        App.setRoot("Home");
    }
    @FXML
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
        //System.out.println(ListDaftarKelas.getSelectionModel().getSelectedItem());
        //System.out.println(ListDaftarKelas.getSelectionModel().getSelectedItem());
        namaKelas = ListDaftarKelas.getSelectionModel().getSelectedItem().getNamaKelas();
        kodeKelas = ListDaftarKelas.getSelectionModel().getSelectedItem().getKodeKelas();
        App.setRoot("Presensi");
    }
    
    @FXML
    private void tambahdosen(ActionEvent event) throws IOException{
        App.setRoot("AddDosen");
    }
    
    static String namaMatkul(){
        return namaKelas;
    }
}
