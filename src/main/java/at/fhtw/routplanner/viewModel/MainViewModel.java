package at.fhtw.routplanner.viewModel;

import at.fhtw.routplanner.enums.TransportType;
import at.fhtw.routplanner.model.Log;
import at.fhtw.routplanner.model.Tour;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel {
    private SearchBarViewModel searchBarViewModel;
    private LogBarViewModel logBarViewModel;
    private OptionBarViewModel optionBarViewModel;
    private OverviewViewModel overviewViewModel;
    private RoutBarViewModel routBarViewModel;

    private ObservableList<Tour> tours;

    public MainViewModel(LogBarViewModel logBarViewModel, OptionBarViewModel optionBarViewModel, OverviewViewModel overviewViewModel, RoutBarViewModel routBarViewModel,SearchBarViewModel searchBarViewModel) {
        this.searchBarViewModel = searchBarViewModel;
        this.logBarViewModel = logBarViewModel;
        this.optionBarViewModel = optionBarViewModel;
        this.overviewViewModel = overviewViewModel;
        this.routBarViewModel = routBarViewModel;
    }
}
