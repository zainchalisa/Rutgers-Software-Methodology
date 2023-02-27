package project2;

/**
 * This class creates tri-state students and calculates their tuition
 *
 * @author zainchalisa
 * @author nanaafriyie
 */
public class TriState extends NonResident {
    private String state;
    public static final int NEW_YORK_DISCOUNT = 4000;
    public static final int CONNECTICUT_DISCOUNT = 5000;

    /**
     * This constructor creates the profile for the tri-state student
     *
     * @param profile
     */
    public TriState(Profile profile) {
        super(profile);
    }

    /**
     * This constructor creates the profile, major, and credit completed
     * for the student
     *
     * @param profile         the profile of the student
     * @param major           the major of the student
     * @param creditCompleted the credits completed by the student
     * @param state           the state the student is from
     */
    public TriState(Profile profile, Major major, int creditCompleted,
                    String state) {
        super(profile, major, creditCompleted);
        this.state = state;
    }

    /**
     * The getter method for the state
     *
     * @return returns the state of the student
     */
    public String getState() {

        return state.toUpperCase();
    }

    /**
     * The setter method for the students state
     *
     * @param state sets the students state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * This method overrides the toString() method for the tri-state
     * student
     *
     * @return returns the proper string for the tri-state student
     */
    @Override
    public String toString() {
        return "" + getProfile() + " (" + getMajor().getCoreCode() + " " +
                major + " " + getMajor().getSchool() + ") " +
                "credits completed: " + creditCompleted + " (" +
                getStanding() + ")" + "(non-resident)" + "(tri-state:" +
                getState() + ")";
    }

    /**
     * This method checks if the student is a valid tri-state student
     *
     * @param creditsEnrolled is the amount of credits they will
     *                        enroll for the semester
     * @return returns if they're valid or not
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
     * This method returns the amount of tuition owed by the student
     *
     * @param creditsEnrolled is the amount of credits they will
     *                        enroll for the semester
     * @return returns the amount of tuition owed by the student
     */
    @Override
    public double tuitionDue(int creditsEnrolled) {
        if (isFullTimeStudent(creditsEnrolled)) {
            if (state.equalsIgnoreCase("NY")) {
                return super.tuitionDue(creditsEnrolled) -
                        NEW_YORK_DISCOUNT;
            } else if (state.equalsIgnoreCase("CT")) {
                return super.tuitionDue(creditsEnrolled) -
                        CONNECTICUT_DISCOUNT;
            } else {
                return super.tuitionDue(creditsEnrolled);
            }
        } else {
            return super.tuitionDue((creditsEnrolled));
        }
    }
}
