package project2;

/**
 * This class implements the enrollment of students into the enroll
 * students array
 *
 * @author zainchalisa
 * @author nanaafriyie
 */
public class Enrollment {
    private EnrollStudent[] enrollStudents;
    private int size;

    public static final int ARRAY_GROWTH = 4;
    public static final int NOT_FOUND = -1;
    public static final int GREATER = 1;
    public static final int EQUAL = 0;

    /**
     * This constructor initializes the enrollment array to hold a
     * beginning size of 4
     */
    public Enrollment() {
        this.enrollStudents = new EnrollStudent[ARRAY_GROWTH];
        this.size = EQUAL;
    }

    /**
     * This method removes a given student from the enrollment array
     *
     * @param enrollStudent is the student being removed from the array
     */
    public void remove(EnrollStudent enrollStudent) {
        int indexHolder = 0;
        if (contains(enrollStudent)) {
            for (int i = 0; i < size; i++) {
                if (enrollStudents[i] == enrollStudent) {
                    indexHolder = i;
                    enrollStudents[i] = null;
                }
            }
        }

        for (int i = indexHolder; i < size - GREATER; i++) {
            if (i + 1 < size) {
                enrollStudents[i] = enrollStudents[i + GREATER];
            } else {
                enrollStudents[size] = null;
            }
        }
        size--;
    }

    /**
     * This method adds students to the enroll student array
     *
     * @param student is the given student being added to the enrollment
     *                array
     * @return a boolean if the student was added to the enrollment array
     */
    public boolean add(EnrollStudent student) {
        if (size >= enrollStudents.length) {
            grow();
        }

        if (find(student) != NOT_FOUND) {
            return false;
        }

        enrollStudents[size] = student;
        size++;
        return true;
    }

    /**
     * This method grows the enrollment array when the size has been
     * reached
     */
    private void grow() { //increase the array capacity by 4
        EnrollStudent[] newEnrollment =
                new EnrollStudent[enrollStudents.length + ARRAY_GROWTH];
        for (int i = 0; i < size; i++) {
            newEnrollment[i] = this.enrollStudents[i];
        }
        this.enrollStudents = newEnrollment;
    }

    /**
     * This method find the student in the given enrollment array
     *
     * @param student is the given student being searched for in the
     *                enrollment array
     * @return this method returns the index of the student in the
     * enrollment array
     */
    public int find(EnrollStudent student) { //search the given student
        // in roster
        int studentFinder = EQUAL;
        for (int i = 0; i < size; i++) {
            if (student.equals(enrollStudents[i])) {
                studentFinder = i;
                return studentFinder;
            }
        }
        return NOT_FOUND;
    }

    /**
     * This method checks if the student is in the enrollment array
     *
     * @param enrollStudent the given student the method is looking for
     * @return returns true or false if the student is or is not in the
     * array
     */
    public boolean contains(EnrollStudent enrollStudent) {
        for (int i = 0; i < size; i++) {
            if (enrollStudents[i].equals(enrollStudent)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This getter method gets the given student we are looking for from
     * the enrollment array
     *
     * @param enrollStudent is the given student we want information for
     * @return returns the students information
     */
    public EnrollStudent getEnrollStudent(EnrollStudent enrollStudent) {
        return getEnrollStudents()[find(enrollStudent)];
    }

    /**
     * This getter method gets us access to the enroll student array
     *
     * @return returns the enrollment array
     */
    public EnrollStudent[] getEnrollStudents() {
        return enrollStudents;
    }

    /**
     * This method prints out the enrollement array in the order the
     * students were added
     */
    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.println(enrollStudents[i]);
        }
    }
}