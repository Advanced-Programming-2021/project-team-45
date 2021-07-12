package Client.view;

import javafx.application.Application;

public abstract class MenuGui extends Application {
    protected static String username;

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        MenuGui.username = username;
    }
}