package Client.ClientServer;

public abstract class ClientServer {
    protected final SendRequest sendRequest;

    protected ClientServer(int port, String className) {
        sendRequest = new SendRequest(port, className);
    }
}
