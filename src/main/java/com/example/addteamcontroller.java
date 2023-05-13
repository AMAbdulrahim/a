package com.example;

import java.io.IOException;
import com.example.programJFiles.*;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class addteamcontroller extends notStuController {

    @FXML
    private Label addstuLabel;

    @FXML
    private Label TM_left,TML;

    @FXML
    private Label s1,s2,s3;

    @FXML
    private Button addStudent;

    @FXML
    private Button createTeamOK;

    @FXML
    private Button goBackAddteam;

    @FXML
    private TextField stuFN,stuID,stuLN;

    @FXML
    private TextField studentFNfield,studentLNfield,studentIdfield;

    @FXML
    private TextField teamCapacityfield,teamNamefield;

    
    @FXML
    void addStudentaction(ActionEvent event) throws IOException {

        User u = HttpGetRequestSender.userHandler(studentIdfield.getText());
        if(u == null){
            Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("ERROR");alert.setHeaderText(null);
        alert.setContentText("User Does Not Exist");
        alert.showAndWait();
            return;
        }
        if(!u.type.equals("student")){
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("ERROR");alert.setHeaderText(null);
            alert.setContentText("User is Not student");
            alert.showAndWait();
                return;

        }

        stu = new Student(Integer.parseInt(studentIdfield.getText()), u.name);
        
        if (team0.isFull()){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Full Team");alert.setHeaderText(null);
            alert.setContentText("The team is full.");
            alert.showAndWait();
        }
        else {
            team0.addStudent(stu);
        }
        studentIdfield.setText("");

        if(team0.isFull()){
            selectedTournament.addTeam(team0);
        }

        TM_left.setText(""+(team0.getTeamCapacity()-team0.getTeamSize()));
        

    }

    @FXML
    void createTeamOKaction(ActionEvent event) throws IOException {
        User u = HttpGetRequestSender.userHandler(stuID.getText().trim());

        if(u == null){
            Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("ERROR");alert.setHeaderText(null);
        alert.setContentText("User Does Not Exist");
        alert.showAndWait();
            return;
        }
        if(!u.type.equals("student")){
            System.out.println(u.type);
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("ERROR");alert.setHeaderText(null);
            alert.setContentText("User is Not student");
            alert.showAndWait();
                return;

        }

        
        stu = new Student(Integer.parseInt(stuID.getText()), u.name);


        team0 = new Team(teamNamefield.getText(), selectedTournament.getGame().getTeamCapacity(), stu); 

        TM_left.setText(""+(team0.getTeamCapacity()-team0.getTeamSize()));

        

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Team added");alert.setHeaderText(null);
        alert.setContentText("The team has been added successfully.");
        alert.showAndWait();

        
    }


    @FXML
    void goBackAddteamAction(ActionEvent event) throws IOException {

            Parent root = FXMLLoader.load(getClass().getResource("sbFiles/nstu.fxml"));
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

    }

}