package GameStateManager;

import javafx.event.Event;
import javafx.scene.layout.Pane;

import java.util.HashMap;

import static GameStateManager.GameStateManager.STATE.MENUSTATE;
import static GameStateManager.GameStateManager.STATE.ROOMSTATE;

public class GameStateManager {

    private HashMap<STATE, GameState> gameStates;
    private STATE currentState;

    private Pane gamePane;

    public static enum STATE {
        MENUSTATE,
        SETTIGS,
        ROOMSTATE
    }

    public static MenuState MENU;
    public static RoomState ROOM;

    public GameStateManager(Pane gamePane){

        this.gamePane = gamePane;

        gameStates = new HashMap<STATE, GameState>();
        gameStates.put(MENUSTATE, MENU = new MenuState(this));
        gameStates.put(ROOMSTATE, ROOM = new RoomState(this));
        setState(MENUSTATE);

    }

    public void setState(STATE state){
        currentState = state;
        gamePane.getChildren().clear();
        gameStates.get(currentState).init(gamePane);
    }

    public void draw(Pane gamePane){
        gameStates.get(currentState).draw(gamePane);
    }

    public void update(){
        gameStates.get(currentState).update();
    }

    public void handle(Event event){
        gameStates.get(currentState).handle(event);
    }

    public Pane getGamePane() {
        return gamePane;
    }

    public void setGamePane(Pane gamePane) {
        this.gamePane = gamePane;
    }
}

