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
    void addStudentaction(ActionEvent event) {
        addstu = new Student(Integer.parseInt(studentIdfield.getText()), studentFNfield.getText()+" "+ studentLNfield.getText());
        
        if (team0.isFull()){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Full Team");alert.setHeaderText(null);
            alert.setContentText("The team is full.");
            alert.showAndWait();
        }
        else {
            team0.addStudent(addstu);
        }
        studentIdfield.setText("");studentFNfield.setText("");studentLNfield.setText("");

    }

    @FXML
    void createTeamOKaction(ActionEvent event) {
        stu = new Student(Integer.parseInt(stuID.getText()), stuFN.getText()+" "+ stuLN.getText());
        team0 = new Team(teamNamefield.getText(), Integer.parseInt(teamCapacityfield.getText()), stu); 
        stu.getID();
        
        stuFN.setText("");stuID.setText("");stuLN.setText("");


        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Team added");alert.setHeaderText(null);
        alert.setContentText("The team has been added successfully.");
        alert.showAndWait();

        if (Integer.parseInt(teamCapacityfield.getText())!=1){
            addstuLabel.setVisible(true);studentIdfield.setVisible(true);addStudent.setVisible(true);
            studentFNfield.setVisible(true);studentLNfield.setVisible(true);
            s1.setVisible(true);s2.setVisible(true);s3.setVisible(true);

        }
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