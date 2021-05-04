package controller;

import model.Game.*;
import model.card.MonsterCard;
import model.card.SpecialMonsterEnum;
import model.user.User;
import view.menu.DuelMenu;
import view.menu.MainMenu;
import view.menu.Menu;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class GameController extends Controller {

    private final User player;
    private final User opponentPlayer;
    private final int rounds;
    private Game game;
    private GameErrorHandler gameErrorHandler;
    private int playerWins = 0;
    private int opponentWins = 0;
    private DuelMenu playerDuelMenu;


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
        playerDuelMenu = new DuelMenu(player.getUsername(), this);
        playerDuelMenu.show();

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
                            MonsterCard monster = (MonsterCard) game.getSelectedCard();
                            if (monster.getLevel() < 5) {
                                game.summonMonster();
                                return 6;

                            } else {
                                return tributeSummonErrorHandler(monster);

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
        }

        return 1;
    }

    private int tributeSummonErrorHandler(MonsterCard monster) {
        ArrayList<Integer> cardsToTribute = new ArrayList<>();
        if (!gameErrorHandler.isThereEnoughCardsToTribute(monster)) {
            return 7;
        } else {
            if (monster.getSpecial() == SpecialMonsterEnum.BEAST_KING_BARBAROS) {


            } else if (monster.getLevel() > 10) {
                cardsToTribute = playerDuelMenu.getCardsForTribute(3);
                if (gameErrorHandler.isTributeCardsValid(cardsToTribute)) {
                    game.tributeSummon(cardsToTribute);
                    return 6;
                } else {
                    return 9;
                }
            } else if (monster.getLevel() >= 7) {
                cardsToTribute = playerDuelMenu.getCardsForTribute(2);
                if (gameErrorHandler.isTributeCardsValid(cardsToTribute)) {
                    game.tributeSummon(cardsToTribute);
                    return 6;
                } else {
                    return 9;
                }
            } else {
                cardsToTribute = playerDuelMenu.getCardsForTribute(1);
                if (gameErrorHandler.isTributeCardsValid(cardsToTribute)) {
                    game.tributeSummon(cardsToTribute);
                    return 6;
                } else {
                    return 8;
                }
            }
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

    public void activeSpellAndTrapInOtherTurn(){
        playerDuelMenu.activeSpellOrTrapInOtherPlayerTurn();
    }
    public int activeSpellOrTrapInOtherPlayerTurnErrorHandler() {
        getGame().changeTurnForSpecials();
        String answer = Menu.inputString();

        if (answer.equals("yes") || answer.equals("YES") || answer.equals("Yes")) {
            return getNextCommandForActiveSpellOrTrap();
        } else {
            getGame().changeTurnForSpecials();
            return 1;
        }

    }
    public int getNextCommandForActiveSpellOrTrap() {
        String input = Menu.inputString();
        Matcher matcher = Regex.getMatcher(input, playerDuelMenu.getDUEL_MENU_REGEX()[0]);
        if (matcher.find()) {
            if (matcher.group(3) != null) {
                if (selectCardSpecial(matcher)) {
                    String newInput =Menu.inputString();
                    Matcher newMatcher = Regex.getMatcher(newInput, playerDuelMenu.getDUEL_MENU_REGEX()[0]);
                    if (newMatcher.find() && newMatcher.group(12) != null) {
                        activateEffectOfTrapAndSpell();
                        return 3;
                    } else return 2;
                } else return 2;
            } else return 2;
        } else return 2;
        
    }

    private boolean selectCardSpecial(Matcher matcher) {
        if (matcher.find()) {
            String cardType = matcher.group(1);
            boolean isOpponentCard = matcher.group(2).matches("--opponent|-O");
            int cardPosition = Integer.parseInt(matcher.group(3));
            int error =selectCardErrorHandler(cardType, cardPosition, isOpponentCard);
            if (error == 0) {
                return true;
            } else if (error == 1) {
                return false;
            } else {
                return false;
            }
        }
        return false;
    }


    public void activateEffectOfTrapAndSpell(){
        game.activeSelectedTrapOrSpell();
        game.deselectCard();
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
