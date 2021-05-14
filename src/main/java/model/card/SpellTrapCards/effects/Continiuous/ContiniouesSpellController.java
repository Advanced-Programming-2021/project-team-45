package model.card.SpellTrapCards.effects.Continiuous;

import model.game.Game;
import model.game.fields.Graveyard;

public class ContiniouesSpellController {

    public static int Controller(String CardName, Game game, ContinouesSpellActivatePlace place) {
        if (CardName.equals("Supply Squad") && place.equals(ContinouesSpellActivatePlace.GraveYard)) {
            if (SupplySquad.isThereSupplySquadInSpellField(game.getGameBoardOfOpponentPlayerOfThisTurn()
                    .getSpellTrapField()) != null) {
                SupplySquad.isThereSupplySquadInSpellField(game.getGameBoardOfOpponentPlayerOfThisTurn()
                        .getSpellTrapField()).doActivity(game);
                return 0;
            }
        } else if (CardName.equals("Spell Absorption") && place.equals(ContinouesSpellActivatePlace.Activation)) {
            if (SpellAbsorption.isThereSpellAbsorptionInField(game.getGameBoardOfOpponentPlayerOfThisTurn()
                    .getSpellTrapField()) != null) {
                SpellAbsorption.isThereSpellAbsorptionInField(game.getGameBoardOfOpponentPlayerOfThisTurn()
                        .getSpellTrapField()).doActivity(game);
                return 0;
            }
        } else if (CardName.equals("Messenger Of Peace") && place.equals(ContinouesSpellActivatePlace.Attack)) {
            if (MessengerOfPeace.isThereMessengerOfPeaceOnField(game.getGameBoardOfOpponentPlayerOfThisTurn()
                    .getSpellTrapField()) != null){
                return MessengerOfPeace.isThereMessengerOfPeaceOnField(game.getGameBoardOfOpponentPlayerOfThisTurn()
                        .getSpellTrapField()).doActivityInAttack(game);
            }
        } else if(CardName.equals("Messenger Of Peace") && place.equals(ContinouesSpellActivatePlace.StandBy)){
            if (MessengerOfPeace.isThereMessengerOfPeaceOnField(game.getGameBoardOfPlayerOfThisTurn()
                    .getSpellTrapField()) != null){
                return MessengerOfPeace.isThereMessengerOfPeaceOnField(game.getGameBoardOfPlayerOfThisTurn()
                        .getSpellTrapField()).doActivityInStandByPhase(game);
            }
        }
        return 0;
    }
}
