package project2;

public class NonResident extends Student {
    public static final int NON_RESIDENT_TUITION = 29737;
    public static final int NON_RESIDENT_UNIVERSITY_FEE = 3268;
    public static final int NON_RESIDENT_CREDIT_HOUR = 966;
    public static final double UNIVERSITY_FEE_DISCOUNT = .8;
    public static final int MAX_CREDITS = 16;
    public static final int MIN_FULL_TIME_CREDITS = 12;

    public NonResident(Profile profile) {

        super(profile);
    }
    public NonResident(Profile profile, Major major, int creditCompleted) {
        super(profile, major, creditCompleted);
    }

    @Override
    public double tuitionDue(int creditsEnrolled) {
        if(creditsEnrolled == MAX_CREDITS){
            return NON_RESIDENT_TUITION + NON_RESIDENT_UNIVERSITY_FEE;
        } else if (creditsEnrolled < MIN_FULL_TIME_CREDITS) {
            return (creditsEnrolled * NON_RESIDENT_CREDIT_HOUR) + (NON_RESIDENT_UNIVERSITY_FEE *UNIVERSITY_FEE_DISCOUNT);
        } else{
            int leftOverCredits = creditsEnrolled - MAX_CREDITS;
            return NON_RESIDENT_TUITION + NON_RESIDENT_UNIVERSITY_FEE + (leftOverCredits * NON_RESIDENT_CREDIT_HOUR);
        }
    }

    @Override
    public boolean isValid(int creditsEnrolled){
        if(creditsEnrolled < 3 || creditsEnrolled > 24){
            return false;
        } else{
            return true;
        }
    }

    @Override
    public String toString() {
        return "" + getProfile() + " (" + getMajor().getCoreCode() + " " +
                major + " " + getMajor().getSchool() + ") " +
                "credits completed: " + creditCompleted + " (" +
                getStanding() + ")" + "(non-resident)";
    }


    @Override
    public boolean isResident() {
        return false;
    }
}
