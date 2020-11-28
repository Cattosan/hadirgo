package com.mycompany.hadirgo;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Home {
    
    private static String usr;
    private String pwd;
    private boolean status = false;
    
    
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
//    @FXML
//    //private void switchToLogin(ActionEvent event) throws IOException {
//    private void switchToUser(ActionEvent event) throws IOException {
//        //JButton login = new JButton;
//        //App.setRoot("FormLogin");
//        App.setRoot("user");
//    }
    
    @FXML
    private void cekLogin(ActionEvent event) throws IOException{
        usr = username.getText();
        pwd = password.getText();
        status = HadirGoDb.validate(usr, pwd);
        
        if(status == true){
            App.setRoot("user");
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("User tidak ketemu!");
            alert.showAndWait();
        }
    }

   

    /*
    @FXML
    private void handleButtonAction(MouseEvent event){
        if(event.getTarget() == login){
            register.setVisible(true);
        }
    }
    */
    
}
