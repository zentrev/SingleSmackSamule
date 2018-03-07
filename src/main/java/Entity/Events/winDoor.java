package Entity.Events;

import Entity.Items.JasonNeededItem;
import Entity.Items.stick;
import GameStateManager.*;
import Logic.Game;
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
    public winDoor(Tile[][] tileMap, int eventx, int eventy, Room room) {
        super(tileMap, eventx, eventy, room);
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
                if(sam.samsItems.get(i) instanceof stick){
                    fagotronsItem = true;
                }
            }
            if(jasonItem && fagotronsItem && joseItem) {
                room.getGsm().setState(GameStateManager.STATE.WIN);
            }
        }
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
