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
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class MatkulController implements Initializable{
    private ObservableList<Kelas> daftarKelas = FXCollections.observableArrayList();
    private ArrayList<Kelas> daftar = KelasDb.showKelas(Home.getuser());
    private String kodeKelas;
    private String matkul;
    private int jam;
    private int menit;
    private String idDosen;
    KelasDb tambahKelas = new KelasDb();

    @FXML
    private TextField tambahKodeKelas;
    @FXML
    private TextField tambahMataKuliah;
    @FXML
    private TextField tambahJam;
    @FXML
    private TextField tambahMenit;
    @FXML
    private TextField tambahIDdosen;
    @FXML
    private JFXButton Kembali;
    @FXML
    private TextField KodeKelas;
    @FXML
    private TextField MataKuliah;
    @FXML
    private TextField Jam;
    @FXML
    private TextField Menit;
    @FXML
    private JFXButton btnTambahMatKul;
    @FXML
    private JFXButton btnUpdate;
    @FXML
    private JFXButton btnBatal;
    @FXML
    private TextField IDdosen;
    @FXML
    private ListView<Kelas> listKelas;
    
    public int sizekelas(){
        return daftar.size();
    }
    
    public void listKelas(){
        for(int i = 0; i < sizekelas(); i++){
            daftarKelas.addAll(daftar.get(i));
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
    private void pilihKelas(MouseEvent event) throws IOException{
        btnUpdate.setVisible(true);
        btnTambahMatKul.setVisible(false);
        KodeKelas.setEditable(false);
        MataKuliah.setEditable(false);
        KodeKelas.setText(listKelas.getSelectionModel().getSelectedItem().getKodeKelas());
        MataKuliah.setText(listKelas.getSelectionModel().getSelectedItem().getNamaKelas());
        Jam.setText(Integer.toString(listKelas.getSelectionModel().getSelectedItem().getJam()));
        Menit.setText(Integer.toString(listKelas.getSelectionModel().getSelectedItem().getMenit()));
        IDdosen.setText(listKelas.getSelectionModel().getSelectedItem().getIdDosen());
    }
    
    @FXML
    private void tambahKelas(ActionEvent event) throws IOException{
        kodeKelas = KodeKelas.getText();
        matkul = MataKuliah.getText();
        jam = Integer.parseInt(Jam.getText());
        menit = Integer.parseInt(Menit.getText());
        idDosen = IDdosen.getText();
        
//        if (kodeKelas.equals(daftar.get(0))) {
//            
//        }
        
        tambahKelas.insertKelasBaru(kodeKelas, matkul, (byte) jam, (byte)menit, idDosen);
        kembali();
    }
    @FXML
    private void ubahKelas(ActionEvent event) throws IOException{
        kodeKelas = KodeKelas.getText();
        matkul = MataKuliah.getText();
        jam = Integer.parseInt(Jam.getText());
        menit = Integer.parseInt(Menit.getText());
        idDosen = IDdosen.getText();
        
        
        
        tambahKelas.updateKelas(kodeKelas, matkul, (byte) jam, (byte)menit, idDosen);
        kembali();
    }
    
    @FXML
    private void btnBatal(ActionEvent event) throws IOException{
        btnUpdate.setVisible(false);
        btnTambahMatKul.setVisible(true);
        KodeKelas.setEditable(true);
        MataKuliah.setEditable(true);
        KodeKelas.clear();
        MataKuliah.clear();
        Jam.clear();
        Menit.clear();
        IDdosen.clear();
    }
            
    @FXML
    private void backcurrentuser(ActionEvent event) throws IOException{
     kembali();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnUpdate.setVisible(false);
        listKelas();
        listKelas.setItems(daftarKelas);
    }

}
