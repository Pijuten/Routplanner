package at.fhtw.routplanner;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import java.net.URL;
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

    public static Parent showStage(Stage primaryStage) throws Exception {
        Parent root = FXMLDependencyInjection.load("main.fxml", Locale.GERMAN);
        Scene scene = new Scene(root);

        String cssPath = "/at/fhtw/routplanner/view/styles.css";
        URL cssURL = Main.class.getResource(cssPath);
        if (cssURL != null) {
            scene.getStylesheets().add(cssURL.toExternalForm());
            System.out.println("CSS geladen: " + cssURL.toExternalForm());
        } else {
            System.out.println("CSS-Datei nicht gefunden an: " + cssPath);
        }

        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.setWidth(800); // Breite des Fensters
        primaryStage.setHeight(600); // Höhe des Fensters

        primaryStage.setMinWidth(primaryStage.getWidth());
        primaryStage.setMinHeight(primaryStage.getHeight());
        primaryStage.setTitle("Routplanner"); // Titel des Fensters
        primaryStage.show();
        return root;
    }


    public static void showMapInDefaultBrowser(){
        hostServices.showDocument(Main.class.getResource("/at/fhtw/routplanner/html/leaflet.html").toExternalForm());
    }
}
