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

        @FXML
        private void disableButtons(){
                if(resident.isSelected()){
                        triState.setDisable(true);
                        international.setDisable(true);
                        newYorkState.setDisable(true);
                        connecticutState.setDisable(true);
                        studyAbroad.setDisable(true);
                }
                if(nonResident.isSelected()){
                        triState.setDisable(false);
                        international.setDisable(false);
                        newYorkState.setDisable(false);
                        connecticutState.setDisable(false);
                        studyAbroad.setDisable(false);
                }
                if(triState.isSelected()){
                        international.setDisable(true);
                        studyAbroad.setDisable(true);
                        resident.setDisable(true);
                }
        }


        Roster roster = new Roster();
        Enrollment enrollment = new Enrollment();

        private Major getMajorButton(){
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

        public String getStateButton(){
                if(newYorkState.isSelected()){
                        return "NY";
                } else if (connecticutState.isSelected()){
                        return "CT";
                } else{
                        resultField.appendText("Please select a state." + "\n");
                        return "";

                }
        }

        public boolean getStudyAbroadButton(){
                if(studyAbroad.isSelected()){
                        return true;
                } else {
                        return false;
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
        void addResident(ActionEvent add){

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

        @FXML
        private void addNonResident(ActionEvent add) {

                String studentFirstName = String.valueOf(firstName.getText());
                String studentLastName = String.valueOf(lastName.getText());
                String dateOfBirth = dob.getValue().format(
                        DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                String creditsCompletedString = String.valueOf(creditsCompleted.getText());
                NonResident studentProfile =
                        new NonResident(new Profile(studentLastName, studentFirstName,
                                new Date(dateOfBirth)));
                Date dob = studentProfile.getProfile().getDob();

                if (dob.isValid()) {
                        if (dob.isValidStudent()) {
                                if (!roster.contains(studentProfile)) {
                                        Major majorName = getMajorButton();
                                        if (majorName != null) {
                                                validNonResident(roster, studentFirstName, studentLastName,
                                                        dateOfBirth, majorName,
                                                        creditsCompletedString);
                                        }
                                } else {
                                        resultField.appendText(studentFirstName + " " + studentLastName + " " +
                                                dateOfBirth + " is already in the roster." + "\n");
                                }
                        } else {
                                resultField.appendText("DOB invalid: " + dob + " younger " +
                                        "than 16 years old." + "\n");
                        }
                } else {
                        resultField.appendText("DOB invalid: " + dob + " not a valid " +
                                "calendar date!" + "\n");
                }
        }

        /**
         * This method checks if the non-resident you're adding is valid
         *
         * @param roster                 the list of students
         * @param firstName              the first name of the student
         * @param lastName               the last name of the student
         * @param dateOfBirth            the dob of the student
         * @param majorName              the major of the student
         * @param creditsCompletedString the credits completed by the student
         */
        private void validNonResident(Roster roster, String firstName,
                                      String lastName, String dateOfBirth,
                                      Major majorName,
                                      String creditsCompletedString) {

                if (isValidCreditString(creditsCompletedString)) {
                        int creditsCompleted =
                                Integer.parseInt(creditsCompletedString);
                        if (creditsCompleted >= 0) {
                                Student student = new NonResident(new Profile(lastName,
                                        firstName, new Date(dateOfBirth)), majorName,
                                        creditsCompleted);
                                roster.add(student);
                                resultField.appendText(firstName + " " + lastName + " " +
                                                dateOfBirth + " added to the roster." + "\n");


                        } else {
                                resultField.appendText("Credits completed " + "invalid: " +
                                        "cannot be negative!" + "\n");
                        }
                } else {
                        resultField.appendText("Credits completed " + "invalid: not an " +
                                "integer!" + "\n");
                }
        }

        @FXML
        private void addTriState(ActionEvent add) {

                String studentFirstName = String.valueOf(firstName.getText());
                String studentLastName = String.valueOf(lastName.getText());
                String dateOfBirth = dob.getValue().format(
                        DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                String creditsCompletedString = String.valueOf(creditsCompleted.getText());
                String studentState = getStateButton();
                TriState studentProfile = new TriState(new Profile(studentLastName,
                        studentFirstName, new Date(dateOfBirth)));
                Date dob = studentProfile.getProfile().getDob();

                if (dob.isValid()) {
                        if (dob.isValidStudent()) {
                                if (!roster.contains(studentProfile)) {
                                        Major majorName = getMajorButton();
                                        if (majorName != null) {
                                                validTriState(roster, studentFirstName, studentLastName,
                                                        dateOfBirth, majorName,
                                                        creditsCompletedString, studentState,
                                                        studentProfile);
                                        }
                                } else {
                                        resultField.appendText(studentFirstName + " " + studentLastName + " " +
                                                dateOfBirth + " is already in the roster." + "\n");
                                }
                        } else {
                                resultField.appendText("DOB invalid: " + dob + " younger " +
                                        "than 16 years old." + "\n");
                        }
                } else {
                        resultField.appendText("DOB invalid: " + dob + " not a valid " +
                                "calendar date!" + "\n");
                }
        }

        /**
         * This method checks if the student is a valid tri-state student
         *
         * @param roster                 the list of students
         * @param firstName              the first name of the student
         * @param lastName               the last name of the student
         * @param dateOfBirth            the dob of the student
         * @param majorName              the major of the student
         * @param creditsCompletedString the credits completed by the student
         * @param state                  the state the student is from
         * @param studentProfile         the students profile
         */
        private void validTriState(Roster roster, String firstName,
                                   String lastName, String dateOfBirth,
                                   Major majorName,
                                   String creditsCompletedString,
                                   String state,
                                   TriState studentProfile) {
                if (state.equalsIgnoreCase("NY") || state.
                        equalsIgnoreCase("CT")) {
                        studentProfile.setState(state);
                }

                if (isValidCreditString(creditsCompletedString)) {
                        int creditsCompleted =
                                Integer.parseInt(creditsCompletedString);
                        if (creditsCompleted >= 0) {
                                Student student = new TriState(new Profile(lastName,
                                        firstName, new Date(dateOfBirth)), majorName,
                                        creditsCompleted, state);
                                roster.add(student);
                                resultField.appendText(firstName + " " + lastName + " " +
                                        dateOfBirth + " added to the roster." + "\n");

                        } else {
                                resultField.appendText("Credits completed " + "invalid: " +
                                        "cannot be negative!" + "\n");
                        }
                } else {
                        resultField.appendText("Credits completed " + "invalid: not an " +
                                "integer!" + "\n");
                }
        }

        @FXML
        private void addInternational(ActionEvent add) {

                String studentFirstName = String.valueOf(firstName.getText());
                String studentLastName = String.valueOf(lastName.getText());
                String dateOfBirth = dob.getValue().format(
                        DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                String creditsCompletedString = String.valueOf(creditsCompleted.getText());
                Boolean isStudyAbroad = getStudyAbroadButton();
                International studentProfile =
                        new International(new Profile(studentLastName, studentFirstName,
                                new Date(dateOfBirth)));
                Date dob = studentProfile.getProfile().getDob();

                if (dob.isValid()) {
                        if (dob.isValidStudent()) {
                                if (!roster.contains(studentProfile)) {
                                        Major majorName = getMajorButton();
                                        if (majorName != null) {
                                                validInternational(roster, studentFirstName, studentLastName,
                                                        dateOfBirth, majorName,
                                                        creditsCompletedString, isStudyAbroad);
                                        }
                                } else {
                                        resultField.appendText(studentFirstName + " " + studentLastName + " " +
                                                dateOfBirth + " is already in the roster." + "\n");
                                }
                        } else {
                                resultField.appendText("DOB invalid: " + dob + " younger " +
                                        "than 16 years old." + "\n");
                        }
                } else {
                        resultField.appendText("DOB invalid: " + dob + " not a valid " +
                                "calendar date!" + "\n");
                }
        }



        /**
         * This method checks if a student is valid to be international
         *
         * @param roster                 the list of students
         * @param firstName              the first name of the student
         * @param lastName               the last name of the student
         * @param dateOfBirth            the dob of the student
         * @param majorName              the major of the student
         * @param creditsCompletedString the credits completed by the student
         * @param isStudyAbroad          if the student is studying abroad
         */
        private void validInternational(Roster roster, String firstName,
                                        String lastName, String dateOfBirth,
                                        Major majorName,
                                        String creditsCompletedString,
                                        boolean isStudyAbroad) {
                if (isValidCreditString(creditsCompletedString)) {
                        int creditsCompleted =
                                Integer.parseInt(creditsCompletedString);
                        if (creditsCompleted >= 0) {
                                Student student = new International(new Profile(lastName
                                        , firstName, new Date(dateOfBirth)), majorName,
                                        creditsCompleted, isStudyAbroad);
                                roster.add(student);
                                resultField.appendText(firstName + " " + lastName + " " +
                                        dateOfBirth + " added to the roster." + "\n");

                        } else {
                                resultField.appendText("Credits completed " + "invalid: " +
                                        "cannot be negative!" + "\n");
                        }
                } else {
                        resultField.appendText("Credits completed " + "invalid: not an " +
                                "integer!" + "\n");
                }
        }


        @FXML
        void addStudent(ActionEvent event){
                if(resident.isSelected()){
                        addResident(event);
                }
                if(nonResident.isSelected()){
                        addNonResident(event);
                }
                if(triState.isSelected()){
                        addTriState(event);
                }
                if(international.isSelected()){
                        addInternational(event);
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

