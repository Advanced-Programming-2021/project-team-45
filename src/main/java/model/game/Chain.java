package model.game;

import model.card.Card;
import model.card.MonsterCard;
import model.card.SpecialMonsterEnum;
import model.card.SpellTrapCard;
import model.card.SpellTrapCards.effects.ChainStartState;
import model.game.fields.MonsterField;
import model.game.fields.SpellTrapField;
import model.user.User;

import java.util.ArrayList;

public class Chain {

    private final ArrayList<Card> chain;
    private final Game game;
    private final User player1;
    private final User player2;
    private final ChainStartState chainState;
    private User turnPlayer;

    public Chain(Game game, Card firstCard, User firstPlayer, User secondPlayer, ChainStartState chainState) {
        chain = new ArrayList<>();
        chain.add(firstCard);
        this.game = game;
        this.player1 = firstPlayer;
        this.player2 = secondPlayer;
        this.turnPlayer = player2;
        this.chainState = chainState;
    }


    public GameBoard getPlayerGameBoard() {
        if (game.getPlayerOfThisTurn() == turnPlayer) {
            return game.getGameBoardOfPlayerOfThisTurn();
        } else {
            return game.getGameBoardOfOpponentPlayerOfThisTurn();
        }
    }

    public GameBoard getOpponentGameBoard() {
        if (game.getPlayerOfThisTurn() == turnPlayer) {
            return game.getGameBoardOfOpponentPlayerOfThisTurn();
        } else {
            return game.getGameBoardOfPlayerOfThisTurn();
        }
    }

    public User getPlayer() {
        return turnPlayer;
    }

    public User getOpponent() {
        if (player1 == turnPlayer) {
            return player2;
        } else {
            return player1;
        }
    }

    public Game getGame() {
        return game;
    }

    public ChainStartState getChainStartState() {
        return chainState;
    }

    public void startChain() {
        while (canAddToChain()) {
            if (doesPlayerWantToAddToChain()) {
                addToChain();
            } else {
                break;
            }
            nextPlayer();
        }
        activateChain();
    }

    public boolean canAddToChain(SpellTrapCard spell) {
        if (spell.getSpeed() > 1 && spell.getSpeed() >= chain.get(chain.size() - 1).getSpeed()) {
            if (spell.isSpell()) {
                return true;
            } else {
                if (canPlayTrap()) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean canAddToChain() {
        SpellTrapField playerSpellTrapField = getPlayerGameBoard().getSpellTrapField();
        ArrayList<SpellTrapCard> spellTraps = playerSpellTrapField.getSpellTrapsArrayList();

        for (SpellTrapCard spell : spellTraps) {
            if (spell.getSpeed() > 1 && spell.getSpeed() >= chain.get(chain.size() - 1).getSpeed()) {
                if (spell.isSpell()) {
                    if (spell.canActivateEffects(this)) {
                        return true;
                    }
                } else {
                    if (canPlayTrap()) {
                        if (spell.canActivateEffects(this)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean canPlayTrap() {
        MonsterField opponentMonsterField = getOpponentGameBoard().getMonsterField();
        ArrayList<MonsterCard> opponentMonsters = opponentMonsterField.getMonstersOnField();

        for (MonsterCard monster : opponentMonsters) {
            if (monster.getSpecial() == SpecialMonsterEnum.MIRAGE_DRAGON) {
                return false;
            }
        }

        return true;
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
            nextPlayer();
            Card card = chain.get(i);

            if (card instanceof SpellTrapCard) {
                ((SpellTrapCard) card).activateEffects(this);

            } else {
                // WHAT TO DO FOR MONSTERS!!!
            }

            // REMOVE CARD FROM FIELD AND ADD TO GRAVEYARD IF NEEDED

        }
    }

    private boolean doesPlayerWantToAddToChain() {
        return game.getGameController().doesPlayerWantToAddToTheChain(turnPlayer);
    }

    private void addToChain() {
        SpellTrapCard spell;
        do {
            spell = game.getGameController().getSpellToAddToChain(turnPlayer, this);
        } while (!spell.canActivateEffects(this));

        chain.add(spell);
    }
}
