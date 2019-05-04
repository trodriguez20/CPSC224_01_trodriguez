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
import java.awt.geom.AffineTransform;
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
    private AITanks grey;
    private AITanks blue;
    private AITanks red;
    final int DELAY = 10;
    private int[] wallX;
    private int[] wallY;
    private int[] wallWidth;
    private int[] wallHeight;
    /**
     * @param args the command line arguments
     */
    public GameBoard (int level)
    {
        addKeyListener(new TAdapter());
        addMouseListener(new MyMouseListener());
        addMouseMotionListener(new myMouseMotionListener());
        setBackground(new Color(235, 232, 145));
        setFocusable(true);
        
        //user chooses level on title screen, between 1, 2, and 3
        switch (level) {
            case 1:
                wallX = new int[]{0,0,0,1545,400,1100};
                wallY = new int[]{0,0,800,0,50,400};
                wallWidth = new int[]{1600,50,1600,50,50,50};
                wallHeight = new int[]{50,850,100,850,500,600};
                break;
            case 2:
                wallX = new int[9];
                wallY = new int[9];
                wallWidth = new int[9];
                wallHeight = new int[9];
                break;
            default:
                wallX = new int[8];
                wallY = new int[8];
                wallWidth = new int[8];
                wallHeight = new int[8];
                break;
        }
        
        
        player1 = new Player();
        grey = new AITanks();
        blue = new AITanks();
        red = new AITanks();
        
        timer = new Timer(DELAY, this);
        timer.start();
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.setColor(new Color(207, 163, 139));
        for(int i = 0; i < wallX.length; i++){
            g.fillRect(wallX[i], wallY[i], wallWidth[i], wallHeight[i]);
        }
        draw(g);
        
        Toolkit.getDefaultToolkit().sync();
    }
    
    private void draw(Graphics g)
    {
        
        Graphics2D g2d = (Graphics2D) g;

        AffineTransform at = AffineTransform.getTranslateInstance(player1.X+10, player1.Y+10);
        at.rotate((player1.angle + Math.PI/2), player1.widthT/2, player1.heightT/2);
        g2d.drawImage(player1.tankD, player1.X, player1.Y, this);
        g2d.drawImage(player1.tankT, at, this);
        
        AffineTransform atg = AffineTransform.getTranslateInstance(grey.Xg+10, grey.Yg+10);
        atg.rotate((Math.atan2(player1.Y + 25 - grey.Yg, player1.X + 25 - grey.Xg)) + 90, grey.widthgt/2, grey.heightgt/2);
        g2d.drawImage(grey.tankBG, grey.Xg, grey.Yg, this);
        g2d.drawImage(grey.tankTG, atg, this);
        
        AffineTransform atb = AffineTransform.getTranslateInstance(blue.Xb+10, blue.Yb+10);
        atb.rotate((Math.atan2(player1.Y + 25 - blue.Yb, player1.X + 25 - blue.Xb)) + 90, blue.widthgt/2, blue.heightgt/2);
        g2d.drawImage(blue.tankDB, blue.Xb, blue.Yb, this);
        g2d.drawImage(blue.tankTB, atb, this);
        
        AffineTransform atr = AffineTransform.getTranslateInstance(red.Xr+10, red.Yr+10);
        atr.rotate((Math.atan2(player1.Y + 25 - red.Yr, player1.X + 25 - red.Xr)) + 90, red.widthgt/2, red.heightgt/2);
        g2d.drawImage(red.tankDR, red.Xr, red.Yr, this);
        g2d.drawImage(red.tankTR, atr, this);
        
        List<Bullet> playerShots = player1.bullets;
        g2d.setColor(Color.black);
        for (Bullet shot : playerShots) {
            g2d.fillOval(shot.getX(), shot.getY(), 10, 10);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        player1.move();
        grey.moveAI();
        blue.moveAI();
        moveBullets();
        
       repaint();
    }
    
    private void moveBullets(){
        List<Bullet> shots = player1.bullets;

        for (int i = 0; i < shots.size(); i++) {

            Bullet bullet = shots.get(i);

            if (bullet.isVisible()) {
                bullet.move(wallX, wallY, wallWidth, wallHeight);
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
            player1.mouseMoved(e);
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            player1.mouseMoved(e);
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
