package project2;


/**
 * This class forms a roster based on the Students, Profiles,
 * Date, Majors, and Standings
 * @author zainchalisa
 * @author nanaafriyie
 */
public class Roster {
    private Student[] roster;
    private int size;
    public static final int ARRAY_GROWTH = 4;
    public static final int NOT_FOUND = -1;
    public static final int GREATER = 1;
    public static final int EQUAL = 0;

    /**
     * This constructor is used to create an starting array for the roster
     */
    public Roster() {
        this.roster = new Student[4];
        this.size = 0;
    }

    /**
     * Getter method used to get the roster storing the students
     * @return returns the roster array
     */
    public Student[] getRoster() {
        return roster;
    }

    /**
     * Getter method which gets the size of the roster
     * @return returns the size of the array
     */
    public int getSize() {
        return size;
    }

    /**
     * This method finds the index for the student you're looking for
     * @param student student you're looking for in the roster array
     * @return returns the index the student is located in the roster
     */
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

    /**
     * This constructor is used to grow the array by 4 when it
     * reaches max
     * capacity
     */
    private void grow() { //increase the array capacity by 4
        Student[] newRoster = new Student[roster.length + ARRAY_GROWTH];
        for (int i = 0; i < size; i++) {
            newRoster[i] = this.roster[i];
        }
        this.roster = newRoster;
    }
    /**
     * This method is used to validate if the student can and should be
     * added to the array. This is based off the size and if the student
     * is already in the roster.
     * @param student object which the array is made up of
     * @return returns the if the student was added to the roster
     */
    public boolean add(Student student) {

        if (size >= roster.length) {
            grow();
        }

        if (find(student) != NOT_FOUND) {
            return false;
        }

        roster[size] = student;
        size++;

        return true;
    }

    /**
     * This method is used to remove the student from the roster and also
     * reorganize the array after the student has been removed.
     * @param student object which is being removed from the array
     * @return returns if the student was removed from the array
     */
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

    /**
     * This method checks if the student is in the roster
     * @param student the object which we're looking for in the roster
     * @return returns true if the student is in the roster and false if
     * not
     */
    public boolean contains(Student student) { //if the student is in
        // roster
        if (find(student) == NOT_FOUND) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Overrides the toString() method for the Roster class
     * @return returns the student proceeding with a new line for the next
     */
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

    /**
     * Sorts the list by first name, last name, and DOB
     * @param roster the array holding all the different students
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

    /**
     * This method sorts the roster by last name, first name , and DOB
     * @param roster the roster of students
     */
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

    /**
     * This method sorts the students by their majors in alphabetical order
     * @param roster the roster array of students
     */
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

    /**
     * This method sorts the students by standing
     * @param roster the roster array of students being sorted
     */
    public void insertionSortStanding(Student[] roster) {
        for (int i = 0; i < size; i++) {
            int j = i;
            while (j > 0){
                if(roster[j - 1].getStanding().name().compareTo(roster[j].
                        getStanding().name()) > GREATER){
                    Student temp = roster[j];
                    roster[j] = roster[j - 1];
                    roster[j - 1] = temp;
                }

                j--;
            }
        }
    }

    /**
     * This constructor prints the student by first name, last name, DOB
     */
    public void print() { //print roster sorted by profiles
        if (size == 0) {
            System.out.println("Student roster is empty!");
            return;
        }
        insertionSort(roster);
        System.out.println("* Student roster sorted by last name, first " +
                "name, DOB **");
        for (int i = 0; i < size; i++) {
            System.out.println(roster[i]);
        }
        System.out.println("* end of roster **");
    }

    /**
     * This constructor prints the roster in order of majors
     */
    public void printBySchoolMajor() {
        if (size == 0) {
            System.out.println("Student roster is empty!");
            return;
        }
        insertionSortMajor(roster);
        System.out.println("* Student roster sorted by school, major **");
        for (int i = 0; i < size; i++) {
            System.out.println(roster[i]);
        }
        System.out.println("* end of roster **");
    }

    /**
     * This constructor prints the roster in order of standings
     */
    public void printByStanding() {
        if (size == 0) {
            System.out.println("Student roster is empty!");
            return;
        }
        insertionSortStanding(roster);
        System.out.println("* Student roster sorted by standing **");
        for (int i = 0; i < size; i++) {
            System.out.println(roster[i]);
        }
        System.out.println("* end of roster **");
    }
}
