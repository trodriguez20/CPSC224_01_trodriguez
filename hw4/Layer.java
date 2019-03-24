
import java.awt.Graphics2D;
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
    
    private Layer(int inScreenWidth, Image inImage, int inX, int inY, int inChange){
        image = inImage;
        screenWidth = inScreenWidth;
        height = image.getHeight(null);
        x = inX;
        y = inY;
        change = inChange;
    }
    
    private void move(Graphics2D depth){
        if (x > (0 - screenWidth)){
            draw(depth, 0, screenWidth + x, (0 - x), screenWidth);
            draw(depth, screenWidth + x,  screenWidth, 0, screenWidth - x);
        } else {
            draw(depth, 0, screenWidth, 0, screenWidth);
            x = 0;
        }
        x = (x - change) % screenWidth;
    }
    
    private void draw(Graphics2D depth, int startDrawX, int endDrawX, int startImage, int endImage){
        depth.drawImage(image, startDrawX, y, endDrawX, y + height, startImage, 0, endImage, height, null);
    }
    
    
    
}
