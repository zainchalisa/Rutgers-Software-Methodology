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
import java.util.List;

public class OrderingDonutsController {

    private CafeStoreMainController mainController;
    @FXML
    private ComboBox<String> donutTypes;

    @FXML
    private ListView<String> donutFlavors;

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
    private void getDonutType(ActionEvent getDonutType){
        String donutType = donutTypes.getSelectionModel().getSelectedItem();
        if(donutType.equals("Yeast Donut")){
            donutFlavors.getItems().clear();
            donutFlavors.getItems().addAll("Chocolate", "Vanilla", "Jelly", "Sugar", "Glazed", "Maple Iced");
        }
        if(donutType.equals("Cake Donut")){
            donutFlavors.getItems().clear();
            donutFlavors.getItems().addAll("Boston Creme", "Vanilla", "Strawberry");
        }
        if(donutType.equals("Donut Holes")){
            donutFlavors.getItems().clear();
            donutFlavors.getItems().addAll("Chocolate", "Glazed", "Blueberry");
        }
    }

    @FXML
    private void addDonuts(ActionEvent addDonut){
        if(donutTypes.getSelectionModel().getSelectedItem() != null){
            if(donutFlavors.getSelectionModel().getSelectedItem() != null){
                String donutType = donutTypes.getSelectionModel().getSelectedItem();
                String donutFlavor = donutFlavors.getSelectionModel().getSelectedItem();
                Donut newDonut = new Donut(donutType, donutFlavor);
                newDonut.setQuantity(Integer.parseInt(quantity.getText()));
                mainController.getDonutOrders().add(newDonut);
                ObservableList<MenuItem> newAdditions = FXCollections.observableArrayList();
                newAdditions.addAll(mainController.getDonutOrders().getOrderList());
                donutShoppingCart.setItems(newAdditions);
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
        // based off the options selected add the donut to the list view
        // Donut Flavor + Donut Type + (Quantity of Donut Type)
    }

    @FXML
    private void donutImageChange() throws FileNotFoundException {
        String donutType = donutTypes.getSelectionModel().getSelectedItem();
        if(donutType.isEmpty()){
            donutPictures.setImage(null);
        }
        InputStream imagePath = new FileInputStream("src/main/resources/project4/project4/"+ donutType +".jpeg");
        Image image = new Image(imagePath);
        donutPictures.setImage(image);

    }

}
