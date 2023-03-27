package project4.project4;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CafeManagerMain extends Application {

    public CafeManagerMain(){
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader =
                new FXMLLoader(CafeManagerMain.class.getResource("CafeStoreMain" +
                        "-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Rutgers Cafe Manager");
        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {

        launch();
    }

}