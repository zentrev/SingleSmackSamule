package GameState.GameStates;

import Enitiy.Character.Samuel;
import GameState.*;
import TileMap.*;
import javafx.event.Event;
import javafx.scene.canvas.GraphicsContext;

public class RoomState extends GameState {

    private TileMap tileMap;
    private Background bg;

    private Samuel sam;

    public RoomState(GameStateController gsc){
        this.gsc = gsc;
    }

    @Override
    public void init() {

        tileMap = new TileMap(30);
        tileMap.loadTiles("/Assets/maxresdefault.jpg");
        tileMap.loadMap("/Maps/level1-1.map");
        tileMap.setPosition(0,0);

        bg = new Background("/Assets/maxresdefault.jpg", 01);

//        sam = new Samuel(tileMap);
//        sam.setPosition(100,100);


    }

    @Override
    public void update() {

    }

    @Override
    public void draw(GraphicsContext gc) {

    }

    @Override
    public void handle(Event event){

    }

}
