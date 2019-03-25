/*
 * Timothy Rodriguez & Jared Elliott
 */
package parallax;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.Timer;
import java.io.*;
import javax.imageio.*;

public class Parallax extends JFrame
{
    
   
   public Parallax()
   {
      JFrame frame = new JFrame( "Motion Parallax" );
      frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
      fencePanel fp = new fencePanel(); 
      frame.add(fp);
      frame.setSize( 2000, 1180 ); // set frame size
      frame.setResizable(false);
      frame.setVisible( true ); // display frame
   }
   
   public static void main( String args[] )
   {
	   new Parallax();
   }
}

// class BallPanel

class fencePanel extends JPanel implements ActionListener
{
   private Image window;
   private Image fence;
   
   private Image mountains;
   private Image field;
   private Layer mountLayer;
   private Layer fieldLayer;
   
   private int delay = 10;
   protected Timer timer;

   private int x = 1800;		// x position
   private int y = 955;		// y position
   private int halfFence = 15;	// ball radius

   private int dx = 2;		// increment amount (x coord)
   //private int dy = 2;		// increment amount (y coord)

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
        
      mountLayer = new Layer(2000, mountains, 0, 0, 10); 
      fieldLayer = new Layer(2000, field, 0, 655, 20);
      timer = new Timer(delay, this);
	timer.start();		// start the timer
   }

   public void actionPerformed(ActionEvent e)
   // will run when the timer fires
   {
	repaint();
   }

   // draw rectangles and arcs
   public void paintComponent( Graphics g )
   {
      super.paintComponent( g ); // call superclass's paintComponent 
	//g.setColor(Color.red);
        
        mountLayer.move(g);
        fieldLayer.move(g);
        
	// check for boundaries
	if (x < halfFence)			dx = Math.abs(dx);
	if (x > getWidth() - halfFence)	dx = -Math.abs(dx);
	//if (y < radius)			dy = Math.abs(dy);
	//if (y > getHeight() - radius)	dy = -Math.abs(dy);

	// adjust ball position
	x -= dx;
	//y += dy;
	g.drawImage(fence, x-halfFence, y, null);
        g.drawImage(window, 0, 0, null);
	//g.fillOval(x - radius, y - radius, radius*2, radius*2);
   }
  

}