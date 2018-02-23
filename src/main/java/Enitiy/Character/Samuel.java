package Enitiy.Character;

import TileMap.*;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Samuel extends Character {


    public Samuel(TileMap tm) {

        try{
            BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Sprites/Player/playersprites.gif"));
            sprites = new ArrayList<Image[]>();
            for(int i =0; i< 7; i++){
                Image[] bit = new Image[[numFrames[i]];
                for(int j = 0; j < numFrames[i]; j++){
                    if(i != 6) {
                        bit[j] = SwingFXUtils.toFXImage(spritesheet.getSubimage(j * width, i * height, width, height));
                    }else{
                        bit[j] = spritesheet.getSubimage(j * width  * 2, i * height, width, height);
                    }
                }
                sprites.add(bit);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

//        animation = new Animation();
//        currentActtion = IDLE;
//        animation.setFrames(sprites.get(IDLE));
//        animation.setDelay(400);
    }
}
