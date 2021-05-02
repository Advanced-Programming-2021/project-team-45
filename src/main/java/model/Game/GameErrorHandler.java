package model.Game;

import model.card.MonsterCard;
import model.card.PositionMonsters;
import model.card.SpellTrapCard;

import java.util.regex.Matcher;

public class GameErrorHandler {

    private final Game game;

    public GameErrorHandler(Game game) {
        this.game = game;
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
                        return cardPosition <= game.getGameBoardOfOpponentPlayerOfThisTurn().getHand().getCardsInHand().size();
                    } else {
                        return cardPosition <= game.getGameBoardOfPlayerOfThisTurn().getHand().getCardsInHand().size();
                    }
                }
            }
        } else return false;
    }

    public boolean isThereAnyCardHere(String cardType, int cardPosition, boolean isOpponentCard) {
        GameBoard gameBoard;
        boolean result;
        if (isOpponentCard) {
            gameBoard = game.getGameBoardOfOpponentPlayerOfThisTurn();
            if (cardType.equals("--monster") || cardType.equals("-M")) {
                if (gameBoard.getMonsterField().isThisCellOfMonsterFieldEmptyInOpponentMode(cardPosition))
                    result = true;
                else result = true;
            } else if (cardType.equals("--spell") || cardType.equals("-S")) {
                result = gameBoard.getSpellTrapField().isThisCellOfSpellTrapFieldEmptyInOpponentMode(cardPosition);
            } else if (cardType.equals("--field") || cardType.equals("-F")) {
                result = gameBoard.getFieldZone().isFull();
            } else {
                result = gameBoard.getHand().doesCardExistInThesePlace(cardPosition);
            }
        } else {
            gameBoard = game.getGameBoardOfPlayerOfThisTurn();
            if (cardType.equals("--monster") || cardType.equals("-M")) {
                if (gameBoard.getMonsterField().isThisCellOfMonsterFieldEmptyInPlayerMode(cardPosition)) result = true;
                else result = true;
            } else if (cardType.equals("--spell") || cardType.equals("-S")) {
                result = gameBoard.getSpellTrapField().isThisCellOfSpellTrapFieldEmptyInPlayerMode(cardPosition);
            } else if (cardType.equals("--field") || cardType.equals("-F")) {
                result = gameBoard.getFieldZone().isFull();
            } else {
                result = gameBoard.getHand().doesCardExistInThesePlace(cardPosition);
            }
        }
        return result;
    }

    public boolean doesSelectedCardExist() {
        return game.getSelectedCard() != null;
    }

    public boolean isSelectedCardMonster() {
        return game.getSelectedCard() instanceof MonsterCard;
    }

    public boolean isCardInHand() {
        GameBoard gameBoard = game.getGameBoardOfPlayerOfThisTurn();
        return gameBoard.getHand().doesCardExistInHand(game.getSelectedCard());
    }

    public boolean isMonsterFieldFull() {
        GameBoard gameBoard = game.getGameBoardOfPlayerOfThisTurn();
        return gameBoard.getMonsterField().isFull();
    }

    public boolean isSpellTrapFieldFull() {
        GameBoard gameBoard = game.getGameBoardOfPlayerOfThisTurn();
        return gameBoard.getSpellTrapField().isFull();
    }

    public boolean wasSummonOrSetCardBeforeInThisTurn() {
        return game.getNumberOfSummonsInThisTurn() != 0 || game.getNumberOfSetsInThisTurn() != 0;
    }

    public boolean isThereSelectedCardInMonsterField() {
        GameBoard gameBoard = game.getGameBoardOfPlayerOfThisTurn();
        return gameBoard.getMonsterField().doesExistCardInMonsterField(game.getSelectedCard());
    }

    //CHANGE POSITION:
    public boolean isChangeCorrect(Matcher matcher) {
        String newCardPosition = "";
        if (matcher.find()) newCardPosition = matcher.group(1);
        MonsterCard monsterCard = (MonsterCard) game.getSelectedCard();
        boolean result = true;
        if (newCardPosition.equals("attack")) {
            if (monsterCard.getPosition() == PositionMonsters.ATTACK) {
                result = false;
            }
        } else if (newCardPosition.equals("defense")) {
            result = monsterCard.getPosition() == PositionMonsters.DEFENSE;
        }
        return result;
    }

    public boolean wasChangePositionInThisTurn() {
        return game.getChangeCardPosition();
    }

    public boolean canFlipSummonSelectedCard() {
        MonsterCard playerCard = (MonsterCard) game.getSelectedCard();
        //hamchenin bayad DH bashad
        return playerCard.getPosition().equals(PositionMonsters.DEFENSE);
    }

    public boolean wasThisCardAttackedInThisTurn() {
        MonsterCard answer = (MonsterCard) game.getSelectedCard();
        return answer.isWasAttackedInThisTurn();
    }

    public boolean isThereAnyMonsterInThisCell(int numberOfEnemyMonsterZone) {
        GameBoard gameBoard = game.getGameBoardOfOpponentPlayerOfThisTurn();
        return !gameBoard.getMonsterField().isThisCellOfMonsterFieldEmptyInOpponentMode(numberOfEnemyMonsterZone);
    }

    public boolean isSelectedCardSpell() {
        return game.getSelectedCard() instanceof SpellTrapCard;
    }

    public boolean isSelectedSpellActive() {
        SpellTrapCard spellTrapCard = (SpellTrapCard) game.getSelectedCard();
        return spellTrapCard.isActivated();
    }

    public boolean isSelectedCardInHand() {
        GameBoard gameBoard = game.getGameBoardOfPlayerOfThisTurn();
        return gameBoard.getHand().doesCardExistInHand(game.getSelectedCard());
    }
}