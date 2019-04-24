/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanks;

import java.awt.Image;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;


public class Player {
    
    private int dx, dy;
    int X = 40;
    int Y = 60;
    int width;
    int height;
    int widthT;
    int heightT;
    Image tankImage;
    Image tankD;
    Image tankT;
    Image tankS;
    
    public Player()
    {
        ImageIcon tankIcon = new ImageIcon("tankBottom.png");
        ImageIcon tankTurret = new ImageIcon("tankTop.png");
        ImageIcon tankIconS = new ImageIcon("tankBottomS.png");
        tankImage = tankIcon.getImage();
        tankT=tankTurret.getImage();
        tankS=tankIconS.getImage();
        
        width = tankImage.getWidth(null);
        height = tankImage.getHeight(null);
        widthT=tankT.getWidth(null);
        heightT=tankT.getHeight(null);
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
            tankD=tankS;
            dx = -2;
        }

        if (key == 'd') {
            System.out.println("right");
            tankD=tankS;
            dx = 2;
        }

        if (key == 'w') {
            System.out.println("up");
            tankD=tankImage;
            dy = -2;
        }

        if (key == 's') {
            System.out.println("down");
            tankD=tankImage;
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
