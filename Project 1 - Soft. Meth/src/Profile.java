public class Profile implements Comparable { // implements Comparable allow us to compare objects of the same class
    private String lname;
    private String fname;
    private Date dob; //use the Date class described in (f)

    public Profile(String lname, String fname, Date dob) {
        this.lname = lname;
        this.fname = fname;
        this.dob = dob;
    }

    @Override
    public String toString(){
        return lname + "," + fname + "," + dob;
    }

    @Override
    public int compareTo(Object obj){
        return 0;
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