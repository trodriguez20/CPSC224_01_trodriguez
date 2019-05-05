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
    
    public Bullet(int x, int y, int mouseX, int mouseY, int bounce){
        this.x = x;
        this.y = y;
        this.hypotenuse = Math.sqrt((Math.pow((mouseX - x), 2) + Math.pow((mouseY - y), 2)));
        this.cos = (mouseX - x) /hypotenuse;
        this.sin = (mouseY - y) /hypotenuse;
        this.bounce = bounce;
        visible = true;
    }
    
    public void move(int[] wallX, int[] wallY, int[] wallWidth, int[] wallHeight){
        x += 8*cos;
        for(int i = 0; i < wallX.length; i++){
            if((x > wallX[i]) && (x < wallX[i] + wallWidth[i]) && (y > wallY[i]) && (y < wallY[i] + wallHeight[i])){
                x -= 8*cos;
                if(bounce > 0)
                    visible = false;
                cos = -1 * cos;
                bounce++;
            }
        }
        
        y += 8*sin;
        for(int i = 0; i < wallX.length; i++){
            if((x > wallX[i]) && (x < wallX[i] + wallWidth[i]) && (y > wallY[i]) && (y < wallY[i] + wallHeight[i])){
                y -= 8*sin;
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
