package project2;

/**
 * This class creates a non-resident student, provides their tuition, and
 * validates them
 *
 * @author zainchalisa
 * @author nanaafriyie
 */
public class NonResident extends Student {
    public static final int NON_RESIDENT_TUITION = 29737;
    public static final int NON_RESIDENT_UNIVERSITY_FEE = 3268;
    public static final int NON_RESIDENT_CREDIT_HOUR = 966;
    public static final double UNIVERSITY_FEE_DISCOUNT = .8;
    public static final int MAX_CREDITS = 16;
    public static final int MIN_FULL_TIME_CREDITS = 12;

    /**
     * This constructor sets the profile of the non-resident student
     *
     * @param profile is the profile of the non-resident student
     */
    public NonResident(Profile profile) {
        super(profile);
    }

    /**
     * This constructor sets the profile, major, and creditsCompleted
     * of the non-resident student
     *
     * @param profile         is the profile of the non-resident student
     * @param major           is the major of the non-resident student
     * @param creditCompleted is the credits completed for the
     *                        non-resident student
     */
    public NonResident(Profile profile, Major major, int creditCompleted) {
        super(profile, major, creditCompleted);
    }

    /**
     * This method checks if the student is full-time
     *
     * @param creditsEnrolled is the amount of credits the student is
     *                        taking this semester
     * @return returns a boolean value depending on if the student is
     * full time or not
     */
    public boolean isFullTimeStudent(int creditsEnrolled) {
        if (creditsEnrolled > 12) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * The override tuitionDue method for Non-resident students which
     * fetches how much the student owes
     *
     * @param creditsEnrolled is the amount of credits they will
     *                        enroll for the semester
     * @return returns the amount students owe in tuition
     */
    @Override
    public double tuitionDue(int creditsEnrolled) {
        if (creditsEnrolled > MAX_CREDITS) {
            int leftOverCredits = creditsEnrolled - MAX_CREDITS;
            return NON_RESIDENT_TUITION + NON_RESIDENT_UNIVERSITY_FEE +
                    (leftOverCredits * NON_RESIDENT_CREDIT_HOUR);
        } else if (creditsEnrolled >= MIN_FULL_TIME_CREDITS &&
                creditsEnrolled <= MAX_CREDITS) {
            return NON_RESIDENT_TUITION + NON_RESIDENT_UNIVERSITY_FEE;
        } else {
            return (creditsEnrolled * NON_RESIDENT_CREDIT_HOUR) +
                    (NON_RESIDENT_UNIVERSITY_FEE *
                            UNIVERSITY_FEE_DISCOUNT);
        }
    }

    /**
     * This method checks if the international student is a valid student
     * as per the schools standards
     *
     * @param creditsEnrolled is the amount of credits they will
     *                        enroll for the semester
     * @return returns if the student is valid
     */
    @Override
    public boolean isValid(int creditsEnrolled) {
        if (creditsEnrolled < 3 || creditsEnrolled > 24) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Overrides the toString() method for the non-resident class
     *
     * @return returns the non-resident string which is used in print
     * methods
     */
    @Override
    public String toString() {
        return "" + getProfile() + " (" + getMajor().getCoreCode() + " "
                + major + " " + getMajor().getSchool() + ") " +
                "credits completed: " + creditCompleted + " (" +
                getStanding() + ")" + "(non-resident)";
    }

    /**
     * This method checks if the student is a Resident
     *
     * @return returns false since they're a non-resident
     */
    @Override
    public boolean isResident() {
        return false;
    }
}
