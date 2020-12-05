/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hadirgo;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author KUCI
 */
public class Presensi implements Initializable {
    private ObservableList<Kelas> daftarKelas = FXCollections.observableArrayList();
    
    @FXML
    private Label inimatkul;
    
    @FXML
    private TableView<Mahasiswa> tabPresensi;
    
//    @FXML
//    private TableColumn<?, int> nomor;

    @FXML
    private TableColumn<Mahasiswa, String> foto;

    @FXML
    private TableColumn<Mahasiswa, String> namaMhs;

    @FXML
    private TableColumn<Mahasiswa, String> nim;

    @FXML
    private TableColumn<Mahasiswa, Boolean> presensi;

    @FXML
    private TableColumn<Mahasiswa, String> pin;
    
    @FXML
    protected void peserta(){
//        ObservableList<Mahasiswa> data = tabPresensi.getItems();
//        data.add(new Mahasiswa("foto", "nama", "12345678", true, "test"));
    }
    
    public ObservableList<Mahasiswa> test(){
        ObservableList<Mahasiswa> coba = FXCollections.observableArrayList();
        coba.add(new Mahasiswa("Path foto", "nama mahasiswa", "71123456"));
        return coba;
    }
    
    private void showKelasDetail(){
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inimatkul.setText(Admin.namaMatkul());
        foto.setCellValueFactory(new PropertyValueFactory<>("path_foto"));
        namaMhs.setCellValueFactory(new PropertyValueFactory<>("nama"));
        nim.setCellValueFactory(new PropertyValueFactory<>("nim"));
//      presensi.setCellFactory(new PropertyValueFactory<Mahasiswa,Boolean>("presensi"));
//      pin.setCellValueFactory(new PropertyValueFactory<Mahasiswa,String>("pin"));
        
        tabPresensi.setItems(test());
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
