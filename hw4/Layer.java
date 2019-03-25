package parallax;

import java.awt.Graphics;
import java.awt.Image;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jared
 */
public class Layer {
    private Image image;
    
    private int screenWidth;
    private int height;
    private int x;
    private int y;
    
    private int change;
    
    Layer(int inScreenWidth, Image inImage, int inX, int inY, int inChange){
        image = inImage;
        screenWidth = inScreenWidth;
        height = image.getHeight(null);
        x = inX;
        y = inY;
        change = inChange;
    }
    
    void move(Graphics depth){
        if(x < screenWidth){
            draw(depth, 0, x, screenWidth - x, screenWidth);
            // Head.
            draw(depth, x, screenWidth, 0, screenWidth - x);
        } else {
            draw(depth, 0, screenWidth, 0, screenWidth);
        }
//        if (x > (0 - screenWidth)){
//            draw(depth, 0, screenWidth + x, (0 - x), screenWidth);
//            draw(depth, screenWidth + x,  screenWidth - x, 0, screenWidth - x);
//        } else {
//            draw(depth, 0, screenWidth, 0, screenWidth);
//        }
        x = (x + change) % screenWidth;
    }
    
    private void draw(Graphics depth, int startDrawX, int endDrawX, int startImage, int endImage){
        depth.drawImage(image, startDrawX, y, endDrawX, y + height, startImage, 0, endImage, height, null);
    }
    
    
    
}
