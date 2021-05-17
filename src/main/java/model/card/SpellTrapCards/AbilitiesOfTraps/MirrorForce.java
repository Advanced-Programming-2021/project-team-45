package model.card.SpellTrapCards.AbilitiesOfTraps;


import model.card.SpellTrapCards.AbilitiesOfSpell.RingOfDefense;
import model.game.Game;
import model.game.GameBoard;
import model.card.MonsterCard;
import model.card.PositionMonsters;
import model.game.fields.SpellTrapField;

public class MirrorForce {
    public static void ability(Game game){
        if(canDoAbility(game)) {
            GameBoard opponentGameBoard = game.getGameBoardOfOpponentPlayerOfThisTurn();
            for (MonsterCard monsterCard : opponentGameBoard.getMonsterField().getMonstersOnField()) {
                if (monsterCard != null) {
                    if (monsterCard.getPosition() == PositionMonsters.ATTACK)
                        opponentGameBoard.getMonsterField().deleteAndDestroyMonster(monsterCard);
                }
            }
        }
    }
    private static boolean canDoAbility(Game game){
        if(RingOfDefense.isThereRingOfDefenseInField(game)!=null){
            if(RingOfDefense.ringOfDefenseHashMap.get(RingOfDefense.isThereRingOfDefenseInField(game)).getActive()){
                return false;
            }else{
                return true;
            }
        }
        return true;
    }

    public static boolean isThereAnyMirrorForce(GameBoard gameBoard){
        for(int i=0;i<gameBoard.getSpellTrapField().getSpellTrapsArrayList().size();i++) {
            if (gameBoard.getSpellTrapField().getSpellTrapsArrayList().get(i).getCardName().equals("Mirror Force")) {
                return true;
            }
        }
        return false;
    }
}