package model.Game;

import model.card.DOorDH;
import model.card.PositionMonsters;
import model.card.SpellsAndTrapPosition;
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

    public GameBoard(User owner, Graveyard graveyard,
                     Hand hand, MonsterField monsterField, SpellTrapField spellTrapField, FieldZone fieldZone,
                     DeckField deckField, Game game) {
        this.owner = owner;
        this.graveyard = graveyard;
        this.hand = hand;
        this.monsterField = monsterField;
        this.spellTrapField = spellTrapField;
        this.fieldZone = fieldZone;
        this.deckField = deckField;
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
        Integer u = getOwner().getUserDeck().getActiveDeck().getMainDeckCards().size();
        gameBoard[2][0] = String.valueOf(u);
        gameBoard[3][0] = "    ";
        int forIndex = 0;
        for (int i = 1; i < 10; i += 2) {
            if (!spellTrapField.isItFull((i + 1) / 2 - 1)) {
                gameBoard[3][i] = "E";
                gameBoard[3][i + 1] = "    ";
            } else {
                if (spellTrapField.getSpellTrapCardsOnField()[(i + 1) / 2 - 1].getPosition().
                        equals(SpellsAndTrapPosition.SUMMON)) {
                    gameBoard[3][i] = "O";
                    gameBoard[3][i + 1] = "    ";
                } else {
                    gameBoard[3][i] = "H";
                    gameBoard[3][i + 1] = "    ";
                }
            }
            forIndex++;
        }
        gameBoard[4][0] = "    ";
        forIndex = 0;
        for (int i = 0; i < 10; i += 2) {
            if (monsterField.isFull()) {
                gameBoard[4][i] = "E";
                gameBoard[4][i + 1] = "    ";
            } else {
                if (monsterField.getMonstersOnField()[forIndex].getPosition()
                        .equals(PositionMonsters.DEFENSE)) {
                    if (monsterField.getMonstersOnField()[forIndex].getDefenceMode().equals(DOorDH.DH)) {
                        gameBoard[4][i] = "DH";
                        gameBoard[4][i + 1] = "    ";
                    } else {
                        gameBoard[4][i] = "DO";
                        gameBoard[4][i + 1] = "    ";
                    }
                } else {
                    gameBoard[4][i] = "OO";
                    gameBoard[4][i + 1] = "OO";
                }
            }
        }
        gameBoard[5][0] = String.valueOf(getGraveyard().getGraveyardStr().size());
        gameBoard[5][11] = fieldZone.isFull() ? "O" : "E";
        return gameBoard;
    }
}
