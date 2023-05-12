package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

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
    void loginbtnAct(ActionEvent event) throws IOException, URISyntaxException {

        String string = "http://example.com";

        URL url = (new URI(string)).toURL();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");



        HttpURLConnection req = (HttpURLConnection) url.openConnection();
        req.setRequestMethod("GET"); // specify the request type
        req.setConnectTimeout(5000);
        req.setReadTimeout(5000);



        int status = req.getResponseCode();


        BufferedReader in = new BufferedReader(
        new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            System.out.println();
        content.append(inputLine);
        }



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