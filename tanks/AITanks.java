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
public class AITanks// implements ActionListener
{
    Image tankB;
    Image tankS;
    Image tankImage;
    BufferedImage tankT;
    int X;
    int Y;
    int xPlayer = 0;
    int yPlayer = 0;
    int width;
    int widthT;
    int height;
    int heightT;
    char type;
    
    boolean alive = true;
    /*
    Image tankBG;
    BufferedImage tankTG;
    int Xg=1400;
    int Yg=100;
    int widthgt;
    int heightgt;
    
    double angle = 0;
    double a;
    double b;
    
    Image tankBB;
    Image tankDB;
    Image tankSB;
    BufferedImage tankTB;
    int Xb=1400;
    int Yb=600;
    int widthb;
    int heightb;
    
    Image tankBR;
    Image tankDR;
    Image tankSR;
    BufferedImage tankTR;
    int Xr=1400;
    int Yr=300;
    int widthr;
    int heightr;
    */
    List<Bullet> bullets;
    protected Timer aitimer;
    
    public AITanks(int X, int Y, char type)
    {
        this.X = X;
        this.Y = Y;
        this.type = type;
        ImageIcon tankIcon = null;
        ImageIcon tankIconS = null;
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
        
        width = tankB.getWidth(null);
        height = tankB.getHeight(null);
        widthT = tankT.getWidth(null);
        heightT = tankT.getHeight(null);
        
        bullets = new ArrayList<>();
        /*
        ImageIcon tankIconBG = new ImageIcon("greyTankS.png");
        ImageIcon tankIconBB = new ImageIcon("blueTankB.png");
        ImageIcon tankIconSB = new ImageIcon("blueTankS.png");
        ImageIcon tankIconBR = new ImageIcon("redTankB.png");
        ImageIcon tankIconSR = new ImageIcon("redTankS.png");
        
        tankBG = tankIconBG.getImage();
        tankBB = tankIconBB.getImage();
        tankSB = tankIconSB.getImage();
        tankBR = tankIconBR.getImage();
        tankSR = tankIconSR.getImage();
        tankDB = tankBB;
        tankDR = tankBR;
        
        try{
            tankTG = ImageIO.read(new File("greyT.png"));
            tankTB = ImageIO.read(new File("blueT.png"));
            tankTR = ImageIO.read(new File("redT.png"));
        } catch(IOException e) {
        
        }
        widthgt=tankTG.getWidth(null);
        heightgt=tankTG.getHeight(null);
        */
        //aitimer=new Timer(3000, this);
        //aitimer.start();
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
        if(alive)
            aiShoot();
    }
    
    public void moveBlue()
    {
        int dx = 0;
        int dy = 0;
        
        if(X != 1300 && Y > 599)
        {
            dx-=2;
            tankB=tankS;
        }
        else if(X < 1350 && Y != 500)
        {
            dy-=2;
            tankB=tankImage;
        }
        else if(Y < 550 && X != 1402)
        {
            dx+=2;
            tankB=tankS;
        }
        else if(X > 1401 && Y != 600)
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
        int side = 0;
        int vertical = 0;
        if(bullets.size()<1)
        {
            if(X < xPlayer){
                side = 25;
            } else {
                side = -25;
            }
            if(Y < yPlayer){
                vertical = 25;
            } else {
                vertical = -25;
            }
            bullets.add(new Bullet(X+width/2 + side, Y + height/2 + vertical, xPlayer, yPlayer, 1));
        }
        
    }
    
    
    public void moveRed(int x, int y)
    {
        int dxr=0;
        int dyr=0;
        
       if(x>X-100 && x<X+100 && y>Y)
       {
           dyr+=1;
           tankB=tankImage;
       }
       if(x>X-100 && x<X+100 && y<Y)
       {
           dyr-=1;
           tankB=tankImage;
       }
       if(y>Y-100 && y<Y+100 && x>X)
       {
           dxr+=1;
           tankB=tankS;
       }
       if(y>Y-100 && y<Y+100 && x<X)
       {
           dxr-=1;
           tankB=tankS;
       }
        
        if((X < 1545) && (X > 0))
            X += dxr;
        if((Y < 815) && (Y > 0))
            Y += dyr;
    }
/*
    @Override
    public void actionPerformed(ActionEvent ae) {
        this.aiShoot();
    }
    */
}
