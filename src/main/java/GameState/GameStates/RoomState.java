package GameState.GameStates;

import Enitiy.Character.Samuel;
import GameState.*;
import Limbo.Game;
import TileMap.*;
import javafx.event.Event;
import javafx.scene.canvas.GraphicsContext;

public class RoomState extends GameState {

    private TileMap tileMap;
    private Background background;
    private final String backgroundPath = "/Assets/maxresdefault.jpg";

    private Samuel sam;

    public RoomState(GameStateController gsc){
        this.gsc = gsc;
        init();
    }

    @Override
    public void init() {

        tileMap = new TileMap(30);
        tileMap.loadTiles("/Assets/maxresdefault.jpg");
        tileMap.loadMap("/Maps/room1.map");
        tileMap.setPosition(0,0);

        background = new Background(backgroundPath, 0);

//        sam = new Samuel(tileMap);
//        sam.setPosition(100,100);


    }

    @Override
    public void update() {
        //sam.update();
        //tileMap.setPosition(Game.WIDTH / 2 - sam.getX(), Game.HEIGHT / 2 - sam.getY());

    }

    @Override
    public void draw(GraphicsContext gc) {
        background.draw(gc);
        tileMap.draw(gc);
        //sam.draw(gc);
    }

    @Override
    public void handle(Event event){

    }

}
