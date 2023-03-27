package project4.project4;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class OrderingBasketController {

    private CafeStoreMainController mainController;

    @FXML
    public void setMainController(CafeStoreMainController cafeStoreMainController) {
        mainController = cafeStoreMainController;
    }
}
