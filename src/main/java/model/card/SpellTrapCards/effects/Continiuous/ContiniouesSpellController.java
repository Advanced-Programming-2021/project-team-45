package model.card.SpellTrapCards.effects.Continiuous;

import model.game.Game;
import model.game.fields.Graveyard;

public class ContiniouesSpellController {

    public static void Controller(String CardName, Game game, ContinouesSpellActivatePlace place) {
        if (CardName.equals("Supply Squad") && place.equals(ContinouesSpellActivatePlace.GraveYard)) {
            if (SupplySquad.isThereSupplySquadInSpellField(game.getGameBoardOfOpponentPlayerOfThisTurn()
                    .getSpellTrapField()) != null) {
                SupplySquad.isThereSupplySquadInSpellField(game.getGameBoardOfOpponentPlayerOfThisTurn()
                        .getSpellTrapField()).doActivity(game);
            }
        } else if (CardName.equals("Spell Absorption") && place.equals(ContinouesSpellActivatePlace.Activation)) {
            if (SpellAbsorption.isThereSpellAbsorptionInField(game.getGameBoardOfOpponentPlayerOfThisTurn()
                    .getSpellTrapField()) != null) {
                SpellAbsorption.isThereSpellAbsorptionInField(game.getGameBoardOfOpponentPlayerOfThisTurn()
                        .getSpellTrapField()).doActivity(game);
            }
        }


    }
}
