// need to add packages to classes

import java.util.Scanner;

public class RosterManager {


    private void checkMajor(String major, Major majorName) { // finish helper method
        if ( major.equals("CS") ) {
            majorName = Major.CS;
        }
        else if (major.equals("MATH")) {
            majorName = Major.MATH;
        }
        else if (major.equals("EE")) {
            majorName = Major.EE;
        }
        else if (major.equals("ITI")) {
            majorName = Major.ITI;
        }
        else if (major.equals("BAIT")) {
            majorName = Major.BAIT;
        }
    }


    public void run() { // will need to Scanner class to read input, either StringTokenizer or String.split() to break up command line args
        System.out.println("Roster Manager running...");
        Scanner scanner = new Scanner(System.in);
        String dataToken = scanner.nextLine();
        String [] line = dataToken.split("\\s+");

        String command = line[0];
        String firstName = line[1];
        String lastName = line[2];
        String dateOfBirth = line[3];
        String major = line[4];

        Major majorName = null;

        checkMajor(major,majorName);

        String creditCompleted = line[5];
        int tokenCount = 0;
        while ( tokenCount < line.length ) {
            if ( line[0].equals("A") ) {
                Student student = new Student(new Profile(lastName,firstName,new Date(dateOfBirth)),null,Integer.parseInt(creditCompleted));
                //Roster.add(student);
            }
            else if ( line[0].equals("R") ) {
                System.out.println("Run command R");
            }
            else if ( line[0].equals("P") ) {
                System.out.println("Run command P");
            }
            else if ( line[0].equals("PS") ) {
                System.out.println("Run command PS");
            }
            else if ( line[0].equals("PC") ) {
                System.out.println("Run command PC");
            }
            else if ( line[0].equals("L") ) {
                System.out.println("Run command PC");
            }
            else if ( line[0].equals("C") ) {
                System.out.println("Run command PC");
            }
            else if ( line[0].equals("Q") ) {
                System.out.println("Roster Manager terminated.");
                break;
            }
            else {
                System.out.println("Error, command not valid.");
            }
            tokenCount++;
        }

    }
}