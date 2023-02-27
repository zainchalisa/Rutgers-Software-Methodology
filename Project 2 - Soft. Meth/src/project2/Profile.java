package project2;

/**
 * This class creates Profile objects with First/Last Name and DOB.
 * @author zainchalisa
 * @author nanaafriyie
 */
public class Profile implements Comparable { // implements Comparable
    // allow us to compare objects of the same class
    private String lname;
    private String fname;
    private Date dob; //use the Date class described in (f)

    public static final int GREATER = 1;
    public static final int SMALLER = -1;
    public static final int EQUAL = 0;

    /**
     * Constructor is made to set the inputted first/last name and the dob
     *
     * @param lname the last name of the student
     * @param fname the first name of the student
     * @param dob   the date of birth of the student
     */
    public Profile(String lname, String fname, Date dob) {
        this.lname = lname;
        this.fname = fname;
        this.dob = dob;
    }

    /**
     * Overrides the toString() method for the profile class
     *
     * @return returns the profile string
     */
    @Override
    public String toString() {
        return fname.substring(0, 1).toUpperCase() + fname.substring(
                1) + " " + lname.substring(0, 1).toUpperCase()
                + lname.substring(1) + " " + dob;
    }

    /**
     * Overrides the compareTo() method for the Profile class
     * @param obj the object to be compared.
     * @return returns if profile is less, greater, or equal to another
     */
    @Override
    public int compareTo(Object obj) {

        String fName = this.fname;
        String lName = this.lname;
        Date dob = this.dob;

        Profile profile = (Profile) obj;

        String compareFirstName = profile.fname;
        String compareLastName = profile.lname;
        Date compareDate = profile.dob;

        if (lName.compareTo(compareLastName) < EQUAL) {
            return SMALLER;
        } else if (lName.compareTo(compareLastName) > EQUAL) {
            return GREATER;
        } else {
            if (fName.compareTo(compareFirstName) < EQUAL) {
                return SMALLER;
            } else if (fName.compareTo(compareFirstName) > EQUAL) {
                return GREATER;
            } else {
                if (dob.compareTo(compareDate) < EQUAL) {
                    return SMALLER;
                } else if (dob.compareTo(compareDate) > EQUAL) {
                    return GREATER;
                } else {
                    return EQUAL;
                }
            }
        }
    }

    /**
     * Getter method used to get the date of birth of a student
     *
     * @return returns the date of birth
     */
    public Date getDob() {
        return dob;
    }

    /**
     * Overrides the equals method for the profile class
     *
     * @param obj which object is checked if it's equal to another
     * @return returns false if the profiles are not the same
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Profile) {
            Profile profile = (Profile) obj; //casting
            return (profile.lname.equalsIgnoreCase(this.lname)) &&
                    (profile.fname.equalsIgnoreCase(this.fname)) &&
                    (profile.dob.equals(this.dob));
        } else {
            return false;
        }
    }

}