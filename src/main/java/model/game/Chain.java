package model.game;

import model.card.Card;
import model.card.MonsterCard;
import model.card.SpecialMonsterEnum;
import model.card.SpellTrapCard;
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
    private boolean canActiveMonster;

    public Chain(Game game, Card firstCard, User firstPlayer, User secondPlayer, ChainStartState chainState) {
        chain = new ArrayList<>();
        chain.add(firstCard);
        this.game = game;
        this.player1 = firstPlayer;
        this.player2 = secondPlayer;
        this.turnPlayer = player2;
        this.chainState = chainState;
        this.canActiveMonster = true;
    }


    public GameBoard getPlayerGameBoard() {
        if (game.getPlayerOfThisTurn() == turnPlayer) {
            return game.getPlayerGameBoard();
        } else {
            return game.getOpponentGameBoard();
        }
    }

    public GameBoard getOpponentGameBoard() {
        if (game.getPlayerOfThisTurn() == turnPlayer) {
            return game.getOpponentGameBoard();
        } else {
            return game.getPlayerGameBoard();
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

    public void deActiveMonster() {
        canActiveMonster = false;
    }

    public boolean startChain() {
        while (canAddToChain()) {
            if (doesPlayerWantToAddToChain()) {
                addToChain();
            } else {
                break;
            }
            nextPlayer();
        }
        activateChain();

        return canActiveMonster;
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
        if (spellTraps.size() != 0) {
            for (SpellTrapCard spell : spellTraps) {
                if (spell != null) {
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
                SpellTrapCard spell = (SpellTrapCard) card;
                spell.activateEffects(this);
                // move spell to graveyard:
                SpellTrapField spellField = getPlayerGameBoard().getSpellTrapField();
                spellField.deleteAndDestroySpellTrap(spell);

            }
        }
    }

    //comment shode bara run shodan gui

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
