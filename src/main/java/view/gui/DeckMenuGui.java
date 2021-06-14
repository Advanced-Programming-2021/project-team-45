package view.gui;

import controller.DeckController;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class DeckMenuGui extends Application {

    private static DeckController deckController;
    private static AnchorPane anchorPane;
    private static Stage stage;
    private static Rectangle[][] mainRectangles = new Rectangle[6][10];
    private static Rectangle[][] sideRectangles=new Rectangle[2][10];

    @Override
    public void start(Stage stage) throws Exception {
        anchorPane = FXMLLoader.load(getClass().getResource("DeckMenuGui.fxml"));
        Scene scene = new Scene(anchorPane);
        DeckMenuGui.stage = stage;
        DeckMenuGui.stage.setScene(scene);
      //  DeckMenuGui.stage.show();
        initialize();
    }

    void initialize() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 10; j++) {
                mainRectangles[i][j] = new Rectangle();
                mainRectangles[i][j].setWidth(62.2);
                mainRectangles[i][j].setHeight(87.83);
                mainRectangles[i][j].setX(210 + j * 63.5);
                mainRectangles[i][j].setY(13 + 89.5 * i);
                mainRectangles[i][j].setFill(Color.color(1,1,1,0));
                anchorPane.getChildren().add(mainRectangles[i][j]);
            }
        }
        for(int i=0;i<2;i++){
            for(int j=0;j<10;j++){
                sideRectangles[i][j]=new Rectangle();
                sideRectangles[i][j].setWidth(62.2);
                sideRectangles[i][j].setHeight(67.83);
                sideRectangles[i][j].setX(210+j*63.5);
                sideRectangles[i][j].setY(572+69.5*i);
                sideRectangles[i][j].setFill(Color.color(0,0,1,0));
                anchorPane.getChildren().add(sideRectangles[i][j]);
            }
        }
        initialize1();
    }

    private void initialize1(){
        ScrollPane scrollPane=(ScrollPane) anchorPane.getChildren().get(9);
        ListView listView=(ListView) scrollPane.getContent();
        deckController.setScrollBar(listView);
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void setDeckController(DeckController deckController) {
        DeckMenuGui.deckController = deckController;
    }
}
