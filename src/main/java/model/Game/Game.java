package model.Game;

import model.card.Card;
import model.card.MonsterCard;
import model.card.SpellTrapCard;
import model.user.User;

public class Game {
    private String phase;
    private Card addedCardInDrawPhase;
    private User player;
    private User opponent;
    private GameBoard gameBoard;
    private Card selectedCard;
    private int numberOfSummonsInThisTurn;
    private int numberOfSetsInThisTurn;

    // selected faghat card nist
    public Game(User player, User opponent){
        this.player = player;
        this.opponent = opponent;
    }

    public boolean isThereSelectedCard(){
        if(this.selectedCard == null) return false;
        else return true;
    }
    public Card getSelectedCard() {
        return selectedCard;
    }

    public User getOpponent() {
        return opponent;
    }

    public String getPhase() {
        return phase;
    }

    public Card getAddedCardInDrawPhase() {
        return addedCardInDrawPhase;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public void nextPhase(){
        if((this.phase).equals("draw phase")){
            this.phase = "standby phase";
        } else if((this.phase).equals("standby phase")){
            this.phase = "Main Phase1";
        } else if((this.phase).equals("Main Phase1")){
            this.phase = "End Phase";
        } else if((this.phase).equals("End Phase")){
            this.phase = "battle phase";
        } else if((this.phase).equals("battle phase")){
            this.phase = "Main Phase2";
        }
    }

    public void drawPhase(){
        this.addedCardInDrawPhase = this.gameBoard.getDeckField().getCard();
    }

    public void standbyPhase(){

    }

    public void mainPhase1(){
        this.numberOfSetsInThisTurn = 0;
        this.numberOfSummonsInThisTurn = 0;
    }

    public void endPhase(){

    }

    public void battlePhase(){

    }

    public void mainPhase2(){
        this.numberOfSetsInThisTurn = 0;
        this.numberOfSummonsInThisTurn = 0;
    }

    public boolean isSelectedCardMonster(){
        return this.selectedCard instanceof MonsterCard;
    }

    public boolean isThereInHand(){
        return this.gameBoard.getHand().doesCardExistInHand(this.selectedCard);
    }

    public boolean wasSummonOrSetCardBeforeInThisTurn(){
        if(this.numberOfSummonsInThisTurn != 0 ||
        this.numberOfSetsInThisTurn != 0)
            return true;
        else return false;
    }

    public void summonMonster(){
        MonsterCard monsterCard = (MonsterCard) this.selectedCard;
        monsterCard.summon();
        this.gameBoard.getMonsterField().addMonsterToField(monsterCard);
        this.selectedCard = null;
    }

    public boolean isThereCardForTribute5Or6(){
        if(this.gameBoard.getMonsterField().getNumberOfMonstersInField() > 0)
            return true;
        else return false;
    }

    public boolean isEnoughCardForTribute7OrMore(){
        if(this.gameBoard.getMonsterField().getNumberOfMonstersInField() > 1)
            return true;
        else return false;
    }

    public boolean canUseAorBForSummon(int A, int B){
        if(this.gameBoard.getMonsterField().isThisCellEmpty(A) ||
        this.gameBoard.getMonsterField().isThisCellEmpty(B))
            return false;
        else return true;
    }

    public boolean isMonsterFieldFull(){
       return this.gameBoard.getMonsterField().isFull();
    }

    public void setMonster(){
        MonsterCard monsterCard = (MonsterCard) this.selectedCard;
        monsterCard.set();
        this.gameBoard.getMonsterField().addMonsterToField(monsterCard);
    }

    public boolean isSpellTrapFieldFull(){
        return this.gameBoard.getSpellTrapField().isFull();
    }

    public void setSpellOrTrap(){
        SpellTrapCard spellTrapCard = (SpellTrapCard) this.selectedCard;
        spellTrapCard.set();
        this.gameBoard.getSpellTrapField().addSpellTrapCard(spellTrapCard);
    }

    public boolean isThereSelectedCardInMonsterField(){
        return this.gameBoard.getMonsterField().doesExistCardInMonsterField(this.selectedCard);
    }
}
