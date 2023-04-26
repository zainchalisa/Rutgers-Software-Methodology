package project5.project5;

public class DonutItem extends Donut {
    private String donutName;
    private int image;
    private String donutType;


    public DonutItem(String donutName,double price,int quantity,String donutType) {
        super();
        this.setItemPrice(price);
        this.donutName = donutName;
        super.setQuantity(quantity);
        this.donutType = donutType;
    }


    public DonutItem(String donutName,int image,double price,String donutType) {
        super();
        super.setItemPrice(price);
        this.donutName = donutName;
        this.image = image;
        this.donutType = donutType;
    }

    public String getDonutName() {
        return donutName;
    }

    public int getImage() {
        return image;
    }

    public String getDonutType() {
        return donutType;
    }

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

    @Override
    public String toString() {
        return "(" + getQuantity() + ") " + getDonutName();
    }

    /*
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DonutItem) {
            return this.donutName.equals(((DonutItem) obj).donutName);
        } else {
            return false;
        }
    }

     */
}


