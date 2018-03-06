package Entity.Events;

import GameStateManager.Room;
import GameStateManager.RoomState;
import TileMap.*;

public class Event1 extends Event {
    /**
     * default constructor
     * @param tm - tilemap for the room
     * @param eventx - x position to place the event relative to the tilemap
     * @param eventy - y position to place the event relative to the tilemap
     */
    public Event1(Tile[][] tm, int eventx, int eventy){
        super(tm,eventx,eventy);

    }

    @Override
    public void commitEvent() {
        System.out.println("Event contacted");
    }

    @Override
    public void commitAttackEvent() {
        System.out.println("Event Attacked");
    }


}
