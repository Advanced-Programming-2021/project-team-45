package view.gui.elements;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GetImage {
    public static Image getGameIcon() {
        return new Image(String.valueOf(GetImage.class.getResource("img/GameIcon.png")));
    }

    public static Image getImage(String imageName) {
        String path = String.valueOf(GetImage.class.getResource("img"));
        path += "/" + imageName;
        return new Image(path);
    }

    public static Image getCardImage(String cardName) {
        return findFile(cardName);
    }

    private static Image findFile(String name) {
        name = name.trim();
        String[] names = name.split(" ");
        StringBuilder finalName = new StringBuilder();
        for (int i = 0; i < names.length; i++) {
            char u = names[i].charAt(0);
            if (!(u >= 'A' && u <= 'Z')) {
                u -= 32;
                String newName = u + names[i].substring(1);
                names[i] = newName;
            }
            finalName.append(names[i]);
        }
        Image file = null;
        try {
            file = new Image(new FileInputStream("src/main/resources/view/gui/elements/cards/" + finalName + ".jpg"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return file;
    }
}
