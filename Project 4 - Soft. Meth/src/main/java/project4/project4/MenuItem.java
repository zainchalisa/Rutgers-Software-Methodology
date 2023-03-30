package project4.project4;

public abstract class MenuItem {

    public abstract double itemPrice();

    private double price;
    private int quantity;

    public static final int initialQuantity = 1;

    public MenuItem(){}

    public double setItemPrice(double priceOfItem){
        return this.price = priceOfItem;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}
