package model.Game;

import model.card.Card;
import model.card.MonsterCard;
import model.card.PositionMonsters;
import model.card.SpellTrapCard;
import model.user.User;

import java.util.regex.Matcher;

public class Game {
    private boolean changeCardPosition = false; //change card position have to been false in change turn method .
    private String phase;
    private Card addedCardInDrawPhase;
    private User player;
    private User opponent;
    private int totalRounds;
    private User playerOfThisTurn;
    private GameBoard playerGameBoard;
    private GameBoard opponentGameBoard;
    private Card selectedCard;
    private int numberOfSummonsInThisTurn;
    private int numberOfSetsInThisTurn;
    private Card lastOpponentMonsterCard;


    public Game(User player, User opponent, int round) {
        this.player = player;
        this.opponent = opponent;
        this.totalRounds = round;
    }

    private void setPlayerOfNextTurn() {
        getGameBoardOfPlayerOfThisTurn().getMonsterField().deleteAttackedHistory();
        if ((this.playerOfThisTurn).equals(this.player))
            this.playerOfThisTurn = opponent;
        else this.playerOfThisTurn = player;
        this.numberOfSummonsInThisTurn=0;
        this.numberOfSetsInThisTurn=0;


    }

    public User getPlayerOfThisTurn() {
        return this.playerOfThisTurn;
    }

    public User getOpponentOfThisTurn() {
        if (this.playerOfThisTurn.equals(this.player))
            return opponent;
        else return player;
    }

    public GameBoard getGameBoardOfPlayerOfThisTurn() {
        if ((this.playerOfThisTurn).equals(this.player))
            return this.playerGameBoard;
        else return this.opponentGameBoard;
    }

    public GameBoard getGameBoardOfOpponentPlayerOfThisTurn() {
        if ((this.playerOfThisTurn).equals(this.player))
            return this.opponentGameBoard;
        else return this.playerGameBoard;
    }

    public boolean isInputForSelectCardValid(String cardType, int cardPosition, boolean isOpponentCard) {
        if (cardType.equals("--monster") || cardType.equals("-M") || cardType.equals("--spell") || cardType.equals("-S") ||
                cardType.equals("--field") || cardType.equals("-F") || cardType.equals("--hand") || cardType.equals("-H")) {
            if (!(cardType.equals("--hand") || cardType.equals("-H") ||
                    cardType.equals("--field") || cardType.equals("-F")) && cardPosition <= 5) return true;
            else {
                if ((cardType.equals("--field") || cardType.equals("-F")) && cardPosition == -1) return true;
                else {
                    if (isOpponentCard) {
                        return cardPosition <= this.getGameBoardOfOpponentPlayerOfThisTurn().getHand().getCardsInHand().size();
                    } else {
                        return cardPosition <= this.getGameBoardOfPlayerOfThisTurn().getHand().getCardsInHand().size();
                    }
                }
            }
        } else return false;
    }

    public boolean isThereAnyCardHere(String cardType, int cardPosition, boolean isOpponentCard) {
        GameBoard gameBoard;
        boolean result = false;
        if (isOpponentCard) {
            gameBoard = getGameBoardOfOpponentPlayerOfThisTurn();
            if (cardType.equals("--monster") || cardType.equals("-M")) {
                if (gameBoard.getMonsterField().isThisCellOfMonsterFieldEmptyInOpponentMode(cardPosition))
                    result = true;
                else result = true;
            } else if (cardType.equals("--spell") || cardType.equals("-S")) {
                result = gameBoard.getSpellTrapField().isThisCellOfSpellTrapFieldEmptyInOpponentMode(cardPosition);
            }
        } else {
            gameBoard = getGameBoardOfPlayerOfThisTurn();
            if (cardType.equals("--monster") || cardType.equals("-M")) {
                if (gameBoard.getMonsterField().isThisCellOfMonsterFieldEmptyInPlayerMode(cardPosition)) result = true;
                else result = true;
            } else if (cardType.equals("--spell") || cardType.equals("-S")) {
                result = gameBoard.getSpellTrapField().isThisCellOfSpellTrapFieldEmptyInPlayerMode(cardPosition);
            }
        }
        return result;
    }

