package Enitiy.Character;

import Enitiy.EnitiyObject;
import TileMap.TileMap;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.HashMap;

public class Character extends EnitiyObject{

    private int health;

    private int maxHealth;

    private boolean isDead;

    private boolean flinch;

    private long flinchTimer;

    private ArrayList<Image> sprites;

    private HashMap<Integer, Integer> frames;

    public Character(){

    }

    public Character(TileMap tileMap) {

    }

    public void update(){

    }

    public void draw(){

    }

    public void getNextPosition(){

    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public boolean isFlinch() {
        return flinch;
    }

    public void setFlinch(boolean flinch) {
        this.flinch = flinch;
    }

    public long getFlinchTimer() {
        return flinchTimer;
    }

    public void setFlinchTimer(long flinchTimer) {
        this.flinchTimer = flinchTimer;
    }

    public ArrayList<Image> getSprites() {
        return sprites;
    }

    public void setSprites(ArrayList<Image> sprites) {
        this.sprites = sprites;
    }

    public HashMap<Integer, Integer> getFrames() {
        return frames;
    }

    public void setFrames(HashMap<Integer, Integer> frames) {
        this.frames = frames;
    }
}
