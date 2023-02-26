package project2;

import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.io.File;

/**
 * This class creates a user interface to process command line arguments
 * entered in the terminal and display results
 *
 * @author zainchalisa
 * @author nanaafriyie
 */
public class TuitionManager {

    /**
     * This method adds a student to the roster.
     *
     * @param roster    object to hold a list of students
     * @param inputLine command line arguments from user input to access
     *                  student information
     */



    private void addResident(Roster roster, String[] inputLine,
                             boolean printRoster) {
        if (inputLine.length != 6) {
            System.out.println("Missing data in line command.");
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

        if (dob.isValid()) {
            if (dob.isValidStudent()) {
                if (!roster.contains(studentProfile)) {
                    Major majorName = checkMajor(major);
                    if (majorName != null) {
                        validResident(roster,firstName,lastName,
                                dateOfBirth,majorName,
                                creditsCompletedString,defaultScholarship,
                                printRoster);
                    }
                } else {
                    System.out.println(firstName + " " + lastName + " " +
                            dateOfBirth + " is already in the roster.");
                }
            } else {
                System.out.println("DOB invalid: " + dob + " younger " +
                        "than 16 years old.");
            }
        } else {
            System.out.println("DOB invalid: " + dob + " not a valid " +
                    "calendar date!");
        }
    }

    private void validResident(Roster roster, String firstName,
                               String lastName, String dateOfBirth,
                               Major majorName,
                               String creditsCompletedString,
                               int defaultScholarship,
                               boolean printRoster) {
        if (isValidCreditString(creditsCompletedString)) {
            int creditsCompleted = Integer.
                    parseInt(creditsCompletedString);
            if (creditsCompleted >= 0) {
                Student student =
                        new Resident(new Profile(lastName
                                , firstName,
                                new Date(dateOfBirth)),
                                majorName,
                                creditsCompleted,
                                defaultScholarship);
                roster.add(student);
                if (printRoster) {
                    System.out.println(firstName + " " +
                            lastName + " " + dateOfBirth +
                            " added to the roster.");
                }

            } else {
                System.out.println("Credits completed " +
                        "invalid: cannot be negative!");
            }
        } else {
            System.out.println("Credits completed " +
                    "invalid: not an integer!");
        }
    }

    private void addNonResident(Roster roster, String[] inputLine,
                                boolean printRoster) {
        if (inputLine.length != 6) {
            System.out.println("Missing data in command line.");
            return;
        }
        String firstName = inputLine[1];
        String lastName = inputLine[2];
        String dateOfBirth = inputLine[3];
        String major = inputLine[4];
        String creditsCompletedString = inputLine[5];
        NonResident studentProfile = new NonResident(new Profile(lastName,
                firstName, new Date(dateOfBirth)));
        Date dob = studentProfile.getProfile().getDob();

        if (dob.isValid()) {
            if (dob.isValidStudent()) {
                if (!roster.contains(studentProfile)) {
                    Major majorName = checkMajor(major);
                    if (majorName != null) {
                        validNonResident(roster,firstName,lastName,
                                dateOfBirth,majorName,
                                creditsCompletedString,printRoster);
                    }
                } else {
                    System.out.println(firstName + " " + lastName + " " +
                            dateOfBirth + " is already in the roster.");
                }
            } else {
                System.out.println("DOB invalid: " + dob + " younger " +
                        "than 16 years old.");
            }
        } else {
            System.out.println("DOB invalid: " + dob + " not a valid " +
                    "calendar date!");
        }
    }

    private void validNonResident(Roster roster, String firstName,
                                  String lastName, String dateOfBirth,
                                  Major majorName,
                                  String creditsCompletedString,
                                  boolean printRoster) {
        if (isValidCreditString(creditsCompletedString)) {
            int creditsCompleted = Integer.
                    parseInt(creditsCompletedString);
            if (creditsCompleted >= 0) {
                Student student =
                        new NonResident(
                                new Profile(lastName
                                        , firstName,
                                        new Date(
                                                dateOfBirth
                                        )),
                                majorName,
                                creditsCompleted);
                roster.add(student);
                if (printRoster) {
                    System.out.println(firstName + " " +
                            lastName + " " + dateOfBirth +
                            " added to the roster.");
                }

            } else {
                System.out.println("Credits completed " +
                        "invalid: cannot be negative!");
            }
        } else {
            System.out.println("Credits completed " +
                    "invalid: not an integer!");
        }
    }

    private void addTriState(Roster roster, String[] inputLine,
                             boolean printRoster) {
        if (inputLine.length != 7) {
            if (inputLine.length == 6) {
                System.out.println("Missing the state code.");
                return;
            }
            System.out.println("Missing data in command line.");
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

        if (dob.isValid()) {
            if (dob.isValidStudent()) {
                if (!roster.contains(studentProfile)) {
                    Major majorName = checkMajor(major);
                    if (majorName != null) {
                        validTriState(roster,firstName,lastName,
                                dateOfBirth,majorName,
                                creditsCompletedString,state,
                                printRoster,studentProfile);
                    }
                } else {
                    System.out.println(firstName + " " + lastName + " " +
                            dateOfBirth + " is already in the roster.");
                }
            } else {
                System.out.println("DOB invalid: " + dob + " younger " +
                        "than 16 years old.");
            }
        } else {
            System.out.println("DOB invalid: " + dob + " not a valid " +
                    "calendar date!");
        }
    }

    private void validTriState(Roster roster, String firstName,
                                  String lastName, String dateOfBirth,
                                  Major majorName,
                                  String creditsCompletedString,
                               String state, boolean printRoster,
                               TriState studentProfile) {
        if (state.equalsIgnoreCase("NY") ||
                state.equalsIgnoreCase("CT")) {
            studentProfile.setState(state);
        } else {
            System.out.println(state + ": Invalid state code.");
            return;
        }
        if (isValidCreditString(creditsCompletedString)) {
            int creditsCompleted = Integer.
                    parseInt(creditsCompletedString);
            if (creditsCompleted >= 0) {
                Student student =
                        new TriState(new Profile(lastName
                                , firstName,
                                new Date(dateOfBirth)),
                                majorName,
                                creditsCompleted, state);
                roster.add(student);
                if (printRoster) {
                    System.out.println(firstName + " " +
                            lastName + " " + dateOfBirth +
                            " added to the roster.");
                }
            } else {
                System.out.println("Credits completed " +
                        "invalid: cannot be negative!");
            }
        } else {
            System.out.println("Credits completed " +
                    "invalid: not an integer!");
        }
    }

    private void addInternational(Roster roster, String[] inputLine,
                                  boolean printRoster) {
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
                new International(new Profile(lastName,
                        firstName, new Date(dateOfBirth)));
        Date dob = studentProfile.getProfile().getDob();

        if (dob.isValid()) {
            if (dob.isValidStudent()) {
                if (!roster.contains(studentProfile)) {
                    Major majorName = checkMajor(major);
                    if (majorName != null) {
                        validInternational(roster,firstName,lastName,
                                dateOfBirth,majorName,
                                creditsCompletedString,isStudyAbroad,
                                printRoster);
                    }
                } else {
                    System.out.println(firstName + " " + lastName + " " +
                            dateOfBirth + " is already in the roster.");
                }
            } else {
                System.out.println("DOB invalid: " + dob + " younger " +
                        "than 16 years old.");
            }
        } else {
            System.out.println("DOB invalid: " + dob + " not a valid " +
                    "calendar date!");
        }
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

    private void validInternational(Roster roster, String firstName,
                                  String lastName, String dateOfBirth,
                                  Major majorName,
                                    String creditsCompletedString,
                                  boolean isStudyAbroad,
                                  boolean printRoster) {
        if (isValidCreditString(creditsCompletedString)) {
            int creditsCompleted = Integer.
                    parseInt(creditsCompletedString);
            if (creditsCompleted >= 0) {
                Student student =
                        new International(
                                new Profile(lastName
                                        , firstName,
                                        new Date(
                                                dateOfBirth)),
                                majorName,
                                creditsCompleted,
                                isStudyAbroad);
                roster.add(student);
                if (printRoster) {
                    System.out.println(firstName + " " +
                            lastName + " " + dateOfBirth +
                            " added to the roster.");
                }

            } else {
                System.out.println("Credits completed " +
                        "invalid: cannot be negative!");
            }
        } else {
            System.out.println("Credits completed " +
                    "invalid: not an integer!");
        }
    }

    private void enrollStudent(Roster roster, String[] inputLine,
                               Enrollment enrollment) {
        if (inputLine.length != 5) {
            System.out.println("Missing data in command line.");
            return;
        }
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
                if (student.isValid(creditsEnrolled)) { // each valid
                    // class must return error message
                    EnrollStudent newStudent =
                            new EnrollStudent(new Profile(lastName,
                                    firstName, new Date(dateOfBirth)),
                                    creditsEnrolled);
                    if (enrollment.contains(newStudent)) {
                        enrollment.getEnrollStudent(newStudent)
                                .setCreditsEnrolled(creditsEnrolled);
                    } else {
                        enrollment.add(newStudent);
                    }
                    System.out.println(firstName + " " + lastName + " " +
                            dateOfBirth + " enrolled " + creditsEnrolled +
                            " credits");
                } else if (student.getClass().getSimpleName().equals((
                        "International")) &&
                        ((International) student).isStudyAbroad()) {
                    System.out.println(
                            "(" + student.getClass().getSimpleName() +
                                    " studentstudy abroad) " +
                                    creditsEnrolled +
                                    ": invalid credit hours.");
                } else if (student.getClass().getSimpleName().equals((
                        "International"))) {
                    System.out.println(
                            "(" + student.getClass().getSimpleName() +
                                    "student) " + creditsEnrolled +
                                    ": invalid credit hours.");
                } else {
                    System.out.println(
                            "(" + student.getClass().getSimpleName() +
                                    ") " + creditsEnrolled +
                                    ": invalid credit hours.");
                }
            } else {
                System.out.println(
                        "Cannot enroll: " + firstName + " " + lastName +
                                " " + dateOfBirth +
                                " is not in the roster.");
            }
        } else {
            System.out.println("Credits enrolled is not an integer.");
        }
    }

    private void dropStudent(Roster roster, String[] inputLine,
                             Enrollment enrollment) {
        if (inputLine.length != 4) {
            System.out.println("Missing data in line command.");
            return;
        }
        String firstName = inputLine[1];
        String lastName = inputLine[2];
        String dateOfBirth = inputLine[3];

        EnrollStudent student = new EnrollStudent(new Profile(lastName,
                firstName, new Date(dateOfBirth)));
        if (enrollment.contains(student)) {
            int studentIndex = enrollment.find(student);
            student = enrollment.getEnrollStudents()[studentIndex];
            enrollment.remove(student);
            System.out.println(
                    firstName + " " + lastName + " " + dateOfBirth +
                            " dropped.");
        } else {
            System.out.println(
                    firstName + " " + lastName + " " + dateOfBirth +
                            " is not enrolled.");
        }
    }

    private void grantScholarship(Roster roster, String[] inputLine,
                                  Enrollment enrollment) {
        if (inputLine.length < 4) {
            System.out.println("Missing data in command line.");
            return;
        } // change order of checking input
        String firstName = inputLine[1];
        String lastName = inputLine[2];
        String dateOfBirth = inputLine[3];
        String scholarshipString = null;

        //if (isValidCreditString(scholarshipString)) {
        //int scholarship = Integer.parseInt(scholarshipString);
        Student student = new Resident(new Profile(lastName, firstName,
                new Date(dateOfBirth)));
        if (roster.contains(student)) {
            int studentIndex = roster.find(student);
            student = roster.getRoster()[studentIndex];
            if (student instanceof Resident) {
                EnrollStudent checkStudent =
                        new EnrollStudent(new Profile(lastName,
                                firstName, new Date(dateOfBirth)));
                int index = enrollment.find(checkStudent);
                checkStudent = enrollment.getEnrollStudents()[index];
                if (!checkStudent.isPartTime()) {
                    try {
                        scholarshipString = inputLine[4];
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Missing data in command line" +
                                ".");
                        return;
                    }
                    if (isValidCreditString(scholarshipString)) {
                        int scholarship =
                                Integer.parseInt(scholarshipString);
                        if (((Resident) student).isValidScholarship(
                                scholarship)) {
                            ((Resident) student).setScholarship(
                                    scholarship);
                            System.out.println(
                                    firstName + " " + lastName + " " +
                                            dateOfBirth +
                                            ": scholarship amount " +
                                            "updated.");
                        } else {
                            System.out.println(scholarship + ": invalid " +
                                    "amount.");
                        }
                    } else {
                        System.out.println("Amount is not an integer.");
                    }
                } else {
                    System.out.println(firstName + " " + lastName + " "
                            + dateOfBirth + " part time student is not " +
                            "eligible for the scholarship.");
                }
            } else {
                if (student instanceof NonResident) {
                    if (student.getClass().getSimpleName().equals(
                            "NonResident")) {
                        System.out.println(firstName + " " + lastName +
                                " " +
                                dateOfBirth + " (" + "Non-Resident" + ")" +
                                " is not eligible for the scholarship.");
                    }
                } else {
                    System.out.println(firstName + " " + lastName + " " +
                            dateOfBirth + " (" +
                            student.getClass().getSimpleName() +
                            ") is not eligible for the scholarship.");
                }
            }
        } else {
            System.out.println(
                    firstName + " " + lastName + " " + dateOfBirth +
                            " is not in the roster.");
        }
    }

