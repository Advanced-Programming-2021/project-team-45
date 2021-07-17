package Server.ServerController;

import Server.controller.DatabaseController;
import Server.controller.ProfileController;


import java.net.Socket;

public class ProfileRequestHandler extends RequestHandler {
    private ProfileController profileController;

    public ProfileRequestHandler(Socket socket) {
        super(socket);
    }

    @Override
    protected String handle(String request) {
        String[] parts = request.split("\n");
        String token = parts[0];
        String methodName = parts[2];
        Object[] fields = fieldParser.getObjects(request);
        Object answer ="";
        profileController = new ProfileController(DatabaseController.getUserByToken(token).getUsername());
        switch (methodName) {
            case "setProfilePic":
                profileController.setProfilePic((String) fields[0]);
                break;
            case "changeNicknameErrorHandler":
                answer=profileController.changeNicknameErrorHandler((String) fields[0]);
                break;
            case "changePasswordErrorHandler":
                answer=profileController.changePasswordErrorHandler((String) fields[1], (String) fields[0]);
                break;
            case "getUser":
                answer=profileController.getUser();
                break;
            case "getProfileImage":
                answer=profileController.getProfileImage();
                break;

        }
        return fieldParser.getAnswer(answer);
    }
}
