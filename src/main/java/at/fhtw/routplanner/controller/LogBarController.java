package at.fhtw.routplanner.controller;

import at.fhtw.routplanner.model.Log;
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

        removeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Log selectedLog = (Log) tableView.getSelectionModel().getSelectedItem();
                if (selectedLog != null) {
                    String id = selectedLog.getId();
                    System.out.println("Selected Log ID: " + id);
                    logBarViewModel.removeLog(id);
                } else {
                    System.out.println("No item selected.");
                }
            }
        });

    }

}
