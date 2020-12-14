/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hadirgo;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author KUCI
 */
public class Presensi implements Initializable {
    String kodekls;
    private ObservableList<Kelas> daftarKelas = FXCollections.observableArrayList();
    ArrayList<Mahasiswa> peserta = KelasDb.showDetailKelas(user());
    ObservableList<Mahasiswa> coba = FXCollections.observableArrayList();
    ObservableList<String> list = FXCollections.observableArrayList();
    String cmbMingguKe;
    @FXML
    private Label inimatkul;
    
    @FXML
    private TableView<Mahasiswa> tabPresensi;
    
    @FXML
    private ComboBox<String> mingguPertemuan;
    
    @FXML
    private TableColumn<Mahasiswa, Integer> nomor;

    @FXML
    private TableColumn<Mahasiswa, ImageView> foto;

    @FXML
    private TableColumn<Mahasiswa, String> namaMhs;

    @FXML
    private TableColumn<Mahasiswa, String> nim;

    @FXML
    private TableColumn<Mahasiswa, String> presensi;
    
    @FXML
    private TableColumn<Mahasiswa, String> btnHadir;

    @FXML
    private TableColumn<Mahasiswa, String> btnAbsen;

    @FXML
    private TableColumn<Mahasiswa, Object> pin;
    
    @FXML
    private void hadir(ActionEvent event) throws IOException{
        
    }
    @FXML
    private void absen(ActionEvent event) throws IOException{
        
    }
    
    private String user(){
        if(HadirGoDb.isAdmin(Home.getuser())){
            kodekls = Admin.kodeKelas;
        }
        else{
            kodekls = Dosen.kodeKelas;
        }
     return kodekls;
    }
    
    private int jumlahPeserta(){
        return peserta.size();
    }
    
    private ObservableList<Mahasiswa> peserta(){
        coba.clear();
        for(int i=0;i<jumlahPeserta();i++){
            coba.add(peserta.get(i));
        }
        return coba;
    }
    
    private void pertemuan(){
        int jumlahMinggu = 13;
        for(int i = 1; i <= jumlahMinggu; i++){
            list.add("Minggu ke-" + i);
        }
        mingguPertemuan.setItems(list);
    }
    
    public void mingguBtn(ActionEvent event) throws IOException{
       cmbMingguKe = mingguPertemuan.getSelectionModel().getSelectedItem();
       System.out.println(hasilMinggu());
       iniYangHadir();
    }
    private int hasilMinggu(){
        StringBuilder sb = new StringBuilder();
        for(Character c : cmbMingguKe.toCharArray()){
            if(Character.isDigit(c)){
                sb.append(c);
            }
        }
        
        return Integer.parseInt(sb.toString());
    }
    
    private void iniYangHadir(){
        boolean pesertaHadir=false;
        PresensiDb pakeYangIni = new PresensiDb();
        if(pakeYangIni.isMahasiswaHadir(Admin.kodeKelas, hasilMinggu())) {
            System.out.println("Kalo ini jalan");            
        }
        else{
            System.out.println("ndak jalan");
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pertemuan();
        inimatkul.setText(Admin.namaMatkul());
        nomor.setCellValueFactory(new PropertyValueFactory<>("nomor"));
        foto.setCellValueFactory(new PropertyValueFactory<>("objekFoto"));
        namaMhs.setCellValueFactory(new PropertyValueFactory<>("nama"));
        nim.setCellValueFactory(new PropertyValueFactory<>("nim"));
        btnHadir.setCellValueFactory(new PropertyValueFactory<>("btnHadir"));
        btnAbsen.setCellValueFactory(new PropertyValueFactory<>("btnAbsen"));
        pin.setCellValueFactory(new PropertyValueFactory<>("pin"));
        tabPresensi.setItems(peserta());
    }    
    
    @FXML
    private void backcurrentuser(ActionEvent event) throws IOException{
        if(HadirGoDb.isAdmin(Home.getuser())){
            App.setRoot("Admin");
        }
        else{
            App.setRoot("Dosen");
        }
    }
}
