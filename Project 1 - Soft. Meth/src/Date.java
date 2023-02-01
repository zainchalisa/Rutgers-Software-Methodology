
import java.util.Calendar;
import java.util.StringTokenizer;

public class Date implements Comparable {
    private int year;
    private int month;
    private int day;

    public static final int  MONTH_ADDITIVE = 1;

    public Date() { //create an object with today’s date (see Calendar class)

        Calendar calendar = Calendar.getInstance();
        this.year = calendar.get(Calendar.YEAR);
        this.month = calendar.get(Calendar.MONTH) + MONTH_ADDITIVE;
        this.day = calendar.get(Calendar.DAY_OF_MONTH);

    }
    public Date(String date) { //take “mm/dd/yyyy” and create a Date object

        String[] dateArray = date.split("/", 3);
        String day = dateArray[0];
        String month = dateArray[1];
        String year = dateArray[2];

    }
    public boolean isValid() { //check if a date is a valid calendar date




        return false;
    }

    @Override
    public int compareTo(Object obj){
        return 0;
    }

    @Override
    public String toString(){
        return month + "/" + day + "/" + year;
    }
}