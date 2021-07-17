package Client.view;

import Server.controller.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuGui extends MenuGui {
    private static Stage stage;
    private static MainMenuController mainMenuController;
    public Button muteButton;
    private boolean isMusicMute = false;

    @Override
    public void start(Stage stage) throws Exception {
        MainMenuGui.stage = stage;
        Parent root = FXMLLoader.load(getClass().getResource("MainMenuGui.fxml"));
        Scene scene = new Scene(root, 1080, 720);
        stage.setScene(scene);
        stage.setTitle("YU-GI-OH!");
        MusicPlayer.playMainMenuMusic();
        mainMenuController = new MainMenuController(username);
    }

    public void startLobbyMenu(MouseEvent mouseEvent) {
        LobbyMenuGui lobbyMenuGui = LobbyMenuGui.getLobbyMenuGui();
        LobbyMenuGui.setUsername(username);
        try {
            lobbyMenuGui.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public void startDuel(MouseEvent mouseEvent) {
//        // get opponent username:
//        String opponentUsername = GetInput.getStringAnswerPopupWindow("Duel",
//                "Please enter second player's username.");
//        // get rounds:
//        Boolean isSingleRound = GetInput.getTwoChoiceAnswer("How many rounds do you want to play?",
//                "1", "3");
//        int rounds = 1;
//        if (!isSingleRound)
//            rounds = 3;
//
//        String message = handleStartGame(opponentUsername, rounds);
//
//        if (message == null) {
//            CoinTossMenu coinTossMenu = new CoinTossMenu();
//            CoinTossMenu.setUserNames(username, opponentUsername);
//            CoinTossMenu.setRounds(rounds);
//            coinTossMenu.tossCoin();
//            try {
//                coinTossMenu.start(stage);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        } else {
//            ShowOutput.showOutput("Error", message);
//        }
//    }

    private String handleStartGame(String opponentUsername, int rounds) {
        String message = "";
        int error = mainMenuController.startGameErrorHandler(opponentUsername, rounds);
        if (error == 0) {
            message = null;
        } else if (error == 1) {
            message = "there is no player with this username";
        } else if (error == 2) {
            message = username + " has no active deck";
        } else if (error == 3) {
            message = opponentUsername + " has no active deck";
        } else if (error == 4) {
            message = username + "'s deck is invalid";
        } else if (error == 5) {
            message = opponentUsername + "'s deck is invalid";
        } else if (error == 6) {
            message = "number of rounds is not supported";
        }
        return message;
    }

    public void startDeckMenu(MouseEvent mouseEvent) {
        DeckStarterMenuGui deckMenuGui = new DeckStarterMenuGui();
        DeckStarterMenuGui.setUsername(username);
        try {
            deckMenuGui.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startScoreBoardMenu(MouseEvent mouseEvent) throws Exception {
        ScoreBoardMenuGui scoreBoardMenuGui = new ScoreBoardMenuGui();
        ScoreBoardMenuGui.setUsername(username);
        scoreBoardMenuGui.start(stage);
    }

    public void startProfileMenu(MouseEvent mouseEvent) {
        ProfileMenuGui profileMenuGui = new ProfileMenuGui();
        ProfileMenuGui.setUsername(username);
        try {
            profileMenuGui.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startShopMenu(MouseEvent mouseEvent) throws Exception {
        ShopMenuGui shopMenuGui = ShopMenuGui.getShopMenuGui();
        ShopMenuGui.setUsername(username);
        shopMenuGui.start(stage);
    }

    public void startImportExportMenu(MouseEvent mouseEvent) throws Exception {
        ImportExportMenuGui importExportMenuGui = new ImportExportMenuGui();
        ImportExportMenuGui.setUsername(username);
        importExportMenuGui.start(stage);
    }

    public void logout(MouseEvent mouseEvent) throws IOException {
        MusicPlayer.muteMainMenu();
        MusicPlayer.unMuteLoginMenu();
        LoginMenuGui loginMenuGui = new LoginMenuGui();
        loginMenuGui.start(stage);
    }

    public void startCardCreatorMenu() {
        CardCreatorMenuGui menu = new CardCreatorMenuGui();
        CardCreatorMenuGui.setUsername(username);
        try {
            menu.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mute(MouseEvent mouseEvent) {
        if (isMusicMute) {
            MusicPlayer.unMuteMainMenu();
            muteButton.setText("mute");
            isMusicMute = false;
        } else {
            MusicPlayer.muteMainMenu();
            muteButton.setText("unmute");
            isMusicMute = true;
        }
    }
}
