package project2;

public abstract class Student implements Comparable<Student> {
...
    public boolean isValid(int creditEnrolled){} //polymorphism
    public abstract double tuitionDue(int creditsEnrolled); //polymorphism
    public abstract boolean isResident(); //polymorphism
}