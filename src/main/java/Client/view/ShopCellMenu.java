package Client.view;

import Client.ClientServer.ClientShopServer;
import Server.controller.ShopController;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import Client.view.elements.GetImage;

import java.io.IOException;

public class ShopCellMenu extends AnchorPane {
    private static ClientShopServer clientShopServer;
    private String cardName;
    private Button buyButton;
    private Text numberOfBoughtCardsText;

    public ShopCellMenu(String cardName) {
        super();
        this.setWidth(216);
        this.setHeight(322);
        this.setStyle("-fx-background-color: red; -fx-border-color: orange");
        this.cardName = cardName;
        setAnchorPane();
    }

    public static void setClientShopServer(ClientShopServer clientShopServer) {
        ShopCellMenu.clientShopServer = clientShopServer;
    }

    public void setAnchorPane() {
        Rectangle rectangle = new Rectangle();
        rectangle.setFill(new ImagePattern(GetImage.getCardImage(cardName)));
        rectangle.setHeight(218);
        rectangle.setWidth(146);
        this.getChildren().add(rectangle);
        setButton();
        setCardText();
    }

    public void setButton() {
        buyButton = new Button();
        buyButton.setTextFill(Color.color(1, 1, 1));
        int price = clientShopServer.getCardsPrices().get(cardName);
        if (clientShopServer.getUserMoney() >= price) {
            buyButton.setText("buy");
            buyButton.setStyle("-fx-background-color: #0000ff");
        }
        else {
            buyButton.setText("_");
            buyButton.setStyle("-fx-background-color: orange");
        }
        buyButton.setOnMouseEntered(e -> {
            if (clientShopServer.getUserMoney() >= price) {
                buyButton.setText("buy");
                buyButton.setStyle("-fx-background-color: #0000ff");
            }
            else {
                buyButton.setText("_");
                buyButton.setStyle("-fx-background-color: orange");
            }
            Tooltip tooltip = new Tooltip("card price: " + price + "\nyour money: " + clientShopServer.getUserMoney());
            buyButton.setTooltip(tooltip);
        });
        buyButton.setOnMouseClicked(e -> {
            try {
                buyCard();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        buyButton.setLayoutX(150);
        buyButton.setLayoutY(10);
        this.getChildren().add(buyButton);
    }

    public void setCardText() {
        numberOfBoughtCardsText = new Text("");
        numberOfBoughtCardsText.setFont(new Font("Arial Bold", 12));
        int number = clientShopServer.numberOfBoughtCards(cardName);
        if (number != 0)
            numberOfBoughtCardsText.setText("bought cards: " + number);
        numberOfBoughtCardsText.setY(230);
        this.getChildren().add(numberOfBoughtCardsText);
    }

    public void buyCard() throws IOException {
        if (clientShopServer.getCardsPrices().get(cardName) <= clientShopServer.getUserMoney()) {
            int result = clientShopServer.buyCardErrorHandler(cardName);
            if (result == 2)
                ShowOutput.showOutput("Error", "not enough money");
            else {
                ShowOutput.showOutput("Success", "Card added successfully");
                numberOfBoughtCardsText.setText("bought cards: " + clientShopServer.numberOfBoughtCards(cardName));
            }
        }
    }
}
