package at.fhtw.routplanner.controller;

import at.fhtw.routplanner.model.Log;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Setter;

import java.net.URL;
import java.util.Objects;
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
    Log log;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cancelButton.setOnAction(actionEvent -> stage.close());
    }

    public void setComboBoxElements() {
        datePicker.setValue(log.getDate());
        commentTextField.setText(log.getComment());
        difficultyTextField.setText(String.valueOf(log.getDifficulty()));
        distanceTextField.setText(String.valueOf(log.getDistance()));
        timeTextField.setText(String.valueOf(log.getTimeStamp()));
        ratingTextField.setText(String.valueOf(log.getRating()));
    }

    public void setSaveButtonAction() {
        saveButton.setOnAction(actionEvent -> saveLog());
    }

    public void setEditButtonAction() {
        saveButton.setOnAction(actionEvent -> editLog());
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
        log.setDate(datePicker.getValue());
        log.setComment(commentTextField.getText());
        log.setDifficulty(Integer.parseInt(difficultyTextField.getText()));
        log.setDistance(Float.parseFloat(distanceTextField.getText()));
        log.setTimeLength(Float.parseFloat(timeTextField.getText()));
        log.setRating(Integer.parseInt(ratingTextField.getText()));
        logBarController.editLog(log);

        stage.close();
    }

    private boolean checkInput() {
        if (Objects.equals(datePicker.getValue(), null)) {
            dateErrorLabel.setVisible(true);
            return false;
        } else if (Objects.equals(commentTextField.getText(), "")) {
            commentErrorLabel.setVisible(true);
            return false;
        } else if (Objects.equals(difficultyTextField.getText(), "") || !difficultyTextField.getText().matches("^(0|[1-9]|10)$")) {
            difficultyErrorLabel.setVisible(true);
            return false;
        } else if (Objects.equals(distanceTextField.getText(), "") || !distanceTextField.getText().matches("^[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?$")) {
            distanceErrorLabel.setVisible(true);
            System.out.println("no dis");
            return false;
        } else if (Objects.equals(timeTextField.getText(), "") || !timeTextField.getText().matches("^[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?$")) {
            timeErrorLabel.setVisible(true);
            return false;
        } else if (Objects.equals(ratingTextField.getText(), "") || !ratingTextField.getText().matches("^[1-5]$")) {
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
