package Server.ServerController;

import Server.controller.DatabaseController;
import Server.model.user.User;
import com.gilecode.yagson.YaGson;

import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class ClientUpdateHandler extends RequestHandler {
    private static final HashMap<User, ArrayList<String>> clientUpdates;

    static {
        clientUpdates = new HashMap<>();
    }

    public ClientUpdateHandler(Socket socket) {
        super(socket);
    }

    public static synchronized void addClientUpdate(User user, String update) {
        if (clientUpdates.containsKey(user)) {
            ArrayList<String> userUpdates = clientUpdates.get(user);
            userUpdates.add(update);
        } else {
            ArrayList<String> userUpdates = new ArrayList<>();
            userUpdates.add(update);
            clientUpdates.put(user, userUpdates);
        }
    }

    public static synchronized String getUserUpdate(User user) {
        if (clientUpdates.containsKey(user)) {
            ArrayList<String> userUpdates = clientUpdates.get(user);
            if (userUpdates.size() == 0)
                return null;
            String update = userUpdates.get(0);
            userUpdates.remove(0);
            return update;
        }
        return null;
    }

    public static String getUpdateStringFormat(String methodName, Object... fields) {
        YaGson yaGson = new YaGson();
        String json = yaGson.toJson(fields);
        return methodName + "\n"
                + json;
    }

    @Override
    protected String handle(String request) {
        String answer = "null";
        String[] parts = request.split("\n");
        User user = DatabaseController.getUserByToken(parts[0]);
        if (parts[1].equals("UPDATE")) {
            String update = getUserUpdate(user);
            if (update != null)
                answer = fieldParser.getAnswer(update);
        }
        return answer;
    }
}
