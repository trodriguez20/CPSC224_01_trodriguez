/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanks;

import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.awt.Robot;
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
    int Y = 100;
    int width;
    int height;
    int widthT;
    int heightT;
    List<Bullet> bullets;
    Image tankImage;
    Image tankD;
    Image tankS;
    BufferedImage tankT;
    double angle = 0;
    boolean tankMove;
    
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
        tankMove = true;
        width = tankImage.getWidth(null);
        height = tankImage.getHeight(null);
        widthT = tankT.getWidth(null);
        heightT = tankT.getHeight(null);
        
        bullets = new ArrayList<>();
    }
    
    public void move()
    {
        Color color = Color.BLACK;
        int centerX = X + 25;
        int centerY = Y + 60;
        try{
            Robot rob = new Robot();
            color = rob.getPixelColor(centerX + (15 * dx), centerY + (15 * dy));
            
        } catch (Exception evt) {
            System.err.println(evt.getMessage());
        }
        if(color.getGreen() != 163){
            X += dx;
            Y += dy;
        }  
    }
    
    public void mouseMoved(MouseEvent e){
        double a = e.getX() - X;
        double b = e.getY() - Y;
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
            dx = -2;
            tankD=tankS;
        }

        if (key == 'd') {
            dx = 2;
            tankD=tankS;
        }
        
        if (key == 'w') {
            dy = -2;
            tankD=tankImage;
        }
        
        if (key == 's') {
            dy = 2;
            tankD=tankImage;
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
