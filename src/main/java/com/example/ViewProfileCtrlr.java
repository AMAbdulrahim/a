package com.example;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ViewProfileCtrlr extends stuController{

    @FXML
    private Button VPBack;

    @FXML
    void VPBackAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("sbFiles/stu.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    
}
