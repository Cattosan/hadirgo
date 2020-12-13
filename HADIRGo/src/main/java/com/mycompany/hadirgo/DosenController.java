package com.mycompany.hadirgo;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class DosenController {

    @FXML
    private JFXButton kembali;

    @FXML
    private TextField namaDosen;

    @FXML
    private TextField idDosen;

    @FXML
    private JFXButton tambah;

    @FXML
    private ListView<DaftarDosen> listDosen;
    
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
