package Entity.Items;

import TileMap.Tile;
import javafx.scene.image.Image;

import static GameStateManager.RoomState.sam;

public class Microwave extends Item {
    Microwave(Tile[][] tm, double itemx, double itemy){
        super(tm);
        this.x = itemx + 16;
        this.y = itemy + 16;

        this.xSet = (int)itemx;
        this.ySet = (int)itemy;

        height = 32;
        width = 32;
        collionHeight = height;
        collionWidth = width;

        fallSpeed = 1;

        this.setFitHeight(height);
        this.setFitWidth(width);

        this.setTranslateX(x);
        this.setTranslateY(y);

        this.setImage(new Image("Assets/Items/microwave.png"));

        this.isPicked = false;
    }

    /**
     * updates the collision and velocity
     */
    public void update() {

        checkCollision();
        moveVelocity();

    }

    private void moveVelocity(){
        yVelocity = fallSpeed;
        if(fallingOption){
            yVelocity = fallSpeed;
            y += yVelocity;
            this.setTranslateY(y);
        }
    }

    /**
     * checks to see if player has touched the item
     */
    public void itemTouched(){
        isPicked = true;
        sam.addItems(this);
    }

}
