package at.fhtw.routplanner.viewModel;

public class MainViewModel {
    private SearchBarViewModel searchBarViewModel;
    private EditRoutViewModel editRoutViewModel;
    private OptionBarViewModel optionBarViewModel;
    private OverviewViewModel overviewViewModel;
    private RoutAccordionViewModel routAccordionViewModel;
    private RoutBarViewModel routBarViewModel;

    public MainViewModel( EditRoutViewModel editRoutViewModel, OptionBarViewModel optionBarViewModel, OverviewViewModel overviewViewModel, RoutAccordionViewModel routAccordionViewModel, RoutBarViewModel routBarViewModel,SearchBarViewModel searchBarViewModel) {
        this.searchBarViewModel = searchBarViewModel;
        this.editRoutViewModel = editRoutViewModel;
        this.optionBarViewModel = optionBarViewModel;
        this.overviewViewModel = overviewViewModel;
        this.routAccordionViewModel = routAccordionViewModel;
        this.routBarViewModel = routBarViewModel;
    }

    private void searchTours(String serachString){

    }
}
