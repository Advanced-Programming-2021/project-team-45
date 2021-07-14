package Client.ClientServer;

import Network.ServerHost;
import com.gilecode.yagson.YaGson;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SendRequest {
    private final String host;
    private final int port;
    private static String token;
    private final String className;
    private final Socket socket;
    private final DataInputStream dataInputStream;
    private final DataOutputStream dataOutputStream;

    public SendRequest(int port, String className) {
        this.host = ServerHost.HOST.getHostName();
        this.port = port;
        this.className = className;
        Socket socket1;
        DataInputStream dataInputStream1;
        DataOutputStream dataOutputStream1;
        try {
            socket1 = new Socket(host, port);
            dataInputStream1 = new DataInputStream(socket1.getInputStream());
            dataOutputStream1 = new DataOutputStream(socket1.getOutputStream());
        } catch (IOException ioe) {
            socket1 = null;
            dataInputStream1 = null;
            dataOutputStream1 = null;
            ioe.printStackTrace();
        }
        this.socket = socket1;
        this.dataInputStream = dataInputStream1;
        this.dataOutputStream = dataOutputStream1;
    }

    public static void setToken(String token) {
        SendRequest.token = token;
    }

    public Socket getSocket() {
        return socket;
    }

    public DataInputStream getDataInputStream() {
        return dataInputStream;
    }

    public DataOutputStream getDataOutputStream() {
        return dataOutputStream;
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

    public boolean invokeMethod(String methodName, Object[] fields) {
        String request = initRequest(methodName, fields);
        try {
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
        if(answer.equals("null")) return null;
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
