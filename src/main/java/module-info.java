module at.fhtw.routplanner {
    requires javafx.controls;
    requires javafx.web;
    requires javafx.fxml;
    requires lombok;
    requires log4j;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;

    opens at.fhtw.routplanner;
    opens at.fhtw.routplanner.controller;
    opens at.fhtw.routplanner.model;
    exports at.fhtw.routplanner;
    exports at.fhtw.routplanner.model;
    exports at.fhtw.routplanner.enums;
    exports at.fhtw.routplanner.model.OpenRoute.Geocode to com.fasterxml.jackson.databind;
    exports at.fhtw.routplanner.model.OpenRoute.Direction to com.fasterxml.jackson.databind;
}