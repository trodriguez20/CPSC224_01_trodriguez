/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanks;

import java.awt.EventQueue;
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

/**
 *
 * @author Jared
 */
public class Tanks extends JFrame {
    
    public Tanks()
    {
        add(new titlePanel());
        //add(new GameBoard());
        
        setTitle("Tanks");
        setSize(1600, 900);
        
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setVisible(true);
    }
    
    class titlePanel extends JPanel
    {
       private Image title;
       private Image tank;
       private Image mascot;
       private JButton start;
       private JButton Exit;
       //private int delay = 100;
       //protected Timer timer;
       public boolean gamestatus = false;
       
       public titlePanel()
       {
           try
            {
                title = ImageIO.read(new File("title.png"));
                tank = ImageIO.read(new File("tank.png"));
                mascot = ImageIO.read(new File("mascot.png"));
            }
           catch(IOException e)
            {
                System.out.println("Error opening image files");
            }
           setLayout(new FlowLayout());
           start=new JButton("START");
           Exit=new JButton("EXIT");
           add(start);
           add(Exit);
           start.addActionListener(new gameListener());
           Exit.addActionListener(new exitListener());
           repaint();
       }
       
       public void paint( Graphics g )
       {
           g.drawImage(tank, 200, 100, null);
           g.drawImage(mascot, 20, 250, null);
           g.drawImage(title, 425, 100, null);
       }
       
       private class gameListener extends JButton implements ActionListener
       {
           public void actionPerformed(ActionEvent e)
           {
            //gamestatus = true;
               add(new GameBoard());
           }
       }
       
       private class exitListener extends JButton implements ActionListener
       {
        public void actionPerformed(ActionEvent e)
        {
            System.exit(0);
        }
       }
    }
    
    public static void main(String[] args) {
        new Tanks();
    }
    
}