    /* Helper method to check tokens
    private boolean isValidTokens(String[] inputLine) {
        String command = inputLine[0];
        if (command.equals("AR") || command.equals("AN")) {
            try {
                String firstName = inputLine[1];
                String lastName = inputLine[2];
                String dateOfBirth = inputLine[3];
                String major = inputLine[4];
                String creditsCompletedString = inputLine[5];
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Missing data in command line.");
                return false;
            }
        } else if (command.equals("AT")) {
            try {
                String firstName = inputLine[1];
                String lastName = inputLine[2];
                String dateOfBirth = inputLine[3];
                String major = inputLine[4];
                String creditsCompletedString = inputLine[5];
                String state = inputLine[6];
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Missing data in command line.");
                return false;
            }
        }
        return true;
    }

    /*
    private void addStudent(Roster roster, String[] inputLine) {
        String firstName = inputLine[1];
        String lastName = inputLine[2];
        String dateOfBirth = inputLine[3];
        String major = inputLine[4];
        String creditsCompletedString = inputLine[5];
        //Student studentProfile = new Student(new Profile(lastName,
          //      firstName, new Date(dateOfBirth)));
        Date dob = studentProfile.getProfile().getDob();

        if (dob.isValid()) {
            if (dob.isValidStudent()) {
                if (!roster.contains(studentProfile)) {
                    Major majorName = checkMajor(major);
                    if (majorName != null) {
                        if (isValidCreditString(creditsCompletedString)) {
                            int creditsCompleted = Integer.
                                    parseInt(creditsCompletedString);
                            isValidCredit(roster,firstName,lastName,
                                    dateOfBirth,creditsCompleted,
                                    majorName);
                        } else {
                            System.out.println("Credits completed " +
                                    "invalid: not an integer!");
                        }
                    }
                } else {
                    System.out.println(firstName + " " + lastName + " " +
                            dateOfBirth + " is already in the roster.");
                }
            } else {
                System.out.println("DOB invalid: " + dob + " younger " +
                        "than 16 years old.");
            }
        } else {
            System.out.println("DOB invalid: " + dob + " not a valid " +
                    "calendar date!");
        }
    }

     */

