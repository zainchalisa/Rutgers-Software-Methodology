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
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public double tuitionDue(int creditsEnrolled){
        if(state.equal("NY")){
            return super.tuitionDue(creditsEnrolled) - NEW_YORK_DISCOUNT;
        } else if (state.equal("CT")){
            return super.tuitionDue(creditsEnrolled) - CONNECTICUT_DISCOUNT;
        } else{
            return super.tuitionDue(creditsEnrolled);
        }
    }
}
