package project4.project4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
        donutTypeList = FXCollections.observableArrayList("Yeast Donut", "Cake Donut", "Donut Holes");
        donutTypes.setItems(donutTypeList);  //fruitList is the data source, and it is an observable list



        /*
          The following statements would add the items to the GUI objects only without setting the data source.
          cmb_color.getItems().addAll("Red", "Green", "Blue", "Yellow"); //add to ComboBox object
          listview.getItems().addAll("Apple", "Orange", "Banana", "Watermelon"); //add to ListView object
        */
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

}
