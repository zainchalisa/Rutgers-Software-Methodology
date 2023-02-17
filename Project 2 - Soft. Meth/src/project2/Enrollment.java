package project2;

public class Enrollment {
    private EnrollStudent[] enrollStudents;
    private int size;

    public static final int ARRAY_ADDITIVE = 1;
    public void add(EnrollStudent enrollStudent){
        EnrollStudent[] newEnrollment = new EnrollStudent[enrollStudents.length + ARRAY_ADDITIVE];
        for (int i = 0; i < newEnrollment.length - 1; i++) {
            newEnrollment[i] = this.enrollStudents[i];
        }

        this.enrollStudents = newEnrollment;
        enrollStudent[size] = enrollStudent;
        size++;

    } //add to the end of array
    //move the last one in the array to replace the deleting index position
    public void remove(EnrollStudent enrollStudent){

        if(enrollStudents.contains(enrollStudent)){
            for (int i = 0; i < size; i++) {
                if(enrollStudents[i] == enrollStudent){
                    indexHolder = i;
                    enrollStudents[i] = null;
                }
            }
        }

        for (int i = indexHolder; i < size - 1; i++) {
            if (i + 1 < size) {
                roster[i] = roster[i + 1];
            } else {
                roster[size] = null;
            }
        }
        size--;
    }
    public boolean contains(EnrollStudent enrollStudent){
        for (int i = 0; i < size; i++) {
            if(enrollStudents[i] == enrollStudent){
                return true;
            }
        }
        return false;
    }
    public void print() {
        for (int i = 0; i < size; i++){
            System.out.println(enrollStudents[i]);
        }
    } //print the array as is without sorting
}