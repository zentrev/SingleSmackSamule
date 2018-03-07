package Logic;

import GameStateManager.GameStateManager;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class Game extends Stage implements EventHandler{

    // dimensions
    public static final int WIDTH = 1080;
    public static final int HEIGHT = 720;
    public static final double SCALE = 1;

    public boolean running;

    // game state manager
    public static GameStateManager gsm;

    // panes
    private Pane gamePane;
    public static ImageView backgroundPane;
    private Pane rootPane;
    public static Pane UIPane;


    /**
     * default constructor
     */
    public Game(){
        this.setTitle("Single Smack Samuel");
        gamePane = new Pane();
        rootPane = new Pane();
        UIPane = new Pane();
        backgroundPane = new ImageView();
        backgroundPane.setFitHeight(HEIGHT*SCALE);
        backgroundPane.setFitWidth(WIDTH*SCALE);
        UIPane.setPrefHeight(HEIGHT*SCALE);
        UIPane.setPrefWidth(WIDTH*SCALE);
        rootPane.setPrefHeight(this.HEIGHT*SCALE);
        rootPane.setPrefWidth(this.WIDTH*SCALE);
        rootPane.getChildren().add(backgroundPane);
        rootPane.getChildren().add(gamePane);
        rootPane.getChildren().add(UIPane);
        Scene scene = new Scene(rootPane);
        scene.setOnKeyPressed(this);
        this.setScene(scene);
        running = true;

        this.setMaxWidth(this.WIDTH*SCALE);
        this.setMaxHeight(this.HEIGHT*SCALE);
        this.setResizable(false);

        this.setFocused(true);
        this.requestFocus();
        this.addEventFilter(KeyEvent.ANY, this);
    }


    /**
     * initiates a new game state manage and set it to gsm
     */
    public void init(){
        gsm = new GameStateManager(gamePane, this);
    }

    /**
     * updates the gsm
     */
    public void update(){
        gsm.update();
    }

    /**
     * calls the gsm draw function and inputs gamepane
     */
    public void draw(){
        gsm.draw(gamePane);
    }

    @Override
    public void handle(Event event) {
        gsm.handle(event);
    }
}

