package Client.ClientServer;

import NetworkConfiguration.ServerHost;
import com.gilecode.yagson.YaGson;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SendRequest {
    private final String host;
    private final int port;
    private static String token;
    private final String className;

    public SendRequest(int port, String className) {
        this.host = ServerHost.HOST.getHostName();
        this.port = port;
        this.className = className;
    }

    public static void setToken(String token) {
        SendRequest.token = token;
    }

    public Object getMethodResult(String methodName, Object... fields) {
        String request = initRequest(methodName, fields);
        try {
            Socket socket = new Socket(host, port);
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataOutputStream.writeUTF(request);
            dataOutputStream.flush();
            // get answer from server:
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            String answer = dataInputStream.readUTF();
            return processAnswer(answer);
        } catch (IOException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public boolean invokeMethod(String methodName, Object[] fields) {
        String request = initRequest(methodName, fields);
        try {
            Socket socket = new Socket(host, port);
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataOutputStream.writeUTF(request);
            dataOutputStream.flush();
            return true;
        } catch (IOException exception) {
            exception.printStackTrace();
            return false;
        }
    }

    private Object getObjectFromJson(Class<?> clazz, String json) {
        YaGson yaGson = new YaGson();
        return yaGson.fromJson(json, clazz);
    }

    private Object processAnswer(String answer) {
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
        return token + "\n"
                + className + "\n"
                + methodName + "\n"
                + objectsToJson(fields);
    }
}
