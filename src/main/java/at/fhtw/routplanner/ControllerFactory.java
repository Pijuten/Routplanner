package at.fhtw.routplanner;

import at.fhtw.routplanner.controller.MainController;
import at.fhtw.routplanner.controller.SearchBarController;
import at.fhtw.routplanner.viewModel.MainViewModel;
import at.fhtw.routplanner.viewModel.SearchBarViewModel;

public class ControllerFactory {
    private static final ControllerFactory INSTANCE = new ControllerFactory();
    private final MainViewModel mainViewModel;
    private final SearchBarViewModel searchBarViewModel;
    public static ControllerFactory getInstance() {
        return INSTANCE;
    }
    private ControllerFactory(){
        searchBarViewModel = new SearchBarViewModel();
        mainViewModel = new MainViewModel(searchBarViewModel);
    }


    public Object create(Class<?> controllerClass){
        if(controllerClass== MainController.class)
            return new MainController(mainViewModel);
        if(controllerClass == SearchBarController.class)
            return new SearchBarController(searchBarViewModel);
        throw new IllegalArgumentException();
    }
}
