package controller;

import model.Game.Game;
import model.Game.GameBoard;
import model.Game.MonsterField;
import model.Game.SpellTrapField;
import model.card.MonsterCard;
import view.menu.DuelMenu;
import view.menu.Menu;

public class GameController extends Controller {

    private Game game;

    public void createNewGame() {
        this.game = new Game();
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

    public int deSelectErrorHAndler(String input) {
        if (DuelMenu.getGame().isCardSelected(input)) return 0;
        else return 1;
    }

    public int nextPhaseInController() {
        game.nextPhase();
        String phase = game.getPhase();
        if (phase.equals("draw phase")) {
            game.drawPhase();
            return 1;
        } else if (phase.equals("standby phase")) {
            game.standbyPhase();
            return 0;
        } else if (phase.equals("Main Phase1") {
            game.mainPhase1();
            return 2;
        }else if (phase.equals("End Phase") {
            game.EndPhase();
            return 3;
        }
    }

    public int summonErrorHandler() {
        if (game.isThereSelectedCard()) {
            if (game.canSummonThisMonster() && game.isSelectedCardMonster() && game.isThereInHAnd()) {
                if (!(game.getPhase().equals("Main Phase1") || game.getPhase().equals("Main Phase2"))) {
                    if (MonsterField.isFull()) {
                        if (!game.wasSummonOrSetCardBeforeInThisTurn()) {
                            int cardLevel = ((MonsterCard) game.getSelectedCard()).getLevel();
                            if ((cardLevel <= 4) {
                                game.summonMonster();
                                return 6;
                            }else if (cardLevel == 5 || cardLevel == 6) {
                                if (game.isThereCardForTribute5Or6()) {
                                    int house = Menu.scanner.nextInt();
                                    if (!game.getGameBoard().isThisCellEmpty(house)) {
                                        game.summonMonster();
                                        return 6;
                                    } else return 8;

                                } else return 7;
                            } else if (cardLevel == 7 || cardLevel == 8){
                                if(game.isEnoughCardForTribute7Or8()){
                                    int A,B;
                                    A=Menu.scanner.nextInt();
                                    B=Menu.scanner.nextInt();
                                    if(game.canUseAorBForSummon(A,B)){
                                        game.summonMonster();
                                        return 6;
                                    }else return 9;
                                }else return 7;
                            }
                        } else return 5;
                    } else return 4;
                } else return 3;
            } else return 2;
        } else return 1;
    }

    public int setCardErrorHandler(){
        if(game.isThereSelectedCard()){
            if(game.isThereInHAnd()){
                if(game.isSelectedCardMonster()){
                    if((game.getPhase().equals("Main Phase1")||game.getPhase().equals("Main Phase2"))){
                        if(!MonsterField.isFull()){
                            if(!game.wasSummonOrSetCardBeforeInThisTurn()){
                                game.setMonster();
                                return 6;
                            }else return 5;
                        }else return 4;
                    }else return 3;
                }else{
                    if((game.getPhase().equals("Main Phase1")||game.getPhase().equals("Main Phase2"))){
                        if(!SpellTrapField.isFull()){
                            game.setSpellOrTrap();
                            return 6;
                        }else return 7;
                    }else return 3;
                }
            }else return 2;
        }else return 1;
    }

    public Game getGame() {
        return game;
    }
}
