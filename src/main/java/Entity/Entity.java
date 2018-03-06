package Entity;

import GameStateManager.Room;
import TileMap.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

abstract public class Entity extends ImageView {

    protected Tile[][] tileMap;

    protected double x;
    protected double y;
    protected double xVelocity;
    protected double yVelocity;
    protected double xtemp;
    protected double ytemp;
    protected int currCol;
    protected int currRow;

    protected int height;
    protected int width;

    protected int collionHeight;
    protected int collionWidth;

    protected boolean facingRight;
    protected boolean left;
    protected boolean right;
    protected boolean up;
    protected boolean down;
    protected boolean jumping;
    protected boolean atacking;

    protected boolean falling;
    protected boolean leftOption;
    protected boolean rightOption;
    protected boolean upOption;
    protected boolean downOption;
    protected boolean jumpingOption;
    protected boolean fallingOption;

    protected double moveSpeed;
    protected double maxSpeed;
    protected double stopSpeed;
    protected double fallSpeed;
    protected double maxFallSpeed;

    protected double jumpStartVelecity;

    protected Animator animation;
    protected ArrayList<Image[]> sprites;
    protected int[] numFrames;

    /**
     * default constructor
     * @param tileMap - the tilemap of the relative map
     */
    public Entity(Tile[][] tileMap) {
        this.setTileMap(tileMap);
    }

    /**
     * returns the tilemap connected to this entity
     * @return
     */
    public Tile[][] getTileMap() {
        return tileMap;
    }

    /**
     *
     * @param tileMap
     */
    public void setTileMap(Tile[][] tileMap) {
        this.tileMap = tileMap;
    }

    /**
     * checks the collision of the velocity of this entity
     */
    public void checkCollision() {
        fallingOption = true;
        upOption = true;
        leftOption = true;
        rightOption = true;

        y = this.getTranslateY();
        x = this.getTranslateX();

        currRow = (int) y / Room.tileSize;
        currCol = (int) x / Room.tileSize;

        ytemp = y + yVelocity;

        if (xVelocity > 0) {
            xtemp = x + moveSpeed;
        } else if (xVelocity < 0) {
            xtemp = x - moveSpeed;
        } else {
            xtemp = x;
        }


        for (int row = 0; row < tileMap.length; row++) {
            for (int col = 0; col < tileMap[row].length; col++) {

                if (tileMap[row][col].getBoundsInParent().intersects(x, ytemp, collionWidth, collionHeight)) {
                    if (tileMap[row][col].getTileType() == TileType.BLOCKED) {
                        if (yVelocity > 0) {
                            fallingOption = false;
                            jumpingOption = true;
                        } else if (yVelocity < 0) {
                            upOption = false;
                        }
                    }
                }
                if (tileMap[row][col].getBoundsInParent().intersects(xtemp, y, collionWidth, collionHeight)) {
                    if (tileMap[row][col].getTileType() == TileType.BLOCKED) {
                        if (xVelocity > 0) {
                            rightOption = false;
                        } else if (xVelocity < 0) {
                            leftOption = false;
                        }
                    }
                }
            }
        }
    }

    /**
     * updates all needed things for the entity
     */
    public void update() {
        checkCollision();
    }

    /**
     * draws the entity
     */
    public void draw() {

    }

}
