package model;

import model.card.Card;
import model.card.MonsterCard;
import model.card.SpellTrapCard;
import model.game.Game;
import model.game.GameBoard;
import model.user.User;

import java.util.Random;

public class ArtificialIntelligence {
    private User AI;
    public ArtificialIntelligence(){
        AI=new User("AI","1234","artificial intelligence");
        AI.getCardInventory().setAICardInventory();
        AI.getUserDeck().createDeck("AIDECK",User.getUserByUsername("AI"));
        AI.getUserDeck().activateDeck("AIDECK");
        AI.getUserDeck().getActiveDeck().setDeckForAI();
    }


    public User getAI() {
        return AI;
    }

    public static void playTurn(Game game){
        game.drawPhase();
        mainPhase1(game);
        battlePhase(game);
        game.endPhase();
    }

    private static void mainPhase1(Game game){
        Random random=new Random();
        if(game.getGameBoardOfPlayerOfThisTurn().getHand().getCardsInHand().size()>0){
            Card card=game.getGameBoardOfPlayerOfThisTurn().getHand().getCardFromHand(random.nextInt(game
                    .getGameBoardOfPlayerOfThisTurn().getHand().getCardsInHand().size()));
            if(card instanceof MonsterCard){
                if(game.getGameBoardOfPlayerOfThisTurn().getMonsterField().getMonstersOnField().size()<5) {
                    game.getGameBoardOfPlayerOfThisTurn().getMonsterField().addMonsterToField((MonsterCard) card);
                   ((MonsterCard) card).summon();
                }
            }else{
                if(game.getGameBoardOfPlayerOfThisTurn().getSpellTrapField().getSpellTrapsArrayList().size()<5){
                    game.getGameBoardOfPlayerOfThisTurn().getSpellTrapField().addSpellTrapCard((SpellTrapCard) card);
                    ((SpellTrapCard) card).set();
                }
            }
        }
    }

    private static void battlePhase(Game game){
        for(int i=0;i<game.getGameBoardOfPlayerOfThisTurn().getMonsterField().getMonstersOnField().size();i++){
            game.setSelectedCard1(game.getGameBoardOfPlayerOfThisTurn().getMonsterField().getMonster(i));
            if(whereIsEnemy(game)==0){
                game.directAttack();
            }else{
                game.attack(whereIsEnemy(game));
            }
        }
    }

    private static int whereIsEnemy(Game game){
        for(int i=1;i<6;i++){
            if(game.getGameBoardOfOpponentPlayerOfThisTurn().getMonsterField()
                    .getMonsterCardOpponentFromMonsterField(i)!=null){
                return i;
            }
        }
        return 0;
    }


}
