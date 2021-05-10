//package model.card.SpellTrapCards.AbilitiesOfTraps;
//
//import model.game.Game;
//import model.card.SpellTrapCard;
//
//public class MagicJammer {
//    public static boolean isASpellActivated = true;
//
//    public static void ability(Game game){
//        /*
//        a boolean to recognize that is a spell activated
//        and also the SpellCard that activated
//         */
//        if(isASpellActivated) {
//            /*
//            get the activated card
//             */
//            SpellTrapCard spellTrapCard;
//            /*
//            deActivate the card
//             */
//            if(spellTrapCard.getOwner().equals(game.getPlayerOfThisTurn())) {
//                game.getGameBoardOfPlayerOfThisTurn().getSpellTrapField().deleteAndDestroySpellTrap(spellTrapCard);
//            } else
//                game.getGameBoardOfOpponentPlayerOfThisTurn().getSpellTrapField().deleteAndDestroySpellTrap(spellTrapCard);
//        }
//    }
//}
