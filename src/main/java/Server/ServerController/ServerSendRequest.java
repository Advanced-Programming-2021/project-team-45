package Server.ServerController;

import com.gilecode.yagson.YaGson;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerSendRequest {
    private final Socket socket;
    private final DataInputStream dataInputStream;
    private final DataOutputStream dataOutputStream;

    public ServerSendRequest(Socket socket, DataInputStream dataInputStream, DataOutputStream dataOutputStream) {
        this.socket = socket;
        this.dataInputStream = dataInputStream;
        this.dataOutputStream = dataOutputStream;
    }

    public Object getMethodResult(String methodName, Object... fields) {
        String request = initRequest(methodName, fields);
        try {
            dataOutputStream.writeUTF(request);
            dataOutputStream.flush();
            // get answer from server:
            String answer = dataInputStream.readUTF();
            return processAnswer(answer);
        } catch (IOException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    private Object getObjectFromJson(Class<?> clazz, String json) {
        YaGson yaGson = new YaGson();
        return yaGson.fromJson(json, clazz);
    }

    private Object processAnswer(String answer) {
        if (answer.equals("success"))
            return null;
        String[] parts = answer.split("\n");
        try {
            Class<?> clazz = Class.forName(parts[0]);
            return getObjectFromJson(clazz, parts[1]);
        } catch (ClassNotFoundException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    private String objectsToJson(Object[] objects) {
        YaGson yaGson = new YaGson();
        return yaGson.toJson(objects);
    }

    private String initRequest(String methodName, Object[] fields) {
        return "SERVER_REQUEST\n"
                + methodName + "\n"
                + objectsToJson(fields);
    }
}
