package Entity;

import javafx.scene.image.Image;

public class Animator{

    private Image[] frames;
    private int currentFrame;

    private long startTime;
    private long delay;

    private boolean playedOnce;

    /**
     * default constructor
     */
    public void Animation(){
        playedOnce = false;
    }

    /**
     * sets the frame to swap between
     * @param frames - Image array of sprits to swap between
     */
    public void setFrames(Image[] frames) {
        this.frames = frames;
        currentFrame = 0;
        startTime = System.nanoTime();
        playedOnce = false;
    }

    /**
     * sets the time to delay between image swaps
     * @param delay - time to delay
     */
    public void setDelay(long delay) {
        this.delay = delay;
    }

    /**
     * updates the animator and if delay has passes it changes the current frame
     */
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

    /**
     * returns the current image in the frames
     * @return - image currently animating
     */
    public Image getImage(){ return frames[currentFrame];}

    /**
     * returns if the animation has played one
     * @return - true if played once false if not
     */
    public boolean hasPlayedOnce(){return playedOnce;}

}
