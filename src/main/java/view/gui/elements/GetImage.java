package view.gui.elements;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class GetImage {
    public static Image getGameIcon() {
        return new Image(String.valueOf(GetImage.class.getResource("img/GameIcon.png")));
    }

    public static Image getImage(String imageName) {
        String path = String.valueOf(GetImage.class.getResource("img"));
        path += "/" + imageName;
        return new Image(path);
    }

    public static Rectangle getCardsImages(String cardName){
        return null;
    }
}
