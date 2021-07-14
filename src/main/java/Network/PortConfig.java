package Network;

public enum PortConfig {
    PROFILE_PORT(69),
    LOGIN_PORT(85),
    SHOP_PORT(77),
    DUEL_PORT(1234),
    CARD_CREATOR_PORT(8585),
    MESSENGER_PORT(120);

    private final int port;

    PortConfig(int port) {
        this.port = port;
    }

    public int getPort() {
        return port;
    }
}
