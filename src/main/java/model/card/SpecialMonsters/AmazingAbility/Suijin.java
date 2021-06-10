package model.card.SpecialMonsters.AmazingAbility;

import model.game.Game;
import model.card.Card;
import model.card.MonsterCard;

import java.util.ArrayList;

public class Suijin {
    private static ArrayList<Card> allSuijinInEachTurn=new ArrayList<>();

    public static void ability(Card selectedCard, Game game) {
        if(!allSuijinInEachTurn.contains(selectedCard)) {
            allSuijinInEachTurn.add(selectedCard);
            game.getPlayerGameBoard().getMonsterField().deleteAndDestroyMonster((MonsterCard) selectedCard);
        }
    }

    public static void setAllSuijinInEachTurn(){
        allSuijinInEachTurn.clear();
    }
}
