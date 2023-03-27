package project4.project4;

import javafx.scene.control.Menu;

public class Coffee extends MenuItem {

    private String cupSize;

    private String coffeeToppings[];

    public static final String SMALL_CUP = "Short";
    public static final String MEDIUM_CUP = "Grande";
    public static final String LARGE_CUP = "Venti";
    public static final String SWEET_CREAM = "Sweet Cream";
    public static final String FRENCH_VANILLA = "French Vanilla";
    public static final String IRISH_CREAM = "Irish Cream";
    public static final String CARAMEL = "Caramel";
    public static final String MOCHA = "Mocha";

    public static final double SMALL_PRICE = 1.89;
    public static final double MEDIUM_PRICE = 2.29;
    public static final double LARGE_PRICE = 2.69;

    public Coffee (String cupSize, String[] coffeeToppings){
        this.cupSize = cupSize;
        this.coffeeToppings = coffeeToppings;
    }

    public String getCupSize() {
        return cupSize;
    }

    public String[] getCoffeeToppings() {
        return coffeeToppings;
    }

    @Override
    public double itemPrice() {
        if(cupSize.equals(SMALL_CUP)){
            return super.setItemPrice(SMALL_PRICE);
        }
        if(cupSize.equals(MEDIUM_CUP)){
            return super.setItemPrice(MEDIUM_PRICE);
        }
        if(cupSize.equals(LARGE_CUP)){
            return super.setItemPrice(LARGE_PRICE);
        }
        return 0;
    }

}
