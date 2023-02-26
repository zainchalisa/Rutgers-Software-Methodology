package project2;

import static org.junit.Assert.*;

public class ResidentTest {

    @org.junit.Test
    public void tuitionDue() {
        Resident student = new Resident(new Profile("Andrew", "Chacko", new Date ("01/04/2002")), Major.CS, 100, 0);
        assertEquals(9036.00, student.tuitionDue(24), 0.00);
    }
}