/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanks;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author m14wo
 */
public class AITanks 
{
    Image tankBG;
    BufferedImage tankTG;
    int Xg=1400;
    int Yg=100;
    int widthgt;
    int heightgt;
    
    double angleG = 0;
    double a=0;
    double b=0;
    
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
    
    List<Bullet> bullets;
    protected Timer aitimer;
    
    boolean Alive=true;
    
    public AITanks()
    {
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
        aitimer=new Timer();
    }
    
    public void moveAI(int xp, int yp)
    {
        moveBlue();
        moveRed(xp, yp);
        //aiShoot(xp, yp);
    }
    
    public void moveBlue()
    {
        int dxb = 0;
        int dyb = 0;
        
        if(Xb != 1300 && Yb > 599)
        {
            dxb-=2;
            tankDB=tankSB;
        }
        else if(Xb < 1350 && Yb != 500)
        {
            dyb-=2;
            tankDB=tankBB;
        }
        else if(Yb < 550 && Xb != 1402)
        {
            dxb+=2;
            tankDB=tankSB;
        }
        else if(Xb > 1401 && Yb != 600)
        {
            dyb+=2; 
            tankDB=tankBB;
        } 
        else
        {
            dxb=0;
            dyb=0;
        }
        
        
        if((Xb < 1545) && (Xb > 0))
            Xb += dxb;
        if((Yb < 815) && (Yb > 0))
            Yb += dyb;
    }
    /*
    public void aiShoot(int x, int y)
    {
        if(bullets.size()<2)
        {
            bullets.add(new Bullet(Xg+widthgt/2, Yg + heightgt/2, x, y));
        }
        /*
        aitimer.scheduleAtFixedRate(new TimerTask()
        {
            int i=0;
            public void run()
            {
                i++;
                if(i%4==0)
                {
                    if(bullets.size()<2)
                    {
                        bullets.add(new Bullet(Xg+widthgt/2, Yg + heightgt/2, x, y));
                    }
                }
                
            }
        }, 0, 1000);
        //*/
    //}
    
    
    public void moveRed(int x, int y)
    {
        int dxr=0;
        int dyr=0;
        
       if(x>Xr-100 && x<Xr+100 && y>Yr)
       {
           dyr+=1;
           tankDR=tankBR;
       }
       if(x>Xr-100 && x<Xr+100 && y<Yr)
       {
           dyr-=1;
           tankDR=tankBR;
       }
       if(y>Yr-100 && y<Yr+100 && x>Xr)
       {
           dxr+=1;
           tankDR=tankSR;
       }
       if(y>Yr-100 && y<Yr+100 && x<Xr)
       {
           dxr-=1;
           tankDR=tankSR;
       }
        
        if((Xr < 1545) && (Xr > 0))
            Xr += dxr;
        if((Yr < 815) && (Yr > 0))
            Yr += dyr;
    }
}
