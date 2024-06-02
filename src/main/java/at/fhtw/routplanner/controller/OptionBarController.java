package at.fhtw.routplanner.controller;

import at.fhtw.routplanner.viewModel.OptionBarViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.Optional;

public class OptionBarController {
    final OptionBarViewModel optionBarViewModel;
    public MenuItem importButton;
    public MenuItem exportButton;

    public OptionBarController(OptionBarViewModel optionBarViewModel) {
        this.optionBarViewModel = optionBarViewModel;
    }
    @FXML
    void initialize() {
        importButton.setOnAction(event -> {
            File selectedFile = chooseFile();
            if (selectedFile != null) {
                optionBarViewModel.importTour(selectedFile);
            }
        });

        exportButton.setOnAction(event -> {
            exportFile();
        });
    }

    private File chooseFile() {
        FileChooser fileChooser = new FileChooser();
        // Configure the file chooser if needed
        fileChooser.setTitle("Choose Tour File");
        // Show the file chooser dialog
        Stage stage = (Stage) importButton.getParentPopup().getOwnerWindow();
        return fileChooser.showOpenDialog(stage);
    }
    private void exportFile() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Choose Directory for Export");
        Stage stage = (Stage) exportButton.getParentPopup().getOwnerWindow();
        File selectedDirectory = directoryChooser.showDialog(stage);
        if (selectedDirectory != null) {
            // The user selected a directory, now prompt for filename
            TextInputDialog dialog = new TextInputDialog("filename");
            dialog.setTitle("Enter File Name");
            dialog.setHeaderText("Enter the name for the exported file");
            dialog.setContentText("Filename:");

            Optional<String> result = dialog.showAndWait();
            result.ifPresent(filename -> {
                // Create the file with the selected directory and filename
                File exportFile = new File(selectedDirectory, filename);
                // Perform export operation using exportFile
                optionBarViewModel.exportTour(exportFile);
            });
        }
    }
}
