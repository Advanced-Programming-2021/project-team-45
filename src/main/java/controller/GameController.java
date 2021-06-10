package controller;

import model.card.SpellTrapCard;
import model.game.*;
import model.card.Card;
import model.card.MonsterCard;
import model.card.SpecialMonsterEnum;
import model.card.SpecialMonsters.AmazingAbility.BeastKingBarbaros;
import model.game.fields.CardField;
import model.user.User;
import view.cli.DuelMenu;
import view.cli.MainMenu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;

public class GameController extends Controller {

    private final User player;
    private final User opponentPlayer;
    private final int rounds;
    private Game game;
    private GameErrorHandler gameErrorHandler;
    private int playerWins = 0;
    private int opponentWins = 0;
    private DuelMenu playerDuelMenu;


    public GameController(String username, String opponentUsername, int rounds) {
        super(username);
        this.player = User.getUserByUsername(username);
        this.opponentPlayer = User.getUserByUsername(opponentUsername);
        this.rounds = rounds;
    }


    private void createNewGame() {
        this.game = new Game(player, opponentPlayer, rounds, this);
        this.gameErrorHandler = new GameErrorHandler(game);
    }

    public void startGame() {
        int playerMaxLp = 0;
        int opponentMaxLp = 0;
        User winner = null;
        for (int i = 0; i < rounds; i++) {
            if (playerWins == 2 || opponentWins == 2) break;
            // create new round and start it:
            createNewGame();
            playRound();
            // show winner of round:
            winner = game.getWinner();
            if (winner.equals(player)) {
                playerWins++;
            } else {
                opponentWins++;
            }
            playerDuelMenu.showGameWinner(winner.getUsername(), playerWins, opponentWins);
            // save maxLp
            int playerLp = player.getLifepoint().getLifepoint();
            if (playerLp > playerMaxLp) playerMaxLp = playerLp;
            int opponentLp = opponentPlayer.getLifepoint().getLifepoint();
            if (opponentLp > opponentMaxLp) opponentMaxLp = opponentLp;
        }
        // show match winner if there is 3 rounds:
        if (rounds == 3) playerDuelMenu.showMatchWinner(winner.getUsername(), playerWins, opponentWins);
        // calculate and increase score and money after match:
        increaseMoneyAndScore(playerMaxLp, opponentMaxLp);
        // go back to first player's MainMenu:
        MainMenu playerMainMenu = new MainMenu(player.getUsername());
        playerMainMenu.show();
        playerMainMenu.execute();
    }

    private void increaseMoneyAndScore(int playerMaxLp, int opponentMaxLp) {
        if (rounds == 1) {
            if (playerWins > 0) {
                player.increaseScore(1000);
                player.increaseMoney(1000 + playerMaxLp);
                opponentPlayer.increaseMoney(100);
            } else {
                opponentPlayer.increaseScore(1000);
                opponentPlayer.increaseMoney(1000 + opponentMaxLp);
                player.increaseMoney(100);
            }
        } else {
            if (playerWins > 1) {
                player.increaseScore(3000);
                player.increaseMoney(3000 + playerMaxLp);
                opponentPlayer.increaseMoney(300);
            } else {
                opponentPlayer.increaseScore(3000);
                opponentPlayer.increaseMoney(3000 + opponentMaxLp);
                player.increaseMoney(300);
            }
        }
    }

    public void surrender() {
        game.surrenderGame();
    }

    private void playRound() {
        playerDuelMenu = new DuelMenu(player.getUsername(), this);
        playerDuelMenu.show();

        while (!game.isFinished()) {
            playerDuelMenu.getNextCommand();
        }
    }

    public void cancel() {
        game.cancelCommand();
    }

    ///////////////////////////////////////////// ERROR HANDLING:

    public int selectCardErrorHandler(String cardType, int cardPosition, boolean isOpponentCard) {
        if (gameErrorHandler.isInputForSelectCardValid(cardType, cardPosition, isOpponentCard)) {

            if (gameErrorHandler.isThereAnyCardHere(cardType, cardPosition, isOpponentCard)) {
                game.selectCard(cardType, cardPosition, isOpponentCard);
                return 0;
            } else {
                return 2;
            }

        } else {
            return 1;
        }
    }

