package model.game;

import model.card.MonsterCard;
import model.card.PositionMonsters;
import model.card.SpecialMonsterEnum;
import model.card.SpellTrapCard;
import model.game.fields.MonsterField;

import java.util.ArrayList;
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
                result = !(gameBoard.getMonsterField().isThisCellOfOpponentMonsterFieldEmpty(cardPosition));
            } else if (cardType.equals("--spell") || cardType.equals("-S")) {
                result = !(gameBoard.getSpellTrapField().isThisCellOfOpponentSpellTrapFieldEmpty(cardPosition));
            } else if (cardType.equals("--field") || cardType.equals("-F")) {
                result = gameBoard.getFieldZone().isFull();
            } else {
                result = gameBoard.getHand().doesCardExistInThesePlace(cardPosition);
            }
        } else {
            gameBoard = game.getGameBoardOfPlayerOfThisTurn();
            if (cardType.equals("--monster") || cardType.equals("-M")) {
                result = !(gameBoard.getMonsterField().isThisCellOfPlayerMonsterFieldEmpty(cardPosition));
            } else if (cardType.equals("--spell") || cardType.equals("-S")) {
                result = !(gameBoard.getSpellTrapField().isThisCellOfPlayerSpellTrapFieldEmpty(cardPosition));
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
        return gameBoard.getHand().doesCardExist(game.getSelectedCard().getCardName());
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
        return gameBoard.getMonsterField().doesCardExist(game.getSelectedCard().getCardName());
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
        return answer.wasAttackedInThisTurn();
    }

    public boolean isThereAnyMonsterInThisCell(int numberOfEnemyMonsterZone) {
        GameBoard gameBoard = game.getGameBoardOfOpponentPlayerOfThisTurn();
        return !gameBoard.getMonsterField().isThisCellOfOpponentMonsterFieldEmpty(numberOfEnemyMonsterZone);
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
        return gameBoard.getHand().doesCardExist(game.getSelectedCard().getCardName());
    }

    public boolean isThereEnoughCardsToTribute(MonsterCard monster) {
        MonsterField monsterField = game.getPlayerGameBoard().getMonsterField();
        if (monster.getSpecial() == SpecialMonsterEnum.BEAST_KING_BARBAROS) {
            return true;

        } else if (monster.getLevel() > 10) {
            return monsterField.getNumberOfMonstersInField() > 2;

        } else if (monster.getLevel() >= 7) {
            return monsterField.getNumberOfMonstersInField() > 1;

        } else {
            return monsterField.getNumberOfMonstersInField() > 0;

        }
    }

    public boolean isTributeCardsValid(ArrayList<Integer> cardsToTribute) {
        MonsterField monsterField = game.getPlayerGameBoard().getMonsterField();
        for (int i : cardsToTribute) {
            if (monsterField.isThisCellOfPlayerMonsterFieldEmpty(i)) {
                return false;
            }
        }
        return true;
    }
}
