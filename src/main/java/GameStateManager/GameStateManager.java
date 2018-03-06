package GameStateManager;

import Logic.Game;
import javafx.event.Event;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.util.HashMap;

import static GameStateManager.GameStateManager.STATE.HELPSTATE;
import static GameStateManager.GameStateManager.STATE.DEATH;
import static GameStateManager.GameStateManager.STATE.MENUSTATE;
import static GameStateManager.GameStateManager.STATE.ROOMSTATE;

public class GameStateManager {

    private HashMap<STATE, GameState> gameStates;
    private STATE currentState;

    private Pane gamePane;

    private Game game;

    public enum STATE {
        MENUSTATE,
        HELPSTATE,
        ROOMSTATE,
        DEATH
    }

    public static MenuState MENU;
    public static HelpState HELP;
    public static RoomState ROOM;
    public static Death DEAD;

    public GameStateManager(Pane gamePane, Game game){

        this.gamePane = gamePane;
        this.game = game;

        gameStates = new HashMap<STATE, GameState>();
        gameStates.put(MENUSTATE, MENU = new MenuState(this));
        gameStates.put(ROOMSTATE, ROOM = new RoomState(this));
        gameStates.put(HELPSTATE, HELP = new HelpState(this));
        gameStates.put(DEATH, DEAD = new Death(this));
        setState(MENUSTATE);

    }

    public void newRoomState(){
        ROOM = new RoomState(this);
        gameStates.put(ROOMSTATE, ROOM );
        setState(ROOMSTATE);
    }

    public void setState(STATE state){
        currentState = state;
        gamePane.getChildren().clear();
        gameStates.get(currentState).init(gamePane);
    }

    public void setBackground(Image bg){
        Game.backgroundPane.setImage(bg);
        Game.backgroundPane.setFitWidth(Game.WIDTH*Game.SCALE);
        Game.backgroundPane.setFitHeight(Game.HEIGHT*Game.SCALE);

    }

    public void closeGame(){
        this.game.close();
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

