package at.fhtw.routplanner.controller;

import at.fhtw.routplanner.FXMLDependencyInjection;
import at.fhtw.routplanner.model.Log;
import at.fhtw.routplanner.viewModel.LogBarViewModel;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class LogBarController implements Initializable {
    final LogBarViewModel logBarViewModel;

    public TableView tableView;
    public TableColumn dateColumn;
    public TableColumn durationColumn;
    public TableColumn distanceColumn;
    public Button removeButton;
    public Button editButton;
    public Button addButton;

    public LogBarController(LogBarViewModel logBarViewModel) {
        this.logBarViewModel = logBarViewModel;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("timeLength"));
        distanceColumn.setCellValueFactory(new PropertyValueFactory<>("distance"));

        tableView.setItems(logBarViewModel.getData());

        removeButtonListener(removeButton);
        addButtonListener(addButton);
        editButtonListener(editButton);
    }

    private void removeButtonListener(Button removeButton) {
        removeButton.setOnAction(event -> {
            Log selectedLog = (Log) tableView.getSelectionModel().getSelectedItem();
            if (selectedLog != null) {
                String id = selectedLog.getId();
                System.out.println("Selected Log ID: " + id);
                logBarViewModel.removeLog(id);
            } else {
                System.out.println("No item selected.");
            }
        });
    }

    private void addButtonListener(Button addButton) {
        addButton.setOnAction(event -> {

            if (logBarViewModel.getTour()==null)
                return;
            FXMLLoader loader = new FXMLLoader(
                    FXMLDependencyInjection.class.getResource("/at/fhtw/routplanner/view/addLog.fxml"),
                    ResourceBundle.getBundle("at.fhtw.routplanner.view." + "gui_strings", Locale.GERMAN)
            );
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            AddLogController addLogController = loader.getController();
            addLogController.setStage(stage);
            addLogController.setLogBarController(this);
            addLogController.setComboBoxElements();
            addLogController.setSaveButtonAction();

            stage.setScene(scene);
            stage.setResizable(false);

            stage.show();
        });
    }

    private void editButtonListener(Button editButton) {

    }

    public void saveLog(Log log) {
        logBarViewModel.saveLog(log);
    }

    public void editLog() {
        logBarViewModel.editLog();
    }
}
