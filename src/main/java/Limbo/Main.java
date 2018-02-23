package Limbo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application{
    public static void main(String[] args) {
        launch(Main.class,args);
    }

    public Stage stage;
    public Stage game;

    public void start(Stage primaryStage) throws Exception {
        game = new Game();
        game.show();
    }
}
