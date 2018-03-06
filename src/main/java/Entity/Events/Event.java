package Entity.Events;

import Entity.*;
import GameStateManager.Room;
import TileMap.Tile;

/**
Class that creates the events.
 */
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

    /**
     * commits the event
     */
    public abstract void commitEvent();

    /**
     * commits the attacked event
     */
    public abstract void commitAttackEvent();

    /**
     * gets the activated event
     * @return the event
     */
    public boolean getActivatedOnce(){
        return activatedOnce;
    }

    /**
     * sets the event when activated
     * @param activatedOnce takes in the activated event.
     */
    public void setActivatedOnce(boolean activatedOnce) {
        this.activatedOnce = activatedOnce;
    }
}
