package model.game;

import controller.GameController;
import model.ArtificialIntelligence;
import model.card.*;
import model.card.SpecialMonsters.AmazingAbility.*;
import model.card.SpecialMonsters.EffectPlace;
import model.card.SpecialMonsters.SpecialMonster;
import model.card.SpellTrapCards.effects.Continiuous.ContiniouesSpellController;
import model.card.SpellTrapCards.effects.Continiuous.ContinouesSpellActivatePlace;
import model.game.fields.MonsterField;
import model.game.fields.SpellTrapField;
import model.user.User;

import java.util.ArrayList;

public class Game {

    private boolean changeCardPosition = false;
    private String phase;
    private Card addedCardInDrawPhase;
    private final User player;
    private final User opponent;
    private final int totalRounds;
    private User playerOfThisTurn;
    private final GameBoard playerGameBoard;
    private final GameBoard opponentGameBoard;
    private Card selectedCard;
    private int numberOfSummonsInThisTurn = 0;
    private int numberOfSetsInThisTurn = 0;
    private Card lastOpponentMonsterCard;
    private final GameController gameController;
    private User surrendered;


    public Game(User player, User opponent, int round, GameController gameController) {
        this.player = player;
        this.playerOfThisTurn = player;
        this.opponent = opponent;
        this.totalRounds = round;
        this.gameController = gameController;
        this.playerGameBoard = new GameBoard(player, this);
        this.opponentGameBoard = new GameBoard(opponent, this);
        player.getLifepoint().startNewGame();
        opponent.getLifepoint().startNewGame();
        this.phase = "draw phase";
    }

