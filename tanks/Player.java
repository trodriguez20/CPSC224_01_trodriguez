/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanks;

import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.ImageIcon;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

/**
 *
 * @author Jared
 */
public class Player {
    
    private int dx, dy;
    int X = 100;
    int Y = 60;
    int width;
    int height;
    int widthT;
    int heightT;
    List<Bullet> bullets;
    Image tankImage;
    Image tankD;
    Image tankS;
    
    BufferedImage tankT;
    Point turretPos = new Point(X, Y);
    double angle = 0;
    
    public Player()
    {
        ImageIcon tankIcon = new ImageIcon("tankBottom.png");
        //ImageIcon tankTurret = new ImageIcon("tankTop.png");
        ImageIcon tankIconS = new ImageIcon("tankBottomS.png");
        tankImage = tankIcon.getImage();
        try{
            tankT = ImageIO.read(new File("tankTop.png"));
        } catch(IOException e) {
        
        }
        
        tankS = tankIconS.getImage();
        tankD = tankImage;
        
        width = tankImage.getWidth(null);
        height = tankImage.getHeight(null);
        widthT = tankT.getWidth(null);
        heightT = tankT.getHeight(null);
        
        bullets = new ArrayList<>();
    }
    
    public void move(int[] wallX, int[] wallY, int[] wallWidth, int[] wallHeight)
    {
        X += dx;
        for(int i = 0; i < wallX.length; i++){
            if((X + width > wallX[i]) && (X < wallX[i] + wallWidth[i]) && (Y + height > wallY[i]) && (Y < wallY[i] + wallHeight[i])){
                X -= dx;
            }
        }
        
        Y += dy;
        for(int i = 0; i < wallX.length; i++){
            if((X + width > wallX[i]) && (X < wallX[i] + wallWidth[i]) && (Y + height > wallY[i]) && (Y < wallY[i] + wallHeight[i])){
                Y -= dy;
            }
        }
    }
    
    public void mouseMoved(MouseEvent e){
        double a = -(X + 10 -e.getX());
        double b = -(Y + 10 - e.getY());
        angle = Math.atan2(b, a);
    }
    
    public void mousePressed(MouseEvent e){
        if(bullets.size() < 5){
           bullets.add(new Bullet(X + width/2, Y + height / 2, e.getX(), e.getY())); 
        }
    }
    
    public void keyPressed(KeyEvent e) {

        char key = e.getKeyChar();

        if (key == 'a') {
            tankD=tankS;
            if(X >= 1545)
                X -= 2;
            dx = -2;
        }

        if (key == 'd') {
            tankD=tankS;
            if(X <= 0)
                X += 2;
            dx = 2;
        }

        if (key == 'w') {
            tankD=tankImage;
            if(Y >= 815)
                Y -= 2;
            dy = -2;
        }

        if (key == 's') {
            tankD=tankImage;
            if(Y <= 0)
                Y += 2;
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