    public int deselectErrorHandler() {
        if (gameErrorHandler.doesSelectedCardExist()) {
            game.deselectCard();
            return 0;
        } else {
            return 1;
        }
    }

    public int nextPhaseInController() {
        game.nextPhase();
        String phase = game.getPhase();
        switch (phase) {
            case "draw phase":
                game.drawPhase();
                return 1;

            case "standby phase":
                game.standbyPhase();
                return 0;

            case "Main Phase1":
                game.mainPhase1();
                return 2;

            case "End Phase":
                game.endPhase();
                return 5;

            case "battle phase":
                game.battlePhase();
                return 3;

            case "Main Phase2":
                game.mainPhase2();
                return 4;
        }
        return -1;
    }

    public int summonErrorHandler() {
        if (gameErrorHandler.doesSelectedCardExist()) {
            if (gameErrorHandler.isSelectedCardMonster() && gameErrorHandler.isCardInHand() && game.canSummonThisMonster()) {
                if ((game.getPhase().equals("Main Phase1") || game.getPhase().equals("Main Phase2"))) {
                    if (!gameErrorHandler.isMonsterFieldFull()) {
                        if (!gameErrorHandler.wasSummonOrSetCardBeforeInThisTurn()) {
                            MonsterCard monster = (MonsterCard) game.getSelectedCard();
                            if (monster.getLevel() < 5 || monster.getSpecial() == SpecialMonsterEnum.CRAB_TURTLE
                                    || monster.getSpecial() == SpecialMonsterEnum.SKULL_GUARDIAN) {

                                return game.summonMonster();

                            } else {
                                return tributeSummonErrorHandler(monster);

                            }
                        } else {
                            return 5;
                        }
                    } else {
                        return 4;
                    }
                } else {
                    return 3;
                }
            } else {
                return 2;
            }
        }

        return 1;
    }

    private int tributeSummonErrorHandler(MonsterCard monster) {
        ArrayList<Integer> cardsToTribute;
        if (!gameErrorHandler.isThereEnoughCardsToTribute(monster)) {
            return 7;
        } else {
            if (monster.getSpecial() == SpecialMonsterEnum.BEAST_KING_BARBAROS) {
                BeastKingBarbaros beastKingBarbaros = new BeastKingBarbaros(game, gameErrorHandler, this);
                return beastKingBarbaros.summonHandler(monster);

            } else if (monster.getLevel() > 10) {
                cardsToTribute = playerDuelMenu.getCardsForTribute(3);
                if (cardsToTribute == null) return -1;

                if (gameErrorHandler.isTributeCardsValid(cardsToTribute)) {
                    game.tributeSummon(cardsToTribute);
                    return 6;
                } else {
                    return 9;
                }
            } else if (monster.getLevel() >= 7) {
                cardsToTribute = playerDuelMenu.getCardsForTribute(2);
                if (cardsToTribute == null) return -1;

                if (gameErrorHandler.isTributeCardsValid(cardsToTribute)) {
                    game.tributeSummon(cardsToTribute);
                    return 6;
                } else {
                    return 9;
                }
            } else {
                cardsToTribute = playerDuelMenu.getCardsForTribute(1);
                if (cardsToTribute == null) return -1;

                if (gameErrorHandler.isTributeCardsValid(cardsToTribute)) {
                    game.tributeSummon(cardsToTribute);
                    return 6;
                } else {
                    return 8;
                }
            }
        }
    }

