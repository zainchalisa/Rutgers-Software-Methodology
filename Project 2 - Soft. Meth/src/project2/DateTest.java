package project2;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * This class tests isValid() in the date class
 * @author zainchalisa
 * @author nanaafriyie
 */
public class DateTest {

    /**
     * This test checks if the date is valid for a leap year in feb
     */
    @Test
    public void test_daysInFeb_nonLeap() {
        Date testCase1 = new Date("2/29/2003");
        assertFalse(testCase1.isValid());
    }

    /**
     * This test case tests if the days of the month are vaild
     */
    @Test
    public void test_daysInMonth() {
        Date testCase2 = new Date("4/31/2003");
        assertFalse(testCase2.isValid());
    }

    /**
     * This test case checks to see if the month is accurate
     */
    @Test
    public void test_numberOfMonth() {
        Date testCase3 = new Date("13/31/2003");
        assertFalse(testCase3.isValid());
    }

    /**
     * this test case checks if the days of the month are accurate
     */
    @Test
    public void test_daysInMonth_ofLeapYear() {
        Date testCase4 = new Date("3/32/2003");
        assertFalse(testCase4.isValid());
    }

    /**
     * This test case checks if the negative month is accurate
     */
    @Test
    public void test_negativeMonth() {
        Date testCase5 = new Date("-1/31/2003");
        assertFalse(testCase5.isValid());
    }

    /**
     * This test case checks for a valid date
     */
    @Test
    public void test_validDate() {
        Date testCase6 = new Date("02/13/2007");
        assertTrue(testCase6.isValid());
    }

    /**
     * This test case checks for a valid date
     */
    @Test
    public void test_validDate2() {
        Date testCase7 = new Date("2/29/1996");
        assertTrue(testCase7.isValid());
    }


}