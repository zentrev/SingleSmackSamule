package Entity.Items;

import Entity.Monster.Monster;
import Entity.Monster.mon1;
import GameStateManager.Room;
import TileMap.Tile;

public class ItemFactory {
    /**
     * generates an item
     *
     * @param tileMap - the tile map the item will spawn in
     * @param itemId  - the type of item being generated
     * @param x       - the item's X spawn position
     * @param y       - the item's Y spawn position
     * @return - null or the newly generated item
     */
    public Item getItem(Tile[][] tileMap, int itemId, int x, int y) {
        switch (itemId) {
            case 1:
                return new Item1(tileMap, x * Room.tileSize, y * Room.tileSize);
            case 2:
                return new JasonNeededItem(tileMap, x * Room.tileSize, y * Room.tileSize);
            case 3:
                return new stick(tileMap, x * Room.tileSize, y * Room.tileSize);
            default:
                return null;
        }
    }
}
