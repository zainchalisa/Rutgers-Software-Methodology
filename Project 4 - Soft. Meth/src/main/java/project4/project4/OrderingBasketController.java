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

    private CafeStoreMainController mainController;


    private static ObservableList<MenuItem> currentOrders = FXCollections.observableArrayList();

    @FXML
    private ListView myOrderItems;

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
        //subtotal.setText(String.valueOf("$" + decimalFormat.format(myOrderPrice)));
        //currentOrders.setAll(mainController.getMyOrder().getOrderList());
        myOrderItems.setItems(currentOrders);
    }



    @FXML
    public void setMainController(CafeStoreMainController cafeStoreMainController) {
        mainController = cafeStoreMainController;
    }

    public static void addToBasket(MenuItem item){
        currentOrders.add(item);

    }



}
