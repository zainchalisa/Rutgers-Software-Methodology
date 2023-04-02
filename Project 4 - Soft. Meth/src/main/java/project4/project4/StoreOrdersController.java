package project4.project4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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


    public void initialize(){
        //orders.setItems(mainController.getStoreOrderNumbers());
        if(!orderList.isEmpty()){
            System.out.println(holderOrder);
            orders.setItems(orderList);
            orders.getSelectionModel().select(0);
            holderOrder = FXCollections.observableArrayList(orders.getValue().getOrderList());
            //orders.getValue().setOrderList(holderOrder);
            //System.out.println(orders.getValue().getOrderList());
            contentOfOrder.setItems(orders.getValue().getOrderList());
        }
    }


    @FXML
    public void getSelectedOrder(ActionEvent selectOrder){
        holderOrder = FXCollections.observableArrayList(orders.getValue().getOrderList());
        contentOfOrder.setItems(holderOrder);
    }

    @FXML
    public void setMainController(CafeStoreMainController cafeStoreMainController) {
        mainController = cafeStoreMainController;
    }

    public static void addToStoreOrders(Order customerOrder){
        holderOrder = FXCollections.observableArrayList(customerOrder.getOrderList());
        orderList.add(customerOrder);
        customerOrder.setOrderList(holderOrder);
        System.out.println("Holder list after add" + holderOrder);
    }

    @FXML
    public void updateOrders(ActionEvent updateOrders){

    }
}
