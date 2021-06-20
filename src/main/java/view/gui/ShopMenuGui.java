//package view.gui;
//
//import controller.ShopController;
//import javafx.application.Application;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.ScrollPane;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.layout.GridPane;
//import javafx.scene.text.Text;
//import javafx.stage.Stage;
//import model.Shop;
//import model.card.Card;
//import model.user.User;
//
//import java.util.Arrays;
//import java.util.HashMap;
//
//public class ShopMenuGui extends Application {
//    private static Stage stage;
//    private static String logInUsername;
//    @FXML
//    public GridPane gridPane;
//    private ShopCellMenu[][] shopCellMenus;
//
//    public static void setUsername(String username) {
//        ShopMenuGui.logInUsername = username;
//    }
//
//
//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        ShopMenuGui.stage = primaryStage;
//        Parent root = FXMLLoader.load(getClass().getResource("ShopMenuGui.fxml"));
//        ScrollPane scrollPane = new ScrollPane();
//        scrollPane.setStyle("-fx-background-color: black");
//        scrollPane.setFitToWidth(true);
//        scrollPane.setContent(root);
//        Scene scene = new Scene(scrollPane, 1080, 720);
//        stage.setScene(scene);
//        stage.setTitle("shop");
//    }
//
//    @FXML
//    void initialize() {
//        shopCellMenus = new ShopCellMenu[17][4];
//        HashMap<String, Integer> cards = new ShopController(logInUsername).getCardsPrices();
//        for (int index = 0; index < cards.keySet().size() - 2; index++) {
//            int j = index % 4;
//            int i = index / 4;
//            ShopCellMenu.setShopController(new ShopController(logInUsername));
//            ShopCellMenu shopCellMenu = new ShopCellMenu((String) cards.keySet().toArray()[index]);
//            shopCellMenus[i][j] = shopCellMenu;
//        }
//
//        for (int i = 0; i < 17; i++) {
//            for (int j = 0; j < 4; j++) {
//                gridPane.add(shopCellMenus[i][j], j, i);
//            }
//        }
//        ShopCellMenu shopCellMenu = new ShopCellMenu((String) cards.keySet().toArray()[68]);
//        ShopCellMenu shopCellMenu1 = new ShopCellMenu((String) cards.keySet().toArray()[69]);
//        gridPane.add(shopCellMenu, 0, 17);
//        gridPane.add(shopCellMenu1, 1, 17);
//    }
//
//    public void backToMainMenu(MouseEvent mouseEvent) throws Exception {
//        MainMenuGui mainMenuGui = new MainMenuGui();
//        mainMenuGui.start(stage);
//    }
//}
