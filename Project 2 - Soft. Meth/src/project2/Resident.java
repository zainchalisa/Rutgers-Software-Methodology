package project2;

// concrete class - can create instances

public class Resident extends Student {

    private int scholarship;
    public static final int FULL_RESIDENT_TUITION = 12536;
    public static final int FULL_RESIDENT_UNIVERSITY_FEE = 3268;
    public static final int PART_RESIDENT_CREDIT = 404;
    public static final double UNIVERSITY_FEE_DISCOUNT = 0.8;
    public static final int MAX_CREDITS = 16;
    public static final int MIN_FULL_TIME_CREDITS = 12;

    public Resident(Profile profile) {
        super(profile);
    }

    public Resident(Profile profile, Major major, int creditCompleted, int scholarship) {
        super(profile, major, creditCompleted);
        this.scholarship = scholarship;
    }

    @Override
    public double tuitionDue(int creditsEnrolled) {

        if(creditsEnrolled > 16){
            return (FULL_RESIDENT_TUITION + FULL_RESIDENT_UNIVERSITY_FEE) + ((creditsEnrolled - MAX_CREDITS) * PART_RESIDENT_CREDIT) - scholarship;
       } else if (creditsEnrolled >= 12 && creditsEnrolled <= 16){
           return FULL_RESIDENT_TUITION + FULL_RESIDENT_UNIVERSITY_FEE - scholarship;
       } else{
           return (creditsEnrolled * PART_RESIDENT_CREDIT) + (FULL_RESIDENT_UNIVERSITY_FEE *UNIVERSITY_FEE_DISCOUNT) - scholarship;
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
    public boolean isResident() {
        return true;
    }

    public boolean isValidScholarship(int scholarship) {
        if (scholarship <= 0 || scholarship > 10000) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "" + getProfile() + " (" + getMajor().getCoreCode() + " " +
                major + " " + getMajor().getSchool() + ") " +
                "credits completed: " + creditCompleted + " (" +
                getStanding() + ")" + "(resident)";
    }

    public void setScholarship(int scholarship) {
        this.scholarship = scholarship;
    }
}


