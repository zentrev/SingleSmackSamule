package Entity.Events;

import GameStateManager.Room;
import TileMap.Tile;
import TileMap.TileType;
import javafx.scene.image.Image;

import static GameStateManager.RoomState.sam;

public class Event4 extends Event{
    /**
     * default constructor
     * @param tm - tilemap for the room
     * @param eventx - x position to place the event relative to the tilemap
     * @param eventy - y position to place the event relative to the tilemap
     */
    public Event4(Tile[][] tm, int eventx, int eventy, Room room){
        super(tm,eventx,eventy,room);
        this.setImage(new Image("Assets/Events/lever.png"));
        room.getGsm().getGamePane().getChildren().add(this);
    }

    @Override
    public void commitEvent() {
    }

    @Override
    public void commitAttackEvent() {
        Image bg = room.getTileSheet().get(5);
        tileMap[23][3].setImage(bg);
        tileMap[23][3].setTileType(TileType.NORMAL);
        sam.setTileMap(tileMap);
    }
}
