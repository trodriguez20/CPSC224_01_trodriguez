/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanks;

import java.awt.FlowLayout;
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

public class titlePanel extends JPanel{
    private Image title;
    private Image tank;
    private Image mascot;
    private JButton start;
    private JButton Exit;
       
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
            JFrame game = new JFrame();
            game.setTitle("Tanks");
            
            game.setSize(1600, 900);
            game.setResizable(false);
            
            JPanel gameBoard = new GameBoard();
            game.add(gameBoard);
            
            game.setVisible(true);
            
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
