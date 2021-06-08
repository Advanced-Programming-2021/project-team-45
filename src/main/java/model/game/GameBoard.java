package model.game;

import model.card.DefensePosition;
import model.card.PositionMonsters;
import model.card.SpellsAndTrapPosition;
import model.game.fields.*;
import model.user.User;


public class GameBoard {
    private User owner;
    private DeckField deckField;
    private Graveyard graveyard;
    private Hand hand;
    private MonsterField monsterField;
    private SpellTrapField spellTrapField;
    private FieldZone fieldZone;
    private Game game;

    public GameBoard(User owner, Game game) {
        this.owner = owner;
        this.graveyard = new Graveyard(game);
        this.hand = new Hand();
        this.monsterField = new MonsterField(graveyard);
        this.spellTrapField = new SpellTrapField(owner, graveyard);
        this.fieldZone = new FieldZone(graveyard);
        this.deckField = new DeckField(owner);
        this.hand.setHandAtFirst(this.deckField);
        this.game = game;
    }

    public MonsterField getMonsterField() {
        return monsterField;
    }

    public SpellTrapField getSpellTrapField() {
        return spellTrapField;
    }

    public User getOwner() {
        return owner;
    }

    public Hand getHand() {
        return hand;
    }

    public FieldZone getFieldZone() {
        return fieldZone;
    }

    public DeckField getDeckField() {
        return deckField;
    }

    public Graveyard getGraveyard() {
        return graveyard;
    }

    public String[][] GameBoardOfPlayer() {
        String[][] gameBoard = new String[7][12];
        gameBoard[0][0] = ("        " + getOwner().getNickname() + ": ");
        gameBoard[0][1] = String.valueOf(getOwner().getLifepoint().getLifepoint());
        gameBoard[1][0] = "    ";
        for (int i = 1; i < getHand().getCardsInHand().size() * 2; i += 2) {
            gameBoard[1][i] = "c";
            gameBoard[1][i + 1] = "    ";
        }
        Integer u = getOwner().getUserDeck().getActiveDeck().getMainDeck().size();
        gameBoard[2][0] = String.valueOf(u);
        gameBoard[3][0] = "    ";
        for (int i = 1; i < 10; i += 2) {
            if (!spellTrapField.isFull((i + 1) / 2 - 1)) {
                gameBoard[3][i] = "E ";
                gameBoard[3][i + 1] = "   ";
            } else {
                if (spellTrapField.getSpellTrapCardsPositionsArray()[(i + 1) / 2 - 1].getPosition().
                        equals(SpellsAndTrapPosition.SUMMON)) {
                    gameBoard[3][i] = "O ";
                    gameBoard[3][i + 1] = "   ";
                } else {
                    gameBoard[3][i] = "H ";
                    gameBoard[3][i + 1] = "   ";
                }
            }
        }
        gameBoard[4][0] = "    ";

        for (int i = 1; i < 10; i += 2) {
            if (!monsterField.isFull((i + 1) / 2 - 1)) {
                gameBoard[4][i] = "E ";
                gameBoard[4][i + 1] = "   ";
            } else {
                if (monsterField.getMonstersOnField().get(((i + 1) / 2 - 1)).getPosition()
                        .equals(PositionMonsters.DEFENSE)) {
                    if (monsterField.getMonstersOnField().get(((i + 1) / 2 - 1)).getDefenceMode().
                            equals(DefensePosition.DH)) {
                        gameBoard[4][i] = "DH";
                        gameBoard[4][i + 1] = "   ";
                    } else {
                        gameBoard[4][i] = "DO";
                        gameBoard[4][i + 1] = "   ";
                    }
                } else {
                    gameBoard[4][i] = "OO";
                    gameBoard[4][i + 1] = "   ";
                }
            }
        }
        gameBoard[5][0] = String.valueOf(getGraveyard().getGraveyardStr().size()) + " ";
        for (int i = 1; i < 11; i++) {
            gameBoard[5][i] = i % 2 == 0 ? "  " : "   ";
        }
        gameBoard[5][11] = fieldZone.isFull() ? " O" : " E";
        return gameBoard;
    }
}
