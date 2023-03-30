package com.example.demonavigation;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    private int value = 10;

    /**
     * When the image button is clicked, a new window(stage) will be displayed.
     * The scene graph associated with the window is View1.fxml, in which the
     * fx:controller attribute defines the controller as View1Controller.
     * When the fxml file is loading, an instance of View1Controller will be created
     * To get the reference to the instance of the controller, use the getController()
     * method.
     */
    @FXML
    protected void displayView1() {
        Stage view1 = new Stage();
        BorderPane root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("View1.fxml"));
            root = (BorderPane) loader.load();
            Scene scene = new Scene(root, 500, 400);
            view1.setScene(scene);
            view1.show();
            View1Controller view1controller = loader.getController();
            /*
              The statement below is to pass the reference of the MainController object
              to the View1Controller object so the View1Controller can call the
              public methods in the MainController.
             */
            view1controller.setMainController(this);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Loading View1.fxml.");
            alert.setContentText("Couldn't load View1.fxml.");
            alert.showAndWait();
        }
    }

    /**
     * The View1Controller can use this getter method to read the private data.
     * @return
     */
    public int getValue() {
        return value;
    }
}