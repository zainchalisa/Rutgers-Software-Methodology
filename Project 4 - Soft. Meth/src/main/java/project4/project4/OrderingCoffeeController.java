package project4.project4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class OrderingCoffeeController {

    private CafeStoreMainController mainController;

    @FXML
    private ComboBox<String> coffeeCupSizes;

    @FXML
    private ListView<String> coffeeToppings;

    @FXML
    private Button addCoffee;

    @FXML
    private Button removeCoffee;

    @FXML
    private ListView<String> coffeeShoppingCart;

    @FXML
    private TextField coffeeSubtotal;

    private ObservableList<String> coffeeCupSizesList;

    @FXML
    public void setMainController(CafeStoreMainController cafeStoreMainController) {
            mainController = cafeStoreMainController;
    }

    public void initialize () {
        coffeeCupSizesList = FXCollections.observableArrayList("Short","Tall","Grande","Venti");
        coffeeCupSizes.setItems(coffeeCupSizesList);
    }
}
