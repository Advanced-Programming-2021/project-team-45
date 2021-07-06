package view.gui;
import javafx.scene.media.*;

import java.io.File;

public class MusicPlayer {
    private static Media loginMenuMusic = new Media(new File("src\\main\\resources\\view\\gui\\musics\\Login Menu.mp3").toURI().toString());
    private static Media mainMenuMusic = new Media(new File("src\\main\\resources\\view\\gui\\musics\\Main Menu.mp3").toURI().toString());
    private static MediaPlayer loginMediaPlayer = new MediaPlayer(loginMenuMusic);
    private static MediaPlayer mainMediaPlayer = new MediaPlayer(mainMenuMusic);

    public static void PlayLoginMenuMusic() {
        loginMediaPlayer.play();
    }
    public static void PlayMainMenuMusic() {
        mainMediaPlayer.play();
    }

    public static void muteMainMenu() {
        mainMediaPlayer.setMute(true);
    }

    public static void unMuteMainMenu() {
        mainMediaPlayer.setMute(false);
    }

    public static void muteLoginMenu() {
        loginMediaPlayer.setMute(true);
    }

    public static void unMuteLoginMenu() {
        loginMediaPlayer.setMute(false);
    }
}
