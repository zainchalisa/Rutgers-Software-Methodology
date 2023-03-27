package project4.project4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;


public class CafeStoreMainController {
    @FXML
    private Label welcomeText;

    @FXML
    private Button orderDonuts, orderCoffee, yourOrder;


    @FXML
    protected void coffeeOrderButton(ActionEvent coffeeOrderView) {
        Stage coffeeView = new Stage();
        AnchorPane root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("OrderingCoffee-view.fxml"));
            root = (AnchorPane) loader.load();
            Scene scene = new Scene(root, 600, 400);
            coffeeView.setScene(scene);
            coffeeView.show();
            OrderingCoffeeController coffeeControl = loader.getController();
            /*
              The statement below is to pass the reference of the MainController object
              to the View1Controller object so the View1Controller can call the
              public methods in the MainController.
             */
            coffeeControl.setMainController(this);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Loading View1.fxml.");
            alert.setContentText("Couldn't load View1.fxml.");
            alert.showAndWait();
        }
    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
