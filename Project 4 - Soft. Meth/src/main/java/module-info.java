module proiect4.project4 {
    requires javafx.controls;
    requires javafx.fxml;


    opens proiect4.project4 to javafx.fxml;
    exports proiect4.project4;
}