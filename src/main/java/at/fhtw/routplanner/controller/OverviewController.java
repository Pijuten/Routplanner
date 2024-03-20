package at.fhtw.routplanner.controller;

import at.fhtw.routplanner.model.Tour;
import at.fhtw.routplanner.viewModel.OverviewViewModel;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class OverviewController implements Initializable {
    final OverviewViewModel overviewViewModel;

    public OverviewController(OverviewViewModel overviewViewModel) {
        this.overviewViewModel = overviewViewModel;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Tour tour = overviewViewModel.getTour();

    }
}
