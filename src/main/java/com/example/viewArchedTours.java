
package com.example;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
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

public class viewArchedTours extends notStuController implements Initializable{

    
    @FXML
    private ListView<String> toursshow;

    @FXML
    private Button goBackSAT;

    @FXML
    private TextField searchField;

    @FXML
    private MenuItem show;

    private ObservableList<String> items;

    @FXML
    private Label showenLabel;



    


    @FXML
    void exitProgram(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("sbFiles/nstu.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void showTours(MouseEvent event) {

        String s = toursshow.getSelectionModel().getSelectedItem();

        showenLabel.setStyle("-fx-font-weight: bold;");showenLabel.setStyle("-fx-font-size: 20px;");
        showenLabel.setText(s.toString());
        //System.out.println(s.toString());

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
        // Student s = new Student(202039600, "Ali", "Abdulrahim");
        // Team t = new Team("Ahmed", 10,s );
        // teams.add(t.getName());
        //for (int i = 0; i < a.length; i++) {teams.add(a[i]);}


///////////////////////////////////////////////////////////////////////////////////////////////////// search bar
        
        items = FXCollections.observableArrayList();for (int i = 0; i < a.length; i++) {items.add(a[i]);}

        FilteredList<String> filteredList = new FilteredList<>(items, p -> true);
        toursshow.setItems(filteredList);
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(item -> {
                if (newValue == null || newValue.isEmpty()) 
                    return true;
                String lowerCaseFilter = newValue.toLowerCase();
                if (item.toLowerCase().contains(lowerCaseFilter)) 
                    return true;
                return false;
            });
        });

/////////////////////////////////////////////////////////////////////////////////////////////////////

    // hi//
    }

}