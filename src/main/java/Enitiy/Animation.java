package Enitiy;

import javafx.scene.image.Image;

public class Animation {

    private Image frames;

    private int currentFrame;

    private long startTime;

    private long delay;

    private boolean playedOnce;

    public Animation Animation(){
        return null;
    }

    public void update(){

    }

    public Image getFrames() {
        return frames;
    }

    public void setFrames(Image frames) {
        this.frames = frames;
    }

    public int getCurrentFrame() {
        return currentFrame;
    }

    public void setCurrentFrame(int currentFrame) {
        this.currentFrame = currentFrame;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getDelay() {
        return delay;
    }

    public void setDelay(long delay) {
        this.delay = delay;
    }

    public boolean isPlayedOnce() {
        return playedOnce;
    }

    public void setPlayedOnce(boolean playedOnce) {
        this.playedOnce = playedOnce;
    }
}
