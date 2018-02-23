package Enitiy.Item;

import Enitiy.EnitiyObject;
import javafx.scene.image.Image;
import java.util.ArrayList;

public class Item extends EnitiyObject {

    private boolean isPickedUp;

    private boolean Used;

    private ArrayList<Image> sprites;

    public void  Item(){

    }

    private void comitEvent(){

    }

    public void update(){

    }

    public void draw(){

    }

    public boolean isPickedUp() {
        return isPickedUp;
    }

    public void setPickedUp(boolean pickedUp) {
        isPickedUp = pickedUp;
    }

    public boolean isUsed() {
        return Used;
    }

    public void setUsed(boolean used) {
        Used = used;
    }

    public ArrayList<Image> getSprites() {
        return sprites;
    }

    public void setSprites(ArrayList<Image> sprites) {
        this.sprites = sprites;
    }
}
