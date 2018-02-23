package GameState;

import javafx.event.Event;
import javafx.scene.canvas.GraphicsContext;

public abstract class GameState {

    protected GameStateController gsc;

    public abstract void init();
    public abstract void update();
    public abstract void draw(GraphicsContext gc);
    public abstract void handle(Event event);
}
