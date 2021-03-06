package Entity.Items;

import GameStateManager.RoomState;
import TileMap.Tile;
import javafx.scene.image.Image;

import static GameStateManager.RoomState.sam;

public class Item1 extends Item {

    /**
     * default constructor
     * @param  - tilemap for room
     * @param itemx - x pos relative to tilemap
     * @param itemy - y pos relative to tilemap
     */
    Item1(Tile[][] tm, double itemx, double itemy){
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

        this.setImage(new Image("Assets/items/health.png"));

        this.isPicked = false;
    }


    @Override
    public void update() {

        checkCollision();
        moveVelocity();

    }

    /**
     * move the item by velocity
     */
    private void moveVelocity(){
        yVelocity = fallSpeed;
        if(fallingOption){
            yVelocity = fallSpeed;
            y += yVelocity;
            this.setTranslateY(y);
        }
    }

    @Override
    public void itemTouched(){
        System.out.println("My son!");
        this.setVisible(false);
        isPicked = true;
        sam.damage(-1);
    }

}
