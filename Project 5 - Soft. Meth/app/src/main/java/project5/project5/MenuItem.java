package project4.project4;

/**
 * This class is the abstract and used to create the Donut and Coffee
 * objects
 *
 * @author zainchalisa
 * @author nanaafriyie
 *
 */
public abstract class MenuItem {

    public abstract double itemPrice();

    private double price;
    private int quantity;

    /**
     * Default constructor since this is the superclass for the Donut and
     * Coffee objects
     */
    public MenuItem() {
    }

    /**
     * This method sets the item price of the object
     *
     * @param priceOfItem the price for the item
     * @return returns the price of the given object
     */
    public double setItemPrice(double priceOfItem) {
        return this.price = priceOfItem;
    }

    /**
     * This method sets the quantity of the object
     *
     * @param quantity the given quantity of the current object
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * This method gets the quantity of the given object
     *
     * @return returns the quantity of the given menu item
     */
    public int getQuantity() {
        return quantity;
    }
}
