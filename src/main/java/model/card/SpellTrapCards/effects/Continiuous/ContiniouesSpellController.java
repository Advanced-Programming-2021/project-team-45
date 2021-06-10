package model.card.SpellTrapCards.effects.Continiuous;

import model.game.Game;

public class ContiniouesSpellController {

    public static int Controller(String CardName, Game game, ContinouesSpellActivatePlace place) {
        if (CardName.equals("Supply Squad") && place.equals(ContinouesSpellActivatePlace.GraveYard)) {
            if (SupplySquad.isThereSupplySquadInSpellField(game.getOpponentGameBoard()
                    .getSpellTrapField()) != null) {
                SupplySquad.isThereSupplySquadInSpellField(game.getOpponentGameBoard()
                        .getSpellTrapField()).doActivity(game);
                return 0;
            }
        } else if (CardName.equals("Spell Absorption") && place.equals(ContinouesSpellActivatePlace.Activation)) {
            if (SpellAbsorption.isThereSpellAbsorptionInField(game.getOpponentGameBoard()
                    .getSpellTrapField()) != null) {
                SpellAbsorption.isThereSpellAbsorptionInField(game.getOpponentGameBoard()
                        .getSpellTrapField()).doActivity(game);
                return 0;
            }
        } else if (CardName.equals("Messenger Of Peace") && place.equals(ContinouesSpellActivatePlace.Attack)) {
            if (MessengerOfPeace.isThereMessengerOfPeaceOnField(game.getOpponentGameBoard()
                    .getSpellTrapField()) != null){
                return MessengerOfPeace.isThereMessengerOfPeaceOnField(game.getOpponentGameBoard()
                        .getSpellTrapField()).doActivityInAttack(game);
            }
        } else if(CardName.equals("Messenger Of Peace") && place.equals(ContinouesSpellActivatePlace.StandBy)){
            if (MessengerOfPeace.isThereMessengerOfPeaceOnField(game.getPlayerGameBoard()
                    .getSpellTrapField()) != null){
                return MessengerOfPeace.isThereMessengerOfPeaceOnField(game.getPlayerGameBoard()
                        .getSpellTrapField()).doActivityInStandByPhase(game);
            }
        }
        return 0;
    }
}
