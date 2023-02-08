import java.sql.SQLOutput;

public class Roster {
    private Student[] roster;
    private int size;
    public static final int ARRAY_GROWTH = 4;
    public static final int NOT_FOUND = -1;

    public static final int GREATER = 1;
    public static final int SMALLER = -1;
    public static final int EQUAL = 0;

    public Roster(){
        this.roster = new Student[4];
        this.size = 0;
    }

    public int find(Student student) { //search the given student in roster
        int studentFinder = 0;
        for (int i =0; i < size; i++){
            if (student.equals(roster[i])){
                studentFinder = i;
                return studentFinder;
            }
        }
        return NOT_FOUND;
    }
    private void grow() { //increase the array capacity by 4
        Student[] newRoster = new Student[roster.length + ARRAY_GROWTH];
        for (int i = 0; i < size; i++){
            newRoster[i] = this.roster[i];
        }
        this.roster = newRoster;
    }
    public boolean add(Student student){ //add student to end of array

        if (size >= roster.length){
            grow();
        }

        if(find(student) != NOT_FOUND){
            return false; // it already is in the roster
        }

        roster[size] = student;
        size++;

        return true; // added it to the roster
    }

    public boolean remove(Student student){ //maintain the order after remove

        if(find(student) == NOT_FOUND){
            return false;
        }

        int studentIndex = find(student);

        for(int i = studentIndex; i < size - 1; i++){
                roster[i] = roster[i+1];
        }

        roster[size] = null;
        size--;
        return true;
    }

    public boolean contains(Student student){ //if the student is in roster
        for (int i =0; i <= size; i++){
            if (student.equals(roster[i])){
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString(){

        String students = "";

        for (Student student: roster) {
            if(student != null) {
                students += student.toString() + "\n";
            }
        }
        return students;
    }

    public void insertionSort(Student[] roster){
        for(int i = 0; i < size; i++){
            int j = i;
            while (j > 0 && roster[j-1].compareTo(roster[j]) == GREATER){
                    Student temp = roster[j];
                    roster[j] = roster[j - 1];
                    roster[j - 1] = temp;
                    j--;
            }
        }
    }

    public void print () { //print roster sorted by profiles
        insertionSort(roster);
        for(int i = 0; i < size; i++) {
            System.out.println(roster[i]);
        }
    }
    public void printBySchoolMajor() {} //print roster sorted by school major
    public void printByStanding() {} //print roster sorted by standing
}