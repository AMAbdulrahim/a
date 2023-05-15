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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class showMatchCtrlr extends tournamentCtrlr implements Initializable{

    @FXML
    private CheckBox MatchDoneCB;

    @FXML
    private Button addGoalT1Btn;

    @FXML
    private Button addGoalT2Btn;

    @FXML
    private Button backBtn;

    @FXML
    private Label matchNameLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private Button subGoalT1Btn;

    @FXML
    private Button subGoalT2Btn;

    @FXML
    private Label team1GoalsLabel;


    @FXML
    private Label team1Label;

    @FXML
    private Label team2GoalsLabel;

    @FXML
    private Label team2Label;

    @FXML
    void MatchDoneCBAction(ActionEvent event) {
        selectedMatch.matchDone();

    }

    @FXML
    void addGoalT1BtnAction(ActionEvent event) {

        selectedMatch.addGoals1(1);
        updateLabel();
    }

    @FXML
    void addGoalT2BtnAction(ActionEvent event) {
        selectedMatch.addGoals2(1);
        updateLabel();
    }

    @FXML
    void backBtnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("sbFiles/tournament.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void subGoalT1BtnAction(ActionEvent event) {

        if(selectedMatch.getTeam1Goals()==0)
            return;
        selectedMatch.addGoals1(-1);
        updateLabel();

    }

    @FXML
    void subGoalT2BtnAction(ActionEvent event) {

        if(selectedMatch.getTeam2Goals()==0)
            return;
        selectedMatch.addGoals2(-1);
        updateLabel();

    }

    void updateLabel(){
        team1GoalsLabel.setText(""+selectedMatch.getTeam1Goals());
        team2GoalsLabel.setText(""+selectedMatch.getTeam2Goals());

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {


        if(matchNameLabel == null || team1GoalsLabel == null || team2GoalsLabel == null || team1Label == null || team2Label == null || dateLabel == null || backBtn == null)
            return;

        
        matchNameLabel.setText(selectedMatch.toString());

        team1Label.setText(selectedMatch.getTeam1().getName());
        team2Label.setText(selectedMatch.getTeam2().getName());

        team1GoalsLabel.setText(""+selectedMatch.getTeam1Goals());
        team2GoalsLabel.setText(""+selectedMatch.getTeam2Goals());


        dateLabel.setText(selectedMatch.getDateString());

        MatchDoneCB.setSelected(selectedMatch.getMatchDone());

    }

}

