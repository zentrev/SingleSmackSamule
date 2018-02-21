package Limbo;

import GameState.GameStateController;
import javafx.event.Event;
import javafx.event.EventHandler;

public class Game implements EventHandler<Event> {

    private GameStateController gsc;

    public void handle(Event event) {
        gsc.handle(event);
    }
}
