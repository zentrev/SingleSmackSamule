package Entity;

import TileMap.Tile;
import javafx.event.Event;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;


public class Samuel extends Entity {
    public Samuel(Tile[][] tileMap) {
        super(tileMap);
        height = 64;
        width = 64;
        x = 100;
        y = 700;
        moveSpeed = 6;
        stopSpeed = 1;
        maxSpeed = 6;
        fallSpeed = .5;
        maxFallSpeed = 8;
        jumpStartVelecity = -13;
        this.setImage(new Image("/Assets/SpriteSheets/Google_Ultron.png"));
        setTranslateX(x);
        setTranslateY(y);
        setFitHeight(height);
        setFitWidth(width);
        collionHeight = height;
        collionWidth = width;
    }

    public void loadSam(Tile[][] tileMap, int samX, int samY){
        this.tileMap = tileMap;
        this.x = samX;
        this.y = samY;

        this.setTranslateX(x);
        this.setTranslateY(y);
    }

    public void update() {
        changeVelocity();
        checkCollision();
        moveVelocity();

    }

    public void checkCollision(){
        super.checkCollision();
    }

    private void moveVelocity() {
        if (fallingOption) {
            setTranslateY(y + yVelocity);
        } else {
            jumpingOption = true;
        }
        if (jumpingOption) {
            if (jumping) {
                yVelocity = jumpStartVelecity;
                setTranslateY(y + yVelocity);
                jumpingOption = false;
            }
        }


        if (right) {
            if (rightOption) {

                setTranslateX(x + xVelocity);
            }
        } else if (left) {
            if (leftOption) {

                setTranslateX(x + xVelocity);
            }
        } else {
            if (xVelocity > 0) {
                xVelocity -= stopSpeed;
            } else if (xVelocity < 0) {
                xVelocity += stopSpeed;
            }
        }

    }

    private void changeVelocity() {





        if (fallingOption) {
            if (yVelocity < maxFallSpeed) {
                yVelocity += fallSpeed;
            }
        } else {
            if(yVelocity >= 0){
                yVelocity = 0;
            }
        }

        if (jumpingOption) {
            if (jumping) {
                yVelocity = jumpStartVelecity;
            }
        }

        if (!upOption) {
            setTranslateY(getTranslateY()-(yVelocity)+5);
            yVelocity = 0;
        }

        if (leftOption) {
            if (left) {
                xVelocity = maxSpeed*-1;
            }
        } else {
            xVelocity = 0;
        }
        if (rightOption) {
            if (right) {
                xVelocity = maxSpeed;
            }
        } else {
            xVelocity = 0;
        }
    }

    public void handle(Event event) {
        if (event.getEventType() == KeyEvent.KEY_PRESSED) {
            KeyEvent keyEvent = (KeyEvent) event;
            switch (keyEvent.getCode()) {
                case D:
                    right = true;
                    break;
                case A:
                    left = true;
                    break;
                case W:
                    jumping = true;
                    break;
            }
        }
        if (event.getEventType() == KeyEvent.KEY_RELEASED) {
            KeyEvent keyEvent = (KeyEvent) event;
            switch (keyEvent.getCode()) {
                case D:
                    right = false;
                    break;
                case A:
                    left = false;
                    break;
                case W:
                    jumping = false;
                    break;
            }
        }
    }

    public void draw(){

    }
}
