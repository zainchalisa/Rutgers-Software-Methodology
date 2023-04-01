package project4.project4;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class StoreOrdersController {

    private CafeStoreMainController mainController;

    public void initialize(){

    }
    @FXML
    public void setMainController(CafeStoreMainController cafeStoreMainController) {
        mainController = cafeStoreMainController;
    }
}
