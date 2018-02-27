package GameStateManager;

import Entity.Samuel;
import Logic.Game;
import TileMap.Tile;
import TileMap.TileType;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Room {

    private Tile[][] tileMap;

    private String mapPath;
    private String tileSheetPath;

    private int numCols;
    private int numRows;

    private int width;
    private int height;

    private BufferedImage tileset;
    private int numTilesAcross;
    private int numTilesDown;

    private boolean active = false;

    private HashMap<Integer, Image> tileSheet;

    // Tile Size
    public static final int tileSize = 80;

    public Room(String mapPath) {
        this.mapPath = mapPath;
    }

    public void init(Pane gamePane) {

        try {
            InputStream in = getClass().getResourceAsStream(mapPath);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
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
                    TileType type = null;
                    if (chips[col] < numTilesAcross) {
                        type = TileType.NORMAL;
                    } else if (chips[col] >= numTilesAcross && chips[col] <= numTilesAcross * 2) {
                        type = TileType.BLOCKED;
                    }
                    tileMap[row][col] = new Tile(tileSheet.get(chips[col]), col * tileSize, row * tileSize, tileSize, type);
                    gamePane.getChildren().add(tileMap[row][col]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void stampTileSheet() {
        try {
            tileSheet = new HashMap<Integer, Image>();
            tileset = ImageIO.read(getClass().getResourceAsStream(tileSheetPath));
            numTilesAcross = (int) tileset.getWidth() / tileSize;
            numTilesDown = (int) tileset.getHeight() / tileSize;
            Image subimage;
            System.out.println(numTilesAcross + " : " + numTilesDown);
            for (int col = 0; col < numTilesDown; col++) {
                for (int row = 0; row < numTilesAcross; row++) {
                    subimage = SwingFXUtils.toFXImage(tileset.getSubimage(col * tileSize, row * tileSize, tileSize, tileSize), null);
                    tileSheet.put((col) + row * numTilesAcross, subimage);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Pane gamePane, Samuel sam) {

        double samX = sam.getTranslateX();
        double samY = sam.getTranslateY();

        if(samX > Game.WIDTH/2-sam.getFitWidth()/2){
            gamePane.setTranslateX((samX*-1)+Game.WIDTH/2-sam.getFitWidth()/2);
        }
        if(samX > gamePane.getWidth()-Game.WIDTH/2-(sam.getFitWidth()/2) && active) {
            gamePane.setTranslateX((gamePane.getWidth()*-1)+Game.WIDTH);
        }
        if(samY > Game.HEIGHT/2-sam.getFitHeight()/2){
            gamePane.setTranslateY((samY*-1)+Game.HEIGHT/2-sam.getFitHeight()/2);
        }
        if(samY > gamePane.getHeight()-Game.HEIGHT/2-(sam.getFitHeight()/2) && active) {
            gamePane.setTranslateY((gamePane.getHeight()*-1)+Game.HEIGHT);
        }
        active = true;
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
}
