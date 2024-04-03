package at.fhtw.routplanner;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Locale;

public class Main extends Application {
    public static void main(String[] args){
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        showStage(stage);
    }
    public static Parent showStage(Stage primaryStage) throws Exception{
        Parent root = FXMLDependencyInjection.load("main.fxml", Locale.GERMAN);
        Scene scene = new Scene(root);

        // Set the scene on the stage
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.setWidth(800); // Width of the window
        primaryStage.setHeight(600); // Height of the window

        primaryStage.setMinWidth(primaryStage.getWidth());
        primaryStage.setMinHeight(primaryStage.getHeight());
        // Set the title of the stage
        primaryStage.setTitle("Routplanner");
        // Show the stage
        primaryStage.show();
        return root;
    }
}
