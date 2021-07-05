package view.gui;

import controller.DeckController;
import controller.DeckStarterController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class DeckMenuGui extends MenuGui {
    private static DeckController deckController;
    private static AnchorPane anchorPane;
    private static Stage stage;
    private Rectangle[][] mainRectangles = new Rectangle[6][10];
    private Rectangle[][] sideRectangles = new Rectangle[2][10];
    private static Label description;
    private static Rectangle pictureOfCard;
    private static Rectangle rectanglePictureOfDescription;

    @Override
    public void start(Stage stage) throws Exception {
        anchorPane = FXMLLoader.load(getClass().getResource("DeckMenuGui.fxml"));
        Scene scene = new Scene(anchorPane);
        DeckMenuGui.stage = stage;
        DeckMenuGui.stage.setScene(scene);
        DeckMenuGui.stage.show();
        initialize();
    }

    void initialize() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 10; j++) {
                mainRectangles[i][j] = new Rectangle();
                mainRectangles[i][j].setAccessibleText("null");
                mainRectangles[i][j].setWidth(62.2);
                mainRectangles[i][j].setHeight(87.83);
                mainRectangles[i][j].setX(210 + j * 63.5);
                mainRectangles[i][j].setY(13 + 89.5 * i);
                mainRectangles[i][j].setFill(Color.color(1, 1, 1, 0));
                anchorPane.getChildren().add(mainRectangles[i][j]);
                deckController.handlerOfCards(mainRectangles[i][j]);
            }
        }
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 10; j++) {
                sideRectangles[i][j] = new Rectangle();
                sideRectangles[i][j].setAccessibleText("null");
                sideRectangles[i][j].setWidth(62.2);
                sideRectangles[i][j].setHeight(67.83);
                sideRectangles[i][j].setX(210 + j * 63.5);
                sideRectangles[i][j].setY(572 + 69.5 * i);
                sideRectangles[i][j].setFill(Color.color(0, 0, 1, 0));
                anchorPane.getChildren().add(sideRectangles[i][j]);
                deckController.handlerOfCards(sideRectangles[i][j]);
            }
        }
        initialize1();
    }

    private void initialize1() {
        ListView listView = (ListView) anchorPane.getChildren().get(6);
        deckController.setScrollBar(listView,this);
        description= (Label) anchorPane.getChildren().get(9);
        pictureOfCard= (Rectangle) anchorPane.getChildren().get(7);
        rectanglePictureOfDescription= (Rectangle) anchorPane.getChildren().get(8);
        try {
            rectanglePictureOfDescription.setFill(new ImagePattern(new Image(new FileInputStream(
                    "src/main/resources/view/gui/elements/Deck/description.JPG"))));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        deckController.initializeMainAndSide(mainRectangles,sideRectangles);
    }

    public Rectangle[][] getMainRectangles() {
        return mainRectangles;
    }

    public Rectangle[][] getSideRectangles() {
        return sideRectangles;
    }

    public static void setDeckController(DeckController deckController) {
        DeckMenuGui.deckController = deckController;
    }

    public static Label getDescription() {
        return description;
    }

    public static Rectangle getPictureOfCard() {
        return pictureOfCard;
    }

    public static void chooseForAddToDeck(AnchorPane anchorPane1, Button button, Button button1){
        button.setText("add to the main deck");
        button1.setText("add to the side deck");
        Scene scene=new Scene(anchorPane1);
        anchorPane1.setPrefHeight(200);
        anchorPane1.setPrefWidth(300);
        button.setLayoutX(7.5);
        button.setLayoutY(150);
        button.setPrefWidth(135);
        button1.setLayoutX(160);
        button1.setLayoutY(150);
        button1.setPrefWidth(135);
        anchorPane1.getChildren().add(button);
        anchorPane1.getChildren().add(button1);
        button.setEffect(new DropShadow());
        button1.setEffect(new DropShadow());
        setStyle(anchorPane1,button,button1);
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.show();
    }

    private static void setStyle(AnchorPane anchorPane,Button button,Button button1){
        anchorPane.setStyle("-fx-background-color: #e55656");
        Text text=new Text();
        text.setText("Please choose a button");
        text.setTextAlignment(TextAlignment.CENTER);
        text.setX(88);
        text.setY(50);
        anchorPane.getChildren().add(text);
        button.setStyle("-fx-background-color: #ffffff");
        button1.setStyle("-fx-background-color: #ffffff");
    }

    public void back(MouseEvent mouseEvent) {
        try {
            new DeckStarterMenuGui().start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}