package Entity.Monster;

import GameStateManager.Room;
import TileMap.Tile;
import javafx.scene.image.Image;

public class mon1 extends Monster {

    public boolean movingLeft;

    public mon1(Tile[][] tileMap, double monx, double mony) {
        super(tileMap);
        this.x = monx;
        this.y = mony;

        moveSpeed = 4;
        height = 64;
        width = 64;
        collionHeight = height;
        collionWidth = width;
        fallSpeed = .5;
        maxFallSpeed = 8;

        this.setFitHeight(height);
        this.setFitWidth(width);

        this.setTranslateX(x);
        this.setTranslateY(y);

        this.setImage(new Image("/Assets/SpriteSheets/Google_Ultron.png"));

        movingLeft = true;
    }

    public void update(){

        changeVelocity();
        checkCollision();
        moveVelocity();

    }

    public void moveVelocity(){
        if(leftOption && movingLeft){
            x += xVelocity;
            this.setTranslateX(x);
        }
        if(!leftOption && movingLeft){
            movingLeft = false;
        }
        if(rightOption && !movingLeft){
            x+=xVelocity;
            this.setTranslateX(x);
        }
        if(!rightOption && !movingLeft){
            movingLeft = true;
        }
        if(fallingOption){
            y += yVelocity;
            this.setTranslateY(y);
        }
    }

    public void changeVelocity(){
        if(movingLeft){
            xVelocity = moveSpeed*-1;
        } else {
            xVelocity = moveSpeed;
        }
        if (fallingOption) {
            if (yVelocity < maxFallSpeed) {
                yVelocity += fallSpeed;
            }
        } else {
            if(yVelocity >= 0){
                yVelocity = 0;
            }
        }
    }

}
