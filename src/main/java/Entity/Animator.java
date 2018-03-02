package Entity;

import javafx.scene.image.Image;

public class Animator{

    private Image[] frames;
    private int currentFrame;

    private long startTime;
    private long delay;

    private boolean playedOnce;

    public void Animation(){
        playedOnce = false;
    }

    public void setFrames(Image[] frames) {
        this.frames = frames;
        currentFrame = 0;
        startTime = System.nanoTime();
        playedOnce = false;
    }

    public void setDelay(long delay) {
        this.delay = delay;
    }

    public void setFrame(int currentFrame){
        this.currentFrame = currentFrame;
    }

    public void update(){

        if(delay == -1) return;

        long elapsed = (System.nanoTime() - startTime) / 1000000;
        if(elapsed > delay){
            currentFrame++;
            startTime = System.nanoTime();
        }
        if(currentFrame == frames.length){
            currentFrame = 0;
            playedOnce = true;
        }

    }

    public int getFrame() {return currentFrame;};
    public Image getImage(){ return frames[currentFrame];}
    public boolean hasPlayedOnce(){return playedOnce;}

}
