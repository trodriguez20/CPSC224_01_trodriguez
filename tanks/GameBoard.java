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
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;


public class GameBoard extends JPanel implements ActionListener {
    
    private Timer timer;
    public Player player1;
    int Score;
    int scoreprev;
    private AITanks[] compTanks;
    final int DELAY = 10;
    private int[] wallX;
    private int[] wallY;
    private int[] wallWidth;
    private int[] wallHeight;
    int lvl;
    /**
     * @param args the command line arguments
     */
    public GameBoard (int level, int score)
    {
        addKeyListener(new TAdapter());
        addMouseListener(new MyMouseListener());
        addMouseMotionListener(new myMouseMotionListener());
        setBackground(new Color(235, 232, 145));
        setFocusable(true);
        
        switch (level) 
        {
            case 0:
                wallX = new int[]{0,0,0,1545,400,1100};
                wallY = new int[]{0,0,800,0,50,400};
                wallWidth = new int[]{1600,50,1600,50,50,50};
                wallHeight = new int[]{50,850,100,850,500,600};
                compTanks = new AITanks[3];
                compTanks[0] = new AITanks(1400, 100, 'g', wallX, wallY, wallWidth, wallHeight);
                compTanks[1] = new AITanks(1400, 600, 'b', wallX, wallY, wallWidth, wallHeight);
                compTanks[2] = new AITanks(1400, 300, 'r', wallX, wallY, wallWidth, wallHeight);
                break;
            case 1:
                wallX = new int[]{0,0,0,1545,400};
                wallY = new int[]{0,0,800,0,50};
                wallWidth = new int[]{1600,50,1600,50,50};
                wallHeight = new int[]{50,850,100,850,500};
                compTanks = new AITanks[3];
                compTanks[0] = new AITanks(1400, 100, 'g', wallX, wallY, wallWidth, wallHeight);
                compTanks[1] = new AITanks(1400, 600, 'b', wallX, wallY, wallWidth, wallHeight);
                compTanks[2] = new AITanks(1400, 300, 'r', wallX, wallY, wallWidth, wallHeight);
                break;
            default:
                wallX = new int[]{0,0,0,1545};
                wallY = new int[]{0,0,800,0};
                wallWidth = new int[]{1600,50,1600,50};
                wallHeight = new int[]{50,850,100,850};
                compTanks = new AITanks[6];
                compTanks[0] = new AITanks(700, 100, 'g', wallX, wallY, wallWidth, wallHeight);
                compTanks[1] = new AITanks(700, 600, 'r', wallX, wallY, wallWidth, wallHeight);
                compTanks[2] = new AITanks(700, 300, 'r', wallX, wallY, wallWidth, wallHeight);
                compTanks[3] = new AITanks(1400, 100, 'g', wallX, wallY, wallWidth, wallHeight);
                compTanks[4] = new AITanks(1400, 600, 'b', wallX, wallY, wallWidth, wallHeight);
                compTanks[5] = new AITanks(1400, 300, 'r', wallX, wallY, wallWidth, wallHeight);
                break;
        }
        
        player1 = new Player();
        
        timer = new Timer(DELAY, this);
        timer.start();
        lvl = level;
        Score = scoreprev = score;
        System.out.println("current lvl: " + lvl);
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.setColor(new Color(207, 163, 139));
        for(int i = 0; i < wallX.length; i++)
        {
            g.fillRect(wallX[i], wallY[i], wallWidth[i], wallHeight[i]);
        }
        draw(g);
        
        Toolkit.getDefaultToolkit().sync();
    }
    
    private void draw(Graphics g)
    {
        
        Graphics2D g2d = (Graphics2D) g;

        if(player1.alive)
        {
            AffineTransform at = AffineTransform.getTranslateInstance(player1.X+10, player1.Y+10);
            at.rotate(player1.angle + (Math.PI/2), player1.widthT/2, player1.heightT/2);
            g2d.drawImage(player1.tankD, player1.X, player1.Y, this);
            g2d.drawImage(player1.tankT, at, this);
        }
        
        for (AITanks compTank : compTanks) {
            if(compTank.alive){
                AffineTransform atg = AffineTransform.getTranslateInstance(compTank.X + 10, compTank.Y + 10);
                atg.rotate((Math.atan2(player1.Y + 25 - (compTank.Y+10), player1.X + 25 - (compTank.X+10))) + (Math.PI/2), compTank.widthT / 2, compTank.heightT / 2);
                g2d.drawImage(compTank.tankB, compTank.X, compTank.Y, this);
                g2d.drawImage(compTank.tankT, atg, this);
            }
        }
        
        for(AITanks compTank : compTanks){
            List<Bullet> aiShots = compTank.bullets;
            g2d.setColor(Color.black);
            for (Bullet shot : aiShots) {
                g2d.fillOval(shot.getX(), shot.getY(), 10, 10);
            }
        }
        
        List<Bullet> playerShots = player1.bullets;
        g2d.setColor(Color.black);
        for (Bullet shot : playerShots) {
            g2d.fillOval(shot.getX(), shot.getY(), 10, 10);
        }
       
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        player1.move(wallX, wallY, wallWidth, wallHeight);
        for (AITanks compTank : compTanks) {
            compTank.moveAI(player1.X, player1.Y);
            //compTank.aiShoot();
        }
       
        moveBullets(player1.bullets);
        for(AITanks compTank : compTanks){
            moveBullets(compTank.bullets);
        }
       repaint();
       levelOver();
    }
    
