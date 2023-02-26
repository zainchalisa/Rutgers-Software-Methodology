package project2;

public class EnrollStudent {

    private Profile profile;
    private int creditsEnrolled;

    public EnrollStudent (Profile profile) {
        this.profile = profile;
    }

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

    public boolean isPartTime() {
        if (this.creditsEnrolled < 12) {
            return true;
        }
        return false;
    }

    public void setCreditsEnrolled(int creditsEnrolled) {
        this.creditsEnrolled = creditsEnrolled;
    }


    @Override
    public boolean equals(Object obj){
        if (obj instanceof EnrollStudent) {
            EnrollStudent enrollStudent = (EnrollStudent) obj;
            return (enrollStudent.profile.equals(this.profile));
        } else {
            return false;
        }
    }

    @Override
    public String toString(){
        return profile + " " ;
    }
}

