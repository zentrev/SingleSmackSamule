package Entity.Events;

import GameStateManager.Room;
import TileMap.Tile;

/**
 * gets the instance the the Events
 */
public class EventFactory {
    /**
     * gets the events with a tile array event id x and y
     * @param tileMap
     * @param eventId
     * @param x
     * @param y
     * @return the tileMap, room tile size for x and y.
     */
    public Event getEvent(Tile[][] tileMap, int eventId, int x, int y, Room room){
        switch(eventId){
            case 1:
                return new Event1(tileMap, x* Room.tileSize, y*Room.tileSize, room);
            case 2:
                return new winDoor(tileMap, x* Room.tileSize, y*Room.tileSize, room);
            case 3:
                return new Event3(tileMap, x* Room.tileSize, y*Room.tileSize, room);
            case 4:
                return new Event4(tileMap, x*Room.tileSize, y*Room.tileSize, room);
            case 5:
                return new snailGen(tileMap, x*Room.tileSize, y*Room.tileSize, room);
            default:
                return null;
        }
    }
}
