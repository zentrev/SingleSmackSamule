package GameState.GameStates;

import GameState.*;
import Limbo.Game;
import TileMap.Background;
import javafx.event.Event;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.security.Key;


public class MenuState extends GameState {

    private Background background;
    private Font font;
    private int fontSize = 45;

    // Image Path
    final static String path = "/Assets/maxresdefault.jpg";

    private int currentChoice = 0;
    private String[] options = {
            "Play",
            "Options",
            "Quit"
    };


    @Override
    public void init() {
        font = new Font("Arial", fontSize);
        try{
            background = new Background(path,1);
            background.setVector(-.1, 0);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public void handle(Event event) {
        if(event.getEventType() == KeyEvent.KEY_RELEASED){
            KeyEvent keyEvent = (KeyEvent)event;
            System.out.println(keyEvent.getCode());
            switch (keyEvent.getCode()){
                case S:
                    currentChoice++;
                    if(currentChoice == options.length){
                        currentChoice=0;
                    }
                    break;
                case W:
                    currentChoice--;
                    if(currentChoice == -1){
                        currentChoice = options.length;
                    }
                    break;
                case ENTER:
                    select();
                    break;

            }
        }

    }


    public MenuState(GameStateController gsm){
        init();
        this.gsc = gsm;
    }

    @Override
    public void update() {
        background.update();
    }

    @Override
    public void draw(GraphicsContext gc) {
        background.draw(gc);

        for(int i = 0; i < options.length; i++){
            if(i == currentChoice){
                gc.setFill(Color.BLUE);
            } else {
                gc.setFill(Color.BLACK);
            }
            gc.setTextAlign(TextAlignment.CENTER);
            gc.setFont(font);
            gc.fillText(options[i], Game.WIDTH/2, Game.HEIGHT/2 + i * fontSize);
        }
    }

    private void select() {
        switch (currentChoice){
            case 0:
                gsc.setState(GameStateController.ROOMSTATE);
                break;
            case 1:
                gsc.setState(GameStateController.SETTINGS);
                break;
            case 2: {
                System.exit(0);
            }
        }
    }

}
