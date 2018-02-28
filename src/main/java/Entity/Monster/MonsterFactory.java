package Entity.Monster;

import GameStateManager.Room;
import TileMap.Tile;

public class MonsterFactory {
    public Monster getMonster(Tile[][] tileMap, int monsterId, int x, int y){
        switch(monsterId){
            case 1:
                return new mon1(tileMap, x* Room.tileSize, y*Room.tileSize);
            default:
                return null;
        }
    }
}
