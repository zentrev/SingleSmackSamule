package Enitiy.Character;

public class Samuel extends Character {

    private static final int IDLE = 0;

    private static final int WALKING = 1;

    private static final int JUMPING = 2;

    private static final int FALLING = 3;

    private static final int SMACKING = 4;

    private static final int DIEING = 5;

    public Samuel(){

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

    public static int getSMACKING() {
        return SMACKING;
    }

    public static int getDIEING() {
        return DIEING;
    }
}
