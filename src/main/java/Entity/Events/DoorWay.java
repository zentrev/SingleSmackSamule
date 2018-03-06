package Entity.Events;

import GameStateManager.*;
import GameStateManager.Room;
import GameStateManager.RoomState;
import TileMap.*;

public class DoorWay extends Event {

    private int roomDest;
    private double samXDest;
    private double samYDest;

    /**
     * default constructor
     * @param tileMap - tilemap for the room
     * @param roomDest - room the door leads to
     * @param x - x pos of the door relative to tilemap
     * @param y - y pos of the door relative to tilemap
     * @param samXDest - x position to spawn samuel when moving to next room
     * @param samYDest - y position to spawn samuel when moving to next room
     */
    public DoorWay(Tile[][] tileMap, int roomDest, double x, double y, double samXDest, double samYDest){
        super(tileMap,(int)x,(int)y);

        this.x = x*Room.tileSize;
        this.y = y*Room.tileSize;
        this.samXDest = samXDest*Room.tileSize+(Room.tileSize/2);
        this.samYDest = samYDest*Room.tileSize;

        this.width = Room.tileSize;
        this.height = Room.tileSize;

        this.roomDest = roomDest;

        this.init();

    }

    public void init(){
        this.setTranslateX(this.x);
        this.setTranslateY(this.y);
        this.setFitWidth(width);
        this.setFitHeight(height);
    }

    @Override
    public void commitEvent() {
        GameStateManager.ROOM.changeRoom(roomDest,(int)samXDest,(int)samYDest);
    }

    @Override
    public void commitAttackEvent() {

    }

    /**
     * @return -  the room to move the player to when colliding with the door
     */
    public int getRoomDest() {
        return roomDest;
    }

    /**
     * @param roomDest - the room to move the player when colliding with the door
     */
    public void setRoomDest(int roomDest) {
        this.roomDest = roomDest;
    }

    /**
     * @return -  the x position samuel will spawn when moving rooms
     */
    public double getSamXDest() {
        return samXDest;
    }

    /**
     * @param samXDest - set the x position samuel will spawn when moving rooms
     */
    public void setSamXDest(double samXDest) {
        this.samXDest = samXDest;
    }

    /**
     * @return - the y positon samel will spawn when moving rooms
     */
    public double getSamYDest() {
        return samYDest;
    }

    /**
     * @param samYDest -  set eh position samuel will spawn when moving rooms
     */
    public void setSamYDest(double samYDest) {
        this.samYDest = samYDest;
    }

}
