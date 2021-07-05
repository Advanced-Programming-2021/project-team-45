package view.gui.elements;


public enum GameElementSize {
    CARD_WIDTH(51),
    CARD_HEIGHT(75),
    CARD_DISTANCE(26),
    PLAYER_START_X(116),
    OPPONENT_START_X(424),
    PLAYER_MONSTER_Y(325),
    PLAYER_SPELL_Y(425),
    OPPONENT_MONSTER_Y(200),
    OPPONENT_SPELL_Y(100),
    CARD_SELECTED_WIDTH(210),
    CARD_SELECTED_HEIGHT(307),
    HAND_CARD_WIDTH(68),
    HAND_CARD_HEIGHT(100),
    OPPONENT_HAND_CARD_START_Y(-20),
    PLAYER_HAND_CARD_START_Y(535);

    private final int size;

    GameElementSize(int size) {
        this.size = size;
    }

    public static int getYSizeByName(String name) {
        switch (name) {
            case "player_monster":
                return PLAYER_MONSTER_Y.size;
            case "player_spell":
                return PLAYER_SPELL_Y.size;
            case "opponent_monster":
                return OPPONENT_MONSTER_Y.size;
            case "opponent_spell":
                return OPPONENT_SPELL_Y.size;
        }
        return 0;
    }

    public static int getXSizeByName(String name) {
        if (name.startsWith("player"))
            return PLAYER_START_X.size;
        else
            return OPPONENT_START_X.size;
    }

    public int getSize() {
        return size;
    }
}
