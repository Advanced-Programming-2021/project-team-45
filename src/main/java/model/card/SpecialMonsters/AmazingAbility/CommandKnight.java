package model.card.SpecialMonsters.AmazingAbility;

import model.game.Game;
import model.game.GameBoard;
import model.game.fields.MonsterField;
import model.card.MonsterCard;
import model.card.PositionMonsters;

import java.util.ArrayList;

public class CommandKnight {
    private static ArrayList<MonsterCard> allCardsThatIncreasedAttacks = new ArrayList<>();

    public static void abilityOfCommandKnightAtSummon(Game game) {
        ArrayList<MonsterCard> monsterCards = game.getGameBoardOfPlayerOfThisTurn().getMonsterField().getMonstersOnField();
        for (int i = 0; i < 5; i++) {
            if (!monsterCards.get(i).getCardName().equals("Command Knight")) {
                monsterCards.get(i).setAttack(monsterCards.get(i).getAttack() + 400);
                allCardsThatIncreasedAttacks.add(monsterCards.get(i));
            }
        }
    }

    public static void abilityOfCommandKnightAtDefense(Game game, MonsterCard card) {
        if (isCommandKnightAlone(game.getGameBoardOfOpponentPlayerOfThisTurn())) {
            if (card.getPosition().equals(PositionMonsters.ATTACK)) {
                game.attackToOpponentCardInAttackPosition((MonsterCard) game.getSelectedCard(), card,
                        game.getGameBoardOfPlayerOfThisTurn(), game.getGameBoardOfOpponentPlayerOfThisTurn());
            } else {
                game.attackToOpponentCardInDefensePosition((MonsterCard) game.getSelectedCard(), card,
                        game.getGameBoardOfPlayerOfThisTurn(), game.getGameBoardOfOpponentPlayerOfThisTurn());
            }
        }
    }

    private static boolean isCommandKnightAlone(GameBoard gameBoard) {
        ArrayList<MonsterCard> monsterCards = gameBoard.getMonsterField().getMonstersOnField();
        for (int i = 0; i < 5; i++) {
            if (!monsterCards.get(i).getCardName().equals("Command Knight")) {
                return false;
            }
        }
        return true;
    }

    public static void CommandKnightOnFieldWithSummonMode(MonsterCard selectedCardToSummonOrSet, MonsterField monsterField) {
        ArrayList<MonsterCard> monsterCards = monsterField.getMonstersOnField();
        for (int i = 0; i < 5; i++) {
            if (monsterCards.get(i).getCardName().equals("Command Knight")) {
                selectedCardToSummonOrSet.setAttack(selectedCardToSummonOrSet.getAttack() + 400);

            }
        }
    }
}
