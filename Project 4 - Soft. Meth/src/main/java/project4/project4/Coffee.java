package project4.project4;

import javafx.collections.ObservableList;
import javafx.scene.control.Menu;

import java.util.ArrayList;

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

    public Coffee (String cupSize, int quantity, ObservableList<String> coffeeToppings){
        super();
        this.cupSize = cupSize;
        this.coffeeToppings = coffeeToppings;
    }

    public String getCupSize() {
        return cupSize;
    }

    public ObservableList<String> getCoffeeToppings() {
        return coffeeToppings;
    }

    private double sumToppings() {
        double runningSum = 0;
        for (String topping : getCoffeeToppings()) {
            runningSum += TOPPING_PRICE;
        }
        return runningSum;
    }

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