    public boolean doesExistSelectedCard() {
        if (this.selectedCard == null) return false;
        else return true;
    }

    public Card getSelectedCard() {
        return selectedCard;
    }


    public String getPhase() {
        return phase;
    }

    public Card getAddedCardInDrawPhase() {
        return addedCardInDrawPhase;
    }

    public GameBoard getGameBoard() {
        if(playerOfThisTurn.equals(player)) return playerGameBoard;
        else return opponentGameBoard;

    }

    public void nextPhase() {
        if ((this.phase).equals("draw phase")) {
            this.phase = "standby phase";
        } else if ((this.phase).equals("standby phase")) {
            this.phase = "Main Phase1";
        } else if ((this.phase).equals("Main Phase1")) {
            this.phase = "End Phase";
        } else if ((this.phase).equals("End Phase")) {
            this.phase = "battle phase";
        } else if ((this.phase).equals("battle phase")) {
            this.phase = "Main Phase2";
        }
    }

    public void drawPhase() {
        // new -haji
        GameBoard gameBoard = getGameBoardOfPlayerOfThisTurn();
        this.addedCardInDrawPhase = gameBoard.getDeckField().getCard();
    }

    public void standbyPhase() {

    }

    public void mainPhase1() {
        this.numberOfSetsInThisTurn = 0;
        this.numberOfSummonsInThisTurn = 0;
    }

    public void endPhase() {

    }

    public void battlePhase() {

    }

    public void mainPhase2() {
        this.numberOfSetsInThisTurn = 0;
        this.numberOfSummonsInThisTurn = 0;
        this.player.setLastDamageAmount(0);
        this.opponent.setLastDamageAmount(0);
    }

    // SUMMON CARD METHODS:
    public boolean canSummonThisMonster() {
        boolean notNormalSummon = (selectedCard.getCardName().equals("Crab Turtle") ||
                selectedCard.getCardName().equals("Skull Guardian")
                || selectedCard.getCardName().equals("Gate Guardian"));

        if (notNormalSummon) {
            return false;
        } else {
            return true;
        }
    }

    public boolean isSelectedCardMonster() {
        return this.selectedCard instanceof MonsterCard;
    }

    public boolean isThereInHand() {
        GameBoard gameBoard = getGameBoardOfPlayerOfThisTurn();
        return gameBoard.getHand().doesCardExistInHand(this.selectedCard);
    }

    public boolean wasSummonOrSetCardBeforeInThisTurn() {
        if (this.numberOfSummonsInThisTurn != 0 || this.numberOfSetsInThisTurn != 0) {
            return true;
        } else {
            return false;
        }
    }

    public void summonMonster() {
        GameBoard gameBoard = getGameBoardOfPlayerOfThisTurn();
        MonsterCard monsterCard = (MonsterCard) this.selectedCard;
        monsterCard.summon();
        gameBoard.getMonsterField().addMonsterToField(monsterCard);
        this.selectedCard = null;
    }

    public boolean isThereCardForTribute5Or6() {
        GameBoard gameBoard = getGameBoardOfPlayerOfThisTurn();
        if (gameBoard.getMonsterField().getNumberOfMonstersInField() > 0)
            return true;
        else return false;
    }

    public boolean isEnoughCardForTribute7OrMore() {
        GameBoard gameBoard = getGameBoardOfPlayerOfThisTurn();
        if (gameBoard.getMonsterField().getNumberOfMonstersInField() > 1)
            return true;
        else return false;
    }

    public boolean canUseAorBForSummon(int A, int B) {
        GameBoard gameBoard = getGameBoardOfPlayerOfThisTurn();
        if (gameBoard.getMonsterField().isThisCellOfMonsterFieldEmptyInOpponentMode(A) ||
                gameBoard.getMonsterField().isThisCellOfMonsterFieldEmptyInOpponentMode(B))
            return false;
        else return true;
    }

    public boolean isMonsterFieldFull() {
        GameBoard gameBoard = getGameBoardOfPlayerOfThisTurn();
        return gameBoard.getMonsterField().isFull();
    }

