package controller;

import model.Game.Game;
import model.Game.GameBoard;
import model.Game.MonsterField;
import model.Game.SpellTrapField;
import model.card.MonsterCard;
import model.user.User;
import view.menu.DuelMenu;
import view.menu.Menu;

public class GameController extends Controller {

    private Game game;

    public void createNewGame() {
        this.game = new Game(player, opponent);
    }

    public GameController(String username) {
        super(username);
    }

    public int selectCardErrorHandler(String input) {
        if (game.isInputForSelectCardValid(input)) {
            if (game.isThereAnyCardHere(input)) return 0;
            else return 2;
        } else return 1;
    }

    public int deSelectErrorHAndler() {
        if (game.isThereSelectedCard()) return 0;
        else return 1;
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
        if (game.isThereSelectedCard()) {
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
                                    if (!game.getGameBoard().getMonsterField().isThisCellEmpty(house)) {
                                        game.summonMonster();
                                        return 6;
                                    } else return 8;

                                } else return 7;
                            } else if (cardLevel == 7 || cardLevel == 8) {
                                if (game.isEnoughCardForTribute7OrMore()) {
                                    int A, B;
                                    A = Menu.scanner.nextInt();
                                    B = Menu.scanner.nextInt();
                                    if (game.canUseAorBForSummon(A, B)) {
                                        game.summonMonster();
                                        return 6;
                                    } else return 9;
                                } else return 7;
                            }
                        } else return 5;
                    } else return 4;
                } else return 3;
            } else return 2;
        } else return 1;
    }

    public int setCardErrorHandler() {
        if (game.isThereSelectedCard()) {
            if (game.isThereInHand()) {
                if (game.isSelectedCardMonster()) {
                    if ((game.getPhase().equals("Main Phase1") || game.getPhase().equals("Main Phase2"))) {
                        if (!game.isMonsterFieldFull()) {
                            if (!game.wasSummonOrSetCardBeforeInThisTurn()) {
                                game.setMonster();
                                return 6;
                            } else return 5;
                        } else return 4;
                    } else return 3;
                } else {
                    if ((game.getPhase().equals("Main Phase1") || game.getPhase().equals("Main Phase2"))) {
                        if (!game.isSpellTrapFieldFull()) {
                            game.setSpellOrTrap();
                            return 6;
                        } else return 7;
                    } else return 3;
                }
            } else return 2;
        } else return 1;
    }

    public int changePositionErrorHandler() {
        if (game.isThereSelectedCard()) {
            if (game.isThereSelectedCardInMonsterField()) {
                if (game.getPhase().equals("Main Phase1") || game.getPhase().equals("Main Phase2")) {
                    if (game.isChangeCorrect()) {
                        if (!game.wasChangePositionInThisTurn()) {
                            game.changePosition();
                            return 6;
                        } else return 5;
                    } else return 4;
                } else return 3;
            } else return 2;
        } else return 1;
    }

    public int flipSummonErrorHandler() {
        if (game.isThereSelectedCard()) {
            if (game.isThereSelectedCardInMonsterField()) {
                if (game.getPhase().equals("Main Phase1" || game.getPhase().equals("Main Phase2"))) {
                    if (game.canFlipSummonSelectedCard()) {
                        game.flipSummon();
                        return 5;
                    } else return 4;
                } else return 3;
            } else return 2;
        } else return 1;
    }

    public int attackErrorHandler(int numberOfEnemyMonsterZone) {
        if (game.isThereSelectedCard()) {
            if (game.isThereSelectedCardInMonsterField()) {
                if (game.getPhase().equals("battle phase")) {
                    if (!game.wasThisCardAttackedInThisTurn()) {
                        if (game.isThereAnyMonsterInThisCell(numberOfEnemyMonsterZone)) {
                            if(game.isTargetCellInAttackPosition(numberOfEnemyMonsterZone)){
                                if(game.isTargetCellAttackPowerMoreThanMe()){
                                    game.attackToOfensiveCard();// needed argument
                                    return 6;
                                }else if(game.isTargetCellAttackPowerEqualWithMe()){
                                    game.attackToOfensiveCard();
                                    return 7;
                                }else{
                                    game.attackToOfensiveCard();
                                    return 8;
                                }
                            }else{
                                if(game.isTargetDefenseLowerThanMyAttackPower()){
                                    game.attackToDefensiveCard();
                                    if(game.isDefenseCardOnDHStyle()) return 12;
                                    else return 9;
                                }else if(game.isTargetDefenseEqualMyAttackPower()){
                                    game.attackToDefensiveCard();
                                    if(game.isDefenseCardOnDHStyle()) return 13;
                                    else return 10;
                                }else{
                                    game.attackToDefensiveCard();
                                    if(game.isDefenseCardOnDHStyle()) return 14;
                                    else return 11;
                                }
                            }
                        } else return 5;
                    } else return 4;
                } else return 3;
            } else return 2;
        } else return 1;
    }

    public String damageOnOpponent(){

    }

    public String damageOnPlayer(){

    }

    public String getDefenseTargetCardName(){

    }
    public Game getGame() {
        return game;
    }
}
