package com.example;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import com.example.programJFiles.Match;
import com.example.programJFiles.Student;
import com.example.programJFiles.Team;
import com.example.programJFiles.Tournament;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class tournamentCtrlr extends showTournamentCtrlr implements Initializable{
    
    ArrayList<Button> buttons;

    @FXML
    private Label tourNameLabel;


    @FXML
    private Button addTeamBtn;

    @FXML
    private Button backBtn;

    @FXML
    private ScrollPane sp;

    @FXML
    private Button startTourBtn;


    @FXML
    void startTourBtnAction(ActionEvent event) {

    }

    @FXML
    void addTeamBtnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("sbFiles/addteamscene.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void backBtnAction(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("sbFiles/showTournamentScene.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {



        if (sp == null || tourNameLabel == null || startTourBtn == null){
            return;
        }


        if(selectedTournament.getTournamentStarted()){
            startTourBtn.setVisible(false);

        }



        tourNameLabel.setText(App.selectedTournament.getName());

        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setHgrow(Priority.NEVER);
        columnConstraints.setPercentWidth(100.00);
        
        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setVgrow(Priority.NEVER);
        
        columnConstraints.setPercentWidth(100.0);
        
        
        GridPane gridPane = new GridPane();
        gridPane.getRowConstraints().add(rowConstraints);
        gridPane.getColumnConstraints().add(columnConstraints);
        gridPane.setGridLinesVisible(true);
        gridPane.setMaxWidth(75);


        // for (int i = 0; i < 20; i++) { 
        //     RowConstraints row = new RowConstraints(20); 
        //     gridPane.getRowConstraints().add(row);
        // } 
        // for (int i = 0; i < 20; i++) { 
        //     ColumnConstraints col = new ColumnConstraints(15); 
        //     gridPane.getColumnConstraints().add(col); 
        // }


        if(selectedTournament.getType() == Tournament.Type.BRACKETS){

            handelBracket(gridPane);
        }
        else{
            handelRoundRobin(gridPane);
        }


        sp.setContent(gridPane);
        
}


private void handelRoundRobin(GridPane gridPane) {


    // testTournamentRR();

    if(!App.selectedTournament.getTournamentStarted()){
        return;
    }
    


    int x=0;int y =0;

    for (ArrayList<Match> round : App.selectedTournament.getAllRounds()) {
        for (Match match : round) {
            Button b = new  Button(match.toString());
                b.setOnMouseClicked(event -> {
                    try {
                        handelMatchBtn(match,event);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                gridPane.add(b,x,y);
                y++;

            
        }
        y = 0 ; x++;
    }
    
    }

void handelMatchBtn(Match match, Event event) throws IOException{

    

    System.out.println(match.toString());




        App.selectedMatch = match;

        Parent root = FXMLLoader.load(getClass().getResource("sbFiles/showMatch.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

}


void testTournamentRR(){


    Student s1 = new Student(123 ,"Ghassan");
		Student s2 = new Student(124 ,"Mohammad");
		Student s3 = new Student(125 ,"Salem");
		Student s4 = new Student(126 ,"Ahmad");
		Student s5 = new Student(127 ,"Ali");
		Student s6 = new Student(128 ,"Ali");
		
		Team t1 = new Team("Tigers", 1, s1);
		Team t2 = new Team("Fighters", 1, s2);
		Team t3 = new Team("Bees", 1, s3);
		Team t4 = new Team("Cats", 1, s4);
		Team t5 = new Team("Dogs", 1, s5);
		Team t6 = new Team("Bats", 1, s6);
		
		selectedTournament.addTeam(t1);
		selectedTournament.addTeam(t2);
		selectedTournament.addTeam(t3);
		selectedTournament.addTeam(t4);
		selectedTournament.addTeam(t5);
		selectedTournament.addTeam(t6);
		
		selectedTournament.setDates(new Date(2023, 5, 13), new Date(2023, 5, 30));
		
		selectedTournament.startTournament();
		
		System.out.println("Teams in tourny: " + selectedTournament.getTeams());
		System.out.println();
		System.out.println("All rounds matches: " + selectedTournament.getAllRounds());
		System.out.println();
		System.out.println("First match: " + selectedTournament.getFirstUnplayedMatch());
		
		System.out.println();
		Match m = selectedTournament.getFirstUnplayedMatch();
		m.addGoals2(4);
		m.matchDone();
		System.out.println();
		System.out.println("Teams standings:");
		System.out.println(selectedTournament.teamsWithPoints());
		
		handleNextMatch(selectedTournament, 3, 3);
		handleNextMatch(selectedTournament, 4, 2);
		handleNextMatch(selectedTournament, 1, 1);
		handleNextMatch(selectedTournament, 3, 2);
		handleNextMatch(selectedTournament, 2, 4);
		handleNextMatch(selectedTournament, 4, 2);
		handleNextMatch(selectedTournament, 2, 4);
		handleNextMatch(selectedTournament, 6, 2);
		handleNextMatch(selectedTournament, 2, 2);
		handleNextMatch(selectedTournament, 4, 2);
		handleNextMatch(selectedTournament, 2, 2);
		handleNextMatch(selectedTournament, 4, 2);
		handleNextMatch(selectedTournament, 2, 2);
		handleNextMatch(selectedTournament, 4, 2);
		handleNextMatch(selectedTournament, 4, 2);
		
		System.out.println(selectedTournament.GetWinner());
}

public static void handleNextMatch(Tournament t, int p1, int p2) {
    System.out.println("Next match: " + t.getFirstUnplayedMatch());
    
    System.out.println();
    Match m = t.getFirstUnplayedMatch();
    if(m == null) {
        System.out.println("Tournament Done");
        return;
    }
    m.addGoals1(p1);
    m.addGoals2(p2);
    m.matchDone();
    System.out.println();
    System.out.println("Teams standings:");
    System.out.println(t.teamsWithPoints());
}

void getTestTournament(){

        Student s1 = new Student(123 ,"Ghassan");
		Student s2 = new Student(124 ,"Mohammad");
		Student s3 = new Student(125 ,"Salem");
		Student s4 = new Student(126 ,"Ahmad");
		Student s5 = new Student(127 ,"Ali");
		
		Team t1 = new Team("Tigers", 1, s1);
		Team t2 = new Team("Fighters", 1, s2);
		Team t3 = new Team("Bees", 1, s3);
		Team t4 = new Team("Cats", 1, s4);
		Team t5 = new Team("Dogs", 1, s5);
		
		selectedTournament.addTeam(t1);
		selectedTournament.addTeam(t2);
		selectedTournament.addTeam(t3);
		selectedTournament.addTeam(t4);
		selectedTournament.addTeam(t5);
		
		selectedTournament.setDates(new Date(2023, 5, 13), new Date(2023, 5, 30));
		
		selectedTournament.startTournament();
		
		System.out.println("Teams in tourny: " + selectedTournament.getTeams());
		System.out.println();
		System.out.println("All rounds matches: " + selectedTournament.getAllRounds());
		System.out.println();
		System.out.println("First match: " + selectedTournament.getFirstUnplayedMatch());
		System.out.println();
		
		Match m = selectedTournament.getFirstUnplayedMatch();
		m.addGoals1(4);
		m.matchDone();
		
		selectedTournament.checkRoundEndBrackets();
		
		System.out.println("All rounds matches after match 1: " + selectedTournament.getAllRounds());
		
		m = selectedTournament.getFirstUnplayedMatch();
		m.addGoals2(4);
		m.matchDone();
		
		selectedTournament.checkRoundEndBrackets();
		
		System.out.println("All rounds matches after match 2: " + selectedTournament.getAllRounds());
		
		m = selectedTournament.getFirstUnplayedMatch();
		m.addGoals2(4);
		m.matchDone();
		
		selectedTournament.checkRoundEndBrackets();
		System.out.println(selectedTournament.checkTournamentDone());
		System.out.println("All rounds matches after match 3: " + selectedTournament.getAllRounds());
		m = selectedTournament.getFirstUnplayedMatch();
		m.addGoals2(4);
		m.matchDone();
		selectedTournament.checkRoundEndBrackets();
		
		System.out.println(selectedTournament.checkTournamentDone());

                
}

void handelBracket(GridPane gridPane){


    // getTestTournament();


    if(App.selectedTournament.getTournamentStarted()){

        int blankOnStarts=0; int blakOnbetween = 1;


        ArrayList<ArrayList<Match>> rounds = App.selectedTournament.getAllRounds();

        int x=0;int y =0;



        for (ArrayList<Match> round : rounds) {
            y=0;
            for (int i = 0; i < blankOnStarts; i++) {
                gridPane.add(new Label(""),x,y);
                y++;

            } 

            blankOnStarts = blankOnStarts*2+1;


            for (Match match : round) {
                Button b = new  Button(match.toString());
                b.setOnMouseClicked(event -> {
                    try {
                        handelMatchBtn(match,event);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                gridPane.add(b,x,y);
                y++;
                for (int i = 0; i < blakOnbetween; i++) {
                    gridPane.add(new Label(""),x,y);
                    y++;

                } 

            }
            x++;
            blakOnbetween = blakOnbetween*2+1;


        }

        
    }

}

}
