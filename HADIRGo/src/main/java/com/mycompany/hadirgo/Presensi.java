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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
//import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Catttttoooossaaannnnnhhh
 */
public class Presensi implements Initializable{
    private int index;
    private ObservableList<Kelas> daftarKelas = FXCollections.observableArrayList();
    ArrayList<Mahasiswa> peserta = KelasDb.showDetailKelas(user());
    ObservableList<Mahasiswa> coba = FXCollections.observableArrayList();
    ObservableList<String> list = FXCollections.observableArrayList();
    String cmbMingguKe;
    String kodek;
    String kodekls;
    boolean comboBox;
    boolean presensih;
   

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
        checkiecheckie(kodek, hasilMinggu());
        namaMhs.setCellFactory(new Callback<TableColumn<Mahasiswa, String>, TableCell<Mahasiswa, String>> (){
            @Override
            public TableCell<Mahasiswa, String> call(TableColumn<Mahasiswa, String> param) {
                return new TableCell<Mahasiswa, String>(){
                    @Override
                    public void updateItem(String item, boolean empty){
                        if(presensih){
                            this.setTextFill(Color.GREEN);
                            setText(item);
                        }
                        else{
                            this.setTextFill(Color.RED);
                            setText(item);
                        }
                    }
                };
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        System.out.println(presensih);
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
    
    private boolean checkBoxCheck(){
        if(mingguPertemuan.getSelectionModel().isEmpty()){
            return false;
        }
        else{
            return true;
        }
    }
    
    private void error(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/hadirgo2.png")));
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Harap Pilih Minggu Perkuliahan Terlebih Dahulu");
        alert.showAndWait();
    }
    
    private void Buttons(){
        Callback<TableColumn<Mahasiswa, String>, TableCell<Mahasiswa, String>> colBtnHadir = new Callback<TableColumn<Mahasiswa, String>, TableCell<Mahasiswa, String>>(){
            @Override
            public TableCell<Mahasiswa, String> call(final TableColumn<Mahasiswa, String> param) {
                final TableCell<Mahasiswa, String> cell = new TableCell<Mahasiswa, String>(){
                    private Button btnhadir2 = new Button("Hadir");
                    {
                        btnhadir2.setOnAction((ActionEvent event) -> {
                            comboBox = checkBoxCheck();
                            if(comboBox){
                                Mahasiswa mhs = getTableView().getItems().get(getIndex());
                                btnhadir2.setStyle("-fx-background-color: #42ff8e");
                                btnhadir2.setDisable(true);
                                PresensiDb.presensi_mahasiswa(Admin.kodeKelas, mhs.getNim().toString(), (byte) hasilMinggu());
                                System.out.println("MAHASISWA: " + mhs.getNama().toString() + mhs.getNim().toString() + " HADIR NJIR");
                            }
                            else{
                                error();
                            }
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
        
        
        Callback<TableColumn<Mahasiswa, String>, TableCell<Mahasiswa, String>> colBtnAbsen = (final TableColumn<Mahasiswa, String> param) -> {
            final TableCell<Mahasiswa, String> cell = new TableCell<Mahasiswa, String>(){
                private Button btnabsen2 = new Button("Absen");
                {
                    btnabsen2.setOnAction((ActionEvent event) -> {
                        comboBox = checkBoxCheck();
                        if(comboBox){
                            Mahasiswa mhs = getTableView().getItems().get(getIndex());
                            btnabsen2.setStyle("-fx-background-color: #ff5f42");
                            btnabsen2.setDisable(true);
                            PresensiDb.absen_mahasiswa(Admin.kodeKelas, mhs.getNim().toString(), (byte) hasilMinggu());
                            System.out.println("Mahasiswa: " + mhs.getNama().toString() + " ABSENNNN");
                        }
                        else{
                            error();
                        }
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
        };
        btnHadir.setCellFactory(colBtnHadir);
        btnAbsen.setCellFactory(colBtnAbsen);
    }
    
    private void checkiecheckie(String kode, int minggu){
        int size = jumlahPeserta();
        String nimmhs = "71180279";
        ArrayList<String> daftarnim = new ArrayList<>();
        daftarnim.clear();
        for(int i = 0; i<size; i++){
            daftarnim.add(peserta.get(i).getNim());
        }
        PresensiDb pdb = new PresensiDb();
        for(int i=0; i<size; i++){
            System.out.println(daftarnim.get(i));
            presensih = pdb.isMahasiswaIniHadir(kode, daftarnim.get(i), minggu);
        }
        
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pertemuan();
        kodek = user();
        Buttons();
        inimatkul.setText(Admin.namaMatkul());
        nomor.setCellValueFactory(new PropertyValueFactory<>("nomor"));
        foto.setPrefWidth(80);
        foto.setCellValueFactory(new PropertyValueFactory<>("objekFoto"));
        namaMhs.setCellValueFactory(new PropertyValueFactory<>("nama"));
        nim.setCellValueFactory(new PropertyValueFactory<>("nim"));
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
    
    @FXML
    private void showReport(ActionEvent event) throws IOException{
        KelasDb.showReportHadir(Admin.kodeKelas, Admin.namaKelas, hasilMinggu());
    }
}
