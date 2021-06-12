package view.gui.elements;

import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class GameCard extends Rectangle {
    private Pane parent;

    public GameCard(Pane parent, double x, double y) {
        super(x, y, 58, 85);
        this.parent = parent;
        setFill(new ImagePattern(GetGameElements.getCardBack()));
    }

}
