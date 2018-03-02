package Entity.Events;

import GameStateManager.Room;
import TileMap.Tile;

public class EventFactory {
    public Event getEvent(Tile[][] tileMap, int eventId, int x, int y){
        switch(eventId){
            case 1:
                return new Event1(tileMap, x* Room.tileSize, y*Room.tileSize);
            default:
                return null;
        }
    }
}
