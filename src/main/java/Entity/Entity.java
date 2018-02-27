package Entity;

import GameStateManager.Room;
import TileMap.*;
import javafx.scene.image.ImageView;

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

    public Entity(Tile[][] tileMap) {
        this.setTileMap(tileMap);
    }

    public Tile[][] getTileMap() {
        return tileMap;
    }

    public void setTileMap(Tile[][] tileMap) {
        this.tileMap = tileMap;
    }

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

    public void update() {
        checkCollision();
    }

    public void draw() {

    }


}
