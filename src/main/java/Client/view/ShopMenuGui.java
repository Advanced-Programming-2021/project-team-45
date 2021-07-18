package Client.view;

import Client.ClientServer.ClientShopServer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShopMenuGui extends MenuGui {
    private static Stage stage;
    private static String logInUsername;
    private static ShopMenuGui shopMenuGui;
    private static ClientShopServer clientShopServer;
    @FXML
    public GridPane gridPane;
    private ArrayList<ShopCellMenu> shopCellMenus;

    public static void setUsername(String username) {
        ShopMenuGui.logInUsername = username;
    }

    public static ShopMenuGui getShopMenuGui() {
        if (shopMenuGui == null)
            shopMenuGui = new ShopMenuGui();
        return shopMenuGui;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ShopMenuGui.stage = primaryStage;

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefSize(1080, 720);
        anchorPane.setStyle("-fx-background-color: black");
        Button backButton = new Button("back");
        backButton.setPrefSize(50, 26);
        backButton.setLayoutY(20);
        backButton.setLayoutX(1000);
        backButton.setOnMouseClicked(e -> backToMainMenu());
        gridPane = new GridPane();
        gridPane.setVgap(5);
        gridPane.setHgap(10);
        anchorPane.getChildren().addAll(gridPane, backButton);
        ScrollPane scrollPane = new ScrollPane(anchorPane);
        Scene scene = new Scene(scrollPane, 1080, 720);
        stage.setScene(scene);
        stage.setTitle("shop");
        update();
        createShortCut();
    }


    public void update() {
        shopCellMenus = new ArrayList<>();
        HashMap<String, Integer> cards = getClientShopServer().getCardsPrices();
        HashMap<String, Integer> shopInventory = getClientShopServer().getShopInventory();
        HashMap<String, Boolean> cardsStatus = getClientShopServer().getCardsStatus();
        ShopCellMenu.setClientShopServer(clientShopServer);
        for (int index = 0; index < cards.keySet().size(); index++) {
            String cardName = (String) cards.keySet().toArray()[index];
            ShopCellMenu shopCellMenu = new ShopCellMenu(cardName, shopInventory.get(cardName), cardsStatus.get(cardName));
            shopCellMenu.setShopMenuGui(this);
            shopCellMenus.add(shopCellMenu);
        }

        for (int i = 0; i < shopCellMenus.size(); i++) {
            int x = i % 5;
            int y = i / 5;
            gridPane.add(shopCellMenus.get(i), x, y);
        }
    }

    public void backToMainMenu() {
        MainMenuGui mainMenuGui = new MainMenuGui();
        try {
            mainMenuGui.start(stage);
        } catch(Exception ignored) {
        }
    }

    private void createShortCut(){
        stage.getScene().getAccelerators().put(KeyCombination.keyCombination("CTRL+SHIFT+C"),
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            new CheatMenu().start(null);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
    }

    private ClientShopServer getClientShopServer() {
        if (clientShopServer == null)
            clientShopServer = new ClientShopServer();
        return clientShopServer;
    }

    public void increaseMoneyCheat(String input) {
        String moneyStr = input.replace("increase --money ", "");
        clientShopServer.increaseMoneyCheat(Integer.parseInt(moneyStr));
    }

    public void setAvailableCard(String input) {
        String cardName = input.replace("setAvailable --cardname ", "");
        int answer = clientShopServer.setIsCardBannedErrorHandler(cardName, false);
        if (answer == 1)
            ShowOutput.showOutput("Error", "there is no card with this name");
        else
            ShowOutput.showOutput("Success", "the " + cardName + " is available now");
        ShopMenuGui.getShopMenuGui().update();
    }

    public void setUnavailableCard(String input) {
        String cardName = input.replace("setUnavailable --cardname ", "");
        int answer = clientShopServer.setIsCardBannedErrorHandler(cardName, true);
        if (answer == 1)
            ShowOutput.showOutput("Error", "there is no card with this name");
        else {
            ShowOutput.showOutput("Success", "the " + cardName + " is unavailable now");
            ShopMenuGui.getShopMenuGui().update();
        }
    }

    public void increaseCard(String input) {
        Pattern pattern = Pattern.compile("^increaseCard --count (\\d+) --cardname (.*)$");
        Matcher matcher = pattern.matcher(input);
        matcher.find();
        int number = Integer.parseInt(matcher.group(1));
        int answer = clientShopServer.increaseShopInventoryErrorHandler(matcher.group(2), number);
        if (answer == 1)
            ShowOutput.showOutput("Error", "there is no card with this name");
        else {
            ShowOutput.showOutput("Success", "the " + matcher.group(2) + " increased successfully");
            ShopMenuGui.getShopMenuGui().update();
        }
    }

    public void decreaseCard(String input) {
        Pattern pattern = Pattern.compile("^decreaseCard --count (\\d+) --cardname (.*)$");
        Matcher matcher = pattern.matcher(input);
        matcher.find();
        int number = Integer.parseInt(matcher.group(1));
        int answer = clientShopServer.decreaseShopInventoryErrorHandler(matcher.group(2), number);
        if (answer == 1)
            ShowOutput.showOutput("Error", "there is no card with this name");
        else {
            ShowOutput.showOutput("Success", "the " + matcher.group(2) + " decreased successfully");
            ShopMenuGui.getShopMenuGui().update();
        }
    }
}
