public enum Major {

    CS (SAS, 01:198),
    MATH (01:640, SAS),
    EE (14:332, SOE),
    ITI (04:547, SC&I),
    BAIT (33:136, RBS);

    private final string classCode;
    private final string schoolName;

    Major(string classCode, string schoolName){
        this.classCode = classCode;
        this.schoolName = schoolName;
    }



}