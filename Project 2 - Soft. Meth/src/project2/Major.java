package project2;

/**
 * This enum is used to access the majors, course codes, and schools.
 *
 * @author zainchalisa
 * @author nanaafriyie
 */
public enum Major {

    CS("01:198", "SAS"), MATH("01:640",
            "SAS"), EE("14:332", "SOE"),
    ITI("04:547", "SC&I"), BAIT("33:136",
            "RBS");

    private final String coreCode;
    private final String school;

    /**
     * This constructor which is used to set the core code and school
     *
     * @param coreCode the core code for the major
     * @param school   the school associated with the major
     */
    Major(String coreCode, String school) {
        this.coreCode = coreCode;
        this.school = school;
    }

    /**
     * Getter method used for the school associated with the major
     *
     * @return returns the school for the major of the student
     */
    public String getSchool() {
        return school;
    }

    /**
     * Getter method for the core code of the students major
     *
     * @return returns the core code of the students major
     */
    public String getCoreCode() {
        return coreCode;
    }
}
