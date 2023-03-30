module com.example.demonavigation {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.demonavigation to javafx.fxml;
    exports com.example.demonavigation;
}