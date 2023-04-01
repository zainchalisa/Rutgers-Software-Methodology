package project4.project4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.text.DecimalFormat;

public class OrderingBasketController {

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

    private static DecimalFormat decimalFormat =  new DecimalFormat("#,##0.00");

    public void initialize(){
        //double myOrderPrice = mainController.getMyOrder().orderPrice();
        //System.out.println("$" + decimalFormat.format(mainController.getMyOrder().orderPrice()));
        myOrderItems.setItems(currentOrders);
        //subtotal.setText("$"+ mainController.getDonutOrders().orderPrice());


    }


    @FXML
    public void setMainController(CafeStoreMainController cafeStoreMainController) {
        mainController = cafeStoreMainController;
    }

    public static void addToBasket(MenuItem item){
        currentOrders.add(item);

    }



}
