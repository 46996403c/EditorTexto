package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;

public class Main extends Application {
    private static Stage scena;
    @Override
    public void start(Stage primaryStage) throws Exception{
        scena = primaryStage;
        scena.getIcons().add(new Image("http://www.mundodeodio.es/foro/images/smilies/kk03.png"));
        Parent root = FXMLLoader.load(getClass().getResource("viewEditorTexto.fxml"));
        primaryStage.setTitle("Editor de texto");
        primaryStage.setScene(new Scene(root, 800, 475));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
    public static Stage getStage() {
        return scena;
    }
}
