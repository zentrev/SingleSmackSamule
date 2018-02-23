package GameState;

import GameState.GameStates.MenuState;
import GameState.GameStates.RoomState;
import javafx.event.Event;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class GameStateController {

    private ArrayList<GameState> gameStates;
    private int currentState;

    public GameStateController(){

        gameStates = new ArrayList<GameState>();

        currentState = 0;
        gameStates.add(new MenuState(this));
        gameStates.add(new RoomState(this));

    }

    public void draw(GraphicsContext gc){

    }

    public void update(){
        gameStates.get(currentState).update();
    }

    public void handle(Event event){
        gameStates.get(currentState).handle(event);
    }
}
