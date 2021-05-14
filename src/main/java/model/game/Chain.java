package model.game;

import model.card.Card;
import model.card.SpellTrapCard;
import model.game.fields.SpellTrapField;
import model.user.User;
import java.util.ArrayList;

public class Chain {

    private final ArrayList<Card> chain;
    private final Game game;
    private final User player1;
    private final User player2;
    private User turnPlayer;

    public Chain(Game game, Card firstCard, User firstPlayer, User secondPlayer) {
        chain = new ArrayList<>();
        chain.add(firstCard);
        this.game = game;
        this.player1 = firstPlayer;
        this.player2 = secondPlayer;
        this.turnPlayer = player2;
    }


    public void startChain() {
        while (canAddToChain()) {


            nextPlayer();
        }
        activateChain();
    }

    private boolean canAddToChain() {
        SpellTrapField playerSpellTrapField = getTurnPlayerGameBoard().getSpellTrapField();
        ArrayList<SpellTrapCard> spellTraps = playerSpellTrapField.getSpellTrapsArrayList();
        
        for (SpellTrapCard spell : spellTraps) {
            if (spell.getSpeed() > 1 && spell.getSpeed() >= chain.get(chain.size() - 1).getSpeed()) {
                return true;
            }
        }
        return false;
    }

    private void nextPlayer() {
        if (turnPlayer == player1) {
            turnPlayer = player2;
        } else {
            turnPlayer = player1;
        }
    }

    private void activateChain() {
        for (int i = chain.size() - 1; i >= 0; i--) {
            Card card = chain.get(i);


        }
    }

    private GameBoard getTurnPlayerGameBoard() {
        if (game.getPlayerOfThisTurn() == turnPlayer) {
            return game.getGameBoardOfPlayerOfThisTurn();
        } else {
            return game.getGameBoardOfOpponentPlayerOfThisTurn();
        }
    }
}
