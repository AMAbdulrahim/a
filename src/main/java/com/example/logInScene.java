package com.example;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import com.example.programJFiles.HttpGetRequestSender;
import com.example.programJFiles.MyMethods;
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
    void loginbtnAct(ActionEvent event) throws IOException, URISyntaxException, ClassNotFoundException {

        if(MyMethods.fileExists(App.filePath)){
            App.tournamentsList= MyMethods.getTournamentsFromTxtFile(filePath);
        }
        else{
            App.tournamentsList = new ArrayList<>();
        }
        




        String un = username.getText();
        String p = password.getText();

        App.user =  HttpGetRequestSender.logInHandler(un, p);



        if (user ==null){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Username or password is incorrect");
            alert.setContentText("Please try again.");
            alert.showAndWait();

        }


        else if(user.type.equals("student")){
            System.out.println("openinf scene");

            Parent root = FXMLLoader.load(getClass().getResource("sbFiles/stu.fxml"));
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();



        }

        else if(user.type.equals("admin")){
            System.out.println("openinf scene");
            Parent root = FXMLLoader.load(getClass().getResource("sbFiles/nstu.fxml"));
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();



        }


  
            
        }


    }

    


    

