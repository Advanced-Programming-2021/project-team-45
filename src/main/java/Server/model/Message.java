package Server.model;

public class Message {

    private static int idCounter = 0;
    private int id;
    private String senderUserName;
    private String messageText;

    public Message(String senderUserName, String messageText) {
        this.senderUserName = senderUserName;
        this.messageText = messageText;
        idCounter++;
        this.id = idCounter;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public int getId() {
        return id;
    }

    public String getSenderUserName() {
        return senderUserName;
    }
}
