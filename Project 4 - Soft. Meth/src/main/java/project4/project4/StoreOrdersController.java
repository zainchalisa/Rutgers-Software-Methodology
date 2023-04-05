package project4.project4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;


/**
 * This class holds all the store orders controller funtionalities
 *
 * @author zainchalisa
 * @author nanaafriyie
 *
 */
public class StoreOrdersController {
    private static final ObservableList<Order> orderList =
            FXCollections.observableArrayList();
    private static ObservableList<MenuItem> holderOrder;

    private CafeStoreMainController mainController;

    /**
     * This JavaFX listview holds the content of the different store orders
     */
    @FXML
    private ListView<MenuItem> contentOfOrder;

    /**
     * This ComboBox holds each of the individual orders sent over from
     * basket
     */
    @FXML
    private ComboBox<Order> orders;

    /**
     * This text field holds the total amount for the specific order
     */
    @FXML
    private TextField totalAmount;

    /**
     * These buttons can cancel the specific order you're viewing or
     * export the order to a text file
     */
    @FXML
    private Button cancelOrder, exportOrder;
    public static final double SALES_TAX = .06625;
    public static final double STARTING_TOTAL = 0.00;
    private static final DecimalFormat decimalFormat = new DecimalFormat
            ("'$'0.00");

    /**
     * This method initalized the store controller to have all the
     * accurately representing data
     */
    public void initialize() {
        if (!orderList.isEmpty()) {
            orders.setItems(orderList);
            orders.getSelectionModel().select(0);
            holderOrder =
                    FXCollections.observableArrayList(orders.getValue().
                            getOrder());
            contentOfOrder.setItems(orders.getValue().getOrder());
            totalAmount.setText(decimalFormat.format(getTotalAmount()));
        } else {
            totalAmount.setText(decimalFormat.format(STARTING_TOTAL));
        }
    }

    /**
     * This JavaFX method cancels the specific order which you're viewing
     * on the UI
     * @param cancelOrder the action of clicking the cancel order button
     */
    @FXML
    public void cancelOrder(ActionEvent cancelOrder) {
        if (!orderList.isEmpty()) {
            holderOrder =
                    FXCollections.observableArrayList(orders.getValue().
                            getOrder());
            for (Order order : orderList) {
                if (order.equals(orders.getValue())) { // works
                    if (orderList.size() == 1 || order.equals(orderList.
                            get(0))) {
                        contentOfOrder.getItems().clear();
                        orders.getItems().remove(orders.getValue());
                        orders.setValue(null);
                        totalAmount.setText(decimalFormat.format
                                (STARTING_TOTAL));
                        break;
                    }
                    orders.getItems().remove(orders.getValue());
                    break;
                }
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Canceled");
            alert.setHeaderText("Your Order Has Been Canceled");
            alert.setContentText("Order was canceled.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Your Store Has No Current Orders");
            alert.setContentText("Please wait till orders are placed to "
                    + "export orders.");
            alert.showAndWait();
        }
        orders.setItems(orderList);
    }

    /**
     * This JavaFX method exports all the orders in the store orders
     * list to a text file
     * @param exportOrders the action of clicking the export orders button
     * @throws IOException if the file directory cannot be found
     */
    @FXML
    public void exportOrders(ActionEvent exportOrders) throws IOException {
        if (!orderList.isEmpty()) {
            FileChooser chooser = new FileChooser();
            chooser.getExtensionFilters().add(new FileChooser.
                    ExtensionFilter("Text Document", "*.txt"));
            Stage stage = new Stage();
            File fileChosen = chooser.showOpenDialog(stage);

            FileWriter myWriter = new FileWriter("" + fileChosen);
            for (Order order : orderList) {
                myWriter.write("Order Number: #" + order.
                        getUniqueOrderNumber() + "\n");
                for (MenuItem item : order.getOrder()) {
                    myWriter.write(item.toString() + "\n");
                }
                myWriter.write("Total Order Price: $" + order.
                        orderPrice() + "\n");
                myWriter.write("-----------------------------" + "\n");
            }
            if(fileChosen != null){
                myWriter.close();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Exported");
                alert.setHeaderText("Your Store Orders Have Been " +
                        "Exported");
                alert.setContentText("Your store orders have been " +
                        "exported to text file selected.");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Your Store Has No Current Orders");
            alert.setContentText("Please wait till orders are placed to "
                    + "export orders.");
            alert.showAndWait();
        }
    }


    /**
     * This method gets the order based on what you click on in the
     * comboBox
     * @param selectOrder when the user clicks in the combo box
     */
    @FXML
    public void getSelectedOrder(ActionEvent selectOrder) {
        if (orders.getValue() != null) {
            holderOrder =
                    FXCollections.observableArrayList(orders.getValue().
                            getOrder());
            contentOfOrder.setItems(holderOrder);
            totalAmount.setText(decimalFormat.format(getTotalAmount()));
        }
    }

    /**
     * This method gets you the total amount of the current store order
     * @return returns the total amount of the current order selected
     */
    private double getTotalAmount() {
        double runningSubtotal = 0;
        for (MenuItem item : holderOrder) {
            runningSubtotal += item.itemPrice();
        }
        double salesTax = runningSubtotal * SALES_TAX;
        return runningSubtotal + salesTax;
    }

    /**
     * Sets the main controller for the store orders controller to access
     * @param cafeStoreMainController the main controller
     */
    @FXML
    public void setMainController(CafeStoreMainController
                                              cafeStoreMainController) {
        mainController = cafeStoreMainController;
    }

    /**
     * This method is used so that the basket controller can add the
     * individual orders to the store
     * @param customerOrder the customer order being added to the store
     */
    public static void addToStoreOrders(Order customerOrder) {
        holderOrder =
                FXCollections.observableArrayList(customerOrder.
                        getOrder());
        orderList.add(customerOrder);
        customerOrder.setOrderList(holderOrder);
    }

}
