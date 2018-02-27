package Logic;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;

public class GameThread extends Application {

    Game game;

    GameThread(Game game){
        this.game = game;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        game.init();
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (game.running) {
                    game.update();
                    game.draw();

                }
            }
        };
        timer.start();
    }
}
