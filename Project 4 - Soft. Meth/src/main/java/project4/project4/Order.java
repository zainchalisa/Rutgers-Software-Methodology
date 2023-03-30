package project4.project4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Order {

    private int orderNumber;
    private ObservableList<MenuItem> orderList;

    public Order (){
        orderList = FXCollections.observableArrayList();
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
            orderPrice += orderItem.itemPrice() * orderItem.getQuantity();
        }
        return orderPrice;
    }

    public ObservableList<MenuItem> getOrderList(){
        return orderList;
    }

}
