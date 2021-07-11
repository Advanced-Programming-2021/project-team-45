package Server.ServerController;
;
import Server.controller.LoginController;

import java.net.Socket;

public class LoginRequestHandler extends RequestHandler {
    private final LoginController loginController;

    public LoginRequestHandler(Socket socket) {
        super(socket);
        loginController = new LoginController();
    }

    @Override
    protected String handle(String request) {
        String[] parts = request.split("\n");
        String methodName = parts[2];
        Object[] fields = fieldParser.getObjects(request);
        Object answer = "";

        if (methodName.equals("createUserErrorHandler")) {
            answer = loginController.createUserErrorHandler((String) fields[0], (String) fields[1], (String) fields[2]);
        } else if (methodName.equals("loginUserErrorHandler")) {
            answer = loginController.loginUserErrorHandler((String) fields[0], (String) fields[1]);
        }

        return fieldParser.getAnswer(answer);
    }
}
