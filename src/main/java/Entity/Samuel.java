package Entity;

import Entity.Items.Item;
import GameStateManager.Room;
import GameStateManager.RoomState;
import Logic.Game;
import TileMap.Tile;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.Event;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;


public class Samuel extends Entity {

    private int currentAction;
    private RoomState roomState;

    private Pane UIPane;

    private ImageView H1;
    private ImageView H2;
    private ImageView H3;


    public int HEALTH = 3;
    public ArrayList<Item> samsItems;

    private static final int WALKING = 0;
    private static final int ATACKING = 1;
    private static final int FALLING = 2;
    private static final int IDLE = 3;
    private static final int DEAD = 4;

    private long startTime;
    private long flinchTime;
    int delay = 0;

    long deathStart = 0;
    long deathElapse = 0;

    private boolean flinching;
    public boolean invince;

    private boolean eventAction;

    /**
     * default constructor
     * @param tileMap - the part of the tile map Samuel will spawn in
     * @param roomState - the room Samuel is in
     */
    public Samuel(Tile[][] tileMap, RoomState roomState) {
        super(tileMap);
        this.roomState = roomState;
        H1 = new ImageView();
        H2 = new ImageView();
        H3 = new ImageView();
        H3.setImage(new Image(getClass().getResourceAsStream("/Assets/Items/health.png")));
        H2.setImage(new Image(getClass().getResourceAsStream("/Assets/Items/health.png")));
        H1.setImage(new Image(getClass().getResourceAsStream("/Assets/Items/health.png")));
        H1.setTranslateX(Game.WIDTH / 2 - 56);
        H1.setTranslateY(15);
        H2.setTranslateX(Game.WIDTH / 2 - 16);
        H2.setTranslateY(15);
        H3.setTranslateX(Game.WIDTH / 2 + 24);
        H3.setTranslateY(15);
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
        eventAction = false;
        setTranslateX(x);
        setTranslateY(y);
        setFitHeight(height);
        setFitWidth(width);
        collionHeight = height;
        collionWidth = width;
        atacking = false;
        numFrames = new int[]{
                4, 3, 1, 1, 1
        };
        try {
            BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Assets/SpriteSheets/sam.png"));
            sprites = new ArrayList<Image[]>();
            for (int i = 0; i < numFrames.length; i++) {
                Image[] sprit = new Image[numFrames[i]];
                for (int j = 0; j < numFrames[i]; j++) {
                    if (j != 4) {
                        sprit[j] = SwingFXUtils.toFXImage(spritesheet.getSubimage(j * 64, i * 64, 64, 64), null);
                    } else {
                        sprit[j] = SwingFXUtils.toFXImage(spritesheet.getSubimage(j * 80, i * 64, 80, 64), null);
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

    /**
     * loads a new instance of Samuel
     * @param tileMap - the part of the tile map Samuel is in
     * @param samX - Samuel's X position
     * @param samY - Samuel's Y position
     */
    public void loadSam(Tile[][] tileMap, int samX, int samY) {
        this.tileMap = tileMap;
        this.x = samX;
        this.y = samY;

        this.setTranslateX(x);
        this.setTranslateY(y);
    }

    /**
     * updates Samuel's state
     */
    public void update() {
        if (HEALTH > 0) {
            changeVelocity();
            checkCollision();
            checkAttacking();
            checkFlinching();
            moveVelocity();
            updateUI();
            deathStart = System.nanoTime();
        } else {
            changeVelocity();
            checkCollision();
            moveVelocity();
            animation.setFrames(sprites.get(DEAD));
            animation.update();
            deathElapse = (System.nanoTime()-deathStart)/1000000;
            if(2000 < deathElapse) {
                roomState.samDies();
            }
        }
    }

    /**
     * adds an item to Samuel's inventory if he picks one up
     * @param item - the item Samuel picked up
     */
    public void addItems(Item item){
        samsItems.add(item);
    }

    /**
     * updates the HUD
     */
    private void updateUI() {
        UIPane.getChildren().clear();
        UIPane.getChildren().addAll(H1, H2, H3);
        for (int i = 0; i < samsItems.size(); i++) {
            samsItems.get(i).setTranslateX(20);
            samsItems.get(i).setTranslateY(35 * (i+1));
            UIPane.getChildren().add(samsItems.get(i));
        }
    }

    /**
     * checks if Samuel is attacking
     */
    private void checkAttacking() {
        if (atacking) {
            long elapsed = (System.nanoTime() - startTime) / 1000000;
            long delay = 400;
            if (animation.hasPlayedOnce() && currentAction == ATACKING) {
                currentAction = IDLE;
                animation.setFrames(sprites.get(IDLE));
                animation.setDelay(150);
            }
            if (elapsed > delay) {
                atacking = false;
            }
        }
    }

    /**
     * sends out Samuel's attack if he is attacking
     */
    private void heAttack() {
        if(HEALTH != 0) {
            Rectangle bound = new Rectangle();
            if (facingRight) {
                bound.setTranslateX(this.getTranslateX() + this.width / 2);
                bound.setTranslateY(this.getTranslateY());
                bound.setHeight(this.height);
                bound.setWidth(Room.tileSize / 1.1);
            }
            if (!facingRight) {
                bound.setTranslateX(this.getTranslateX() + this.width / 2 - (Room.tileSize / 1.1));
                bound.setTranslateY(this.getTranslateY());
                bound.setHeight(this.height);
                bound.setWidth(Room.tileSize / 1.1);
            }

            roomState.getRooms().get(roomState.getCurrentRoom()).checkMonsterCollision(bound);
            roomState.getRooms().get(roomState.getCurrentRoom()).checkEventCollision(bound);
        }
    }

    /**
     * checks if Samuel collides with a blocked tile
     */
    public void checkCollision() {
        super.checkCollision();
    }

    /**
     * moves Samuel by his velocity
     */
    private void moveVelocity() {
        if (fallingOption) {
            setTranslateY(y + yVelocity);
            if (currentAction != ATACKING) {
                currentAction = FALLING;
                this.animation.setFrames(sprites.get(FALLING));
            }
        } else {
            jumpingOption = true;
            if (currentAction != WALKING && currentAction != ATACKING) {
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
            if (jumpingOption) {
                jumpingOption = false;
            }
            if (fallingOption) {
                xMovement();
            }
        }
    }

    /**
     * moves Samuel is moving left and right
     */
    private void xMovement() {
        if (right) {
            if (rightOption) {
                facingRight = true;
                setTranslateX(x + xVelocity);
                if (currentAction != WALKING && currentAction != FALLING && currentAction != ATACKING) {
                    this.currentAction = WALKING;
                    animation.setFrames(sprites.get(WALKING));
                }
            }
        } else if (left) {
            if (leftOption) {
                facingRight = false;
                setTranslateX(x + xVelocity);
                if (currentAction != WALKING && currentAction != FALLING && currentAction != ATACKING) {
                    this.currentAction = WALKING;
                    animation.setFrames(sprites.get(WALKING));
                }

            }
        } else {
            if (xVelocity > 0) {
                if (currentAction != IDLE) {
                    if (currentAction != ATACKING) {
                        this.currentAction = IDLE;
                        animation.setFrames(sprites.get(IDLE));
                    }
                }
                xVelocity -= stopSpeed;
            } else if (xVelocity < 0) {
                if (currentAction != IDLE) {
                    if (currentAction != ATACKING) {
                        this.currentAction = IDLE;
                        animation.setFrames(sprites.get(IDLE));
                    }
                }
                xVelocity += stopSpeed;
            }
        }
    }

    /**
     * changes how fast Samuel moves
     */
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

    /**
     * takes user input and triggers events with said inputs
     * @param event - the event that is triggered from the user's inputs
     */
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
                case J:
                    eventAction = true;
                    break;
                case ESCAPE:
                    roomState.getGSM().closeGame();
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
                case J:
                    eventAction = false;
                    break;
            }
        }
    }

    /**
     * animates Samuel's movements
     */
    public void draw() {
        this.animation.update();
        this.setImage(animation.getImage());
        if (facingRight) {
            this.setScaleX(-1);
        } else {
            this.setScaleX(1);
        }
    }

    /**
     * causes Samuel to flinch after being attacked by an enemy
     * @param startTime - the time when Samuel starts flinching
     */
    public void flinch(Long startTime) {
        this.flinchTime = startTime;
        flinching = true;
        invince = true;
        delay = 100;
    }

    /**
     * checks if Samuel is flinching
     */
    public void checkFlinching() {
        boolean visalbe = true;
        int flinchDelay = 400;
        int invinceTime = 1000;
        long elapse = (System.nanoTime() - flinchTime) / 1000000;
        if (elapse > flinchDelay) {
            flinching = false;
        }
        if(delay < elapse){
            visalbe = true;
            if(delay*2<elapse){
                delay = (int)elapse+(delay*2);
            }
        } else {
            visalbe = false;
        }

        if (elapse > invinceTime) {
            invince = false;
            visalbe = true;
            delay = 0;
        }

        if (visalbe) {
            this.setVisible(true);
        } else {
            this.setVisible(false);
        }
    }

    /**
     * checks whether or not an event was activated
     * @return
     */
    public boolean getEventAction(){
        return eventAction;
    }

    /**
     * Samuel's health. It goes down when he gets touched by an enemy.
     * @param damage - the damage Samuel took from an enemy
     */
    public void damage(int damage) {
        HEALTH -= damage;
        if (HEALTH == 3) {
            H1.setImage(new Image(getClass().getResourceAsStream("/Assets/Items/health.png")));
            H2.setImage(new Image(getClass().getResourceAsStream("/Assets/Items/health.png")));
            H3.setImage(new Image(getClass().getResourceAsStream("/Assets/Items/health.png")));
        }
        if (HEALTH == 2) {
            H1.setImage(new Image(getClass().getResourceAsStream("/Assets/Items/death.png")));
            H2.setImage(new Image(getClass().getResourceAsStream("/Assets/Items/health.png")));
            H3.setImage(new Image(getClass().getResourceAsStream("/Assets/Items/health.png")));
        }
        if (HEALTH == 1) {
            H1.setImage(new Image(getClass().getResourceAsStream("/Assets/Items/death.png")));
            H2.setImage(new Image(getClass().getResourceAsStream("/Assets/Items/death.png")));
            H3.setImage(new Image(getClass().getResourceAsStream("/Assets/Items/health.png")));
        }
        if (HEALTH == 0) {
            H1.setImage(new Image(getClass().getResourceAsStream("/Assets/Items/death.png")));
            H2.setImage(new Image(getClass().getResourceAsStream("/Assets/Items/death.png")));
            H3.setImage(new Image(getClass().getResourceAsStream("/Assets/Items/death.png")));
        }
    }
}
