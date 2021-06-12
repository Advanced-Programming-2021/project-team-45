package view.gui;

import controller.ShopController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Shop;
import model.card.Card;
import model.user.User;

import java.util.Arrays;
import java.util.HashMap;

public class ShopMenuGui extends Application {
    private static Stage stage;
    private static String logInUsername;
    @FXML
    public GridPane gridPane;
    private ShopCellMenu[][] shopCellMenus;

    public static void setUsername(String username) {
        ShopMenuGui.logInUsername = username;
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        ShopMenuGui.stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("ShopMenuGui.fxml"));
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(root);
        Scene scene = new Scene(scrollPane, 1080, 746);
        stage.setScene(scene);
        stage.setHeight(900);
        stage.setTitle("shop");
    }

    @FXML
    void initialize() {
        shopCellMenus = new ShopCellMenu[10][7];
        HashMap<String, Integer> cards = new ShopController(logInUsername).getCardsPrices();
        for (int index = 0; index < cards.keySet().size(); index++) {
            int j = index % 7;
            int i = index / 7;
            ShopCellMenu.setShopController(new ShopController(logInUsername));
            ShopCellMenu shopCellMenu = new ShopCellMenu((String) cards.keySet().toArray()[index]);
            shopCellMenus[i][j] = shopCellMenu;
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 7; j++) {
                gridPane.add(shopCellMenus[i][j], j, i);
            }
        }
    }

    public void backToMainMenu(MouseEvent mouseEvent) throws Exception {
        MainMenuGui mainMenuGui = new MainMenuGui();
        mainMenuGui.start(stage);
    }
}
