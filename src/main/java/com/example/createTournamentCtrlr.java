package com.example;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import com.example.programJFiles.Game;
import com.example.programJFiles.MyMethods;
import com.example.programJFiles.Tournament;
import com.example.programJFiles.Tournament.Type;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class createTournamentCtrlr extends notStuController{

    @FXML
    private Button BackCT,addTeamsbtn,createBtn;

    @FXML
    private TextField maxTeams,tourName,tourGame,teamCapacity;

    @FXML
    private RadioButton tourTypeE,tourTypeRR;

    @FXML
    private Label clickheretoaddteams;

    @FXML
    private DatePicker endDate;

    @FXML
    private DatePicker startDate;



    


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
    void createBtnAction(ActionEvent event) throws IOException  {

        Tournament.Type t = Tournament.Type.BRACKETS;

        Game game = new Game(tourGame.getText(), Integer.parseInt(teamCapacity.getText()));

        
        if (tourTypeRR.isSelected()){
            t= Tournament.Type.ROUNDROBBIN; 
        }
        tour = new Tournament(tourName.getText(), game,t, Integer.parseInt(maxTeams.getText()));
        

        

        int year1 = startDate.getValue().getYear();
        int month1 = startDate.getValue().getMonthValue();
        int day1 = startDate.getValue().getDayOfMonth();
        Date date1 = new Date(year1, month1, day1);
           

        int year2 = endDate.getValue().getYear();
        int month2 = endDate.getValue().getMonthValue();
        int day2 = endDate.getValue().getDayOfMonth();
        Date date2 = new Date(year2, month2, day2);


        tour.setDates(date1, date2);


        for (Tournament to : tournamentsList) {
            if(to.getName().equals(tour.getName())){
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Tournament duplicate");alert.setHeaderText(null);
                alert.setContentText("Tournament name already exist.");
                alert.showAndWait();
                return;
            }
            
        }

        App.tournamentsList.add(tour);


        MyMethods.printTournamentsToTxtFile(filePath, App.tournamentsList);


        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Tournament created");alert.setHeaderText(null);
        alert.setContentText("Tournament created successfully.");
        alert.showAndWait();
    }



    

    

}
