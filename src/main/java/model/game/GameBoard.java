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
        String[][] gameBoard = new String[7][13];
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
        spellAndTrapFieldSetter(gameBoard);
        gameBoard[4][0] = "    ";
        monsterFieldSetter(gameBoard);
        gameBoard[5][0] = String.valueOf(getGraveyard().getGraveyardStr().size()) + " ";
        for (int i = 1; i < 11; i++) {
            gameBoard[5][i] = i % 2 == 0 ? "  " : "   ";
        }
        gameBoard[5][11] = fieldZone.isFull() ? " O" : " E";
        return gameBoard;
    }

    private void spellAndTrapFieldSetter(String[][] gameBoard) {
        if (!spellTrapField.isFull(0)) {
            gameBoard[3][5] = "E ";
            gameBoard[3][6] = "   ";
        } else {
            if (spellTrapField.getSpellTrapCardsPositionsArray()[0].getPosition().
                    equals(SpellsAndTrapPosition.SUMMON)) {
                gameBoard[3][5] = "O ";
                gameBoard[3][6] = "   ";
            } else {
                gameBoard[3][5] = "H ";
                gameBoard[3][6] = "   ";
            }
        }
        if (!spellTrapField.isFull(1)) {
            gameBoard[3][7] = "E ";
            gameBoard[3][8] = "   ";
        } else {
            if (spellTrapField.getSpellTrapCardsPositionsArray()[1].getPosition().
                    equals(SpellsAndTrapPosition.SUMMON)) {
                gameBoard[3][7] = "O ";
                gameBoard[3][8] = "   ";
            } else {
                gameBoard[3][7] = "H ";
                gameBoard[3][8] = "   ";
            }
        }
        if (!spellTrapField.isFull(2)) {
            gameBoard[3][3] = "E ";
            gameBoard[3][4] = "   ";
        } else {
            if (spellTrapField.getSpellTrapCardsPositionsArray()[2].getPosition().
                    equals(SpellsAndTrapPosition.SUMMON)) {
                gameBoard[3][3] = "O ";
                gameBoard[3][4] = "   ";
            } else {
                gameBoard[3][3] = "H ";
                gameBoard[3][4] = "   ";
            }
        }
        if (!spellTrapField.isFull(3)) {
            gameBoard[3][9] = "E ";
            gameBoard[3][10] = "   ";
        } else {
            if (spellTrapField.getSpellTrapCardsPositionsArray()[3].getPosition().
                    equals(SpellsAndTrapPosition.SUMMON)) {
                gameBoard[3][9] = "O ";
                gameBoard[3][10] = "   ";
            } else {
                gameBoard[3][9] = "H ";
                gameBoard[3][10] = "   ";
            }
        }
        if (!spellTrapField.isFull(4)) {
            gameBoard[3][1] = "E ";
            gameBoard[3][2] = "   ";
        } else {
            if (spellTrapField.getSpellTrapCardsPositionsArray()[4].getPosition().
                    equals(SpellsAndTrapPosition.SUMMON)) {
                gameBoard[3][1] = "O ";
                gameBoard[3][2] = "   ";
            } else {
                gameBoard[3][1] = "H ";
                gameBoard[3][2] = "   ";
            }
        }

    }

    private void monsterFieldSetter(String[][] gameBoard) {
        if (!monsterField.isFull(0)) {
            gameBoard[4][5] = "E ";
            gameBoard[4][6] = "   ";
        } else {
            if (monsterField.getMonstersOnField().get(0).getPosition()
                    .equals(PositionMonsters.DEFENSE)) {
                if (monsterField.getMonstersOnField().get(0).getDefenceMode().
                        equals(DefensePosition.DH)) {
                    gameBoard[4][5] = "DH";
                    gameBoard[4][6] = "   ";
                } else {
                    gameBoard[4][5] = "DO";
                    gameBoard[4][6] = "   ";
                }
            } else {
                gameBoard[4][5] = "OO";
                gameBoard[4][6] = "   ";
            }
        }
        if (!monsterField.isFull(1)) {
            gameBoard[4][7] = "E ";
            gameBoard[4][8] = "   ";
        } else {
            if (monsterField.getMonstersOnField().get(1).getPosition()
                    .equals(PositionMonsters.DEFENSE)) {
                if (monsterField.getMonstersOnField().get(1).getDefenceMode().
                        equals(DefensePosition.DH)) {
                    gameBoard[4][7] = "DH";
                    gameBoard[4][8] = "   ";
                } else {
                    gameBoard[4][7] = "DO";
                    gameBoard[4][8] = "   ";
                }
            } else {
                gameBoard[4][7] = "OO";
                gameBoard[4][8] = "   ";
            }
        }
        if (!monsterField.isFull(2)) {
            gameBoard[4][3] = "E ";
            gameBoard[4][4] = "   ";
        } else {
            if (monsterField.getMonstersOnField().get(2).getPosition()
                    .equals(PositionMonsters.DEFENSE)) {
                if (monsterField.getMonstersOnField().get(2).getDefenceMode().
                        equals(DefensePosition.DH)) {
                    gameBoard[4][3] = "DH";
                    gameBoard[4][4] = "   ";
                } else {
                    gameBoard[4][3] = "DO";
                    gameBoard[4][4] = "   ";
                }
            } else {
                gameBoard[4][3] = "OO";
                gameBoard[4][4] = "   ";
            }
        }

        if (!monsterField.isFull(3)) {
            gameBoard[4][9] = "E ";
            gameBoard[4][10] = "   ";
        } else {
            if (monsterField.getMonstersOnField().get(3).getPosition()
                    .equals(PositionMonsters.DEFENSE)) {
                if (monsterField.getMonstersOnField().get(3).getDefenceMode().
                        equals(DefensePosition.DH)) {
                    gameBoard[4][9] = "DH";
                    gameBoard[4][10] = "   ";
                } else {
                    gameBoard[4][9] = "DO";
                    gameBoard[4][10] = "   ";
                }
            } else {
                gameBoard[4][9] = "OO";
                gameBoard[4][10] = "   ";
            }
        }

        if (!monsterField.isFull(4)) {
            gameBoard[4][1] = "E ";
            gameBoard[4][2] = "   ";
        } else {
            if (monsterField.getMonstersOnField().get(4).getPosition()
                    .equals(PositionMonsters.DEFENSE)) {
                if (monsterField.getMonstersOnField().get(4).getDefenceMode().
                        equals(DefensePosition.DH)) {
                    gameBoard[4][1] = "DH";
                    gameBoard[4][2] = "   ";
                } else {
                    gameBoard[4][1] = "DO";
                    gameBoard[4][2] = "   ";
                }
            } else {
                gameBoard[4][1] = "OO";
                gameBoard[4][2] = "   ";
            }
        }
    }
}
