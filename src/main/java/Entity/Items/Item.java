package Entity.Items;

import Entity.*;
import GameStateManager.Room;
import GameStateManager.RoomState;
import TileMap.Tile;

abstract public class Item extends Entity {

    protected Room room;

    protected int xSet;
    protected int ySet;

    protected boolean isPicked;

    /**
     * creates the tile map for the levels
     * @param tileMap - the tile map for the level
     */
    public Item(Tile[][] tileMap) {
        super(tileMap);
    }

    /**
     * returns a room that the item is in
     * @return - room that houses an item
     */
    public Room getRoom() {
        return room;
    }

    /**
     * sets the room that the item is in
     * @param room - the room that houses the item
     */
    public void setRoom(Room room) {
        this.room = room;
    }

    /**
     * checks if Samuel collides with the item
     */
    abstract public void itemTouched();

    @Override
    public boolean equals(Object obj){
        if(obj instanceof Item){
            Item temp = (Item)obj;
            return this.xSet == temp.xSet && this.ySet == temp.ySet;
        }
        return false;
    }

    /**
     * checks if the item has been picked up
     * @return - the result of the item being picked up. True = item has been picked up. False = item has not been picked up
     */
    public boolean isPicked() {
        return isPicked;
    }

    /**
     * sets the true/false for the item being picked up
     * @param picked - the result of the item being picked up
     */
    public void setPicked(boolean picked) {
        isPicked = picked;
    }
}
