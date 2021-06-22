package view.gui.elements;

import javafx.event.EventHandler;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import view.gui.DuelMenuGui;

public class GameCard extends Rectangle {
    private Pane parent;
    private String cardName;

    public GameCard(Pane parent, double x, double y, String cardName, boolean isVisible, int rotationDegree) {
        super(x, y, GameElementSize.CARD_WIDTH.getSize(), GameElementSize.CARD_HEIGHT.getSize());
        this.parent = parent;
        this.cardName = cardName;
        if (!isVisible) {
            setFill(new ImagePattern(GetGameElements.getCardBack()));
        } else {
            setFill(new ImagePattern(GetImage.getCardImage(cardName)));
        }
        this.setOnMouseEntered(GameCard.getMouseEnteredEvent(this));
        this.setOnMouseExited(GameCard.getMouseExitedEvent(this));
        this.setOnMouseClicked(GameCard.getMouseClickedEvent(this));
        this.setRotate(rotationDegree);
    }

    public GameCard(Pane parent, double x, double y, String cardName) {
        super(x, y, GameElementSize.CARD_WIDTH.getSize(), GameElementSize.CARD_HEIGHT.getSize());
        this.parent = parent;
        this.cardName = cardName;
        setFill(new ImagePattern(GetImage.getCardImage(cardName)));
        this.setOnMouseEntered(GameCard.getMouseEnteredEvent(this));
        this.setOnMouseExited(GameCard.getMouseExitedEvent(this));
        this.setOnMouseClicked(GameCard.getMouseClickedEvent(this));
    }

    private static DropShadow getCardMouseEnteredDropShadow() {
        return new DropShadow();
    }

    private static EventHandler<MouseEvent> getMouseEnteredEvent(Rectangle rectangle) {
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                rectangle.setEffect(getCardMouseEnteredDropShadow());
            }
        };
    }

    private static EventHandler<MouseEvent> getMouseExitedEvent(Rectangle rectangle) {
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Effect effect = rectangle.getEffect();
                if (effect instanceof DropShadow) {
                    if (((DropShadow) effect).getColor() != Color.WHITE) {
                        rectangle.setEffect(null);
                    }
                }
            }
        };
    }

    private static EventHandler<MouseEvent> getMouseClickedEvent(GameCard card) {
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                DuelMenuGui.setSelectedCardName(card.getCardName());
                DuelMenuGui.getDuelMenuGui().updateSelectedCard();
                DuelMenuGui.getDuelMenuGui().deSelectCards();
                DropShadow selectedShadow = new DropShadow();
                selectedShadow.setColor(Color.WHITE);
                card.setEffect(selectedShadow);
            }
        };
    }

    public String getCardName() {
        return cardName;
    }

    public void deselectCard() {
        this.setEffect(null);
    }
}
