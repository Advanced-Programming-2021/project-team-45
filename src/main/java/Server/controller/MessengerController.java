package Server.controller;

import Server.model.Message;
import Server.model.Messenger;
import Server.model.user.User;

import java.util.ArrayList;

public class MessengerController {

    public static void addMessage(User user, String messageText) {
        Messenger.addMessage(user.getUsername(), messageText);
    }

    public static int deleteMessageErrorHandler(User user, int id) {
        Message message = Messenger.getMessageById(id);
        if (!user.getUsername().equals(message.getSenderUserName()))
            return 1;
        else {
            Messenger.deleteMessageById(id);
            return 0;
        }
    }

    public static int editMessageErrorHandler(User user, int id, String newMessageText) {
        Message message = Messenger.getMessageById(id);
        if (!user.getUsername().equals(message.getSenderUserName()))
            return 1;
        else {
            Messenger.editMessageById(id, newMessageText);
            return 0;
        }
    }

    public static Message getMessageById(int id) {
        return Messenger.getMessageById(id);
    }

    public static void setIsPinnedMessageById(int id, boolean isPinned) {
        Messenger.setIsPinnedMessageById(id, isPinned);
    }

    public static ArrayList<Message> getAllMessages() {
        return Messenger.getMessages();
    }
}
