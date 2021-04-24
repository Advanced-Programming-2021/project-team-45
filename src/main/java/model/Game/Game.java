package model.Game;

import model.card.Card;
import model.card.MonsterCard;
import model.card.PositionMonsters;
import model.card.SpellTrapCard;
import model.user.User;

public class Game {
    private String phase;
    private Card addedCardInDrawPhase;
    private User player;
    private User opponent;
    private User playerOfThisTurn;
    private GameBoard playerGameBoard;
    private GameBoard opponentGameBoard;
    private Card selectedCard;
    private int numberOfSummonsInThisTurn;
    private int numberOfSetsInThisTurn;

    // selected faghat card nist
    public Game(User player, User opponent){
        this.player = player;
        this.opponent = opponent;
    }

    private void setPlayerOfNextTurn(){
        if((this.playerOfThisTurn).equals(this.player))
            this.playerOfThisTurn = opponent;
        else this.playerOfThisTurn = player;
    }

    private User getPlayerOfThisTurn(){
        return this.playerOfThisTurn;
    }

    private GameBoard getGameBoardOfPlayerOfThisTurn(){
        if((this.playerOfThisTurn).equals(this.player))
            return this.playerGameBoard;
        else return this.opponentGameBoard;
    }

    public boolean isInputForSelectCardValid(String cardType, int cardPosition, boolean isOpponentCard){
        if(cardType.equals("--monster") || cardType.equals("-M") || cardType.equals("--spell") || cardType.equals("-S") ||
        cardType.equals("--field") || cardType.equals("-F") || cardType.equals("--hand") || cardType.equals("-H")){
            if(!(cardType.equals("--hand") || cardType.equals("-H") ||
                    cardType.equals("--field") || cardType.equals("-F")) && cardPosition <= 5) return true;
            else{
                if((cardType.equals("--field") || cardType.equals("-F")) && cardPosition == -1) return true;
                else{
                    if(isOpponentCard){
                        return cardPosition <= this.opponentGameBoard.getHand().getCardsInHand().size();
                    } else{
                        return cardPosition <= this.playerGameBoard.getHand().getCardsInHand().size();
                    }
                }
            }
        } else return false;
    }

    public boolean isThereAnyCardHere(String cardType, int cardPosition, boolean isOpponentCard){
        GameBoard gameBoard;
        boolean result = false;
        if(isOpponentCard) gameBoard = this.opponentGameBoard;
        else gameBoard = this.playerGameBoard;
        if(cardType.equals("--monster") || cardType.equals("-M")){
            if(gameBoard.getMonsterField().isThisCellOfMonsterFieldEmpty(cardPosition)) result = true;
            else result = true;
        }
        else if(cardType.equals("--spell") || cardType.equals("-S")){
            if(gameBoard.getSpellTrapField().isThisCellOfSpellTrapFieldEmpty(cardPosition)) result = true;
            else result = false;
        }
        return result;
    }
    public boolean doesExistSelectedCard(){
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
        // which one? opponent or player -haji
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
        // new -haji
        GameBoard gameBoard = getGameBoardOfPlayerOfThisTurn();
        this.addedCardInDrawPhase = gameBoard.getDeckField().getCard();
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
        GameBoard gameBoard = getGameBoardOfPlayerOfThisTurn();
        return gameBoard.getHand().doesCardExistInHand(this.selectedCard);
    }

    public boolean wasSummonOrSetCardBeforeInThisTurn(){
        if(this.numberOfSummonsInThisTurn != 0 ||
        this.numberOfSetsInThisTurn != 0)
            return true;
        else return false;
    }

    public void summonMonster(){
        GameBoard gameBoard = getGameBoardOfPlayerOfThisTurn();
        MonsterCard monsterCard = (MonsterCard) this.selectedCard;
        monsterCard.summon();
        gameBoard.getMonsterField().addMonsterToField(monsterCard);
        this.selectedCard = null;
    }

    public boolean isThereCardForTribute5Or6(){
        GameBoard gameBoard = getGameBoardOfPlayerOfThisTurn();
        if(gameBoard.getMonsterField().getNumberOfMonstersInField() > 0)
            return true;
        else return false;
    }

    public boolean isEnoughCardForTribute7OrMore(){
        GameBoard gameBoard = getGameBoardOfPlayerOfThisTurn();
        if(gameBoard.getMonsterField().getNumberOfMonstersInField() > 1)
            return true;
        else return false;
    }

    public boolean canUseAorBForSummon(int A, int B){
        GameBoard gameBoard = getGameBoardOfPlayerOfThisTurn();
        if(gameBoard.getMonsterField().isThisCellOfMonsterFieldEmpty(A) ||
        gameBoard.getMonsterField().isThisCellOfMonsterFieldEmpty(B))
            return false;
        else return true;
    }

    public boolean isMonsterFieldFull(){
        GameBoard gameBoard = getGameBoardOfPlayerOfThisTurn();
       return gameBoard.getMonsterField().isFull();
    }

    public void setMonster(){
        GameBoard gameBoard = getGameBoardOfPlayerOfThisTurn();
        MonsterCard monsterCard = (MonsterCard) this.selectedCard;
        monsterCard.set();
        gameBoard.getMonsterField().addMonsterToField(monsterCard);
    }

    public boolean isSpellTrapFieldFull(){
        GameBoard gameBoard = getGameBoardOfPlayerOfThisTurn();
        return gameBoard.getSpellTrapField().isFull();
    }

    public void setSpellOrTrap(){
        GameBoard gameBoard = getGameBoardOfPlayerOfThisTurn();
        SpellTrapCard spellTrapCard = (SpellTrapCard) this.selectedCard;
        spellTrapCard.set();
        gameBoard.getSpellTrapField().addSpellTrapCard(spellTrapCard);
    }

    public boolean isThereSelectedCardInMonsterField(){
        GameBoard gameBoard = getGameBoardOfPlayerOfThisTurn();
        return gameBoard.getMonsterField().doesExistCardInMonsterField(this.selectedCard);
    }

    public boolean isChangeCorrect(String newCardPosition){
        MonsterCard monsterCard = (MonsterCard) this.selectedCard;
        boolean result = true;
        if(newCardPosition.equals("attack")){
            if(monsterCard.getPosition() == PositionMonsters.ATTACK)
                result = false;
            else result = true;
        } else if(newCardPosition.equals("defense")){
            if(monsterCard.getPosition() == PositionMonsters.DEFENSE)
                result = true;
            else result = false;
        }
        return result;
    }

    public void changePosition(){
        MonsterCard monsterCard = (MonsterCard) this.selectedCard;
        monsterCard.changePosition();
    }
}
