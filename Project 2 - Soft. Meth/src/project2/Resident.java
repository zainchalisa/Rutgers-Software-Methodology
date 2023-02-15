package project2;

// concrete class - can create instances

public class Resident extends Student {



    private int scholarship;

    public Resident(Profile profile) {
        super(profile);
    }

    public Resident(Profile profile, Major major, int creditCompleted) {
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
