package at.fhtw.routplanner;

import at.fhtw.routplanner.controller.*;
import at.fhtw.routplanner.viewModel.*;

public class ControllerFactory {
    private static final ControllerFactory INSTANCE = new ControllerFactory();
    private final MainViewModel mainViewModel;
    private final EditRoutViewModel editRoutViewModel;
    private final OptionBarViewModel optionBarViewModel;
    private final OverviewViewModel overviewViewModel;
    private final RoutAccordionViewModel routAccordionViewModel;
    private final RoutBarViewModel routBarViewModel;
    private final SearchBarViewModel searchBarViewModel;
    public static ControllerFactory getInstance() {
        return INSTANCE;
    }
    private ControllerFactory(){
        this.editRoutViewModel = new EditRoutViewModel();
        this.optionBarViewModel = new OptionBarViewModel();
        this.overviewViewModel = new OverviewViewModel();
        this.routAccordionViewModel = new RoutAccordionViewModel();
        this.routBarViewModel = new RoutBarViewModel();
        searchBarViewModel = new SearchBarViewModel();
        mainViewModel = new MainViewModel(editRoutViewModel,optionBarViewModel,overviewViewModel,routAccordionViewModel,routBarViewModel,searchBarViewModel);
    }


    public Object create(Class<?> controllerClass){
        if(controllerClass== MainController.class)
            return new MainController(mainViewModel);
        if(controllerClass == SearchBarController.class)
            return new SearchBarController(searchBarViewModel);
        if(controllerClass == EditRoutController.class)
            return new EditRoutController(editRoutViewModel);
        if(controllerClass == OptionBarController.class)
            return new OptionBarController(optionBarViewModel);
        if(controllerClass == OverviewController.class)
            return new OverviewController(overviewViewModel);
        if(controllerClass == RoutAccordionController.class)
            return new RoutAccordionController(routAccordionViewModel);
        if(controllerClass == RoutBarController.class)
            return new RoutBarController(routBarViewModel);
        throw new IllegalArgumentException();
    }
}
