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
import java.util.List;

public class OrderingDonutsController {

    private CafeStoreMainController mainController;
    @FXML
    private ComboBox<String> donutTypes;

    @FXML
    private ListView<String> donutFlavors;

    private ObservableList<String> yeastDonutFlavors = FXCollections.observableArrayList("Chocolate", "Vanilla", "Jelly", "Sugar", "Glazed", "Maple Iced");

    private ObservableList<String> cakeDonutFlavors = FXCollections.observableArrayList("Boston Creme", "Vanilla", "Strawberry");

    private ObservableList<String> donutHolesFlavors = FXCollections.observableArrayList("Chocolate", "Glazed", "Blueberry");

    @FXML
    private Button addDonut;

    @FXML
    private Button removeDonut;

    @FXML
    private Button addToBasket;

    @FXML
    private TextField quantity;

    @FXML
    private ListView donutShoppingCart;

    @FXML
    private TextField subtotal;

    @FXML
    private ImageView donutPictures;

    private ObservableList<String> donutTypeList;

    public static final String ONE = "1";

    private static DecimalFormat decimalFormat =  new DecimalFormat("#,##0.00");

    @FXML
    public void setMainController(CafeStoreMainController cafeStoreMainController) {
        mainController = cafeStoreMainController;
    }

    public void initialize() {
        donutTypeList = FXCollections.observableArrayList("Yeast Donut", "Cake Donut", "Donut Holes");
        donutTypes.setItems(donutTypeList);
        quantity.setText(ONE);
    }

    @FXML
    private void getDonutType() throws FileNotFoundException {
        String donutType = donutTypes.getSelectionModel().getSelectedItem();
        InputStream imagePath = new FileInputStream("src/main/resources/project4/project4/"+ donutType +".jpeg");
        if(donutType.equals("Yeast Donut")){
            donutFlavors.setItems(yeastDonutFlavors);
            Image image = new Image(imagePath);
            donutPictures.setImage(image);
        }
        if(donutType.equals("Cake Donut")){
            donutFlavors.setItems(cakeDonutFlavors);
            Image image = new Image(imagePath);
            donutPictures.setImage(image);
        }
        if(donutType.equals("Donut Holes")){
            donutFlavors.setItems(donutHolesFlavors);
            Image image = new Image(imagePath);
            donutPictures.setImage(image);
        }
    }

    @FXML
    private void addDonuts(ActionEvent addDonut){
        if(donutTypes.getSelectionModel().getSelectedItem() != null){
            if(donutFlavors.getSelectionModel().getSelectedItem() != null){
                boolean dup = false; // checks for the same donut
                String donutType = donutTypes.getSelectionModel().getSelectedItem();
                String donutFlavor = donutFlavors.getSelectionModel().getSelectedItem();
                Donut newDonut = new Donut(donutType, donutFlavor);
                int quantityOfDonuts = Integer.parseInt(quantity.getText());
                for(MenuItem donut: mainController.getDonutOrders().getOrderList()){
                    if(newDonut.equals(donut)) {
                        dup = true;
                        newDonut.setQuantity(quantityOfDonuts + donut.getQuantity());
                        mainController.getDonutOrders().remove(donut); // removed the original
                        mainController.getDonutOrders().add(newDonut); // adds the new donut with the updated quantity
                        ObservableList<MenuItem> newAdditions = FXCollections.observableArrayList();
                        newAdditions.addAll(mainController.getDonutOrders().getOrderList());
                        donutShoppingCart.setItems(newAdditions);
                    }
                }
                if(!dup){ // if there is on duplicate then add the donut normally
                    newDonut.setQuantity(quantityOfDonuts);
                    mainController.getDonutOrders().add(newDonut);
                    ObservableList<MenuItem> newAdditions = FXCollections.observableArrayList();
                    newAdditions.addAll(mainController.getDonutOrders().getOrderList());
                    donutShoppingCart.setItems(newAdditions);
                }

                subtotal.setText(String.valueOf("$" + decimalFormat.format(mainController.getDonutOrders().orderPrice())));
            } else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("Please Select a Flavor");
                alert.setContentText("You must select a flavor to add the donut to your order.");
                alert.showAndWait();
            }
        } else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Please Select a Donut Type");
            alert.setContentText("You must select a donut type to add the donut to your order.");
            alert.showAndWait();
        }
    }

    @FXML
    private void removeDonuts(ActionEvent removeDonut){
        if(donutShoppingCart.getSelectionModel().getSelectedItem() != null){
            mainController.getDonutOrders().remove(donutShoppingCart.getSelectionModel().getSelectedItem());
            ObservableList<MenuItem> newAdditions = FXCollections.observableArrayList();
            newAdditions.addAll(mainController.getDonutOrders().getOrderList());
            donutShoppingCart.setItems(newAdditions);
            subtotal.setText(String.valueOf("$" + decimalFormat.format(mainController.getDonutOrders().orderPrice())));
            } else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("Please Select a Donut");
                alert.setContentText("You must select a donut you want to remove from your donut order.");
                alert.showAndWait();
            }
        }
    @FXML
    private void addToOrder(ActionEvent addToBasket){
        if(!mainController.getDonutOrders().getOrderList().isEmpty()){
            for(MenuItem donut: mainController.getDonutOrders().getOrderList()){
                OrderingBasketController.addToBasket(donut);
            }
        } else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Please Add Something to the Basket");
            alert.setContentText("You must have donuts in your basket to add to your basket.");
            alert.showAndWait();
        }
    }

}



