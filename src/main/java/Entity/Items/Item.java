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


    public Item(Tile[][] tileMap) {
        super(tileMap);
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    abstract public void itemTouched();

    @Override
    public boolean equals(Object obj){
        if(obj instanceof Item){
            Item temp = (Item)obj;
            return this.xSet == temp.xSet && this.ySet == temp.ySet;
        }
        return false;
    }

    public boolean isPicked() {
        return isPicked;
    }

    public void setPicked(boolean picked) {
        isPicked = picked;
    }
}
