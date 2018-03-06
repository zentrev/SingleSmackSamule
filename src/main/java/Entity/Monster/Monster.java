package Entity.Monster;

import Entity.*;
import TileMap.Tile;

public abstract class Monster extends Entity{
    /**
     * a monster that attacks Samuel and will subtract his health upon contact
     * @param tileMap - the part of the tile map that the monster will be placed in
     */
    public Monster(Tile[][] tileMap) {
        super(tileMap);
    }

}
