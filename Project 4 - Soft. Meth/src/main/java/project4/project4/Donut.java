package project4.project4;

public class Donut extends MenuItem{

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

    public Donut (String type, String flavor){
        donutType = type;
        donutFlavor = flavor;
    }


    public String getDonutFlavor() {
        return this.donutFlavor;
    }

    public String getDonutType() {
        return this.donutType;
    }

    @Override
    public double itemPrice() {
        if (donutType.equals(YEAST_DONUT)){
            return super.setItemPrice(YEAST_DONUT_PRICE);
        }
        if(donutType.equals(CAKE_DONUT)){
            return super.setItemPrice(CAKE_DONUT_PRICE);
        }
        if(donutType.equals(DONUT_HOLES)){
            return super.setItemPrice(DONUT_HOLES_PRICE);
        }
        return 0;
    }

    @Override
    public String toString(){
        return "(" + getQuantity() + ") " + getDonutFlavor() + " "
                + getDonutType();
    }

    @Override
    public boolean equals(Object obj){
        if (obj instanceof Donut) {
            Donut donut = (Donut) obj;
            return (donut.donutFlavor.equals(this.donutFlavor) &&
                    donut.donutType.equals(this.donutType));
        } else {
            return false;
        }
    }
}
