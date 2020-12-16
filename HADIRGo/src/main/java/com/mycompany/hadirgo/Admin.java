package com.mycompany.hadirgo;

import com.jfoenix.controls.JFXButton;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class Admin implements Initializable{
    private ObservableList<Kelas> daftarKelas = FXCollections.observableArrayList();
    private List<Kelas> daftar2 = KelasDb.showKelas(Home.getuser());
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
    private TextField boxPencarian;

    @FXML
    private JFXButton btnHapusPencarian;
    
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
    
    @FXML
    private void moveToDetailKelas(ActionEvent event) throws IOException{
        App.setRoot("DetailKelas");
    }
    
    @FXML
    private void ubahPassword(ActionEvent event) throws IOException{
        App.setRoot("UbahPassword");
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
    
    @FXML
    private void tambahdaneditkelas(ActionEvent event) throws IOException{
        App.setRoot("Matkul");
    }
    
    @FXML
    private void batalCari(ActionEvent event) throws IOException{
        boxPencarian.clear();
    }
    
    static String namaMatkul(){
        return namaKelas;
    }
    
    private ObservableList<Kelas> filterlist(List<Kelas> list, String cari){
        List<Kelas> filteredList = new ArrayList<>();
        for(Kelas kelas : list){
            if(pencarianFunct(kelas, cari)){
                filteredList.add(kelas);
            }
        }
        return FXCollections.observableList(filteredList);
    }
    
    private boolean pencarianFunct(Kelas kelas, String cari){
        return kelas.getKodeKelas().toLowerCase().contains(boxPencarian.getText().toLowerCase()) ||
                kelas.getNamaKelas().toLowerCase().contains(boxPencarian.getText().toLowerCase());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        labeluser.setText(Home.getuser());
        listKelas();
        ListDaftarKelas.setItems(daftarKelas);
        
        boxPencarian.textProperty().addListener((o) -> {
            ListDaftarKelas.setItems(filterlist(daftarKelas, namaKelas));
        });
    }
    
}
