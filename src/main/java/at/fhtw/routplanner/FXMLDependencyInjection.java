package at.fhtw.routplanner;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class FXMLDependencyInjection {
    public static Parent load(String location, Locale locale) throws IOException {
        FXMLLoader loader = getLoader(location, locale);
        return loader.load();
    }

    private static FXMLLoader getLoader(String location, Locale locale) {
        return new FXMLLoader(
                FXMLDependencyInjection.class.getResource("/at/fhtw/routplanner/view/"+location),
                ResourceBundle.getBundle("at.fhtw.routplanner.view."+"gui_strings",locale),
                new JavaFXBuilderFactory(),
                controllerClass -> ControllerFactory.getInstance().create(controllerClass)
        );
    }
}

