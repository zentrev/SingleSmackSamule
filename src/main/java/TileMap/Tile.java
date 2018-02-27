package TileMap;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Tile extends ImageView{

    private TileType tileType;

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

    public TileType getTileType() {
        return tileType;
    }

    public void setTileType(TileType tileType) {
        this.tileType = tileType;
    }
}
