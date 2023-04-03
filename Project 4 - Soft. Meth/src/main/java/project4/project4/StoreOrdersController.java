package project4.project4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
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
            System.out.println(holderOrder);
            orders.setItems(orderList);
            orders.getSelectionModel().select(0);
            holderOrder = FXCollections.observableArrayList(orders.getValue().getOrderList());
            contentOfOrder.setItems(orders.getValue().getOrderList());
            totalAmount.setText(decimalFormat.format(getTotalAmount()));
        }
    }

    @FXML
    public void cancelOrder(ActionEvent cancelOrder){
        holderOrder = FXCollections.observableArrayList(orders.getValue().getOrderList());
        System.out.println("Current Order" + holderOrder);
        System.out.println("Order List" + orders.getValue());
        orderList.remove(orders.getValue().getOrderList());
        orders.setItems(orderList);
        System.out.println(orders.getValue().getOrderList());
    }

    @FXML
    public void exportOrders(ActionEvent exportOrders){
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Document", "*.txt"));
        Stage stage = new Stage();
        File file = chooser.showSaveDialog(stage);
        //displayExportAlert(file);
    }


    @FXML
    public void getSelectedOrder(ActionEvent selectOrder){
        holderOrder = FXCollections.observableArrayList(orders.getValue().getOrderList());
        contentOfOrder.setItems(holderOrder);
        System.out.println(holderOrder);
        System.out.println(getTotalAmount());
        totalAmount.setText(decimalFormat.format(getTotalAmount()));
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
