package at.fhtw.routplanner.controller;

import at.fhtw.routplanner.viewModel.LogTableViewModel;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class LogTableController implements Initializable{
    final LogTableViewModel logTableViewModel;
    public TableView tableView;
    public TableColumn dateColumn;
    public TableColumn durationColumn;
    public TableColumn distanceColumn;

    public LogTableController(LogTableViewModel logTableViewModel) {
        this.logTableViewModel = logTableViewModel;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("timeLength"));
        distanceColumn.setCellValueFactory(new PropertyValueFactory<>("distance"));

        tableView.setItems(logTableViewModel.getData());
    }
}
