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
    protected int speed;
    protected boolean visible;
    protected int bounce;
    protected double angle;
    
    public Bullet(int x, int y, int mouseX, int mouseY, int bounce, int speed){
        int length = mouseX - x + 20;
        int height = mouseY - y + 20;
        this.hypotenuse = Math.sqrt((Math.pow(length, 2) + Math.pow(height, 2)));
        this.cos = (length) /hypotenuse;
        this.sin = (height) /hypotenuse;
        this.x = (int) (x + (cos*40));
        this.y = (int) (y + (sin*40));
        this.bounce = bounce;
        visible = true;
        this.speed = speed;
    }
    
    public void move(int[] wallX, int[] wallY, int[] wallWidth, int[] wallHeight){
        x += speed*cos;
        for(int i = 0; i < wallX.length; i++){
            if((x > wallX[i]) && (x < wallX[i] + wallWidth[i]) && (y > wallY[i]) && (y < wallY[i] + wallHeight[i])){
                x -= speed*cos;
                if(bounce > 0)
                    visible = false;
                cos = -1 * cos;
                bounce++;
            }
        }
        
        y += speed*sin;
        for(int i = 0; i < wallX.length; i++){
            if((x > wallX[i]) && (x < wallX[i] + wallWidth[i]) && (y > wallY[i]) && (y < wallY[i] + wallHeight[i])){
                y -= speed*sin;
                if(bounce > 0)
                    visible = false;
                sin = -1 * sin;
                bounce++;
            }
        }
    }
    
    public boolean hit(int tankX, int tankY){
        if(x>tankX && x<tankX+50 && y>tankY && y<tankY+50)
        {
            visible=false;
            return false;
        }
        return true;
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
