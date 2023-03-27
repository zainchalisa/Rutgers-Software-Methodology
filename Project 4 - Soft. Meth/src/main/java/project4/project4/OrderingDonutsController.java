package project4.project4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

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
    private TextField quantity;

    @FXML
    private ListView donutShoppingCart;

    @FXML
    private TextField subtotal;

    @FXML
    private ImageView donutPictures;

    private ObservableList<String> donutTypeList;



    @FXML
    public void setMainController(CafeStoreMainController cafeStoreMainController) {
        mainController = cafeStoreMainController;
    }

    public void initialize() {
        donutTypeList = FXCollections.observableArrayList("Green", "Red", "Blue", "Yellow");
        donutTypes.setItems(donutTypeList);  //fruitList is the data source, and it is an observable list
        String selectedDonutType = donutTypes.getSelectionModel().getSelectedItem();
        if(selectedDonutType.equals("Green")){
            donutFlavors.getItems().addAll("Apple", "Orange", "Banana", "Watermelon");
        }


        /*
          The following statements would add the items to the GUI objects only without setting the data source.
          cmb_color.getItems().addAll("Red", "Green", "Blue", "Yellow"); //add to ComboBox object
          listview.getItems().addAll("Apple", "Orange", "Banana", "Watermelon"); //add to ListView object
        */
    }
}
