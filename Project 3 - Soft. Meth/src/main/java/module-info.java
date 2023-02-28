module project3.project3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens project3.project3 to javafx.fxml;
    exports project3.project3;
}