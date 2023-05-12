package com.example;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class logInScene extends App {

    @FXML
    private Button loginBtn;

    @FXML
    private PasswordField password;

    @FXML
    private TextField  username;

    
    @FXML
    void loginbtnAct(ActionEvent event) throws IOException {

        if (username.getText().equals("s") & password.getText().equals("1")){
            Parent root = FXMLLoader.load(getClass().getResource("sbFiles/stu.fxml"));
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }
        else if (username.getText().equals("nots") & password.getText().equals("1")){
            Parent root = FXMLLoader.load(getClass().getResource("sbFiles/nstu.fxml"));
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else{
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("An error has occurred");
            alert.setContentText("Please try again.");
            alert.showAndWait();
        }
    }

    


    

}