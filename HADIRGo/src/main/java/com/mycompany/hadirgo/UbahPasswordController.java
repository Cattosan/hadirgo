/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hadirgo;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;


public class UbahPasswordController{
    private String pwdLama;
    private String pwdBaru;
    private String confirmPwd;
    
    @FXML
    private PasswordField passwordLama;
    @FXML
    private PasswordField passwordBaru;
    @FXML
    private PasswordField confirmPasswordBaru;
    
    @FXML
    private void cekPassword(ActionEvent event) throws IOException{
        pwdLama = passwordLama.getText().trim();
        pwdBaru = passwordBaru.getText().trim();
        confirmPwd = confirmPasswordBaru.getText().trim();
        
        if(!pwdLama.equals(Home.pwd)){
            notifikasiError("Password lama yang diinputkan salah");
        }
        else if(pwdLama.equals(pwdBaru)){
            notifikasiError("Password lama tidak boleh sama dengan password baru");
        } else if(!pwdBaru.equals(confirmPwd)){
            notifikasiError("Konfirmasi password baru berbeda");
        } else{
            HadirGoDb.editPassword(Home.usr, pwdBaru);
            notifikasiBerhasil("Password berhasil diubah");
        }
    }
    
    private void notifikasiError(String isiNotif){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(isiNotif);
        alert.showAndWait();
    }
    
    private void notifikasiBerhasil(String isiNotif){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(isiNotif);
        alert.showAndWait();
    }
}
