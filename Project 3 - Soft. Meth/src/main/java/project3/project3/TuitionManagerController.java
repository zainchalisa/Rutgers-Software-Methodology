package project3.project3;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class TuitionManagerController extends Application {

        @FXML
        private Label welcomeText;

        @FXML
        protected void onHelloButtonClick() {
                welcomeText.setText("Welcome to JavaFX Application!");
        }

        @Override
        public void start(Stage stage) throws Exception {
                stage.show();
        }
}

