package project4.project4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Order {

    private static int orderNumber = -3;
    private int uniqueOrderNumber;
    private ObservableList<MenuItem> orderList;

    public ObservableList<String> storeOrderNumbers = FXCollections.observableArrayList();

    public Order (){
        orderList = FXCollections.observableArrayList();
        orderNumber++;
        this.uniqueOrderNumber = orderNumber;
    }

    public void setOrderList(ObservableList<MenuItem> orderList) {
        this.orderList = orderList;
    }

    public int getUniqueOrderNumber() {
        return uniqueOrderNumber;
    }

    public ObservableList<MenuItem> getOrder() {
        return orderList;
    }

    public boolean add (Object obj){
        if(obj instanceof MenuItem itemAdded){
            orderList.add(itemAdded);
            return true;
        } else{
            return false;
        }
    }

    public boolean remove (Object obj){
        if(obj instanceof MenuItem itemRemoved){
            orderList.remove(itemRemoved);
            return true;
        } else{
            return false;
        }
    }

    public double orderPrice(){
        double orderPrice = 0;
        for(MenuItem orderItem : orderList){
            orderPrice += orderItem.itemPrice();
        }
        return orderPrice;
    }

    public ObservableList<MenuItem> getOrderList(){
        return orderList;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public int getOrderNumber() {
        orderNumber++;
        return orderNumber;
    }

    @Override
    public String toString() {
        return "#" + uniqueOrderNumber;
    }
}
