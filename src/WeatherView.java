/****************************************************************************************************************
 * Program Title: JavaFX Weather API App
 * Author: Ben Stearns
 * Date: 4-11-2024
 * Description: main JavaFX application that runs the Weather GUI
 * Known Issues: none
 ***************************************************************************************************************/
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class WeatherView extends Application {

    /*** Define class variables ***/

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("WeatherScene.fxml")));
        primaryStage.setTitle("Ben's Super Awesome Weather App");
        Scene scene = new Scene(root,640,440);
        primaryStage.setScene(scene);
        primaryStage.show();

    };
}