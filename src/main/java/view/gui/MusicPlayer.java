package view.gui;
import javafx.scene.media.*;
import javafx.util.Duration;

import java.io.File;

public class MusicPlayer {
    private static Media loginMenuMusic = new Media(new File("src\\main\\resources\\view\\gui\\musics\\Login Menu.mp3").toURI().toString());
    private static Media mainMenuMusic = new Media(new File("src\\main\\resources\\view\\gui\\musics\\Main Menu.mp3").toURI().toString());
    private static Media duelMenuMusic = new Media(new File("src\\main\\resources\\view\\gui\\musics\\Duel Menu.mp3").toURI().toString());
    private static MediaPlayer loginMediaPlayer = new MediaPlayer(loginMenuMusic);
    private static MediaPlayer mainMediaPlayer = new MediaPlayer(mainMenuMusic);
    private static MediaPlayer duelMenuPlayer = new MediaPlayer(duelMenuMusic);

    static {
        duelMenuPlayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                duelMenuPlayer.seek(Duration.ZERO);
            }
        });
        mainMediaPlayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                mainMediaPlayer.seek(Duration.ZERO);
            }
        });
    }
    public static void playLoginMenuMusic() {
        loginMediaPlayer.play();
    }
    public static void playMainMenuMusic() {
        mainMediaPlayer.play();
    }

    public static void playDuelMenuMusic() {
        duelMenuPlayer.play();
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
