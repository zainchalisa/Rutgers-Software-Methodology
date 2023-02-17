package project2;

// concrete class - can create instances

public class Resident extends Student {

    private int scholarship;

    public static final int FULL_RESIDENT_TUITION = 12536;
    public static final int FULL_RESIDENT_UNIVERSITY_FEE = 3268;
    public static final int PART_RESIDENT_CREDIT = 404;
    public static final double UNIVERSITY_FEE_DISCOUNT = .8;
    public static final int MAX_CREDITS = 16;
    public static final int MIN_FULL_TIME_CREDITS = 12;

    public Resident(Profile profile, Major major, int creditCompleted, int scholarship) {
        super(profile, major, creditCompleted);
        this.scholarship = scholarship;
    }

    @Override
    public double tuitionDue(int creditsEnrolled) {

       if(creditsEnrolled == MAX_CREDITS){
           return FULL_RESIDENT_TUITION + FULL_RESIDENT_UNIVERSITY_FEE;
       } else if (creditsEnrolled < MIN_FULL_TIME_CREDITS) {
           return (creditsEnrolled * PART_RESIDENT_CREDIT) + (FULL_RESIDENT_UNIVERSITY_FEE *UNIVERSITY_FEE_DISCOUNT);
       } else{
           int leftOverCredits = creditsEnrolled - MAX_CREDITS;
           return FULL_RESIDENT_TUITION + FULL_RESIDENT_UNIVERSITY_FEE + (leftOverCredits * PART_RESIDENT_CREDIT);
       }

    }

    @Override
    public boolean isResident() {
        return true;
    }
}