    /**
     * Checks to make sure credits entered by user is an integer
     *
     * @param creditsCompletedString string entered by user to show how
     *                               many credits student has
     * @return returns whether or not credit value entered by user is valid
     */
    private boolean isValidCreditString(String creditsCompletedString) {
        try {
            Integer.parseInt(creditsCompletedString);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Removes student from the roster
     *
     * @param roster    object to hold a list of students
     * @param inputLine command line arguments from user input to access
     *                  student information
     */

    private void removeStudent(Roster roster, String[] inputLine) {
        String firstName = inputLine[1];
        String lastName = inputLine[2];
        String dateOfBirth = inputLine[3];

        Student student = new Resident(new Profile(lastName, firstName,
                new Date(dateOfBirth)));
        if (roster.contains(student)) {
            roster.remove(student);
            System.out.println(firstName + " " + lastName + " " +
                    dateOfBirth + " removed from the roster.");
        } else {
            System.out.println(firstName + " " + lastName + " " +
                    dateOfBirth + " is not in the roster.");
        }
    }

    /**
     * Change the major of a student in the roster
     *
     * @param roster    object to hold a list of students
     * @param inputLine command line arguments from user input to access
     *                  student information
     */

    private void changeMajor(Roster roster, String[] inputLine) {
        String firstName = inputLine[1];
        String lastName = inputLine[2];
        String dateOfBirth = inputLine[3];
        String major = inputLine[4];

        Student student = new Resident(new Profile(lastName, firstName,
                new Date(dateOfBirth)));
        if (roster.contains(student)) {
            int studentIndex = roster.find(student);
            Major majorName = checkMajor(major);
            if (majorName != null) {
                Student rosterStudent = roster.getRoster()[studentIndex];
                rosterStudent.setMajor(majorName);
                System.out.println(firstName + " " + lastName + " " +
                        dateOfBirth + " major changed to " + major);
            }
        } else {
            System.out.println(firstName + " " + lastName + " " +
                    dateOfBirth + " is not in the roster.");
        }
    }

    /**
     * Checks string user inputted for the major and sees if it's valid
     *
     * @param major string entered by user for the major
     * @return returns corresponding major from enum class
     */
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
            System.out.println("Major code invalid: " + major);
        }
        return majorName;
    }

