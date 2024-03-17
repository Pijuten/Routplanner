package at.fhtw.routplanner.viewModel;

import at.fhtw.routplanner.controller.LogTableController;

public class MainViewModel {
    private SearchBarViewModel searchBarViewModel;
    private EditRoutViewModel editRoutViewModel;
    private LogBarViewModel logBarViewModel;
    private LogTableViewModel logTableViewModel;
    private OptionBarViewModel optionBarViewModel;
    private OverviewViewModel overviewViewModel;
    private RoutAccordionViewModel routAccordionViewModel;
    private RoutBarViewModel routBarViewModel;

    public MainViewModel( EditRoutViewModel editRoutViewModel, LogBarViewModel logBarViewModel, LogTableViewModel logTableViewModel, OptionBarViewModel optionBarViewModel, OverviewViewModel overviewViewModel, RoutAccordionViewModel routAccordionViewModel, RoutBarViewModel routBarViewModel,SearchBarViewModel searchBarViewModel) {
        this.searchBarViewModel = searchBarViewModel;
        this.editRoutViewModel = editRoutViewModel;
        this.logBarViewModel = logBarViewModel;
        this.logTableViewModel = logTableViewModel;
        this.optionBarViewModel = optionBarViewModel;
        this.overviewViewModel = overviewViewModel;
        this.routAccordionViewModel = routAccordionViewModel;
        this.routBarViewModel = routBarViewModel;
    }

    private void searchTours(String serachString){

    }
}