    private void setPlayerOfNextTurn() {
        getPlayerGameBoard().getMonsterField().deleteAttackedHistory();
        getOpponentGameBoard().getMonsterField().deleteAttackedHistory();
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

    public boolean getChangeCardPosition() {
        return changeCardPosition;
    }

    public int getNumberOfSummonsInThisTurn() {
        return numberOfSummonsInThisTurn;
    }

    public int getNumberOfSetsInThisTurn() {
        return numberOfSetsInThisTurn;
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

    public GameBoard getPlayerGameBoard() {
        if ((this.playerOfThisTurn).equals(this.player))
            return this.playerGameBoard;
        else return this.opponentGameBoard;
    }

    public GameBoard getOpponentGameBoard() {
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
            GameBoard gameBoard = getOpponentGameBoard();
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
            GameBoard gameBoard = getPlayerGameBoard();
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
        } else if ((this.phase).equals("End Phase")) {
            this.phase = "draw phase";
        }
    }

    public void drawPhase() {
        GameBoard gameBoard = getPlayerGameBoard();
        if (getPlayerGameBoard().getHand().getCardsInHand().size() < 6) {
            this.addedCardInDrawPhase = gameBoard.getDeckField().drawCard();
            getPlayerGameBoard().getHand().addCard(addedCardInDrawPhase);
        }
        if (selectedCard != null) {
            SpecialMonster.specialMonsterController(selectedCard, EffectPlace.CHANGETURN, this);
        }
    }


    public void standbyPhase() {

    }

    public void mainPhase1() {

    }

    public void endPhase() {
        Scanner.deleteSwapMonsterIfHadScanner(getPlayerGameBoard().getMonsterField());
        Suijin.setAllSuijinInEachTurn();
        Texchanger.setAllTexchanger();
        if (getOpponentOfThisTurn().getUsername().equals("AI")) {
            setPlayerOfNextTurn();
            ArtificialIntelligence.playTurn(this);
        } else {
            setPlayerOfNextTurn();
        }
        this.numberOfSetsInThisTurn = 0;
        this.numberOfSummonsInThisTurn = 0;
    }

    public void battlePhase() {

    }

    public void mainPhase2() {
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

    public int summonMonster() {
        Chain chain = new Chain(this, selectedCard, getPlayerOfThisTurn(), getOpponentOfThisTurn(), ChainStartState.MONSTER_SUMMON);
        boolean canSummon = chain.startChain();

        if (canSummon) {
            GameBoard gameBoard = getPlayerGameBoard();
            CommandKnight.CommandKnightOnFieldWithSummonMode((MonsterCard) selectedCard,
                    getPlayerGameBoard().getMonsterField());
            if (SpecialMonster.isSelectedCardASpecialMonster(selectedCard)) {
                SpecialMonster.specialMonsterController(selectedCard, EffectPlace.SUMMON, this);
            }

            // add monster to monsterField and remove from hand:
            gameBoard.getMonsterField().addMonsterToField(((MonsterCard) this.selectedCard));
            ((MonsterCard) this.selectedCard).summon();
            numberOfSummonsInThisTurn++;
            gameBoard.getHand().deleteCard(selectedCard);

            this.selectedCard = null;
            return 6;

        } else {
            return -1;

        }
    }

    public void tributeSummon(ArrayList<Integer> cardsToTribute) {
        MonsterField monsterField = getPlayerGameBoard().getMonsterField();
        for (int position : cardsToTribute) {
            MonsterCard monsterCard = monsterField.getMonster(position);
            monsterField.deleteAndDestroyMonster(monsterCard);
        }

        // add monster to field and remove from hand
        monsterField.addMonsterToField((MonsterCard) selectedCard);
        ((MonsterCard) selectedCard).summon();
        numberOfSummonsInThisTurn++;
        getPlayerGameBoard().getHand().deleteCard(selectedCard);

        CommandKnight.CommandKnightOnFieldWithSummonMode((MonsterCard) selectedCard,
                getPlayerGameBoard().getMonsterField());
        selectedCard = null;
    }

    public void specialSummon(MonsterCard monster, GameBoard gameBoard) {
        CommandKnight.CommandKnightOnFieldWithSummonMode(monster,
                getPlayerGameBoard().getMonsterField());
        if (SpecialMonster.isSelectedCardASpecialMonster(monster)) {
            SpecialMonster.specialMonsterController(monster, EffectPlace.SUMMON, this);
        }
        monster.summon();
        numberOfSummonsInThisTurn++;
        // add card to monsterField and remove from hand:
        gameBoard.getMonsterField().addMonsterToField(monster);
        monster.summon();
        gameBoard.getHand().deleteCard(monster);
    }

    public void setMonster() {
        GameBoard gameBoard = getPlayerGameBoard();
        MonsterCard monsterCard = null;
        if (selectedCard instanceof MonsterCard)
            monsterCard = (MonsterCard) this.selectedCard;

        // add card to monsterField and remove from hand:
        gameBoard.getMonsterField().addMonsterToField(monsterCard);
        monsterCard.setForFirstTime();
        numberOfSetsInThisTurn++;
        gameBoard.getHand().deleteCard(monsterCard);
    }

    public void setSpellOrTrap() {
        GameBoard gameBoard = getPlayerGameBoard();
        SpellTrapCard spellTrapCard = null;
        if (selectedCard instanceof SpellTrapCard)
            spellTrapCard = (SpellTrapCard) this.selectedCard;

        // add card to spellField and remove from hand:
        gameBoard.getSpellTrapField().addSpellTrapCard(spellTrapCard);
        spellTrapCard.set();
        gameBoard.getHand().deleteCard(spellTrapCard);
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
        GameBoard gameBoard = getOpponentGameBoard();
        if (gameBoard.getMonsterField().getMonsterCardOpponentFromMonsterField(numberOfEnemyMonsterZone).getPosition()
                == PositionMonsters.ATTACK)
            return true;
        else return false;
    }

    private boolean isTargetCellInDefensePosition(int numberOfEnemyMonsterZone) {
        GameBoard gameBoard = getOpponentGameBoard();
        return gameBoard.getMonsterField().getMonsterCardOpponentFromMonsterField(numberOfEnemyMonsterZone).getPosition()
                == PositionMonsters.DEFENSE;
    }

    public int flipSummon() {
        Chain chain = new Chain(this, selectedCard, getPlayerOfThisTurn(), getOpponentOfThisTurn(), ChainStartState.MONSTER_FLIP_SUMMON);
        boolean canFlipSummon = chain.startChain();

        if (canFlipSummon) {
            MonsterCard playerCard = (MonsterCard) this.selectedCard;
            playerCard.setPosition(PositionMonsters.ATTACK);
            this.selectedCard = playerCard;
            return 5;

        } else {
            return -1;

        }
    }

    public int attack(int numberOfEnemyMonsterZone) {
        int result = 0;

        Chain chain = new Chain(this, selectedCard, getPlayerOfThisTurn(), getOpponentOfThisTurn(), ChainStartState.MONSTER_ATTACK);
        boolean canAttack = chain.startChain();

        if (!canAttack) {
            result = -1;

        } else {
            GameBoard opponentGameBoard = getOpponentGameBoard();
            GameBoard playerGameBoard = getPlayerGameBoard();
            MonsterCard playerCard = (MonsterCard) this.selectedCard;
            playerCard.setWasAttackedInThisTurn(true);
            MonsterCard opponentCard = opponentGameBoard.getMonsterField().getMonsterCardOpponentFromMonsterField
                    (numberOfEnemyMonsterZone);
            if (ContiniouesSpellController.Controller("Messenger Of Peace", this,
                    ContinouesSpellActivatePlace.Attack) == 1) {
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
        }
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
        GameBoard opponentGameBoard = getOpponentGameBoard();
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
        monsterCard.setWasAttackedInThisTurn(true);
        this.selectedCard = null;
    }

    public boolean isSelectedCardHaveToPutInField() {
        /*
        statements
         */
        return true;
    }

    public boolean canActivateSpell() {
        SpellTrapField playerSpellTrapField = getPlayerGameBoard().getSpellTrapField();
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
            Chain chain = new Chain(this, spellTrap, getPlayerOfThisTurn(), getOpponentOfThisTurn(), ChainStartState.SPELL_TRAP);
            chain.startChain();
        }
        ContiniouesSpellController.Controller("Spell Absorption", this,
                ContinouesSpellActivatePlace.Activation);
    }

    public String showGraveyard() {
        GameBoard gameBoard = getPlayerGameBoard();
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

    public boolean isSelectedCardVisibleToPlayer() {
        GameBoard opponentGameBoard = getOpponentGameBoard();
        for (Card card : opponentGameBoard.getHand().getCardsInHand()) {
            if (selectedCard == card) return false;
        }
        for (MonsterCard monster : opponentGameBoard.getMonsterField().getMonstersOnField()) {
            if (selectedCard == monster) {
                return monster.getPosition() != PositionMonsters.DEFENSE ||
                        monster.getDefenceMode() != DefensePosition.DH;
            }
        }
        for (SpellTrapCard spell : opponentGameBoard.getSpellTrapField().getSpellTrapsArrayList()) {
            if (selectedCard == spell) {
                return spell.isActivated();
            }
        }
        return true;
    }

    public String showCard() {
        return Card.showCard(selectedCard);
    }

    public GameController getGameController() {
        return gameController;
    }

    public void setSelectedCard1(Card card) {
        this.selectedCard = card;
    }

    public boolean isMonsterExistInMonsterField(User user, int index) {
        if (user == playerOfThisTurn) {
            return getPlayerGameBoard().getMonsterField().isFull(index - 1);
        } else {
            return getOpponentGameBoard().getMonsterField().isFull(index - 1);
        }
    }
}
