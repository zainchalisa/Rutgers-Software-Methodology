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

/**
 * This class is used to order the donuts for the users
 *
 * @author zainchalisa
 * @author nanaafriyie
 *
 */
public class OrderingDonutsController {

    private CafeStoreMainController mainController;

    /**
     * This JavaFx combo box stores all the various donutTypes
     */
    @FXML
    private ComboBox<String> donutTypes;

    /**
     * This JavaFX list view holds all the flavors for that specific donut
     * type
     */
    @FXML
    private ListView<String> donutFlavors;

    private final ObservableList<String> yeastDonutFlavors =
            FXCollections.observableArrayList("Chocolate", "Vanilla",
                    "Jelly", "Sugar", "Glazed", "Maple Iced");

    private final ObservableList<String> cakeDonutFlavors =
            FXCollections.observableArrayList("Boston Creme",
                    "Vanilla", "Strawberry");

    private final ObservableList<String> donutHolesFlavors =
            FXCollections.observableArrayList("Chocolate", "Glazed",
                    "Blueberry");

    /**
     * These JavaFX buttons allow users to add donuts to their order,
     * remove donuts from their order, and send their donut orders to the
     * basket
     */
    @FXML
    private Button addDonut, removeDonut, addToBasket;

    /**
     * This JavaFX text field stores the quantity of donuts a user is
     * ordering
     */
    @FXML
    private TextField quantity;

    /**
     * This JavaFX list view holds all the current donut selections
     * being made for the donut order
     */
    @FXML
    private ListView donutShoppingCart;

    /**
     * This JavaFX text field holds the subtotal for the entirety of the
     * donut order
     */
    @FXML
    private TextField subtotal;

    /**
     * This JavaFX image view sets the donut pictures for the different
     * donut types
     */
    @FXML
    private ImageView donutPictures;

    private ObservableList<String> donutTypeList;

    public static final String ONE = "1";

    private static final DecimalFormat decimalFormat =
            new DecimalFormat("#," + "##0.00");

    /**
     * This method sets the main controller for the Donuts controller
     * @param cafeStoreMainController the main controller being used
     */
    @FXML
    public void setMainController(CafeStoreMainController
                                              cafeStoreMainController) {
        mainController = cafeStoreMainController;
    }

    /**
     * This method initializes all the values on the UI when the user
     * first opens the donuts controller
     */
    public void initialize() {
        donutTypeList = FXCollections.observableArrayList(
                "Yeast Donut", "Cake Donut", "Donut Holes");
        donutTypes.setItems(donutTypeList);
        quantity.setText(ONE);
        subtotal.setText("$0.00");
        restrictDonutQuantity();
    }

    /**
     * This method restricts the user from inputting letters into the
     * donut quantity field
     */
    private void restrictDonutQuantity() {
        TextFormatter<String> formatterQuantity =
                new TextFormatter<>(change -> {
            if (change.getText().matches("^\\d*[\b]?$")) {
                return change;
            } else {
                return null;
            }
        });
        quantity.setTextFormatter(formatterQuantity);

    }

    /**
     * This method gets the donut type from the UI and sets the
     * appropriate image on the UI
     * @throws FileNotFoundException if the photos cannot be found
     */
    @FXML
    private void getDonutType() throws FileNotFoundException {
        String donutType =
                donutTypes.getSelectionModel().getSelectedItem();
        InputStream imagePath =
                new FileInputStream("src/main/resources" +
                        "/project4" + "/project4/" + donutType + ".jpeg");
        if (donutType.equals("Yeast Donut")) {
            donutFlavors.setItems(yeastDonutFlavors);
            Image image = new Image(imagePath);
            donutPictures.setImage(image);
        }
        if (donutType.equals("Cake Donut")) {
            donutFlavors.setItems(cakeDonutFlavors);
            Image image = new Image(imagePath);
            donutPictures.setImage(image);
        }
        if (donutType.equals("Donut Holes")) {
            donutFlavors.setItems(donutHolesFlavors);
            Image image = new Image(imagePath);
            donutPictures.setImage(image);
        }
    }

