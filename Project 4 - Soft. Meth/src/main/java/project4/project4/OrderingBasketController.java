package project4.project4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.*;

import java.text.DecimalFormat;

public class OrderingBasketController {

    public static final double SALES_TAX = .06625;

    private static CafeStoreMainController mainController;

    private static ObservableList<MenuItem> currentOrders = FXCollections.observableArrayList();

    @FXML
    private ListView<MenuItem> myOrderItems;

    @FXML
    private Button removeItem;

    @FXML
    private Button placeOrder;

    @FXML
    private TextField subtotal;

    @FXML
    private TextField salesTax;

    @FXML
    private TextField totalAmount;

    private static DecimalFormat decimalFormat =  new DecimalFormat("'$'0.00");

    public void initialize(){
        subtotal.setEditable(false);
        salesTax.setEditable(false);
        totalAmount.setEditable(false);
        myOrderItems.setItems(currentOrders);
        subtotal.setText(decimalFormat.format(getSubtotal()));
        salesTax.setText(decimalFormat.format(getSalesTax()));
        totalAmount.setText(decimalFormat.format(getTotalAmount()));
    }

    @FXML
    private void removeItem (ActionEvent removeItem) {
        if (myOrderItems.getSelectionModel().getSelectedItem() != null) {
            currentOrders.remove(myOrderItems.getSelectionModel().getSelectedItem());
            subtotal.setText(decimalFormat.format(getSubtotal()));
            salesTax.setText(decimalFormat.format(getSalesTax()));
            totalAmount.setText(decimalFormat.format(getTotalAmount()));
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("No Item Selected");
            alert.setContentText("Please select an item to remove from your basket.");
            alert.showAndWait();
        }
    }

    private double getSubtotal() {
        double runningSubtotal = 0;
        for (MenuItem item : currentOrders) {
            runningSubtotal += item.itemPrice();
        }
        return runningSubtotal;
    }

    private double getSalesTax() {
        return getSubtotal() * SALES_TAX;
    }

    private double getTotalAmount() {
        return getSubtotal() + getSalesTax();
    }

    @FXML
    public void setMainController(CafeStoreMainController cafeStoreMainController) {
        mainController = cafeStoreMainController;
    }

    public static void addToBasket(MenuItem item){
        currentOrders.add(item);
    }

}
