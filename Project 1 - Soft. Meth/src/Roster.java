import java.sql.SQLOutput;

public class Roster {
    private Student[] roster;
    private int size;
    public static final int ARRAY_GROWTH = 4;
    public static final int NOT_FOUND = -1;

    public static final int GREATER = 1;
    public static final int SMALLER = -1;
    public static final int EQUAL = 0;


    public Roster() {
        this.roster = new Student[4];
        this.size = 0;
    }

    public Student[] getRoster() {
        return roster;
    }

    public int getSize() {
        return size;
    }

    public int find(Student student) { //search the given student in roster
        int studentFinder = 0;
        for (int i = 0; i < size; i++) {
            if (student.equals(roster[i])) {
                studentFinder = i;
                return studentFinder;
            }
        }
        return NOT_FOUND;
    }

    private void grow() { //increase the array capacity by 4
        Student[] newRoster = new Student[roster.length + ARRAY_GROWTH];
        for (int i = 0; i < size; i++) {
            newRoster[i] = this.roster[i];
        }
        this.roster = newRoster;
    }

    public boolean add(Student student) { //add student to end of array

        if (size >= roster.length) {
            grow();
        }

        if (find(student) != NOT_FOUND) {
            return false; // it already is in the roster
        }


        roster[size] = student;
        size++;

        return true; // added it to the roster
    }

    public boolean remove(Student student) { //maintain the order after
        // remove

        if (find(student) == NOT_FOUND) {
            return false;
        }

        int studentIndex = find(student);

        for (int i = studentIndex; i < size - 1; i++) {
            if (i + 1 < size) {
                roster[i] = roster[i + 1];
            } else {
                roster[size] = null;
            }

        }
        size--;
        return true;
    }

    public boolean contains(Student student) { //if the student is in
        // roster
        if (find(student) == NOT_FOUND) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public String toString() {

        String students = "";

        for (Student student : roster) {
            if (student != null) {
                students += student.toString() + "\n";
            }
        }
        return students;
    }
/*
    public void listBySchoolMethod(String school) {

        Student[] sortedSchoolArray = new Student[size];
        int counter = 0;

        for (int i = 0; i < size; i++) {
            if (roster[i].getMajor().getSchool().
                    compareTo(school) == EQUAL) {
                sortedSchoolArray[counter] = roster[i];
                counter++;
            }
        }
        insertionSortList(sortedSchoolArray);
        for (int i = 0; i < counter; i++) {
            System.out.println(sortedSchoolArray[i]);
        }
    }

 */

    public void insertionSortList(Student[] roster) {
        for (int i = 0; i < roster.length; i++) {
            int j = i;
            if (roster[j] != null) {
                while (j > 0 && roster[j - 1].
                        compareTo(roster[j]) == GREATER) {
                    Student temp = roster[j];
                    roster[j] = roster[j - 1];
                    roster[j - 1] = temp;
                    j--;
                }
            }
        }
    }

    public void insertionSort(Student[] roster) {
        for (int i = 0; i < size; i++) {
            int j = i;
            while (j > 0 && roster[j - 1].compareTo(roster[j]) == GREATER){
                Student temp = roster[j];
                roster[j] = roster[j - 1];
                roster[j - 1] = temp;
                j--;
            }
        }
    }

    public void insertionSortMajor(Student[] roster) {
        for (int i = 0; i < size; i++) {
            int j = i;
            while (j > 0) {
                if (roster[j - 1].getMajor().getSchool().compareTo
                        (roster[j].getMajor().getSchool()) > EQUAL) {
                    Student temp = roster[j];
                    roster[j] = roster[j - 1];
                    roster[j - 1] = temp;
                }
                if (roster[j - 1].getMajor().getSchool().compareTo
                        (roster[j].getMajor().getSchool()) == EQUAL &&
                        roster[j - 1].getMajor().name().compareTo
                                (roster[j].getMajor().name()) > EQUAL) {
                    Student temp = roster[j];
                    roster[j] = roster[j - 1];
                    roster[j - 1] = temp;
                }
                j--;
            }
        }
    }


    public void insertionSortStanding(Student[] roster) {
        for (int i = 0; i < size; i++) {
            int j = i;
            while (j > 0 && roster[j - 1].getStanding().name().
                    compareTo(roster[j].getStanding().name()) > GREATER) {
                Student temp = roster[j];
                roster[j] = roster[j - 1];
                roster[j - 1] = temp;
                j--;
            }
        }
    }

    public void print() { //print roster sorted by profiles
        if (size == 0) {
            System.out.println("Student roster is empty!");
        }
        insertionSort(roster);
        for (int i = 0; i < size; i++) {
            System.out.println(roster[i]);
        }
    }

    public void printBySchoolMajor() { //print roster sorted by school
        // major
        insertionSortMajor(roster);
        for (int i = 0; i < size; i++) {
            System.out.println(roster[i]);
        }
    }

    public void printByStanding() { //print roster sorted by standing
        insertionSortStanding(roster);
        for (int i = 0; i < size; i++) {
            System.out.println(roster[i]);
        }
    }
}

/*
A Zain Chalisa 01/02/2002 CS 30
A Andrew Chacko 01/04/2002 CS 60
A Abhitej Bokka 09/10/2002 CS 20
A Akash Shah 12/18/2002 BAIT 3
A Kush Patel 04/17/2002 MATH 120
A Vivek Kumar 03/12/2002 ITI 55
A Humpty Dumpty 04/02/2002 EE 77
 */