package Server.model.card;

public enum DefensePosition {
    DO,
    DH;

    @Override
    public String toString() {
        if (this == DefensePosition.DO) {
            return "DO";
        } else {
            return "DH";
        }
    }
}
