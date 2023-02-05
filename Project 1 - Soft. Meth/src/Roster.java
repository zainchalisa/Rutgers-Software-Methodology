public class Roster {
    private Student[] roster;
    private int size = 1;
    public static final int FULL_ARRAY = size - 1;
    public static final int ARRAY_GROWTH = 4;
    public static final int NOT_FOUND = -1;

    private int find(Student student) { //search the given student in roster
        int studentFinder = 0;
        for (int i =0; i < roster.length; i++){
            if (student.equals(roster[i])){
                studentFinder = i;
                return studentFinder;
            }
        }
        return NOT_FOUND;
    }
    private void grow() { //increase the array capacity by 4
        Student[] newRoster = new Student[size + ARRAY_GROWTH];
        size = size + ARRAY_GROWTH;
        for (int i = 0; i < roster.length; i++){
            newRoster[i] = this.roster[i];
        }
        this.roster = newRoster;
    }
    public boolean add(Student student){ //add student to end of array

        if (find(student) == NOT_FOUND){
            roster[FULL_ARRAY] = student;
        }

        if (roster[FULL_ARRAY] != null){
            grow();
        }

        return false;
    }

    public boolean remove(Student student){ //maintain the order after remove

        for(int i = 0; i < roster.length; i++){
            if(roster[i].equals(student)){
                roster[i] = null;
            }
        }

        Student[] newRoster = new Student[size];

        for(int i = 0; i < roster.length; i++){
            if (roster[i] == null){
                int arrayHolder = i;
                newRoster[i] = this.roster[arrayHolder + 1];
            }
            newRoster[i] = this.roster[i];
        }

        this.roster = newRoster;

        return false;
    }
    public boolean contains(Student student){ //if the student is in roster
        for (int i =0; i < roster.length; i++){
            if (student.equals(roster[i])){
                return true;
            }
        }
        return false;
    }
    public void print () {} //print roster sorted by profiles
    public void printBySchoolMajor() {} //print roster sorted by school major
    public void printByStanding() {} //print roster sorted by standing
}