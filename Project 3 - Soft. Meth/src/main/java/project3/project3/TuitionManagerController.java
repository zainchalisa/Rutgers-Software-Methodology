package project3.project3;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

        // Scholarship
        @FXML
        private TextField scholarFirstName;

        @FXML
        private TextField scholarLastName;

        @FXML
        private DatePicker scholarDob;

        @FXML
        private TextField scholarAmount;

        @FXML
        private Button updateScholarButton;




        Roster roster = new Roster();
        Enrollment enrollment = new Enrollment();

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
                        resultField.appendText("Please select a major." + "\n");
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
                                        student.getStanding() + ")" + "(resident)" + "\n");

                        } else {
                                resultField.appendText("Credits completed " + "invalid: " +
                                        "cannot be negative!" + "\n");
                        }
                } else {
                        resultField.appendText("Credits completed " + "invalid: not an " +
                                "integer!" + "\n");
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
                        String dateOfBirth = dob.getValue().format(
                                DateTimeFormatter.ofPattern("MM/dd/yyyy"));
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
                                                        dateOfBirth + " is already in the roster." + "\n");
                                        }
                                } else {
                                        resultField.appendText("DOB invalid: " + dateOfBirth + " younger " +
                                                "than 16 years old." + "\n");
                                }
                        } else {
                                resultField.appendText("DOB invalid: " + dateOfBirth + " not a valid " +
                                        "calendar date!" + "\n");
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
        void enrollStudent(ActionEvent enroll) {
                String firstName = String.valueOf(enrollFirstName.getText());
                String lastName = String.valueOf(enrollLastName.getText());
                String dateOfBirth = enrollDob.getValue().format(
                        DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                String creditsCompletedString = String.valueOf(enrollCreditsCompleted.getText());

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
                                resultField.appendText("Cannot enroll: " + firstName + " " +
                                        lastName + " " + dateOfBirth + " " +
                                        "is not in the roster." + "\n");
                        }
                } else {
                        resultField.appendText("Credits enrolled is not an integer." + "\n");
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
                        resultField.appendText(firstName + " " + lastName + " " +
                                dateOfBirth + " enrolled " + creditsEnrolled +
                                " credits" + "\n");
                } else if (student.getClass().getSimpleName().equals((
                        "International")) && ((International) student).
                        isStudyAbroad()) {
                        resultField.appendText("(" + student.getClass().getSimpleName() +
                                " studentstudy abroad) " + creditsEnrolled +
                                ": invalid credit hours." + "\n");
                } else if (student.getClass().getSimpleName().equals((
                        "International"))) {
                        resultField.appendText("(" + student.getClass().getSimpleName() +
                                "student) " + creditsEnrolled +
                                ": invalid credit hours." + "\n");
                } else {
                        resultField.appendText("(" + student.getClass().getSimpleName() +
                                ") " + creditsEnrolled + ": invalid credit hours." + "\n");
                }
        }

        @FXML
        void dropStudent(ActionEvent drop) {
                String firstName = String.valueOf(enrollFirstName.getText());
                String lastName = String.valueOf(enrollLastName.getText());
                String dateOfBirth = enrollDob.getValue().format(
                        DateTimeFormatter.ofPattern("MM/dd/yyyy"));

                EnrollStudent student = new EnrollStudent(new Profile(lastName,
                        firstName, new Date(dateOfBirth)));
                if (enrollment.contains(student)) {
                        int studentIndex = enrollment.find(student);
                        student = enrollment.getEnrollStudents()[studentIndex];
                        enrollment.remove(student);
                        resultField.appendText(firstName + " " + lastName + " " +
                                dateOfBirth + " dropped." + "\n");
                } else {
                        resultField.appendText(firstName + " " + lastName + " " +
                                dateOfBirth + " is not enrolled." + "\n");
                }
        }

        @FXML
        void grantScholarship(ActionEvent updateScholarship) {
                String firstName = String.valueOf(scholarFirstName.getText());
                String lastName = String.valueOf(scholarLastName.getText());
                String dateOfBirth = scholarDob.getValue().format(
                        DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                String scholarshipString = String.valueOf(scholarAmount.getText());
                Student student = new Resident(new Profile(lastName, firstName,
                        new Date(dateOfBirth)));

                if (roster.contains(student)) {
                        int studentIndex = roster.find(student);
                        student = roster.getRoster()[studentIndex];
                        if (student instanceof Resident) {
                                residentScholarship(student, firstName, lastName,
                                        dateOfBirth, scholarshipString, enrollment);
                        } else {
                                if (student instanceof NonResident) {
                                        if (student.getClass().getSimpleName().equals(
                                                "NonResident")) {
                                                resultField.appendText(firstName + " " + lastName +
                                                        " " + dateOfBirth + " (" + "Non-Resident"
                                                        + ")" + " is not eligible for the " +
                                                        "scholarship." + "\n");
                                        }
                                } else {
                                        resultField.appendText(firstName + " " + lastName + " " +
                                                dateOfBirth + " (" + student.getClass().
                                                getSimpleName() +
                                                ") is not eligible for the scholarship." + "\n");
                                }
                        }
                } else {
                        resultField.appendText(firstName + " " + lastName + " "
                                + dateOfBirth + " is not in the roster." + "\n");
                }
        }

        private void residentScholarship(Student student, String firstName,
                                         String lastName, String dateOfBirth
                , String scholarshipString, Enrollment enrollment) {
                EnrollStudent checkStudent =
                        new EnrollStudent(new Profile(lastName, firstName,
                                new Date(dateOfBirth)));
                int index = 0;
                if (enrollment.contains(checkStudent)) {
                        index = enrollment.find(checkStudent);
                } else {
                        resultField.appendText(firstName + " " + lastName + " "
                                + dateOfBirth + " is not enrolled." + "\n");
                        return;
                }
                checkStudent = enrollment.getEnrollStudents()[index]; // error

                if (!checkStudent.isPartTime()) {
                        /*
                        try {
                                scholarshipString = inputLine[4];
                        } catch (ArrayIndexOutOfBoundsException e) {
                                System.out.println("Missing data in command line" + ".");
                                return;
                        }

                         */
                        if (isValidCreditString(scholarshipString)) {
                                int scholarship = Integer.parseInt(scholarshipString);
                                if (((Resident) student).isValidScholarship(scholarship)) {
                                        ((Resident) student).setScholarship(scholarship);
                                        resultField.appendText(firstName + " " + lastName + " " +
                                                dateOfBirth + ": scholarship amount " +
                                                "updated." + "\n");
                                } else {
                                        resultField.appendText(scholarship + ": invalid " +
                                                "amount." + "\n");
                                }
                        } else {
                                resultField.appendText("Amount is not an integer." + "\n");
                        }
                } else {
                        resultField.appendText(firstName + " " + lastName + " " +
                                dateOfBirth + " part time student is not " +
                                "eligible for the scholarship." + "\n");
                }
        }


        @Override
        public void start(Stage stage) throws Exception {
                stage.show();
        }
}

