package controller;

import model.Game.Game;
import model.Game.GameBoard;
import model.Game.MonsterField;
import model.Game.SpellTrapField;
import model.card.MonsterCard;
import model.user.User;
import view.menu.DuelMenu;
import view.menu.Menu;

import java.util.regex.Matcher;

public class GameController extends Controller {

    private Game game;

    public void createNewGame() {
        this.game = new Game(player, opponent);
    }

    public GameController(String username, String opponentUsername, int rounds) {


        // make modifications in order to start the game correctly between username and opponentUsername


        super(username);
    }

    public int selectCardErrorHandler(String cardType, int cardPosition, boolean isOpponentCard) {
        if (game.isInputForSelectCardValid(cardType, cardPosition, isOpponentCard)) {
            if (game.isThereAnyCardHere(cardType, cardPosition, isOpponentCard)) {
                return 0;
            } else {
                return 2;
            }
        } else {
            return 1;
        }
    }

    public int deselectErrorHandler() {
        if (game.doesExistSelectedCard()) {
            return 0;
        } else {
            return 1;
        }
    }

    public int nextPhaseInController() {
        game.nextPhase();
        String phase = game.getPhase();
        switch (phase) {
            case "draw phase":
                game.drawPhase();
                return 1;

            case "standby phase":
                game.standbyPhase();
                return 0;

            case "Main Phase1":
                game.mainPhase1();
                return 2;

            case "End Phase":
                game.endPhase();
                return 5;

            case "battle phase":
                game.battlePhase();
                return 3;

            case "Main Phase2":
                game.mainPhase2();
                return 4;
        }
        return -1;
    }

    public int summonErrorHandler() {
        if (game.doesExistSelectedCard()) {
            if (game.canSummonThisMonster() && game.isSelectedCardMonster() && game.isThereInHand()) {
                if (!(game.getPhase().equals("Main Phase1") || game.getPhase().equals("Main Phase2"))) {
                    if (game.isMonsterFieldFull()) {
                        if (!game.wasSummonOrSetCardBeforeInThisTurn()) {
                            int cardLevel = ((MonsterCard) game.getSelectedCard()).getLevel();
                            if (cardLevel <= 4) {
                                game.summonMonster();
                                return 6;
                            } else if (cardLevel == 5 || cardLevel == 6) {
                                if (game.isThereCardForTribute5Or6()) {
                                    int house = Menu.scanner.nextInt();
                                    if (!game.getGameBoard().getMonsterField()
                                            .isThisCellOfMonsterFieldEmptyInPlayerMode(house)) {
                                        game.summonMonster();
                                        return 6;
                                    } else {
                                        return 8;
                                    }

                                } else {
                                    return 7;
                                }
                            } else  {   //cardLevel == 7 Or cardLevel == 8
                                if (game.isEnoughCardForTribute7OrMore()) {
                                    int A, B;
                                    A = Menu.inputInt();
                                    B = Menu.inputInt();
                                    if (game.canUseAorBForSummon(A, B)) {
                                        game.summonMonster();
                                        return 6;
                                    } else {
                                        return 9;
                                    }
                                } else {
                                    return 7;
                                }
                            }
                        } else {
                            return 5;
                        }
                    } else {
                        return 4;
                    }
                } else {
                    return 3;
                }
            } else {
                return 2;
            }
        } else {
            return 1;
        }
    }

    public int setCardErrorHandler() {
        if (game.doesExistSelectedCard()) {
            if (game.isThereInHand()) {
                if (game.isSelectedCardMonster()) {
                    if ((game.getPhase().equals("Main Phase1") || game.getPhase().equals("Main Phase2"))) {
                        if (!game.isMonsterFieldFull()) {
                            if (!game.wasSummonOrSetCardBeforeInThisTurn()) {
                                game.setMonster();
                                return 6;
                            } else {
                                return 5;
                            }
                        } else {
                            return 4;
                        }
                    } else {
                        return 3;
                    }
                } else {
                    if ((game.getPhase().equals("Main Phase1") || game.getPhase().equals("Main Phase2"))) {
                        if (!game.isSpellTrapFieldFull()) {
                            game.setSpellOrTrap();
                            return 6;
                        } else {
                            return 7;
                        }
                    } else {
                        return 3;
                    }
                }
            } else {
                return 2;
            }
        } else {
            return 1;
        }
    }

