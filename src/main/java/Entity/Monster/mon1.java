package Entity.Monster;

import Entity.Animator;
import TileMap.Tile;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import static GameStateManager.RoomState.sam;

public class mon1 extends Monster {

    public boolean movingLeft;

    private static final int SLOSHING = 0;
    private int currentAction;

    public mon1(Tile[][] tileMap, double monx, double mony) {
        super(tileMap);
        this.x = monx;
        this.y = mony;

        moveSpeed = 3;
        height = 48;
        width = 64;
        collionHeight = height;
        collionWidth = width;
        fallSpeed = .5;
        maxFallSpeed = 8;

        this.setFitHeight(height);
        this.setFitWidth(width);

        this.setTranslateX(x);
        this.setTranslateY(y);

        numFrames = new int[]{
                2
        };

        try {
            BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Assets/SpriteSheets/snailsprite.png"));
            sprites = new ArrayList<Image[]>();
            for (int i = 0; i < numFrames.length; i++) {
                Image[] sprit = new Image[numFrames[i]];
                for (int j = 0; j < numFrames[i]; j++) {
                    sprit[j] = SwingFXUtils.toFXImage(spritesheet.getSubimage(j * 64, i * 48, 64, 48), null);

                }
                sprites.add(sprit);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.animation = new Animator();
        this.currentAction = SLOSHING;
        animation.setFrames(sprites.get(currentAction));
        animation.setDelay(150);
        this.setImage(animation.getImage());


        movingLeft = true;
    }

    public void draw(){
        this.animation.update();
        this.setImage(animation.getImage());
        if(movingLeft){
            this.setScaleX(1);
        } else {
            this.setScaleX(-1);
        }
    }

    public void update() {

        this.draw();
        changeVelocity();
        checkCollision();
        if (!checkAttacking()) {
            moveVelocity();
        }

    }

    public boolean checkAttacking() {
        if (sam.getBoundsInParent().intersects(this.getBoundsInParent()) && !sam.invince) {
            sam.damage(1);
            sam.flinch(System.nanoTime());
            System.out.println(sam.HEALTH);

            return true;
        }
        return false;
    }

    public void moveVelocity() {
        if (leftOption && movingLeft) {
            x += xVelocity;
            this.setTranslateX(x);
        }
        if (!leftOption && movingLeft) {
            movingLeft = false;
        }
        if (rightOption && !movingLeft) {
            x += xVelocity;
            this.setTranslateX(x);
        }
        if (!rightOption && !movingLeft) {
            movingLeft = true;
        }
        if (fallingOption) {
            y += yVelocity;
            this.setTranslateY(y);
        }
    }

    public void changeVelocity() {
        if (movingLeft) {
            xVelocity = moveSpeed * -1;
        } else {
            xVelocity = moveSpeed;
        }
        if (fallingOption) {
            if (yVelocity < maxFallSpeed) {
                yVelocity += fallSpeed;
            }
        } else {
            if (yVelocity >= 0) {
                yVelocity = 0;
            }
        }
    }

}
