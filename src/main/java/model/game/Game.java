package model.game;

import controller.GameController;
import model.ArtificialIntelligence;
import model.card.*;
import model.card.SpecialMonsters.AmazingAbility.*;
import model.card.SpecialMonsters.EffectPlace;
import model.card.SpecialMonsters.SpecialMonster;
import model.card.SpellTrapCards.AbilitiesOfTraps.TimeSeal;
import model.card.SpellTrapCards.effects.Continiuous.ContiniouesSpellController;
import model.card.SpellTrapCards.effects.Continiuous.ContinouesSpellActivatePlace;
import model.game.fields.MonsterField;
import model.game.fields.SpellTrapField;
import model.user.User;

import java.util.ArrayList;

public class Game {

    private boolean changeCardPosition = false; //change card position have to been false in change turn method.
    private String phase;
    private Card addedCardInDrawPhase;
    private final User player;
    private final User opponent;
    private final int totalRounds;
    private User playerOfThisTurn;
    private final GameBoard playerGameBoard;
    private final GameBoard opponentGameBoard;
    private Card selectedCard;
    private int numberOfSummonsInThisTurn;
    private int numberOfSetsInThisTurn;
    private Card lastOpponentMonsterCard;
    private GameController gameController;
    private User surrendered;


    public Game(User player, User opponent, int round, GameController gameController) {
        this.player = player;
        this.opponent = opponent;
        this.totalRounds = round;
        this.gameController = gameController;
        this.playerGameBoard = new GameBoard(player, this);
        this.opponentGameBoard = new GameBoard(opponent, this);
        player.getLifepoint().startNewGame();
        opponent.getLifepoint().startNewGame();
    }

    private void setPlayerOfNextTurn() {
        getGameBoardOfPlayerOfThisTurn().getMonsterField().deleteAttackedHistory();
        if ((this.playerOfThisTurn).equals(this.player))
            this.playerOfThisTurn = opponent;
        else this.playerOfThisTurn = player;
        this.numberOfSummonsInThisTurn = 0;
        this.numberOfSetsInThisTurn = 0;
        this.changeCardPosition = false;


    }

    public boolean isFinished() {
        return player.getLifepoint().getLifepoint() == 0 ||
                opponent.getLifepoint().getLifepoint() == 0 ||
                surrendered != null;
    }

    public void changeTurnForSpecials() {
        if ((this.playerOfThisTurn).equals(this.player))
            this.playerOfThisTurn = opponent;
        else this.playerOfThisTurn = player;
    }

    public User getPlayer() {
        return player;
    }

    public User getOpponent() {
        return opponent;
    }

    public boolean getChangeCardPosition() {
        return changeCardPosition;
    }

    public int getNumberOfSummonsInThisTurn() {
        return numberOfSummonsInThisTurn;
    }

    public int getNumberOfSetsInThisTurn() {
        return numberOfSetsInThisTurn;
    }

    public GameBoard getPlayerGameBoard() {
        return playerGameBoard;
    }

    public GameBoard getOpponentGameBoard() {
        return opponentGameBoard;
    }

    public Card getLastOpponentMonsterCard() {
        return lastOpponentMonsterCard;
    }

