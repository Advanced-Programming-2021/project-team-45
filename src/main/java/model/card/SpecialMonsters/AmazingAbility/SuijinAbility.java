package model.card.SpecialMonsters.AmazingAbility;

import model.Game.Game;
import model.card.Card;
import model.card.MonsterCard;

import java.util.ArrayList;

public class SuijinAbility {

    public static void suijinAbility(Card selectedCard, Game game) {
        game.getGameBoardOfPlayerOfThisTurn().getMonsterField().deleteAndDestroyMonster((MonsterCard) selectedCard);
    }
}
