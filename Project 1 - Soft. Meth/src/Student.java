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

    public Standing getStanding(){

        int credits = this.creditCompleted;
        if(credits < 30){
            return Standing.FRESHMAN;
        } else if (credits >= 30 && credits < 60){
            return Standing.SOPHOMORE;
        } else if (credits >= 60 && credits < 90){
            return Standing.JUNIOR;
        } else{
            return Standing.SENIOR;
        }
    }

    public Profile getProfile() {
        return this.profile;
    }

    public Major getMajor(){
        return this.major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    @Override
    public String toString(){
        return "" + profile + " " + "(" + getMajor().getCoreCode() + " " + major + " " + getMajor().getSchool() + ")" + "credits completed: " + creditCompleted + " (" + getStanding() + ")";
    }

    //Kate Lindsey 7/15/2002 (04:547 ITI SC&I) credits completed: 59 (Sophomore)

    public int compareTo(Object obj){
        Student student = (Student) obj; //casting
        if(student.profile != null){
            return this.profile.compareTo(student.profile);
        } else{
            return 1;
        }
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
