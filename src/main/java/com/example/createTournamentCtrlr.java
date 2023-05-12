package com.example;

import java.io.IOException;

import com.example.programJFiles.Game;
import com.example.programJFiles.Tournament;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class createTournamentCtrlr extends notStuController{

    @FXML
    private Button BackCT;

    @FXML
    private Button addTeamsbtn;

    @FXML
    private Button createBtn;

    @FXML
    private TextField maxTeams;

    @FXML
    private TextField tourGame;

    @FXML
    private TextField tourName;

    @FXML
    private TextField teamCapacity;

    @FXML
    private RadioButton tourTypeE;

    @FXML
    private RadioButton tourTypeRR;

    @FXML
    private Label clickheretoaddteams;


    @FXML
    void BackCTAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("sbFiles/nstu.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void addTeamsbtnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("sbFiles/addteamscene.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();



    }

    @FXML
    void createBtnAction(ActionEvent event) {

        game = new Game(tourGame.getText(), Integer.parseInt(teamCapacity.getText()));

        
        if (tourTypeE.isSelected())
            tour = new Tournament(tourName.getText(), game,Tournament.Type.BRACKETS, Integer.parseInt(maxTeams.getText()));  
        else if (tourTypeRR.isSelected())
            tour = new Tournament(tourName.getText(), game,Tournament.Type.ROUNDROBBIN, Integer.parseInt(maxTeams.getText()));

            
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Tournament created");alert.setHeaderText(null);
            alert.setContentText("Tournament created successfully.");
            alert.showAndWait();

            addTeamsbtn.setVisible(true);clickheretoaddteams.setVisible(true);

        
            
        

    }

}
