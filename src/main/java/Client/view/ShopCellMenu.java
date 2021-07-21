package Client.view;

import Client.ClientServer.ClientShopServer;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import Client.view.elements.GetImage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ShopCellMenu extends AnchorPane {
    private static ClientShopServer clientShopServer;
    private ShopMenuGui shopMenuGui;
    private String cardName;
    private int shopInventory;
    private boolean isBanned;
    private Button buyButton;
    private Text cardText;

    public ShopCellMenu(String cardName, int shopInventory, boolean isBanned) {
        super();
        this.setWidth(216);
        this.setHeight(322);
        this.setStyle("-fx-background-color: red; -fx-border-color: orange");
        this.cardName = cardName;
        this.shopInventory = shopInventory;
        this.isBanned = isBanned;
        setAnchorPane();
    }

    public static void setClientShopServer(ClientShopServer clientShopServer) {
        ShopCellMenu.clientShopServer = clientShopServer;
    }

    public void setShopMenuGui(ShopMenuGui shopMenuGui) {
        this.shopMenuGui = shopMenuGui;
    }

    public void setAnchorPane() {
        Rectangle rectangle = new Rectangle();
        rectangle.setFill(new ImagePattern(GetImage.getCardImage(cardName)));
        ImageView imageView = new ImageView();
        rectangle.setHeight(218);
        rectangle.setWidth(146);
        this.getChildren().add(rectangle);
        setButton();
        setCardText();
    }

    public void setButton() {
        buyButton = new Button();
        buyButton.setPrefSize(36, 25);
        buyButton.setTextFill(Color.color(1, 1, 1));
        int price = clientShopServer.getCardsPrices().get(cardName);
        if (isBanned) {
            setImageButton(buyButton);
        } else {
            if (clientShopServer.getUserMoney() >= price) {
                buyButton.setText("buy");
                buyButton.setStyle("-fx-background-color: #0000ff");
            } else {
                buyButton.setText("_");
                buyButton.setStyle("-fx-background-color: orange");
            }
        }
        buyButton.setOnMouseEntered(e -> {
            if (!isBanned) {
                if (clientShopServer.getUserMoney() >= price) {
                    buyButton.setText("buy");
                    buyButton.setStyle("-fx-background-color: #0000ff");
                } else {
                    buyButton.setText("_");
                    buyButton.setStyle("-fx-background-color: orange");
                }
            }
            Tooltip tooltip = new Tooltip("card price: " + price + "\nyour money: " + clientShopServer.getUserMoney());
            buyButton.setTooltip(tooltip);
        });
        buyButton.setOnMouseClicked(e -> {
            buyCard();
        });
        buyButton.setLayoutX(150);
        buyButton.setLayoutY(10);
        this.getChildren().add(buyButton);
    }

    private void setImageButton(Button buyButton) {
        try {
            ImageView imageView = new ImageView(new Image(
                    new FileInputStream("src\\main\\resources\\Client\\view\\chatButtonImages\\forbidden.png")));
            imageView.setFitWidth(19);
            imageView.setFitHeight(17);
            buyButton.setGraphic(imageView);
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
    }

    public void setCardText() {
        cardText = new Text("");
        cardText.setFont(new Font("Arial Bold", 12));
        int number = clientShopServer.numberOfBoughtCards(cardName);
        if (number != 0)
            cardText.setText("bought cards: " + number + "\nNo. of cards in shop: " + shopInventory);
        else
            cardText.setText("No. of cards in shop: " + shopInventory);
        cardText.setY(230);
        this.getChildren().add(cardText);
    }

    public void buyCard() {
        if (!isBanned) {
            if (clientShopServer.getCardsPrices().get(cardName) <= clientShopServer.getUserMoney()) {
                int result = clientShopServer.buyCardErrorHandler(cardName);
                if (result == 2)
                    ShowOutput.showOutput("Error", "not enough money");
                else if (result == 3)
                    ShowOutput.showOutput("Error", "the number of " + cardName + " in shop is 0");
                else {
                    ShowOutput.showOutput("Success", "Card added successfully");
                    shopMenuGui.update();
                }
            }
        }
    }
}
