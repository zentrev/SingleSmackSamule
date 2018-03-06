package Entity.Events;

import Entity.*;
import GameStateManager.Room;
import TileMap.Tile;

public abstract class Event extends Entity {
    public Event(Tile[][] tileMap, int eventx, int eventy) {
        super(tileMap);
        this.x = eventx;
        this.y = eventy;

        this.setTranslateX(x);
        this.setTranslateY(y);

        this.width = Room.tileSize;
        this.height = Room.tileSize;

        this.collionHeight = height;
        this.collionWidth = width;

        this.setFitWidth(collionWidth);
        this.setFitHeight(collionHeight);

        activatedOnce = false;
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
