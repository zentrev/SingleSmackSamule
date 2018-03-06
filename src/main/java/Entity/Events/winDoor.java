package Entity.Events;

import Entity.Items.JasonNeededItem;
import TileMap.Tile;

import static GameStateManager.RoomState.sam;

public class winDoor extends Event {

    private boolean jasonItem;
    private boolean joseItem;
    private boolean fagotronsItem;

    /**
     * default constructor
     * @param tileMap - tilemap for the room
     * @param eventx - x position relative to tilemap
     * @param eventy - y position relative to tilemap
     */
    public winDoor(Tile[][] tileMap, int eventx, int eventy) {
        super(tileMap, eventx, eventy);
        jasonItem = false;
        joseItem = false;
        fagotronsItem = false;
    }

    @Override
    public void commitEvent() {
        if(sam.getEventAction()){
            for(int i = 0; i < sam.samsItems.size(); i++){
                if(sam.samsItems.get(i) instanceof JasonNeededItem){
                    jasonItem = true;
                }
            }
            if(jasonItem) {
                System.out.println("winner");
            }
        }
        System.out.println("here");
    }

    @Override
    public void commitAttackEvent() {

    }

    @Override
    public void update(){
        if(this.getBoundsInParent().intersects(sam.getBoundsInParent())){
            commitEvent();
        }
    }
}