    /**
     * This methods adds the donut object selected to the donut shopping
     * cart list view
     * @param addDonut the event which adds the object to the cart
     */
    @FXML
    private void addDonuts(ActionEvent addDonut) {
        if (donutTypes.getSelectionModel().getSelectedItem() != null) {
            if (donutFlavors.getSelectionModel().
                    getSelectedItem() != null) {
                boolean dup = false;
                String donutType =
                        donutTypes.getSelectionModel().getSelectedItem();
                String donutFlavor =
                        donutFlavors.getSelectionModel().getSelectedItem();
                Donut newDonut = new Donut(donutType, donutFlavor);
                int quantityOfDonuts =
                        Integer.parseInt(quantity.getText());
                for (MenuItem donut :
                        mainController.getDonutOrders().getOrder()) {
                    if (newDonut.equals(donut)) {
                        dup = true;
                        newDonut.setQuantity(quantityOfDonuts +
                                donut.getQuantity());
                        mainController.getDonutOrders().remove(donut);
                        mainController.getDonutOrders().add(newDonut);
                        ObservableList<MenuItem> newAdditions =
                                FXCollections.observableArrayList();
                        newAdditions.addAll(mainController.getDonutOrders()
                                .getOrder());
                        donutShoppingCart.setItems(newAdditions);
                    }
                }
                if (!dup) {
                    createDonut();
                }
                subtotal.setText("$" + decimalFormat.format(mainController.
                        getDonutOrders().orderPrice()));
                quantity.setText(ONE);
            } else {
                donutFlavorError();
            }
        } else {
            donutTypeError();
        }
    }

    /**
     * This method creates a donut object to send to list view
     */
    public void createDonut(){
        String donutType =
                donutTypes.getSelectionModel().getSelectedItem();
        String donutFlavor =
                donutFlavors.getSelectionModel().getSelectedItem();
        Donut newDonut = new Donut(donutType, donutFlavor);
        int quantityOfDonuts =
                Integer.parseInt(quantity.getText());
        newDonut.setQuantity(quantityOfDonuts);
        mainController.getDonutOrders().add(newDonut);
        ObservableList<MenuItem> newAdditions =
                FXCollections.observableArrayList();
        newAdditions.addAll(mainController.getDonutOrders().
                getOrder());
        donutShoppingCart.setItems(newAdditions);
    }

    /**
     * This method creates an error when the user does not select a donut
     * flavor
     */
    public void donutFlavorError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setHeaderText("Please Select a Flavor");
        alert.setContentText("You must select a flavor to add the donut "
                + "to your order.");
        alert.showAndWait();
    }

    /**
     * This method creates an error when the user does not select a donut
     * type
     */
    public void donutTypeError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setHeaderText("Please Select a Donut Type");
        alert.setContentText("You must select a donut type to add the " +
                "donut to your order.");
        alert.showAndWait();
    }

    /**
     * This JavaFX method removes donuts from the list view based off user
     * selection
     * @param removeDonut when the user clicks on the remove button
     */
    @FXML
    private void removeDonuts(ActionEvent removeDonut) {
        if (donutShoppingCart.getSelectionModel().getSelectedItem()
                != null) {
            mainController.getDonutOrders().remove(donutShoppingCart.
                    getSelectionModel().getSelectedItem());
            ObservableList<MenuItem> newAdditions =
                    FXCollections.observableArrayList();
            newAdditions.addAll(mainController.getDonutOrders().
                    getOrder());
            donutShoppingCart.setItems(newAdditions);
            subtotal.setText("$" + decimalFormat.format(mainController.
                    getDonutOrders().orderPrice()));
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Please Select a Donut");
            alert.setContentText("You must select a donut you want to " +
                    "remove from your donut order.");
            alert.showAndWait();
        }
    }

    /**
     * This method takes the current donut order which you've created and
     * sends it to the ordering basket
     * @param addToBasket the action of clicking send to basket
     */
    @FXML
    private void addToOrder(ActionEvent addToBasket) {
        if (!mainController.getDonutOrders().getOrder().isEmpty()) {
            for (MenuItem donut :
                    mainController.getDonutOrders().getOrder()) {
                OrderingBasketController.addToBasket(donut);
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Complete");
            alert.setHeaderText("Donut Order Has Been Placed");
            alert.setContentText("Your donut order has been added to " +
                    "your basket.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Please Add Something to the Basket");
            alert.setContentText("You must have donuts in your basket " +
                    "to" + " add to your basket.");
            alert.showAndWait();
        }

        donutShoppingCart.getItems().clear();
        mainController.getDonutOrders().getOrder().clear();
        subtotal.setText("$0.00");
    }
}



