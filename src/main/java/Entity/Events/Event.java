package Entity.Events;

import Entity.*;
import TileMap.Tile;

public abstract class Event extends Entity {
    public Event(Tile[][] tileMap) {
        super(tileMap);
    }
    public abstract void comitEvent();
}
