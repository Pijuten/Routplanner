package at.fhtw.routplanner;

import at.fhtw.routplanner.controller.*;
import at.fhtw.routplanner.viewModel.*;

public class ControllerFactory {
    private static final ControllerFactory INSTANCE = new ControllerFactory();
    private final MainViewModel mainViewModel;
    private final LogBarViewModel logBarViewModel;
    private final OptionBarViewModel optionBarViewModel;
    private final OverviewViewModel overviewViewModel;
    private final RoutBarViewModel routBarViewModel;
    private final SearchBarViewModel searchBarViewModel;
    public static ControllerFactory getInstance() {
        return INSTANCE;
    }
    private ControllerFactory(){
        this.logBarViewModel = new LogBarViewModel();
        this.overviewViewModel = new OverviewViewModel();
        this.routBarViewModel = new RoutBarViewModel();
        this.optionBarViewModel = new OptionBarViewModel(routBarViewModel);
        this.searchBarViewModel = new SearchBarViewModel();
        mainViewModel = new MainViewModel(logBarViewModel,optionBarViewModel,overviewViewModel,routBarViewModel,searchBarViewModel);
    }


    public Object create(Class<?> controllerClass){
        if(controllerClass== MainController.class)
            return new MainController(mainViewModel);
        if(controllerClass == SearchBarController.class)
            return new SearchBarController(searchBarViewModel);
        if(controllerClass == LogBarController.class)
            return new LogBarController(logBarViewModel);
        if(controllerClass == OptionBarController.class)
            return new OptionBarController(optionBarViewModel);
        if(controllerClass == OverviewController.class)
            return new OverviewController(overviewViewModel);
        if(controllerClass == RoutBarController.class)
            return new RoutBarController(routBarViewModel);
        throw new IllegalArgumentException();
    }
}