    private void moveBullets(List<Bullet> shots){

        for (int i = 0; i < shots.size(); i++) {

            Bullet bullet = shots.get(i);
            
            for(AITanks compTank : compTanks){
                if(bullet.hit(compTank.X, compTank.Y)){
                    compTank.alive = false;
                    Score += 1;
                    compTank.X = 0;
                    compTank.Y = 0;
                }
            }
            
            if(bullet.hit(player1.X, player1.Y)){
                player1.alive = false;
                player1.X = 0;
                player1.Y = 0;
            }
            
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
    
    private void levelOver()
    {
        boolean allDead = true;
        for(AITanks compTank : compTanks){
            if(compTank.alive == true){
                allDead = false;
            }
        }
        
        boolean playerDead = true;
        if(player1.alive==true)
        {
            playerDead=false;
        }
        
        if(playerDead)
        {
            int choice2=JOptionPane.showConfirmDialog(null,"<html>SCORE: " + Score + "<br>TRY AGAIN?</html>", "GAME OVER!", JOptionPane.YES_NO_OPTION);
            if(choice2==JOptionPane.YES_OPTION)
            {
                compTanks[0].alive = true;
                player1.alive=true;
                Tanks.game[lvl].dispose();
                Tanks.game[lvl] = new JFrame();
                Tanks.game[lvl].setTitle("Tanks");
                Tanks.game[lvl].setSize(1600, 900);
                Tanks.game[lvl].setResizable(false);
                Tanks.game[lvl].setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                JPanel gameBoard = new GameBoard(lvl, scoreprev);
                Tanks.game[lvl].add(gameBoard);
                Tanks.game[lvl].setVisible(true);
            }
            else if(choice2==JOptionPane.NO_OPTION)
            {
                    //compTanks[0].alive = true;
                    player1.alive=true;
                    new Tanks(); 
                    Tanks.game[lvl].dispose(); 
            }
            else
            {
                
            }
        }
        
        if(allDead)
        {
            if(lvl<2)
            {
                int choice=JOptionPane.showConfirmDialog(null,"<html>SCORE: " + Score + "<br>CONTINUE?</html>", "LEVEL CLEAR!", JOptionPane.YES_NO_OPTION);
                if(choice==JOptionPane.YES_OPTION)
                {
                    lvl ++;
                    Tanks.game[lvl] = new JFrame();
                    Tanks.game[lvl].setTitle("Tanks");
                    Tanks.game[lvl].setSize(1600, 900);
                    Tanks.game[lvl].setResizable(false);
                    Tanks.game[lvl].setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    JPanel gameBoard = new GameBoard(lvl, Score);
                    Tanks.game[lvl].add(gameBoard);
                    Tanks.game[lvl].setVisible(true);
                    Tanks.game[lvl-1].dispose();
                    compTanks[0].alive = true;
                    //player1.alive=true;
                }
                else if(choice==JOptionPane.NO_OPTION)
                {
                    compTanks[0].alive = true;
                   // player1.alive=true;
                    new Tanks(); 
                    Tanks.game[lvl].dispose();  
                }
                else
                {

                }
            }
            else
            {
                int choice1=JOptionPane.showConfirmDialog(null,"<html>SCORE: " + Score + "<br>PLAY AGAIN?</html>", "YOU WIN!", JOptionPane.YES_NO_OPTION);
                if(choice1==JOptionPane.YES_OPTION)
                {
                    Tanks.game[0] = new JFrame();
                    Tanks.game[0].setTitle("Tanks");
                    Tanks.game[0].setSize(1600, 900);
                    Tanks.game[0].setResizable(false);
                    Tanks.game[0].setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    JPanel gameBoard = new GameBoard(0, 0);
                    Tanks.game[0].add(gameBoard);
                    Tanks.game[0].setVisible(true);
                    Tanks.game[lvl].dispose();
                    compTanks[0].alive = true;
                    //player1.alive=true;
                }
                else if(choice1==JOptionPane.NO_OPTION)
                {
                    compTanks[0].alive = true;
                    //player1.alive=true;
                    new Tanks(); 
                    Tanks.game[lvl].dispose();
                }
            }
        }
    }
}
