package GameStateManager;

import Entity.Events.DoorWay;
import Entity.Events.Event;
import Entity.Events.EventFactory;
import Entity.Items.Item;
import Entity.Items.ItemFactory;
import Entity.Monster.Monster;
import Entity.Monster.MonsterFactory;
import Entity.Samuel;
import Logic.Game;
import TileMap.Tile;
import TileMap.TileType;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static GameStateManager.RoomState.sam;

public class Room {

    private Tile[][] tileMap;

    private GameStateManager gsm;

    private String mapPath;
    private String tileSheetPath;
    private String backgroundPath;

    private int numCols;
    private int numRows;

    private int width;
    private int height;

    private BufferedImage tileset;
    private int numTilesAcross;
    private int numTilesDown;

    private Image background;

    private boolean active = false;

    private HashMap<Integer, Image> tileSheet;

    private ArrayList<DoorWay> doorWays;
    private int numDoors;
    private ArrayList<Monster> monsters;
    private int numOfMonsters;
    private ArrayList<Item> items;
    private int numOfItems;
    private ArrayList<Item> collectedItems = new ArrayList<>();
    private ArrayList<Event> events;
    private int numOfEvents;
    private ArrayList<Event> activatedEvents = new ArrayList<>();

    // Tile Size
    public static final int tileSize = 80;

    Room(String mapPath, GameStateManager gsm) {
        this.mapPath = mapPath;
        this.gsm = gsm;
    }

