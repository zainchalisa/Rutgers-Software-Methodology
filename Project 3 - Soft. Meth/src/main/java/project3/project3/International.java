package project3.project3;

/**
 * This class creates International Students and validates them
 * and their tuition costs
 *
 * @author zainchalisa
 * @author nanaafriyie
 */
public class International extends NonResident {

    private boolean isStudyAbroad;
    public static final int INTERNATIONAL_TUITION = 29737;
    public static final int INTERNATIONAL_UNIVERSITY_FEE = 3268;
    public static final int INTERNATIONAL_CREDIT_HOUR = 966;
    public static final double UNIVERSITY_FEE_DISCOUNT = .8;
    public static final int MAX_CREDITS = 16;
    public static final int MIN_FULL_TIME_CREDITS = 12;
    public static final int MIN_STUDY_ABROAD_CREDITS = 3;
    public static final int MAX_STUDY_ABROAD_CREDITS = 12;
    public static final int SUPER_MAX_CREDITS = 24;
    public static final int INTERNATIONAL_HEALTH_CARE = 2650;

    /**
     * This constructor sets the profile of a enroll student
     *
     * @param profile is the profile of an enroll student
     */
    public International(Profile profile) {
        super(profile);
    }

    /**
     * This constructor sets the profile, major, and creditsCompleted of
     * an international student
     *
     * @param profile         is the profile of an international student
     * @param major           is the major of an international student
     * @param creditCompleted is the credits completed of an
     *                        international student
     */
    public International(Profile profile, Major major,
                         int creditCompleted) {
        super(profile, major, creditCompleted);
    }

    /**
     * This constructor sets the profile, major, creditsCompleted, and
     * studyAbroad of an international student
     *
     * @param profile         is the profile of an international student
     * @param major           is the major of an international student
     * @param creditCompleted is the credits completed of an
     *                        *                 international student
     * @param isStudyAbroad   is the boolean to see if the student is
     *                        studying abroad
     */
    public International(Profile profile, Major major,
                         int creditCompleted, boolean isStudyAbroad) {
        super(profile, major, creditCompleted);
        this.isStudyAbroad = isStudyAbroad;
    }

    /**
     * The override tuitionDue method for International students which
     * fetches how much the student owes
     *
     * @param creditsEnrolled is the amount of credits they will
     *                        enroll for the semester
     * @return returns the amount students owe in tuition
     */
    @Override
    public double tuitionDue(int creditsEnrolled) {
        if (!isStudyAbroad) {
            if (creditsEnrolled > MAX_CREDITS) {
                int leftOverCredits = creditsEnrolled - MAX_CREDITS;
                return INTERNATIONAL_TUITION + INTERNATIONAL_UNIVERSITY_FEE
                        + (leftOverCredits * INTERNATIONAL_CREDIT_HOUR) +
                        INTERNATIONAL_HEALTH_CARE;
            } else if (creditsEnrolled >= MIN_FULL_TIME_CREDITS &&
                    creditsEnrolled <= MAX_CREDITS) {
                return INTERNATIONAL_TUITION + INTERNATIONAL_UNIVERSITY_FEE
                        + INTERNATIONAL_HEALTH_CARE;
            } else {
                return (creditsEnrolled * INTERNATIONAL_CREDIT_HOUR) +
                        (INTERNATIONAL_UNIVERSITY_FEE *
                                UNIVERSITY_FEE_DISCOUNT);
            }
        } else {
            return INTERNATIONAL_UNIVERSITY_FEE + INTERNATIONAL_HEALTH_CARE;
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
    public boolean isValid(int creditsEnrolled) {
        if (creditsEnrolled < MIN_STUDY_ABROAD_CREDITS ||
                creditsEnrolled > SUPER_MAX_CREDITS) {
            return false;
        } else if (isStudyAbroad == false && creditsEnrolled <
                MAX_STUDY_ABROAD_CREDITS) {
            return false;
        } else if (isStudyAbroad == true && creditsEnrolled >
                MAX_STUDY_ABROAD_CREDITS) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * This method checks if the international student is study abroad
     *
     * @return returns true or false depending on if the student is
     * studying abroad
     */
    public boolean isStudyAbroad() {
        return isStudyAbroad;
    }

    /**
     * This overrides the toString() for the international student method
     *
     * @return returns the appropriate string depending on the type of
     * international student
     */
    @Override
    public String toString() {
        if (isStudyAbroad()) {
            return "" + getProfile() + " (" + getMajor().getCoreCode() +
                    " " + major + " " + getMajor().getSchool() + ") " +
                    "credits completed: " + creditCompleted + " ("
                    + getStanding() + ")" + "(non-resident)" +
                    "(international:study abroad)";
        } else {
            return "" + getProfile() + " (" + getMajor().getCoreCode() +
                    " " + major + " " + getMajor().getSchool() + ") "
                    + "credits completed: " + creditCompleted + " (" +
                    getStanding() + ")" + "(non-resident)" +
                    "(international)";
        }
    }
}

