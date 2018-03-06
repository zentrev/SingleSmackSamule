package GameStateManager;

import javafx.event.Event;
import javafx.scene.layout.Pane;

abstract public class GameState {

    protected GameStateManager gsm;

    /**
     * checks the initial, does the update, draws out the game and handles all the events or items.
     * @param gsm
     */
    public GameState(GameStateManager gsm){
        this.gsm = gsm;
    }

    /**
     * inits the game
     * @param gamePane
     */
    public abstract void init(Pane gamePane);

    /**
     * updates the game.
     */
    public abstract void update();

    /**
     * draws out the maps
     * @param gamePane
     */
    public abstract void draw(Pane gamePane);

    /**
     * handles the events.
     * @param event
     */
    public abstract void handle(Event event);
}
