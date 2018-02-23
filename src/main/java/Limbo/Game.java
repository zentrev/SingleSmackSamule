package Limbo;

import GameState.GameStateController;
import javafx.event.Event;
import javafx.event.EventHandler;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Collections;

public class Game extends Stage implements Runnable, EventHandler{


    // dimensions
    public static final int WIDTH = 1080;
    public static final int HEIGHT = 720;
    public static final double SCALE = 1;

    // game thread
    private Thread thread;
    private boolean running;
    private int FPS = 60;
    private long targetTime = 1000 / FPS;

    // image
    private Canvas canvas;
    private GraphicsContext gc;

    // game state manager
    private GameStateController gsc;


    public Game(){
        this.setTitle("Single Smack Samuel");
        canvas = new Canvas();
        canvas.setHeight(this.HEIGHT*SCALE);
        canvas.setWidth(this.WIDTH*SCALE);
        gc = canvas.getGraphicsContext2D();
        Pane pane = new Pane();
        pane.getChildren().add(canvas);
        Scene scene = new Scene(pane);
        scene.setOnKeyPressed(this);
        this.setScene(scene);
        running = true;
        if(thread == null){
            thread = new Thread(this);
            thread.start();
        }
        this.setFocused(true);
        this.requestFocus();
        this.addEventFilter(Event.ANY, this);
    }



    public void run() {
        init();


        long start;
        long elapsed;
        long wait;

        // game loop
        while(running){
            start = System.nanoTime();

            update();
            draw();
            drawToScreen();

            elapsed = System.nanoTime() - start;
            wait = targetTime - elapsed/1000000;

            if(wait < 0){
                wait = 1;
            }
            try{
                thread.sleep(wait);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void init(){

    }

    public void update(){

    }
    public void draw(){
        gsc.draw(gc);
    }
    public void drawToScreen(){

    }


    @Override
    public void handle(Event event) {
        gsc.handle(event);
    }
}
