package GameStateManager;

import javafx.event.Event;
import javafx.scene.layout.Pane;

abstract public class GameState {

    protected GameStateManager gsm;

    public GameState(GameStateManager gsm){
        this.gsm = gsm;
    }

    public abstract void init(Pane gamePane);
    public abstract void update();
    public abstract void draw(Pane gamePane);
    public abstract void handle(Event event);
}
