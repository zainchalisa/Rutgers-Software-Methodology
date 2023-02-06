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
    public static final int LEAP_YEAR_DAY = 29;
    public static final int LOWEST_DAY = 1;


    public Date() { //create an object with today’s date (see Calendar class)

        Calendar calendar = Calendar.getInstance();
        this.year = calendar.get(Calendar.YEAR);
        this.month = calendar.get(Calendar.MONTH) + MONTH_ADDITIVE;
        this.day = calendar.get(Calendar.DAY_OF_MONTH);

    }

    public Date(String date) { //take “mm/dd/yyyy” and create a Date object

        String[] dateArray = date.split("/", 3);
        this.month = Integer.parseInt(dateArray[0]);
        this.day = Integer.parseInt(dateArray[1]);
        this.year = Integer.parseInt(dateArray[2]);

    }

    public boolean isValid() { //check if a date is a valid calendar date

        if (this.month > 12 || this.month < 1) {
            return false;
        }

        if (this.month == FEBRUARY && isLeapYear()) {
            if (this.day > LEAP_YEAR_DAY) {
                return false;
            }
        } else if (this.month > MONTH_DAYS[this.month - MONTH_ADDITIVE] || this.month < LOWEST_DAY) {
            return false;
        }

        return true;
    }

    private boolean isLeapYear() {

        if (this.year % QUADRENNIAL != 0) {
            return false;
        }

        if (this.year % CENTENNIAL != 0) {
            return false;
        }

        if (this.year % QUATERCENTENNIAL != 0) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Object obj) {
        return 0;
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
}