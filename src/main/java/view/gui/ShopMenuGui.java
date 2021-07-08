package view.gui;

import controller.MainMenuController;
import controller.ShopController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;

public class ShopMenuGui extends MenuGui {
    private static Stage stage;
    private static String logInUsername;
    @FXML
    public GridPane gridPane;
    private ArrayList<ShopCellMenu> shopCellMenus;

    public static void setUsername(String username) {
        ShopMenuGui.logInUsername = username;
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        ShopMenuGui.stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("ShopMenuGui.fxml"));
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setStyle("-fx-background-color: black");
        scrollPane.setFitToWidth(true);
        scrollPane.setContent(root);
        Scene scene = new Scene(scrollPane, 1080, 720);
        stage.setScene(scene);
        stage.setTitle("shop");
        createShortCut();
    }

    @FXML
    void initialize() {
        shopCellMenus = new ArrayList<>();
        HashMap<String, Integer> cards = new ShopController(logInUsername).getCardsPrices();
        for (int index = 0; index < cards.keySet().size(); index++) {
            ShopCellMenu.setShopController(new ShopController(logInUsername));
            ShopCellMenu shopCellMenu = new ShopCellMenu((String) cards.keySet().toArray()[index]);
            shopCellMenus.add(shopCellMenu);
        }

        for (int i = 0; i < shopCellMenus.size(); i++) {
            int x = i % 5;
            int y = i / 5;
            gridPane.add(shopCellMenus.get(i), x, y);
        }
    }

    public void backToMainMenu(MouseEvent mouseEvent) throws Exception {
        MainMenuGui mainMenuGui = new MainMenuGui();
        mainMenuGui.start(stage);
    }

    private void createShortCut(){
        MainMenuController.ShortCutsRunnable(stage);
    }
}
