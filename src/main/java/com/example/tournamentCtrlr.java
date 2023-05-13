package com.example;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

public class tournamentCtrlr extends showTournamentCtrlr{
    

    @FXML
    private Button addTeamBtn;

    @FXML
    private Button backBtn;

    @FXML
    private ScrollPane sp;

    @FXML
    void addTeamBtnAction(ActionEvent event) {

    }

    @FXML
    void backBtnAction(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("sbFiles/showTournamentScene.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}
