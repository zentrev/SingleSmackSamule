package Entity.Events;

import GameStateManager.Room;
import TileMap.*;

public class Event1 extends Event {
    public Event1(Tile[][] tm, int eventx, int eventy){
        super(tm);

        this.x = eventx;
        this.y = eventy;

        this.setTranslateX(x);
        this.setTranslateY(y);

        this.width = Room.tileSize;
        this.height = Room.tileSize;

        collionHeight = height;
        collionWidth = width;

        activatedOnce = false;
    }

    @Override
    public void commitEvent() {
        System.out.println("Event contacted");
    }
}
