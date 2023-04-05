package project4.project4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This class manages to the main controller which holds 4 other
 * controllers those include Coffee, Donut, Order Cart, and Store Cart
 *
 * @author zainchalisa
 * @author nanaafriyie
 */
public class CafeStoreMainController {

    /**
     * These JavaFx buttons hold the access to open the 4 other controllers
     */
    @FXML
    private Button orderDonuts, orderCoffee, yourOrder, storeOrders;

    public final Order donutOrders = new Order();
    public final Order coffeeOrders = new Order();
    public final Order myOrder = new Order();
    public ObservableList<String> storeOrderNumbers =
            FXCollections.observableArrayList();

    /**
     * This getter method gets the current donut order which is
     * accessible by the main controller
     *
     * @return returns the donut order
     */
    public Order getDonutOrders() {
        return donutOrders;
    }

    /**
     * This method opens up the coffeeOrderButton Controller in a new
     * window
     *
     * @param coffeeOrderView on action opens a new window
     */
    @FXML
    protected void coffeeOrderButton(ActionEvent coffeeOrderView) {
        Stage coffeeView = new Stage();
        AnchorPane root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "OrderingCoffee-view.fxml"));
            root = loader.load();
            Scene scene = new Scene(root, 600, 400);
            coffeeView.setScene(scene);
            coffeeView.show();
            OrderingCoffeeController coffeeControl =
                    loader.getController();
            coffeeControl.setMainController(this);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Loading OrderingCoffee-view.fxml.");
            alert.setContentText("Couldn't load OrderingCoffee-view.fxml" +
                    ".");
            alert.showAndWait();
        }
    }

    /**
     * This method opens up the storeOrder Controller in a new window
     *
     * @param storeOrdersView on action opens up a new window
     */
    @FXML
    protected void storeOrdersButton(ActionEvent storeOrdersView) {
        Stage storeOrderView = new Stage();
        AnchorPane root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "StoreOrders-view.fxml"));
            root = loader.load();
            Scene scene = new Scene(root, 600, 400);
            storeOrderView.setScene(scene);
            storeOrderView.show();
            StoreOrdersController storeOrdersControl =
                    loader.getController();
            storeOrdersControl.setMainController(this);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Loading StoreOrders-view.fxml.");
            alert.setContentText("Couldn't load StoreOrders-view.fxml.");
            alert.showAndWait();
        }
    }

    /**
     * This method opens up the donutOrder controller in a new window
     *
     * @param donutsOrderView on action opens a new window
     */
    @FXML
    protected void donutsOrderButton(ActionEvent donutsOrderView) {
        Stage donutView = new Stage();
        AnchorPane root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "OrderingDonuts-view.fxml"));
            root = loader.load();
            Scene scene = new Scene(root, 600, 400);
            donutView.setScene(scene);
            donutView.show();
            OrderingDonutsController donutsControl =
                    loader.getController();
            donutsControl.setMainController(this);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Loading OrderingDonuts-view.fxml.");
            alert.setContentText("Couldn't load OrderingDonuts-view.fxml" +
                    ".");
            alert.showAndWait();
        }
    }

    /**
     * opens up the orderButton controller in a new window
     *
     * @param myOrderView on action opens a new window
     */
    @FXML
    protected void myOrderButton(ActionEvent myOrderView) {
        Stage myOrdersView = new Stage();
        AnchorPane root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "OrderingBasket-view.fxml"));
            root = loader.load();
            Scene scene = new Scene(root, 600, 400);
            myOrdersView.setScene(scene);
            myOrdersView.show();
            OrderingBasketController basketControl =
                    loader.getController();
            basketControl.setMainController(this);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Loading OrderingBasket-view.fxml.");
            alert.setContentText("Couldn't load OrderingBasket-view.fxml" +
                    ".");
            alert.showAndWait();
        }
    }

}