    public int changePositionErrorHandler(Matcher matcher) {
        if (game.doesExistSelectedCard()) {
            if (game.isThereSelectedCardInMonsterField()) {
                if (game.getPhase().equals("Main Phase1") || game.getPhase().equals("Main Phase2")) {
                    if (game.isChangeCorrect(matcher)) {
                        if (!game.wasChangePositionInThisTurn()) {
                            game.changePosition();
                            return 6;
                        } else {
                            return 5;
                        }
                    } else {
                        return 4;
                    }
                } else {
                    return 3;
                }
            } else {
                return 2;
            }
        } else {
            return 1;
        }
    }

    public int flipSummonErrorHandler() {
        if (game.doesExistSelectedCard()) {
            if (game.isThereSelectedCardInMonsterField()) {
                if (game.getPhase().equals("Main Phase1") || game.getPhase().equals("Main Phase2")) {
                    if (game.canFlipSummonSelectedCard()) {
                        game.flipSummon();
                        return 5;
                    } else {
                        return 4;
                    }
                } else {
                    return 3;
                }
            } else {
                return 2;
            }
        } else {
            return 1;
        }
    }

    public int attackErrorHandler(int numberOfEnemyMonsterZone) {
        if (game.doesExistSelectedCard()) {
            if (game.isThereSelectedCardInMonsterField()) {
                if (game.getPhase().equals("battle phase")) {
                    if (!game.wasThisCardAttackedInThisTurn()) {
                        if (game.isThereAnyMonsterInThisCell(numberOfEnemyMonsterZone)) {
                            int returnedNumber = game.attack(numberOfEnemyMonsterZone);
                            return returnedNumber;
                            /*
                            some order have to done for attack method in model{
                            enemy card in attack position:
                            my attack power > enemy attack power -> return 6
                            my attack power = enemy attack power -> return 7
                            my attack power < enemy attack power -> return 8
                            enemy card in defense position:
                            my attack power > enemy defense power :
                                DH mode -> return 12
                                Not DH mode -> return 9;
                            my attack power = enemy defense power :
                                DH mode -> return 13
                                Not DH mode -> return 10;
                            my attack power > enemy defense power :
                                DH mode -> return 14
                                Not DH mode -> return 11;
                             }
                             */

                        } else {
                            return 5;
                        }
                    } else {
                        return 4;
                    }
                } else {
                    return 3;
                }
            } else {
                return 2;
            }
        } else {
            return 1;
        }
    }

    public int directAttackErrorHandler() {
        if (game.doesExistSelectedCard()) {
            if (game.isThereSelectedCardInMonsterField()) {
                if (game.getPhase().equals("battle phase")) {
                    if (!game.wasThisCardAttackedInThisTurn()) {
                        if (game.canDoDirectAttack()) {
                            game.directAttack();
                            return 6;
                        }
                        return 5;
                    }
                    return 4;
                }
                return 3;
            }
            return 2;
        }
        return 1;
    }

    public int activeEffectErrorHandler() {
        if (game.doesExistSelectedCard()) {
            if (game.isSelectedCardSpell()) {
                if (game.getPhase().equals("Main Phase 1") || game.getPhase().equals("Main Phase 2")) {
                    if (!game.isSelectedSpellActive()) {
                        if (game.isSelectedCardInHand() && game.isSpellTrapFieldFull()
                                && game.isSelectedCardHaveToPutInField()) {
                            return 5;
                        }
                        if (game.canActiveSpell()) {
                            game.activeSpell();
                            return 7;
                        }
                        return 6;
                    }
                    return 4;
                }
                return 3;
            }
            return 2;
        }
        return 1;
    }

    public String controlGraveyard() {
        String answer = game.showGraveyard();
        return answer;
    }

    public String controlCardShow() {
        String answer =game.showCard();
        return answer;
    }

    public String damageOnOpponent() {
        String answer = game.calculateDamageOnEnemy();
        return answer;
    }

    public String damageOnPlayer() {
        String answer = game.calculateDamageOnMe();
        return answer;
    }

    public String getDefenseTargetCardName() {
        String answer = game.getEnemyCardName();
        return answer;
    }

    public Game getGame() {
        return game;
    }
}
