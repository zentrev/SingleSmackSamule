package Enitiy.Character.Monsters;

import Enitiy.Character.Character;

public class Monster extends Character {

    public static final int IDLE = 0;

    public static final int WALKING = 1;

    public static final int JUMPING = 2;

    public static final int FALLING = 3;

    public static final int ATACKING = 4;

    public static final int DIEING = 5;

    public void Monster(){

    }

    public void nextPosition(){

    }

    public void update(){

    }

    public void draw(){

    }

    public static int getIDLE() {
        return IDLE;
    }

    public static int getWALKING() {
        return WALKING;
    }

    public static int getJUMPING() {
        return JUMPING;
    }

    public static int getFALLING() {
        return FALLING;
    }

    public static int getATACKING() {
        return ATACKING;
    }

    public static int getDIEING() {
        return DIEING;
    }
}
