package project4.project4;

/**
 * This class extends menu item to create a donut object
 *
 * @author zainchalisa
 * @author nanaafriyie
 */
public class Donut extends MenuItem {

    public String donutType;
    public String donutFlavor;
    private static final String YEAST_DONUT = "Yeast Donut";
    private static final String CAKE_DONUT = "Cake Donut";
    private static final String DONUT_HOLES = "Donut Holes";
    private static final String CHOCOLATE = "Chocolate";
    private static final String VANILLA = "Vanilla";
    private static final String JELLY = "Jelly";
    private static final String SUGAR = "Sugar";
    private static final String GLAZED = "Glazed";
    private static final String MAPLE_ICED = "Maple Iced";
    private static final String BOSTON_CREME = "Boston Creme";
    private static final String BLUEBERRY = "Blueberry";
    private static final String STRAWBERRY = "Strawberry";
    private static final double YEAST_DONUT_PRICE = 1.59;
    private static final double CAKE_DONUT_PRICE = 1.79;
    private static final double DONUT_HOLES_PRICE = 0.39;

    /**
     * This constructor creates the donut object
     *
     * @param type   this is the donut type (yeast, cake, or donut hole)
     * @param flavor this is the donut flavor
     */
    public Donut(String type, String flavor) {
        donutType = type;
        donutFlavor = flavor;
    }

    /**
     * This gets the donut objects flavor
     *
     * @return returns the flavor of the donut in a string value
     */
    public String getDonutFlavor() {
        return this.donutFlavor;
    }

    /**
     * This gets the donut objects type
     *
     * @return returns the donut type in a string value
     */
    public String getDonutType() {
        return this.donutType;
    }

    /**
     * This method calculates the price of the donut based on the donut
     * type
     *
     * @return returns the price of the donut based off the donut type
     * selected
     */
    @Override
    public double itemPrice() {
        if (donutType.equals(YEAST_DONUT)) {
            return super.setItemPrice(YEAST_DONUT_PRICE * super.
                    getQuantity());
        }
        if (donutType.equals(CAKE_DONUT)) {
            return super.setItemPrice(CAKE_DONUT_PRICE * super.
                    getQuantity());
        }
        if (donutType.equals(DONUT_HOLES)) {
            return super.setItemPrice(DONUT_HOLES_PRICE * super.
                    getQuantity());
        }
        return 0;
    }

    /**
     * This method is the toString() for the donut object
     *
     * @return returns the string for how the donut objects are printed
     */
    @Override
    public String toString() {
        return "(" + getQuantity() + ") " + getDonutFlavor() + " " +
                getDonutType();
    }

    /**
     * This method checks if two donut objects are the same
     *
     * @param obj Object which we're comparing the donut object to
     * @return returns the boolean value if the two objects are equal to
     * each other
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Donut donut) {
            return (donut.donutFlavor.equals(this.donutFlavor) && donut.
                    donutType.equals(this.donutType));
        } else {
            return false;
        }
    }
}