    public int setCardErrorHandler() {
        if (gameErrorHandler.doesSelectedCardExist()) {
            if (gameErrorHandler.isCardInHand()) {
                if (gameErrorHandler.isSelectedCardMonster()) {
                    if ((game.getPhase().equals("Main Phase1") || game.getPhase().equals("Main Phase2"))) {
                        if (!gameErrorHandler.isMonsterFieldFull()) {
                            if (!gameErrorHandler.wasSummonOrSetCardBeforeInThisTurn()) {
                                game.setMonster();
                                return 6;
                            } else {
                                return 5;
                            }
                        } else {
                            return 4;
                        }
                    } else {
                        return 3;
                    }
                } else {
                    if ((game.getPhase().equals("Main Phase1") || game.getPhase().equals("Main Phase2"))) {
                        if (!gameErrorHandler.isSpellTrapFieldFull()) {
                            game.setSpellOrTrap();
                            return 6;
                        } else {
                            return 7;
                        }
                    } else {
                        return 3;
                    }
                }
            } else {
                return 2;
            }
        } else {
            return 1;
        }
    }

    public int changePositionErrorHandler(Matcher matcher) {
        if (gameErrorHandler.doesSelectedCardExist()) {
            if (gameErrorHandler.isThereSelectedCardInMonsterField()) {
                if (game.getPhase().equals("Main Phase1") || game.getPhase().equals("Main Phase2")) {
                    if (gameErrorHandler.isChangeCorrect(matcher)) {
                        if (!gameErrorHandler.wasChangePositionInThisTurn()) {
                            game.changePosition();
                            return 6;
                        } else {
                            return 5;
                        }
                    } else {
                        return 4;
                    }
                } else {
                    return 3;
                }
            } else {
                return 2;
            }
        } else {
            return 1;
        }
    }

    public int flipSummonErrorHandler() {
        if (gameErrorHandler.doesSelectedCardExist()) {
            if (gameErrorHandler.isThereSelectedCardInMonsterField()) {
                if (game.getPhase().equals("Main Phase1") || game.getPhase().equals("Main Phase2")) {
                    if (gameErrorHandler.canFlipSummonSelectedCard()) {
                        return game.flipSummon();
                    } else {
                        return 4;
                    }
                } else {
                    return 3;
                }
            } else {
                return 2;
            }
        } else {
            return 1;
        }
    }

    public int attackErrorHandler(int numberOfEnemyMonsterZone) {
        if (gameErrorHandler.doesSelectedCardExist()) {
            if (gameErrorHandler.isThereSelectedCardInMonsterField()) {
                if (game.getPhase().equals("battle phase")) {
                    if (!gameErrorHandler.wasThisCardAttackedInThisTurn()) {
                        if (gameErrorHandler.isThereAnyMonsterInThisCell(numberOfEnemyMonsterZone)) {
                            int returnedNumber = game.attack(numberOfEnemyMonsterZone);
                            return returnedNumber;
                            /*
                            some order have to done for attack method in model{
                            enemy card in attack position:
                            my attack power > enemy attack power -> return 6
                            my attack power = enemy attack power -> return 7
                            my attack power < enemy attack power -> return 8
                            enemy card in defense position:
                            my attack power > enemy defense power :
                                DH mode -> return 12
                                Not DH mode -> return 9;
                            my attack power = enemy defense power :
                                DH mode -> return 13
                                Not DH mode -> return 10;
                            my attack power < enemy defense power :
                                DH mode -> return 14
                                Not DH mode -> return 11;
                             }
                             */

                        } else {
                            return 5;
                        }
                    } else {
                        return 4;
                    }
                } else {
                    return 3;
                }
            } else {
                return 2;
            }
        } else {
            return 1;
        }
    }

    public int directAttackErrorHandler() {
        if (gameErrorHandler.doesSelectedCardExist()) {
            if (gameErrorHandler.isThereSelectedCardInMonsterField()) {
                if (game.getPhase().equals("battle phase")) {
                    if (!gameErrorHandler.wasThisCardAttackedInThisTurn()) {
                        if (game.canDirectAttack()) {
                            game.directAttack();
                            return 6;
                        }
                        return 5;
                    }
                    return 4;
                }
                return 3;
            }
            return 2;
        }
        return 1;
    }

