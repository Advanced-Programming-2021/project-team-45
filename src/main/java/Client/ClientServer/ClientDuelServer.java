package Client.ClientServer;

import Network.GameData;
import Network.PortConfig;

public class ClientDuelServer extends ClientServer {
    private GameData gameData;

    public ClientDuelServer() {
        super(PortConfig.DUEL_PORT.getPort(), "GameController");
    }

    public GameData getGameData() {
        return gameData;
    }

    // Methods to be called from server:
    public void updateGameData() {
        gameData = (GameData) sendRequest.getMethodResult("getGameData");
    }

    public void selectCardErrorHandler(String cardType, int cardPosition, boolean isOpponent) {
        sendRequest.getMethodResult("selectCardErrorHandler", cardType, cardPosition, isOpponent);
    }

    public String controlCardShow() {
        return (String) sendRequest.getMethodResult("controlCardShow");
    }

    public int nextPhaseInController() {
        return (int) sendRequest.getMethodResult("nextPhaseInController");
    }

    public String getAddedCardNameInDrawPhase() {
        return (String) sendRequest.getMethodResult("getAddedCardNameInDrawPhase");
    }

    public String getThisTurnPlayerNickname() {
        return (String) sendRequest.getMethodResult("getThisTurnPlayerNickname");
    }

    public int summonErrorHandler() {
        return (int) sendRequest.getMethodResult("summonErrorHandler");
    }

    public int setCardErrorHandler() {
        return (int) sendRequest.getMethodResult("setCardErrorHandler");
    }

    public int changePositionErrorHandler() {
        return (int) sendRequest.getMethodResult("changePositionErrorHandler");
    }

    public int attackErrorHandler(int monsterPosition) {
        return (int) sendRequest.getMethodResult("attackErrorHandler", monsterPosition);
    }

    public String damageOnOpponent() {
        return (String) sendRequest.getMethodResult("damageOnOpponent");
    }

    public String damageOnPlayer() {
        return (String) sendRequest.getMethodResult("damageOnPlayer");
    }

    public String getDefenseTargetCardName() {
        return (String) sendRequest.getMethodResult("getDefenseTargetCardName");
    }

    public int directAttackErrorHandler() {
        return (int) sendRequest.getMethodResult("directAttackErrorHandler");
    }

    public int activeEffectErrorHandler() {
        return (int) sendRequest.getMethodResult("activeEffectErrorHandler");
    }

    public void surrender() {
        sendRequest.getMethodResult("surrender");
    }

    public void cancel() {
        sendRequest.getMethodResult("cancel");
    }

    public boolean isThereAnyMonsterOnOpponentMonsterField() {
        return (boolean) sendRequest.getMethodResult("isThereAnyMonsterOnOpponentMonsterField");
    }

    public void increaseLpCheat(int lp) {
        sendRequest.getMethodResult("increaseLpCheat", lp);
    }

    public void setWinnerCheat(String winnerNickname) {
        sendRequest.getMethodResult("setWinnerCheat", winnerNickname);
    }
}