    /**
     * Checks if school enter by user for list command is valid
     *
     * @param school string entered by user to a call the list of a school
     * @return returns true if school entered by student is valid
     */
    private boolean isValidSchool(String school) {
        for (Major major : Major.values()) {
            if (major.getSchool().equalsIgnoreCase(school)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Lists all students in a specific school
     *
     * @param roster    object to hold a list of students
     * @param inputLine command line arguments from user input to access
     *                  student information
     */
    private void listSchool(Roster roster, String[] inputLine) {
        String school = inputLine[1];
        Student[] sortedSchoolArray = new Student[roster.getSize()];
        int counter = 0;
        if (!isValidSchool(school)) {
            System.out.println("School doesn't exist: " + school);
            return;
        }
        for (int i = 0; i < roster.getSize(); i++) {
            if (roster.getRoster()[i].getMajor().getSchool().
                    equalsIgnoreCase(school)) {
                sortedSchoolArray[counter] = roster.getRoster()[i];
                counter++;
            }
        }
        roster.insertionSortList(sortedSchoolArray);
        System.out.println("* Students in " + school + " *");
        for (int i = 0; i < counter; i++) {
            System.out.println(sortedSchoolArray[i]);
        }
        System.out.println("* end of list **");
    }

    // Can't use this method anymore, different types of students
    /*
    private void isValidCredit (Roster roster, String firstName, String
            lastName, String dateOfBirth, int creditsCompleted, Major
            majorName) {
        if (creditsCompleted >= 0) {
            Student student =
                    new Resident(new Profile(lastName
                            , firstName,
                            new Date(dateOfBirth)),
                            majorName,
                            creditsCompleted,0);
            roster.add(student);
            System.out.println(firstName + " " +
                    lastName + " " + dateOfBirth +
                    " added to the roster.");
        } else {
            System.out.println("Credits completed " +
                    "invalid: cannot be negative!");
        }
    }

     */


    private void loadRoster(Roster roster, String[] inputLine,
                            boolean printRoster) {
        printRoster = false;
        try {
            String filename = inputLine[1];
            Scanner fileScanner = new Scanner(new File("Project 2 - Soft" +
                    ". Meth/" + filename));
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] inputLines = line.split(",");
                String command = inputLines[0];
                if (command.equals("R")) {
                    addResident(roster, inputLines, printRoster);
                } else if (command.equals("I")) {
                    addInternational(roster, inputLines, printRoster);
                } else if (command.equals("T")) {
                    addTriState(roster, inputLines, printRoster);
                } else if (command.equals("N")) {
                    addNonResident(roster, inputLines, printRoster);
                }
            }
            fileScanner.close();
            System.out.println("Students loaded to the roster.");
        } catch (FileNotFoundException e) {
            System.out.println("** File not found **");
            System.out.println(e);
        }
    }

    public void printEnrollment(Enrollment enrollment) {
        if (enrollment.getEnrollStudents()[0] == null) {
            System.out.println("Enrollment is empty!");
        } else {
            System.out.println("** Enrollment **");
            for (int i = 0; i < enrollment.getEnrollStudents().length;
                 i++) {
                if (enrollment.getEnrollStudents()[i] != null) {
                    System.out.println(enrollment.getEnrollStudents()[i] +
                            ": credits enrolled: " +
                            enrollment.getEnrollStudents()[i].getCreditsEnrolled());
                }
            }
            System.out.println("* end of enrollment *");
        }

    }

    private void printTuitionDue(Roster roster, Enrollment enrollment) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###.00");
        if (enrollment.getEnrollStudents()[0] != null) {
            System.out.println("** Tuition due **");
            for (EnrollStudent enrollStudent :
                    enrollment.getEnrollStudents()) {
                if (enrollStudent != null) {
                    Student student =
                            roster.getStudent(new NonResident(
                                    enrollStudent.getProfile(), null, 0));
                    double tuitionDue =
                            student.tuitionDue(
                                    enrollStudent.getCreditsEnrolled());
                    if (student.getClass().getSimpleName().equals(
                            "International")) {
                        if (((International) student).isStudyAbroad()) {
                            System.out.println("" + student.getProfile() +
                                    " (International studentstudy " +
                                    "abroad) enrolled " +
                                    enrollStudent.getCreditsEnrolled() +
                                    " credits: tuition due: $" +
                                    decimalFormat.format(tuitionDue));
                        } else {
                            System.out.println("" + student.getProfile() +
                                    " (International student) enrolled " +
                                    enrollStudent.getCreditsEnrolled() +
                                    " credits: tuition due: $" +
                                    decimalFormat.format(tuitionDue));
                        }
                    } else if (student.getClass().getSimpleName()
                            .equals("TriState")) {
                        System.out.println("" + student.getProfile() +
                                " (Tri-state " +
                                ((TriState) student).getState() +
                                ") enrolled " +
                                enrollStudent.getCreditsEnrolled() +
                                " credits: tuition due: $" +
                                decimalFormat.format(tuitionDue));
                    } else if (student.getClass().getSimpleName()
                            .equals("NonResident")) {
                        System.out.println("" + student.getProfile() +
                                " (Non-Resident) enrolled " +
                                enrollStudent.getCreditsEnrolled() +
                                " credits: tuition due: $" +
                                decimalFormat.format(tuitionDue));
                    } else if (student.getClass().getSimpleName()
                            .equals("Resident")) {
                        System.out.println("" + student.getProfile() +
                                " (Resident) enrolled " +
                                enrollStudent.getCreditsEnrolled() +
                                " credits: tuition due: $" +
                                decimalFormat.format(tuitionDue));
                    }
                }
            }
            System.out.println("* end of tuition due *");
        } else {
            System.out.println("Student roster is empty!");
        }

    }

