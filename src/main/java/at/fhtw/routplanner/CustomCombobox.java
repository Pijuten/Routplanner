package at.fhtw.routplanner;

import javafx.scene.control.ComboBox;
import javafx.scene.control.skin.ComboBoxListViewSkin;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class CustomCombobox extends ComboBox<String> {
    public CustomCombobox() {
        // Initialize the ComboBox normally
        super();

        // Create a custom skin for the ComboBox
        ComboBoxListViewSkin<String> skin = new ComboBoxListViewSkin<>(this);

        // Add an event filter to the popup content to consume spacebar key events
        skin.getPopupContent().addEventFilter(KeyEvent.ANY, e -> {
            if (e.getCode() == KeyCode.SPACE) {
                e.consume(); // Consume the spacebar key event
            }
        });

        // Set the custom skin to the ComboBox
        setSkin(skin);
    }
}