    void init(Pane gamePane) {

        try {
            InputStream in = getClass().getResourceAsStream(mapPath);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            backgroundPath = br.readLine();
            background = new Image(getClass().getResourceAsStream(backgroundPath));
            gsm.setBackground(background);
            tileSheetPath = br.readLine();
            stampTileSheet();
            numCols = Integer.parseInt(br.readLine());
            numRows = Integer.parseInt(br.readLine());
            tileMap = new Tile[numRows][numCols];
            width = numCols * tileSize;
            height = numRows * tileSize;


            gamePane.setPrefWidth(width);
            gamePane.setPrefHeight(height);


            String delims = "\\s+";
            for (int row = 0; row < numRows; row++) {
                String line = br.readLine();
                String[] tokens = line.split(delims);
                int[] chips = new int[tokens.length];
                for (int i = 0; i < tokens.length; i++) {
                    chips[i] = Integer.parseInt(tokens[i]);
                }
                for (int col = 0; col < numCols; col++) {
                    TileType type;
                    if (chips[col] < numTilesAcross) {
                        type = TileType.NORMAL;
                    } else if (chips[col] >= numTilesAcross && chips[col] <= numTilesAcross * 2) {
                        type = TileType.BLOCKED;
                    } else {
                        //option to add more tileTypes
                        type = TileType.NORMAL;
                    }
                    tileMap[row][col] = new Tile(tileSheet.get(chips[col]), col * tileSize, row * tileSize, tileSize, type);
                    gamePane.getChildren().add(tileMap[row][col]);
                }
            }

            doorWays = new ArrayList<>();
            numDoors = Integer.parseInt(br.readLine());
            for (int door = 0; door < numDoors; door++) {
                String doorLine = br.readLine();
                String[] tokens = doorLine.split(delims);
                int roomDest = Integer.parseInt(tokens[0]);
                int doorx = Integer.parseInt(tokens[1]);
                int doory = Integer.parseInt(tokens[2]);
                int samx = Integer.parseInt(tokens[3]);
                int samy = Integer.parseInt(tokens[4]);
                DoorWay thisDoor = new DoorWay(tileMap, roomDest, doorx, doory, samx, samy);
                doorWays.add(thisDoor);
            }

            MonsterFactory MF = new MonsterFactory();

            monsters = new ArrayList<>();
            numOfMonsters = Integer.parseInt(br.readLine());
            for (int monster = 0; monster < numOfMonsters; monster++) {
                String monsterLine = br.readLine();
                String[] tokens = monsterLine.split(delims);
                int monsterNum = Integer.parseInt(tokens[0]);
                int monx = Integer.parseInt(tokens[1]);
                int mony = Integer.parseInt(tokens[2]);
                Monster thisMonster = MF.getMonster(tileMap, monsterNum, monx, mony);
                monsters.add(thisMonster);
                gamePane.getChildren().add(thisMonster);
            }

            ItemFactory IF = new ItemFactory();

            items = new ArrayList<>();
            numOfItems = Integer.parseInt(br.readLine());
            for (int item = 0; item < numOfItems; item++) {
                String itemLine = br.readLine();
                String[] tokens = itemLine.split(delims);
                int itemNum = Integer.parseInt(tokens[0]);
                int itemx = Integer.parseInt(tokens[1]);
                int itemy = Integer.parseInt(tokens[2]);
                Item thisItem = IF.getItem(tileMap, itemNum, itemx, itemy);
                if (!(collectedItems.contains(thisItem))) {
                    items.add(thisItem);
                    gamePane.getChildren().add(thisItem);
                    thisItem.setRoom(this);
                }

            }

            EventFactory EF = new EventFactory();


            events = new ArrayList<>();
            numOfEvents = Integer.parseInt(br.readLine());
            for (int item = 0; item < numOfEvents; item++) {
                String eventLine = br.readLine();
                String[] tokens = eventLine.split(delims);
                int eventNum = Integer.parseInt(tokens[0]);
                int eventx = Integer.parseInt(tokens[1]);
                int eventy = Integer.parseInt(tokens[2]);
                Event thisEvent = EF.getEvent(tileMap, eventNum, eventx, eventy, this);
                events.add(thisEvent);
                events.removeAll(activatedEvents);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void stampTileSheet() {
        try {
            tileSheet = new HashMap<>();
            tileset = ImageIO.read(getClass().getResourceAsStream(tileSheetPath));
            numTilesAcross = tileset.getWidth() / tileSize;
            numTilesDown = tileset.getHeight() / tileSize;
            Image subimage;
            for (int col = 0; col < numTilesDown; col++) {
                for (int row = 0; row < numTilesAcross; row++) {
                    subimage = SwingFXUtils.toFXImage(tileset.getSubimage(row * tileSize, col * tileSize, tileSize, tileSize), null);
                    tileSheet.put((row) + col * numTilesAcross, subimage);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void checkMonsterCollision(Rectangle attackBound) {
        List<Monster> toRemove = new ArrayList<>();
        for (Monster monster : monsters) {
            if (monster.getBoundsInParent().intersects(attackBound.getBoundsInParent())) {
                toRemove.add(monster);
                gsm.getGamePane().getChildren().remove(monster);
            }
        }
        monsters.removeAll(toRemove);
    }

    public void checkEventCollision(Rectangle attackBound) {
        for (Event event : events) {
            if (event.getBoundsInParent().intersects(attackBound.getBoundsInParent())) {
                event.commitAttackEvent();
            }
        }
    }

    void draw(Pane gamePane, Samuel sam) {

        double samX = sam.getTranslateX();
        double samY = sam.getTranslateY();

        if (samX > Game.WIDTH / 2 - sam.getFitWidth() / 2) {
            gamePane.setTranslateX((samX * -1) + Game.WIDTH / 2 - sam.getFitWidth() / 2);
        }
        if (samX > gamePane.getWidth() - Game.WIDTH / 2 - (sam.getFitWidth() / 2) && active) {
            gamePane.setTranslateX((gamePane.getWidth() * -1) + Game.WIDTH);
        }
        if (samY > Game.HEIGHT / 2 - sam.getFitHeight() / 2) {
            gamePane.setTranslateY((samY * -1) + Game.HEIGHT / 2 - sam.getFitHeight() / 2);
        }
        if (samY > gamePane.getHeight() - Game.HEIGHT / 2 - (sam.getFitHeight() / 2) && active) {
            gamePane.setTranslateY((gamePane.getHeight() * -1) + Game.HEIGHT);
        }
        active = true;
    }

    public void update() {
        for (DoorWay door : doorWays) {
            if (door.getBoundsInParent().intersects(sam.getBoundsInParent())) {
                door.commitEvent();
            }
        }
        for (Monster monster : monsters) {
            monster.update();
        }

        for (Item item : items) {
            item.update();
        }

        List<Item> toRemove = new ArrayList<>();
        for (Item item : items) {
            if (!item.isPicked()) {
                if (item.getBoundsInParent().intersects(sam.getBoundsInParent())) {
                    toRemove.add(item);
                    item.itemTouched();
                }
            }
        }
        items.removeAll(toRemove);
        collectedItems.addAll(toRemove);

        for (Event event : events) {
            event.update();
        }

    }


    public Tile[][] getTileMap() {
        return tileMap;
    }

    public void setTileMap(Tile[][] tileMap) {
        this.tileMap = tileMap;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public static int getTileSize() {
        return tileSize;
    }

    public HashMap<Integer,Image> getTileSheet(){
        return tileSheet;
    }

    public GameStateManager getGsm() {
        return gsm;
    }

    public void addMon(Monster mon){
        monsters.add(mon);
        gsm.getGamePane().getChildren().add(mon);
    }
}
