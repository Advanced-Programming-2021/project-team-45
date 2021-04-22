package model.Game;

import model.card.Card;
import model.user.User;

public class Game {
    private String phase;
    private Card addedCardInDrawPhase;
    private User player;
    private User opponent;
    private GameBoard gameBoard;
    private Card selectedCard;

    public Card getSelectedCard() {
        return selectedCard;
    }

    public User getOpponent() {
        return opponent;
    }

    public String getPhase() {
        return phase;
    }

    public Card getAddedCardInDrawPhase() {
        return addedCardInDrawPhase;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }
}
