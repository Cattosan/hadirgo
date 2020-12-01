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
    
    public static String usr;
    public static String pwd;
    private static boolean status = false;
    
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
        usr = username.getText().trim();
        pwd = password.getText().trim();
        status = HadirGoDb.validate(usr, pwd);
        
        if(status == true){
            status = false;
            App.setRoot("user");
//            if(HadirGoDb.isAdmin(usr)){
//                App.setRoot("admin");
//            } else{
//                App.setRoot("user");
//            }
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
