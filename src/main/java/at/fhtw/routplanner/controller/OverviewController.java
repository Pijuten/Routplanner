package at.fhtw.routplanner.controller;

import at.fhtw.routplanner.viewModel.OverviewViewModel;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class OverviewController implements Initializable {
    final OverviewViewModel overviewViewModel;
    public Label nameLabel;
    public Label descriptionLabel;
    public Label startPointLabel;
    public Label endPointLabel;
    public Label transportTypeLabel;
    public Label distanceLabel;
    public Label timeLabel;
    public WebView hyperlink;

    public OverviewController(OverviewViewModel overviewViewModel) {
        this.overviewViewModel = overviewViewModel;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameLabel.textProperty().bindBidirectional(overviewViewModel.getCurrentTourName());
        descriptionLabel.textProperty().bindBidirectional(overviewViewModel.getCurrentDescription());
        startPointLabel.textProperty().bindBidirectional(overviewViewModel.getCurrentStartPoint());
        endPointLabel.textProperty().bindBidirectional(overviewViewModel.getCurrentEndpoint());
        transportTypeLabel.textProperty().bindBidirectional(overviewViewModel.getCurrentTransportType());
        distanceLabel.textProperty().bindBidirectional(overviewViewModel.getCurrentDistance());
        timeLabel.textProperty().bindBidirectional(overviewViewModel.getCurrentTime());

        hyperlink.getEngine().load(overviewViewModel.getMapUrl().get());
        overviewViewModel.getMapUrl().addListener((observable, oldValue, newValue) -> {
            hyperlink.getEngine().load(newValue);
        });

    }

}
