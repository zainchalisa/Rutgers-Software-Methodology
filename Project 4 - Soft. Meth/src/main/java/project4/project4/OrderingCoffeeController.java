package project4.project4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;

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

    private double runningSubtotal;

    DecimalFormat decimalFormat = new DecimalFormat("'$'0.00");
    Order order = new Order();




    @FXML
    public void setMainController(CafeStoreMainController cafeStoreMainController) {
            mainController = cafeStoreMainController;
    }

//    private void setCoffeeImage() throws FileNotFoundException {
//        InputStream imagePath = new FileInputStream("src/main/resources/project4/project4/"+ "Coffee" +".jpeg");
//        Image image = new Image(imagePath);
//        coffeeImage.setImage(image);
//    }

    public void initialize () {
        coffeeCupSizesList = FXCollections.observableArrayList("Short","Tall","Grande","Venti");
        coffeeQuantityList = FXCollections.observableArrayList(1,2,3,4,5);
        coffeeCupSizes.setItems(coffeeCupSizesList);
        coffeeQuantity.setItems(coffeeQuantityList);
        setStartingTotal();
        coffeeQuantity.setValue(coffeeQuantityList.get(0));
        //setCoffeeImage();
    }

    private void setStartingTotal () {
//        TextFormatter<String> restrictInput = new TextFormatter<String>(change -> {
//            return null;
//        });
        coffeeSubtotal.appendText(decimalFormat.format(Coffee.STARTING_TOTAL));
//        coffeeSubtotal.setTextFormatter(restrictInput);
    }

    @FXML
    private void calculateSubtotal(ActionEvent event) {
        runningSubtotal = Coffee.STARTING_TOTAL;
        int startIndex = 0;

        double cupPrice = checkCupSize();
        double quantity = checkQuantity();
        double toppingPrice = checkTopping();

        if (cupPrice < 0) {
            unselectButtons();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Didn't choose cup size");
            alert.setContentText("Please choose a cup size before changing quanity and adding toppings.");
            alert.showAndWait();
            return;
        }

        runningSubtotal = (cupPrice + toppingPrice) * quantity;

        coffeeSubtotal.replaceText(startIndex, coffeeSubtotal.getText().length(),decimalFormat.format(runningSubtotal));
    }

    private double checkCupSize() {
        if (coffeeCupSizes.getSelectionModel().getSelectedItem() != null) {
            String cupSize = coffeeCupSizes.getSelectionModel().getSelectedItem();
            if (cupSize.equals(Coffee.SHORT_CUP)) {
                return Coffee.SHORT_PRICE;
            } else if (cupSize.equals(Coffee.TALL_CUP)) {
                return Coffee.TALL_PRICE;
            } else if (cupSize.equals(Coffee.GRANDE_CUP)) {
                return Coffee.GRANDE_PRICE;
            } else if (cupSize.equals(Coffee.VENTI_CUP)) {
                return Coffee.VENTI_PRICE;
            }
        }
        return -1;
    }

    private int checkQuantity () {
        return coffeeQuantity.getSelectionModel().getSelectedItem();
    }

    private double checkTopping() {
        double toppingTotal = 0;
       if (sweetCream.isSelected()) {
           toppingTotal += Coffee.TOPPING_PRICE;
       }
       if (frenchVanilla.isSelected()) {
           toppingTotal += Coffee.TOPPING_PRICE;
       }
       if (irishCream.isSelected()) {
           toppingTotal += Coffee.TOPPING_PRICE;
       }
       if (mocha.isSelected()) {
           toppingTotal += Coffee.TOPPING_PRICE;
       }
       if (caramel.isSelected()) {
           toppingTotal += Coffee.TOPPING_PRICE;
       }
       return toppingTotal;
    }

    private void unselectButtons() {
        sweetCream.setSelected(false);
        frenchVanilla.setSelected(false);
        irishCream.setSelected(false);
        mocha.setSelected(false);
        caramel.setSelected(false);

    }

    private void addToppings (ObservableList<String> toppings) {
        if (sweetCream.isSelected()) {
            toppings.add(Coffee.SWEET_CREAM);
        }
        if (frenchVanilla.isSelected()) {
            toppings.add(Coffee.FRENCH_VANILLA);
        }
        if (irishCream.isSelected()) {
            toppings.add(Coffee.IRISH_CREAM);
        }
        if (mocha.isSelected()) {
            toppings.add(Coffee.MOCHA);
        }
        if (caramel.isSelected()) {
            toppings.add(Coffee.CARAMEL);
        }
    }

    private void confirmOrder () {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("CONFIRMATION");
        alert.setHeaderText("Your order has been added to the basket.");
        alert.showAndWait();
    }

    @FXML
    private void addToOrder(ActionEvent event) {
        if (coffeeCupSizes.getSelectionModel().getSelectedItem() != null) {
            String cupSize = coffeeCupSizes.getSelectionModel().getSelectedItem();
            int quantity = checkQuantity();
            ObservableList<String> toppingList = FXCollections.observableArrayList();
            addToppings(toppingList);

            Coffee item = new Coffee(cupSize,quantity,toppingList);
            mainController.coffeeOrders.add(item);
            OrderingBasketController.addCoffee();
            confirmOrder();
        } else {
            unselectButtons();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Didn't finish order");
            alert.setContentText("Please choose a cup size for your order.");
            alert.showAndWait();
        }


    }

}