    public void setLastOpponentMonsterCard(Card lastOpponentMonsterCard) {
        this.lastOpponentMonsterCard = lastOpponentMonsterCard;
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

    public Card getSelectedCard() {
        return selectedCard;
    }

    public void setSelectedCard(Card selectedCard) {
        this.selectedCard = selectedCard;
    }

    public String getPhase() {
        return phase;
    }

    public Card getAddedCardInDrawPhase() {
        return addedCardInDrawPhase;
    }

    public GameBoard getGameBoard() {
        if (playerOfThisTurn.equals(player)) return playerGameBoard;
        else return opponentGameBoard;

    }

    public String getEnemyCardName() {
        return this.lastOpponentMonsterCard.getCardName();
    }

    public User getWinner() {
        if (surrendered != null) {
            if (surrendered.equals(player)) {
                return opponent;
            } else {
                return player;
            }
        } else {
            if (player.getLifepoint().getLifepoint() == 0) {
                return opponent;
            } else if (opponent.getLifepoint().getLifepoint() == 0) {
                return player;
            }
        }
        return null;
    }

    public void surrenderGame() {
        surrendered = getPlayerOfThisTurn();
    }

    public void cancelCommand() {
        selectedCard = null;
    }

    public void selectCard(String cardType, int cardPosition, boolean isOpponent) {
        if (isOpponent) {
            GameBoard gameBoard = getGameBoardOfOpponentPlayerOfThisTurn();
            if (cardType.equals("--monster") || cardType.equals("-M")) {
                this.selectedCard = gameBoard.getMonsterField().getMonsterCardOpponentFromMonsterField(cardPosition);
            } else if (cardType.equals("--spell") || cardType.equals("-S")) {
                this.selectedCard = gameBoard.getSpellTrapField().getOpponentSpellTrapCard(cardPosition);
            } else if (cardType.equals("--field") || cardType.equals("-F")) {
                this.selectedCard = gameBoard.getFieldZone().getFieldCard();
            } else {
                this.selectedCard = gameBoard.getHand().getCardFromHand(cardPosition);
            }
        } else {
            GameBoard gameBoard = getGameBoardOfPlayerOfThisTurn();
            if (cardType.equals("--monster") || cardType.equals("-M")) {
                this.selectedCard = gameBoard.getMonsterField().getMonsterCardFromPlayerMonsterField(cardPosition);
            } else if (cardType.equals("--spell") || cardType.equals("-S")) {
                this.selectedCard = gameBoard.getSpellTrapField().getPlayerSpellTrapCard(cardPosition);
            } else if (cardType.equals("--field") || cardType.equals("-F")) {
                this.selectedCard = gameBoard.getFieldZone().getFieldCard();
            } else {
                this.selectedCard = gameBoard.getHand().getCardFromHand(cardPosition);
            }
        }
//        if (SpecialMonster.isSelectedCardASpecialMonster(selectedCard)) {
//            SpecialMonster.specialMonsterController(selectedCard, EffectPlace.SELECT, this);
//        }
    }

    public void deselectCard() {
        this.selectedCard = null;
    }

    public void nextPhase() {
        if ((this.phase).equals("draw phase")) {
            this.phase = "standby phase";
        } else if ((this.phase).equals("standby phase")) {
            this.phase = "Main Phase1";
        } else if ((this.phase).equals("Main Phase1")) {
            this.phase = "battle phase";
        } else if ((this.phase).equals("battle phase")) {
            this.phase = "Main Phase2";
        } else if ((this.phase).equals("Main Phase2")) {
            this.phase = "End Phase";
        }
    }

    public void drawPhase() {
        // new -haji
        if (canGetCard()) {
            GameBoard gameBoard = getGameBoardOfPlayerOfThisTurn();
            this.addedCardInDrawPhase = gameBoard.getDeckField().drawCard();
        }
        worksHaveToDoneAfterGetCard();
        SpecialMonster.specialMonsterController(selectedCard, EffectPlace.CHANGETURN, this);
    }

    public boolean canGetCard() {
        return TimeSeal.canGetCard;
    }

    public void worksHaveToDoneAfterGetCard() {
        TimeSeal.canGetCard = true;
    }

    public void standbyPhase() {

    }

    public void mainPhase1() {
        this.numberOfSetsInThisTurn = 0;
        this.numberOfSummonsInThisTurn = 0;
    }

    public void endPhase() {
        Scanner.deleteSwapMonsterIfHadScanner(getGameBoardOfPlayerOfThisTurn().getMonsterField());
        Suijin.setAllSuijinInEachTurn();
        Texchanger.setAllTexchanger();
        if(getOpponentOfThisTurn().getUsername().equals("AI")){
            setPlayerOfNextTurn();
            ArtificialIntelligence.playTurn(this);
        }else{
            setPlayerOfNextTurn();
        }
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
        MonsterCard monster = (MonsterCard) selectedCard;
        boolean notNormalSummon = (monster.getSpecial() == SpecialMonsterEnum.CRAB_TURTLE) ||
                (monster.getSpecial() == SpecialMonsterEnum.SKULL_GUARDIAN);

        if (notNormalSummon) {
            return false;
        } else {
            return true;
        }
    }

    public void  summonMonster() {
        GameBoard gameBoard = getGameBoardOfPlayerOfThisTurn();
        CommandKnight.CommandKnightOnFieldWithSummonMode((MonsterCard) selectedCard,
                getGameBoardOfPlayerOfThisTurn().getMonsterField());
        if (SpecialMonster.isSelectedCardASpecialMonster(selectedCard)) {
            SpecialMonster.specialMonsterController(selectedCard, EffectPlace.SUMMON, this);
        }
        ((MonsterCard) this.selectedCard).summon();
        gameBoard.getMonsterField().addMonsterToField(((MonsterCard) this.selectedCard));
        this.selectedCard = null;
    }

    public void tributeSummon(ArrayList<Integer> cardsToTribute) {
        MonsterField monsterField = getGameBoardOfPlayerOfThisTurn().getMonsterField();
        for (int position : cardsToTribute) {
            MonsterCard monsterCard = monsterField.getMonster(position);
            monsterField.deleteAndDestroyMonster(monsterCard);
        }
        monsterField.addMonsterToField((MonsterCard) selectedCard);
        CommandKnight.CommandKnightOnFieldWithSummonMode((MonsterCard) selectedCard,
                getGameBoardOfPlayerOfThisTurn().getMonsterField());
        selectedCard = null;
    }

    public void specialSummon(MonsterCard monster, GameBoard gameBoard) {
        CommandKnight.CommandKnightOnFieldWithSummonMode(monster,
                getGameBoardOfPlayerOfThisTurn().getMonsterField());
        if (SpecialMonster.isSelectedCardASpecialMonster(monster)) {
            SpecialMonster.specialMonsterController(monster, EffectPlace.SUMMON, this);
        }
        monster.summon();
        gameBoard.getMonsterField().addMonsterToField(monster);
    }

    public void setMonster() {
        GameBoard gameBoard = getGameBoardOfPlayerOfThisTurn();
        MonsterCard monsterCard = (MonsterCard) this.selectedCard;
        monsterCard.set();
        gameBoard.getMonsterField().addMonsterToField(monsterCard);
    }

    public void setSpellOrTrap() {
        GameBoard gameBoard = getGameBoardOfPlayerOfThisTurn();
        SpellTrapCard spellTrapCard = (SpellTrapCard) this.selectedCard;
        spellTrapCard.set();
        gameBoard.getSpellTrapField().addSpellTrapCard(spellTrapCard);
    }

    public void changePosition() {
        MonsterCard monsterCard = (MonsterCard) this.selectedCard;
        monsterCard.changePosition();
        this.changeCardPosition = true;
        if (SpecialMonster.isSelectedCardASpecialMonster(monsterCard)) {
            SpecialMonster.specialMonsterController(monsterCard, EffectPlace.CHANGEPOSITION, this);
        }
    }

    private boolean isTargetCellInAttackPosition(int numberOfEnemyMonsterZone) {
        GameBoard gameBoard = getGameBoardOfOpponentPlayerOfThisTurn();
        if (gameBoard.getMonsterField().getMonsterCardOpponentFromMonsterField(numberOfEnemyMonsterZone).getPosition()
                == PositionMonsters.ATTACK)
            return true;
        else return false;
    }

    private boolean isTargetCellInDefensePosition(int numberOfEnemyMonsterZone) {
        GameBoard gameBoard = getGameBoardOfOpponentPlayerOfThisTurn();
        if (gameBoard.getMonsterField().getMonsterCardOpponentFromMonsterField(numberOfEnemyMonsterZone).getPosition()
                == PositionMonsters.DEFENSE)
            return true;
        else return false;
    }

    public void flipSummon() {

        MonsterCard playerCard = (MonsterCard) this.selectedCard;
        playerCard.setPosition(PositionMonsters.ATTACK);
        this.selectedCard = playerCard;
//        if(canActiveASpellOrTrapInOtherTurn()){
//            gameController.activeSpellAndTrapInOtherTurn();
//        }
    }

    public int attack(int numberOfEnemyMonsterZone) {
        int result = 0;
//        if(canActiveASpellOrTrapInOtherTurn()){
//            gameController.activeSpellAndTrapInOtherTurn();
//        }
        GameBoard opponentGameBoard = getGameBoardOfOpponentPlayerOfThisTurn();
        GameBoard playerGameBoard = getGameBoardOfPlayerOfThisTurn();
        MonsterCard playerCard = (MonsterCard) this.selectedCard;
        playerCard.setWasAttackedInThisTurn(true);
        MonsterCard opponentCard = opponentGameBoard.getMonsterField().getMonsterCardOpponentFromMonsterField
                (numberOfEnemyMonsterZone);
        if(ContiniouesSpellController.Controller("Messenger Of Peace",this,
                ContinouesSpellActivatePlace.Attack)==1){
            return 0;
        }
        if (SpecialMonster.isSelectedCardASpecialMonsterOnDestroyMode(opponentCard)) {
            SpecialMonster.specialMonsterController(opponentCard, EffectPlace.DESTROY, this);
            return 0;
        }
        if (isTargetCellInAttackPosition(numberOfEnemyMonsterZone)) {
            result = attackToOpponentCardInAttackPosition(playerCard, opponentCard, playerGameBoard
                    , opponentGameBoard);
        } else if (isTargetCellInDefensePosition(numberOfEnemyMonsterZone)) {
            result = attackToOpponentCardInDefensePosition(playerCard, opponentCard, playerGameBoard
                    , opponentGameBoard);
        }
        this.selectedCard = null;
        return result;
    }

    public int attackToOpponentCardInAttackPosition(MonsterCard playerCard, MonsterCard opponentCard,
                                                    GameBoard opponentGameBoard, GameBoard playerGameBoard) {
        int result = 0;
        playerCard.attackMonster(opponentCard);
        if (playerCard.getAttack() > opponentCard.getAttack()) {
            result = 6;
            opponentGameBoard.getMonsterField().deleteAndDestroyMonster(opponentCard);
        } else if (playerCard.getAttack() == opponentCard.getAttack()) {
            result = 7;
            opponentGameBoard.getMonsterField().deleteAndDestroyMonster(opponentCard);
            playerGameBoard.getMonsterField().deleteAndDestroyMonster(playerCard);
        } else if (playerCard.getAttack() < opponentCard.getAttack()) {
            result = 8;
            playerGameBoard.getMonsterField().deleteAndDestroyMonster(playerCard);
        }
        return result;
    }

    public int attackToOpponentCardInDefensePosition(MonsterCard playerCard, MonsterCard opponentCard,
                                                     GameBoard playerGameBoard, GameBoard opponentGameBoard) {
        int result = 0;
        playerCard.attackMonster(opponentCard);
        if (opponentCard.getDefenceMode() == DefensePosition.DO) {
            if (opponentCard.getDefense() < playerCard.getAttack()) {
                result = 9;
                opponentGameBoard.getMonsterField().deleteAndDestroyMonster(opponentCard);
            } else if (opponentCard.getDefense() == playerCard.getAttack()) {
                result = 10;
            } else result = 11;
        } else {
            this.lastOpponentMonsterCard = opponentCard;
            if (opponentCard.getDefense() < playerCard.getAttack()) {
                result = 12;
                opponentGameBoard.getMonsterField().deleteAndDestroyMonster(opponentCard);
            } else if (opponentCard.getDefense() == playerCard.getAttack()) {
                result = 13;
            } else result = 14;
        }
        return result;
    }

    public boolean canDirectAttack() {
        GameBoard opponentGameBoard = getGameBoardOfOpponentPlayerOfThisTurn();
        if (opponentGameBoard.getMonsterField().getNumberOfMonstersInField() == 0) return true;
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

    public boolean isSelectedCardHaveToPutInField() {
        /*
        statements
         */
        return true;
    }

    public boolean canActivateSpell() {
        SpellTrapField playerSpellTrapField = getGameBoardOfPlayerOfThisTurn().getSpellTrapField();
        ArrayList<SpellTrapCard> playerSpellTraps = playerSpellTrapField.getSpellTrapsArrayList();

        boolean opponentMirageDragonExist = false;
        for (MonsterCard monster : getOpponentGameBoard().getMonsterField().getMonstersOnField()) {
            if (monster.getSpecial() == SpecialMonsterEnum.MIRAGE_DRAGON) {
                opponentMirageDragonExist = true;
                break;
            }
        }

        for (SpellTrapCard spellTrap : playerSpellTraps) {
            if (spellTrap.isSpell()) {
                return true;
            } else if (!opponentMirageDragonExist) {
                return true;
            }
        }

        return false;
    }

    public void activeSpell() {
        if (canActivateSpell()) {
            SpellTrapCard spellTrap = (SpellTrapCard) selectedCard;
            Chain chain = new Chain(this, spellTrap, getPlayerOfThisTurn(), getOpponentOfThisTurn());
            chain.startChain();
        }
        ContiniouesSpellController.Controller("Spell Absorption",this,
                ContinouesSpellActivatePlace.Activation);
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

    public String showCard() {
        return Card.showCard(selectedCard);
    }

    public GameController getGameController() {
        return gameController;
    }

    public void setSelectedCard1(Card card){
        this.selectedCard=card;
    }


}