    public void setMonster() {
        GameBoard gameBoard = getGameBoardOfPlayerOfThisTurn();
        MonsterCard monsterCard = (MonsterCard) this.selectedCard;
        monsterCard.set();
        gameBoard.getMonsterField().addMonsterToField(monsterCard);
    }

    public boolean isSpellTrapFieldFull() {
        GameBoard gameBoard = getGameBoardOfPlayerOfThisTurn();
        return gameBoard.getSpellTrapField().isFull();
    }

    public void setSpellOrTrap() {
        GameBoard gameBoard = getGameBoardOfPlayerOfThisTurn();
        SpellTrapCard spellTrapCard = (SpellTrapCard) this.selectedCard;
        spellTrapCard.set();
        gameBoard.getSpellTrapField().addSpellTrapCard(spellTrapCard);
    }

    public boolean isThereSelectedCardInMonsterField() {
        GameBoard gameBoard = getGameBoardOfPlayerOfThisTurn();
        return gameBoard.getMonsterField().doesExistCardInMonsterField(this.selectedCard);
    }

    //CHANGE POSITION:
    public boolean isChangeCorrect(Matcher matcher) {
        String newCardPosition = matcher.group();
        MonsterCard monsterCard = (MonsterCard) this.selectedCard;
        boolean result = true;
        if (newCardPosition.equals("attack")) {
            if (monsterCard.getPosition() == PositionMonsters.ATTACK) {
                result = false;
            }
        } else if (newCardPosition.equals("defense")) {
            if (monsterCard.getPosition() == PositionMonsters.DEFENSE) {
                result = true;
            } else {
                result = false;
            }
        }
        if (result) {
            this.changeCardPosition = true;
        }
        return result;
    }

    public boolean wasChangePositionInThisTurn() {
        return this.changeCardPosition;
    }

    public void changePosition() {
        MonsterCard monsterCard = (MonsterCard) this.selectedCard;
        monsterCard.changePosition();
    }

    public boolean isThereAnyMonsterInThisCell(int numberOfEnemyMonsterZone) {
        GameBoard gameBoard = getGameBoardOfOpponentPlayerOfThisTurn();
        if (gameBoard.getMonsterField().isThisCellOfMonsterFieldEmptyInOpponentMode(numberOfEnemyMonsterZone))
            return false;
        else return true;
    }

    private boolean isTargetCellInAttackPosition(int numberOfEnemyMonsterZone) {
        GameBoard gameBoard = getGameBoardOfOpponentPlayerOfThisTurn();
        if (gameBoard.getMonsterField().getMonsterCardFromMonsterField(numberOfEnemyMonsterZone).getPosition()
                == PositionMonsters.ATTACK)
            return true;
        else return false;
    }

    private boolean isTargetCellInDefensePosition(int numberOfEnemyMonsterZone) {
        GameBoard gameBoard = getGameBoardOfOpponentPlayerOfThisTurn();
        if (gameBoard.getMonsterField().getMonsterCardFromMonsterField(numberOfEnemyMonsterZone).getPosition()
                == PositionMonsters.DEFENSE)
            return true;
        else return false;
    }

    public boolean canFlipSummonSelectedCard() {
        MonsterCard playerCard = (MonsterCard) this.selectedCard;
        if (playerCard.getPosition().equals(PositionMonsters.DEFENSE)) { //hamchenin bayad DH bashad
            return true;
        } else return false;
    }

    public void flipSummon() {
        MonsterCard playerCard = (MonsterCard) this.selectedCard;
        playerCard.setPosition(PositionMonsters.ATTACK);
        this.selectedCard = playerCard;
    }

    public boolean wasThisCardAttackedInThisTurn() {
        MonsterCard answer=(MonsterCard) selectedCard;
        return answer.isWasAttackedInThisTurn();
    }

