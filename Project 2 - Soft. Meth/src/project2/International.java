package project2;

public class International extends NonResident {

    private boolean isStudyAbroad;

    public International(Profile profile) {
        super(profile);
    }
    public International(Profile profile, Major major,
                         int creditCompleted) {
        super(profile, major, creditCompleted);
    }
}
