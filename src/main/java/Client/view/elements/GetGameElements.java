package Client.view.elements;

import javafx.scene.image.Image;

public class GetGameElements {
    public static Image getCardFieldImage() {
        return new Image(String.valueOf(GetGameElements.class.getResource("game/field/field.bmp")));
    }

    public static Image getCardBack() {
        return new Image(String.valueOf(GetGameElements.class.getResource("cards/CardBack.jpg")));
    }
}
