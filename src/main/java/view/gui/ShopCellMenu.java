package view.gui;

import controller.ShopController;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import model.card.Card;
import model.user.User;

import java.io.IOException;

public class ShopCellMenu extends AnchorPane {
    private User logInUser;
    private ShopController shopController;
    private Card card;
    private ImageView imageView;
    private Button buyButton;
    private Text numberOfBoughtCardsText;

    public ShopCellMenu(Card card, User user, ShopController shopController) {
        super();
        this.setWidth(135);
        this.setHeight(72);
        this.logInUser = user;
        this.shopController = shopController;
        this.card = card;
        setAnchorPane();
    }

    public void setAnchorPane() {
        imageView = new ImageView();
        imageView.setFitHeight(72);
        imageView.setFitWidth(80);
        // set image for card and set x&Y for imageView
        this.getChildren().add(imageView);
        setButton();
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
    }

    public void buyCard() throws IOException {
        int result = shopController.buyCardErrorHandler(card.getCardName());
        if (result == 2)
            ShowOutput.showOutput("Error", "not enough money");
        else
            ShowOutput.showOutput("Success", "card bought successfully");
    }
}
