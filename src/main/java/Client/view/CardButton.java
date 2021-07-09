package Client.view;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import Client.view.elements.GetImage;

public class CardButton extends Button {
    private String cardName;
    private ImportExportMenuGui importExportMenuGui;
    public CardButton(String cardName, ImportExportMenuGui importExportMenuGui) {
        super();
        this.cardName = cardName;
        this.importExportMenuGui = importExportMenuGui;
        //this.setStyle("-fx-background-color: red");
        ImageView imageView = new ImageView(GetImage.getCardImage(cardName));
        imageView.setFitWidth(100);
        imageView.setPreserveRatio(true);
        this.setHeight(imageView.getFitHeight());
        this.setWidth(100);
        this.setGraphic(imageView);
        this.setOnMouseClicked(e -> importExportMenuGui.setSelectedCardName(cardName));
    }
}
