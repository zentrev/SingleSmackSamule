package Entity.Monster;

import GameStateManager.Room;
import TileMap.Tile;

public class MonsterFactory {
    /**
     * generates a monster
     * @param tileMap - the tile map the monster will be present in
     * @param monsterId - which type of monster the generated monster is
     * @param x - the monster's X spawn position
     * @param y - the monster's Y spawn position
     * @return - null or the newly generated monster
     */
    public Monster getMonster(Tile[][] tileMap, int monsterId, int x, int y){
        switch(monsterId){
            case 1:
                return new mon1(tileMap, x* Room.tileSize, y*Room.tileSize);
            default:
                return null;
        }
    }
}
