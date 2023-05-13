package com.example;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class stuController extends App implements Initializable{

    @FXML
    private Button JoinTournament,ViewMatches,ViewProfile;

    @FXML
    private ListView<String> todaysmatches;

    @FXML
    private Label helloLabel;


    

    @FXML
    void JoinTournamentAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("sbFiles/joinTournamentScene.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void ViewMatchesAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("sbFiles/ViewMatchesfxml.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void ViewProfileAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("sbFiles/ViewProfilefxml.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        if(helloLabel == null)
            return;

        String[] s = user.name.split(" "); 
        String r = s[1].substring(0, 1)+s[1].substring(1).toLowerCase();
        helloLabel.setText("Hello, "+r);
    }

    
}
