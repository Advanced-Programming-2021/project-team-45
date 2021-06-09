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

public class ShopCellMenu extends Pane {
    private static User logInUser;
    private static ShopController shopController;
    private Card card;
    private ImageView imageView;
    private Button buyButton;
    private Text numberOfBoughtCardsText;

    public ShopCellMenu(Card card) {
        super();
        this.setWidth(154);
        this.setHeight(72);
        this.card = card;
        setAnchorPane();
    }

    public static void setLogInUser(User user) {
        ShopCellMenu.logInUser = user;
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
        rectangle.setHeight(72);
        rectangle.setWidth(80);
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
        this.getChildren().add(buyButton);
    }

    public void setCardText() {
        numberOfBoughtCardsText = new Text();
        numberOfBoughtCardsText.setText("bought numbers:");


        int number = shopController.numberOfBoughtCards(card.getCardName());
        if (number != 0)
        numberOfBoughtCardsText.setText("bought numbers: " + number);
        this.getChildren().add(numberOfBoughtCardsText);
    }

    public void buyCard() throws IOException {
        int result = shopController.buyCardErrorHandler(card.getCardName());
        numberOfBoughtCardsText.setText("1");
        if (result == 2)
            ShowOutput.showOutput("Error", "not enough money");
        else
            ShowOutput.showOutput("Success", "card bought successfully");
    }
}
