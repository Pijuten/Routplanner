package at.fhtw.routplanner.viewModel;

import at.fhtw.routplanner.model.Log;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class LogTableViewModel {
    private final ObservableList<Log> data = FXCollections.observableArrayList(
            new Log("01.04.2003","19:30","3","5km","2h","4"),
            new Log("01.23.2003","21:23","3","3km","2h","4")
    );

    public ObservableList<Log> getData() {
        return data;
    }
    public void addData(){
    }
}
