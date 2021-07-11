package NetworkConfiguration;

public enum PortConfig {
    PROFILE_PORT(69),
    LOGIN_PORT(85);

    private int port;

    PortConfig(int port) {
        this.port = port;
    }

    public int getPort() {
        return port;
    }
}
