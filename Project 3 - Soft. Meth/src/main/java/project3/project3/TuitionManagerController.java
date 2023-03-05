package project3.project3;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
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

        // Enroll/Drop tab
        @FXML
        private Button enrollStudentButton;

        @FXML
        private Button dropStudentButton;

        @FXML
        private TextField enrollFirstName;

        @FXML
        private TextField enrollLastName;

        @FXML
        private DatePicker enrollDob;

        @FXML
        private TextField enrollCreditsCompleted;



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
                        // lalalallala
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
        // this method is called from the GUI, when the enrollStudentButton is clicked
        @FXML
        private void enrollStudent(ActionEvent enroll) {

                /*
                EventHandler handler = new EventHandler() {
                        @Override
                        public void handle(Event event) {

                        }
                }

                 */
                String firstName = inputLine[1];
                String lastName = inputLine[2];
                String dateOfBirth = inputLine[3];
                String creditsCompletedString = inputLine[4];

                if (isValidCreditString(creditsCompletedString)) {
                        int creditsEnrolled = Integer.parseInt(creditsCompletedString);
                        Student student = new Resident(new Profile(lastName,
                                firstName, new Date(dateOfBirth)));
                        if (roster.contains(student)) {
                                int studentIndex = roster.find(student);
                                student = roster.getRoster()[studentIndex];
                                validEnrollStudent(student, creditsEnrolled, firstName,
                                        lastName, dateOfBirth, enrollment);
                        } else {
                                System.out.println("Cannot enroll: " + firstName + " " +
                                        lastName + " " + dateOfBirth + " " +
                                        "is not in the roster.");
                        }
                } else {
                        System.out.println("Credits enrolled is not an integer.");
                }
        }

        // Stays private bc helper method?
        private void validEnrollStudent(Student student, int creditsEnrolled
                , String firstName, String lastName, String dateOfBirth,
                                        Enrollment enrollment) {
                if (student.isValid(creditsEnrolled)) {
                        EnrollStudent newStudent =
                                new EnrollStudent(new Profile(lastName, firstName,
                                        new Date(dateOfBirth)), creditsEnrolled);
                        if (enrollment.contains(newStudent)) {
                                enrollment.getEnrollStudent(newStudent).setCreditsEnrolled
                                        (creditsEnrolled);
                        } else {
                                enrollment.add(newStudent);
                        }
                        System.out.println(firstName + " " + lastName + " " +
                                dateOfBirth + " enrolled " + creditsEnrolled +
                                " credits");
                } else if (student.getClass().getSimpleName().equals((
                        "International")) && ((International) student).
                        isStudyAbroad()) {
                        System.out.println("(" + student.getClass().getSimpleName() +
                                " studentstudy abroad) " + creditsEnrolled +
                                ": invalid credit hours.");
                } else if (student.getClass().getSimpleName().equals((
                        "International"))) {
                        System.out.println("(" + student.getClass().getSimpleName() +
                                "student) " + creditsEnrolled +
                                ": invalid credit hours.");
                } else {
                        System.out.println("(" + student.getClass().getSimpleName() +
                                ") " + creditsEnrolled + ": invalid credit hours.");
                }
        }


        @Override
        public void start(Stage stage) throws Exception {
                stage.show();
        }
}

