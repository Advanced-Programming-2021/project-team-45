package view.gui;

import controller.ShopController;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import model.Shop;
import model.card.Card;
import model.user.User;

import java.io.IOException;

public class ShopCellMenu extends AnchorPane {
    private static ShopController shopController;
    private String cardName;
    private ImageView imageView;
    private Button buyButton;
    private Text numberOfBoughtCardsText;

    public ShopCellMenu(String cardName) {
        super();
        this.setWidth(150);
        this.setHeight(63);
        this.setStyle("-fx-background-color: red; -fx-border-color: orange");
        this.cardName = cardName;
        setAnchorPane();
    }

    public static void setShopController(ShopController shopController) {
        ShopCellMenu.shopController = shopController;
    }

    public void setAnchorPane() {
//        imageView = new ImageView();
//        imageView.setFitHeight(72);
//        imageView.setFitWidth(80);
//         set image for card and set x&Y for imageView
        Rectangle rectangle = new Rectangle();
        rectangle.setHeight(122.8);
        rectangle.setWidth(84.1);
        rectangle.setFill(Paint.valueOf("blue"));
        this.getChildren().add(rectangle);
        setButton();
        setCardText();
    }

    public void setButton() {
        buyButton = new Button();
//        if (logInUser.getMoney() < card.getPrice())
//            buyButton.setText("unable to buy");
//        else
//            buyButton.setText("buy");
        buyButton.setText("buy");
        buyButton.setOnMouseClicked(e -> {
            try {
                buyCard();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        buyButton.setLayoutX(89);
        this.getChildren().add(buyButton);
    }

    public void setCardText() {
        numberOfBoughtCardsText = new Text();
        numberOfBoughtCardsText.setText("bought numbers:");


        int number = shopController.numberOfBoughtCards(cardName);
        if (number != 0)
        numberOfBoughtCardsText.setText("bought numbers: " + number);
        numberOfBoughtCardsText.setY(136);
        this.getChildren().add(numberOfBoughtCardsText);
    }

    public void buyCard() throws IOException {
        int result = shopController.buyCardErrorHandler(cardName);
        numberOfBoughtCardsText.setText("1");
        if (result == 2)
            ShowOutput.showOutput("Error", "not enough money");
        else
            ShowOutput.showOutput("Success", cardName +" bought successfully");
    }
}
