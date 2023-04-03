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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;

public class StoreOrdersController {

    private static ObservableList<Order> orderList = FXCollections.observableArrayList();

    private static ObservableList<MenuItem> holderOrder;


    private CafeStoreMainController mainController;

    @FXML
    private ListView<MenuItem> contentOfOrder;

    @FXML
    private ComboBox<Order> orders;

    @FXML
    private TextField totalAmount;

    @FXML
    private Button cancelOrder, exportOrder;

    private ObservableList<String> storeOrdersList;

    public static final double SALES_TAX = .06625;

    private static DecimalFormat decimalFormat =  new DecimalFormat("'$'0.00");



    public void initialize(){
        if(!orderList.isEmpty()){
            orders.setItems(orderList);
            orders.getSelectionModel().select(0);
            holderOrder = FXCollections.observableArrayList(orders.getValue().getOrderList());
            contentOfOrder.setItems(orders.getValue().getOrderList());
            totalAmount.setText(decimalFormat.format(getTotalAmount()));
        }
    }

    // orders is the name of the combo box that holds the order numbers
    // holderOrder is the specific order the user has selceted (return list of menu items in that order)
    // orderList is the list of orders that have been placed by the user from the basket tab

    @FXML
    public void cancelOrder(ActionEvent cancelOrder){
        if(!orderList.isEmpty()){
            holderOrder = FXCollections.observableArrayList(orders.getValue().getOrderList());
            for(Order order: orderList){
                if(order.equals(orders.getValue())){ // works
                    if (orderList.size() == 1 || order.equals(orderList.get(0))) {
                        contentOfOrder.getItems().clear();
                        orders.getItems().remove(orders.getValue());
                        orders.setValue(null);
                        totalAmount.setText(decimalFormat.format(Coffee.STARTING_TOTAL));
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
        } else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Your Store Has No Current Orders");
            alert.setContentText("Please wait till orders are placed to export orders.");
            alert.showAndWait();
        }
        orders.setItems(orderList);
    }

    @FXML
    public void exportOrders(ActionEvent exportOrders) throws IOException {
        if(!orderList.isEmpty()){
            FileChooser chooser = new FileChooser();
            chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Document", "*.txt"));
            Stage stage = new Stage();
            File fileChosen = chooser.showOpenDialog(stage);

            FileWriter myWriter = new FileWriter("" + fileChosen);
            for (Order order : orderList){
                myWriter.write("Order Number: #" + order.getUniqueOrderNumber() + " " +  order.getOrderList().toString() + "\n");
            }
            myWriter.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Exported");
            alert.setHeaderText("Your Store Orders Have Been Exported");
            alert.setContentText("Your store orders have been exported to ExportOrders.txt.");
            alert.showAndWait();
        } else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Your Store Has No Current Orders");
            alert.setContentText("Please wait till orders are placed to export orders.");
            alert.showAndWait();
        }
    }


    @FXML
    public void getSelectedOrder(ActionEvent selectOrder){
        if (orders.getValue() != null) {
            holderOrder = FXCollections.observableArrayList(orders.getValue().getOrderList());
            contentOfOrder.setItems(holderOrder);
            totalAmount.setText(decimalFormat.format(getTotalAmount()));
        }
    }

    private double getSubtotal() {
        double runningSubtotal = 0;
        for (MenuItem item : holderOrder) {
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

    public static void addToStoreOrders(Order customerOrder){
        holderOrder = FXCollections.observableArrayList(customerOrder.getOrderList());
        orderList.add(customerOrder);
        customerOrder.setOrderList(holderOrder);
    }

    @FXML
    public void updateOrders(ActionEvent updateOrders){

    }
}
