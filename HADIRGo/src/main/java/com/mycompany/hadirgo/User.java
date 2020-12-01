package com.mycompany.hadirgo;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

public class User implements Initializable{
    private ObservableList<String> daftarKelas;

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
    private ListView<String> ListDaftarKelas;
    
    @FXML
    private void listKelas(){
//        daftarKelas.add(e) //Ini ntar dari db
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
        labeluser.setText(Home.getUsername());
        daftarKelas = FXCollections.observableArrayList();
        daftarKelas.addAll("test","kelas1","kelas2");
        ListDaftarKelas.setItems(daftarKelas);
    }
    @FXML
    public void handle(MouseEvent event) {
        System.out.println(ListDaftarKelas.getSelectionModel().getSelectedItem());
    }
}
