package com.example;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.programJFiles.Tournament;

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
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class showTournamentCtrlr extends notStuController implements Initializable{

    @FXML
    private Button goBackSAT;

    @FXML
    private TextField searchField;

    @FXML
    private MenuItem show;

    @FXML
    private Label showenLabel;

    @FXML
    private ListView<String> toursshow;

    @FXML
    void exitProgram(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("sbFiles/nstu.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void showTours(MouseEvent event) throws IOException {
        String s = toursshow.getSelectionModel().getSelectedItem();

        Tournament t = null;

        for (int i = 0; i < tournamentsList.size(); i++) {
            if(tournamentsList.get(i).getName().equals(s)){
                t = tournamentsList.get(i);
            }
            
        }

        App.selectedTournament = t;
        

        Parent root = FXMLLoader.load(getClass().getResource("sbFiles/tournament.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        


    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        

        for (int i = 0; i < tournamentsList.size(); i++) {
            if(!tournamentsList.get(i).tournamentDone()){
                toursshow.getItems().add(tournamentsList.get(i).getName());
            }
            
        }


        
    }



}
