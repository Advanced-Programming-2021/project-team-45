package view.menu;

import controller.GameController;
import controller.Regex;

import java.util.regex.Matcher;

public class DuelMenu extends Menu {

    private final GameController gameController;
    private final String[] DUEL_MENU_REGEX = {
            // i = 0
            "^(menu exit)$|" +
                    "^(menu show-current)$|" +
                    "^(menu enter \\w+)$|" +
                    "^(select (?:--monster|-M|--spell|-S|--field|-F|--hand|-H)(?: (?:--opponent|-O))? \\d+)$|" +
                    "^(select -d)$|" +
                    "^(next phase)$" +
                    "^(summon)$|" +
                    "^(set)$|" +
                    "^(set (?:--position|-p) (?:attack|defense))$|" +
                    "^(flip-summon)$|" +
                    "^(attack \\d)$|" +
                    "^(attack direct)$|" +
                    "^(activate effect)$|" +
                    "^(show graveyard)$|" +
                    "^(card show (?:--selected|-X))$",
            // i = 1
            "select (--monster|-M|--spell|-S|--field|-F|--hand|-H)(?: (--opponent|-O))? (\\d+)",
            // i = 2
            "attack (\\d)"
    };


    public DuelMenu(String username, String opponentUsername, int rounds, Menu parentMenu) {
        super("Duel", parentMenu);
        setUsername(username);

        gameController = new GameController(username, opponentUsername, rounds);
    }


    private void selectCard(Matcher matcher) {
        if (matcher.find()) {
            String cardType = matcher.group(1);
            boolean isOpponentCard = matcher.group(2).matches("--opponent|-O");
            int cardPosition = Integer.parseInt(matcher.group(3));

            int error = gameController.selectCardErrorHandler(cardType, cardPosition, isOpponentCard);
            if (error == 0) {
                System.out.println("card selected");

            } else if (error == 1) {
                System.out.println("invalid selection");

            } else {
                System.out.println("no card found in the given position");

            }
        }
    }

    private void deselectCard() {
        int error = gameController.deselectErrorHandler();
        if (error == 0) {
            System.out.println("card deselected");

        } else {
            System.out.println("no card is selected yet");

        }
    }

    private void nextPhase() {
        int error = gameController.nextPhaseInController();

        if (error == 1) {
            System.out.println("phase: draw phase");

            System.out.println("new card added to the hand : " +
                    gameController.getGame().getAddedCardInDrawPhase().getCardName());

        } else if (error == 2) {
            System.out.println("phase: Main phase 1");

        } else if (error == 6) {
            System.out.println("phase: End Phase");

            System.out.println("its" + gameController.getGame().getOpponentOfThisTurn().getNickname() + "'s turn");

        } else if (error == 0) {
            System.out.println("phase: standby phase");

        } else if (error == 3) {
            System.out.println("phase: battle Phase");

        } else if (error == 4) {
            System.out.println("phase: Main Phase 2");

        }
    }

    private void summonCard() {
        int error = gameController.summonErrorHandler();
        if (error == 1) {
            System.out.println("no card is selected yet");

        } else if (error == 2) {
            System.out.println("you can’t summon this card");

        } else if (error == 3) {
            System.out.println("action not allowed in this phase");

        } else if (error == 4) {
            System.out.println("monster card zone is full");

        } else if (error == 5) {
            System.out.println("you already summoned/set on this turn");

        } else if (error == 6) {
            System.out.println("summoned successfully");

        } else if (error == 7) {
            System.out.println("there are not enough cards for tribute");

        } else if (error == 8) {
            System.out.println("there no monsters one this address");

        } else if (error == 9) {
            System.out.println("there is no monster on one of these addresses");

        }
    }

    private void setCard() {
        int error = gameController.setCardErrorHandler();
        if (error == 1) {
            System.out.println("no card is selected yet");

        } else if (error == 2) {
            System.out.println("you can’t set this card");

        } else if (error == 3) {
            System.out.println("you can’t do this action in this phase");

        } else if (error == 4) {
            System.out.println("monster card zone is full");

        } else if (error == 5) {
            System.out.println("you already summoned/set on this turn");

        } else if (error == 6) {
            System.out.println("set successfully");

        } else if (error == 7) {
            System.out.println("spell card zone is full");

        }
    }

    private void changePosition(String input) {
        int error = gameController.changePositionErrorHandler(Regex.getMatcher(input,"(?:attack|defense)"));
        if (error == 1) {
            System.out.println("no card is selected yet");

        } else if (error == 2) {
            System.out.println("you can’t change this card position");

        } else if (error == 3) {
            System.out.println("you can’t do this action in this phase");

        } else if (error == 4) {
            System.out.println("this card is already in the wanted position");

        } else if (error == 5) {
            System.out.println("you already changed this card position in this turn");

        } else if (error == 6) {
            System.out.println("monster card position changed successfully");

        }
    }

    private void flipSummon() {
        int error = gameController.flipSummonErrorHandler();
        if (error == 1) {
            System.out.println("no card is selected yet");

        } else if (error == 2) {
            System.out.println("you can’t change this card position");

        } else if (error == 3) {
            System.out.println("you can’t do this action in this phase");

        } else if (error == 4) {
            System.out.println("you can’t flip summon this card");

        } else if (error == 5) {
            System.out.println("flip summoned successfully");

        }
    }

