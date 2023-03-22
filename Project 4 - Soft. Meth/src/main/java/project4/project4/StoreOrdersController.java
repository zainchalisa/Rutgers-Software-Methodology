package project4.project4;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class StoreOrdersController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
