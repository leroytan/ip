package gui;

import components.Light;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * A GUI for Light using FXML.
 */
public class Main extends Application {

    private Light light = new Light("./data/saved.txt");

    /**
     * Starts the application.
     *
     * @param stage The stage to start the application on.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);

            stage.setTitle("Light");
            fxmlLoader.<MainWindow>getController().setLight(light);  // inject the Light instance
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
