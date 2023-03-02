package project3.project3;

/**
 * This class creates a resident student, validates them, and calculates
 * the amount of tuition they owe
 *
 * @author nanaafriyie
 * @author zainchalisa
 */
public class Resident extends Student {

    private int scholarship;
    public static final int FULL_RESIDENT_TUITION = 12536;
    public static final int FULL_RESIDENT_UNIVERSITY_FEE = 3268;
    public static final int PART_RESIDENT_CREDIT = 404;
    public static final double UNIVERSITY_FEE_DISCOUNT = 0.8;
    public static final int MAX_CREDITS = 16;
    public static final int MIN_FULL_TIME_CREDITS = 12;
    public static final int MIN_CREDITS = 3;
    public static final int SUPER_MAX_CREDITS = 24;
    public static final int MIN_SCHOLARSHIP = 0;
    public static final int MAX_SCHOLARSHIP = 10000;


    /**
     * This constructor sets the profile for the resident student
     *
     * @param profile the profile of the resident student
     */
    public Resident(Profile profile) {
        super(profile);
    }

    /**
     * This constructor sets the profile, major, creditsCompleted, and
     * scholarship award of the resident student
     *
     * @param profile         the profile of the resident student
     * @param major           the major of the resident student
     * @param creditCompleted the creditsCompleted of the resident student
     * @param scholarship     the scholarship awarded to the resident
     *                        student
     */
    public Resident(Profile profile, Major major, int creditCompleted,
                    int scholarship) {
        super(profile, major, creditCompleted);
        this.scholarship = scholarship;
    }

    /**
     * The override tuitionDue method for Resident students which
     * fetches how much the student owes
     *
     * @param creditsEnrolled is the amount of credits they will
     *                        enroll for the semester
     * @return returns the amount the resident student owes in tuition
     */
    @Override
    public double tuitionDue(int creditsEnrolled) {

        if (creditsEnrolled > MAX_CREDITS) {
            return (FULL_RESIDENT_TUITION + FULL_RESIDENT_UNIVERSITY_FEE) +
                    ((creditsEnrolled - MAX_CREDITS) *
                            PART_RESIDENT_CREDIT)
                    - scholarship;
        } else if (creditsEnrolled >= MIN_FULL_TIME_CREDITS &&
                creditsEnrolled <= MAX_CREDITS) {
            return FULL_RESIDENT_TUITION + FULL_RESIDENT_UNIVERSITY_FEE
                    - scholarship;
        } else {
            return (creditsEnrolled * PART_RESIDENT_CREDIT) +
                    (FULL_RESIDENT_UNIVERSITY_FEE *
                            UNIVERSITY_FEE_DISCOUNT) - scholarship;
        }
    }

    /**
     * This method checks if the resident student is valid as per the
     * schools standards
     *
     * @param creditsEnrolled is the amount of credits they will enroll
     *                        for in this semester
     * @return returns if the student is valid or not
     */
    @Override
    public boolean isValid(int creditsEnrolled) {
        if (creditsEnrolled < MIN_CREDITS || creditsEnrolled >
                SUPER_MAX_CREDITS) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * This constuctor cheks if the student is a resident or not
     *
     * @return returns true since the student is being checked in the
     * resident class
     */
    @Override
    public boolean isResident() {
        return true;
    }

    /**
     * This method checks if the scholarship being awarded to the student
     * is valid
     *
     * @param scholarship the amount of scholarship being awarded to
     *                    a student
     * @return returns the amount of scholarship being awarded to the
     * student
     */
    public boolean isValidScholarship(int scholarship) {
        if (scholarship <= MIN_SCHOLARSHIP || scholarship >
                MAX_SCHOLARSHIP) {
            return false;
        }
        return true;
    }

    /**
     * This method overrides the toString() method for the resident class
     *
     * @return returns the string for the resident student
     */
    @Override
    public String toString() {
        return "" + getProfile() + " (" + getMajor().getCoreCode() + " " +
                major + " " + getMajor().getSchool() + ") " +
                "credits completed: " + creditCompleted + " (" +
                getStanding() + ")" + "(resident)";
    }

    /**
     * This method sets the scholarship amount for the student
     *
     * @param scholarship is the amount of scholarship the resident
     *                    student is getting
     */
    public void setScholarship(int scholarship) {
        this.scholarship = scholarship;
    }
}


