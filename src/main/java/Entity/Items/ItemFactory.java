package Entity.Items;

import Entity.Monster.Monster;
import Entity.Monster.mon1;
import GameStateManager.Room;
import TileMap.Tile;

public class ItemFactory {
    public Item getItem(Tile[][] tileMap, int itemId, int x, int y){
        switch(itemId){
            case 1:
                return new Item1(tileMap, x* Room.tileSize, y*Room.tileSize);
            case 2:
                return new JasonNeededItem(tileMap, x*Room.tileSize, y*Room.tileSize);
            default:
                return null;
        }
    }
}
