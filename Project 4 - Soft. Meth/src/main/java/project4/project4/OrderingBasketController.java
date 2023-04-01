package project4.project4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
//import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class OrderingBasketController {

    private static CafeStoreMainController mainController;

    @FXML
    private TextField subtotal;

    @FXML
    private TextField salesTax;

    @FXML
    private TextField totalAmount;

    @FXML
    private static ListView basketList;

    @FXML
    private Button removeItem;

    @FXML
    private Button placeOrder;


    public static void addCoffee() {
        ObservableList<MenuItem> orders =
                FXCollections.observableArrayList();
        orders.addAll(mainController.getCoffeeOrders().getOrderList()); // This line causes error
        basketList.setItems(orders);
    }

    @FXML
    public void setMainController(CafeStoreMainController cafeStoreMainController) {
        mainController = cafeStoreMainController;
    }
}
