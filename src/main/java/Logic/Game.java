package Logic;

import GameStateManager.GameStateManager;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Game extends Stage implements EventHandler{

    // dimensions
    public static final int WIDTH = 1080;
    public static final int HEIGHT = 720;
    public static final double SCALE = 1;

    public boolean running;

    // game state manager
    private GameStateManager gsm;

    // panes
    private Pane gamePane;
    private Pane rootPane;


    public Game(){
        this.setTitle("Single Smack Samuel");
        gamePane = new Pane();
        rootPane = new Pane();
        rootPane.setPrefHeight(this.HEIGHT*SCALE);
        rootPane.setPrefWidth(this.WIDTH*SCALE);
        rootPane.getChildren().add(gamePane);
        Scene scene = new Scene(rootPane);
        scene.setOnKeyPressed(this);
        this.setScene(scene);
        running = true;

        this.setFocused(true);
        this.requestFocus();
        this.addEventFilter(KeyEvent.ANY, this);
    }



    public void init(){
        gsm = new GameStateManager(gamePane);
    }

    public void update(){
        gsm.update();
    }
    public void draw(){
        gsm.draw(gamePane);
    }

    @Override
    public void handle(Event event) {
        gsm.handle(event);
    }
}
