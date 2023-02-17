package project2;

public class International extends NonResident {

    private boolean isStudyAbroad;

    public International(Profile profile) {

        super(profile);
    }
    public International(Profile profile, Major major,
                         int creditCompleted) {
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
}
