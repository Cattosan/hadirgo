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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
//import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author KUCI
 */
public class Presensi implements Initializable {
    private int index;
    private ObservableList<Kelas> daftarKelas = FXCollections.observableArrayList();
    ArrayList<Mahasiswa> peserta = KelasDb.showDetailKelas(Admin.kodeKelas);
    ObservableList<Mahasiswa> coba = FXCollections.observableArrayList();
    private Button btnPresensi= new Button("hadir");
    
    
    @FXML
    private Label inimatkul;
    
    @FXML
    private TableView<Mahasiswa> tabPresensi;
    
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
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inimatkul.setText(Admin.namaMatkul());
//        ImageView fotoMhs = new ImageView(new Image(this.getClass().getResourceAsStream("./foto.jpg")));
        nomor.setCellValueFactory(new PropertyValueFactory<>("nomor"));
        foto.setPrefWidth(80);
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
