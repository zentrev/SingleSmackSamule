package Entity.Events;

import GameStateManager.*;
import GameStateManager.Room;
import GameStateManager.RoomState;
import TileMap.*;

public class DoorWay extends Event {

    private int roomDest;
    private double samXDest;
    private double samYDest;

    public DoorWay(Tile[][] tileMap, int roomDest, double x, double y, double samXDest, double samYDest){
        super(tileMap);

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
    public void comitEvent() {
        GameStateManager.ROOM.changeRoom(roomDest,(int)samXDest,(int)samYDest);
    }

    public int getRoomDest() {
        return roomDest;
    }

    public void setRoomDest(int roomDest) {
        this.roomDest = roomDest;
    }

    public double getSamXDest() {
        return samXDest;
    }

    public void setSamXDest(double samXDest) {
        this.samXDest = samXDest;
    }

    public double getSamYDest() {
        return samYDest;
    }

    public void setSamYDest(double samYDest) {
        this.samYDest = samYDest;
    }
}
