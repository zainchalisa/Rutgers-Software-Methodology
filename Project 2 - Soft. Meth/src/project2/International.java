package project2;

public class International extends NonResident {

    private boolean isStudyAbroad;
    public static final int INTERNATIONAL_TUITION = 29737;
    public static final int INTERNATIONAL_UNIVERSITY_FEE = 3268;
    public static final int INTERNATIONAL_CREDIT_HOUR = 966;
    public static final double UNIVERSITY_FEE_DISCOUNT = .8;
    public static final int MAX_CREDITS = 16;
    public static final int MIN_FULL_TIME_CREDITS = 12;
    public static final int INTERNATIONAL_HEALTH_CARE = 2650;

    public International(Profile profile) {
        super(profile);
    }
    public International(Profile profile, Major major,
                         int creditCompleted) {
        super(profile, major, creditCompleted);
    }


    public double tuitionDue(int creditsEnrolled) {
        if(!isStudyAbroad){
            if(creditsEnrolled == MAX_CREDITS){
                return INTERNATIONAL_TUITION + INTERNATIONAL_UNIVERSITY_FEE + INTERNATIONAL_HEALTH_CARE;
            } else if (creditsEnrolled < MIN_FULL_TIME_CREDITS) {
                return (creditsEnrolled * INTERNATIONAL_CREDIT_HOUR) + (INTERNATIONAL_UNIVERSITY_FEE * UNIVERSITY_FEE_DISCOUNT);
            } else{
                int leftOverCredits = creditsEnrolled - MAX_CREDITS;
                return INTERNATIONAL_TUITION + INTERNATIONAL_UNIVERSITY_FEE + (leftOverCredits * INTERNATIONAL_CREDIT_HOUR) + INTERNATIONAL_HEALTH_CARE;
            }
        } else{
            return INTERNATIONAL_UNIVERSITY_FEE + INTERNATIONAL_HEALTH_CARE;
        }

    }

    public boolean isValid(int creditsEnrolled){
        if(creditsEnrolled < 3 || creditsEnrolled > 24){
            return false;
        } else if(isStudyAbroad == false && creditsEnrolled < 12 ) {
            return false;
        } else{
            return true;
        }
    }

    public static void main(String[] args) {
        International student1 = new International(new Profile("Chalisa", "Zain", new Date("01/02/2002")), Major.CS, 99);
        System.out.println(student1.isValid(12));
    }
}
