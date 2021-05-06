package model.card.SpecialMonsters.AmazingAbility;

import model.Game.Game;
import model.Game.GameBoard;
import model.Game.MonsterField;
import model.card.Card;
import model.card.MonsterCard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Scanner {
    private static ArrayList<MonsterCard> convertToScanner=new ArrayList<>();

    public static void activeAbilityOfScanner(Game game){
        MonsterCard selectedMonster=null; // bayad voroodi gerefte she az graveyard  ((((( motmaen shodan darbare new shodan ya hamoon card))))
        MonsterCard scanner=null;
        for(int i=0;i<5;i++){
            if(game.getGameBoardOfPlayerOfThisTurn().getMonsterField().
                    getMonstersOnField()[i].getCardName().equals("Scanner")){
                scanner=game.getGameBoardOfPlayerOfThisTurn().getMonsterField().
                        getMonstersOnField()[i];
            }
            swapMonsterAndScanner(game.getGameBoardOfPlayerOfThisTurn(),scanner,selectedMonster);
        }
    }
    private static void swapMonsterAndScanner(GameBoard gameBoard,MonsterCard scanner,MonsterCard monster){
        MonsterField monsterField= gameBoard.getMonsterField();
        MonsterCard[] monsterCards=monsterField.getMonstersOnField();
        for(int i=0;i<5;i++){
            if(monsterCards[i].equals(scanner)){
                monsterCards[i]=monster;
                convertToScanner.add(monster);
                break;
            }
        }
    }
    public static void deleteSwapMonsterIfHadScanner(MonsterField monsterField){
        MonsterCard[] monsterCards=monsterField.getMonstersOnField();
        for(int i=0;i<5;i++){
            if(convertToScanner.contains(monsterCards[i])){
                try {
                    convertToScanner.remove(monsterCards[i]);
                    monsterCards[i]=new MonsterCard("Scanner");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /*
    دقت شود که موقع استفاده از این شرط این متود را درون حلقه وایل قرار بدیم چون امکان داره چنتا اسکنر رو فیلد باشه
     */
    public static boolean haveScanner(MonsterField monsterField){
        MonsterCard[] monsterCards=monsterField.getMonstersOnField();
        for(int i=0;i<5;i++){
            if(monsterCards[i].getCardName().equals("Scanner")){
                return true;
            }
        }
        return false;
    }


}
