package project2;

public class EnrollStudent {

    private Profile profile;
    private int creditsEnrolled;

    public EnrollStudent (Profile profile, int creditsEnrolled){
        this.profile = profile;
        this.creditsEnrolled = creditsEnrolled;
    }

    public int getCreditsEnrolled() {
        return creditsEnrolled;
    }

    public Profile getProfile() {
        return profile;
    }

    @Override
    public boolean equals(Object obj){
        if (obj instanceof EnrollStudent) {
            EnrollStudent enrollStudent = (EnrollStudent) obj;
            return (enrollStudent.equals(this.profile));
        } else {
            return false;
        }
    }

    @Override
    public String toString(){
        return profile + " " + creditsEnrolled;
    }
}

