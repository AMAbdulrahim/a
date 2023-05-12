package com.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import com.example.programJFiles.Game;
import com.example.programJFiles.Student;
import com.example.programJFiles.Team;
import com.example.programJFiles.Tournament;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {


    Team team0;
    Student stu;
    Student addstu;
    Tournament tour;
    Game game;

    FileOutputStream fileOutT;
    ObjectOutputStream outT;

    FileInputStream fileInT;
    ObjectInputStream inT;
    
    protected ObservableList<String> items=FXCollections.observableArrayList();

    @FXML
    static
    ArrayList<String> teams = new ArrayList<>();

    
    


    String[] a = {"Team 1", "Team 2", "Team 3", "Team 4", "Team 5", "Team 6", "Team 7", "Team 8", "Team 9", "Team 10",
    "Team 11", "Team 12", "Team 13", "Team 14", "Team 15", "Team 16", "Team 17", "Team 18", "Team 19", "Team 20",
    "Team 21", "Team 22", "Team 23", "Team 24", "Team 25", "Team 26", "Team 27", "Team 28", "Team 29", "Team 30"};



    protected Stage stage;
    protected Scene scene;
    protected Parent root;
    protected Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sbFiles/s1.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    

    public static void main(String[] args) {
        launch(args);
    }
    
}
