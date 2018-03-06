package Entity.Events;

import Entity.*;
import TileMap.Tile;

public abstract class Event extends Entity {
    public Event(Tile[][] tileMap) {
        super(tileMap);
    }
    protected boolean activatedOnce;
    public abstract void commitEvent();
    public abstract void commitAttackEvent();
    public boolean getActivatedOnce(){
        return activatedOnce;
    }

    public void setActivatedOnce(boolean activatedOnce) {
        this.activatedOnce = activatedOnce;
    }
}
