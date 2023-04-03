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
        System.out.println("All Orders" + orderList);
        for(Order order: orderList){
            if(order == holderOrder){
                orderList.remove(orders.getValue().getOrderList());
            }
        }
        System.out.println("All Orders After Remove" + orderList);
        orders.setItems(orderList);
        System.out.println(orders.getValue().getOrderList());
    }

    @FXML
    public void exportOrders(ActionEvent exportOrders) throws IOException {
        if(!orderList.isEmpty()){
            FileChooser chooser = new FileChooser();
            chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Document", "*.txt"));
            System.out.println();
            Stage stage = new Stage();
            File fileChosen = chooser.showOpenDialog(stage);

            System.out.println(fileChosen);

            FileWriter myWriter = new FileWriter("" + fileChosen);
            for (Order order : orderList){
                myWriter.write("Order Number: #" + order.getUniqueOrderNumber() + " " +  order.getOrderList().toString() + "\n");
                System.out.println("Order Number: #" + order.getUniqueOrderNumber() + " " +  order.getOrderList().toString() + "\n");
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
