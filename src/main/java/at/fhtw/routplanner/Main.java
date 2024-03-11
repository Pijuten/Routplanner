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
        Parent root = FXMLDependencyInjection.load("Main.fxml", Locale.GERMAN);
        Scene scene = new Scene(root);

        // Set the scene on the stage
        stage.setScene(scene);

        // Set the title of the stage
        stage.setTitle("Routplanner");

        // Show the stage
        stage.show();
    }
}
