package project4.project4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.text.DecimalFormat;

/**
 * This class is the class which manages the JavaFX portion for the coffee
 * controller
 *
 * @author zainchalisa
 * @author nanaafriyie
 *
 */
public class OrderingCoffeeController {

    private CafeStoreMainController mainController;

    /**
     * This is the JavaFX component which shows the image of the coffee
     */
    @FXML
    private ImageView coffeeImage;

    /**
     * This is the JavaFX combo box which holds all of the cupSizes
     */
    @FXML
    private ComboBox<String> coffeeCupSizes;

    /**
     * This is the JavaFX combo box which holds the quantity of coffee
     * you can order
     */
    @FXML
    private ComboBox<Integer> coffeeQuantity;

    /**
     * This is the JavaFX text field which holds the subtotal of your
     * coffee order
     */
    @FXML
    private TextField coffeeSubtotal;

    /**
     * This JavaFX button sends the coffee order over to the basket
     */
    @FXML
    private Button addCoffee;

    /**
     * These JavaFX check boxes set the flavors for the current coffee
     * order
     */
    @FXML
    private CheckBox sweetCream, frenchVanilla, irishCream, mocha, caramel;

    private ObservableList<String> coffeeCupSizesList;

    private ObservableList<Integer> coffeeQuantityList;

    private double runningSubtotal;

    DecimalFormat decimalFormat = new DecimalFormat("'$'0.00");

    /**
     * This JavaFX method sets the main controller for the class
     * @param cafeStoreMainController the main controller
     */
    @FXML
    public void setMainController(CafeStoreMainController
                                              cafeStoreMainController) {
        mainController = cafeStoreMainController;
    }

    /**
     * The initalizes method sets all of the buttons, lists, and textboxes
     * to their base status
     */
    public void initialize() {
        coffeeCupSizesList = FXCollections.observableArrayList(
                "Short", "Tall", "Grande", "Venti");
        coffeeQuantityList = FXCollections.observableArrayList(1, 2,
                3, 4, 5);
        coffeeCupSizes.setItems(coffeeCupSizesList);
        coffeeQuantity.setItems(coffeeQuantityList);
        setStartingTotal();
        coffeeQuantity.setValue(coffeeQuantityList.get(0));
        coffeeSubtotal.setEditable(false);
    }

    /**
     * This method sets the starting total to 0.00
     */
    private void setStartingTotal() {
        coffeeSubtotal.appendText(decimalFormat.format(Coffee.
                STARTING_TOTAL));
    }

    /**
     * This JavaFX method calculates the subtotal as users add more and
     * more to their coffee order
     * @param event event which users click to add more to their order
     */
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
            alert.setContentText("Please choose a cup size before " +
                    "changing quantity and adding toppings.");
            alert.showAndWait();
            return;
        }
        runningSubtotal = (cupPrice + toppingPrice) * quantity;
        coffeeSubtotal.replaceText(startIndex,
                coffeeSubtotal.getText().length(),
                decimalFormat.format(runningSubtotal));
    }

    /**
     * This method checks the current cup size chosen by the customer
     * @return returns the cupSize as a double
     */
    private double checkCupSize() {
        if (coffeeCupSizes.getSelectionModel().getSelectedItem() != null) {
            String cupSize =
                    coffeeCupSizes.getSelectionModel().getSelectedItem();
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

    /**
     * This method checks the quantity of coffees selected
     * @return returns the number of coffees selected as an integer value
     */
    private int checkQuantity() {
        return coffeeQuantity.getSelectionModel().getSelectedItem();
    }

    /**
     * This method checks for the toppings the user selected
     * @return returns the selected toppings as a double value
     */
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

    /**
     * This method unselects the different flavor buttons to reset it for
     * the users
     */
    private void unselectButtons() {
        sweetCream.setSelected(false);
        frenchVanilla.setSelected(false);
        irishCream.setSelected(false);
        mocha.setSelected(false);
        caramel.setSelected(false);

    }

    /**
     * This method adds the toppings to over coffee arraylist
     * @param toppings the toppings arraylist for the coffee
     */
    private void addToppings(ObservableList<String> toppings) {
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

    /**
     * This method shows an alert once the coffee order has been
     * sent to the basket
     */
    private void confirmOrder() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("CONFIRMATION");
        alert.setHeaderText("Your order has been added to the basket.");
        alert.showAndWait();
    }

    /**
     * Thie method resets the UI for the controller once the order has
     * been sent to basket
     */
    private void resetOrder() {
        coffeeQuantity.setValue(coffeeQuantityList.get(0));
        coffeeSubtotal.setText(decimalFormat.format(Coffee.STARTING_TOTAL));
        unselectButtons();
        coffeeCupSizes.setValue(coffeeCupSizesList.get(0));
    }


    /**
     * This JavaFX method adds the current coffee order to the basket
     * @param event basked on when the user clicks to send the order
     */
    @FXML
    private void addToOrder(ActionEvent event) {
        if (coffeeCupSizes.getSelectionModel().getSelectedItem() != null) {
            String cupSize =
                    coffeeCupSizes.getSelectionModel().getSelectedItem();
            int quantity = checkQuantity();
            ObservableList<String> toppingList =
                    FXCollections.observableArrayList();
            addToppings(toppingList);
            Coffee item = new Coffee(cupSize, quantity, toppingList);
            item.setQuantity(quantity);
            OrderingBasketController.addToBasket(item);
            confirmOrder();
            resetOrder();
        } else {
            unselectButtons();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Didn't finish order");
            alert.setContentText("Please choose a cup size for your " +
                    "order.");
            alert.showAndWait();
        }
    }

}
