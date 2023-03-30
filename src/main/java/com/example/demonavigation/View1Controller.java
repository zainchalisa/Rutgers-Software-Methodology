package com.example.demonavigation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class View1Controller {
    private MainController mainController;
    private ObservableList<String> colorList;
    private ObservableList<String> fruitList;

    @FXML
    private Label value, color;

    @FXML
    private ComboBox<String> cmb_color;

    @FXML
    private ListView<String> listview;

    /**
     * This method will be automatically performed when the controller object is
     * first created. Therefore, use this method to set up the default values.
     */
    public void initialize() {
        colorList = FXCollections.observableArrayList("Green", "Red", "Blue", "Yellow");
        fruitList = FXCollections.observableArrayList("Apple", "Orange", "Banana", "Watermelon");
        cmb_color.setItems(colorList); //colorList is the data source, and it is an observable list
        listview.setItems(fruitList);  //fruitList is the data source, and it is an observable list
        /*
          The following statements would add the items to the GUI objects only without setting the data source.
          cmb_color.getItems().addAll("Red", "Green", "Blue", "Yellow"); //add to ComboBox object
          listview.getItems().addAll("Apple", "Orange", "Banana", "Watermelon"); //add to ListView object
        */
    }

    //Get the reference to the MainController object
    public void setMainController (MainController controller){
        mainController = controller;
    }

    //An example to get the private data in the MainController object
    @FXML
    public void showValue(ActionEvent event) {
        value.setText(String.valueOf(mainController.getValue()));
    }

    //Event handler for the ComboBox; this is an ActionEvent handler
    @FXML
    public void displaySelected(ActionEvent event) {
        String selected = cmb_color.getSelectionModel().getSelectedItem();
        color.setText(selected);
    }

    /**
     * Event handler for the ListView; this is a MouseEvent handler
     * The item selected in the ListView will be removed from the observable list.
     * Since the source data of the ListView is set to an observable list, they
     * are synchronized. That is, removing the item either from the ListView
     * or from the data source would work. However, it is a better approach to
     * remove it from the source; after all, the ListView is just a GUI object
     * used to show the data from the source.
     */
    @FXML
    public void displayFruit(MouseEvent event) {
        int selected = listview.getSelectionModel().getSelectedIndex(); //index of selected item
        //String selected = listview.getSelectionModel().getSelectedItem(); //return the selected object
        if (listview.getItems().size() != 0) {
            color.setText(listview.getItems().get(selected) + " is removed.");
            fruitList.remove(selected); //remove the item from the observable list
            //listview.getItems().remove(selected); //remove the item from the ListView
        }
    }

    /**
     *  Remove the first item from the observable list.
     *  Since the source data of the ComboBox is set to an observable list, they
     *  are synchronized. That is, removing the item either from the ComboBox
     *  or from the data source would work. However, it is a better approach to
     *  remove it from the source; after all, the ComboBox is just a GUI object
     *  used to show the data from the source.
     *  Please note that, this method is for demo purpose. A typical case to use
     *  a ComboBox (dropdown) is to provide a static list for the user to select
     *  from; thus, doesn't allow the removal of an item.
     */
    @FXML
    public void removeColor(ActionEvent event) {
        if (cmb_color.getItems().size() != 0) {
            color.setText(cmb_color.getItems().get(0).toString() + " is removed.");
            cmb_color.getItems().remove(0); //remove from cmb_color and colorList
            //colorList.remove(0); //remove from colorList and cmb_color
        }
    }
}
