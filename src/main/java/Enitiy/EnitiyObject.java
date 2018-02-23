package Enitiy;

import TileMap.TileMap;
import javafx.scene.shape.Rectangle;

public abstract class EnitiyObject {

    protected TileMap titleMap;

    protected int tileSize;

    protected double xmap;

    protected double ymap;

    protected double x;

    protected double y;

    protected double dx;

    protected double dy;

    protected int width;

    protected int hieght;

    protected int cwidth;

    protected int chieght;

    protected int currRow;

    protected  int currCol;

    protected double xdest;

    protected double ydest;

    protected double xtemp;

    protected double ytemp;

    protected boolean topLeft;

    protected boolean topRight;

    protected boolean bottomLeft;

    protected boolean bottomRight;

    protected int currentAction;

    protected int previousAction;

    protected boolean facingRight;

    protected double moveSpeed;

    protected double maxSpeed;

    protected double stopSpeed;

    protected double fallSpeed;

    protected double maxFallSpeed;

    protected double jumpStart;

    protected double stopJumpSpeed;

    public EnitiyObject(){

    }

    public EnitiyObject(TileMap tileMap){
        this.setTitleMap(tileMap);
    }

    public boolean intersects(EnitiyObject obj){
        return false;
    }

    public Rectangle getRectangle() {
        return null;
    }

    public void calculateCorners(double x, double y){

    }

    public void checkTileMapCollision(){

    }

    public boolean notOnScreen(){
        return false;
    }

    public TileMap getTitleMap() {
        return titleMap;
    }

    public void setTitleMap(TileMap titleMap) {
        this.titleMap = titleMap;
    }

    public int getTileSize() {
        return tileSize;
    }

    public void setTileSize(int tileSize) {
        this.tileSize = tileSize;
    }

    public double getXmap() {
        return xmap;
    }

    public void setXmap(double xmap) {
        this.xmap = xmap;
    }

    public double getYmap() {
        return ymap;
    }

    public void setYmap(double ymap) {
        this.ymap = ymap;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getDx() {
        return dx;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public double getDy() {
        return dy;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHieght() {
        return hieght;
    }

    public void setHieght(int hieght) {
        this.hieght = hieght;
    }

    public int getCwidth() {
        return cwidth;
    }

    public void setCwidth(int cwidth) {
        this.cwidth = cwidth;
    }

    public int getChieght() {
        return chieght;
    }

    public void setChieght(int chieght) {
        this.chieght = chieght;
    }

    public int getCurrRow() {
        return currRow;
    }

    public void setCurrRow(int currRow) {
        this.currRow = currRow;
    }

    public int getCurrCol() {
        return currCol;
    }

    public void setCurrCol(int currCol) {
        this.currCol = currCol;
    }

    public double getXdest() {
        return xdest;
    }

    public void setXdest(double xdest) {
        this.xdest = xdest;
    }

    public double getYdest() {
        return ydest;
    }

    public void setYdest(double ydest) {
        this.ydest = ydest;
    }

    public double getXtemp() {
        return xtemp;
    }

    public void setXtemp(double xtemp) {
        this.xtemp = xtemp;
    }

    public double getYtemp() {
        return ytemp;
    }

    public void setYtemp(double ytemp) {
        this.ytemp = ytemp;
    }

    public boolean isTopLeft() {
        return topLeft;
    }

    public void setTopLeft(boolean topLeft) {
        this.topLeft = topLeft;
    }

    public boolean isTopRight() {
        return topRight;
    }

    public void setTopRight(boolean topRight) {
        this.topRight = topRight;
    }

    public boolean isBottomLeft() {
        return bottomLeft;
    }

    public void setBottomLeft(boolean bottomLeft) {
        this.bottomLeft = bottomLeft;
    }

    public boolean isBottomRight() {
        return bottomRight;
    }

    public void setBottomRight(boolean bottomRight) {
        this.bottomRight = bottomRight;
    }

    public int getCurrentAction() {
        return currentAction;
    }

    public void setCurrentAction(int currentAction) {
        this.currentAction = currentAction;
    }

    public int getPreviousAction() {
        return previousAction;
    }

    public void setPreviousAction(int previousAction) {
        this.previousAction = previousAction;
    }

    public boolean isFacingRight() {
        return facingRight;
    }

    public void setFacingRight(boolean facingRight) {
        this.facingRight = facingRight;
    }

    public double getMoveSpeed() {
        return moveSpeed;
    }

    public void setMoveSpeed(double moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public double getStopSpeed() {
        return stopSpeed;
    }

    public void setStopSpeed(double stopSpeed) {
        this.stopSpeed = stopSpeed;
    }

    public double getFallSpeed() {
        return fallSpeed;
    }

    public void setFallSpeed(double fallSpeed) {
        this.fallSpeed = fallSpeed;
    }

    public double getMaxFallSpeed() {
        return maxFallSpeed;
    }

    public void setMaxFallSpeed(double maxFallSpeed) {
        this.maxFallSpeed = maxFallSpeed;
    }

    public double getJumpStart() {
        return jumpStart;
    }

    public void setJumpStart(double jumpStart) {
        this.jumpStart = jumpStart;
    }

    public double getStopJumpSpeed() {
        return stopJumpSpeed;
    }

    public void setStopJumpSpeed(double stopJumpSpeed) {
        this.stopJumpSpeed = stopJumpSpeed;
    }
}
