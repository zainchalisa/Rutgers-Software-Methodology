import java.util.Scanner;

public class RosterManager {

    private void addStudent(Roster roster, String[] inputLine) {
        String firstName = inputLine[1];
        String lastName = inputLine[2];
        String dateOfBirth = inputLine[3];
        String major = inputLine[4];
        String creditsCompletedString = inputLine[5];
        Student studentProfile = new Student(new Profile(lastName, firstName, new Date(dateOfBirth)));
        Date dob = studentProfile.getProfile().getDob();

        if (dob.isValid()) {
            if (dob.isValidStudent()) {
                if (!roster.contains(studentProfile)) {
                    Major majorName = checkMajor(major);
                    if (majorName != null) {
                        if (isValidCredit(creditsCompletedString)) {
                            int creditsCompleted = Integer.parseInt(creditsCompletedString);
                            if (creditsCompleted >= 0) {
                                Student student = new Student(new Profile(lastName, firstName, new Date(dateOfBirth)), majorName, creditsCompleted);
                                roster.add(student);
                                System.out.println(firstName + " " + lastName + " " + dateOfBirth + " added to the roster.");
                            } else {
                                System.out.println("Credits completed invalid: cannot be negative!");
                            }
                        } else {
                            System.out.println("Credits completed invalid: not an integer!");
                        }
                    }
                } else {
                    System.out.println(firstName + " " + lastName + " " + dateOfBirth + " is already in the roster.");
                }
            } else {
                System.out.println("DOB invalid: " + dob + " younger than 16 years old.");
            }
        } else {
            System.out.println("DOB invalid: " + dob + " not a valid calendar date!");
        }
    }

    private boolean isValidCredit (String creditsCompletedString) {
        try {
            Integer.parseInt(creditsCompletedString);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void removeStudent(Roster roster, String[] inputLine) {
        String firstName = inputLine[1];
        String lastName = inputLine[2];
        String dateOfBirth = inputLine[3];

        Student student = new Student(new Profile(lastName, firstName, new Date(dateOfBirth)));
        if (roster.contains(student)) {
            roster.remove(student);
            System.out.println(firstName + " " + lastName + " " + dateOfBirth + " removed from the roster.");
        } else {
            System.out.println(firstName + " " + lastName + " " + dateOfBirth + " is not in the roster.");
        }
    }

    private void changeMajor(Roster roster, String[] inputLine) {
        String firstName = inputLine[1];
        String lastName = inputLine[2];
        String dateOfBirth = inputLine[3];
        String major = inputLine[4];

        Student student = new Student(new Profile(lastName, firstName, new Date(dateOfBirth)));
        if (roster.contains(student)) {
            int studentIndex = roster.find(student);
            Major majorName = checkMajor(major);
            if (majorName != null) {
                Student rosterStudent = roster.getRoster()[studentIndex];
                rosterStudent.setMajor(majorName);
                System.out.println(firstName + " " + lastName + " " + dateOfBirth + " major changed to " + major);
            }
        } else {
            System.out.println(firstName + " " + lastName + " " + dateOfBirth + " is not in the roster.");
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
            System.out.println("Major code invalid: " + major);
        }
        return majorName;
    }
    public void run() {
        System.out.println("Roster Manager running...");
        Scanner scanner = new Scanner(System.in);
        String dataToken = "";
        Roster roster = new Roster();

        while (!dataToken.equals("Q")) {
            dataToken = scanner.nextLine();
            String[] inputLine = dataToken.split("\\s+");
            String command = inputLine[0];
            if (command.equals("A")) {
                addStudent(roster,inputLine);
            } else if (command.equals("R")) {
                removeStudent(roster,inputLine);
            } else if (command.equals("P")) {
                roster.print(); // need to add spacing, if roster is empty need to print to terminal
            } else if (command.equals("PS")) {
                roster.printByStanding();
            } else if (command.equals("PC")) {
                roster.printBySchoolMajor();
            } else if (command.equals("L")) {
                System.out.println("Run command L");
            } else if (command.equals("C")) {
                changeMajor(roster,inputLine);
            } else if (command.equals("Q")) {
                break;
            } else {
                System.out.println(command + " is not a valid command!");
            }
        }
        System.out.println("Roster Manager Terminated");
    }
}