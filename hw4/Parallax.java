/*
 * Timothy Rodriguez & Jared Elliott
 */
package parallax;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Parallax extends JFrame 
{
   private int WINDOW_WIDTH = 2000;
   private int WINDOW_HEIGHT = 1125;
   private JPanel fencePanel;
   private JLabel fenceImage;
   private JPanel windowPanel;
   private JLabel windowImage;
   
   public myParallax()
   {
       //set Title
       setTitle("Motion Parallax");
       
       // Specify an action for the close button.
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
       
   }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
