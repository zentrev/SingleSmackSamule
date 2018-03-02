package Entity;

import GameStateManager.Room;
import GameStateManager.RoomState;
import TileMap.Tile;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.Event;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;


public class Samuel extends Entity {

    private int currentAction;
    private RoomState roomState;

    public static int HEALTH = 3;

    private static final int IDLE = 0;
    private static final int WALKING = 1;
    private static final int ATACKING = 2;
    private static final int FALLING = 3;

    private long startTime;

    public Samuel(Tile[][] tileMap,RoomState roomState) {
        super(tileMap);
        this.roomState = roomState;
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
        setTranslateX(x);
        setTranslateY(y);
        setFitHeight(height);
        setFitWidth(width);
        collionHeight = height;
        collionWidth = width;
        atacking = false;
        numFrames = new int[]{
                4
        };
        try {
            BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Assets/SpriteSheets/testSam.png"));
            sprites = new ArrayList<Image[]>();
            for (int i = 0; i < numFrames.length; i++) {
                Image[] sprit = new Image[numFrames[i]];
                for (int j = 0; j < numFrames[i]; j++) {
                    sprit[j] = SwingFXUtils.toFXImage(spritesheet.getSubimage(j * 64, i * 64, 64, 64), null);
                }
                sprites.add(sprit);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.animation = new Animator();
        this.currentAction = IDLE;
        animation.setFrames(sprites.get(IDLE));
        animation.setDelay(150);
        this.setImage(animation.getImage());

    }

    public void loadSam(Tile[][] tileMap, int samX, int samY) {
        this.tileMap = tileMap;
        this.x = samX;
        this.y = samY;

        this.setTranslateX(x);
        this.setTranslateY(y);
    }

    public void update() {
        changeVelocity();
        checkCollision();
        checkAttacking();
        moveVelocity();

    }

    private void checkAttacking() {
        if(atacking){
            long elapsed = (System.nanoTime() - startTime)/1000000;
            long delay = 400;
            if(elapsed>delay){
                atacking = false;
            }
        }
    }

    private void heAttack(){
        Rectangle bound = new Rectangle();
        if(facingRight){
            bound.setTranslateX(this.getTranslateX()+this.width/2);
            bound.setTranslateY(this.getTranslateY());
            bound.setHeight(this.height);
            bound.setWidth(Room.tileSize);
        }
        if(!facingRight){
            bound.setTranslateX(this.getTranslateX()-this.width/2-Room.tileSize);
            bound.setTranslateY(this.getTranslateY());
            bound.setHeight(this.height);
            bound.setWidth(Room.tileSize);
        }

        roomState.getRooms().get(roomState.getCurrentRoom()).checkMonsterCollision(bound);

    }

    public void checkCollision() {
        super.checkCollision();
    }

    private void moveVelocity() {
        if (fallingOption) {
            setTranslateY(y + yVelocity);
        } else {
            jumpingOption = true;
        }
        if (!atacking) {
            if (jumpingOption) {
                if (jumping) {
                    yVelocity = jumpStartVelecity;
                    setTranslateY(y + yVelocity);
                    jumpingOption = false;
                }
            }
            xMovment();
        } else {
            if(jumpingOption){
                jumpingOption = false;
            }
            if (fallingOption) {
                xMovment();
            }
        }
    }

    private void xMovment() {
        if (right) {
            if (rightOption) {
                facingRight = true;
                setTranslateX(x + xVelocity);
            }
        } else if (left) {
            if (leftOption) {
                facingRight = false;
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
            if (yVelocity >= 0) {
                yVelocity = 0;
            }
        }

        if (jumpingOption) {
            if (jumping) {
                yVelocity = jumpStartVelecity;
            }
        }

        if (!upOption) {
            setTranslateY(getTranslateY() - (yVelocity) + 5);
            yVelocity = 0;
        }

        if (leftOption) {
            if (left) {
                xVelocity = maxSpeed * -1;
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
                case K:
                    atacking = true;
                    startTime = System.nanoTime();
                    heAttack();
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

    public void draw() {
        this.animation.update();
        this.setImage(animation.getImage());
        if(facingRight){
            this.setScaleX(-1);
        } else {
            this.setScaleX(1);
        }
    }
}
