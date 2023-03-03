package project3.project3;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;

public class TuitionManagerController extends Application {

        @FXML
        private Label welcomeText;

        @FXML
        private Button addStudentButton, removeStudentButton, changeMajorButton, loadFileButton;

        @FXML
        private TextField firstName, lastName, creditsCompleted;

        @FXML
        private DatePicker dateOfBirth;

        @FXML
        private RadioButton CS, BAIT, ITI, ECE, MATH;
        @FXML
        private RadioButton resident, nonResident, triState, international;

        @FXML
        private CheckBox newYorkState, connecticutState, studyAbroad;

        @FXML
        private TextArea resultField;


/*
        @FXML
        void addStudent(ActionEvent add){

                try {
                        String studentFirstName = String.valueOf(firstName);
                        String studentLastName = String.valueOf(lastName);

                        resultField.appendText(studentFirstName + studentLastName);
                }
                //Show the error message with a pop-up window.
                catch (NumberFormatException e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Invalid Data");
                        alert.setHeaderText("Non-numeric data has been entered.");
                        alert.setContentText("Please enter an integer.");
                        alert.showAndWait();
                }

        }
*/

        @Override
        public void start(Stage stage) throws Exception {
                stage.show();
        }
}

