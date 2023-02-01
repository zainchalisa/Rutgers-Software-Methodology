public class Profile implements Comparable<Profile> { // implements Comparable allow us to compare objects of the same class
    private String lname;
    private String fname;
    private Date dob; //use the Date class described in (f)

    public Profile(String lname, String fname, Date dob) {
        this.lname = lname;
        this.fname = fname;
        this.dob = new Date(dob);
    }

    @Override
    public String toString(){
        return null;
    }

    @Override
    public int compareTo(Object obj){
        return 0;
    }
    @Override
    public boolean equals(Object obj) {
        return false;
    }

}