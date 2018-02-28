package Entity.Items;

import TileMap.Tile;
import javafx.scene.image.Image;

public class Item1 extends DisposableItems {

    public Item1(Tile[][] tm, double itemx, double itemy){
        super(tm);
        this.x = itemx;
        this.y = itemy;

        height = 32;
        width = 32;
        collionHeight = height;
        collionWidth = width;

        this.setFitHeight(height);
        this.setFitWidth(width);

        this.setTranslateX(x);
        this.setTranslateY(y);

        this.setImage(new Image("Assets/SpriteSheets/Google_Ultron.png"));
    }

    public void update() {

        checkCollision();
        moveVelocity();

    }

    public void moveVelocity(){
        if(fallingOption){
            yVelocity = fallSpeed;
            y += yVelocity;
            this.setTranslateY(y);
        }
    }

    public void itemTouched(){
        System.out.println("My son!");
    }

}
