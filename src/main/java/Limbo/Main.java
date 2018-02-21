package Limbo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
    public static void main(String[] args) {
        launch(Main.class,args);
    }

    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/samuel.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = primaryStage;
        stage.setTitle("Single Smack Samuel paint 2000!!!");
        stage.setScene(scene);
        stage.show();
        Game controller = loader.getController();
        controller.init();
    }
}
