package Server.model;

import java.util.ArrayList;

public class Messenger {

    private static ArrayList<Message> messages;

    static {
        messages = new ArrayList<>();
    }

    public static void addMessage(String senderUserName, String messageText) {
        Message message = new Message(senderUserName, messageText);
        messages.add(message);
    }

    public static Message getMessageById(int id) {
        for (Message message : messages) {
            if (message.getId() == id)
                return message;
        }
        return null;
    }


    public static void deleteMessageById(int id) {
        messages.remove(getMessageById(id));
    }

    public static void editMessageById(int id, String newMessageText) {
        Message message = getMessageById(id);
        if (message != null)
            message.setMessageText(newMessageText);
    }

    public static void setIsPinnedMessageById(int id, boolean isPinned)  {
        Messenger.getMessageById(id).setPinned(isPinned);
    }

    public static ArrayList<Message> getMessages() {
        return messages;
    }

}
