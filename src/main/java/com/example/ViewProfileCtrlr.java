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

public class ViewProfileCtrlr extends stuController implements Initializable{

    @FXML
    private Button VPBack;

    @FXML
    private ListView<?> previousTours;

    @FXML
    private Label emailLabel;

    @FXML
    private Label idLabel;

    @FXML
    private Label nameLabel;

    @FXML
    void VPBackAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("sbFiles/stu.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void previousToursAction(MouseEvent event) {

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        if (emailLabel == null || idLabel ==null || nameLabel == null){
            return;
        }

        idLabel.setText(user.id); nameLabel.setText(user.name); emailLabel.setText("Abdulmjeed.alothman222@gmail.com");





    }
    
}
