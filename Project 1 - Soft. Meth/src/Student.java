public class Student implements Comparable {
    private Profile profile;
    private Major major; // Major is an enum type
    private int creditCompleted;

    public Student(Profile profile, Major major, int creditCompleted) {
        this.profile = profile;
        this.major = major;
        this.creditCompleted = creditCompleted;
    }

    @Override
    public String toString(){
        return null;
    }

    @Override
    public int compareTo(Object obj){
        return 0;
    }
    @Override
    public boolean equals(Object obj) {
        return false;
    }

}
