package project2;

public class EnrollStudent {
    private Profile profile;
    private int creditsEnrolled;

    public EnrollStudent (Profile profile, int creditsEnrolled){
        this.profile = profile;
        this.creditsEnrolled = creditsEnrolled;
    }

    @Override
    public boolean equals(Object obj){
        if (obj instanceof EnrollStudent) {
            EnrollStudent enrollStudent = (EnrollStudent) obj;
            return (EnrollStudent.enrollStudent.equals(this.enrollStudent));
        } else {
            return false;
        }
    }
    @Override
    public String toString(){
        return profile + " " + creditsEnrolled;
    }
}