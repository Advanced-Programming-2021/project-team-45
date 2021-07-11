package NetworkConfiguration;

public enum PortConfig {
    PROFILE_PORT(69),
    LOGIN_PORT(85),
    DUEL_PORT(1234);

    private final int port;

    PortConfig(int port) {
        this.port = port;
    }

    public int getPort() {
        return port;
    }
}
