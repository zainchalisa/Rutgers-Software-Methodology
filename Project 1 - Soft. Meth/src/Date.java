import java.util.Calendar;

public class Date implements Comparable {
    private int year;
    private int month;
    private int day;

    public static final int MONTH_ADDITIVE = 1;
    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;
    public static final int[] MONTH_DAYS = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public static final int FEBRUARY = 2;
    public static final int LEAP_YEAR_DAY = 28;
    public static final int LOWEST_DAY = 1;

    public static final int GREATER = 1;
    public static final int SMALLER = -1;
    public static final int EQUAL = 0;
    public static final int minAge = 1;

    public Date() {
        Calendar calendar = Calendar.getInstance();
        this.year = calendar.get(Calendar.YEAR);
        this.month = calendar.get(Calendar.MONTH) + MONTH_ADDITIVE;
        this.day = calendar.get(Calendar.DAY_OF_MONTH);

    }

    public Date(String date) {

        String[] dateArray = date.split("/", 3);
        this.month = Integer.parseInt(dateArray[0]);
        this.day = Integer.parseInt(dateArray[1]);
        this.year = Integer.parseInt(dateArray[2]);

    }

    public boolean isValid() { //check if a date is a valid calendar date

        if (this.month > 12 || this.month < 1) {
            return false;
        }

        if (this.month == FEBRUARY && isLeapYear() == false) {
            if (this.day > LEAP_YEAR_DAY) {
                return false;
            }
        } else if (this.day > MONTH_DAYS[this.month - MONTH_ADDITIVE] || this.month < LOWEST_DAY) {
            return false;
        }

        if(isValidStudent() == false){
            return false;
        }

        return true;
    }

    public boolean isValidStudent(){
        Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + MONTH_ADDITIVE;
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        int minYear = year - minAge;

        if(this.year <= minYear){
            return true;
        } else if (this.year == minYear){
            if (this.month <= month){
                return true;
            } else if (this.day <= day){
                return true;
            } else{
                return false;
            }
        } else{
            return false;
        }
    }

    private boolean isLeapYear() {

        if (this.year % QUADRENNIAL == 0) {
            if (this.year % CENTENNIAL == 0) {
                if(this.year % QUATERCENTENNIAL == 0) {
                    return true;
                } else{
                    return false;
                }
            }else{
                return true;
            }
        } else{
            return false;
        }
    }

    @Override
    public int compareTo(Object obj) {

        int year = this.year;
        int month = this.month;
        int day = this.day;

        Date date = (Date)obj;

        int compareYear = date.year;
        int compareMonth = date.month;
        int compareDay = date.day;

        if(year < compareYear){
            return SMALLER;
        } else if(year > compareYear){
            return GREATER;
        } else{
            if (month < compareMonth){
                return SMALLER;
            } else if(month > compareMonth){
                return GREATER;
            } else{
                if (day < compareDay){
                    return SMALLER;
                } else if (day > compareDay){
                    return GREATER;
                } else{
                    return EQUAL;
                }
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Date) {
            Date date = (Date) obj; //casting
            return (date.year == this.year) && (date.day == this.day) && (date.month == this.month);
        }
        return true;
    }


    @Override
    public String toString(){

        return month + "/" + day + "/" + year;
    }

    public static void main(String[] args){
        Date testCase1 = new Date("2/29/2003");
        Date testCase2 = new Date("4/31/2003");
        Date testCase3 = new Date("13/31/2003");
        Date testCase4 = new Date("3/32/2003");
        Date testCase5 = new Date("-1/31/2003");
        Date testCase6 = new Date("2/29/2017");
        System.out.println(testCase1.isValid());
        System.out.println(testCase2.isValid());
        System.out.println(testCase3.isValid());
        System.out.println(testCase4.isValid());
        System.out.println(testCase5.isValid());
        System.out.println(testCase5.isValidStudent());

    }
}