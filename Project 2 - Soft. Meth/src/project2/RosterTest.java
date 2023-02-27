package project2;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * This class checks the add and remove methods in roster
 * @author zainchalisa
 * @author nanaafriyie
 */
public class RosterTest {

    /**
     * Tests if you can appropriately add a international student
     */
    @Test
    public void addInternationalStudent_true() {
        Roster roster = new Roster();
        International student = new International(new Profile(
                "Chalisa",
                "Zain", new Date("01/02/2002")), Major.CS,
                30);
        assertTrue(roster.add(student));
    }

    /**
     * Tests that you cannot add the same international student
     */
    @Test
    public void addInternational_false() {
        Roster roster = new Roster();
        International student = new International(new Profile(
                "Chalisa",
                "Zain", new Date("01/02/2002")), Major.CS,
                30);
        roster.add(student);
        assertFalse(roster.add(student));
    }

    /**
     * Tests that you cannot remove a international student not in the list
     */
    @Test
    public void removeInternational_false() {
        Roster roster = new Roster();
        International student = new International(new Profile(
                "Chalisa",
                "Zain", new Date("01/02/2002")), Major.CS,
                30);
        assertFalse(roster.remove(student));
    }

    /**
     * Tests that you can remove a international student from the list
     */
    @Test
    public void removeInternational_true() {
        Roster roster = new Roster();
        International student = new International(new Profile(
                "Chalisa",
                "Zain", new Date("01/02/2002")), Major.CS,
                30);
        roster.add(student);
        assertTrue(roster.remove(student));
    }

    /**
     * Tests that you cant add the same tri-state student into the list
     */
    @Test
    public void addTriStateStudent_false() {
        Roster roster = new Roster();
        TriState student = new TriState(new Profile("Chalisa",
                "Zain",
                new Date("01/02/2002")), Major.CS, 30,
                "NY");
        roster.add(student);
        assertFalse(roster.add(student));
    }

    /**
     * Tests that you can add a tri-state student into the list
     */
    @Test
    public void addTriStateStudent_true() {
        Roster roster = new Roster();
        TriState student = new TriState(new Profile("Chalisa",
                "Zain",
                new Date("01/02/2002")), Major.CS, 30,
                "NY");
        assertTrue(roster.add(student));
    }

    /**
     * Tests that you can remove a tri-state student into the list
     */
    @Test
    public void removeTriStateStudent_true() {
        Roster roster = new Roster();
        TriState student = new TriState(new Profile("Chalisa",
                "Zain",
                new Date("01/02/2002")), Major.CS, 30,
                "NY");
        roster.add(student);
        assertTrue(roster.remove(student));
    }

    /**
     * Tests that you cant remove a student that does not exist from the
     * list
     */
    @Test
    public void removeTriStateStudent_false() {
        Roster roster = new Roster();
        TriState student = new TriState(new Profile(
                "Chalisa", "Zain",
                new Date("01/02/2002")), Major.CS, 30,
                "NY");
        assertFalse(roster.remove(student));
    }


}