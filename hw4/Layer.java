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
    
    private float screenWidth;
    private float height;
    private float x;
    private float y;
    
    private float change;
    
    Layer(float inScreenWidth, Image inImage, float inX, float inY, float inChange){
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
    
    private void draw(Graphics depth, float startDrawX, float endDrawX, float startImage, float endImage){
        depth.drawImage(image, Math.round(startDrawX), Math.round(y), Math.round(endDrawX), Math.round(y + height), Math.round(startImage), 0, Math.round(endImage), Math.round(height), null);
    }
    
    
    
}
