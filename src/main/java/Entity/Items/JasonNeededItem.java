package Entity.Items;

import TileMap.Tile;
import javafx.scene.image.Image;

public class JasonNeededItem extends Item {

    JasonNeededItem(Tile[][] tm, double itemx, double itemy){
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

        this.setImage(new Image("Assets/Items/Item.png"));

        this.isPicked = false;
    }

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

    public void itemTouched(){
        System.out.println("My son!");
        this.setVisible(false);
        isPicked = true;
    }
}
