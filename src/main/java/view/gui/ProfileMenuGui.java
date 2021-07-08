package view.gui;

import controller.ProfileController;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import model.user.User;
import org.apache.commons.io.FileUtils;

import java.io.*;

public class ProfileMenuGui extends MenuGui {
    public ImageView imageView;
    public AnchorPane anchorPane;
    public TextField newNickname;
    public TextField oldPassword;
    public TextField newPassword;
    private static Stage stage;
    private static ProfileController profileController;

    @Override
    public void start(Stage stage) throws Exception {
        anchorPane = FXMLLoader.load(getClass().getResource("ProfileMenuGui.fxml"));
        Scene scene = new Scene(anchorPane);
        ProfileMenuGui.stage = stage;
        ProfileMenuGui.stage.setScene(scene);
        setUsernameAndNickname();
    }

    public void initialize() {
        User user = profileController.getUser();
        imageView.setImage(User.getPicture(user.getProfilePicturePath()));
    }

    public static void setProfileController(ProfileController profileController) {
        ProfileMenuGui.profileController = profileController;
    }

    public void ChooseFile(MouseEvent mouseEvent) throws FileNotFoundException {
        File file = GetInput.choosePictureFile();
        if (file != null) {
            File file1=new File("src/main/resources/view/gui/pictureFiles"+file.getName());
            try {
                FileUtils.copyFile(file, file1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Image image = new Image(new FileInputStream(file.getPath()));
            imageView.setFitWidth(150);
            imageView.setImage(image);
            profileController.getUser().setProfilePicturePath(file.getPath());
        } else {
            ShowOutput.showOutput("error box","wrong file type please try again");
        }
    }

    private void setUsernameAndNickname() {
        Text usernameText = new Text();
        Text nicknameText = new Text();
        usernameText.setFill(new Color(1,1,1,1));
        nicknameText.setFill(new Color(1,1,1,1));
        usernameText.setFont(new Font("Bookman Old Style",12));
        nicknameText.setFont(new Font("Bookman Old Style",12));
        usernameText.setX(309);
        usernameText.setY(109);
        nicknameText.setY(149);
        nicknameText.setX(309);
        usernameText.setText("username: " + profileController.getUser().getUsername());
        nicknameText.setText("nickname: " + profileController.getUser().getNickname());
        anchorPane.getChildren().add(usernameText);
        anchorPane.getChildren().add(nicknameText);
    }

    public void back(MouseEvent mouseEvent) {
        try {
            new MainMenuGui().start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changeNickname(MouseEvent mouseEvent) {
        int error = profileController.changeNicknameErrorHandler(newNickname.getText());
        if (error == 1) {
            ShowOutput.showOutput("error box","this nickname is already exist please try again");
        } else {
            try {
                this.start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void changePassword(MouseEvent mouseEvent) {
        int error = profileController.changePasswordErrorHandler(oldPassword.getText(), newPassword.getText());
        if (error == 1) {
            ShowOutput.showOutput("error box","incorrect old password");
        } else if (error == 2) {
            ShowOutput.showOutput("error box","your old and new password are equal");
        } else {
            try {
                new ProfileMenuGui().start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
