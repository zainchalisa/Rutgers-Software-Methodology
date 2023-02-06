
// git add - A
// git commit -m "message of what you added"
// git push



public class Main {
    public static void main(String[] args) {

        Student day = new Student( new Profile("Chalisa", "Zain", new Date("1/2/2002") ) , Major.CS, 191);
        //System.out.println(day);

        Student[] theRoster = new Student[4];

        Roster roster = new Roster();
        roster.add(day);
        roster.add(day);
        roster.add(day);
        roster.add(day);
        roster.add(day);
        System.out.println(roster.find(day));
        System.out.println(roster);




    }
}