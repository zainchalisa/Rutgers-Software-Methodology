
// git add - A
// git commit -m "message of what you added"
// git push



public class Main {
    public static void main(String[] args) {

        Student studentOne = new Student( new Profile("Chalisa", "Zain", new Date("1/2/2002") ) , Major.CS, 99);
        Student studentTwo = new Student( new Profile("Chacko", "Andrew", new Date("1/4/2002") ) , Major.BAIT, 120);
        Student studentThree = new Student( new Profile("Bokka", "Abhitej", new Date("10/24/2002") ) , Major.EE, 76);
        Student studentFour = new Student( new Profile("Reddy", "Akhil", new Date("10/24/2002") ) , Major.MATH, 76);
        Student studentFifth = new Student( new Profile("Patel", "Kush", new Date("04/17/2002") ) , Major.ITI, 76);

        //System.out.println(day);

        Student[] theRoster = new Student[4];

        Roster roster = new Roster();
        roster.add(studentOne);
        roster.add(studentTwo);
        roster.add(studentThree);
        roster.add(studentFour);
        roster.add(studentFifth);


        //roster.remove(studentOne);
        //roster.remove(studentTwo);
        roster.printBySchoolMajor();
        //roster.print();

        //System.out.println(roster.find(studentOne));





    }
}