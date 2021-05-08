package model.card.SpecialMonsters.AmazingAbility;

import model.Game.Game;
import model.Game.GameBoard;
import model.Game.MonsterField;
import model.card.MonsterCard;

import java.io.IOException;
import java.util.ArrayList;

public class Scanner {
    private static ArrayList<MonsterCard> convertToScanner=new ArrayList<>();

    public static void activeAbilityOfScanner(Game game,MonsterCard scanner){
        MonsterCard selectedMonster=game.getGameController()
                .getACardFromGraveyardForScanner("enter a card name from graveyard:");;
        while (true){
            if(selectedMonster==null){
                break;
            }else{
                if(!selectedMonster.getCardName().equals("-1")){
                    break;
                }
            }
            selectedMonster=game.getGameController()
                    .getACardFromGraveyardForScanner("invalid card name please enter correct card name");

        }
        if(selectedMonster!=null) {

            for (int i = 0; i < 5; i++) {
                if (game.getGameBoardOfPlayerOfThisTurn().getMonsterField().
                        getMonstersOnField().get(i).getCardName().equals("Scanner")) {
                    scanner = game.getGameBoardOfPlayerOfThisTurn().getMonsterField().
                            getMonstersOnField().get(i);
                }
                swapMonsterAndScanner(game.getGameBoardOfPlayerOfThisTurn(), scanner, selectedMonster);
            }
        }
    }
    private static void swapMonsterAndScanner(GameBoard gameBoard,MonsterCard scanner,MonsterCard monster){
        MonsterField monsterField= gameBoard.getMonsterField();
        ArrayList<MonsterCard> monsterCards=monsterField.getMonstersOnField();
        for(int i=0;i<5;i++){
            if(monsterCards.get(i).equals(scanner)){
                monsterCards.set(i,monster);
                convertToScanner.add(monster);
                break;
            }
        }
    }
    public static void deleteSwapMonsterIfHadScanner(MonsterField monsterField){
        ArrayList<MonsterCard> monsterCards=monsterField.getMonstersOnField();
        for(int i=0;i<5;i++){
            if(convertToScanner.contains(monsterCards.get(i))){
                try {
                    convertToScanner.remove(monsterCards.get(i));
                    monsterCards.set(i,new MonsterCard("Scanner"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /*
    دقت شود که موقع استفاده از این شرط این متود را درون حلقه وایل قرار بدیم چون امکان داره چنتا اسکنر رو فیلد باشه
     */
    public static MonsterCard haveScanner(MonsterField monsterField){
        ArrayList<MonsterCard> monsterCards=monsterField.getMonstersOnField();
        for(int i=0;i<monsterCards.size();i++){
            if(monsterCards.get(i).getCardName().equals("Scanner")){
                return monsterCards.get(i);
            }
        }
        try {
            return new MonsterCard("-1");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
