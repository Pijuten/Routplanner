package at.fhtw.routplanner;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import java.util.Locale;

public class Main extends Application {
    private static final Logger log = Logger.getLogger(Main.class);
    private static HostServices hostServices;
    public static void main(String[] args){
        launch(Main.class, args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        showStage(stage);
        hostServices = getHostServices();

        log.info("Stage started");
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

    public static void showMapInDefaultBrowser(){
        hostServices.showDocument(Main.class.getResource("/at/fhtw/routplanner/html/leaflet.html").toExternalForm());
    }
}
