package project2;

public class TriState extends NonResident {
    private String state;

    public TriState(Profile profile) {
        super(profile);
    }
    public TriState(Profile profile, Major major, int creditCompleted) {
        super(profile, major, creditCompleted);
    }
}
