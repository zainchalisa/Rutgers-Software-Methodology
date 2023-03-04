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
        private DatePicker dob;

        @FXML
        private RadioButton CS, BAIT, ITI, EE, MATH;
        @FXML
        private RadioButton resident, nonResident, triState, international;

        @FXML
        private CheckBox newYorkState, connecticutState, studyAbroad;

        @FXML
        private TextArea resultField;

        Roster roster = new Roster();

        public Major getMajorButton(){
                Major major = null;
                if(BAIT.isSelected()){
                        major = Major.BAIT;
                        return major;
                } else if (CS.isSelected()){
                        major = Major.CS;
                        return major;
                } else if (EE.isSelected()){
                        major = Major.EE;
                        return major;
                } else if (ITI.isSelected()){
                        major = Major.ITI;
                        return major;
                } else if (MATH.isSelected()){
                        major = Major.MATH;
                        return major;
                } else{
                        resultField.appendText("Please select a major.");
                        return major;
                }

        }

        private void validResident(Roster roster, String firstName,
                                   String lastName, String dateOfBirth,
                                   Major majorName,
                                   String creditsCompletedString,
                                   int defaultScholarship) {
                if (isValidCreditString(creditsCompletedString)) {
                        int creditsCompleted =
                                Integer.parseInt(creditsCompletedString);
                        if (creditsCompleted >= 0) {
                                Student student = new Resident(new Profile(lastName,
                                        firstName, new Date(dateOfBirth)), majorName,
                                        creditsCompleted, defaultScholarship);
                                roster.add(student);
                                resultField.appendText("" + student.getProfile() + " (" + majorName.getCoreCode() + " " +
                                        majorName + " " + majorName.getSchool() + ") " +
                                        "credits completed: " + creditsCompletedString + " (" +
                                        student.getStanding() + ")" + "(resident)");

                        } else {
                                resultField.appendText("Credits completed " + "invalid: " +
                                        "cannot be negative!");
                        }
                } else {
                        resultField.appendText("Credits completed " + "invalid: not an " +
                                "integer!");
                }
        }

        private boolean isValidCreditString(String creditsCompletedString) {
                try {
                        Integer.parseInt(creditsCompletedString);
                        return true;
                } catch (NumberFormatException e) {
                        return false;
                }
        }


        @FXML
        void addStudent(ActionEvent add){

                try {
                        String studentFirstName = String.valueOf(firstName.getText());
                        String studentLastName = String.valueOf(lastName.getText());
                        String dateOfBirth = String.valueOf(dob.getValue());
                        String creditsCompletedString = String.valueOf(creditsCompleted.getText());
                        int defaultScholarship = 0;

                        Resident studentProfile = new Resident(new Profile(studentLastName,
                                studentFirstName, new Date(dateOfBirth)));
                        Date dobObject = new Date(dateOfBirth);

                        if (dobObject.isValid()) {
                                if (dobObject.isValidStudent()) {
                                        if (!roster.contains(studentProfile)) {
                                                Major majorName = getMajorButton();
                                                if (majorName != null) {
                                                        validResident(roster, studentFirstName, studentLastName,
                                                                dateOfBirth, majorName,
                                                                creditsCompletedString,
                                                                defaultScholarship);
                                                }
                                        } else {
                                                resultField.appendText(studentFirstName + " " + studentLastName + " " +
                                                        dateOfBirth + " is already in the roster.");
                                        }
                                } else {
                                        resultField.appendText("DOB invalid: " + dateOfBirth + " younger " +
                                                "than 16 years old.");
                                }
                        } else {
                                resultField.appendText("DOB invalid: " + dateOfBirth + " not a valid " +
                                        "calendar date!");
                        }
                        //resultField.appendText(studentFirstName + " " + studentLastName + " " + creditsCompletedString + " " + dateOfBirth + " " + studentsMajor);
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

