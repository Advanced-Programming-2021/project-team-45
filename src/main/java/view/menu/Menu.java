package view.menu;

import controller.Controller;
import model.user.User;

import java.util.HashMap;
import java.util.Scanner;

public abstract class Menu {

    protected static final Scanner scanner;

    private String name;
    private Menu parentMenu;
    private Controller menuController;
    private User user;
    private HashMap<MenuNames, Menu> subMenus;

    static {
        scanner = new Scanner(System.in);
    }


    public Menu(String name) {
        this.name = name;
    }


    public String getName() {
        return this.name;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }

    public void setSubMenus(HashMap<MenuNames, Menu> subMenus) {
        this.subMenus = subMenus;
    }

    public void showCurrentMenu() {
        System.out.println(this.getName());
    }

    public void exitMenu() {
        parentMenu.show();
        parentMenu.execute();
    }

    public abstract void show();

    public abstract void execute();

}
