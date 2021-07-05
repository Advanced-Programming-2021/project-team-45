package view.gui.elements;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import view.gui.DuelMenuGui;

public class MouseClickedOnGameCardEvent implements EventHandler {
    private final GameCard card;

    public MouseClickedOnGameCardEvent(GameCard card) {
        this.card = card;
    }

    @Override
    public void handle(Event event) {
        if (event instanceof MouseEvent)
            handleMouseEvent((MouseEvent) event);
    }

    private void handleMouseEvent(MouseEvent mouseEvent) {
        if (DuelMenuGui.isCardSelectMode()) {
            DuelMenuGui.addToSelectedCards(card);
        } else {
            DuelMenuGui.setSelectedCard(card);
            DuelMenuGui.getDuelMenuGui().updateSelectedCard();
            DuelMenuGui.getDuelMenuGui().deSelectCards();
            DropShadow selectedShadow = new DropShadow();
            selectedShadow.setColor(Color.WHITE);
            card.setEffect(selectedShadow);
        }
    }
}
