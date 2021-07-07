package view.gui;

import controller.DatabaseController;
import controller.LoginController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.user.User;
import view.gui.elements.GetImage;

import java.io.IOException;

public class LoginMenuGui extends MenuGui {

    private static LoginController loginController;
    private static Stage stage;

    public TextField usernameSignUpTextBox;
    public TextField nicknameSignUpTextBox;
    public TextField passwordSignUpTextBox;
    public TextField usernameLoginTextBox;
    public TextField passwordLoginTextBox;
    public Button signUpButton;
    public Button loginButton;
    public Button exitButton;
    public Rectangle yugiohRect;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        LoginMenuGui.stage = stage;
        Parent root = FXMLLoader.load(getClass().getResource("LoginMenuGui.fxml"));
        Scene scene = new Scene(root, 1080, 720);
        stage.setScene(scene);
        stage.setTitle("YU-GI-OH!");
        stage.getIcons().add(GetImage.getGameIcon());
        stage.show();
    }

    @FXML
    private void initialize() {
        MusicPlayer.playLoginMenuMusic();
        Image image = GetImage.getImage("YuGiOh.png");
        yugiohRect.setWidth(256);
        yugiohRect.setHeight(128);
        yugiohRect.setFill(new ImagePattern(image));
    }


    private LoginController getLoginController() {
        if (loginController == null) loginController = new LoginController();
        return loginController;
    }

    private boolean isInputFormatCorrect(String username, String nickname, String password) {
        if (username.matches("\\W") || username.equals("")) {
            ShowOutput.showOutput("Error", "The entered username's format is incorrect!");
            return false;
        } else if (nickname.matches("\\W") || nickname.equals("")) {
            ShowOutput.showOutput("Error", "The entered nickname's format is incorrect!");
            return false;
        } else if (password.matches("\\W") || password.equals("")) {
            ShowOutput.showOutput("Error", "The entered password's format is incorrect!");
            return false;
        }
        return true;
    }

    private void signUp(String username, String nickname, String password) {
        if (isInputFormatCorrect(username, nickname, password)) {
            int error = getLoginController().createUserErrorHandler(username, nickname, password);
            if (error == 0) {
                ShowOutput.showOutput("Success", "user created successfully!");
            } else if (error == 1) {
                ShowOutput.showOutput("Error", "user with username " + username + " exists");
            } else if (error == 2) {
                ShowOutput.showOutput("Error", "user with nickname " + nickname + " exists");
            }
        }
    }

    private void login(String username, String password) throws Exception {
        int error = getLoginController().loginUserErrorHandler(username, password);
        if (error == 0) {
            ShowOutput.showOutput("Success", "user logged in successfully!");
            MusicPlayer.muteLoginMenu();
            MusicPlayer.unMuteMainMenu();
            MainMenuGui mainMenuGui = new MainMenuGui();
            MainMenuGui.setUsername(username);
            mainMenuGui.start(stage);

        } else if (error == 1) {
            ShowOutput.showOutput("Error", "Username and password didn't match!");
        }
    }

    private void clearTextBoxes() {
        usernameSignUpTextBox.setText("");
        nicknameSignUpTextBox.setText("");
        passwordSignUpTextBox.setText("");
        usernameLoginTextBox.setText("");
        passwordLoginTextBox.setText("");
    }

    public void signUp(MouseEvent mouseEvent) {
        String username = usernameSignUpTextBox.getText();
        String nickname = nicknameSignUpTextBox.getText();
        String password = passwordSignUpTextBox.getText();
        signUp(username, nickname, password);
        clearTextBoxes();
    }

    public void login(MouseEvent mouseEvent) throws Exception {
        String username = usernameLoginTextBox.getText();
        String password = passwordLoginTextBox.getText();
        login(username, password);
    }

    public void exit(MouseEvent mouseEvent) {
        DatabaseController.exportUsers();
        System.exit(1);
    }
}
