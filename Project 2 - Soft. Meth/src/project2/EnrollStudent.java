package project2;

/**
 * This class is creating enrollStudent objects for the enrollment array
 * @author zainchalisa
 * @author nanaafriyie
 */
public class EnrollStudent {

    private Profile profile;
    private int creditsEnrolled;

    /**
     * This contructor sets the profile of the enroll student
     * @param profile the profile of the enroll student
     */
    public EnrollStudent (Profile profile) {
        this.profile = profile;
    }

    /**
     * This constuctor sets the profile of the enroll student
     * and the credits they're enrolled for
     * @param profile the profile of the enroll student
     * @param creditsEnrolled the creditsEnrolled for the enroll student
     */
    public EnrollStudent (Profile profile, int creditsEnrolled){
        this.profile = profile;
        this.creditsEnrolled = creditsEnrolled;
    }

    /**
     * This getter method gets the credits the enroll student is
     * enrolled for
     * @return returns the credits the student is enrolled for
     */
    public int getCreditsEnrolled() {
        return creditsEnrolled;
    }

    /**
     * This getter method gets the profile of the enroll student
     * @return returns the profile of the enroll student
     */
    public Profile getProfile() {
        return profile;
    }

    /**
     * This method checks if a student is a part-time student
     * @return returns a boolean if the student is part-time or not
     */
    public boolean isPartTime() {
        if (this.creditsEnrolled < 12) {
            return true;
        }
        return false;
    }

    /**
     * This method sets the student is enrolled for if they re-enroll with
     * a different credit amount
     * @param creditsEnrolled is the credit amount the return is
     * re-enrolling with
     */

    public void setCreditsEnrolled(int creditsEnrolled) {
        this.creditsEnrolled = creditsEnrolled;
    }

    /**
     * Overrides the equals() method for the EnrollStudent object
     * @param obj compared to another enrollStudent object
     * @return returns true or false depending on the comparison
     */
    @Override
    public boolean equals(Object obj){
        if (obj instanceof EnrollStudent) {
            EnrollStudent enrollStudent = (EnrollStudent) obj;
            return (enrollStudent.profile.equals(this.profile));
        } else {
            return false;
        }
    }

    /**
     * Overrides the toString() method for the EnrollStudent object
     * @return returns the string for an enrollStudent
     */
    @Override
    public String toString(){
        return profile + "" ;
    }
}

