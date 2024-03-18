package at.fhtw.routplanner.controller;

import at.fhtw.routplanner.viewModel.LogBarViewModel;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class LogBarController implements Initializable {
    final LogBarViewModel logBarViewModel;

    public LogBarController(LogBarViewModel logBarViewModel) {
        this.logBarViewModel = logBarViewModel;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
