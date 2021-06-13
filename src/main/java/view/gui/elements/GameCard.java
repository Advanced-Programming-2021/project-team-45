package view.gui.elements;

import javafx.event.EventHandler;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class GameCard extends Rectangle {
    private Pane parent;

    public GameCard(Pane parent, double x, double y) {
        super(x, y, GameElementSize.CARD_WIDTH.getSize(), GameElementSize.CARD_HEIGHT.getSize());
        this.parent = parent;
        setFill(new ImagePattern(GetGameElements.getCardBack()));
        this.setOnMouseEntered(GameCard.getMouseEnteredEvent(this));
        this.setOnMouseExited(GameCard.getMouseExitedEvent(this));
    }

    private static DropShadow getCardMouseEnteredDropShadow() {
        return new DropShadow();
    }

    private static EventHandler<MouseEvent> getMouseEnteredEvent(Rectangle rectangle) {
        return new EventHandler<MouseEvent> () {
            @Override
            public void handle(MouseEvent mouseEvent) {
                rectangle.setEffect(getCardMouseEnteredDropShadow());
            }
        };
    }

    private static EventHandler<MouseEvent> getMouseExitedEvent(Rectangle rectangle) {
        return new EventHandler<MouseEvent> () {
            @Override
            public void handle(MouseEvent mouseEvent) {
                rectangle.setEffect(null);
            }
        };
    }
}
