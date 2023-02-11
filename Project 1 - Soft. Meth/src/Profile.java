public class Profile implements Comparable { // implements Comparable allow us to compare objects of the same class
    private String lname;
    private String fname;
    private Date dob; //use the Date class described in (f)

    public static final int GREATER = 1;
    public static final int SMALLER = -1;
    public static final int EQUAL = 0;

    public Profile(String lname, String fname, Date dob) {
        this.lname = lname;
        this.fname = fname;
        this.dob = dob;
    }



    @Override
    public String toString(){
        return fname.substring(0,1).toUpperCase() + fname.substring(1) + " " + lname.substring(0,1).toUpperCase() + lname.substring(1) + " " + dob;
    }

    @Override
    public int compareTo(Object obj){

        String fName = this.fname;
        String lName = this.lname;
        Date dob = this.dob;

        Profile profile = (Profile)obj;

        String compareFirstName = profile.fname;
        String compareLastName = profile.lname;
        Date compareDate = profile.dob;


        if (lName.compareTo(compareLastName) < 0) {
                return SMALLER;
            } else if (lName.compareTo(compareLastName) > 0) {
                return GREATER;
            } else {
                if (fName.compareTo(compareFirstName) < 0) {
                    return SMALLER;
                } else if (fName.compareTo(compareFirstName) > 0) {
                    return GREATER;
                } else {
                    if (dob.compareTo(compareDate) < 0) {
                        return SMALLER;
                    } else if (dob.compareTo(compareDate) > 0) {
                        return GREATER;
                    } else {
                        return EQUAL;
                    }
                }
            }
    }

    public Date getDob() {
        return dob;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Profile) {
            Profile profile = (Profile) obj; //casting
            return (profile.lname.equalsIgnoreCase(this.lname)) && (profile.fname.equalsIgnoreCase(this.fname)) && (profile.dob.equals(this.dob));
        } else{
            return false;
        }
    }

}