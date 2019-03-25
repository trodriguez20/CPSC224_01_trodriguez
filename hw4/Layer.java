package parallax;

import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author Jared
 */
// This class takes an image that has a width larger/the same as the screen
// and turns it into an object that can be scrolled acrossed the screen
// in an endless loop
public class Layer {
    private Image image;        // Image to be scrolled
    
    private float screenWidth; // width of the screen
    private float height;      // height of the image
    private float x;           // location on x axis to display image
    private float y;           // location on y axis to display image
    
    private float change;      // how fast the image scrolls
    
    Layer(float inScreenWidth, Image inImage, float inX, float inY, float inChange){
        image = inImage;
        screenWidth = inScreenWidth;
        height = image.getHeight(null);
        x = inX;
        y = inY;
        change = inChange;
    }
    
    // This function calls the draw function depending on where the
    //  the start of the image is, and takes the part of the image not shown 
    //  and fills the rest of the layer with it.
    //  After doing this the function increments x and resets it if x > screenWidth
    void move(Graphics depth){
        if(x < screenWidth){
            draw(depth, 0, x, screenWidth - x, screenWidth);
            // draw the rest of the image in the empty space
            draw(depth, x, screenWidth, 0, screenWidth - x);
        } else {
            draw(depth, 0, screenWidth, 0, screenWidth);
        }
        x = (x + change) % screenWidth;
    }
    
    // This function draws the image using the x values passed to it
    private void draw(Graphics depth, float startDrawX, float endDrawX, float startImage, float endImage){
        depth.drawImage(image, Math.round(startDrawX), Math.round(y), Math.round(endDrawX), Math.round(y + height), Math.round(startImage), 0, Math.round(endImage), Math.round(height), null);
    }
    
    
    
}
