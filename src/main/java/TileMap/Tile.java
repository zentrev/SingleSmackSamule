package TileMap;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Tile extends ImageView{

    private TileType tileType;

    /**
     * the different types of tiles that appear in the level
     * @param image - the image used for the tile
     * @param x - the tile's x postition
     * @param y - the tile's y position
     * @param tilesize - the size of the tile
     * @param tileType - the type of the tile
     */
    public Tile(Image image, int x, int y, int tilesize, TileType tileType){
        super();
        this.setImage(image);
        this.setTranslateX(x);
        this.setTranslateY(y);
        this.minHeight(tilesize);
        this.minWidth(tilesize);
        this.prefHeight(tilesize);
        this.prefWidth(tilesize);
        this.maxHeight(tilesize);
        this.maxWidth(tilesize);
        this.setFitHeight(tilesize);
        this.setFitWidth(tilesize);
        this.setTileType(tileType);
    }

    /**
     * gets the tile that appears in the level
     * @return - the created tile
     */
    public TileType getTileType() {
        return tileType;
    }

    /**
     * sets the tile in the tile map
     * @param tileType - the set tile
     */
    public void setTileType(TileType tileType) {
        this.tileType = tileType;
    }
}
