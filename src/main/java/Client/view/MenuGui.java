package Client.view;

import javafx.application.Application;

public abstract class MenuGui extends Application {
    protected static String username;
    protected static boolean isScoreboard;

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        MenuGui.username = username;
    }

    public static void setIsScoreboard(boolean isScoreboard) {
        MenuGui.isScoreboard = isScoreboard;
    }

    public static boolean isScoreboard() {
        return isScoreboard;
    }
}
