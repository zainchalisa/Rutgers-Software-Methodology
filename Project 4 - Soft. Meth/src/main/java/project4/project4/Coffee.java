package project4.project4;

import javafx.collections.ObservableList;

/**
 * This class extends menu item and contains the methods neccesary to
 * create a coffee object
 *
 * @author zainchalisa
 * @author nanaafriyie
 *
 */
public class Coffee extends MenuItem {

    private String cupSize;
    private ObservableList<String> coffeeToppings;
    public static final String SHORT_CUP = "Short";
    public static final String TALL_CUP = "Tall";
    public static final String GRANDE_CUP = "Grande";
    public static final String VENTI_CUP = "Venti";
    public static final String SWEET_CREAM = "Sweet Cream";
    public static final String FRENCH_VANILLA = "French Vanilla";
    public static final String IRISH_CREAM = "Irish Cream";
    public static final String CARAMEL = "Caramel";
    public static final String MOCHA = "Mocha";
    public static final double STARTING_TOTAL = 0.00;
    public static final double SHORT_PRICE = 1.89;
    public static final double TALL_PRICE = 2.29;
    public static final double GRANDE_PRICE = 2.69;
    public static final double VENTI_PRICE = 3.09;
    public static final double TOPPING_PRICE = 0.30;

    /**
     * This constructor contains the necessary parameters to create a coffee
     * object
     * @param cupSize this is the coffee's cup size
     * @param quantity this is the quantity of coffee's being ordered
     * @param coffeeToppings this is the toppings put on the coffee
     */
    public Coffee (String cupSize, int quantity, ObservableList<String> coffeeToppings){
        super();
        this.cupSize = cupSize;
        this.coffeeToppings = coffeeToppings;
    }

    /**
     * This method gets the cupsize of the coffee
     * @return returns the coffee objects cup size
     */
    public String getCupSize() {
        return cupSize;
    }

    /**
     * This method gets the toppings of coffee and sets them to an
     * observable array list of strings
     * @return returns the arraylist of coffee toppings
     */
    public ObservableList<String> getCoffeeToppings() {
        return coffeeToppings;
    }

    /**
     * This method calculates how much it will cost for the additional
     * toppings added to the coffee
     * @return returns double value it will cost to add those toppings to
     * the coffee
     */
    private double sumToppings() {
        double runningSum = 0;
        for (String topping : getCoffeeToppings()) {
            runningSum += TOPPING_PRICE;
        }
        return runningSum;
    }

    /**
     * This method overrides the menu item class and gets the price for the
     * cup of coffee depending on the cup size
     * @return returns the cost of the coffee depending on the cupSize
     */
    @Override
    public double itemPrice() {
        if(cupSize.equals(SHORT_CUP)){
            return super.setItemPrice((SHORT_PRICE + sumToppings()) * super.getQuantity());
        }
        if(cupSize.equals(TALL_CUP)){
            return super.setItemPrice((TALL_PRICE + sumToppings()) * super.getQuantity());
        }
        if(cupSize.equals(GRANDE_CUP)){
            return super.setItemPrice((GRANDE_PRICE + sumToppings()) * super.getQuantity());
        }
        if(cupSize.equals(VENTI_CUP)){
            return super.setItemPrice((VENTI_PRICE + sumToppings()) * super.getQuantity());
        }
        return 0;
    }

    /**
     * This method overrides the toString() method for coffee
     * @return returns the string associated to the coffee object
     */
    @Override
    public String toString(){
        if(!getCoffeeToppings().isEmpty()){
            return "(" + getQuantity() + ") " + getCupSize() + " Coffee "
                    + getCoffeeToppings();
        } else{
            return "(" + getQuantity() + ") " + getCupSize() + " Coffee";
        }

    }

}
