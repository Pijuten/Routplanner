package at.fhtw.routplanner.controller;

import at.fhtw.routplanner.viewModel.SearchBarViewModel;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class SearchBarController implements Initializable {
     final SearchBarViewModel searchBarViewModel;
    public Button searchButton;

    public SearchBarController(SearchBarViewModel searchBarViewModel) {
        this.searchBarViewModel = searchBarViewModel;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
