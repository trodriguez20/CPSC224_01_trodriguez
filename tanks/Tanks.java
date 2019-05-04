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
    
    private JButton start;
    private JButton exit;
    
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
        public void actionPerformed(ActionEvent e)
        {
            JFrame game = new JFrame();
            game.setTitle("Tanks");
            
            game.setSize(1600, 900);
            game.setResizable(false);
            game.setVisible(false);
            game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JPanel gameBoard = new GameBoard(1);
            game.add(gameBoard);
            
            game.setVisible(true);
            dispose();
        }
    }
    
    private class exitListener extends JButton implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            System.exit(0);
        }
    }
    
    public static void main(String[] args) {
        new Tanks();
    }
    
}
