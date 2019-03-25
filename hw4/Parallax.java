/*
 Homework 4
 Due Date: 3/24/19
 Names: Timothy Rodriguez & Jared Elliott
*/
package parallax;

import java.awt.event.*;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.PopupMenu;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.image.BufferedImage;
import static java.lang.Math.max;
import static java.lang.Math.min;
import javax.imageio.*;


public class Parallax extends JFrame
{
    public Parallax()
    {
        setTitle("Motion Parallax");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fencePanel fp = new fencePanel();
        add(fp);
        setSize(2000, 1175);
        setResizable(false);
        setVisible(true);
    }
    
    class fencePanel extends JPanel implements ActionListener
    {
       private Image window;
       private Image fence;
       private Image mountains;
       private Image field;
       private BufferedImage[] runner = new BufferedImage[5];
       private Image[] mothra = new Image[2];
       private int runCount = 0;
       private Layer mountLayer;
       private Layer fieldLayer;
       private Layer fenceLayer;
	   
       private int delay = 100;
       protected Timer timer;
		
       private int runnerX = 800;
       private int runnerY = 775;
       private int butterX = 0;
       private int butterY = 0;
       private int currentX = 0;
       private int currentY = 0;
       int numMouseClicks = 0;
       
       Random rand = new Random();
       Color color1 = new Color(153, 217, 234);
      
       public fencePanel()
       {
            try
            {
                window = ImageIO.read(new File("window.png"));
                fence = ImageIO.read(new File("fence.png"));
                mountains = ImageIO.read(new File("mountains.png"));
                field = ImageIO.read(new File("horizon.png"));
                mothra[0] = ImageIO.read(new File("mothra0.png"));
                mothra[1] = ImageIO.read(new File("mothra1.png"));
                for(int i = 0; i < 5; i++)
                {
                    runner[i] = ImageIO.read(new File("dude" + i + ".gif"));
                }
            }
            catch(IOException e)
            {
                System.out.println("Error opening image files");
            }
            mountLayer = new Layer(2000, mountains, 0, 0, 5); 
            fieldLayer = new Layer(2000, field, 0, 650, 10);
            fenceLayer = new Layer(2000, fence, 0, 950, 20);

            timer = new Timer(delay, this);
            timer.start();		// start the timer

            addMouseListener(new MyMouseListener());
            addMouseMotionListener(new MyMouseMotionListener());
       }

       public void actionPerformed(ActionEvent e)
       // will run when the timer fires
       {
            repaint();
       }

       public void paintComponent( Graphics g )
       {
            super.paintComponent(g); // call superclass's paintComponent 
            g.setColor(color1);
            g.fillRect(0, 0, 2000, 1125);
            mountLayer.move(g);
            fieldLayer.move(g);
            if(numMouseClicks % 2 == 1)
            {
                g.drawImage(runner[runCount%5], runnerX, runnerY, null);
            }
            fenceLayer.move(g);
            g.drawImage(mothra[runCount%2], butterX, butterY, null);
            g.drawImage(window, 0, 0, null);
            runCount = (runCount + 1)%20;
       }
       
       private class MyMouseListener extends MouseAdapter
       {
            public void mousePressed(MouseEvent e)
            {
               currentX  = e.getX();
               currentY = e.getY();
            }   
            public void mouseClicked(MouseEvent e)
            {
                numMouseClicks++;
                repaint();
            }
            /*
            public void mouseReleased(MouseEvent e)
            {
                setBackground(Color.ORANGE);
                repaint();
            }
			*/
            public void mouseEntered(MouseEvent e)
            {
		color1 = new Color(153, 217, 234);
                //setBackground(Color.cyan);
                repaint();         
                    
            }
            public void mouseExited(MouseEvent e)
            {
                color1 = new Color(57, 86, 125);
                //setBackground(Color.blue);
                repaint();
            }
        }
        private class MyMouseMotionListener implements MouseMotionListener
        {
            public void mouseDragged(MouseEvent e)
            {
                if(e.getX() < currentX)
                {
                    runnerX = max(0, runnerX - Math.abs((currentX - e.getX())/20));
                } else {
                    runnerX = min(1700, runnerX + Math.abs((e.getX() - currentX)/20));
                }
                repaint();
            }

            public void mouseMoved(MouseEvent e)
            {
                butterX = e.getX();
                butterY = e.getY();
                //mouseStates[6].setText("X: " + e.getX());
                //mouseStates[7].setText("Y: " + e.getY());       
            }
        }
    }

    public static void main(String[] args) 
    {
        new Parallax();
     
    }
}