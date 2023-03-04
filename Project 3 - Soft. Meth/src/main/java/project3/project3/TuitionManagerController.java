package project3.project3;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;

public class TuitionManagerController extends Application {

        @FXML
        private Button addStudentButton;

        @FXML
        private Button removeStudentButton;

        @FXML
        private Button changeMajorButton;

        @FXML
        private Button loadFromFileButton;

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


        @FXML
        void addStudent(ActionEvent add){

                try {
                        String studentFirstName = String.valueOf(firstName.getText());
                        String studentLastName = String.valueOf(lastName.getText());

                        resultField.appendText(studentFirstName + studentLastName);
                }
                //Show the error message with a pop-up window.
                catch (NumberFormatException e) {
                        resultField.appendText("no");
                }

        }


        @Override
        public void start(Stage stage) throws Exception {
                stage.show();
        }
}

