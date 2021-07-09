package view.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import view.gui.elements.GetImage;

public class ShowOutput {
    private static Stage showOutPutPopUpWindow;

    public static void showOutput(String title, String header) {
        showOutPutPopUpWindow = new Stage();
        showOutPutPopUpWindow.initModality(Modality.APPLICATION_MODAL);
        showOutPutPopUpWindow.getIcons().add(GetImage.getGameIcon());

        BorderPane borderPane = new BorderPane();
        Text text = new Text(header);
        text.setFont(new Font("Arial", 14));
        text.setStyle("-fx-fill: white");
        HBox hBox = new HBox(text);
        hBox.setPadding(new Insets(5));
        hBox.setStyle("-fx-background-color: #0303a4");
        hBox.setAlignment(Pos.CENTER);
        borderPane.setCenter(hBox);

        Button okButton = new Button("ok");
        okButton.setStyle("-fx-background-color: orange");
        okButton.setOnMouseClicked(e -> showOutPutPopUpWindow.close());
        HBox bottomHBox = new HBox(okButton);
        bottomHBox.setPadding(new Insets(15));
        bottomHBox.setStyle("-fx-background-color: #0303a4");
        bottomHBox.setAlignment(Pos.CENTER);
        borderPane.setBottom(bottomHBox);

        showOutPutPopUpWindow.setScene(new Scene(borderPane, 400, 120));
        showOutPutPopUpWindow.setTitle(title);
        showOutPutPopUpWindow.showAndWait();
    }
}
