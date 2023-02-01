// need to add packages to classes

import java.util.Scanner;

public class RosterManager {

    public void run() { // will need to Scanner class to read input, either StringTokenizer or String.split() to break up command line args
        System.out.println("Roster Manager running...");
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        String [] line = command.split("\\s+");
        for ( String dataToken : line ) {
            System.out.println(dataToken);
        }
    }
}