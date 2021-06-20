//package view.gui;
//
//import javafx.scene.control.Alert;
//import javafx.scene.control.ButtonBar;
//import javafx.scene.control.ButtonType;
//import javax.activation.MimetypesFileTypeMap;
//import javafx.stage.FileChooser;
//
//import java.io.File;
//import java.util.concurrent.atomic.AtomicBoolean;
//
//public class GetInput {
//    public static boolean getYesNoAnswer(String title, String question) {
//        AtomicBoolean result = new AtomicBoolean(false);
//
//        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//        alert.setTitle(title);
//        alert.setHeaderText(question);
//        ButtonType yesButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
//        ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
//        alert.getButtonTypes().setAll(yesButton, noButton);
//        alert.showAndWait().ifPresent(type -> {
//            result.set(type == yesButton);
//        });
//
//        return result.get();
//    }
//
//    public static File choosePictureFile(){
//        FileChooser fileChooser=new FileChooser();
//        File file=fileChooser.showOpenDialog(null);
//        String mimetype= new MimetypesFileTypeMap().getContentType(file);
//        String type = mimetype.split("/")[0];
//        if(type.equals("image")){
//            return file;
//        }else{
//            return null;
//        }
//    }
//}
