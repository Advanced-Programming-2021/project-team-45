package view.gui;

import javafx.scene.control.Alert;

public class ShowOutput {
    public static void showOutput(String title, String header) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.show();
    }
}
