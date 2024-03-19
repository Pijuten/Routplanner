package at.fhtw.routplanner.controller;

import at.fhtw.routplanner.viewModel.LogBarViewModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class LogBarController implements Initializable {
    final LogBarViewModel logBarViewModel;

    public TableView tableView;
    public TableColumn dateColumn;
    public TableColumn durationColumn;
    public TableColumn distanceColumn;
    public Button removeButton;

    public LogBarController(LogBarViewModel logBarViewModel) {
        this.logBarViewModel = logBarViewModel;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("timeLength"));
        distanceColumn.setCellValueFactory(new PropertyValueFactory<>("distance"));

        tableView.setItems(logBarViewModel.getData());

        removeButton.setOnAction(event);
    }
    EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent e) {
            // Your action here
           tableView.getItems().removeAll(
                   tableView.getSelectionModel().getSelectedItems()
           );
        }
    };
}
