package at.fhtw.routplanner;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;

public class Main extends Application {
    public static void main(String[] args){
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLDependencyInjection.load("main.fxml", Locale.GERMAN);
        Scene scene = new Scene(root);

        // Set the scene on the stage
        stage.setScene(scene);
        stage.sizeToScene();
        stage.setWidth(800); // Width of the window
        stage.setHeight(600); // Height of the window

        stage.setMinWidth(stage.getWidth());
        stage.setMinHeight(stage.getHeight());
        // Set the title of the stage
        stage.setTitle("Routplanner");
        // Show the stage
        stage.show();
    }
}
