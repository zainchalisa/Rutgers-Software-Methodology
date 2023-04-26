package project5.project5;

/**
 * This class extends Donut to create donutItems to use
 *
 * @author nanaafriyie
 * @author zainchalisa
 */
public class DonutItem extends Donut {
    private String donutName;
    private int image;
    private String donutType;


    /**
     * The constructor to create DonutItem objects
     * @param donutName string for the donut flavor and donut type
     * @param price the price of the DonutItem
     * @param quantity the amount of donuts in the item
     * @param donutType the type of donut being ordered
     */
    public DonutItem(String donutName, double price, int quantity,
                     String donutType) {
        super();
        this.setItemPrice(price);
        this.donutName = donutName;
        super.setQuantity(quantity);
        this.donutType = donutType;
    }


    /**
     * Overloaded DonutItem constructor used when passing images inside
     * @param donutName string for the donut flavor and donut type
     * @param image the image associated with this specific donut
     * @param price the price of the DonutItem
     * @param donutType the type of donut being ordered
     */
    public DonutItem(String donutName, int image, double price,
                     String donutType) {
        super();
        super.setItemPrice(price);
        this.donutName = donutName;
        this.image = image;
        this.donutType = donutType;
    }

    /**
     * Getter method to obtain the name of the DonutItem
     * @return string containing the donut name
     */
    public String getDonutName() {
        return donutName;
    }

    /**
     * Getter method to obtain image used for DonutItem
     * @return integer representing the image of the DonutItem
     */
    public int getImage() {
        return image;
    }

    /**
     * Getter method that returns the type of donut the object is
     * @return string containing the type of donut
     */
    public String getDonutType() {
        return donutType;
    }

    /**
     * Calculates the price of an object
     * @return double value containing the price of the object
     */
    @Override
    public double itemPrice() {
        if (donutType.contains(YEAST_DONUT)) {
            return super.setItemPrice(YEAST_DONUT_PRICE * super.
                    getQuantity());
        }
        if (donutType.contains(CAKE_DONUT)) {
            return super.setItemPrice(CAKE_DONUT_PRICE * super.
                    getQuantity());
        }
        if (donutType.contains(DONUT_HOLES)) {
            return super.setItemPrice(DONUT_HOLES_PRICE * super.
                    getQuantity());
        }
        return 0;
    }

    /**
     * Return a string denoting the quantity and type of donut of object
     * @return string with object quantity and name
     */
    @Override
    public String toString() {
        return "(" + getQuantity() + ") " + getDonutName();
    }


    /**
     * Compares if two DonutItems are the same
     * @param obj Object which we're comparing the donut object to
     * @return boolean value determing if the objects are equal to each
     *         other
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DonutItem) {
            return this.donutName.equals(((DonutItem) obj).donutName);
        } else {
            return false;
        }
    }
}


