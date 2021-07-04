package view.gui;

import javafx.application.Application;

public abstract class MenuGui extends Application {
    protected static String username;

    public static void setUsername(String username) {
        MenuGui.username = username;
    }
}
