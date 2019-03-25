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
   
       private Layer mountLayer;
       private Layer fieldLayer;
       private Layer fenceLayer;
	   
       private int delay = 100;
       protected Timer timer;

       private int x = 1800;		// x position
       private int y = 955;		// y position       
       private int currentX = 0;
       private int currentY = 0;
       
       
       Random rand = new Random();
       Color color1 = new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255));
      
       public fencePanel()
       {
            try
            {
                    window = ImageIO.read(new File("window.png"));
                    fence = ImageIO.read(new File("fence.png"));
                    mountains = ImageIO.read(new File("mountains.png"));
                    field = ImageIO.read(new File("horizon.png"));
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
            fenceLayer.move(g);
            g.drawImage(window, 0, 0, null);
            
           
/*
            // creates a mountain
            g.setColor(color1);
            g.fillPolygon(aValues, bValues, 3);

            // creates another mountain
            g.setColor(color2);
            g.fillPolygon(xValues, yValues, 3);
*/
     

       }
       
       private class MyMouseListener extends MouseAdapter
       {
           /*
            public void mousePressed(MouseEvent e)
            {
               setBackground(Color.GRAY);
               repaint();
               
            }        
            public void mouseClicked(MouseEvent e)
            {
                color1 = new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255));
                color2 = new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255));
                repaint();
              
            }
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
    }

    public static void main(String[] args) 
    {
        new Parallax();
     
    }
}