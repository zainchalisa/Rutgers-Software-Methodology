public enum Major {

    CS ("01:198","SAS"),
    MATH ("01:640","SAS"),
    EE ("14:332","SOE"),
    ITI ("04:547","SC&I"),
    BAIT ("33:136","RBS");

    private final String coreCode;
    private final String school;

    Major(String coreCode, String school) {
        this.coreCode = coreCode;
        this.school = school;
    }

    public String getSchool() {
        return school;
    }

    public String getCoreCode() {
        return coreCode;
    }
}
