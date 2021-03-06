package Client.view;
import javafx.scene.media.*;
import javafx.util.Duration;

import java.io.File;

public class MusicPlayer {
    private static Media loginMenuMusic = new Media(new File("src\\main\\resources\\Client\\view\\musics\\Login Menu.mp3").toURI().toString());
    private static Media mainMenuMusic = new Media(new File("src\\main\\resources\\Client\\view\\musics\\Main Menu.mp3").toURI().toString());
    private static Media duelMenuMusic = new Media(new File("src\\main\\resources\\Client\\view\\musics\\Duel Menu.mp3").toURI().toString());
    private static Media winMusic = new Media(new File("src\\main\\resources\\Client\\view\\musics\\Win.mp3").toURI().toString());
    private static Media loseMusic = new Media(new File("src\\main\\resources\\Client\\view\\musics\\Lose.mp3").toURI().toString());
    private static Media setCardMusic = new Media(new File("src\\main\\resources\\Client\\view\\musics\\set card.mp3").toURI().toString());
    private static Media pointDropMusic = new Media(new File("src\\main\\resources\\Client\\view\\musics\\Points drop.mp3").toURI().toString());
    private static MediaPlayer loginMediaPlayer = new MediaPlayer(loginMenuMusic);
    private static MediaPlayer mainMediaPlayer = new MediaPlayer(mainMenuMusic);
    private static MediaPlayer duelMenuPlayer = new MediaPlayer(duelMenuMusic);
    private static boolean isDuelMenuMute = false;

    static {
        loginMediaPlayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                loginMediaPlayer.seek(Duration.ZERO);
            }
        });
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

    public static void playPointDropMusic() {
        MediaPlayer mediaPlayer = new MediaPlayer(pointDropMusic);
        mediaPlayer.play();
    }

    public static void muteDuelMenuMusic() {
        isDuelMenuMute = true;
        duelMenuPlayer.setMute(true);
    }

    public static void unMuteDuelMenuMusic() {
        isDuelMenuMute = false;
        duelMenuPlayer.setMute(false);
    }

    public static void playSetCardMusic() {
        MediaPlayer mediaPlayer = new MediaPlayer(setCardMusic);
        if (!isDuelMenuMute)
            mediaPlayer.play();
    }

    public static void playWinMusic() {
        duelMenuPlayer.setMute(true);
        MediaPlayer mediaPlayer = new MediaPlayer(winMusic);
        mediaPlayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                unMuteMainMenu();
            }
        });
        if (!isDuelMenuMute)
            mediaPlayer.play();
        else
            unMuteMainMenu();
    }

    public static void playLoseMusic() {
        duelMenuPlayer.setMute(true);
        MediaPlayer mediaPlayer = new MediaPlayer(loseMusic);
        mediaPlayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                unMuteMainMenu();
            }
        });
        if (!isDuelMenuMute)
            mediaPlayer.play();
        else
            unMuteMainMenu();
    }

    public static void setIsDuelMenuMute(boolean isDuelMenuMute) {
        MusicPlayer.isDuelMenuMute = isDuelMenuMute;
    }

    public static boolean isDuelMenuMute() {
        return isDuelMenuMute;
    }
}
