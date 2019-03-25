/*
 Homework 4
 Due Date: 3/25/19
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

// This program creats a motion parallax of looking out a window of a car
// into a beautiful valley with mouse listeners the creat a stick man
// runnig away from the kaiju Mothra.
public class Parallax extends JFrame
{
    public Parallax()
    {
        //set title of the JFrame to "Motion Parallax"
        setTitle("Motion Parallax");
        // set the frame to exit on close
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //create a new panel of class fencePanel called fp
        fencePanel fp = new fencePanel();
        add(fp);
       //set size of the JFrame windo to 2000x1175 pixels.
        setSize(2000, 1175);
        setResizable(false);
        setVisible(true);
    }
    
    //creates 
    class fencePanel extends JPanel implements ActionListener
    {
       //new image variables for images
       private Image window;
       private Image fence;
       private Image mountains;
       private Image field;
       //image arrays for animated sprites
       private BufferedImage[] runner = new BufferedImage[5];
       private Image[] mothra = new Image[3];
       //variable for keeping track of which frame is showing
       private int runCount = 0;
       //layer variables for images to create depth
       private Layer mountLayer;
       private Layer fieldLayer;
       private Layer fenceLayer;
       //boolean for keeping track of when mothra attacks
       private boolean laser = false;
       //timer stuff
       private int delay = 100;
       protected Timer timer;
       //variables for (x,y) location of stick figure
       private int runnerX = 800;
       private int runnerY = 775;
       //variables for (x,y) location of mothra
       private int butterX = 0;
       private int butterY = 0;
       //variables to help keep track of mouse (x,y) location
       private int currentX = 0;
       private int currentY = 0;
       //intialize a counter to keep track of mouse clicks
       int numMouseClicks = 0;
       //creates a new colot variable to hold color of the background
       Color color1 = new Color(153, 217, 234);
      
       public fencePanel()
       {
           // load images into their respective variables
            try
            {
                window = ImageIO.read(new File("window.png"));
                fence = ImageIO.read(new File("fence.png"));
                mountains = ImageIO.read(new File("mountains.png"));
                field = ImageIO.read(new File("horizon.png"));
                mothra[0] = ImageIO.read(new File("mothra0.png"));
                mothra[1] = ImageIO.read(new File("mothra2.png"));
                mothra[2] = ImageIO.read(new File("mothra1.png"));
                for(int i = 0; i < 5; i++)
                {
                    runner[i] = ImageIO.read(new File("dude" + i + ".gif"));
                }
            }
            catch(IOException e)
            {
                System.out.println("Error opening image files");
            }
            //set location and image for layers
            mountLayer = new Layer(2000, mountains, 0, 0, 5); 
            fieldLayer = new Layer(2000, field, 0, 650, 10);
            fenceLayer = new Layer(2000, fence, 0, 950, 20);
            //start timer
            timer = new Timer(delay, this);
            timer.start();		// start the timer
            //add mouse listeners
            addMouseListener(new MyMouseListener());
            addMouseMotionListener(new MyMouseMotionListener());
       }
       
        //constantly repaints the image in frame to animate it aslong as 
       // the timer is running
       public void actionPerformed(ActionEvent e)
       {
            repaint();
       }

       //member that calls everything to frame and animates the parallax
       public void paintComponent( Graphics g )
       {
            super.paintComponent(g); // call superclass's paintComponent 
            //makes background a light blue rectangle
            g.setColor(color1);
            g.fillRect(0, 0, 2000, 1125);
            //calls layers in correct display order and animates with move
            //member from layer.java
            mountLayer.move(g);
            fieldLayer.move(g);
            //controls animated sprites and adds running man if mouse clicked
            if(numMouseClicks % 2 == 1)
            {
                g.drawImage(runner[runCount%5], runnerX, runnerY, null);
            }
            fenceLayer.move(g);
            //contols if mothra is attacking based on if mouse is pressed
            if(laser){
                g.drawImage(mothra[2], runnerX + 100, butterY, null);
            } else {
                g.drawImage(mothra[runCount%2], butterX, butterY, null);
            }
            // draws image of car window
            g.drawImage(window, 0, 0, null);
            //modifier to get run count to work for both sprites
            //keeps runcount bellow 20
            runCount = (runCount + 1)%20;
       }
       //implents mouse listener class
       private class MyMouseListener extends MouseAdapter
       {
           //when mouse pressed mothra attacks with lassers
            public void mousePressed(MouseEvent e)
            {
               currentX  = e.getX();
               currentY = e.getY();
               laser = true;
            }   
            //mothra stops attacking when released
            public void mouseReleased(MouseEvent e){
                laser = false;
            }
            //adds or removes the running stick figure
            public void mouseClicked(MouseEvent e)
            {
                numMouseClicks++;
                repaint();
            }
            // if mouse in Frame backround is light blue ie day
            public void mouseEntered(MouseEvent e)
            {
		color1 = new Color(153, 217, 234);
                repaint();             
            }
            //if mouse is off screen background is navy blue ie night
            public void mouseExited(MouseEvent e)
            {
                color1 = new Color(57, 86, 125);
                repaint();
            }
        }
       
       //implements mouse listener class
        private class MyMouseMotionListener implements MouseMotionListener
        {
            //if moouse is dragged in the left direction running man runs left
            //if in the right direction running man runs right
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
            //mothra follows the location of the mouse
            public void mouseMoved(MouseEvent e)
            {
                butterX = e.getX();
                butterY = e.getY();      
            }
        }
    }
    // calls a new intance of parrallax
    public static void main(String[] args) 
    {
        new Parallax();   
    }
}