    private void printEligibleGraduates(Roster roster,
                                        Enrollment enrollment) {
        for (int i = 0; i < roster.getSize(); i++) {
            Student student = roster.getRoster()[i];
            EnrollStudent enrollStudent =
                    new EnrollStudent(student.getProfile());
            if (enrollment.contains(enrollStudent)) {
                int index = enrollment.find(enrollStudent);
                student.creditCompleted +=
                        enrollment.getEnrollStudents()[index].getCreditsEnrolled();
            }
        }
        System.out.println("Credit completed has been updated.");

        System.out.println("** list of students eligible for graduation " +
                "**");
        for (int i = 0; i < roster.getSize(); i++) {
            Student student = roster.getRoster()[i];
            if (student.creditCompleted >= 120) {
                if (student instanceof International) {
                    System.out.println(student.getProfile().toString() +
                            " (" + student.getMajor().getCoreCode() + " " +
                            student.getMajor().name() + " " +
                            student.getMajor().getSchool() +
                            ") credits completed: " +
                            student.creditCompleted +
                            " (" + student.getStanding().name() + ")(non" +
                            "-resident)(" +
                            student.getClass().getSimpleName()
                                    .toLowerCase() + ")");
                } else if (student instanceof TriState) {
                    System.out.println(student.getProfile().toString() +
                            " (" + student.getMajor().getCoreCode() + " " +
                            student.getMajor().name() + " " +
                            student.getMajor().getSchool() +
                            ") credits completed: " +
                            student.creditCompleted +
                            " (" + student.getStanding().name() + ")(non" +
                            "-resident)(" +
                            "tri-state" + ":" +
                            ((TriState) student).getState() + ")");
                } else {
                    System.out.println(student.getProfile().toString() +
                            " (" + student.getMajor().getCoreCode() + " " +
                            student.getMajor().name() + " " +
                            student.getMajor().getSchool() +
                            ")credits completed: " +
                            student.creditCompleted +
                            "(" + student.getStanding().name() + ")(" +
                            student.getClass().getSimpleName() + ")");
                }
            }
        }
    }


