package Server.model.card.SpecialMonsters.AmazingAbility;

import Server.model.game.Game;
import Server.model.game.GameBoard;
import Server.model.game.fields.MonsterField;
import Server.model.card.MonsterCard;
import Server.model.card.PositionMonsters;

import java.util.ArrayList;

public class CommandKnight {
    private static ArrayList<MonsterCard> allCardsThatIncreasedAttacks = new ArrayList<>();

    public static void summonAbility(Game game) {
        ArrayList<MonsterCard> monsterCards = game.getPlayerGameBoard().getMonsterField().getMonstersOnField();
        for (int i = 0; i < 5; i++) {
            if (!monsterCards.get(i).getCardName().equals("Command Knight")) {
                monsterCards.get(i).setAttack(monsterCards.get(i).getAttack() + 400);
                allCardsThatIncreasedAttacks.add(monsterCards.get(i));
            }
        }
    }

    public static void defenseAbility(Game game, MonsterCard card) {
        if (isCommandKnightAlone(game.getOpponentGameBoard())) {
            if (card.getPosition().equals(PositionMonsters.ATTACK)) {
                game.attackToOpponentCardInAttackPosition((MonsterCard) game.getSelectedCard(), card,
                        game.getPlayerGameBoard(), game.getOpponentGameBoard());
            } else {
                game.attackToOpponentCardInDefensePosition((MonsterCard) game.getSelectedCard(), card,
                        game.getPlayerGameBoard(), game.getOpponentGameBoard());
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
        for (int i = 0; i < monsterCards.size(); i++) {
            if (monsterCards.get(i).getCardName().equals("Command Knight")) {
                selectedCardToSummonOrSet.setAttack(selectedCardToSummonOrSet.getAttack() + 400);

            }
        }
    }
}
