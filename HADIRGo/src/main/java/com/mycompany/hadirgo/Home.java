package com.mycompany.hadirgo;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Home {
    public static String usr;
    public static String pwd;
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
        Alert alert = new Alert(Alert.AlertType.ERROR);
        usr = username.getText().trim();
        pwd = password.getText().trim();
        status = HadirGoDb.validate(usr, pwd);
        
        if(status == true){
            status = false;
//            App.setRoot("Admin");
            if(HadirGoDb.isAdmin(usr)){
                App.setRoot("Admin");
            } else{
                App.setRoot("Dosen");
            }
        }
        
        else if(usr.equals("")|| pwd.equals("")){
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Username atau Password tidak boleh kosong ヾ(≧▽≦*)o!");
            alert.showAndWait();
        }
        
        else{
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("User tidak ketemu ＞﹏＜!");
            alert.showAndWait();
        }
    }
    
    public static String getpwd(){
        return pwd;
    }

    public static String getuser(){
        return usr;
    }
    
    @FXML
    private void exitprogram(){
        System.exit(0);
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
