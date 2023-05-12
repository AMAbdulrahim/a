package com.example;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class stuController extends App {

    @FXML
    private Button JoinTournament,ViewMatches,ViewProfile;

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

    
}
