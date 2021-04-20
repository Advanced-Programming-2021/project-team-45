package view.menu;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public abstract class Menu {

    protected static final Scanner scanner;

    private final String name;
    private final Menu parentMenu;
    protected String username;
    protected HashMap<MenuName, Menu> subMenus;

    static {
        scanner = new Scanner(System.in);
    }


    protected Menu(String name, Menu parentMenu) {
        this.name = name;
        this.parentMenu = parentMenu;
    }


    protected String getName() {
        return this.name;
    }

    protected void setUsername(String username) {
        this.username = username;
    }

    protected String getUsername() {
        return this.username;
    }

    protected void showCurrentMenu() {
        System.out.println(this.getName());
    }

    protected void exitMenu() throws IOException {
        parentMenu.show();
        parentMenu.execute();
    }

    public abstract void show();

    public abstract void execute() throws IOException;

}
