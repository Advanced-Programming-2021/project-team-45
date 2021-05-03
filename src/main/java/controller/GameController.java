package controller;

import model.Game.*;
import model.card.MonsterCard;
import model.user.User;
import view.menu.DuelMenu;
import view.menu.MainMenu;
import view.menu.Menu;

import java.util.regex.Matcher;

public class GameController extends Controller {

    private final User player;
    private final User opponentPlayer;
    private final int rounds;
    private Game game;
    private GameErrorHandler gameErrorHandler;
    private int playerWins = 0;
    private int opponentWins = 0;

    public GameController(String username, String opponentUsername, int rounds) {
        super(username);
        this.player = User.getUserByUsername(username);
        this.opponentPlayer = User.getUserByUsername(opponentUsername);
        this.rounds = rounds;
    }


    private void createNewGame() {
        this.game = new Game(player, opponentPlayer, rounds);
        this.gameErrorHandler = new GameErrorHandler(game);
    }

    public void startGame() {
        while (playerWins < rounds && opponentWins < rounds) {
            createNewGame();
            playRound();
        }
        // go back to first player's MainMenu:
        MainMenu playerMainMenu = new MainMenu(player.getUsername());
        playerMainMenu.show();
        playerMainMenu.execute();
    }

    private void playRound() {
        DuelMenu playerDuelMenu = new DuelMenu(player.getUsername(), this);
        playerDuelMenu.show();

        boolean finishRound = false;
        while (!game.isFinished()) {

            playerDuelMenu.getNextCommand();





        }
    }








    ///////////////////////////////////////////// ERROR HANDLING:

    public int selectCardErrorHandler(String cardType, int cardPosition, boolean isOpponentCard) {
        if (gameErrorHandler.isInputForSelectCardValid(cardType, cardPosition, isOpponentCard)) {

            if (gameErrorHandler.isThereAnyCardHere(cardType, cardPosition, isOpponentCard)) {
                game.selectCard(cardType, cardPosition, isOpponentCard);
                return 0;
            } else {
                return 2;
            }

        } else {
            return 1;
        }
    }

    public int deselectErrorHandler() {
        if (gameErrorHandler.doesSelectedCardExist()) {
            game.deselectCard();
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
        if (gameErrorHandler.doesSelectedCardExist()) {
            if (gameErrorHandler.isSelectedCardMonster() && gameErrorHandler.isCardInHand() && game.canSummonThisMonster()) {
                if (!(game.getPhase().equals("Main Phase1") || game.getPhase().equals("Main Phase2"))) {
                    if (gameErrorHandler.isMonsterFieldFull()) {
                        if (!gameErrorHandler.wasSummonOrSetCardBeforeInThisTurn()) {
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
                            } else {   //cardLevel == 7 Or cardLevel == 8
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
        if (gameErrorHandler.doesSelectedCardExist()) {
            if (gameErrorHandler.isCardInHand()) {
                if (gameErrorHandler.isSelectedCardMonster()) {
                    if ((game.getPhase().equals("Main Phase1") || game.getPhase().equals("Main Phase2"))) {
                        if (!gameErrorHandler.isMonsterFieldFull()) {
                            if (!gameErrorHandler.wasSummonOrSetCardBeforeInThisTurn()) {
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
                        if (!gameErrorHandler.isSpellTrapFieldFull()) {
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
        if (gameErrorHandler.doesSelectedCardExist()) {
            if (gameErrorHandler.isThereSelectedCardInMonsterField()) {
                if (game.getPhase().equals("Main Phase1") || game.getPhase().equals("Main Phase2")) {
                    if (gameErrorHandler.isChangeCorrect(matcher)) {
                        if (!gameErrorHandler.wasChangePositionInThisTurn()) {
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
        if (gameErrorHandler.doesSelectedCardExist()) {
            if (gameErrorHandler.isThereSelectedCardInMonsterField()) {
                if (game.getPhase().equals("Main Phase1") || game.getPhase().equals("Main Phase2")) {
                    if (gameErrorHandler.canFlipSummonSelectedCard()) {
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
        if (gameErrorHandler.doesSelectedCardExist()) {
            if (gameErrorHandler.isThereSelectedCardInMonsterField()) {
                if (game.getPhase().equals("battle phase")) {
                    if (!gameErrorHandler.wasThisCardAttackedInThisTurn()) {
                        if (gameErrorHandler.isThereAnyMonsterInThisCell(numberOfEnemyMonsterZone)) {
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
                            my attack power < enemy defense power :
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
        if (gameErrorHandler.doesSelectedCardExist()) {
            if (gameErrorHandler.isThereSelectedCardInMonsterField()) {
                if (game.getPhase().equals("battle phase")) {
                    if (!gameErrorHandler.wasThisCardAttackedInThisTurn()) {
                        if (game.canDirectAttack()) {
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
        if (gameErrorHandler.doesSelectedCardExist()) {
            if (gameErrorHandler.isSelectedCardSpell()) {
                if (game.getPhase().equals("Main Phase 1") || game.getPhase().equals("Main Phase 2")) {
                    if (!gameErrorHandler.isSelectedSpellActive()) {
                        if (gameErrorHandler.isSelectedCardInHand() && gameErrorHandler.isSpellTrapFieldFull()
                                && game.isSelectedCardHaveToPutInField()) {
                            return 5;
                        }
                        if (game.canActivateSpell()) {
                            game.activateSpell();
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
        return game.showGraveyard();
    }

    public String controlCardShow() {
        return game.showCard();
    }

    public String damageOnOpponent() {
        return game.calculateDamageOnEnemy();
    }

    public String damageOnPlayer() {
        return game.calculateDamageOnMe();
    }

    public String getDefenseTargetCardName() {
        return game.getEnemyCardName();
    }

    public Game getGame() {
        return game;
    }
}