    public int attack(int numberOfEnemyMonsterZone) {
        int result = 0;
        MonsterCard handle=(MonsterCard) selectedCard;
        handle.setWasAttackedInThisTurn(true);
        GameBoard opponentGameBoard = getGameBoardOfOpponentPlayerOfThisTurn();
        GameBoard playerGameBoard = getGameBoardOfPlayerOfThisTurn();
        MonsterCard playerCard = (MonsterCard) this.selectedCard;
        MonsterCard opponentCard = opponentGameBoard.getMonsterField().getMonsterCardFromMonsterField(numberOfEnemyMonsterZone);
        if (isTargetCellInAttackPosition(numberOfEnemyMonsterZone)) {
            result = attackToOpponentCardInAttackPosition(playerCard, opponentCard, playerGameBoard, opponentGameBoard);
        } else if (isTargetCellInDefensePosition(numberOfEnemyMonsterZone)) {
            result = attackToOpponentCardInAttackPosition(playerCard, opponentCard, playerGameBoard, opponentGameBoard);
        }
        this.selectedCard = null;
        return result;
    }

    private int attackToOpponentCardInAttackPosition(MonsterCard playerCard, MonsterCard opponentCard,
                                                     GameBoard opponentGameBoard, GameBoard playerGameBoard) {
        int result = 0;
        if (playerCard.getAttack() > opponentCard.getAttack()) {
            result = 6;
            playerCard.attackMonster(opponentCard);
            opponentGameBoard.getMonsterField().deleteAnDestroyedMonster(opponentCard);
        } else if (playerCard.getAttack() == opponentCard.getAttack()) {
            result = 7;
            playerCard.attackMonster(opponentCard);
            opponentGameBoard.getMonsterField().deleteAnDestroyedMonster(opponentCard);
            playerGameBoard.getMonsterField().deleteAnDestroyedMonster(playerCard);
        } else if (playerCard.getAttack() < opponentCard.getAttack()) {
            result = 8;
            playerCard.attackMonster(opponentCard);
            playerGameBoard.getMonsterField().deleteAnDestroyedMonster(playerCard);
        }
        return result;
    }

    private int attackToOpponentCardInDefensePosition(MonsterCard playerCard, MonsterCard opponentCard,
                                                      GameBoard playerGameBoard, GameBoard opponentGameBoard) {
        int result = 0;
        /*
        ?
         */
        return result;
    }

    public boolean canDoDirectAttack() {
        GameBoard opponentGameBoard = getGameBoardOfOpponentPlayerOfThisTurn();
        if (opponentGameBoard.getMonsterField().getNumberOfMonstersInField() != 0) return true;
        else {
            /*
            another reasons
             */
            return false;
        }
    }

    public void directAttack() {
        MonsterCard monsterCard = (MonsterCard) this.selectedCard;
        User opponent = getOpponentOfThisTurn();
        monsterCard.attackOpponent(opponent);
        this.selectedCard = null;
    }

    public boolean isSelectedCardSpell() {
        if (this.selectedCard instanceof SpellTrapCard)
            return true;
        else return false;
    }

    public boolean isSelectedSpellActive() {
        SpellTrapCard spellTrapCard = (SpellTrapCard) this.selectedCard;
        return spellTrapCard.isActivated();
    }

    public boolean isSelectedCardInHand() {
        GameBoard gameBoard = getGameBoardOfPlayerOfThisTurn();
        if (gameBoard.getHand().doesCardExistInHand(this.selectedCard))
            return true;
        else return false;
    }

    public boolean isSelectedCardHaveToPutInField() {
        /*
        statements
         */
        return true;
    }

    public boolean canActiveSpell() {
        /*
        statements
         */
        return false;
    }

    public void activeSpell() {
        // seda zadan method spell
    }

    public String showGraveyard() {
        GameBoard gameBoard = getGameBoardOfPlayerOfThisTurn();
        return gameBoard.getGraveyard().toString();
    }

    public String calculateDamageOnMe() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.playerOfThisTurn.getLastDamageAmount());
        return stringBuilder.toString();
    }

    public String calculateDamageOnEnemy() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getOpponentOfThisTurn().getLastDamageAmount());
        return stringBuilder.toString();
    }

    public String getEnemyCardName(){
        return this.lastOpponentMonsterCard.getCardName();
    }

    public String showCard() {
        return Card.showCard(selectedCard);
    }


}
