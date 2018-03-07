package Entity.Events;

import Entity.Monster.Monster;
import Entity.Monster.MonsterFactory;
import GameStateManager.Room;
import TileMap.Tile;

public class snailGen extends Event {

    private long startTime;
    private long elapse;
    private int delay;

    public snailGen(Tile[][] tileMap, int x, int y, Room room) {
        super(tileMap, x, y, room);
        startTime = System.nanoTime();
        delay = 2000;
    }

    @Override
    public void commitEvent() {

    }

    @Override
    public void commitAttackEvent() {

    }

    public void update(){
        elapse = (System.nanoTime()-startTime)/1000000;
        if(elapse > delay){
            MonsterFactory mf = new MonsterFactory();
            Monster snail = mf.getMonster(tileMap,1,8,4);
            room.addMon(snail);
            startTime = System.nanoTime();
            if(delay < 3000){
                delay+=50;
            } else {
                delay += 100;
            }
        }
    }
}
