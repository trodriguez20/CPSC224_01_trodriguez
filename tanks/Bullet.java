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
    protected int bounce;
    
    public Bullet(int x, int y, int mouseX, int mouseY){
        this.x = x;
        this.y = y;
        this.hypotenuse = Math.sqrt((Math.pow((mouseX - x), 2) + Math.pow((mouseY - y), 2)));
        this.cos = (mouseX - x) /hypotenuse;
        this.sin = (mouseY - y) /hypotenuse;
        bounce = 0;
        visible = true;
    }
    
    public void moveX(){
        x += 8*cos;
        if((x > 1590) || (x < 0)){
            if(bounce > 0){
                visible = false;
            }
            cos = -1 * cos;
            bounce++;
        }
    }
    
    public void moveY(){
        y += 8*sin;
        if((y > 850) || (y < 0)){
            if(bounce > 0){
                visible = false;
            }
            sin = -1 * sin;
            bounce++;
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
