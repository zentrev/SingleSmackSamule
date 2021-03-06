package Logic;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{

    public static void main(String[] args) {
        launch(Main.class,args);
    }

    public Game game;
    GameThread gameThread;

    @Override
    public void start(Stage primaryStage) throws Exception {
        game = new Game();
        game.show();
        gameThread = new GameThread(game);
        gameThread.start(null);
    }

    /**
     * closes the stage and stops the program
     */
    public void close(){
        game.close();
    }
}
