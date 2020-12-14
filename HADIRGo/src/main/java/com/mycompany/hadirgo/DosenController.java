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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class DosenController implements Initializable{
    private ObservableList<DosenObject> daftarDosen = FXCollections.observableArrayList();
    private ArrayList<DosenObject> daftar = KelasDb.showAllDosen();
    KelasDb db = new KelasDb();
    
    @FXML
    private JFXButton kembali;

    @FXML
    private TextField namaDosen;

    @FXML
    private TextField idDosen;

    @FXML
    private JFXButton tambah;
    
    @FXML
    private JFXButton hapus;

    @FXML
    private ListView<DosenObject> listDosen;
    
    public int sizeList(){
        return daftar.size();
    }
    
    public void daftarDosen(){
        for(int i = 0; i < sizeList(); i++){
            daftarDosen.addAll(daftar.get(i));
        }
    }
    
    private void kembali() throws IOException{
        if(HadirGoDb.isAdmin(Home.getuser())){
            App.setRoot("Admin");
        }
        else{
            App.setRoot("Dosen");
        }
    }
    
    @FXML
    private void tmbhDosen(ActionEvent event) throws IOException{
        db.createDosen(idDosen.getText().toUpperCase(), namaDosen.getText());
        kembali();
    }
    
    @FXML
    private void backcurrentuser(ActionEvent event) throws IOException{
        kembali();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        daftarDosen();
        listDosen.setItems(daftarDosen);
    }
    
    

}
