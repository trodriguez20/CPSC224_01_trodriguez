/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanks;

import java.awt.Image;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

/**
 *
 * @author Jared
 */
public class Player {
    
    private int dx, dy;
    int X = 40;
    int Y = 60;
    int width;
    int height;
    Image tankImage;
    
    public Player()
    {
        ImageIcon tankIcon = new ImageIcon("tankBottom.png");
        tankImage = tankIcon.getImage();
        
        width = tankImage.getWidth(null);
        height = tankImage.getHeight(null);
    }
    
    public void move()
    {
        X += dx;
        Y += dy;
    }
    
    public void keyPressed(KeyEvent e) {

        char key = e.getKeyChar();

        System.out.println("key has been pressed");
        if (key == 'a') {
            System.out.println("left");
            dx = -2;
        }

        if (key == 'd') {
            System.out.println("right");
            dx = 2;
        }

        if (key == 'w') {
            System.out.println("up");
            dy = -2;
        }

        if (key == 's') {
            System.out.println("down");
            dy = 2;
        }
    }

    public void keyReleased(KeyEvent e) {
        
        char key = e.getKeyChar();

        if (key == 'a') {
            dx = 0;
        }

        if (key == 'd') {
            dx = 0;
        }

        if (key == 'w') {
            dy = 0;
        }

        if (key == 's') {
            dy = 0;
        }
    }
}
