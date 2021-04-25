package model.Game;

import model.card.SpellsAndTrapPosition;
import model.user.User;

import java.util.ArrayList;

public class GameBoard {
    private User owner;
    private DeckField deckField;
    private Graveyard graveyard;
    private Hand hand;
    private MonsterField monsterField;
    private SpellTrapField spellTrapField;
    private FieldZone fieldZone;

    public GameBoard(User owner, Graveyard graveyard,
                     Hand hand, MonsterField monsterField, SpellTrapField spellTrapField, FieldZone fieldZone, DeckField deckField) {
        this.owner = owner;
        this.graveyard = graveyard;
        this.hand = hand;
        this.monsterField = monsterField;
        this.spellTrapField = spellTrapField;
        this.fieldZone = fieldZone;
        this.deckField = deckField;
    }

    public MonsterField getMonsterField() {
        return monsterField;
    }

    public SpellTrapField getSpellTrapField() {
        return spellTrapField;
    }

    public User getOwner() {
        return owner;
    }

    public Hand getHand() {
        return hand;
    }

    public DeckField getDeckField() {
        return deckField;
    }

    public Graveyard getGraveyard() {
        return graveyard;
    }

    public String GameBoardOfPlayer(GameBoard gameBoardOfPlayer){
        String[][] gameBoard=new String[7][gameBoardOfPlayer.getHand().getCardsInHand().size()+12];
        gameBoard[0][0]=(gameBoardOfPlayer.getOwner().getNickname()+": ");
        gameBoard[0][1]=String.valueOf(gameBoardOfPlayer.getOwner().getLifepoint().getLifepoint());
        gameBoard[1][0]="    ";
        for (int i=1;i<gameBoardOfPlayer.getHand().getCardsInHand().size()*2;i+=2){
            gameBoard[1][i]="c";
            gameBoard[1][i+1]="    ";
        }
        gameBoard[2][0]=String.valueOf(gameBoardOfPlayer.getOwner().getUserDeck().getActiveDeck());
        gameBoard[3][0]="    ";
        int  forIndex=0;
        for(int i=1;i<10;i+=2){
            if(gameBoardOfPlayer.spellTrapField.getSpellTrapCardsOnField()[forIndex].getPosition().
                    equals(SpellsAndTrapPosition.SUMMON)){
                gameBoard[3][i]="O";
                gameBoard[3][i+1]="    ";
            }
            else {
                gameBoard[3][i]="H";
                gameBoard[3][i+1]="    ";
            }
            forIndex++;
        }
        gameBoard[4][0]="    ";


        //hanooz takmil nashode


    }
}
