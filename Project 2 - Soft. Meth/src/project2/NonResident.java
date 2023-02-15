package project2;

public class NonResident extends Student {


    public NonResident(Profile profile) {
        super(profile);
    }
    public NonResident(Profile profile, Major major, int creditCompleted) {
        super(profile, major, creditCompleted);
    }

    @Override
    public double tuitionDue(int creditsEnrolled) {
        return 0;
    }

    @Override
    public boolean isResident() {
        return false;
    }
}
