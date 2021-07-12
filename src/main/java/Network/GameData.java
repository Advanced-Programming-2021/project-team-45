package Network;

import java.util.ArrayList;
import java.util.HashMap;

public class GameData {
    private HashMap<String, ArrayList<String>> fields;
    private ArrayList<String> playerGraveyard;
    private String gamePhase;
    private ArrayList<String> playerHand;
    private ArrayList<String> opponentHand;
    private String playerUsername;
    private String playerNickname;
    private String opponentUsername;
    private String opponentNickname;
    private int playerLifePoint;
    private int playerDeckSize;
    private int opponentLifePoint;
    private int opponentDeckSize;

    public ArrayList<String> getFieldCards(String fieldName) {
        if (fields.containsKey(fieldName))
            return fields.get(fieldName);
        return null;
    }

    public ArrayList<String> getPlayerGraveyardCards() {
        return playerGraveyard;
    }

    public String getGamePhase() {
        return gamePhase;
    }

    public ArrayList<String> getPlayerHand() {
        return playerHand;
    }

    public ArrayList<String> getOpponentHand() {
        return opponentHand;
    }

    public String getPlayerUsername() {
        return playerUsername;
    }

    public String getPlayerNickname() {
        return playerNickname;
    }

    public String getOpponentUsername() {
        return opponentUsername;
    }

    public String getOpponentNickname() {
        return opponentNickname;
    }

    public int getPlayerLifePoint() {
        return playerLifePoint;
    }

    public int getPlayerDeckSize() {
        return playerDeckSize;
    }

    public int getOpponentLifePoint() {
        return opponentLifePoint;
    }

    public int getOpponentDeckSize() {
        return opponentDeckSize;
    }

    public void setFields(HashMap<String, ArrayList<String>> fields) {
        this.fields = fields;
    }

    public void setPlayerGraveyard(ArrayList<String> playerGraveyard) {
        this.playerGraveyard = playerGraveyard;
    }

    public void setGamePhase(String gamePhase) {
        this.gamePhase = gamePhase;
    }

    public void setPlayerHand(ArrayList<String> playerHand) {
        this.playerHand = playerHand;
    }

    public void setOpponentHand(ArrayList<String> opponentHand) {
        this.opponentHand = opponentHand;
    }

    public void setPlayerUsername(String playerUsername) {
        this.playerUsername = playerUsername;
    }

    public void setPlayerNickname(String playerNickname) {
        this.playerNickname = playerNickname;
    }

    public void setOpponentUsername(String opponentUsername) {
        this.opponentUsername = opponentUsername;
    }

    public void setOpponentNickname(String opponentNickname) {
        this.opponentNickname = opponentNickname;
    }

    public void setPlayerLifePoint(int playerLifePoint) {
        this.playerLifePoint = playerLifePoint;
    }

    public void setPlayerDeckSize(int playerDeckSize) {
        this.playerDeckSize = playerDeckSize;
    }

    public void setOpponentLifePoint(int opponentLifePoint) {
        this.opponentLifePoint = opponentLifePoint;
    }

    public void setOpponentDeckSize(int opponentDeckSize) {
        this.opponentDeckSize = opponentDeckSize;
    }
}
