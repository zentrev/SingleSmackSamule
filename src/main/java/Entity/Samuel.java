package Entity;

import Entity.Items.Item;
import GameStateManager.Room;
import GameStateManager.RoomState;
import Logic.Game;
import TileMap.Tile;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.Event;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;


public class Samuel extends Entity {

    private int currentAction;
    private RoomState roomState;

    private Pane UIPane;

    public int HEALTH = 3;
    public ArrayList<Item> samsItems;

    private static final int WALKING = 0;
    private static final int ATACKING = 1;
    private static final int FALLING = 2;
    private static final int IDLE = 3;
    private static final int DEAD = 4;

    private long startTime;

    private boolean flinching;
    public boolean invince;

    public Samuel(Tile[][] tileMap,RoomState roomState) {
        super(tileMap);
        this.roomState = roomState;
        UIPane = Game.UIPane;
        samsItems = new ArrayList<>();
        height = 64;
        width = 64;
        x = 400;
        y = 400;
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
                4,3,1,1,1
        };
        try {
            BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Assets/SpriteSheets/sam.png"));
            sprites = new ArrayList<Image[]>();
            for (int i = 0; i < numFrames.length; i++) {
                Image[] sprit = new Image[numFrames[i]];
                for (int j = 0; j < numFrames[i]; j++) {
                    if(j != 4) {
                        sprit[j] = SwingFXUtils.toFXImage(spritesheet.getSubimage(j * 64, i * 64, 64, 64), null);
                    } else {
                        sprit[j] = SwingFXUtils.toFXImage(spritesheet.getSubimage(j * 64, i * 64, 80, 64), null);
                    }
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
        if(HEALTH > 0) {
            changeVelocity();
            checkCollision();
            checkAttacking();
            checkFlinching();
            moveVelocity();
            updateUI();
        }
    }

    private void updateUI(){
        UIPane.getChildren().clear();
        //add things to ui
        UIPane.getChildren().addAll();
    }

    private void checkAttacking() {
        if(atacking){
            long elapsed = (System.nanoTime() - startTime)/1000000;
            long delay = 400;
            if(animation.hasPlayedOnce() && currentAction == ATACKING) {
                currentAction = IDLE;
                animation.setFrames(sprites.get(IDLE));
                animation.setDelay(150);
            }
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
            bound.setWidth(Room.tileSize/1.1);
        }
        if(!facingRight){
            bound.setTranslateX(this.getTranslateX()+this.width/2-(Room.tileSize/1.1));
            bound.setTranslateY(this.getTranslateY());
            bound.setHeight(this.height);
            bound.setWidth(Room.tileSize/1.1);
        }

        roomState.getRooms().get(roomState.getCurrentRoom()).checkMonsterCollision(bound);

    }

    public void checkCollision() {
        super.checkCollision();
    }

    private void moveVelocity() {
        if (fallingOption) {
            setTranslateY(y + yVelocity);
            if(currentAction != ATACKING) {
                currentAction = FALLING;
                this.animation.setFrames(sprites.get(FALLING));
            }
        } else {
            jumpingOption = true;
            if(currentAction != WALKING && currentAction != ATACKING) {
                currentAction = IDLE;
                animation.setFrames(sprites.get(currentAction));
            }
        }
        if (!atacking && !flinching) {
            if (jumpingOption) {
                if (jumping) {
                    yVelocity = jumpStartVelecity;
                    setTranslateY(y + yVelocity);
                    jumpingOption = false;
                }
            }
            xMovement();
        } else {
            if(jumpingOption){
                jumpingOption = false;
            }
            if (fallingOption) {
                xMovement();
            }
        }
    }

    private void xMovement() {
        if (right) {
            if (rightOption) {
                facingRight = true;
                setTranslateX(x + xVelocity);
                if(currentAction != WALKING && currentAction != FALLING && currentAction != ATACKING) {
                    this.currentAction = WALKING;
                    animation.setFrames(sprites.get(WALKING));
                }
            }
        } else if (left) {
            if (leftOption) {
                facingRight = false;
                setTranslateX(x + xVelocity);
                if(currentAction != WALKING && currentAction != FALLING && currentAction != ATACKING) {
                    this.currentAction = WALKING;
                    animation.setFrames(sprites.get(WALKING));
                }

            }
        } else {
            if (xVelocity > 0) {
                if(currentAction != IDLE) {
                    if(currentAction != ATACKING) {
                        this.currentAction = IDLE;
                        animation.setFrames(sprites.get(IDLE));
                    }
                }
                xVelocity -= stopSpeed;
            } else if (xVelocity < 0) {
                if(currentAction != IDLE) {
                    if(currentAction != ATACKING) {
                        this.currentAction = IDLE;
                        animation.setFrames(sprites.get(IDLE));
                    }
                }
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
                yVelocity = .5;
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
                    currentAction = ATACKING;
                    animation.setFrames(sprites.get(ATACKING));
                    animation.setDelay(100);
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

    public void flinch(Long startTime){
        this.startTime = startTime;
        flinching = true;
        invince = true;
    }

    public void checkFlinching(){
        int flinchTime = 400;
        int invinceTime = 1000;
        long elapse = (System.nanoTime()-startTime)/1000000;
        if(elapse>flinchTime){
            flinching = false;
        } if(elapse>invinceTime){
            invince = false;
        }
    }
}
