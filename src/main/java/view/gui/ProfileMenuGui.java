package view.gui;

import controller.ProfileController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Shadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.user.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ProfileMenuGui extends MenuGui {

    public ImageView imageView;
    public AnchorPane anchorPane;
    public Text usernameText;
    public Text nicknameText;
    public TextField newNickname;
    public TextField oldPassword;
    public TextField newPassword;
    private User user;

    //in code comment shode baad az ok shodan MenuGui ok mishe

//    private final ProfileController profileController;

//    public ProfileMenuGui(ProfileController profileController) {
//        this.profileController = profileController;
//
//    }

    @Override
    public void start(Stage stage) throws Exception {
        anchorPane= FXMLLoader.load(getClass().getResource("ProfileMenuGui.fxml"));
        Scene scene=new Scene(anchorPane);
        stage.setScene(scene);
        getReady();
        stage.show();
    }

    public void ChooseFile(MouseEvent mouseEvent) throws FileNotFoundException {
        File file=GetInput.choosePictureFile();
        if(file!=null){
            Image image=new Image(new FileInputStream(file.getPath()));
            imageView.setFitWidth(150);
            imageView.setImage(image);
        }else{
            buttonError();
        }
    }

    private void buttonError(){
        Button button=new Button();
        button.setText("wrong file type please try again");
        button.setStyle("-fx-cursor:  Hand");
        button.setEffect(new DropShadow());
        button.setLayoutX(81);
        button.setLayoutY(295);
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                anchorPane.getChildren().remove(button);
            }
        });
        anchorPane.getChildren().add(button);
    }

    private void getReady(){
        Text text=new Text();
        text.setX(10);
        text.setY(10);
        text.setText("username: "+"kos");
        text.setFont(new Font(36));

    }

    //baad az dorost shodan class menu gui ok mikonam bakhsh haye payeeni ro

    public void back(MouseEvent mouseEvent) {

    }

    public void changeNickname(MouseEvent mouseEvent) {

    }

    public void changePassword(MouseEvent mouseEvent) {

    }
}