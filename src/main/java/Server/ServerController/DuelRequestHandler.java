package Server.ServerController;

import Server.controller.DatabaseController;
import Server.controller.GameController;
import Server.controller.MatchMakingController;
import Server.model.user.User;

import java.net.Socket;

public class DuelRequestHandler extends RequestHandler {
    public DuelRequestHandler(Socket socket) {
        super(socket);
    }

    @Override
    protected String handle(String request) {
        String[] parts = request.split("\n");
        String token = parts[0];
        String methodName = parts[2];
        Object[] fields = fieldParser.getObjects(request);
        Object answer = "";

        User user = DatabaseController.getUserByToken(token);
        GameController gameController = MatchMakingController.getGameControllerByUser(user);

        if (methodName.equals("getGameData")) {
            answer = gameController.getGameData(user);
        } else if (methodName.equals("selectCardErrorHandler")) {
            gameController.selectCardErrorHandler((String) fields[0], (int) fields[1], (boolean) fields[2]);
        } else if (methodName.equals("controlCardShow")) {
            answer = gameController.controlCardShow();
        } else if (methodName.equals("nextPhaseInController")) {
            answer = gameController.nextPhaseInController();
        } else if (methodName.equals("getAddedCardNameInDrawPhase")) {
            answer = gameController.getAddedCardNameInDrawPhase();
        } else if (methodName.equals("getThisTurnPlayerNickname")) {
            answer = gameController.getThisTurnPlayerNickname();
        } else if (methodName.equals("summonErrorHandler")) {
            answer = gameController.summonErrorHandler();
        } else if (methodName.equals("setCardErrorHandler")) {
            answer = gameController.setCardErrorHandler();
        } else if (methodName.equals("changePositionErrorHandler")) {
            answer = gameController.changePositionErrorHandler();
        } else if (methodName.equals("attackErrorHandler")) {
            answer = gameController.attackErrorHandler((int) fields[0]);
        } else if (methodName.equals("damageOnOpponent")) {
            answer = gameController.damageOnOpponent();
        } else if (methodName.equals("damageOnPlayer")) {
            answer = gameController.damageOnPlayer();
        } else if (methodName.equals("getDefenseTargetCardName")) {
            answer = gameController.getDefenseTargetCardName();
        } else if (methodName.equals("directAttackErrorHandler")) {
            answer = gameController.directAttackErrorHandler();
        } else if (methodName.equals("activeEffectErrorHandler")) {
            answer = gameController.activeEffectErrorHandler();
        } else if (methodName.equals("surrender")) {
            gameController.surrender();
        } else if (methodName.equals("cancel")) {
            gameController.cancel();
        } else if (methodName.equals("isThereAnyMonsterOnOpponentMonsterField")) {
            answer = gameController.isThereAnyMonsterOnOpponentMonsterField();
        } else if (methodName.equals("increaseLpCheat")) {
            gameController.increaseLpCheat((int) fields[0]);
        } else if (methodName.equals("setWinnerCheat")) {
            gameController.setWinnerCheat((String) fields[0]);
        }

        return fieldParser.getAnswer(answer);
    }
}
