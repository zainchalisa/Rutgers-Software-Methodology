package project5.project5;

public class DonutItem {
    private String donutName;
    private int image;
    private String donutPrice;

    public DonutItem(String donutName,int image,String donutPrice) {
        this.donutName = donutName;
        this.image = image;
        this.donutPrice = donutPrice;
    }

    public String getDonutName() {
        return donutName;
    }

    public int getImage() {
        return image;
    }

    public String getDonutPrice() {
        return donutPrice;
    }
}
