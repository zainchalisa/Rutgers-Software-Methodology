package project3.project3;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class TuitionManagerController extends Application {

    @FXML
    private Button addStudentButton, removeStudentButton, changeMajorButton, loadFromFileButton;

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

    @FXML
    private Button enrollStudentButton, dropStudentButton;

    @FXML
    private TextField enrollFirstName, enrollLastName;

    @FXML
    private DatePicker enrollDob;

    @FXML
    private TextField enrollCreditsCompleted, scholarFirstName, scholarLastName;

    @FXML
    private DatePicker scholarDob;

    @FXML
    private TextField scholarAmount;

    @FXML
    private Button updateScholarButton;

    @FXML
    private MenuItem printProfileItem, printSchoolMajorItem, printStandingItem;

    @FXML
    private MenuItem printRBSItem, printSASItem, printSCIItem, printSOEItem;

    @FXML
    private MenuItem printEnrolledItem, printTuitionDueItem, printSemesterEndItem;

    Roster roster = new Roster();
    Enrollment enrollment = new Enrollment();

    public void initialize() {
        disableDOBTextFields();
        restrictInputRoster();
        restrictInputEnroll();
        restrictInputScholarship();
    }

    @FXML
    private void disableButtons() {
        if (resident.isSelected()) {
            triState.setSelected(false);
            international.setSelected(false);
            newYorkState.setSelected(false);
            connecticutState.setSelected(false);
            triState.setDisable(true);
            international.setDisable(true);
            newYorkState.setDisable(true);
            connecticutState.setDisable(true);
            studyAbroad.setDisable(true);
        }
        if (nonResident.isSelected()) {
            triState.setDisable(false);
            international.setDisable(false);
            newYorkState.setDisable(false);
            connecticutState.setDisable(false);
            studyAbroad.setDisable(false);
        }
        if (triState.isSelected()) {
            international.setSelected(false);
            studyAbroad.setSelected(false);
            studyAbroad.setDisable(true);
            newYorkState.setDisable(false);
            connecticutState.setDisable(false);
        }

        if (international.isSelected()) {
            newYorkState.setSelected(false);
            connecticutState.setSelected(false);
            triState.setSelected(false);
            newYorkState.setDisable(true);
            connecticutState.setDisable(true);
            studyAbroad.setDisable(false);
        }

        if (connecticutState.isSelected()) {
            newYorkState.setSelected(false);
        }
    }

    private void disableDOBTextFields() {
        dob.getEditor().setDisable(true);
        enrollDob.getEditor().setDisable(true);
        scholarDob.getEditor().setDisable(true);
    }

    private Major getMajorButton() {
        Major major = null;
        if (BAIT.isSelected()) {
            major = Major.BAIT;
            return major;
        } else if (CS.isSelected()) {
            major = Major.CS;
            return major;
        } else if (EE.isSelected()) {
            major = Major.EE;
            return major;
        } else if (ITI.isSelected()) {
            major = Major.ITI;
            return major;
        } else if (MATH.isSelected()) {
            major = Major.MATH;
            return major;
        } else {
            resultField.appendText("Please select a major." + "\n");
            return major;
        }

    }

    public String getStateButton() {
        if (newYorkState.isSelected()) {
            return "NY";
        } else if (connecticutState.isSelected()) {
            return "CT";
        } else {
            return null;
        }
    }

    public boolean getStudyAbroadButton() {
        if (studyAbroad.isSelected()) {
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
                resultField.appendText(firstName + " " + lastName + " " +
                        dateOfBirth + " added to the roster." + "\n");
            } else {
                resultField.appendText("Credits completed " + "invalid: " +
                        "cannot be negative!" + "\n");
            }
        } else {
            resultField.appendText(
                    "Credits completed " + "invalid: not an " +
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

    private void inputAddResident(Roster roster, String[] inputLine) {
        if (inputLine.length != 6) {
            resultField.appendText("Missing data in line command." + "\n");
            return;
        }
        String firstName = inputLine[1];
        String lastName = inputLine[2];
        String dateOfBirth = inputLine[3];
        String major = inputLine[4];
        String creditsCompletedString = inputLine[5];
        int defaultScholarship = 0;
        Resident studentProfile = new Resident(new Profile(lastName,
                firstName, new Date(dateOfBirth)));
        Date dob = studentProfile.getProfile().getDob();

        inputCheckResident(dob,studentProfile,firstName,
                lastName,dateOfBirth,creditsCompletedString, major,
                defaultScholarship);
    }

    private boolean hasEmptyFieldsRoster() {
        if (firstName.getText().equalsIgnoreCase("")) {
            resultField.appendText("Please enter a first name." + "\n");
            return true;
        } else if (lastName.getText().equalsIgnoreCase("")) {
            resultField.appendText("Please enter a last name." + "\n");
            return true;
        } else if (dob.getValue() == null) {
            resultField.appendText("Please enter a date of birth." + "\n");
            return true;
        } else if (creditsCompleted.getText().equalsIgnoreCase("")) {
            resultField.appendText(
                    "Please enter credits completed." + "\n");
            return true;
        }
        return false;
    }

    private void checkResident (Date dobObject, Resident studentProfile,
                                String studentFirstName,
                                String studentLastName,String dateOfBirth,
                                String creditsCompletedString,
                                int defaultScholarship) {
        if (dobObject.isValid()) {
            if (dobObject.isValidStudent()) {
                if (!roster.contains(studentProfile)) {
                    Major majorName = getMajorButton();
                    if (majorName != null) {
                        validResident(roster, studentFirstName,
                                studentLastName,
                                dateOfBirth, majorName,
                                creditsCompletedString,
                                defaultScholarship);
                    }
                } else {
                    resultField.appendText(
                            studentFirstName + " " + studentLastName +
                                    " " +
                                    dateOfBirth +
                                    " is already in the roster." + "\n");
                }
            } else {
                resultField.appendText(
                        "DOB invalid: " + dateOfBirth + " younger " +
                                "than 16 years old." + "\n");
            }
        } else {
            resultField.appendText(
                    "DOB invalid: " + dateOfBirth + " not a valid " +
                            "calendar date!" + "\n");
        }
    }

    private void inputCheckResident (Date dobObject, Resident studentProfile,
                                String studentFirstName,
                                String studentLastName,String dateOfBirth,
                                String creditsCompletedString, String major,
                                int defaultScholarship) {
        if (dobObject.isValid()) {
            if (dobObject.isValidStudent()) {
                if (!roster.contains(studentProfile)) {
                    Major majorName = checkMajor(major);
                    if (majorName != null) {
                        validResident(roster, studentFirstName,
                                studentLastName,
                                dateOfBirth, majorName,
                                creditsCompletedString,
                                defaultScholarship);
                    }
                } else {
                    resultField.appendText(
                            studentFirstName + " " + studentLastName +
                                    " " +
                                    dateOfBirth +
                                    " is already in the roster." + "\n");
                }
            } else {
                resultField.appendText(
                        "DOB invalid: " + dateOfBirth + " younger " +
                                "than 16 years old." + "\n");
            }
        } else {
            resultField.appendText(
                    "DOB invalid: " + dateOfBirth + " not a valid " +
                            "calendar date!" + "\n");
        }
    }
    @FXML
    void addResident(ActionEvent add) {
        String studentFirstName = String.valueOf(firstName.getText());
        String studentLastName = String.valueOf(lastName.getText());
        String dateOfBirth;
        String creditsCompletedString =
                String.valueOf(creditsCompleted.getText());
        int defaultScholarship = 0;

        if (hasEmptyFieldsRoster()) {
            return;
        }

        dateOfBirth = dob.getValue().format(
                DateTimeFormatter.ofPattern("MM/dd/yyyy"));

        Resident studentProfile = new Resident(new Profile(studentLastName,
                studentFirstName, new Date(dateOfBirth)));
        Date dobObject = new Date(dateOfBirth);

        checkResident(dobObject,studentProfile,studentFirstName,
                studentLastName,dateOfBirth,creditsCompletedString,
                defaultScholarship);
    }

    private void inputAddNonResident(Roster roster, String[] inputLine) {
        if (inputLine.length != 6) {
            System.out.println("Missing data in command line." + "\n");
            return;
        }
        String firstName = inputLine[1];
        String lastName = inputLine[2];
        String dateOfBirth = inputLine[3];
        String major = inputLine[4];
        String creditsCompletedString = inputLine[5];
        NonResident studentProfile =
                new NonResident(new Profile(lastName, firstName,
                        new Date(dateOfBirth)));
        Date dob = studentProfile.getProfile().getDob();

        inputCheckNonResident(dob,studentProfile,firstName,
                lastName,dateOfBirth, major,creditsCompletedString);
    }

    private void checkNonResident (Date dob, NonResident studentProfile,
                                String studentFirstName,
                                String studentLastName,String dateOfBirth,
                                String creditsCompletedString) {
        if (dob.isValid()) {
            if (dob.isValidStudent()) {
                if (!roster.contains(studentProfile)) {
                    Major majorName = getMajorButton();
                    if (majorName != null) {
                        validNonResident(roster, studentFirstName,
                                studentLastName,
                                dateOfBirth, majorName,
                                creditsCompletedString);
                    }
                } else {
                    resultField.appendText(
                            studentFirstName + " " + studentLastName +
                                    " " +
                                    dateOfBirth +
                                    " is already in the roster." + "\n");
                }
            } else {
                resultField.appendText(
                        "DOB invalid: " + dob + " younger " +
                                "than 16 years old." + "\n");
            }
        } else {
            resultField.appendText(
                    "DOB invalid: " + dob + " not a valid " +
                            "calendar date!" + "\n");
        }
    }

    private void inputCheckNonResident (Date dob, NonResident studentProfile,
                                   String studentFirstName,
                                   String studentLastName,String dateOfBirth, String major,
                                   String creditsCompletedString) {
        if (dob.isValid()) {
            if (dob.isValidStudent()) {
                if (!roster.contains(studentProfile)) {
                    Major majorName = checkMajor(major);
                    if (majorName != null) {
                        validNonResident(roster, studentFirstName,
                                studentLastName,
                                dateOfBirth, majorName,
                                creditsCompletedString);
                    }
                } else {
                    resultField.appendText(
                            studentFirstName + " " + studentLastName +
                                    " " +
                                    dateOfBirth +
                                    " is already in the roster." + "\n");
                }
            } else {
                resultField.appendText(
                        "DOB invalid: " + dob + " younger " +
                                "than 16 years old." + "\n");
            }
        } else {
            resultField.appendText(
                    "DOB invalid: " + dob + " not a valid " +
                            "calendar date!" + "\n");
        }
    }
    @FXML
    private void addNonResident(ActionEvent add) {

        String studentFirstName = String.valueOf(firstName.getText());
        String studentLastName = String.valueOf(lastName.getText());
        String dateOfBirth;
        String creditsCompletedString =
                String.valueOf(creditsCompleted.getText());

        if (hasEmptyFieldsRoster()) {
            return;
        }

        dateOfBirth = dob.getValue().format(
                DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        NonResident studentProfile =
                new NonResident(
                        new Profile(studentLastName, studentFirstName,
                                new Date(dateOfBirth)));
        Date dob = studentProfile.getProfile().getDob();

        checkNonResident(dob,studentProfile,studentFirstName,
                studentLastName,dateOfBirth,creditsCompletedString);
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
            resultField.appendText(
                    "Credits completed " + "invalid: not an " +
                            "integer!" + "\n");
        }
    }

    private void inputAddTriState(Roster roster, String[] inputLine) {
        if (inputLine.length != 7) {
            if (inputLine.length == 6) {
                System.out.println("Missing the state code." + "\n");
                return;
            }
            System.out.println("Missing data in command line." + "\n");
            return;
        }
        String firstName = inputLine[1];
        String lastName = inputLine[2];
        String dateOfBirth = inputLine[3];
        String major = inputLine[4];
        String creditsCompletedString = inputLine[5];
        String state = inputLine[6];
        TriState studentProfile = new TriState(new Profile(lastName,
                firstName, new Date(dateOfBirth)));
        Date dob = studentProfile.getProfile().getDob();

        inputCheckTriState(dob,studentProfile,firstName,
                lastName,dateOfBirth,creditsCompletedString, major,
                state);
    }

    private void checkTriState (Date dob, TriState studentProfile,
                                   String studentFirstName,
                                   String studentLastName,String dateOfBirth,
                                   String creditsCompletedString, String studentState) {
        if (dob.isValid()) {
            if (dob.isValidStudent()) {
                if (!roster.contains(studentProfile)) {
                    Major majorName = getMajorButton();
                    if (majorName != null) {
                        validTriState(roster, studentFirstName,
                                studentLastName,
                                dateOfBirth, majorName,
                                creditsCompletedString, studentState,
                                studentProfile);
                    }
                } else {
                    resultField.appendText(
                            studentFirstName + " " + studentLastName +
                                    " " +
                                    dateOfBirth +
                                    " is already in the roster." + "\n");
                }
            } else {
                resultField.appendText(
                        "DOB invalid: " + dob + " younger " +
                                "than 16 years old." + "\n");
            }
        } else {
            resultField.appendText(
                    "DOB invalid: " + dob + " not a valid " +
                            "calendar date!" + "\n");
        }
    }

    private void inputCheckTriState (Date dob, TriState studentProfile,
                                String studentFirstName,
                                String studentLastName,String dateOfBirth,
                                String creditsCompletedString, String major, String studentState) {
        if (dob.isValid()) {
            if (dob.isValidStudent()) {
                if (!roster.contains(studentProfile)) {
                    Major majorName = checkMajor(major);
                    if (majorName != null) {
                        validTriState(roster, studentFirstName,
                                studentLastName,
                                dateOfBirth, majorName,
                                creditsCompletedString, studentState,
                                studentProfile);
                    }
                } else {
                    resultField.appendText(
                            studentFirstName + " " + studentLastName +
                                    " " +
                                    dateOfBirth +
                                    " is already in the roster." + "\n");
                }
            } else {
                resultField.appendText(
                        "DOB invalid: " + dob + " younger " +
                                "than 16 years old." + "\n");
            }
        } else {
            resultField.appendText(
                    "DOB invalid: " + dob + " not a valid " +
                            "calendar date!" + "\n");
        }
    }

    @FXML
    private void addTriState(ActionEvent add) {
        String studentFirstName = String.valueOf(firstName.getText());
        String studentLastName = String.valueOf(lastName.getText());
        String dateOfBirth;
        String creditsCompletedString =
                String.valueOf(creditsCompleted.getText());

        if (hasEmptyFieldsRoster()) {
            return;
        }

        dateOfBirth = dob.getValue().format(
                DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        String studentState = getStateButton();
        TriState studentProfile = new TriState(new Profile(studentLastName,
                studentFirstName, new Date(dateOfBirth)));
        Date dob = studentProfile.getProfile().getDob();

        checkTriState(dob,studentProfile,studentFirstName,
                studentLastName,dateOfBirth,creditsCompletedString,
                studentState);
    }

    /**
     * This method checks if the student is a valid tri-state student
     *
     * @param roster                 the list of students
     * @param firstName              the first name of the student
     * @param lastName               the last name of the student
     * @param dateOfBirth            the dob of the student
     * @param majorName              the major of the student
     * @param creditsCompletedString the credits completed by the
     *                               student
     * @param state                  the state the student is from
     * @param studentProfile         the students profile
     */
    private void validTriState(Roster roster, String firstName,
                               String lastName, String dateOfBirth,
                               Major majorName,
                               String creditsCompletedString,
                               String state,
                               TriState studentProfile) {
        if (state.equals("NY") || state.equals("CT")) {
            studentProfile.setState(state);
            if (isValidCreditString(creditsCompletedString)) {
                int creditsCompleted =
                        Integer.parseInt(creditsCompletedString);
                if (creditsCompleted >= 0) {
                    Student student = new TriState(new Profile(lastName,
                            firstName, new Date(dateOfBirth)), majorName,
                            creditsCompleted, state);
                    roster.add(student);
                    resultField.appendText(
                            firstName + " " + lastName + " " +
                                    dateOfBirth + " added to the roster." +
                                    "\n");

                } else {
                    resultField.appendText(
                            "Credits completed " + "invalid: " +
                                    "cannot be negative!" + "\n");
                }
            } else {
                resultField.appendText(
                        "Credits completed " + "invalid: not an " +
                                "integer!" + "\n");
            }
        } else {
            resultField.appendText("Please select a state." + "\n");
        }

    }

    private void inputAddInternational(Roster roster, String[] inputLine) {
        if (inputLine.length < 6) { // replace magic number
            System.out.println("Missing data in command line.");
            return;
        }
        String firstName = inputLine[1];
        String lastName = inputLine[2];
        String dateOfBirth = inputLine[3];
        String major = inputLine[4];
        String creditsCompletedString = inputLine[5];
        String studyAbroad = validStudyAbroad(inputLine);
        boolean isStudyAbroad = Boolean.parseBoolean(studyAbroad);
        International studentProfile =
                new International(new Profile(lastName, firstName,
                        new Date(dateOfBirth)));
        Date dob = studentProfile.getProfile().getDob();

        inputCheckInternational(dob,studentProfile,firstName,
                lastName,dateOfBirth,creditsCompletedString, major,
                isStudyAbroad);

    }

    private String validStudyAbroad(String[] inputLine) {
        String studyAbroad = null;
        try {
            studyAbroad = inputLine[6];
        } catch (ArrayIndexOutOfBoundsException e) {
            studyAbroad = "false";
        }
        return studyAbroad;
    }

    private void checkInternational (Date dob, NonResident studentProfile,
                                   String studentFirstName,
                                   String studentLastName,
                                     String dateOfBirth,
                                   String creditsCompletedString,
                                     boolean isStudyAbroad) {
        if (dob.isValid()) {
            if (dob.isValidStudent()) {
                if (!roster.contains(studentProfile)) {
                    Major majorName = getMajorButton();
                    if (majorName != null) {
                        validInternational(roster, studentFirstName,
                                studentLastName,
                                dateOfBirth, majorName,
                                creditsCompletedString, isStudyAbroad);
                    }
                } else {
                    resultField.appendText(
                            studentFirstName + " " + studentLastName +
                                    " " +
                                    dateOfBirth +
                                    " is already in the roster." + "\n");
                }
            } else {
                resultField.appendText(
                        "DOB invalid: " + dob + " younger " +
                                "than 16 years old." + "\n");
            }
        } else {
            resultField.appendText("DOB invalid: " + dob +
                    " not a valid " +
                    "calendar date!" + "\n");
        }
    }

    private void inputCheckInternational (Date dob, NonResident studentProfile,
                                     String studentFirstName,
                                     String studentLastName,
                                     String dateOfBirth,
                                     String creditsCompletedString,String major,
                                     boolean isStudyAbroad) {
        if (dob.isValid()) {
            if (dob.isValidStudent()) {
                if (!roster.contains(studentProfile)) {
                    Major majorName = checkMajor(major);
                    if (majorName != null) {
                        validInternational(roster, studentFirstName,
                                studentLastName,
                                dateOfBirth, majorName,
                                creditsCompletedString, isStudyAbroad);
                    }
                } else {
                    resultField.appendText(
                            studentFirstName + " " + studentLastName +
                                    " " +
                                    dateOfBirth +
                                    " is already in the roster." + "\n");
                }
            } else {
                resultField.appendText(
                        "DOB invalid: " + dob + " younger " +
                                "than 16 years old." + "\n");
            }
        } else {
            resultField.appendText("DOB invalid: " + dob +
                    " not a valid " +
                    "calendar date!" + "\n");
        }
    }
    @FXML
    private void addInternational(ActionEvent add) {

        String studentFirstName = String.valueOf(firstName.getText());
        String studentLastName = String.valueOf(lastName.getText());
        String dateOfBirth;
        String creditsCompletedString =
                String.valueOf(creditsCompleted.getText());

        if (hasEmptyFieldsRoster()) {
            return;
        }

        dateOfBirth = dob.getValue().format(
                DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        Boolean isStudyAbroad = getStudyAbroadButton();
        International studentProfile =
                new International(
                        new Profile(studentLastName, studentFirstName,
                                new Date(dateOfBirth)));
        Date dob = studentProfile.getProfile().getDob();

        checkInternational(dob,studentProfile,studentFirstName,
                studentLastName,dateOfBirth,creditsCompletedString,
                isStudyAbroad);
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
            resultField.appendText(
                    "Credits completed " + "invalid: not an " +
                            "integer!" + "\n");
        }
    }

    @FXML
    void addStudent(ActionEvent event) {
        if (triState.isSelected()) {
            if (newYorkState.isSelected() ||
                    connecticutState.isSelected()) {
                addTriState(event);
            } else {
                resultField.appendText("Please select a state." + "\n");
            }
        } else if (international.isSelected()) {
            addInternational(event);
        } else if (resident.isSelected()) {
            addResident(event);
        } else if (nonResident.isSelected()) {
            addNonResident(event);
        } else {
            resultField.appendText("Please select the student's status."
                    + "\n");
        }
    }

    @FXML
    void removeStudent(ActionEvent event) {
        String studentFirstName = String.valueOf(firstName.getText());
        String studentLastName = String.valueOf(lastName.getText());
        String dateOfBirth;

        if (hasEmptyFieldsRoster()) {
            return;
        }

        dateOfBirth = dob.getValue().format(
                DateTimeFormatter.ofPattern("MM/dd/yyyy"));

        Student student =
                new Resident(new Profile(studentLastName, studentFirstName,
                        new Date(dateOfBirth)));
        if (roster.contains(student)) {
            EnrollStudent enrollStudent = new EnrollStudent(
                    new Profile(studentLastName, studentFirstName,
                            new Date(dateOfBirth)));
            if (!enrollment.contains(enrollStudent)) {
                roster.remove(student);
                resultField.appendText(
                        studentFirstName + " " + studentLastName + " " +
                                dateOfBirth + " removed from the roster" +
                                "." + "\n");
            } else {
                resultField.appendText(
                        "Can not remove " + studentFirstName + " " +
                                studentLastName + " " + dateOfBirth +
                                " because they are currently enrolled." +
                                "\n");
            }
        } else {
            resultField.appendText(
                    studentFirstName + " " + studentLastName + " " +
                            dateOfBirth + " is not in the roster." + "\n");
        }
    }

    private Major checkMajor(String major) {
        Major majorName = null;
        if (major.equalsIgnoreCase("CS")) {
            majorName = Major.CS;
        } else if (major.equalsIgnoreCase("MATH")) {
            majorName = Major.MATH;
        } else if (major.equalsIgnoreCase("EE")) {
            majorName = Major.EE;
        } else if (major.equalsIgnoreCase("ITI")) {
            majorName = Major.ITI;
        } else if (major.equalsIgnoreCase("BAIT")) {
            majorName = Major.BAIT;
        } else {
            resultField.appendText("Major code invalid: " + major + "\n");
        }
        return majorName;
    }

    @FXML
    private void changeMajor(ActionEvent event) {
        String studentFirstName = String.valueOf(firstName.getText());
        String studentLastName = String.valueOf(lastName.getText());
        String dateOfBirth;

        if (hasEmptyFieldsRoster()) {
            return;
        }

        dateOfBirth = dob.getValue().format(
                DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        String major = getMajorButton().name();

        Student student =
                new Resident(new Profile(studentLastName, studentFirstName,
                        new Date(dateOfBirth)));
        if (roster.contains(student)) {
            int studentIndex = roster.find(student);
            Major majorName = checkMajor(major);
            if (majorName != student.getMajor()) {
                if (majorName != null) {
                    Student rosterStudent =
                            roster.getRoster()[studentIndex];
                    rosterStudent.setMajor(majorName);
                    resultField.appendText(
                            studentFirstName + " " + studentLastName +
                                    " " +
                                    dateOfBirth + " major changed to " +
                                    major + "\n");
                }
            }
            }else {
            resultField.appendText(
                    studentFirstName + " " + studentLastName + " " +
                            dateOfBirth + " is not in the roster." +
                            "\n");
        }
    }

    @FXML
    private void loadRoster(ActionEvent event) {
        try {
            String filename = "studentList.txt";
            Scanner fileScanner = new Scanner(new File(filename));
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] inputLines = line.split(",");
                String command = inputLines[0];
                if (command.equals("R")) {
                    inputAddResident(roster, inputLines);
                } else if (command.equals("I")) {
                    inputAddInternational(roster, inputLines);
                } else if (command.equals("T")) {
                    inputAddTriState(roster, inputLines);
                } else if (command.equals("N")) {
                    inputAddNonResident(roster, inputLines);
                }
            }
            fileScanner.close();
            resultField.appendText(
                    "Students loaded to the roster. " + "\n");
        } catch (FileNotFoundException e) {
            resultField.appendText("** File not found **" + "\n");
        }
    }

    private void restrictInputRoster() {
        TextFormatter<String> formatterFN = new TextFormatter<>(change -> {
            if (change.getText().matches(
                    "^[a-zA-Z\\-]*[\b]?$")) { // only allow non-digit
                    // characters
                return change; // reject the change
            } else {
                return null; // accept the change
            }
        });
        TextFormatter<String> formatterLN = new TextFormatter<>(change -> {
            if (change.getText().matches(
                    "^[a-zA-Z\\-]*[\b]?$")) { // only allow non-digit
                    // characters
                return change; // reject the change
            } else {
                return null; // accept the change
            }
        });
        TextFormatter<String> formatterCE = new TextFormatter<>(change -> {
            if (change.getText().matches(
                    "^\\d*[\b]?$")) { // only allow non-digit characters
                return change; // reject the change
            } else {
                return null; // accept the change
            }
        });
        firstName.setTextFormatter(formatterFN);
        lastName.setTextFormatter(formatterLN);
        creditsCompleted.setTextFormatter(formatterCE);
    }

    private void restrictInputEnroll() {
        TextFormatter<String> formatterFN = new TextFormatter<>(change -> {
            if (change.getText().matches(
                    "^[a-zA-Z\\-]*[\b]?$")) { // only allow non-digit
                    // characters
                return change; // reject the change
            } else {
                return null; // accept the change
            }
        });
        TextFormatter<String> formatterLN = new TextFormatter<>(change -> {
            if (change.getText().matches(
                    "^[a-zA-Z\\-]*[\b]?$")) { // only allow non-digit
                    // characters
                return change; // reject the change
            } else {
                return null; // accept the change
            }
        });
        TextFormatter<String> formatterCE = new TextFormatter<>(change -> {
            if (change.getText().matches(
                    "^\\d*[\b]?$")) { // only allow non-digit characters
                return change; // reject the change
            } else {
                return null; // accept the change
            }
        });
        enrollFirstName.setTextFormatter(formatterFN);
        enrollLastName.setTextFormatter(formatterLN);
        enrollCreditsCompleted.setTextFormatter(formatterCE);
    }

    private void restrictInputScholarship() {
        TextFormatter<String> formatterFN = new TextFormatter<>(change -> {
            if (change.getText().matches(
                    "^[a-zA-Z\\-]*[\b]?$")) { // only allow non-digit
                    // characters
                return change; // reject the change
            } else {
                return null; // accept the change
            }
        });
        TextFormatter<String> formatterLN = new TextFormatter<>(change -> {
            if (change.getText().matches(
                    "^[a-zA-Z\\-]*[\b]?$")) { // only allow non-digit
                    // characters
                return change; // reject the change
            } else {
                return null; // accept the change
            }
        });
        TextFormatter<String> formatterCE = new TextFormatter<>(change -> {
            if (change.getText().matches(
                    "^\\d*[\b]?$")) { // only allow non-digit characters
                return change; // reject the change
            } else {
                return null; // accept the change
            }
        });
        scholarFirstName.setTextFormatter(formatterFN);
        scholarLastName.setTextFormatter(formatterLN);
        scholarAmount.setTextFormatter(formatterCE);
    }

    private boolean hasEmptyFieldEnroll() {
        if (enrollFirstName.getText().equalsIgnoreCase("")) {
            resultField.appendText("Please enter a first name." + "\n");
            return true;
        } else if (enrollLastName.getText().equalsIgnoreCase("")) {
            resultField.appendText("Please enter a last name." + "\n");
            return true;
        } else if (enrollDob.getValue() == null) {
            resultField.appendText("Please enter a date of birth." + "\n");
            return true;
        } else if (enrollCreditsCompleted.getText().equalsIgnoreCase("")) {
            resultField.appendText(
                    "Please enter credits to enroll." + "\n");
            return true;
        }
        return false;
    }
    @FXML
    void enrollStudent(ActionEvent enroll) {
        String firstName = String.valueOf(enrollFirstName.getText());
        String lastName = String.valueOf(enrollLastName.getText());
        String dateOfBirth;
        String creditsCompletedString =
                String.valueOf(enrollCreditsCompleted.getText());

        if (hasEmptyFieldEnroll()) {
            return;
        }

        dateOfBirth = enrollDob.getValue().format(
                DateTimeFormatter.ofPattern("MM/dd/yyyy"));
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
                resultField.appendText(
                        "Cannot enroll: " + firstName + " " +
                                lastName + " " + dateOfBirth + " " +
                                "is not in the roster." + "\n");
            }
        } else {
            resultField.appendText(
                    "Credits enrolled is not an integer." + "\n");
        }
    }

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
            resultField.appendText(
                    "(" + student.getClass().getSimpleName() +
                            " studentstudy abroad) " + creditsEnrolled +
                            ": invalid credit hours." + "\n");
        } else if (student.getClass().getSimpleName().equals((
                "International"))) {
            resultField.appendText(
                    "(" + student.getClass().getSimpleName() +
                            "student) " + creditsEnrolled +
                            ": invalid credit hours." + "\n");
        } else {
            resultField.appendText(
                    "(" + student.getClass().getSimpleName() +
                            ") " + creditsEnrolled +
                            ": invalid credit hours." + "\n");
        }
    }

    @FXML
    void dropStudent(ActionEvent drop) {
        String firstName = String.valueOf(enrollFirstName.getText());
        String lastName = String.valueOf(enrollLastName.getText());
        String dateOfBirth;
        if (enrollFirstName.getText().equalsIgnoreCase("")) {
            resultField.appendText("Please enter a first name." + "\n");
            return;
        } else if (enrollLastName.getText().equalsIgnoreCase("")) {
            resultField.appendText("Please enter a last name." + "\n");
            return;
        } else if (enrollDob.getValue() == null) {
            resultField.appendText("Please enter a date of birth." + "\n");
            return;
        }

        dateOfBirth = enrollDob.getValue().format(
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

    private boolean hasEmptyFieldScholar() {
            if (scholarFirstName.getText().equalsIgnoreCase("")) {
                    resultField.appendText("Please enter a first name." + "\n");
                    return true;
            } else if (scholarLastName.getText().equalsIgnoreCase("")) {
                    resultField.appendText("Please enter a last name." + "\n");
                    return true;
            } else if (scholarDob.getValue() == null) {
                    resultField.appendText("Please enter a date of birth." + "\n");
                    return true;
            } else if (scholarAmount.getText().equalsIgnoreCase("")) {
                    resultField.appendText(
                            "Please enter a scholarship amount." + "\n");
                    return true;
            }
            return false;
    }

    private void checkEligibleScholarship(Student student,
                                          String firstName,String lastName,
                                          String dateOfBirth,
                                          String scholarshipString) {
            if (roster.contains(student)) {
                    int studentIndex = roster.find(student);
                    student = roster.getRoster()[studentIndex];
                    if (student instanceof Resident) {
                            residentScholarship(student, firstName,
                                    lastName, dateOfBirth,
                                    scholarshipString, enrollment);
                    } else {
                        resultField.appendText(
                                firstName + " " + lastName
                                        + " " +
                                        dateOfBirth + " (" +
                                        student.getClass().
                                                getSimpleName()
                                        + ") is not eligible "
                                        + "for " + "the " +
                                        "scholarship." + "\n");
                    }
            } else {
                    resultField.appendText(firstName + " " + lastName +
                            " " + dateOfBirth + " is not in the roster." +
                            "\n");
            }
    }
    @FXML
    void grantScholarship(ActionEvent updateScholarship) {
        String firstName = String.valueOf(scholarFirstName.getText());
        String lastName = String.valueOf(scholarLastName.getText());
        String dateOfBirth;
        String scholarshipString = String.valueOf(scholarAmount.getText());

        if (hasEmptyFieldScholar()) {
                return;
        }

        dateOfBirth = scholarDob.getValue().format(
                DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        Student student = new Resident(new Profile(lastName, firstName,
                new Date(dateOfBirth)));

        checkEligibleScholarship(student,firstName,lastName,dateOfBirth,
                scholarshipString);
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
            if (isValidCreditString(scholarshipString)) {
                int scholarship = Integer.parseInt(scholarshipString);
                if (((Resident) student).isValidScholarship(scholarship)) {
                    ((Resident) student).setScholarship(scholarship);
                    resultField.appendText(
                            firstName + " " + lastName + " " +
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

    @FXML
    void printProfile(ActionEvent event) {
        roster.print(resultField);
    }

    @FXML
    void printSchoolMajor(ActionEvent event) {
        roster.printBySchoolMajor(resultField);
    }

    @FXML
    void printStanding(ActionEvent event) {
        roster.printByStanding(resultField);
    }

    @FXML
    void listRBS(ActionEvent event) {
        String school = "RBS";
        Student[] sortedSchoolArray = new Student[roster.getSize()];
        int counter = 0;

        for (int i = 0; i < roster.getSize(); i++) {
            if (roster.getRoster()[i].getMajor().getSchool().
                    equalsIgnoreCase(school)) {
                sortedSchoolArray[counter] = roster.getRoster()[i];
                counter++;
            }
        }
        roster.insertionSortList(sortedSchoolArray);
        resultField.appendText("* Students in " + school + " *" + "\n");
        for (int i = 0; i < counter; i++) {
            resultField.appendText(sortedSchoolArray[i] + "\n");
        }
        resultField.appendText("* end of list **" + "\n");
    }

    @FXML
    void listSAS(ActionEvent event) {
        String school = "SAS";
        Student[] sortedSchoolArray = new Student[roster.getSize()];
        int counter = 0;

        for (int i = 0; i < roster.getSize(); i++) {
            if (roster.getRoster()[i].getMajor().getSchool().
                    equalsIgnoreCase(school)) {
                sortedSchoolArray[counter] = roster.getRoster()[i];
                counter++;
            }
        }
        roster.insertionSortList(sortedSchoolArray);
        resultField.appendText("* Students in " + school + " *" + "\n");
        for (int i = 0; i < counter; i++) {
            resultField.appendText(sortedSchoolArray[i] + "\n");
        }
        resultField.appendText("* end of list **" + "\n");
    }

    @FXML
    void listSCI(ActionEvent event) {
        String school = "SC&I";
        Student[] sortedSchoolArray = new Student[roster.getSize()];
        int counter = 0;

        for (int i = 0; i < roster.getSize(); i++) {
            if (roster.getRoster()[i].getMajor().getSchool().
                    equalsIgnoreCase(school)) {
                sortedSchoolArray[counter] = roster.getRoster()[i];
                counter++;
            }
        }
        roster.insertionSortList(sortedSchoolArray);
        resultField.appendText("* Students in " + school + " *" + "\n");
        for (int i = 0; i < counter; i++) {
            resultField.appendText(sortedSchoolArray[i] + "\n");
        }
        resultField.appendText("* end of list **" + "\n");
    }

    @FXML
    void listSOE(ActionEvent event) {
        String school = "SOE";
        Student[] sortedSchoolArray = new Student[roster.getSize()];
        int counter = 0;

        for (int i = 0; i < roster.getSize(); i++) {
            if (roster.getRoster()[i].getMajor().getSchool().
                    equalsIgnoreCase(school)) {
                sortedSchoolArray[counter] = roster.getRoster()[i];
                counter++;
            }
        }
        roster.insertionSortList(sortedSchoolArray);
        resultField.appendText("* Students in " + school + " *" + "\n");
        for (int i = 0; i < counter; i++) {
            resultField.appendText(sortedSchoolArray[i] + "\n");
        }
        resultField.appendText("* end of list **" + "\n");
    }

    @FXML
    void printEnrollment(ActionEvent event) {
        if (enrollment.getEnrollStudents()[0] == null) {
            resultField.appendText("Enrollment is empty!" + "\n");
        } else {
            resultField.appendText("** Enrollment **" + "\n");
            for (int i = 0; i < enrollment.getEnrollStudents().length;
                 i++) {
                if (enrollment.getEnrollStudents()[i] != null) {
                    resultField.appendText(
                            enrollment.getEnrollStudents()[i]
                                    + ": credits enrolled: " + enrollment.
                                    getEnrollStudents()[i].getCreditsEnrolled() +
                                    "\n");
                }
            }
            resultField.appendText("* end of enrollment *" + "\n");
        }

    }

    @FXML
    void printTuitionDue(ActionEvent event) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###.00");
        if (enrollment.getEnrollStudents()[0] != null) {
            resultField.appendText("** Tuition due **" + "\n");
            for (EnrollStudent enrollStudent :
                    enrollment.getEnrollStudents()) {
                if (enrollStudent != null) {
                    Student student =
                            roster.getStudent(new NonResident(enrollStudent
                                    .getProfile(), null,
                                    0));
                    double tuitionDue =
                            student.tuitionDue(enrollStudent.
                                    getCreditsEnrolled());
                    if (student.getClass().getSimpleName().equals(
                            "International")) {
                        tutitionInternational(student, enrollStudent,
                                decimalFormat, tuitionDue);
                    } else if (student.getClass().getSimpleName().
                            equals("TriState")) {
                        tuitionTriState(student, enrollStudent,
                                decimalFormat, tuitionDue);
                    } else if (student.getClass().getSimpleName().
                            equals("NonResident")) {
                        tuitionNonResident(student, enrollStudent,
                                decimalFormat, tuitionDue);
                    } else if (student.getClass().getSimpleName().
                            equals("Resident")) {
                        tuitionResident(student, enrollStudent,
                                decimalFormat, tuitionDue);
                    }
                }
            }
            resultField.appendText("* end of tuition due *" + "\n");
        } else {
            resultField.appendText("Enrollment is empty!" + "\n");
        }
    }

    private void tutitionInternational(Student student,
                                       EnrollStudent enrollStudent,
                                       DecimalFormat decimalFormat,
                                       double tuitionDue) {
        if (((International) student).isStudyAbroad()) {
            resultField.appendText("" + student.getProfile() + " " +
                    "(International studentstudy " + "abroad) enrolled " +
                    enrollStudent.getCreditsEnrolled() +
                    " credits: tuition due: $" + decimalFormat.
                    format(tuitionDue) + "\n");
        } else {
            resultField.appendText("" + student.getProfile() + " " +
                    "(International student) enrolled " +
                    enrollStudent.getCreditsEnrolled() +
                    " credits: tuition due: $" +
                    decimalFormat.format(tuitionDue) + "\n");
        }
    }

    private void tuitionTriState(Student student,
                                 EnrollStudent enrollStudent,
                                 DecimalFormat decimalFormat,
                                 double tuitionDue) {
        resultField.appendText("" + student.getProfile() + " (Tri-state " +
                ((TriState) student).getState() + ") enrolled " +
                enrollStudent.getCreditsEnrolled() +
                " credits: tuition due: $" +
                decimalFormat.format(tuitionDue) + "\n");
    }

    private void tuitionNonResident(Student student,
                                    EnrollStudent enrollStudent,
                                    DecimalFormat decimalFormat,
                                    double tuitionDue) {
        resultField.appendText(
                "" + student.getProfile() + " (Non-Resident) " +
                        "enrolled " + enrollStudent.getCreditsEnrolled() +
                        " " +
                        "credits: tuition due: $" +
                        decimalFormat.format(tuitionDue) + "\n");
    }

    private void tuitionResident(Student student,
                                 EnrollStudent enrollStudent,
                                 DecimalFormat decimalFormat,
                                 double tuitionDue) {
        resultField.appendText("" + student.getProfile() + " (Resident) " +
                "enrolled " + enrollStudent.getCreditsEnrolled() + " " +
                "credits: tuition due: $" + decimalFormat.
                format(tuitionDue) + "\n");
    }

    @FXML
    void printEligibleGraduates(ActionEvent event) {
        updateCredits(roster, enrollment);

        resultField.appendText(
                "** list of students eligible for graduation "
                        + "**" + "\n");
        for (int i = 0; i < roster.getSize(); i++) {
            Student student = roster.getRoster()[i];
            if (student.creditCompleted >= 120) {
                if (student instanceof International) {
                    printInternationalGraduate(student);
                } else if (student instanceof TriState) {
                    printTriStateGraduate(student);
                } else {
                    resultField.appendText(
                            student.getProfile().toString() +
                                    " (" +
                                    student.getMajor().getCoreCode() +
                                    " " +
                                    student.getMajor().name() + " " +
                                    student.getMajor().getSchool() +
                                    ") credits completed: " +
                                    student.creditCompleted + "(" +
                                    student.getStanding().name() + ")(" +
                                    student.getClass().getSimpleName().
                                            toLowerCase() + ")" + "\n");
                }
            }
        }
    }

    private void updateCredits(Roster roster, Enrollment enrollment) {
        for (int i = 0; i < roster.getSize(); i++) {
            Student student = roster.getRoster()[i];
            EnrollStudent enrollStudent =
                    new EnrollStudent(student.getProfile());
            if (enrollment.contains(enrollStudent)) {
                int index = enrollment.find(enrollStudent);
                student.creditCompleted += enrollment.getEnrollStudents()
                        [index].getCreditsEnrolled();
            }
        }
        resultField.appendText(
                "Credit completed has been updated." + "\n");
    }

    private void printInternationalGraduate(Student student) {
        resultField.appendText(student.getProfile().toString() + " (" +
                student.getMajor().getCoreCode() + " " +
                student.getMajor().name() + " " +
                student.getMajor().getSchool() +
                ") credits completed: " + student.creditCompleted +
                " (" + student.getStanding().name() +
                ")(non" + "-resident)(" + student.getClass().
                getSimpleName().toLowerCase() + ")" + "\n");
    }

    private void printTriStateGraduate(Student student) {
        resultField.appendText(student.getProfile().toString() + " (" +
                student.getMajor().getCoreCode() + " " +
                student.getMajor().name() + " " + student.getMajor().
                getSchool() + ") credits completed: " +
                student.creditCompleted + " (" + student.getStanding().
                name() + ")(non" + "-resident)(" + "tri-state" + ":" +
                ((TriState) student).getState() + ")" + "\n");
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.show();
    }

}

