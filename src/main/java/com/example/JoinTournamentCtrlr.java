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
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class JoinTournamentCtrlr extends stuController implements Initializable{

    @FXML
    private Button backJT;

    @FXML
    private Button add;

    @FXML
    private ListView<String> availabletoursLV;

    @FXML
    private Label detaillabel;

    @FXML
    void addAction(ActionEvent event) {

    }

    @FXML
    void availabletoursLVAction(MouseEvent event) {
        String s = availabletoursLV.getSelectionModel().getSelectedItem();
        detaillabel.setText(s+"teams :       winner:    points: ");
    }

    @FXML
    void backJTaction(ActionEvent event)  throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("sbFiles/stu.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        for(int i =0,h='a';i<6;i++,h++){availabletoursLV.getItems().add("Tournament"+h);}
    }

    
}
