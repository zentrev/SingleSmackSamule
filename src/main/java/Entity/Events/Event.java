package Entity.Events;

import Entity.*;
import GameStateManager.Room;
import TileMap.Tile;

/**
Class that creates the events.
 */
public abstract class Event extends Entity {
    protected Room room;
    protected boolean activatedOnce;

    /**
     * constructor
     * @param tileMap - tilemap for room
     * @param eventx - x pos
     * @param eventy - y pos
     * @param room - room connected
     */
    public Event(Tile[][] tileMap, int eventx, int eventy, Room room) {
        super(tileMap);
        this.x = eventx;
        this.y = eventy;
        this.room = room;

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

    /**
     * constructor
     * @param tileMap - tilemap for room
     * @param x - x pos
     * @param y - y pos
     */
    public Event(Tile[][] tileMap, int x, int y) {
        super(tileMap);
        this.x = y;
        this.y = x;

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
