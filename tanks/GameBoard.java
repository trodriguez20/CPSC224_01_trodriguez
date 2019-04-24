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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.List;
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
        addMouseListener(new MyMouseListener());
        addMouseMotionListener(new myMouseMotionListener());
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
        
        List<Bullet> playerShots = player1.bullets;
        
        for (Bullet shot : playerShots) {
            g2d.drawOval(shot.getX(), shot.getY(), 10, 10);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        player1.move();
        
        moveBullets();
        
       repaint();
    }
    
    private void moveBullets(){
        List<Bullet> shots = player1.bullets;

        for (int i = 0; i < shots.size(); i++) {

            Bullet bullet = shots.get(i);

            if (bullet.isVisible()) {

                bullet.moveX();
                bullet.moveY();
            } else {
                shots.remove(i);
            }
        }
    }
    
    private class MyMouseListener extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e)
            {
                player1.mousePressed(e);
            }
    }
    
    
    private class myMouseMotionListener implements MouseMotionListener{
        @Override
        public void mouseMoved(MouseEvent e){
            
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            
        }
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
            player1.keyPressed(e);
        }
    }
}
