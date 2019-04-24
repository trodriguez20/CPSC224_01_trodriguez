/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanks;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Jared
 */
public class GameBoard extends JPanel implements ActionListener {
    
    private Timer timer;
    private Player player1;
    final int DELAY = 10;
    /**
     * @param args the command line arguments
     */
    public GameBoard ()
    {
        addKeyListener(new TAdapter());
        setBackground(new Color(235, 232, 145));
        setFocusable(true);
        
        player1 = new Player();
        
        timer = new Timer(DELAY, this);
        timer.start();
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        draw(g);
        
        Toolkit.getDefaultToolkit().sync();
    }
    
    private void draw(Graphics g)
    {
        
        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(player1.tankD, player1.X, player1.Y, this);
        g2d.drawImage(player1.tankT, player1.X+10, player1.Y+10, this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        player1.move();
       
       repaint(player1.X-1, player1.Y-1, 
               player1.width+2, player1.height+2);
    }
    
    // Class that activates the move commands for the Player Class
    private class TAdapter extends KeyAdapter 
    {
        @Override
        public void keyReleased(KeyEvent e) {
            player1.keyReleased(e);
        }
        
        @Override
        public void keyPressed(KeyEvent e) {
            
            System.out.println("key pressed lol");
            player1.keyPressed(e);
        }
    }
}
