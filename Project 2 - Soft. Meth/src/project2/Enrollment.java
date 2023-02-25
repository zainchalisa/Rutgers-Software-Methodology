package project2;

public class Enrollment {
    private EnrollStudent[] enrollStudents;
    private int size;

    public static final int ARRAY_GROWTH = 4;
    public static final int NOT_FOUND = -1;
    public static final int GREATER = 1;
    public static final int EQUAL = 0;

    public Enrollment() {
        this.enrollStudents = new EnrollStudent[4];
        this.size = 0;
    }
    //add to the end of array
    //move the last one in the array to replace the deleting index position
    public void remove(EnrollStudent enrollStudent){
        int indexHolder = 0;
        if(contains(enrollStudent)){
            for (int i = 0; i < size; i++) {
                if(enrollStudents[i] == enrollStudent){
                    indexHolder = i;
                    enrollStudents[i] = null;
                }
            }
        }

        for (int i = indexHolder; i < size - 1; i++) {
            if (i + 1 < size) {
                enrollStudents[i] = enrollStudents[i + 1];
            } else {
                enrollStudents[size] = null;
            }
        }
        size--;
    }

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

    private void grow() { //increase the array capacity by 4
        EnrollStudent[] newEnrollment = new EnrollStudent[enrollStudents.length + ARRAY_GROWTH];
        for (int i = 0; i < size; i++) {
            newEnrollment[i] = this.enrollStudents[i];
        }
        this.enrollStudents = newEnrollment;
    }

    public int find(EnrollStudent student) { //search the given student in roster
        int studentFinder = 0;
        for (int i = 0; i < size; i++) {
            if (student.equals(enrollStudents[i])) {
                studentFinder = i;
                return studentFinder;
            }
        }
        return NOT_FOUND;
    }

    public boolean contains(EnrollStudent enrollStudent){
        for (int i = 0; i < size; i++) {
            if(enrollStudents[i].equals(enrollStudent)){
                return true;
            }
        }
        return false;
    }

    public EnrollStudent[] getEnrollStudents() {
        return enrollStudents;
    }


    public void print() {
        for (int i = 0; i < size; i++){
            System.out.println(enrollStudents[i]);
        }
    } //print the array as is without sorting
}