package project2;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

/**
 * This class creates a user interface to process command line arguments
 * entered in the terminal and display results
 * @author zainchalisa
 * @author nanaafriyie
 */
public class TuitionManager {

    /**
     * This method adds a student to the roster.
     * @param roster object to hold a list of students
     * @param inputLine command line arguments from user input to access
     * student information
     */

    private void addResident(Roster roster,String[] inputLine) {
        if (inputLine.length != 6) {
            System.out.println("Missing data in command line.");
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
                        if (isValidCreditString(creditsCompletedString)) {
                            int creditsCompleted = Integer.
                                    parseInt(creditsCompletedString);
                            if (creditsCompleted >= 0) {
                                Student student =
                                        new Resident(new Profile(lastName
                                                , firstName,
                                                new Date(dateOfBirth)),
                                                majorName,
                                                creditsCompleted,defaultScholarship);
                                roster.add(student);
                                System.out.println(firstName + " " +
                                        lastName + " " + dateOfBirth +
                                        " added to the roster.");
                            } else {
                                System.out.println("Credits completed " +
                                        "invalid: cannot be negative!");
                            }
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

    private void addNonResident(Roster roster,String[] inputLine) {
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
                        if (isValidCreditString(creditsCompletedString)) {
                            int creditsCompleted = Integer.
                                    parseInt(creditsCompletedString);
                            if (creditsCompleted >= 0) {
                                Student student =
                                        new NonResident(new Profile(lastName
                                                , firstName,
                                                new Date(dateOfBirth)),
                                                majorName,
                                                creditsCompleted);
                                roster.add(student);
                                System.out.println(firstName + " " +
                                        lastName + " " + dateOfBirth +
                                        " added to the roster.");
                            } else {
                                System.out.println("Credits completed " +
                                        "invalid: cannot be negative!");
                            }
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

    private void addTriState(Roster roster, String[] inputLine) {
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

        if (state.equalsIgnoreCase("NY") || state.equalsIgnoreCase("CT")){
            studentProfile.setState(state);
        } else {
            System.out.println(state + ": Invalid state code.");
            return;
        }

        // Can make this whole block a method because it's reused 4 times
        if (dob.isValid()) {
            if (dob.isValidStudent()) {
                if (!roster.contains(studentProfile)) {
                    Major majorName = checkMajor(major);
                    if (majorName != null) {
                        if (isValidCreditString(creditsCompletedString)) {
                            int creditsCompleted = Integer.
                                    parseInt(creditsCompletedString);
                            if (creditsCompleted >= 0) {
                                Student student =
                                        new TriState(new Profile(lastName
                                                , firstName,
                                                new Date(dateOfBirth)),
                                                majorName,
                                                creditsCompleted,state);
                                roster.add(student);
                                System.out.println(firstName + " " +
                                        lastName + " " + dateOfBirth +
                                        " added to the roster.");
                            } else {
                                System.out.println("Credits completed " +
                                        "invalid: cannot be negative!");
                            }
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

    private void addInternational(Roster roster, String[] inputLine) {
        if (inputLine.length < 6) { // replace magic number
            System.out.println("Missing data in command line.");
            return;
        }
        String firstName = inputLine[1];
        String lastName = inputLine[2];
        String dateOfBirth = inputLine[3];
        String major = inputLine[4];
        String creditsCompletedString = inputLine[5];
        String studyAbroad = null;
        try {
            studyAbroad = inputLine[6];
        } catch (ArrayIndexOutOfBoundsException e) {
            studyAbroad = "false";
        }
        boolean isStudyAbroad = Boolean.parseBoolean(studyAbroad);
        International studentProfile = new International(new Profile(lastName,
                firstName, new Date(dateOfBirth)));
        Date dob = studentProfile.getProfile().getDob();

        if (dob.isValid()) {
            if (dob.isValidStudent()) {
                if (!roster.contains(studentProfile)) {
                    Major majorName = checkMajor(major);
                    if (majorName != null) {
                        if (isValidCreditString(creditsCompletedString)) {
                            int creditsCompleted = Integer.
                                    parseInt(creditsCompletedString);
                            if (creditsCompleted >= 0) {
                                Student student =
                                        new International(new Profile(lastName
                                                , firstName,
                                                new Date(dateOfBirth)),
                                                majorName,
                                                creditsCompleted,isStudyAbroad);
                                roster.add(student);
                                System.out.println(firstName + " " +
                                        lastName + " " + dateOfBirth +
                                        " added to the roster.");
                            } else {
                                System.out.println("Credits completed " +
                                        "invalid: cannot be negative!");
                            }
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

    private void enrollStudent(Roster roster, String[] inputLine, Enrollment enrollment) {
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
            Student student = new International(new Profile(lastName,firstName,new Date(dateOfBirth)));
            if (roster.contains(student)) {
                int studentIndex = roster.find(student);
                student = roster.getRoster()[studentIndex];
                if (student.isValid(creditsEnrolled)) { // each valid class must return error message
                    EnrollStudent newStudent = new EnrollStudent(new Profile(lastName,firstName,new Date(dateOfBirth)),creditsEnrolled);
                    enrollment.add(newStudent);
                    System.out.println(firstName + " " + lastName + " " + dateOfBirth + " enrolled " + creditsEnrolled + " credits");
                } else {
                    // some error message if credits arent valid for that kind of student
                }
            } else {
                System.out.println("Cannot enroll: " + firstName + " " + lastName + " " + dateOfBirth + " is not in the roster.");
            }
        } else {
            System.out.println("Credits enrolled is not an integer.");
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
     * @param creditsCompletedString string entered by user to show how
     * many credits student has
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
     * @param roster object to hold a list of students
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
     * @param roster object to hold a list of students
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
     * @param roster object to hold a list of students
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

    /**
     * Checks if credits enter by user are non-negative
     * @param roster object to hold a list of students
     * @param firstName string entered by user for the student's first name
     * @param lastName string entered by user for the student's last name
     * @param dateOfBirth string entered by user for the student's DOB
     * @param creditsCompleted number of credits entered by user
     * @param majorName major of student entered by user
     */

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

    public void loadRoster(Roster roster){
        try{
            Scanner fileScanner = new Scanner(new File("Project 2 - Soft. Meth/src/project2/studentList.txt"));
            while(fileScanner.hasNextLine()){
                String line = fileScanner.nextLine();
                String[] inputLine = line.split(",");
                String command = inputLine[0];
                if(command.equals("R")){
                    addResident(roster, inputLine);
                } else if(command.equals("I")){
                    addInternational(roster, inputLine);
                } else if (command.equals("T")){
                    addTriState(roster, inputLine);
                } else if (command.equals("N")){
                    addNonResident(roster, inputLine);
                }
            }
            fileScanner.close();
        } catch(FileNotFoundException e){
            System.out.println("** File not found **");
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

        while (!dataToken.equals("Q")) {
            dataToken = scanner.nextLine();
            String[] inputLine = dataToken.split("\\s+");
            String command = inputLine[0];
            if (command.equals("AR")) {
                addResident(roster, inputLine);
            } else if (command.equals("AN")) {
                addNonResident(roster,inputLine);
            } else if (command.equals("AT")) {
                addTriState(roster,inputLine);
            } else if (command.equals("AI")){
                addInternational(roster,inputLine);
            } else if (command.equals("R")) {
                removeStudent(roster, inputLine);
            } else if (command.equals("E")) {
                enrollStudent(roster,inputLine, enrollment);
            } else if (command.equals("P")) {
                roster.print();
            } else if (command.equals("PS")) {
                roster.printByStanding();
            } else if (command.equals("PC")) {
                roster.printBySchoolMajor();
            } else if (command.equals("L")) {
                listSchool(roster,inputLine);
            } else if (command.equals("C")) {
                //changeMajor(roster, inputLine);
            } else if (command.equals("C")) {
                changeMajor(roster, inputLine);
            } else if (command.equals("LS")) {
                loadRoster(roster);
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

