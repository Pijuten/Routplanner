package at.fhtw.routplanner.controller;

import at.fhtw.routplanner.FXMLDependencyInjection;
import at.fhtw.routplanner.model.Log;
import at.fhtw.routplanner.viewModel.LogBarViewModel;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

@Log4j
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
        logBarViewModel.setSelectedItems(tableView.getSelectionModel().getSelectedItems());
        editButton.setDisable(true);
        removeButton.setDisable(true);
        logBarViewModel.getSelectedItems().addListener((ListChangeListener<Log>) change -> {
            editButton.setDisable(logBarViewModel.getSelectedItems().isEmpty());
            removeButton.setDisable(logBarViewModel.getSelectedItems().isEmpty());
        });



        removeButtonListener(removeButton);
        addButtonListener(addButton);
        editButtonListener(editButton);
    }

    private void removeButtonListener(Button removeButton) {
        removeButton.setOnAction(event -> {
            Log selectedLog = (Log) tableView.getSelectionModel().getSelectedItem();
            if (selectedLog != null) {
                String id = selectedLog.getId();
                log.info("Selected Log ID: " + id);
                logBarViewModel.removeLog(id);
            } else {
                log.info("No item selected.");
            }
        });
    }

    private void addButtonListener(Button addButton) {
        addButton.setOnAction(event -> {

            if (logBarViewModel.getTour() == null)
                return;
            setupStage(null);
        });
    }

    private void editButtonListener(Button editButton) {
        editButton.setOnAction(event -> {
            Log log = (Log) tableView.getSelectionModel().getSelectedItem();
            if (log == null)
                return;
            setupStage(log);
        });

    }

    private void setupStage(Log selectedLog) {
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
        addLogController.setLogInstance(selectedLog);
        addLogController.setComboBoxElements();
        addLogController.setButtonAction();

        stage.setScene(scene);
        stage.setResizable(false);

        stage.show();
    }

    public void saveLog(Log log) {
        logBarViewModel.saveLog(log);
    }

    public void editLog(Log log) {
        tableView.refresh();
        logBarViewModel.editLog(log);
    }
}
