// need to add packages to classes

import java.util.Scanner;

public class RosterManager {

    public void run() { // will need to Scanner class to read input, either StringTokenizer or String.split() to break up command line args
        System.out.println("Roster Manager running...");
        Scanner scanner = new Scanner(System.in);
        String dataToken = scanner.nextLine();
        String [] line = dataToken.split("\\s+");

        //String command

        for ( String word : line ) {
            if ( line[0].equals("A") ) {
                //Student student = new Student(new Profile(line[2],line[1],))
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
        }

    }
}