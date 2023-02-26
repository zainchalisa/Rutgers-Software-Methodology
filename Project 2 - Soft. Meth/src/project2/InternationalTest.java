package project2;

import org.junit.Test;

import static org.junit.Assert.*;

public class InternationalTest {

    @Test
    public void tuitionDue_studyNotAbroad() {
        International student1 = new International(new Profile("John", "Doe", new Date ("4/3/2003")), Major.CS, 30);
        assertEquals(35655, student1.tuitionDue(12), 0.00);
    }

    @Test
    public void tuitionDue_studyAbroad() {
        International student2 = new International(new Profile("John", "Doe", new Date ("4/3/2003")), Major.CS, 30, true);
        assertEquals(5918, student2.tuitionDue(12), 0.00);

    }



}