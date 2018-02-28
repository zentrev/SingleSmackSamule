package GameStateManager;

import Entity.*;
import Logic.Game;
import javafx.event.Event;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class RoomState extends GameState {

    private ArrayList<Room> rooms = new ArrayList<>();
    private int currentRoom;

    public static Samuel sam;

    private static final int NUMBEROFROOMS = 2;

    public RoomState(GameStateManager gsm) {
        super(gsm);
        for (int i = 0; i < NUMBEROFROOMS; i++) {
            rooms.add(new Room("/Assets/Maps/room" + i + ".map"));
            System.out.println(rooms.get(i).getTileMap());
        }
        currentRoom = 0;

    }

    public void changeRoom(int roomNumber, int samX, int samY) {
        System.out.println("changing");
        gsm.getGamePane().getChildren().clear();
        currentRoom = roomNumber;
        rooms.get(currentRoom).init(gsm.getGamePane());
        sam.loadSam(rooms.get(currentRoom).getTileMap(), samX, samY);
        rooms.get(currentRoom).init(gsm.getGamePane());
        gsm.getGamePane().getChildren().add(sam);
        if(samX < Game.WIDTH/2 - RoomState.sam.getFitWidth()/2) {
            gsm.getGamePane().setTranslateX(0);
        } else if(samX > Game.WIDTH/2 && samX < rooms.get(currentRoom).getWidth()-Game.WIDTH/2){
            gsm.getGamePane().setTranslateX(samX);
        }
        if(samY < Game.HEIGHT/2 - RoomState.sam.getFitHeight()/2) {
            gsm.getGamePane().setTranslateY(0);
        } else if(samY > Game.HEIGHT/2 && samY < rooms.get(currentRoom).getHeight()-Game.HEIGHT/2){
            gsm.getGamePane().setTranslateY(samY);
        }



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
        rooms.get(currentRoom).update();
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
