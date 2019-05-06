/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanks;

import java.awt.event.*;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Jared
 */
public class Tanks extends JFrame {
    
    private JButton start;
    private JButton exit;
    public static JFrame[] game;
    
    public Tanks()
    {
        JPanel title = new titlePanel();
        //GameBoard game = new GameBoard();
        add(title);
        start=new JButton("START");
        exit=new JButton("EXIT");
        start.setFont(new Font("Dialog", 1, 60));
        exit.setFont(new Font("Dialog", 1, 60));
        title.add(start);
        title.add(exit);
        start.addActionListener(new startListener());
        exit.addActionListener(new exitListener());
        
        
        setTitle("Tanks");
        setSize(1600, 900);
        
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
    }
    
    private class startListener extends titlePanel implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            game = new JFrame[3];
            game[0] = new JFrame();
            game[0].setTitle("Tanks");
            
            game[0].setSize(1600, 900);
            game[0].setResizable(false);
            game[0].setVisible(false);
            game[0].setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JPanel gameBoard = new GameBoard(0,0);
            game[0].add(gameBoard);
            
            game[0].setVisible(true);
            dispose();
        }
    }
    
    private class exitListener extends JButton implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            System.exit(0);
        }
    }
    
    public static void main(String[] args) {
        Tanks tanks = new Tanks();
    }
    
}
