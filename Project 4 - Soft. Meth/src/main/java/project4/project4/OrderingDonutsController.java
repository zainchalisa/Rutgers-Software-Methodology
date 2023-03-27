package project4.project4;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.util.List;

public class OrderingDonutsController {
    @FXML
    private ComboBox donutTypes;

    @FXML
    private ListView donutFlavors;

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

    @FXML
    private void populateDonutFlavors(){
        // this method when a type of donut is selected will show the right
        // flavors according to that donut type
    }
}