    public int activeEffectErrorHandler() {
        if (gameErrorHandler.doesSelectedCardExist()) {
            if (gameErrorHandler.isSelectedCardSpell()) {
                if (game.getPhase().equals("Main Phase 1") || game.getPhase().equals("Main Phase 2")) {
                    if (!gameErrorHandler.isSelectedSpellActive()) {
                        if (gameErrorHandler.isSelectedCardInHand() && gameErrorHandler.isSpellTrapFieldFull()
                                && game.isSelectedCardHaveToPutInField()) {
                            return 5;
                        }
                        if (game.canActivateSpell()) {
                            game.activeSpell();
                            return 7;
                        }
                        return 6;
                    }
                    return 4;
                }
                return 3;
            }
            return 2;
        }
        return 1;
    }

    public String showGraveyard() {
        return game.showGraveyard();
    }

    public String controlCardShow() {
        if (game.getSelectedCard() != null) {
            if (!game.isSelectedCardVisibleToPlayer()) {
                return null;
            } else {
                return game.showCard();
            }
        } else {
            return "no card is selected yet";
        }
    }

    public String damageOnOpponent() {
        return game.calculateDamageOnEnemy();
    }

    public String damageOnPlayer() {
        return game.calculateDamageOnMe();
    }

    public String getDefenseTargetCardName() {
        return game.getEnemyCardName();
    }

    public Game getGame() {
        return game;
    }

    public Boolean getYesNoAnswer(String question) {
        return playerDuelMenu.getYesNoAnswer(question);
    }

    public ArrayList<Integer> getCardsForTribute(int n) {
        return playerDuelMenu.getCardsForTribute(n);
    }

    public String NumberOfField(String view) {
        return playerDuelMenu.getInputNumberOfFieldForSpecialMonster(view);
    }

    public MonsterCard getACardFromGraveyardForScanner(String view) {
        String input = playerDuelMenu.getCardFromGraveYard(view);
        if (input != null) {
            ArrayList<Card> cards = game.getPlayerGameBoard().getGraveyard().getGraveyardCards();
            for (int i = 0; i < cards.size(); i++) {
                if (cards.get(i).getCardName().equals(input) && cards.get(i) instanceof MonsterCard) {
                    return (MonsterCard) cards.get(i);
                }
            }
            if (cards.size() == 0) {
                try {
                    return new MonsterCard("Scanner");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                return new MonsterCard("-1");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public ArrayList<Card> getCardFromPlayer(int n, CardField... fields) {
        ArrayList<Card> selectedCards = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            while (true) {
                String cardName = playerDuelMenu.getCardName();
                // for surrender and cancel:

                Card card = null;
                for (CardField field : fields) {
                    if (field.doesCardExist(cardName)) {
                        card = field.getCardByName(cardName);
                    }
                }
                if (card != null) {
                    selectedCards.add(card);
                    break;
                } else {
                    StringBuilder errorMessage = new StringBuilder("the selected card wasn't in: ");
                    for (CardField field : fields) {
                        errorMessage.append(field.getName()).append("\t");
                    }
                    errorMessage.append("(try again!)");
                    playerDuelMenu.showOutput(errorMessage.toString());
                }
            }
        }
        return selectedCards;
    }

    public void showOutput(String text) {
        playerDuelMenu.showOutput(text);
    }

    public int getNumberFromPlayer(String view) {
        return playerDuelMenu.getNumber(view);
    }

    public boolean doesPlayerWantToAddToTheChain(User player) {
        showOutput("now it will be " + player.getUsername() + "’s turn");
        if (player == this.player) {
            playerDuelMenu.updatePlayerGameBoard();
        } else {
            playerDuelMenu.updateOpponentGameBoard();
        }
        return getYesNoAnswer("do you want to add to this chain?");
    }

    public SpellTrapCard getSpellToAddToChain(User player, Chain chain) {
        showOutput("please choose a valid spell to add to chain " + player.getUsername());
        SpellTrapCard spell;
        while (true) {
            spell = (SpellTrapCard) (getCardFromPlayer(1, chain.getPlayerGameBoard().getSpellTrapField())).get(0);
            if (chain.canAddToChain(spell)) {
                return spell;
            }
        }
    }

    public boolean isCardExistsInMonsterField(String username, int index) {
        return game.isMonsterExistInMonsterField(User.getUserByUsername(username), index);
    }
}