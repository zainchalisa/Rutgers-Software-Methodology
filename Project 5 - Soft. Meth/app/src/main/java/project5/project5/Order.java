package project5.project5;

import android.view.Menu;

import androidx.databinding.ObservableArrayList;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class creates an order object holding the unique order id and the
 * menu items objects in an observable arraylist
 *
 * @author zainchalisa
 * @author nanaafriyie
 */
public class Order implements Serializable {
    private static int orderNumber = 0;

    public static final int ZERO = 0;
    private final int uniqueOrderNumber;
    private ObservableArrayList<MenuItem> orderList;

    /**
     * This constructor creates the unique orderID and the orderList
     * observable arraylist
     */
    public Order() {
        orderList = new ObservableArrayList<>();
        orderNumber++;
        this.uniqueOrderNumber = orderNumber;
    }

    /**
     * This method sets the orderList
     *
     * @param orderList the orderList which you're working with
     */
    public void setOrderList(ObservableArrayList<MenuItem> orderList) {
        this.orderList = orderList;
    }

    /**
     * This method gets the unique order id which is associated to the
     * current order list
     *
     * @return returns a unique number which cannot be reused
     */
    public int getUniqueOrderNumber() {
        return uniqueOrderNumber;
    }

    /**
     * This method gets the current observable list associated to the
     * menu item
     *
     * @return returns the current orderList you're working with
     */
    public ObservableArrayList<MenuItem> getOrder() {
        return orderList;
    }

    /**
     * This method adds the current Menu Item you're working with
     *
     * @param obj the obj you're calling to add into the list
     * @return returns a boolean depending on if the item was added or not
     */
    public boolean add(Object obj) {
        if (obj instanceof MenuItem itemAdded) {
            orderList.add(itemAdded);
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method removes the current Menu Item you're working with
     *
     * @param obj the obj you're calling to remove from the list
     * @return returns a boolean depending on if the item was
     * removed or not
     */
    public boolean remove(Object obj) {
        if (obj instanceof MenuItem itemRemoved) {
            orderList.remove(itemRemoved);
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method returns the overall order total cost
     *
     * @return returns the double value of the order total cost
     */
    public double orderPrice() {
        double orderPrice = ZERO;
        for (MenuItem orderItem : orderList) {
            orderPrice += orderItem.itemPrice();
        }
        return orderPrice;
    }


    /**
     * This method overrides the toString() for the order class
     *
     * @return returns the unique order id for the specific list
     */
    @Override
    public String toString() {
        return "#" + uniqueOrderNumber;
    }
}
