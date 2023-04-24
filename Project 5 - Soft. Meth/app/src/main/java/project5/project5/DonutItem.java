package project5.project5;

public class DonutItem {
    private String donutName;
    private int image;
    private double donutPrice;

    private int quantity;

    private static final int DEFAULT_QUANTITY = 1;

    public DonutItem(String donutName,int image,double donutPrice) {
        this.donutName = donutName;
        this.image = image;
        this.donutPrice = donutPrice;
        this.quantity = DEFAULT_QUANTITY;
    }

    public String getDonutName() {
        return donutName;
    }

    public int getImage() {
        return image;
    }

    public double getDonutPrice() {
        return donutPrice;
    }

    public int getQuantity() { return quantity; }

    public double getTotalPrice() {
        return donutPrice * quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setDonutPrice (double donutPrice) { this.donutPrice = donutPrice; }
}
