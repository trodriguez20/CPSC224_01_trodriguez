/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanks;

import java.awt.Color;
import java.awt.Robot;

/**
 *
 * @author Jared
 */
public class Bullet {
    protected int x;
    protected int y;
    protected double cos;
    protected double sin;
    protected boolean visible;
    protected boolean bounceTime = false;
    protected int bounce;
    
    public Bullet(int x, int y, int mouseX, int mouseY){
        this.x = x;
        this.y = y;
        double angle = Math.atan2(mouseY - y, mouseX - x);
        this.cos = Math.cos(angle);
        this.sin = Math.sin(angle);
        bounce = 0;
        visible = true;
    }
    
    public void move(){
        Color color = Color.BLACK;
        try{
            Robot rob = new Robot();
            color = rob.getPixelColor((int) (x + (16*cos)), (int) (y + 30 + (16*sin)));
        } catch (Exception evt) {
            System.err.println(evt.getMessage());
        }
        if(color.getGreen() == 163)
            bounceTime = true;
        moveX();
        moveY();
    }
    
    public void moveX(){
        x += 8*cos;
        if(bounceTime){
            Color color = Color.BLACK;
            try{
                Robot rob = new Robot();
                color = rob.getPixelColor((int) (x + (8*cos)), (int) (y + 30 + (8*sin)));
            } catch (Exception evt) {
                System.err.println(evt.getMessage());
            }
            if(color.getGreen() == 163){
                if(bounce > 0){
                    visible = false;
                }
                cos = -1 * cos;
                bounce++;
                bounceTime = false;
            }
        }
        
    }
    
    public void moveY(){
        y += 8*sin;
        if(bounceTime){
            if(bounce > 0){
                visible = false;
            }
            sin = -1 * sin;
            bounce++;
            bounceTime = false;
        }
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isVisible() {
        return visible;
    }
}
