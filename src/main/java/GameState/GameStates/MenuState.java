package GameState.GameStates;

import GameState.*;
import Limbo.Game;
import TileMap.Background;
import javafx.event.Event;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MenuState extends GameState {

    private Background background;

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

    }


    @Override
    public void handle(Event event) {

    }


    public MenuState(GameStateController gsm){
        this.gsc = gsm;

        try{

            background = new Background(path,1);
            background.setVector(-.1, 0);

        }catch (Exception e){
            e.printStackTrace();
        }
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
                gc.setFill(Color.BLACK);
            } else {
                gc.setFill(Color.RED);
            }
            gc.fillText(options[i], Game.WIDTH/2-(options[i].length()*3), Game.HEIGHT/2 + i * 15);
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