    private void attackCard(Matcher matcher) {
        int error = gameController.attackErrorHandler(Integer.parseInt(matcher.group(1)));
        if (error == 1) {
            System.out.println("no card is selected yet");

        } else if (error == 2) {
            System.out.println("you can’t attack with this card");

        } else if (error == 3) {
            System.out.println("you can’t do this action in this phase");

        } else if (error == 4) {
            System.out.println("this card already attacked");

        } else if (error == 5) {
            System.out.println("there is no card to attack here");

        } else if (error == 6) {
            System.out.println("your opponent’s monster is destroyed and your opponent receives\n" +
                    gameController.damageOnOpponent() + "battle damage");

        } else if (error == 7) {
            System.out.println("both you and your opponent monster cards are destroyed and no\n" +
                    "one receives damage");

        } else if (error == 8) {
            System.out.println("Your monster card is destroyed and you received" +
                    gameController.damageOnPlayer() + " battle\n" +
                    "damage");

        } else if (error == 9) {
            System.out.println("the defense position monster is destroyed");

        } else if (error == 10) {
            System.out.println("no card is destroyed");

        } else if (error == 11) {
            System.out.println("no card is destroyed and you received" + gameController.damageOnPlayer()
                    + " battle damage");

        } else if (error == 12) {
            System.out.println("the defense position monster " +
                    gameController.getDefenseTargetCardName() + " is destroyed");

        } else if (error == 13) {
            System.out.println("opponent’s monster card was " + gameController.getDefenseTargetCardName()
                    + " and no card is\n" + "destroyed");

        } else if (error == 14) {
            System.out.println("opponent’s monster card was " + gameController.getDefenseTargetCardName()
                    + "and no card is destroyed and you received" +
                    gameController.damageOnPlayer() + " battle damage");

        }
    }

    private void directAttack() {
        int error = gameController.directAttackErrorHandler();
        if (error == 1) {
            System.out.println("no card is selected yet");

        } else if (error == 2) {
            System.out.println("you can’t attack with this card");

        } else if (error == 3) {
            System.out.println("you can’t do this action in this phase");

        } else if (error == 4) {
            System.out.println("this card already attacked");

        } else if (error == 5) {
            System.out.println("you can’t attack the opponent directly");

        } else if (error == 6) {
            System.out.println("you opponent receives" + gameController.damageOnOpponent()
                    + " battle damage");

        }
    }

    private void activateEffect() {
        int error = gameController.activeEffectErrorHandler();
        if (error == 1) {
            System.out.println("no card is selected yet");

        } else if (error == 2) {
            System.out.println("activate effect is only for spell cards.");

        } else if (error == 3) {
            System.out.println("you can’t activate an effect on this turn");

        } else if (error == 4) {
            System.out.println("you have already activated this card");

        } else if (error == 5) {
            System.out.println("spell card zone is full");

        } else if (error == 6) {
            System.out.println("preparations of this spell are not done yet");

        } else if (error == 7) {
            System.out.println("spell activated");

        }
    }

    private void showGraveyard() {
        String answer = gameController.controlGraveyard();
        System.out.println(answer);
    }

    private void showCard() {
        String answer = gameController.controlCardShow();
        System.out.println(answer);
    }

    private void showGameBoard() {

    }

    @Override
    public void show() {
    }

    @Override
    public void execute() {
        // regex duel new nist
        //gameController.createNewGame();
        while (true) {
            String input = scanner.nextLine();
            Matcher matcher = Regex.getMatcher(input, DUEL_MENU_REGEX[0]);

            if (matcher.find()) {
                if (matcher.group(1) != null) {
                    break;

                } else if (matcher.group(2) != null) {
                    showCurrentMenu();

                } else if (matcher.group(3) != null) {
                    System.out.println("menu navigation is not possible");

                } else if (matcher.group(4) != null) {
                    selectCard(Regex.getMatcher(input, DUEL_MENU_REGEX[1]));

                } else if (matcher.group(5) != null) {
                    deselectCard();

                } else if (matcher.group(6) != null) {
                    nextPhase();

                } else if (matcher.group(7) != null) {
                    summonCard();

                } else if (matcher.group(8) != null) {
                    setCard();

                } else if (matcher.group(9) != null) {
                    changePosition(input);

                } else if (matcher.group(10) != null) {
                    flipSummon();

                } else if (matcher.group(11) != null) {
                    attackCard(Regex.getMatcher(input, DUEL_MENU_REGEX[2]));

                } else if (matcher.group(12) != null) {
                    directAttack();

                } else if (matcher.group(13) != null) {
                    activateEffect();

                } else if (matcher.group(14) != null) {
                    showGraveyard();

                } else if (matcher.group(15) != null) {
                    showCard();

                }

            } else {
                System.out.println("invalid command");

            }

            if (gameController.getGame().getPhase().equals("Main Phase1")) {
                showGameBoard();

            }
        }

        exitMenu();
    }

}
