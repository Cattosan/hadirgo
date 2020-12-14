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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
//import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author KUCI
 */
public class Presensi implements Initializable{
    private int index;
    private ObservableList<Kelas> daftarKelas = FXCollections.observableArrayList();
    ArrayList<Mahasiswa> peserta = KelasDb.showDetailKelas(Admin.kodeKelas);
    ObservableList<Mahasiswa> coba = FXCollections.observableArrayList();
    ObservableList<String> list = FXCollections.observableArrayList();
    String cmbMingguKe;
    public Button btnhadir2 = new Button("Hadir");
    

    @FXML
    private Label inimatkul;
    
    @FXML
    private ComboBox<String> mingguPertemuan;
    
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
    
    public String toString(Mahasiswa mhs){
        return "" + mhs.toString();
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
        String kodek = Admin.kodeKelas;
        Callback<TableColumn<Mahasiswa, String>, TableCell<Mahasiswa, String>> colBtnHadir = new Callback<TableColumn<Mahasiswa, String>, TableCell<Mahasiswa, String>>(){
            @Override
            public TableCell<Mahasiswa, String> call(final TableColumn<Mahasiswa, String> param) {
                final TableCell<Mahasiswa, String> cell = new TableCell<Mahasiswa, String>(){
                    
                    {
                        btnhadir2.setOnAction((ActionEvent event) -> {
                            Mahasiswa mhs = getTableView().getItems().get(getIndex());
                            btnhadir2.setStyle("-fx-background-color: #42ff8e");
                            btnhadir2.setDisable(true);
                            PresensiDb.presensi_mahasiswa(Admin.kodeKelas, mhs.getNim().toString(), (byte) hasilMinggu());
                            //btnabsen2.setStyle("none");
                            //btnabsen2.setDisable(false);
                            System.out.println("MAHASISWA: " + mhs.getNama().toString() + " HADIR NJIR");
                            System.out.println("MAHASISWA: " + mhs.getNim().toString() + " HADIR NJIR");
                        });
                    }
                    
                                        
                    @Override
                    public void updateItem(String item, boolean empty){
                        super.updateItem(item, empty);
                        if(empty){
                            setGraphic(null);
                        }
                        else{
                            setGraphic(btnhadir2);
                        }
                    }
                };
                return cell;
            }
        };
        
        Callback<TableColumn<Mahasiswa, String>, TableCell<Mahasiswa, String>> colBtnAbsen = new Callback<TableColumn<Mahasiswa, String>, TableCell<Mahasiswa, String>>(){
            @Override
            public TableCell<Mahasiswa, String> call(final TableColumn<Mahasiswa, String> param) {
                final TableCell<Mahasiswa, String> cell = new TableCell<Mahasiswa, String>(){
                    public Button btnabsen2 = new Button("Absen");
                    {
                        btnabsen2.setOnAction((ActionEvent event) -> {
                            Mahasiswa mhs = getTableView().getItems().get(getIndex());
                            btnabsen2.setStyle("-fx-background-color: #ff5f42");
                            btnabsen2.setDisable(true);
                            //btnhadir2.setStyle("none");
                            //btnhadir2.setDisable(false);
                            System.out.println("Mahasiswa: " + mhs.getNama().toString() + " ABSENNNN");
                        });
                    }
                    
                                        
                    @Override
                    public void updateItem(String item, boolean empty){
                        super.updateItem(item, empty);
                        if(empty){
                            setGraphic(null);
                        }
                        else{
                            setGraphic(btnabsen2);
                        }
                    }
                };
                return cell;
            }
        };
        
        inimatkul.setText(Admin.namaMatkul());
//      ImageView fotoMhs = new ImageView(new Image(this.getClass().getResourceAsStream("./foto.jpg")));
        nomor.setCellValueFactory(new PropertyValueFactory<>("nomor"));
        foto.setPrefWidth(80);
        foto.setCellValueFactory(new PropertyValueFactory<>("objekFoto"));
        namaMhs.setCellValueFactory(new PropertyValueFactory<>("nama"));
        nim.setCellValueFactory(new PropertyValueFactory<>("nim"));
        btnHadir.setCellFactory(colBtnHadir);
        btnAbsen.setCellFactory(colBtnAbsen);
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
