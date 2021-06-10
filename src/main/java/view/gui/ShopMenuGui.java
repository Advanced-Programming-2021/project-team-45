package view.gui;

import controller.ShopController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.card.Card;
import model.user.User;

import java.util.Arrays;

public class ShopMenuGui extends Application {
    private static Stage stage;
    private static ShopController shopController;
    private static User logInUser;
    @FXML
    public GridPane gridPane;
    private ShopCellMenu[][] shopCellMenus;

    public static void setUser(User user) {
        ShopMenuGui.logInUser = user;
    }

    public static void setShopController(ShopController shopController) {
        ShopMenuGui.shopController = shopController;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ShopMenuGui.stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("ShopMenuGui.fxml"));
        Scene scene = new Scene(root, 1080, 720);
        stage.setScene(scene);
        stage.setTitle("shop");
    }

    @FXML
    void initialize() {
        shopCellMenus = new ShopCellMenu[10][7];
        for (int index = 0; index < Card.getAllCards().size(); index++) {
            int j = index % 7;
            int i = index / 7;
            ShopCellMenu shopCellMenu = new ShopCellMenu(Card.getAllCards().get(index));
            ShopCellMenu.setLogInUser(logInUser);
            ShopCellMenu.setShopController(shopController);
            shopCellMenus[i][j] = shopCellMenu;
        }
        System.out.println(Arrays.deepToString(shopCellMenus));
        System.out.println(gridPane.toString());
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
