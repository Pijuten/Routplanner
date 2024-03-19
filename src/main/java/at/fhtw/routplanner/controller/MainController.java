package at.fhtw.routplanner.controller;

import at.fhtw.routplanner.viewModel.MainViewModel;
import javafx.fxml.FXML;

public class MainController {
    @FXML private LogBarController logBarController;
    @FXML private OptionBarController optionBarController;
    @FXML private OverviewController overviewController;
    @FXML private RoutBarController routBarController;
    @FXML private SearchBarController searchBarController;

    private final MainViewModel mainViewModel;

    public MainController(MainViewModel mainViewModel){
        this.mainViewModel = mainViewModel;
    }
    public MainViewModel getMainViewModel(){return mainViewModel;}

    @FXML void initialize(){

    };
}
