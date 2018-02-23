package TileMap;

import Limbo.Game;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Background {

    private Image image;

    private double x;
    private double y;
    private double dx;
    private double dy;

    private double moveScale;

    public Background(String s, double ms){
        try{
            image = SwingFXUtils.toFXImage(ImageIO.read(getClass().getResourceAsStream(s)),null);
            moveScale = ms;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setPosition(double x, double y){
        this.x = (x*moveScale)% Game.WIDTH;
        this.y = (y*moveScale)% Game.HEIGHT;
    }

    public void setVector(double dx, double dy){
        this.dx = dx + x;
        this.dy = dy + y;
    }

    public void update(){
        x += dx;
        y += dy;
    }

    public void draw(GraphicsContext gc){
        gc.drawImage(image, (int)x, (int)y);
        if(x < 0){
            gc.drawImage(image, (int) x + image.getWidth(), (int) y);
        }
        if(x > 0){
            gc.drawImage(image, (int) x - image.getWidth(), (int) y);
        }

    }

}