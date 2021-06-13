package view.gui.elements;

public enum GameElementSize {
    CARD_WIDTH(51),
    CARD_HEIGHT(75),
    CARD_DISTANCE(20),
    CARD_START_X(105),
    PLAYER_MONSTER_Y(320),
    PLAYER_SPELL_Y(425),
    OPPONENT_MONSTER_Y(205),
    OPPONENT_SPELL_Y(110),
    CARD_SELECTED_X(210),
    CARD_SELECTED_Y(307);

    private final int size;

    GameElementSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
