public class Student implements Comparable {
    private Profile profile;
    private Major major; // Major is an enum type
    private int creditCompleted;

    public Student(Profile profile) {
        this.profile = profile;
    }
    public Student(Profile profile, Major major, int creditCompleted) {
        this.profile = profile;
        this.major = major;
        this.creditCompleted = creditCompleted;
    }

    public int getCreditCompleted(){
        return this.creditCompleted;
    }

    public Major getMajor(){
        return this.major;
    }

    @Override
    public String toString(){
        return "" + profile + major + creditCompleted;
    }

    @Override
    public int compareTo(Object obj){
        Student student = (Student) obj; //casting
        return this.profile.compareTo(student.profile);
    }
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Student) {
            Student student = (Student) obj; //casting
            return (student.profile.equals(this.profile));
        } else {
            return false;
        }
    }

}
