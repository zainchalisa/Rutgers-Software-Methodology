package project2;

import org.junit.Test;

import static org.junit.Assert.*;

public class RosterTest {

    @Test
    public void addInternationalStudent_true() {
        Roster roster = new Roster();
        International student = new International(new Profile("Chalisa", "Zain", new Date("01/02/2002")), Major.CS,30);
        assertTrue(roster.add(student));
    }

    @Test
    public void addInternational_false() {
        Roster roster = new Roster();
        International student = new International(new Profile("Chalisa", "Zain", new Date("01/02/2002")), Major.CS,30);
        roster.add(student);
        assertFalse(roster.add(student));
    }
    @Test
    public void removeInternational_false(){
        Roster roster = new Roster();
        International student = new International(new Profile("Chalisa", "Zain", new Date("01/02/2002")), Major.CS,30);
        assertFalse(roster.remove(student));
    }

    @Test
    public void removeInternational_true(){
        Roster roster = new Roster();
        International student = new International(new Profile("Chalisa", "Zain", new Date("01/02/2002")), Major.CS,30);
        roster.add(student);
        assertTrue(roster.remove(student));
    }

    @Test
    public void addTriStateStudent_false() {
        Roster roster = new Roster();
        TriState student = new TriState(new Profile("Chalisa", "Zain", new Date("01/02/2002")), Major.CS, 30, "NY");
        roster.add(student);
        assertFalse(roster.add(student));
    }

    @Test
    public void addTriStateStudent_true() {
        Roster roster = new Roster();
        TriState student = new TriState(new Profile("Chalisa", "Zain", new Date("01/02/2002")), Major.CS, 30, "NY");
        assertTrue(roster.add(student));
    }

    @Test
    public void removeTriStateStudent_true() {
        Roster roster = new Roster();
        TriState student = new TriState(new Profile("Chalisa", "Zain", new Date("01/02/2002")), Major.CS, 30, "NY");
        roster.add(student);
        assertTrue(roster.remove(student));
    }

    @Test
    public void removeTriStateStudent_false() {
        Roster roster = new Roster();
        TriState student = new TriState(new Profile("Chalisa", "Zain", new Date("01/02/2002")), Major.CS, 30, "NY");
        assertFalse(roster.remove(student));
    }


}