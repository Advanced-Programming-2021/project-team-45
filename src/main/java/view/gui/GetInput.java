package view.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javax.activation.MimetypesFileTypeMap;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import view.gui.elements.GetImage;

import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class GetInput {
    private static Stage getYesOrNoAnswerPopupWindow;
    private static Stage getStringAnswerPopupWindow;

    public static Boolean getYesNoAnswer(String question) {
        return getTwoChoiceAnswer(question, "Yes", "No");
    }

    public static Boolean getTwoChoiceAnswer(String question, String trueOption, String falseOption) {
        AtomicBoolean result = new AtomicBoolean(false);
        getYesOrNoAnswerPopupWindow = new Stage();
        getYesOrNoAnswerPopupWindow.getIcons().add(GetImage.getGameIcon());
        getYesOrNoAnswerPopupWindow.initModality(Modality.APPLICATION_MODAL);

        BorderPane borderPane = new BorderPane();
        Text text = new Text(question);
        text.setFont(new Font("Bold", 14));
        text.setStyle("-fx-fill: white");
        HBox hBox = new HBox(text);
        hBox.setStyle("-fx-background-color: #050588");
        hBox.setAlignment(Pos.CENTER);
        borderPane.setCenter(hBox);

        Button yesButton = new Button(trueOption);
        yesButton.setStyle("-fx-background-color: orange; -fx-fill: #020264");
        Button noButton = new Button(falseOption);
        noButton.setStyle("-fx-background-color: orange; -fx-fill: #020264");
        yesButton.setOnAction(e -> {
            result.set(true);
            getYesOrNoAnswerPopupWindow.close();
        });

        noButton.setOnAction(e -> {
            result.set(false);
            getYesOrNoAnswerPopupWindow.close();
        });

        HBox hBox1 = new HBox();
        hBox1.setSpacing(10);
        hBox1.setAlignment(Pos.CENTER);
        hBox1.setPadding(new Insets(15));
        hBox1.setStyle("-fx-background-color: #050588");
        hBox1.getChildren().addAll(yesButton, noButton);
        borderPane.setBottom(hBox1);

        Scene scene = new Scene(borderPane, 350, 70);
        getYesOrNoAnswerPopupWindow.setScene(scene);
        getYesOrNoAnswerPopupWindow.setTitle("Duel");
        getYesOrNoAnswerPopupWindow.showAndWait();
        return result.get();
    }

    public static String getStringAnswerPopupWindow(String title, String question) {
        AtomicReference<String> atomicReference = new AtomicReference<>();
        atomicReference.set(null);
        getStringAnswerPopupWindow = new Stage();
        getStringAnswerPopupWindow.getIcons().add(GetImage.getGameIcon());
        getStringAnswerPopupWindow.initModality(Modality.APPLICATION_MODAL);

        BorderPane borderPane = new BorderPane();
        Text text = new Text(question);
        text.setFont(new Font("Bold", 14));
        text.setStyle("-fx-background-color: #0404e9; -fx-fill: white");
        TextField textField = new TextField(null);
        HBox textFieldHBox = new HBox(textField);
        textFieldHBox.setPadding(new Insets(15));
        textFieldHBox.setAlignment(Pos.CENTER);
        textFieldHBox.setStyle("-fx-background-color: #0404e9");
        VBox vBox = new VBox();
        vBox.setSpacing(15);
        vBox.setAlignment(Pos.CENTER);
        vBox.setStyle("-fx-background-color: #0404e9");
        vBox.getChildren().addAll(text, textFieldHBox);
        borderPane.setCenter(vBox);


        Button button = new Button("ok");
        button.setStyle("-fx-background-color: orange; -fx-fill: #020264");
        button.setMinWidth(80);
        button.setOnAction(e -> {
            atomicReference.set(textField.getText());
            getStringAnswerPopupWindow.close();
        });
        HBox hBox = new HBox(button);
        hBox.setPadding(new Insets(15));
        hBox.setStyle("-fx-background-color: #0404e9");
        hBox.setAlignment(Pos.CENTER);
        borderPane.setBottom(hBox);

        getStringAnswerPopupWindow.setScene(new Scene(borderPane, 450, 250));
        getStringAnswerPopupWindow.setResizable(false);
        getStringAnswerPopupWindow.setTitle(title);
        getStringAnswerPopupWindow.showAndWait();
        return atomicReference.get();
    }

    public static File choosePictureFile() {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(null);
        String mimetype = new MimetypesFileTypeMap().getContentType(file);
        String type = mimetype.split("/")[0];
        if (type.equals("image")) {
            return file;
        } else {
            return null;
        }
    }

    public static File chooseFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JSON source File", "*.json"),
                new FileChooser.ExtensionFilter("CSV file", "*.csv"));
        return fileChooser.showOpenDialog(null);
    }

    public static File getDirectory() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        return directoryChooser.showDialog(null);
    }
}
