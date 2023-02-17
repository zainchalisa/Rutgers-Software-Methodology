package project2;



public abstract class Student implements Comparable {

    // Made visibility type protected so subclasses can access
    // these instance values
    protected Profile profile;
    protected Major major;
    protected int creditCompleted;

    // Superclass needs default constructor for subclass to call on

    /**
     * This method sets the profile based off user inputs
     * @param profile profile of the student (first, last, dob)
     */
    public Student(Profile profile) {
        this.profile = profile;
    }

    /**
     * Creates a student object with the given parameters
     * @param profile profile of the student (first, last, dob)
     * @param major major of the student
     * @param creditCompleted credits completed towards major of student
     */
    public Student(Profile profile, Major major, int creditCompleted) {
        this.profile = profile;
        this.major = major;
        this.creditCompleted = creditCompleted;
    }

    public boolean isValid(int creditEnrolled) {
        if(creditEnrolled < 3 || creditEnrolled > 24){
            return false;
        } else{
            return true;
        }
    } //polymorphism // don't have to override in all subclasses
    public abstract double tuitionDue(int creditsEnrolled); //polymorphism // implement differently in subclasses
    public abstract boolean isResident(); //polymorphism

    /**
     * Getter method for credits the student completed
     * @return returns the credits the student completed
     */
    public int getCreditCompleted() {
        return this.creditCompleted;
    }

    /**
     * Provides the standing of thr student depending on credits completed
     * @return returns the standing of the student
     */
    public Standing getStanding() {

        int credits = this.creditCompleted;
        if (credits < 30) {
            return Standing.Freshman;
        } else if (credits >= 30 && credits < 60) {
            return Standing.Sophomore;
        } else if (credits >= 60 && credits < 90) {
            return Standing.Junior;
        } else {
            return Standing.Senior;
        }
    }

    /**
     * Getter method for the students profile
     * @return returns the profile of the student
     */
    public Profile getProfile() {
        return this.profile;
    }

    /**
     * Getter method for the students major
     * @return returns the major of the student
     */
    public Major getMajor() {
        return this.major;
    }

    /**
     * Setter method used to change the students major
     * @param major major which your changing
     */
    public void setMajor(Major major) {
        this.major = major;
    }

    /**
     * Overrides the toString() method for the student object
     * @return returns the student object
     */
    @Override
    public String toString() {
        return "" + profile + " " + "(" + getMajor().getCoreCode() + " " +
                major + " " + getMajor().getSchool() + ") " +
                "credits completed: " + creditCompleted + " (" +
                getStanding() + ")";
    }

    /**
     * Compares two student objects to one another
     * @param obj the object to be compared.
     * @return returns if the object is greater, less, or equal
     */
    public int compareTo(Object obj) {
        Student student = (Student) obj;
        if (student.profile != null) {
            return this.profile.compareTo(student.profile);
        } else {
            return 1;
        }
    }

    /**
     * Overrides the equals method for a student object
     * @param obj which object is checked if it's equal to another
     * @return return false if it's not a student
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Student) {
            Student student = (Student) obj;
            return (student.profile.equals(this.profile));
        } else {
            return false;
        }
    }


    public static void main(String[] args) {
        /*
        Student firstStudent = new Student(new Profile("Chalisa",
                "Zain", (new Date("01/02/2002"))));
        Student secondStudent = new Student(new Profile("Afriyie",
                "Nana", (new Date("10/24/2002"))));
        System.out.println("Comparing two students which are equal " +
                "to each other: " + firstStudent.compareTo(firstStudent));
        System.out.println("Comparing two students where one is " +
                "greater than the other: " + firstStudent.compareTo
                (secondStudent));
        System.out.println("Comparing two students where one " +
                "is less than the other: " + secondStudent.compareTo
                (firstStudent));
         */
    }

}