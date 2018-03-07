package GameStateManager;

import Logic.Game;
import javafx.event.Event;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.util.HashMap;

import static GameStateManager.GameStateManager.STATE.*;

public class GameStateManager {

    private HashMap<STATE, GameState> gameStates;
    private STATE currentState;

    private Pane gamePane;

    private Game game;

    public enum STATE {
        MENUSTATE,
        HELPSTATE,
        ROOMSTATE,
        DEATH,
        WIN
    }

    public static MenuState MENU;
    public static HelpState HELP;
    public static RoomState ROOM;
    public static Death DEAD;
    public static winState WINSTATE;

    /**
     * default constructor
     * @param gamePane - Pane to draw the main game
     * @param game - The game stage
     */
    public GameStateManager(Pane gamePane, Game game){

        this.gamePane = gamePane;
        this.game = game;

        gameStates = new HashMap<STATE, GameState>();
        gameStates.put(MENUSTATE, MENU = new MenuState(this));
        gameStates.put(ROOMSTATE, ROOM = new RoomState(this));
        gameStates.put(HELPSTATE, HELP = new HelpState(this));
        gameStates.put(DEATH, DEAD = new Death(this));
        gameStates.put(WIN, WINSTATE = new winState(this));
        setState(MENUSTATE);

    }

    /**
     * creates a new roomstate restarting the game
     */
    public void newRoomState(){
        ROOM = new RoomState(this);
        gameStates.put(ROOMSTATE, ROOM );
        setState(ROOMSTATE);
    }

    /**
     * sets the state that the game is currently in
     * @param state - state to change the game to
     */
    public void setState(STATE state){
        currentState = state;
        gamePane.getChildren().clear();
        gamePane.setTranslateY(0);
        gamePane.setTranslateX(0);
        gameStates.get(currentState).init(gamePane);
        Game.UIPane.getChildren().clear();
    }

    /**
     * sets background of game
     * @param bg
     */
    public void setBackground(Image bg){
        Game.backgroundPane.setImage(bg);
        Game.backgroundPane.setFitWidth(Game.WIDTH*Game.SCALE);
        Game.backgroundPane.setFitHeight(Game.HEIGHT*Game.SCALE);

    }

    /**
     * closes the game stage
     */
    public void closeGame(){
        this.game.close();
    }

    /**
     * draws whatever the state need to draw to the gamepane
     * @param gamePane - gamepane that the game is displayed on
     */
    public void draw(Pane gamePane){
        gameStates.get(currentState).draw(gamePane);
    }

    /**
     * runs the update function in the current state
     */
    public void update(){
        gameStates.get(currentState).update();
    }

    /**
     * handles all events and pushes them to the current state
     * @param event - user input
     */
    public void handle(Event event){
        gameStates.get(currentState).handle(event);
    }

    /**
     * returns the game pane we draw the game onto
     * @return - gamepane
     */
    public Pane getGamePane() {
        return gamePane;
    }

}

