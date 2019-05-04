/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanks;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

public class titlePanel extends JPanel{
    private Image title;
    private Image tank;
    private Image mascot;
    //private JButton start;
    //private JButton Exit;
       
    public titlePanel()
    {
        setBackground(new Color(204, 204, 204));
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
        repaint();
    }
       
    public void paint( Graphics g )
    {
        g.drawImage(tank, 150, -100, null);
        g.drawImage(mascot, 0, -50, null);
        g.drawImage(title, 425, 100, null);
    }
}
