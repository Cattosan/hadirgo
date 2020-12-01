/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hadirgo;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author KUCI
 */
public class PresensiController implements Initializable {

    @FXML
    private Text inimatkul;
    
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
        coba.add(new Mahasiswa("Path foto", "nama mahasiswa", "71123456", true, "test"));
        
        return coba;
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inimatkul.setText(User.namaMatkul());
        foto.setCellValueFactory(new PropertyValueFactory<Mahasiswa,String>("foto"));
        namaMhs.setCellValueFactory(new PropertyValueFactory<Mahasiswa,String>("nama"));
        nim.setCellValueFactory(new PropertyValueFactory<Mahasiswa,String>("nim"));
//        presensi.setCellFactory(new PropertyValueFactory<Mahasiswa,Boolean>("presensi"));
        pin.setCellValueFactory(new PropertyValueFactory<Mahasiswa,String>("pin"));
        
        tabPresensi.setItems(test());
    }    
    
}
