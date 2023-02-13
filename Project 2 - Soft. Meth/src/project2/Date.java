package project2;

import java.util.Calendar;

/**
 * This class implements date processing and implementations
 * @author zainchalisa
 * @author nanaafriyie
 */
public class Date implements Comparable {
    private int year;
    private int month;
    private int day;
    public static final int MONTH_ADDITIVE = 1;
    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;
    public static final int[] MONTH_DAYS = {31, 28, 31, 30, 31, 30, 31,
            31, 30, 31, 30, 31};
    public static final int FEBRUARY = 2;
    public static final int LEAP_YEAR_DAY = 29;
    public static final int LOWEST_DAY = 1;
    public static final int GREATER = 1;
    public static final int SMALLER = -1;
    public static final int EQUAL = 0;
    public static final int minAge = 16;

    /**
     * This is a constructor which fetches the current date
     */
    public Date() {
        Calendar calendar = Calendar.getInstance();
        this.year = calendar.get(Calendar.YEAR);
        this.month = calendar.get(Calendar.MONTH) + MONTH_ADDITIVE;
        this.day = calendar.get(Calendar.DAY_OF_MONTH);

    }

    /**
     * This method splits the date string inputted by the user
     * @param date is the inputted string by the user
     */
    public Date(String date) {

        String[] dateArray = date.split("/", 3);
        this.month = Integer.parseInt(dateArray[0]);
        this.day = Integer.parseInt(dateArray[1]);
        this.year = Integer.parseInt(dateArray[2]);

    }

    /**
     * This method validates if the date string entered is valid
     * @return returns if the date is a valid date
     */
    public boolean isValid() { //check if a date is a valid calendar date

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + MONTH_ADDITIVE;
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        if (this.month > 12 || this.month < 1) {
            return false;
        }

        if (this.month == FEBRUARY && isLeapYear() == true) {
            if (this.day > LEAP_YEAR_DAY || this.day < LOWEST_DAY) {
                return false;
            }
        } else if (this.day > MONTH_DAYS[this.month - MONTH_ADDITIVE] ||
                this.day < LOWEST_DAY) {
            return false;
        }

        if(this.year > year && this.month > month && this.day > day ||
                this.year < EQUAL){
            return false;
        }
        return true;
    }

    /**
     * This method checks if the student is exactly 16 older or younger,
     * @return returns if the students is valid based on their age
     */
    public boolean isValidStudent() {
        Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + MONTH_ADDITIVE;
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        int minYear = year - minAge;

        if (this.year < minYear) {
            return true;
        } else if (this.year > minYear) {
            return false;
        }

        if (this.year == minYear) {
            if (this.month < month) {
                return true;
            } else if (this.month == month) {
                if (this.day <= day) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * This method checks if the year is a leap year
     * @return if the year is a leap year or not
     */
    private boolean isLeapYear() {

        if (this.year % QUADRENNIAL == 0) {
            if (this.year % CENTENNIAL == 0) {
                if (this.year % QUATERCENTENNIAL == 0) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    /**
     * Overrides the compareTo() method for the Date object
     * @param obj the object to be compared.
     * @return returns if the date is greater, smaller, or equal to another
     */
    @Override
    public int compareTo(Object obj) {

        int year = this.year;
        int month = this.month;
        int day = this.day;

        Date date = (Date) obj;

        int compareYear = date.year;
        int compareMonth = date.month;
        int compareDay = date.day;

        if (year < compareYear) {
            return SMALLER;
        } else if (year > compareYear) {
            return GREATER;
        } else {
            if (month < compareMonth) {
                return SMALLER;
            } else if (month > compareMonth) {
                return GREATER;
            } else {
                if (day < compareDay) {
                    return SMALLER;
                } else if (day > compareDay) {
                    return GREATER;
                } else {
                    return EQUAL;
                }
            }
        }
    }

    /**
     * Overrides the equals() method for the Date object
     * @param obj compared to another date object
     * @return return false if the dates are not the same
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Date) {
            Date date = (Date) obj;
            return (date.year == this.year) && (date.day == this.day) &&
                    (date.month == this.month);
        }
        return true;
    }

    /**
     * Overrides the toString() method for date objects
     * @return returns the date as a string
     */
    @Override
    public String toString() {
        return month + "/" + day + "/" + year;
    }

    /**
     * Main method tests the funtionality of the Date method
     * @param args takes in arguments to test the Date file
     */
    public static void main(String[] args) {
        Date testCase1 = new Date("2/29/2003");
        Date testCase2 = new Date("4/31/2003");
        Date testCase3 = new Date("13/31/2003");
        Date testCase4 = new Date("3/32/2003");
        Date testCase5 = new Date("-1/31/2003");
        Date testCase6 = new Date("02/13/2007");
        Date testCase7 = new Date("2/29/1996");
        System.out.println("Test Case 1: " + testCase1.isValid());
        System.out.println("Test Case 2: " + testCase2.isValid());
        System.out.println("Test Case 3: " + testCase3.isValid());
        System.out.println("Test Case 4: " + testCase4.isValid());
        System.out.println("Test Case 5: " + testCase5.isValid());
        System.out.println("Test Case 6: " + testCase6.isValid());
        System.out.println("Test Case 7: " + testCase7.isValid());
    }
}