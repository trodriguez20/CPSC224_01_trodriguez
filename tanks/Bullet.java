/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanks;

/**
 *
 * @author Jared
 */
public class Bullet {
    protected int x;
    protected int y;
    protected double hypotenuse;
    protected double cos;
    protected double sin;
    protected boolean visible;
    
    public Bullet(int x, int y, int mouseX, int mouseY){
        this.x = x;
        this.y = y;
        this.hypotenuse = Math.sqrt((Math.pow((mouseX - x), 2) + Math.pow((mouseY - y), 2)));
        this.cos = (mouseX - x) /hypotenuse;
        this.sin = (mouseY - y) /hypotenuse;
        visible = true;
    }
    
    public void moveX(){
        x += 4*cos;
        if(x > 1600)
            visible = false;
    }
    
    public void moveY(){
        y += 4*sin;
        if(y > 900)
            visible = false;
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
