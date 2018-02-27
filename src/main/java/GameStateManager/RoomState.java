package GameStateManager;

import Entity.*;
import javafx.event.Event;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class RoomState extends GameState{

    private ArrayList<Room> rooms = new ArrayList<>();
    private int currentRoom;

    private Samuel sam;

    public RoomState(GameStateManager gsm) {
        super(gsm);
        rooms.add(new Room("/Assets/Maps/room0.map"));
        currentRoom = 0;


    }

    public void changeRoom(int roomNumber){
        currentRoom = roomNumber;
        init(gsm.getGamePane());
    }

    @Override
    public void init(Pane gamePane) {
        rooms.get(currentRoom).init(gamePane);
        sam = new Samuel(rooms.get(currentRoom).getTileMap());
        gsm.getGamePane().getChildren().add(sam);
    }

    @Override
    public void update() {
        sam.update();
    }

    @Override
    public void draw(Pane gamePane) {
        rooms.get(currentRoom).draw(gamePane, sam);
    }

    @Override
    public void handle(Event event) {
        sam.handle(event);
    }
}
