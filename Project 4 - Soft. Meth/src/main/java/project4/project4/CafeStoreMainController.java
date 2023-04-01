package project4.project4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;


public class CafeStoreMainController {
    @FXML
    private Button orderDonuts, orderCoffee, yourOrder, storeOrders;

    public final Order donutOrders = new Order();
    public final Order coffeeOrders = new Order();
    public final Order myOrder = new Order();

    public Order getDonutOrders() {
        return donutOrders;
    }

    public Order getCoffeeOrders() { return coffeeOrders; }

    public Order getMyOrder(){
        return myOrder;
    }

    @FXML
    protected void coffeeOrderButton(ActionEvent coffeeOrderView) {
        Stage coffeeView = new Stage();
        AnchorPane root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("OrderingCoffee-view.fxml"));
            root = (AnchorPane) loader.load();
            Scene scene = new Scene(root, 600, 400);
            coffeeView.setScene(scene);
            coffeeView.show();
            OrderingCoffeeController coffeeControl = loader.getController();
            coffeeControl.setMainController(this);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Loading OrderingCoffee-view.fxml.");
            alert.setContentText("Couldn't load OrderingCoffee-view.fxml.");
            alert.showAndWait();
        }
    }

    /*
              The statement below is to pass the reference of the MainController object
              to the View1Controller object so the View1Controller can call the
              public methods in the MainController.
             */

    @FXML
    protected void storeOrdersButton(ActionEvent storeOrdersView) {
        Stage storeOrderView = new Stage();
        AnchorPane root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("StoreOrders-view.fxml"));
            root = (AnchorPane) loader.load();
            Scene scene = new Scene(root, 600, 400);
            storeOrderView.setScene(scene);
            storeOrderView.show();
            StoreOrdersController storeOrdersControl = loader.getController();
            storeOrdersControl.setMainController(this);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Loading StoreOrders-view.fxml.");
            alert.setContentText("Couldn't load StoreOrders-view.fxml.");
            alert.showAndWait();
        }
    }

    @FXML
    protected void donutsOrderButton(ActionEvent donutsOrderView) {
        Stage donutView = new Stage();
        AnchorPane root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("OrderingDonuts-view.fxml"));
            root = (AnchorPane) loader.load();
            Scene scene = new Scene(root, 600, 400);
            donutView.setScene(scene);
            donutView.show();
            OrderingDonutsController donutsControl = loader.getController();
            donutsControl.setMainController(this);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Loading OrderingDonuts-view.fxml.");
            alert.setContentText("Couldn't load OrderingDonuts-view.fxml.");
            alert.showAndWait();
        }
    }

    @FXML
    protected void myOrderButton(ActionEvent myOrderView) {
        Stage myOrdersView = new Stage();
        AnchorPane root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("OrderingBasket-view.fxml"));
            root = (AnchorPane) loader.load();
            Scene scene = new Scene(root, 600, 400);
            myOrdersView.setScene(scene);
            myOrdersView.show();
            OrderingBasketController basketControl = loader.getController();
            basketControl.setMainController(this);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Loading OrderingBasket-view.fxml.");
            alert.setContentText("Couldn't load OrderingBasket-view.fxml.");
            alert.showAndWait();
        }
    }

}
