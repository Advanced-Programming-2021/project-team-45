package Server.controller;

import Server.model.ArtificialIntelligence;

import Server.model.user.User;

public class MainMenuController extends Controller {

    public MainMenuController(String username) {
        super(username);
    }

    public int startGameErrorHandler(String opponentUsername, int rounds) {
        User opponent = User.getUserByUsername(opponentUsername);

        if (!User.doesUsernameExist(opponentUsername)) {
            return 1;

        } else if (!user.getUserDeck().doesActiveDeckExist()) {
            return 2;

        } else if (!opponent.getUserDeck().doesActiveDeckExist()) {
            return 3;

        } else if (!user.getUserDeck().isActiveDeckValid()) {
            return 4;

        } else if (!opponent.getUserDeck().isActiveDeckValid()) {
            return 5;

        } else if (!(rounds == 1 || rounds == 3)) {
            return 6;

        }

        return 0;
    }


    public void startAi() {
        new ArtificialIntelligence();
    }

}
