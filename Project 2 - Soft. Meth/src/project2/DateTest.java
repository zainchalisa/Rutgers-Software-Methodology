package project2;

import org.junit.Test;

import static org.junit.Assert.*;

public class DateTest {

    @Test
    public void test_daysInFeb_nonLeap() {
        Date testCase1 = new Date("2/29/2003");
        assertFalse(testCase1.isValid());
    }
    @Test
    public void test_daysInMonth() {
        Date testCase2 = new Date("4/31/2003");
        assertFalse(testCase2.isValid());
    }

    @Test
    public void test_numberOfMonth() {
        Date testCase3 = new Date("13/31/2003");
        assertFalse(testCase3.isValid());
    }

    @Test
    public void test_daysInMonth_ofLeapYear() {
        Date testCase4 = new Date("3/32/2003");
        assertFalse(testCase4.isValid());
    }
    @Test
    public void test_negativeMonth() {
        Date testCase5 = new Date("-1/31/2003");
        assertFalse(testCase5.isValid());
    }
    @Test
    public void test_validDate() {
        Date testCase6 = new Date("02/13/2007");
        assertTrue(testCase6.isValid());
    }
    @Test
    public void test_validDate2() {
        Date testCase7 = new Date("2/29/1996");
        assertTrue(testCase7.isValid());
    }


}