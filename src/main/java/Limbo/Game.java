package Limbo;

import GameState.GameStateController;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;

public class Game extends Pane implements EventHandler<Event>, Runnable {

    private GameStateController gsc;

    public void handle(Event event) {
        gsc.handle(event);
    }

    public void run() {

    }

    public void init(){

    }
}
