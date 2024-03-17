module at.fhtw.routplanner {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;

    opens at.fhtw.routplanner to javafx.fxml;
    opens at.fhtw.routplanner.controller to javafx.fxml;
    exports at.fhtw.routplanner;
}