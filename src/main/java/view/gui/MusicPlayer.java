package view.gui;
import javafx.scene.media.*;

import java.io.File;

public class MusicPlayer {
    private static Media loginMenuMusic = new Media(new File("src\\main\\resources\\view\\gui\\musics\\Login Menu.mp3").toURI().toString());
    private static Media mainMenuMusic = new Media(new File("src\\main\\resources\\view\\gui\\musics\\Main Menu.mp3").toURI().toString());
    private static MediaPlayer mediaPlayer;

    public static void PlayLoginMenuMusic() {
        mediaPlayer = new MediaPlayer(loginMenuMusic);
        mediaPlayer.play();
    }
    public static void PlayMainMenuMusic() {
        mediaPlayer = new MediaPlayer(mainMenuMusic);
        mediaPlayer.play();
    }

    public static void pause() {
        mediaPlayer.pause();
        mediaPlayer = null;
    }
}
