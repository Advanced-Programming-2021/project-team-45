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

public class ShopMenuGui extends Application {
    private static Stage stage;
    private ShopController shopController;
    private User logInUser;
    @FXML
    public GridPane gridPane;
    private ShopCellMenu[][] shopCellMenus;

    public void setUser(User user) {
        this.logInUser = user;
        shopController = new ShopController(user.getUsername());
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
        shopCellMenus = new ShopCellMenu[10][8];
        for (int index = 0; index < Card.getAllCards().size(); index++) {
            int j = index % 8;
            int i = index % 10;
            ShopCellMenu shopCellMenu = new ShopCellMenu(Card.getAllCards().get(index), logInUser, shopController);
            shopCellMenus[i][j] = shopCellMenu;
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 8; j++) {
                gridPane.getChildren().add(shopCellMenus[i][j]);
            }
        }
    }

    public void backToMainMenu(MouseEvent mouseEvent) throws Exception {
        MainMenuGui mainMenuGui = new MainMenuGui();
        mainMenuGui.start(stage);
    }
}
