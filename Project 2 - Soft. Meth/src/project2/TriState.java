package project2;

public class TriState extends NonResident {
    private String state;
    public static final int NEW_YORK_DISCOUNT = 4000;
    public static final int CONNECTICUT_DISCOUNT = 5000;

    public TriState(Profile profile) {
        super(profile);
    }

    public TriState(Profile profile, Major major, int creditCompleted, String state) {
        super(profile, major, creditCompleted);
        this.state = state;
    }

    public String getState() {

        return state.toUpperCase();
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "" + getProfile() + " (" + getMajor().getCoreCode() + " " +
                major + " " + getMajor().getSchool() + ") " +
                "credits completed: " + creditCompleted + " (" +
                getStanding() + ")" + "(non-resident)"+"(tri-state:" + getState() + ")";
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
    public double tuitionDue(int creditsEnrolled){
        if(state.equalsIgnoreCase("NY")){
            return super.tuitionDue(creditsEnrolled) - NEW_YORK_DISCOUNT;
        } else if (state.equalsIgnoreCase("CT")){
            return super.tuitionDue(creditsEnrolled) - CONNECTICUT_DISCOUNT;
        } else{
            return super.tuitionDue(creditsEnrolled);
        }
    }
}
