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
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class tournamentCtrlr extends showTournamentCtrlr implements Initializable{
    

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
    void addTeamBtnAction(ActionEvent event) {

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
        gridPane.setMaxWidth(15);


        // for (int i = 0; i < 20; i++) { 
        //     RowConstraints row = new RowConstraints(20); 
        //     gridPane.getRowConstraints().add(row);
        // } 
        // for (int i = 0; i < 20; i++) { 
        //     ColumnConstraints col = new ColumnConstraints(15); 
        //     gridPane.getColumnConstraints().add(col); 
        // }

        if(App.selectedTournament.getTournamentStarted()){

            for (int i = 0; i < 20; i++) {
                // Node cell = createCell(); cell.setPrefSize(100, 100); // set preferred size gridPane.add(cell, col, row);
                gridPane.add(new Label("hello"),i,i);

            }
        
        }

        
        sp.setContent(gridPane);
        
}

}
