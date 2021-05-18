package model.card.SpellTrapCards.effects;


import model.card.SpellTrapCard;
import model.card.SpellTrapCards.effects.RingOfDefense;
import model.game.Chain;
import model.game.Game;
import model.game.GameBoard;
import model.card.MonsterCard;
import model.card.PositionMonsters;


public class MirrorForce extends Effect {


    @Override
    public void activate(Chain chain) {
        if (canDoAbility(chain.getGame())) {
            GameBoard opponentGameBoard = chain.getGame().getGameBoardOfOpponentPlayerOfThisTurn();
            for (MonsterCard monsterCard : opponentGameBoard.getMonsterField().getMonstersOnField()) {
                if (monsterCard != null) {
                    if (monsterCard.getPosition() == PositionMonsters.ATTACK)
                        opponentGameBoard.getMonsterField().deleteAndDestroyMonster(monsterCard);
                }
            }
        }
    }


    private static boolean canDoAbility(Game game) {
        if (RingOfDefense.isThereRingOfDefenseInField(game) != null) {
            if (RingOfDefense.ringOfDefenseHashMap.get(RingOfDefense.isThereRingOfDefenseInField(game)).getActive()) {
                game.getGameBoardOfPlayerOfThisTurn().getSpellTrapField().
                        deleteAndDestroySpellTrap((SpellTrapCard) RingOfDefense.isThereRingOfDefenseInField(game));
                return false;

            } else {
                return true;
            }
        }
        return true;
    }

    public static boolean isThereAnyMirrorForce(GameBoard gameBoard) {
        for (int i = 0; i < gameBoard.getSpellTrapField().getSpellTrapsArrayList().size(); i++) {
            if (gameBoard.getSpellTrapField().getSpellTrapsArrayList().get(i).getCardName().equals("Mirror Force")) {
                return true;
            }
        }
        return false;
    }


}