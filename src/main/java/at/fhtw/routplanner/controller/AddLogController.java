package at.fhtw.routplanner.controller;

import at.fhtw.routplanner.model.Log;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Setter;

import java.net.URL;
import java.util.ResourceBundle;

public class AddLogController implements Initializable {
    public Button saveButton;
    public Button cancelButton;
    public TextField ratingTextField;
    public TextField timeTextField;
    public TextField distanceTextField;
    public TextField difficultyTextField;
    public TextField commentTextField;
    public DatePicker datePicker;
    @Setter
    private LogBarController logBarController;
    @Setter
    private Stage stage;
    @Setter
    Log log;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setComboBoxElements() {
    }

    public void setSaveButtonAction() {
        saveButton.setOnAction(actionEvent -> saveLog());
    }

    private void saveLog() {
        Log log = new Log("23e",datePicker.getValue(),Integer.parseInt(timeTextField.getText()),Integer.parseInt(difficultyTextField.getText()),Float.parseFloat(distanceTextField.getText()),Float.parseFloat(timeTextField.getText()),Integer.parseInt(ratingTextField.getText()));
        logBarController.saveLog(log);
        stage.close();
    }
}
