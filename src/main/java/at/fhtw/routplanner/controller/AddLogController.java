package at.fhtw.routplanner.controller;

import at.fhtw.routplanner.model.Log;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

import java.net.URL;
import java.util.ResourceBundle;

@Log4j
public class AddLogController implements Initializable {
    public Button saveButton;
    public Button cancelButton;
    public TextField ratingTextField;
    public TextField timeTextField;
    public TextField distanceTextField;
    public TextField difficultyTextField;
    public TextField commentTextField;
    public DatePicker datePicker;
    public Label dateErrorLabel;
    public Label commentErrorLabel;
    public Label difficultyErrorLabel;
    public Label distanceErrorLabel;
    public Label timeErrorLabel;
    public Label ratingErrorLabel;
    @Setter
    private LogBarController logBarController;
    @Setter
    private Stage stage;
    @Setter
    Log logInstance;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (stage != null) {
            cancelButton.setOnAction(actionEvent -> stage.close());
        }
    }

    public void setComboBoxElements() {
        if (logInstance != null) {
            datePicker.valueProperty().bindBidirectional(new SimpleObjectProperty<>(logInstance.getDate()));
            commentTextField.textProperty().bindBidirectional(new SimpleStringProperty(logInstance.getComment()));
            difficultyTextField.textProperty().bindBidirectional(new SimpleStringProperty(logInstance.getDifficulty().toString()));
            distanceTextField.textProperty().bindBidirectional(new SimpleStringProperty(logInstance.getDistance().toString()));
            timeTextField.textProperty().bindBidirectional(new SimpleStringProperty(logInstance.getTimeLength().toString()));
            ratingTextField.textProperty().bindBidirectional(new SimpleStringProperty(logInstance.getRating().toString()));
        }
    }


    public void setButtonAction() {
        if (logInstance == null) {
            saveButton.setOnAction(actionEvent -> saveLog());
        } else {
            saveButton.setOnAction(actionEvent -> editLog());
        }
    }

    private void saveLog() {
        setErrorTextInvis();
        if (!checkInput()) {
            return;
        }
        Log log = new Log("23e", datePicker.getValue(), commentTextField.getText(), 23, Integer.parseInt(difficultyTextField.getText()), Float.parseFloat(distanceTextField.getText()), Float.parseFloat(timeTextField.getText()), Integer.parseInt(ratingTextField.getText()));
        logBarController.saveLog(log);
        stage.close();
    }

    private void editLog() {
        setErrorTextInvis();
        if (!checkInput()) {
            return;
        }
        logInstance.setDate(datePicker.getValue());
        logInstance.setComment(commentTextField.getText());
        logInstance.setDifficulty(Integer.parseInt(difficultyTextField.getText()));
        logInstance.setDistance(Float.parseFloat(distanceTextField.getText()));
        logInstance.setTimeLength(Float.parseFloat(timeTextField.getText()));
        logInstance.setRating(Integer.parseInt(ratingTextField.getText()));
        logBarController.editLog(logInstance);

        stage.close();
    }

    private boolean checkInput() {
        if (datePicker.getValue() == null) {
            dateErrorLabel.setVisible(true);
            return false;
        } else if (commentTextField.getText().isEmpty()) {
            commentErrorLabel.setVisible(true);
            return false;
        } else if (difficultyTextField.getText().isEmpty() || !difficultyTextField.getText().matches("^(0|[1-9]|10)$")) {
            difficultyErrorLabel.setVisible(true);
            return false;
        } else if (distanceTextField.getText().isEmpty() || !distanceTextField.getText().matches("^[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?$")) {
            distanceErrorLabel.setVisible(true);
            return false;
        } else if (timeTextField.getText().isEmpty() || !timeTextField.getText().matches("^[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?$")) {
            timeErrorLabel.setVisible(true);
            return false;
        } else if (ratingTextField.getText().isEmpty() || !ratingTextField.getText().matches("^[1-5]$")) {
            ratingErrorLabel.setVisible(true);
            return false;
        }
        return true;
    }

    private void setErrorTextInvis() {
        dateErrorLabel.setVisible(false);
        commentErrorLabel.setVisible(false);
        difficultyErrorLabel.setVisible(false);
        distanceErrorLabel.setVisible(false);
        timeErrorLabel.setVisible(false);
        ratingErrorLabel.setVisible(false);
    }
}
