package controller;

import model.user.User;

public abstract class Controller {
    protected final User user;
    protected final String username;

    protected Controller(String username) {
        this.user = User.getUserByUsername(username);
        this.username = username;
    }
}