    /**
     * This method reads input from the command line and executes commands
     * based on the input
     */
    public void run() {
        System.out.println("Tuition Manager running...");
        Scanner scanner = new Scanner(System.in);
        String dataToken = "";
        Roster roster = new Roster();
        Enrollment enrollment = new Enrollment();

        // Can add boolean variable to see if added method successfully
        // added student to roster
        // If so, print the in the run method

        while (!dataToken.equals("Q")) {
            dataToken = scanner.nextLine();
            String[] inputLine = dataToken.split("\\s+");
            String command = inputLine[0];
            boolean printRoster = true;
            if (command.equals("AR")) {
                addResident(roster, inputLine, printRoster);
            } else if (command.equals("AN")) {
                addNonResident(roster, inputLine, printRoster);
            } else if (command.equals("AT")) {
                addTriState(roster, inputLine, printRoster);
            } else if (command.equals("AI")) {
                addInternational(roster, inputLine, printRoster);
            } else if (command.equals("R")) {
                removeStudent(roster, inputLine);
            } else if (command.equals("E")) {
                enrollStudent(roster, inputLine, enrollment);
            } else if (command.equals("P")) {
                roster.print();
            } else if (command.equals("PS")) {
                roster.printByStanding();
            } else if (command.equals("PC")) {
                roster.printBySchoolMajor();
            } else if (command.equals("L")) {
                listSchool(roster, inputLine);
            } else if (command.equals("D")) {
                dropStudent(roster, inputLine, enrollment);
            } else if (command.equals("S")) {
                grantScholarship(roster, inputLine, enrollment);
            } else if (command.equals("SE")) {
                printEligibleGraduates(roster, enrollment);
            } else if (command.equals("C")) {
                changeMajor(roster, inputLine);
            } else if (command.equals("LS")) {
                loadRoster(roster, inputLine, printRoster);
            } else if (command.equals("PE")) {
                printEnrollment(enrollment);
            } else if (command.equals("PT")) {
                printTuitionDue(roster, enrollment);
            } else if (command.equals("")) {
                continue;
            } else if (command.equals("Q")) {
                break;
            } else {
                System.out.println(command + " is an invalid command!");
            }
        }
        System.out.println("Tuition Manager terminated.");
    }
}

