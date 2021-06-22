package view.gui.elements;


public enum GameElementSize {
    CARD_WIDTH(51),
    CARD_HEIGHT(75),
    CARD_DISTANCE(26),
    CARD_START_X(116),
    PLAYER_MONSTER_Y(325),
    PLAYER_SPELL_Y(425),
    OPPONENT_MONSTER_Y(200),
    OPPONENT_SPELL_Y(100),
    CARD_SELECTED_WIDTH(210),
    CARD_SELECTED_HEIGHT(307);

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
                return OPPONENT_SPELL_Y.getSize();
        }
        return 0;
    }

    public int getSize() {
        return size;
    }
}
