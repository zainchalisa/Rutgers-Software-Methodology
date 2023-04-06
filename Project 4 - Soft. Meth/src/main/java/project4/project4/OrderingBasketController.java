package project4.project4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.text.DecimalFormat;

/**
 * This class is the class which manages the JavaFX portion for the basket
 *
 * @author zainchalisa
 * @author nanaafriyie
 *
 */
public class OrderingBasketController {

    public static final double SALES_TAX = .06625;

    public static final int ZERO = 0;

    private static CafeStoreMainController mainController;

    private static final ObservableList<MenuItem> currentOrders =
            FXCollections.observableArrayList();

    /**
     * This is a list of menu items which contains all the items for the
     * specific order in the basket
     */
    @FXML
    private ListView<MenuItem> myOrderItems;

    /**
     * These buttons allow you to either remove the menu item from your
     * order or place the order and send it to the store.
     */
    @FXML
    private Button removeItem, placeOrder;

    /**
     * These fields hold the price for your overall order
     */
    @FXML
    private TextField subtotal, salesTax, totalAmount;

    private static final DecimalFormat decimalFormat = new DecimalFormat
            ("'$'0.00");

    /**
     * This initialize method sets up controller with the correct values
     * and also adds all the orders from the current customer order
     */
    public void initialize() {
        subtotal.setEditable(false);
        salesTax.setEditable(false);
        totalAmount.setEditable(false);
        myOrderItems.setItems(currentOrders);
        subtotal.setText(decimalFormat.format(getSubtotal()));
        salesTax.setText(decimalFormat.format(getSalesTax()));
        totalAmount.setText(decimalFormat.format(getTotalAmount()));
    }

    /**
     * This method allows you to place the order and send it over to the
     * store
     */
    @FXML
    private void placeOrder() {
        if (!currentOrders.isEmpty()) {
            Order newOrder = new Order();
            newOrder.setOrderList(currentOrders);
            StoreOrdersController.addToStoreOrders(newOrder);
            myOrderItems.getItems().clear();
            subtotal.setText("$ 0.00");
            salesTax.setText("$ 0.00");
            totalAmount.setText("$ 0.00");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Complete");
            alert.setHeaderText("Your Order Has Been Placed");
            alert.setContentText("Your order has been sent to the store.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Please Add Something to the Basket");
            alert.setContentText("You must have items in your basket to " +
                    "send to the store.");
            alert.showAndWait();
        }
    }

    /**
     * This method allows you to remove a specific menu item from the
     * customers order
     * @param removeItem
     */
    @FXML
    private void removeItem(ActionEvent removeItem) {
        if (myOrderItems.getSelectionModel().getSelectedItem() != null) {
            currentOrders.remove(myOrderItems.getSelectionModel().
                    getSelectedItem());
            subtotal.setText(decimalFormat.format(getSubtotal()));
            salesTax.setText(decimalFormat.format(getSalesTax()));
            totalAmount.setText(decimalFormat.format(getTotalAmount()));
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("No Item Selected");
            alert.setContentText("Please select an item to remove from " +
                    "your basket.");
            alert.showAndWait();
        }
    }

    /**
     * This method gets you the subtotal of the order
     * @return returns the subtotal for the order as a double
     */
    private double getSubtotal() {
        double runningSubtotal = ZERO;
        for (MenuItem item : currentOrders) {
            runningSubtotal += item.itemPrice();
        }
        return runningSubtotal;
    }

    /**
     * This method gets the sales tax for the order
     * @return returns the sales tax for the order as a double
     */
    private double getSalesTax() {
        return getSubtotal() * SALES_TAX;
    }

    /**
     * This method gets the total amount of the order
     * @return returns the total amount of the order as a double
     */
    private double getTotalAmount() {
        return getSubtotal() + getSalesTax();
    }

    /**
     * This sets the main controller for the basketcontroller
     * @param cafeStoreMainController the parameter used to set the
     *                                controller
     */
    @FXML
    public void setMainController(CafeStoreMainController
                                              cafeStoreMainController) {
        mainController = cafeStoreMainController;
    }

    /**
     * This method sends the items over from donuts and coffee and adds
     * them to the customers basket
     * @param item the menu item being added to the basket
     */
    public static void addToBasket(MenuItem item) {
        currentOrders.add(item);
    }
}
