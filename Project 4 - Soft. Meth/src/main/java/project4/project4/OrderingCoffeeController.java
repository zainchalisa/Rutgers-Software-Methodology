package project4.project4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.text.DecimalFormat;

public class OrderingCoffeeController {

    private CafeStoreMainController mainController;

    @FXML
    private ImageView coffeeImage;

    @FXML
    private ComboBox<String> coffeeCupSizes;

    @FXML
    private ComboBox<Integer> coffeeQuantity;

    @FXML
    private TextField coffeeSubtotal;

    @FXML
    private Button addCoffee;

    @FXML
    private CheckBox sweetCream;

    @FXML
    private CheckBox frenchVanilla;

    @FXML
    private CheckBox irishCream;

    @FXML
    private CheckBox mocha;

    @FXML
    private CheckBox caramel;

    private ObservableList<String> coffeeCupSizesList;

    private ObservableList<Integer> coffeeQuantityList;


    @FXML
    public void setMainController(CafeStoreMainController cafeStoreMainController) {
            mainController = cafeStoreMainController;
    }

    public void initialize () {
        coffeeCupSizesList = FXCollections.observableArrayList("Short","Tall","Grande","Venti");
        coffeeQuantityList = FXCollections.observableArrayList(1,2,3,4,5);
        coffeeCupSizes.setItems(coffeeCupSizesList);
        coffeeQuantity.setItems(coffeeQuantityList);
        setStartingTotal();
    }

    private void setStartingTotal () {
        DecimalFormat decimalFormat = new DecimalFormat("'$'0.00");
        TextFormatter<String> restrictInput = new TextFormatter<String>(change -> {
            return null;
        });
        coffeeSubtotal.appendText(decimalFormat.format(Coffee.STARTING_TOTAL));
        coffeeSubtotal.setTextFormatter(restrictInput);
    }

}
