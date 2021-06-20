//package view.gui;
//
//import controller.DeckController;
//import controller.MainMenuController;
//import controller.ProfileController;
//import controller.ShopController;
//import javafx.application.Application;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.input.MouseEvent;
//import javafx.stage.Stage;
//import model.user.User;
//
//import java.io.IOException;
//
//public class MainMenuGui extends MenuGui {
//    private static Stage stage;
//    private static MainMenuController mainMenuController;
//    private static User user;
//
//
//
//    @Override
//    public void start(Stage stage) throws Exception {
//        MainMenuGui.stage = stage;
//        Parent root = FXMLLoader.load(getClass().getResource("MainMenuGui.fxml"));
//        Scene scene = new Scene(root, 1080, 720);
//        stage.setScene(scene);
//        stage.setTitle("YU-GI-OH!");
//    }
//
//    public static void setUser(User user) {
//        MainMenuGui.user = user;
//    }
//
//    public void startDuel(MouseEvent mouseEvent) {
//    }
//
//    public void startDeckMenu(MouseEvent mouseEvent) {
//        DeckMenuGui deckMenuGui=new DeckMenuGui();
//        DeckController deckController=new DeckController(user.getUsername());
//        DeckMenuGui.setDeckController(deckController);
//        try {
//            deckMenuGui.start(stage);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void startScoreBoardMenu(MouseEvent mouseEvent) throws Exception {
//        ScoreBoardMenuGui scoreBoardMenuGui = new ScoreBoardMenuGui();
//        ScoreBoardMenuGui.setUser(user);
//        scoreBoardMenuGui.start(stage);
//    }
//
//    public void startProfileMenu(MouseEvent mouseEvent) {
//        ProfileMenuGui profileMenuGui=new ProfileMenuGui();
//        ProfileMenuGui.setProfileController(new ProfileController(user.getUsername()));
//        try {
//            profileMenuGui.start(stage);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    public void startShopMenu(MouseEvent mouseEvent) throws Exception {
//        ShopMenuGui shopMenuGui = new ShopMenuGui();
//        ShopMenuGui.setUsername(user.getUsername());
//        shopMenuGui.start(stage);
//    }
//
//    public void startImportExportMenu(MouseEvent mouseEvent) {
//    }
//
//    public void logout(MouseEvent mouseEvent) throws IOException {
//        LoginMenuGui loginMenuGui = new LoginMenuGui();
//        loginMenuGui.start(stage);
//    }
//}
