package GameState;

import GameState.GameStates.MenuState;
import GameState.GameStates.RoomState;
import javafx.event.Event;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.HashMap;

public class GameStateController {

    private HashMap<Integer, GameState> gameStates;
    private int currentState;

    public static final int MENUSTATE = 0;
    public static final int SETTINGS = 1;
    public static final int ROOMSTATE = 2;

    public GameStateController(){

        gameStates = new HashMap<Integer, GameState>();
        currentState = 0;
        gameStates.put(MENUSTATE,new MenuState(this));
        gameStates.put(ROOMSTATE,new RoomState(this));

    }

    public void setState(int state){
        currentState = state;
        gameStates.get(currentState).init();
    }

    public void draw(GraphicsContext gc){
        gameStates.get(currentState).draw(gc);
    }

    public void update(){
        gameStates.get(currentState).update();
    }

    public void handle(Event event){
        gameStates.get(currentState).handle(event);
    }
}
