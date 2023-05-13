package com.example;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import com.example.programJFiles.Game;
import com.example.programJFiles.Student;
import com.example.programJFiles.Team;
import com.example.programJFiles.Tournament;
import com.example.programJFiles.User;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {


    static final String filePath = "tournamentFile.txt";
    Team team0;
    Student stu;
    Student addstu;
    Tournament tour;
    Game game;
    static User user;
    static ArrayList<Tournament> tournamentsList;

   static Tournament selectedTournament;
    
    protected ObservableList<String> items=FXCollections.observableArrayList();

    @FXML
    static
    ArrayList<String> teams = new ArrayList<>();

    
    


    


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
