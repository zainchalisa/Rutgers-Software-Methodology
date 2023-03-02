package project3.project3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TuitionManagerMain extends Application{

        @Override
        public void start(Stage stage) throws IOException {
                FXMLLoader fxmlLoader =
                        new FXMLLoader(TuitionManagerMain.class.getResource("Tuition" +
                                "ManagerView.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 600, 500);
                stage.setTitle("Tuition Manager - Project 3");
                stage.setScene(scene);
                stage.show();
        }

        public static void main(String[] args) {
                launch();
        }
}
