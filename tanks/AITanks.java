/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanks;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import javax.swing.Timer;

/**
 *
 * @author m14wo
 */
public class AITanks implements ActionListener
{
    Image tankB;
    Image tankS;
    Image tankImage;
    Image explosion;
    Image deadTank;
    BufferedImage tankT;
    int X;
    int Y;
    int x;
    int y;
    int xPlayer = 0;
    int yPlayer = 0;
    int width;
    int widthT;
    int height;
    int heightT;
    char type;
    int[] wallX;
    int[] wallY;
    int[] wallWidth;
    int[] wallHeight;
    
    boolean alive = true;
    
    List<Bullet> bullets;
    protected Timer aitimer;
    
    public AITanks(int X, int Y, char type, int[] wallX, int[] wallY, int[] wallW, int[] wallH)
    {
        this.wallX = wallX;
        this.wallY = wallY;
        this.wallWidth = wallW;
        this.wallHeight = wallH;
        this.X = X;
        this.Y = Y;
        this.type = type;
        ImageIcon tankIcon = null;
        ImageIcon tankIconS = null;
        ImageIcon explosionIcon = null;
        ImageIcon deadIcon = null;
        explosionIcon = new ImageIcon("explosion.png");
        deadIcon = new ImageIcon("deadTank.png");
        try{
            switch (type) {
                case 'g':
                    tankIcon = new ImageIcon("greyTankB.png");
                    tankIconS = new ImageIcon("greyTankS.png");
                    tankT = ImageIO.read(new File("greyT.png"));
                    break;
                case 'b':
                    tankIcon = new ImageIcon("blueTankB.png");
                    tankIconS = new ImageIcon("blueTankS.png");
                    tankT = ImageIO.read(new File("blueT.png"));
                    this.x=X;
                    this.y=Y;
                    break;
                case 'r':
                    tankIcon = new ImageIcon("redTankB.png");
                    tankIconS = new ImageIcon("redTankS.png");
                    tankT = ImageIO.read(new File("redT.png"));
                    break;
                default:
                    break;
            }
        } catch(IOException e){
            
        }
        
        tankImage = tankIcon.getImage();
        tankB = tankImage;
        tankS = tankIconS.getImage();
        explosion = explosionIcon.getImage();
        deadTank = deadIcon.getImage();
        
        width = tankB.getWidth(null);
        height = tankB.getHeight(null);
        widthT = tankT.getWidth(null);
        heightT = tankT.getHeight(null);
        
        bullets = new ArrayList<>();
        
        aitimer=new Timer((int) (Math.random()*1000 + 1000), this);
        aitimer.start();
    }
    
    public void moveAI(int xp, int yp)
    {
        xPlayer = xp;
        yPlayer = yp;
        if(type == 'b'){
            moveBlue();
        } else if(type == 'r'){
            moveRed(xp, yp);
        }
    }
    
    public void moveBlue()
    {
        int dx = 0;
        int dy = 0;
        
        if(X != (x-100) && Y > (y-1))
        {
            dx-=2;
            tankB=tankS;
        }
        else if(X < (x-50) && Y != (y-100))
        {
            dy-=2;
            tankB=tankImage;
        }
        else if(Y < (y-50) && X != (x+2))
        {
            dx+=2;
            tankB=tankS;
        }
        else if(X > (x+1) && Y != y)
        {
            dy+=2; 
            tankB=tankImage;
        } 
        else
        {
            dx=0;
            dy=0;
        }
        
        
        if((X < 1545) && (X > 0))
            X += dx;
        if((Y < 815) && (Y > 0))
            Y += dy;
    }
    
    public void aiShoot()
    {
        if(bullets.size()<1)
        {
            Bullet test = new Bullet(X+width/2, Y + height/2, xPlayer, yPlayer, 1, 50);
            while(test.isVisible() && (Math.abs(test.getX() - xPlayer) > 100 || Math.abs(test.getY() - yPlayer) > 100)){
                test.move(wallX, wallY, wallWidth, wallHeight);
            }
            if(test.isVisible())
                bullets.add(new Bullet(X+width/2, Y + height/2, xPlayer, yPlayer, 1, 8));
        }
    }
    
    public void moveRed(int x, int y)
    {
        int dxr=0;
        int dyr=0;
        
        
        if(x>X-200 && x<X+200)
        {
            if(y > Y)
                dyr+=1;
            else
                dyr-=1;
            tankB=tankImage;
        }
        
        if(y>Y-200 && y<Y+200)
        {
            if(x > X)
                dxr+=1;
            else
                dxr-=1;
            tankB=tankS;
        }

        X += dxr;
         for(int i = 0; i < wallX.length; i++){
             if((X + width > wallX[i]) && (X < wallX[i] + wallWidth[i]) && (Y + height > wallY[i]) && (Y < wallY[i] + wallHeight[i])){
                 X -= dxr;
             }
         }
        
        Y += dyr;
        for(int i = 0; i < wallX.length; i++){
            if((X + width > wallX[i]) && (X < wallX[i] + wallWidth[i]) && (Y + height > wallY[i]) && (Y < wallY[i] + wallHeight[i])){
                Y -= dyr;
            }
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(this.alive){
            this.aiShoot();
        }
        else{
            this.tankB = this.deadTank;
            this.aitimer.stop();
        }
    }
}
