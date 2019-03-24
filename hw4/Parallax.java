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

public class Parallax extends JFrame 
{
   private int WINDOW_WIDTH = 2000;
   private int WINDOW_HEIGHT = 1180;
   private JPanel fencePanel;
   private JLabel fenceImage;
   private JPanel windowPanel;
   private JLabel windowImage;
   
   public Parallax()
   {
       //set Title
       JFrame frame = new JFrame("Motion Parallax");
       
       //set Size
       frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
       frame.setResizable(false);
       
       // Specify an action for the close button.
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
       
       buildFencePanel();
       buildWindowPanel();
       
       //add(fencePanel, BorderLayout.SOUTH);
       //add(windowPanel, BorderLayout.CENTER);
       frame.add(fencePanel);
       frame.add(windowPanel);
       //
       pack();
       frame.setVisible(true);
   }
   
//   public void draw(Graphics g)
//   {
//       Image window = ImageLoader.loadImage(this, "window.png");
//       g.drawImage(window, 2000, 1125);
//   }
   private void buildFencePanel()
   {
       fencePanel = new JPanel();
       ImageIcon fence;
       fence = new ImageIcon("fence.png");
       fenceImage = new JLabel(fence);
       fencePanel.add(fenceImage);
   }
   
   private void buildWindowPanel()
   {
       windowPanel = new JPanel();
       ImageIcon window;
       window = new ImageIcon("window.png");
       windowImage = new JLabel(window);
       windowPanel.add(windowImage);
   }
   
    public static void main(String[] args) 
    {
        new Parallax();
    }
    
}
