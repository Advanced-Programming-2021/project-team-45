package Network;

public enum PortConfig {
    PROFILE_PORT(69),
    LOGIN_PORT(85),
    SHOP_PORT(77),
    DUEL_PORT(1234),
    CARD_CREATOR_PORT(662),
    DECK_PORT(2345),
    DeckStarter_PORT(882),
    LOBBY_PORT(3333),
    UPDATE_CLIENT_PORT(111),
    SCOREBOARD_PORT(300);


    private final int port;

    PortConfig(int port) {
        this.port = port;
    }

    public int getPort() {
        return port;
    }
}
