package project4.project4;

import javafx.scene.control.Menu;

public class Coffee extends MenuItem {

    private String cupSize;

    private String coffeeToppings[];

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
        if(cupSize.equals(SHORT_CUP)){
            return super.setItemPrice(SHORT_PRICE);
        }
        if(cupSize.equals(TALL_CUP)){
            return super.setItemPrice(TALL_PRICE);
        }
        if(cupSize.equals(GRANDE_CUP)){
            return super.setItemPrice(GRANDE_PRICE);
        }
        if(cupSize.equals(VENTI_CUP)){
            return super.setItemPrice(VENTI_PRICE);
        }
        return 0;
    }

}
