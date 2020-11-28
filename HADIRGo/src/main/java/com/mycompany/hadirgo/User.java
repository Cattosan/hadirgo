package com.mycompany.hadirgo;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class User {
    @FXML
    private Label labeluser;
    @FXML
    private void backToWelcome(ActionEvent event) throws IOException {
        App.setRoot("Home");
    }
    @FXML
    private void moveToDetailKelas() throws IOException{
        App.setRoot("DetailKelas");
    }
    
    @FXML
    private void kesinilo() throws IOException{
        App.setRoot("UbahPassword");
    }
    @FXML
    public void initialize(){
        labeluser.setText(Home.getUsername());
    }    
}
